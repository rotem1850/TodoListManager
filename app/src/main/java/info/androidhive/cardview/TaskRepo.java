package info.androidhive.cardview;

/**
 * Created by rotem on 4/25/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskRepo {
    private DBHelper dbHelper;

    public TaskRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Album task) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("taskId", task.getId());
        values.put("taskName",task.getName());
        values.put("taskDate", task.getDate());

        // Inserting Row
        long student_Id = db.insert("Tasks", null, values);
        db.close(); // Closing database connection
        return (int) student_Id;
    }

    public void delete(int task_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete("Tasks", "taskId" + "= ?", new String[] { String.valueOf(task_Id) });
        db.close(); // Closing database connection
    }

    public void update(Album task) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("taskId", task.getId());
        values.put("taskName",task.getName());
        values.put("taskDate", task.getDate());


        // It's a good practice to use parameter ?, instead of concatenate string
        db.update("Tasks", values, "taskId" + "= ?", new String[] { String.valueOf(task.getId()) });
        db.close(); // Closing database connection
    }

    public ArrayList<Album>  getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT " +
                "taskId"+ "," +
                "taskName" + "," +
                "taskDate" +
                " FROM " + "Tasks";

        //Student student = new Student();
        ArrayList<Album> studentList = new ArrayList<Album>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                //HashMap<String, String> student = new HashMap<String, String>();
                //student.put("id", cursor.getString(cursor.getColumnIndex(Student.KEY_ID)));
                //student.put("name", cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
                Album album= new Album(cursor.getInt(cursor.getColumnIndex("taskId")), cursor.getString(cursor.getColumnIndex("taskName")), cursor.getString(cursor.getColumnIndex("taskDate")), 0);
                studentList.add(album);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

    public Album getStudentById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT " +
                "taskId"+ "," +
                "taskName" + "," +
                "taskDate" +
                " FROM " + "Tasks"
                + " WHERE " +
                "taskId" + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Album album = new Album();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                album.setId(cursor.getInt(cursor.getColumnIndex("taskId")));
                album.setName(cursor.getString(cursor.getColumnIndex("taskName")));
                album.setDate(cursor.getString(cursor.getColumnIndex("taskDate")));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return album;
    }

}