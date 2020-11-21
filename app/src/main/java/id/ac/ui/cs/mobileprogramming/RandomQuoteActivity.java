package id.ac.ui.cs.mobileprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RandomQuoteActivity extends AppCompatActivity {

    private TextView myTextViewResult;
    private RequestQueue quoteRequestQueue;
    private QuoteViewModel quoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(getClass().getName(), "masuk oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_quote);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        QuoteAdapter adapter = new QuoteAdapter();
        recyclerView.setAdapter(adapter);


        quoteViewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);
        quoteViewModel.getAllQuotes().observe(this, new Observer<List<Quote>>() {
            @Override
            public void onChanged(List<Quote> quotes) {
                Toast.makeText(RandomQuoteActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                adapter.setQuotes(quotes);
            }
        });


        myTextViewResult = findViewById(R.id.text_view_result);
        quoteRequestQueue = Volley.newRequestQueue(this);
        parseQuoteJSON();

        Log.i(getClass().getName(), "parsequotejson() lewat");
    }

    private void parseQuoteJSON() {
        String url = "https://thesimpsonsquoteapi.glitch.me/quotes";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            Log.i(getClass().getName(), "masuk onresponse try");
                            JSONArray jsonArray = new JSONArray(response.toString());
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String quote = jsonObject.getString("quote");
                            Log.i("array", jsonObject.toString());
                            Log.i(getClass().getName(), "quote: " + quote);
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