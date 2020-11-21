package id.ac.ui.cs.mobileprogramming;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class NotificationBroadcast extends BroadcastReceiver {

    // https://stackoverflow.com/questions/16855849/multiple-notifications-and-only-show-the-first-one-in-android
    @Override
    public void onReceive(Context context, Intent intent) {
        Random rand = new Random();
        int id = (int) System.currentTimeMillis();

        String randomQuote = QuoteCollections.QUOTES[rand.nextInt(QuoteCollections.QUOTES.length)];

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                "backgroundNotif")
                .setSmallIcon(R.drawable.bubblespeech)
                .setContentTitle("Your hourly quote")
                .setContentText(randomQuote)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(id, builder.build());

    }

}
