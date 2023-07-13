package sg.edu.rp.c346.id22035357.song;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Parcelable {
    private int id;
    private String title;
    private String singers;
    private int years;
    private int stars;


    public Song(int id, String title, String singers, int years, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.years = years;
        this.stars = stars;
    }

    protected Song(Parcel in) {
        id = in.readInt();
        title = in.readString();
        singers = in.readString();
        years = in.readInt();
        stars = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYears() {
        return years;
    }

    public int getStars() {
        return stars;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @NonNull
    @Override
    public String toString() {
        return id + "\n" + title + "\n" + singers + "\n" + years + "\n" + stars;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(singers);
        dest.writeInt(years);
        dest.writeInt(stars);
    }
}