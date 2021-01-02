package id.ac.ui.cs.mobileprogramming.gemaPratamaAditya.funPicsAndQuotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuoteDao {

    @Insert
    void insert(Quote quote);

    @Update
    void update(Quote quote);

    @Delete
    void delete(Quote quote);

    @Query("DELETE FROM quote_table")
    void deleteAllQuotes();

    @Query("SELECT * FROM quote_table")
    LiveData<List<Quote>> getAllQuotes();
}
