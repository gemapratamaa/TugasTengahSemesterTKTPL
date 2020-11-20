package id.ac.ui.cs.mobileprogramming;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities={Quote.class}, version=1)
public abstract class QuoteDatabase extends RoomDatabase {
    private static QuoteDatabase instance; // Singleton
    public abstract QuoteDao quoteDao();

    public static synchronized QuoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QuoteDatabase.class, "quote_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuoteDao quoteDao;

        private PopulateDbAsyncTask(QuoteDatabase db) {
            quoteDao = db.quoteDao();
            new PopulateDbAsyncTask(instance).execute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String sampleQuote1 = "Your limitation - it's only your imagination.";
            String sampleQuote2 = "Push yourself, because no one else is going to do it for you.";
            String sampleQuote3 = "Sometimes later becomes never. Do it now.";

            quoteDao.insert(new Quote(sampleQuote1));
            quoteDao.insert(new Quote(sampleQuote2));
            quoteDao.insert(new Quote(sampleQuote3));

            return null;
        }

    }
}
