package id.ac.ui.cs.mobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class MainActivity extends AppCompatActivity {
    private TextView myTextViewResult;
    private RequestQueue myRequestQueue;

    private String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextViewResult = findViewById(R.id.text_view_result);
        Button parseButton = findViewById(R.id.button_parse);

        myRequestQueue = Volley.newRequestQueue(this);

        parseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                parseJSON();
            }
        });

    }

    private void parseJSON() {
        String url = "https://thesimpsonsquoteapi.glitch.me/quotes";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONArray jsonArray = response.getJSONArray("");
                            JSONArray jsonArray = new JSONArray(response.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String quote = jsonObject.getString("quote");

                                myTextViewResult.append(quote);
                            }


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
}