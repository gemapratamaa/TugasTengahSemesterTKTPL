package id.ac.ui.cs.mobileprogramming.gemaPratamaAditya.funPicsAndQuotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteHolder> {

    private List<Quote> quotes = new ArrayList<>();
    private OnQuoteClickListener listener;

    @NonNull
    @Override
    public QuoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quote_item, parent, false);
        return new QuoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteHolder holder, int position) {
        Quote currentQuote = quotes.get(position);
        holder.textViewQuote.setText(currentQuote.getQuote());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }

    public Quote getQuoteAt(int position) {
        return quotes.get(position);
    }

    class QuoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewQuote;

        public QuoteHolder(View itemView) {
            super(itemView);
            textViewQuote = itemView.findViewById(R.id.quote_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onQuoteClick(quotes.get(position));
                    }

                }
            });


        }
    }

    public interface OnQuoteClickListener {
        void onQuoteClick(Quote quote);

    }

    public void setOnQuoteClickListener(OnQuoteClickListener listener) {
        this.listener = listener;
    }
}
