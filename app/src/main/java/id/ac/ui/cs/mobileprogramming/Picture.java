package id.ac.ui.cs.mobileprogramming;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="picture_table")
public class Picture {
    @PrimaryKey(autoGenerate=true)
    private int id;

    private String pictureUrl;

    public Picture(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}


