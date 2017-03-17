package info.androidhive.cardview;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Album {
    private static int numOfObj = 0;
    private int id;
    private String name;
    private int numOfSongs;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, int numOfSongs, int thumbnail) {
        this.id = numOfObj;
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
        numOfObj++;
    }

    public Album(String name, int numOfSongs) {
        this.id = numOfObj;
        this.name = name;
        this.numOfSongs = numOfSongs;
        numOfObj++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
