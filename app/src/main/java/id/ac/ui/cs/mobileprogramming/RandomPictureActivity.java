package id.ac.ui.cs.mobileprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RandomPictureActivity extends AppCompatActivity {

    private Button retryButton;
    private ImageView imageView;
    private RequestQueue catRequestQueue;
    private String catPictureUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_picture);

        retryButton = findViewById(R.id.retry_button);
        imageView = findViewById(R.id.random_picture);



        catRequestQueue = Volley.newRequestQueue(this);
        imageView.setImageResource(android.R.color.transparent); // clear image
        catPictureUrl = parseCatJSON();
        imageView.setBackground(null);
        new DownloadImageTask(imageView).execute(catPictureUrl);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catPictureUrl = parseCatJSON();
                imageView.setBackground(null);
                new DownloadImageTask(imageView).execute(catPictureUrl);
            }
        });
    }


    public String parseCatJSON() {
        Log.i("parsecatjson", "baru masuk method");
        String url = "https://api.thecatapi.com/v1/images/search";
        Log.i("parsecatjson", "lewat String url");
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
                            catPictureUrl = jsonObject.getString("url");
                            Log.i("parsecatjson onresponse", "lewat catPictureUrl[0] =");
                            Log.i("[onresponse][try]", catPictureUrl);
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

        Log.i("cat url: ", "catPicture url" + catPictureUrl);
        catRequestQueue.add(request);
        return catPictureUrl;
    }
}