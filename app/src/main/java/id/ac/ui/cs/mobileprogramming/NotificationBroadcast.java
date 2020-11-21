package id.ac.ui.cs.mobileprogramming;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

public class NotificationBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                "backgroundNotif")
                .setSmallIcon(R.drawable.bubblespeech)
                .setContentTitle("Your hourly quote")
                .setContentText(randomQuote())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());




    }

    // http://tutorials.jenkov.com/java-util-concurrent/atomicreference.html
    private String randomQuote() {
        String url = "https://thesimpsonsquoteapi.glitch.me/quotes";
        AtomicReference<String> quote = new AtomicReference<>("");
        new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response.toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        quote.set(jsonObject.getString("quote"));
                        Log.i("quote:", quote.get());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());
        return quote.get();

    }


}
