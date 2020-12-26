package id.ac.ui.cs.mobileprogramming;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
        Button quoteButton = findViewById(R.id.button_random_quote);
        Button catButton = findViewById(R.id.button_random_cat_picture);
        Button reminder = findViewById(R.id.button_reminder);
        notificationManager = NotificationManagerCompat.from(this);



        reminder.setOnClickListener(v -> {
            int id = (int) System.currentTimeMillis();
            Toast.makeText(this, "Hourly quote set!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, NotificationBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,id, intent,0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            //long timeAtButtonClick = System.currentTimeMillis();
            //long fiveSeconds = 10 * 1000;
            long oneHour = 3600 * 1000;

            alarmManager.setRepeating(AlarmManager.RTC, Calendar.getInstance().getTimeInMillis(),
                    oneHour, //fiveSeconds,
                    pendingIntent
            );
        });


        quoteButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RandomQuoteActivity.class);
            startActivity(intent);

        });
        catButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RandomPictureActivity.class);
            startActivity(intent);
        });

        if (!isConnectedToInternet()) {
            quoteButton.setVisibility(View.GONE);
            catButton.setVisibility(View.GONE);
        }
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My reminder";
            String description = "My reminder";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel("backgroundNotif", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }

}