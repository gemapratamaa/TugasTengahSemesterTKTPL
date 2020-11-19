package id.ac.ui.cs.mobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private TextView myTextViewResult;
    private RequestQueue myRequestQueue;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextViewResult = findViewById(R.id.text_view_result);
        Button quoteButton = findViewById(R.id.button_random_quote);

        Button catButton = findViewById(R.id.button_random_cat_picture);

        imageView = findViewById(R.id.cat_picture);
        //imageView.setBackground(null);

        myRequestQueue = Volley.newRequestQueue(this);

        quoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextViewResult.setText("");
                parseQuoteJSON();
            }
        });

        catButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("onclick catbutton", "MASUKKKKKK");

                String pictureUrl = parseCatJSON();

                //String catPictureUrl = "https://cdn2.thecatapi.com/images/b1u.jpg";
                Log.i("[cat button onclick]", pictureUrl);
                //imageView.setBackground(null);
                new DownloadImageTask((ImageView) findViewById(R.id.cat_picture))
                        .execute(pictureUrl);
            }

        });

    }

    private String parseCatJSON() {
        Log.i("parsecatjson", "baru masuk method");
        String url = "https://api.thecatapi.com/v1/images/search";
        Log.i("parsecatjson", "lewat String url");
        String[] catPictureUrl = {""};
        Log.i("parsecatjson", "lewat String[] catPictureUrl");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.i("parsecatjson onresponse", "masuk onResponse");
                            JSONArray jsonArray = new JSONArray(response.toString());
                            Log.i("parsecatjson onresponse", "lewat jsonArray = new");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            Log.i("parsecatjson onresponse", "lewat jsonobject = jsonArray");
                            catPictureUrl[0] = jsonObject.getString("url");
                            Log.i("parsecatjson onresponse", "lewat catPictureUrl[0] =");
                            Log.i("[onresponse][try]", catPictureUrl[0]);
                        } catch (JSONException e) {
                            Log.i("[jsonexception e]", "masuk jsonexception e");
                            Log.i("[jsonexception e]", e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("[onerrorresponse]", error.toString());
                error.printStackTrace();
            }
        });

        Log.i("cat url: ", catPictureUrl[0]);
        myRequestQueue.add(request);
        return catPictureUrl[0];
    }

    private void parseQuoteJSON() {
        String url = "https://thesimpsonsquoteapi.glitch.me/quotes";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response.toString());
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String quote = jsonObject.getString("quote");
                            Log.i("array", jsonObject.toString());
                            myTextViewResult.setText("");
                            myTextViewResult.append(quote);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        myRequestQueue.add(request);
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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
        }
    }
}


// CAT: https://api.thecatapi.com/v1/images/search