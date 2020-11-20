package id.ac.ui.cs.mobileprogramming;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView myTextViewResult;
    private RequestQueue quoteRequestQueue;
    private RequestQueue catRequestQueue;
    private ImageView imageView;

    String catPictureUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextViewResult = findViewById(R.id.text_view_result);
        imageView = findViewById(R.id.cat_picture);
        Button quoteButton = findViewById(R.id.button_random_quote);
        Button catButton = findViewById(R.id.button_random_cat_picture);


        quoteRequestQueue = Volley.newRequestQueue(this);
        catRequestQueue = Volley.newRequestQueue(this);

        quoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextViewResult.setText("");
                imageView.setBackground(null);
                imageView.setImageResource(android.R.color.transparent); // clear image
                parseQuoteJSON();
            }
        });
        catButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                myTextViewResult.setText("");
                String pictureUrl = parseCatJSON();
                imageView.setBackground(null);
                new DownloadImageTask(findViewById(R.id.cat_picture)).execute(pictureUrl);
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
        quoteRequestQueue.add(request);
        Log.i("parsequotejson", "lewat myrequestqueue add request");
    }
}