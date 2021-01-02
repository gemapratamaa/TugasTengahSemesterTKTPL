package id.ac.ui.cs.mobileprogramming.gemaPratamaAditya.funPicsAndQuotes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PictureRepository {
    private PictureDao pictureDao;
    private LiveData<List<Picture>> allPictureUrls;

    public PictureRepository(Application application) {
        PictureDatabase database = PictureDatabase.getInstance(application);
        pictureDao = database.pictureDao();

        allPictureUrls = pictureDao.getAllPictureUrls();
    }
}



