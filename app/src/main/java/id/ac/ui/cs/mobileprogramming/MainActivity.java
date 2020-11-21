package id.ac.ui.cs.mobileprogramming;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button quoteButton = findViewById(R.id.button_random_quote);
        Button catButton = findViewById(R.id.button_random_cat_picture);

        Button reminder = findViewById(R.id.button_reminder);
        notificationManager = NotificationManagerCompat.from(this);


        reminder.setOnClickListener(v -> {
            Toast.makeText(this, "Hourly quote set!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, NotificationBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            long timeAtButtonClick = System.currentTimeMillis();
            long fiveSeconds = 5 * 1000;
            long oneHour = 3600 * 1000;

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    timeAtButtonClick + fiveSeconds,
                    pendingIntent);
        });


        quoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RandomQuoteActivity.class);
                startActivity(intent);

            }
        });
        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RandomPictureActivity.class);
                startActivity(intent);

            }
        });

    }


    public void sendNotification(View v) {
        RandomQuoteActivity rqa = new RandomQuoteActivity();

        Notification notification =
                new NotificationCompat.Builder(this, ApplicationWrapper.CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.bubblespeech)
                        .setContentTitle("Your quote")
                        .setContentText("testtttttttt")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();

        notificationManager.notify(1, notification);

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My reminder";
            String description = "My reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("backgroundNotif", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }





}