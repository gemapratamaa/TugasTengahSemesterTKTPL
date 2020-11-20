package id.ac.ui.cs.mobileprogramming;

import android.content.Intent;
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

    private QuoteViewModel quoteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button quoteButton = findViewById(R.id.button_random_quote);
        Button catButton = findViewById(R.id.button_random_cat_picture);

        quoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToQuoteActivity();
            }
        });
        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToPictureActivity();
            }
        });
    }

    public void changeToQuoteActivity () {
        Intent intent = new Intent(MainActivity.this, RandomQuoteActivity.class);
        startActivity(intent);
    }

    public void changeToPictureActivity() {
        Intent intent = new Intent(MainActivity.this, RandomPictureActivity.class);
        startActivity(intent);
    }
}