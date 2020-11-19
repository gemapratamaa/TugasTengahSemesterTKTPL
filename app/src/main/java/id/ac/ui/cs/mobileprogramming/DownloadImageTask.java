package id.ac.ui.cs.mobileprogramming;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        Log.i("class DownloadImageTask", "masuk constructor");
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        Log.i("doInBackground", "masuk method");
        String urldisplay = urls[0];
        Log.i("doInBackground", "lewat urldisplay=");
        Bitmap mIcon11 = null;
        Log.i("doInBackground", "URL DISPLAY: " + urldisplay);
        Log.i("doInBackground", "len(urls): " + urls.length);
        for (String s : urls) {
            Log.i("for loop", s);
        }
        Log.i("doInBackground", "URLS: " + urls);
        Log.i("doInBackground", "lewat micon11 = null");

        try {
            Log.i("doInBackground", "masuk try");
            InputStream in = new java.net.URL(urldisplay).openStream();
            Log.i("doInBackground try", "lewat inputstream in =");
            mIcon11 = BitmapFactory.decodeStream(in);
            Log.i("doInBackground try", "micon11 =");
        } catch (Exception e) {
            Log.i("doinbackground", "masuk catch exception");
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        Log.i("doInBackground", "keluar try&catch");
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        Log.i("onpostexecute", "masuk method=");
        bmImage.setImageBitmap(result);
        Log.i("onpostexecute", "lewat bmimage.setimagebitmap");
    }
}