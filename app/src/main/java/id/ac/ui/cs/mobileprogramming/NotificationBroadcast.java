package id.ac.ui.cs.mobileprogramming;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class NotificationBroadcast extends BroadcastReceiver {

    //public Stack<String> stack = new Stack<>();

    // https://stackoverflow.com/questions/16855849/multiple-notifications-and-only-show-the-first-one-in-android
    @Override
    public void onReceive(Context context, Intent intent) {
        Random rand = new Random();
        Log.i(getClass().getName(), "masuk onreceive");
        int id = (int) System.currentTimeMillis();

        //pushQuote();
        String randomQuote = QuoteCollections.QUOTES[rand.nextInt(QuoteCollections.QUOTES.length)];
        Log.i("randomquote: ", randomQuote);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                "backgroundNotif")
                .setSmallIcon(R.drawable.bubblespeech)
                .setContentTitle("Your hourly quote")
                .setContentText(randomQuote)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(id, builder.build());

    }

    /* capek debug ini
    // http://tutorials.jenkov.com/java-util-concurrent/atomicreference.html
    private String randomQuote() {
        Log.i(getClass().getName(), "masuk randomquote()");
        String url = "https://thesimpsonsquoteapi.glitch.me/quotes";
        AtomicReference<String> quote = new AtomicReference<>("");
        new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        Log.i(getClass().getName(), "try ");
                        JSONArray jsonArray = new JSONArray(response.toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        quote.set(jsonObject.getString("quote"));
                        Log.i("quote:", quote.get());
                    } catch (JSONException e) {
                        Log.i(getClass().getName(), "catch");
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());

        return quote.get();

    }

    private void pushQuote() {
        Log.i(getClass().getName(), "masuk pushquote()");
        String url = "https://thesimpsonsquoteapi.glitch.me/quotes";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.i(getClass().getName(), "masuk try");
                            JSONArray jsonArray = new JSONArray(response.toString());
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String quote = jsonObject.getString("quote");
                            Log.i("quote:", quote);
                            stack.push(quote);
                        } catch (JSONException e) {
                            Log.i(getClass().getName(), "catch");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

    }
    */


}
