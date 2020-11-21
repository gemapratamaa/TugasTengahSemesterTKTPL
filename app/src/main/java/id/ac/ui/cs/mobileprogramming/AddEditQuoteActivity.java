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

public class AddEditQuoteActivity extends AppCompatActivity {

    public static final String EXTRA_QUOTE = "id.ac.ui.cs.mobileprogramming.EXTRA_QUOTE";
    public static final String EXTRA_ID = "id.ac.ui.cs.mobileprogramming.EXTRA_ID";

    private EditText editTextQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        editTextQuote = findViewById(R.id.edit_quote_title);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Quote");
            editTextQuote.setText(intent.getStringExtra(EXTRA_QUOTE));
        } else {
            setTitle(getString(R.string.add_quote_title));
        }

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
            Toast.makeText(this, R.string.quote_cannot_be_empty, Toast.LENGTH_SHORT);
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_QUOTE, quote);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }
}