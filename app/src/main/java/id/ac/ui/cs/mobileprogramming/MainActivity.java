package id.ac.ui.cs.mobileprogramming;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private static final String DEBUG_TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
        Button quoteButton = findViewById(R.id.button_random_quote);
        Button catButton = findViewById(R.id.button_random_cat_picture);
        Button animationButton = findViewById(R.id.button_animation);
        Button reminder = findViewById(R.id.button_reminder);
        notificationManager = NotificationManagerCompat.from(this);
        TextView tv = findViewById(R.id.is_connected_to_network);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }

        // fitur no9
        if (!isWifiConn && !isMobileConn) {
            tv.setText("Not connected to any network!");
            animationButton.setVisibility(View.GONE);
        }


        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
        Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);


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

        /*
        animationButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
            startActivity(intent);
        });

         */

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