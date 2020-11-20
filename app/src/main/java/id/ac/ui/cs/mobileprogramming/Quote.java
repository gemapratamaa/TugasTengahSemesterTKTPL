package id.ac.ui.cs.mobileprogramming;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="quote_table")
public class Quote {

    @PrimaryKey(autoGenerate=true)
    private int id;

    private String quote;

    public Quote(String quote) {
        this.quote = quote;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

}
