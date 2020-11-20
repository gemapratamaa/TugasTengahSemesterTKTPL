package id.ac.ui.cs.mobileprogramming;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Picture.class}, version=1)
public abstract class PictureDatabase extends RoomDatabase {

    private static PictureDatabase instance; // Singleton

    public abstract PictureDao pictureDao();

    public static synchronized PictureDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PictureDatabase.class, "picture_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
