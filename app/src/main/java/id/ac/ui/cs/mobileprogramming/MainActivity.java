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
    //private TextView myTextViewResult;
    //private RequestQueue quoteRequestQueue;
    //private ImageView imageView;
    //private RequestQueue catRequestQueue;


    //String catPictureUrl = "";

    public void changeToQuoteActivity () {
        Intent intent = new Intent(MainActivity.this, RandomQuoteActivity.class);
        startActivity(intent);
    }

    public void changeToPictureActivity() {
        Intent intent = new Intent(MainActivity.this, RandomPictureActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myTextViewResult = findViewById(R.id.text_view_result);
        //imageView = findViewById(R.id.bubble_speech);
        Button quoteButton = findViewById(R.id.button_random_quote);
        Button catButton = findViewById(R.id.button_random_cat_picture);
        //quoteRequestQueue = Volley.newRequestQueue(this);
        //catRequestQueue = Volley.newRequestQueue(this);

        //imageView.setImageResource(R.drawable.bubblespeech);

        quoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToQuoteActivity();
                /*
                myTextViewResult.setText("");
                imageView.setBackground(null);
                imageView.setImageResource(android.R.color.transparent); // clear image
                parseQuoteJSON();
                */

            }
        });
        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToPictureActivity();
                /*
                myTextViewResult.setText("");
                String pictureUrl = parseCatJSON();
                imageView.setBackground(null);
                new DownloadImageTask(findViewById(R.id.bubble_speech)).execute(pictureUrl);

                 */
            }
        });



    }




}