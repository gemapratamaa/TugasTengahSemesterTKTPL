package id.ac.ui.cs.mobileprogramming;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

public class NotificationBroadcast extends BroadcastReceiver {

    // https://stackoverflow.com/questions/16855849/multiple-notifications-and-only-show-the-first-one-in-android
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(getClass().getName(), "masuk onreceive");
        int id = (int) System.currentTimeMillis();

        String randomQuote = randomQuote();
        Log.i("randomquote: ",randomQuote);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                "backgroundNotif")
                .setSmallIcon(R.drawable.bubblespeech)
                .setContentTitle("Your hourly quote")
                .setContentText(randomQuote)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(id, builder.build());

    }

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


}
