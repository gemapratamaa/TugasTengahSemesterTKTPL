package id.ac.ui.cs.mobileprogramming.gemaPratamaAditya.funPicsAndQuotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuoteViewModel extends AndroidViewModel {

    private QuoteRepository repository;
    private LiveData<List<Quote>> allQuotes;


    public QuoteViewModel(@NonNull Application application) {
        super(application);
        repository = new QuoteRepository(application);
        allQuotes = repository.getAllQuotes();
    }

    public void insert(Quote quote) {
        repository.insert(quote);
    }

    public void update(Quote quote) {
        repository.update(quote);
    }

    public void delete(Quote quote) {
        repository.delete(quote);
    }

    public void deleteAllQuotes() {
        repository.deleteAllQuotes();
    }

    public LiveData<List<Quote>> getAllQuotes() {
        return allQuotes;
    }

}
