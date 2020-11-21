package id.ac.ui.cs.mobileprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RandomQuoteActivity extends AppCompatActivity {
    private RequestQueue quoteRequestQueue;
    private QuoteViewModel quoteViewModel;
    private Button fetchAgainButton;

    public static final int ADD_QUOTE_REQUEST = 1;
    public static final int EDIT_QUOTE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_quote);

        Toast.makeText(RandomQuoteActivity.this, R.string.swipe_left_to_delete_quote, Toast.LENGTH_SHORT).show();

        fetchAgainButton = findViewById(R.id.fetch_again_button);

        fetchAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseQuoteJSON();
            }
        });

        FloatingActionButton buttonAddQuote = findViewById(R.id.button_add_quote);
        buttonAddQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomQuoteActivity.this, AddEditQuoteActivity.class);
                startActivityForResult(intent, ADD_QUOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        QuoteAdapter adapter = new QuoteAdapter();
        recyclerView.setAdapter(adapter);

        quoteViewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);
        quoteViewModel.getAllQuotes().observe(this, new Observer<List<Quote>>() {
            @Override
            public void onChanged(List<Quote> quotes) {

                adapter.setQuotes(quotes);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                quoteViewModel.delete(adapter.getQuoteAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnQuoteClickListener(new QuoteAdapter.OnQuoteClickListener() {

            @Override
            public void onQuoteClick(Quote quote) {
                Intent intent = new Intent(RandomQuoteActivity.this, AddEditQuoteActivity.class);
                intent.putExtra(AddEditQuoteActivity.EXTRA_ID, quote.getId());
                intent.putExtra(AddEditQuoteActivity.EXTRA_QUOTE, quote.getQuote());
                startActivityForResult(intent, EDIT_QUOTE_REQUEST);
            }

        });

        quoteRequestQueue = Volley.newRequestQueue(this);
        parseQuoteJSON();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == ADD_QUOTE_REQUEST) {
            String quote = data.getStringExtra(AddEditQuoteActivity.EXTRA_QUOTE);
            quoteViewModel.insert(new Quote(quote));
            Toast.makeText(this, R.string.quote_added, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.quote_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.delete_all_quotes:
                quoteViewModel.deleteAllQuotes();
                Toast.makeText(this, R.string.all_quotes_removed, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void parseQuoteJSON() {
        String url = "https://thesimpsonsquoteapi.glitch.me/quotes";
        String quote = "";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response.toString());
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String quote = jsonObject.getString("quote");
                            quoteViewModel.insert(new Quote(quote));
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
    }
}