package id.ac.ui.cs.mobileprogramming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuoteActivity extends AppCompatActivity {

    public static final String EXTRA_QUOTE = "id.ac.ui.cs.mobileprogramming.EXTRA_QUOTE";

    private EditText editTextQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        editTextQuote = findViewById(R.id.edit_quote_title);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_delete); // ??
        setTitle("Add Quote");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_quote_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_quote) {
            saveQuote();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveQuote() {
        String quote = editTextQuote.getText().toString();

        if (quote.trim().equals("")) {
            Toast.makeText(this, "Please insert a quote", Toast.LENGTH_SHORT);
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_QUOTE, quote);

        setResult(RESULT_OK, data);
        finish();

    }
}