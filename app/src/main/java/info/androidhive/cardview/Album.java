package info.androidhive.cardview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Album implements Parcelable {
    public static int numOfObj = 0;
    private int id;
    private String name;
    private String date;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, String date, int thumbnail) {
        this.id = numOfObj;
        this.name = name;
        this.date = date;
        this.thumbnail = thumbnail;
        numOfObj++;
    }

    public Album(String name, String date) {
        this.id = numOfObj;
        this.name = name;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private Album(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.date = in.readString();
        this.thumbnail = in.readInt();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.id);
        out.writeString(this.name);
        out.writeString(this.date);
        out.writeInt(this.thumbnail);
    }

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}
