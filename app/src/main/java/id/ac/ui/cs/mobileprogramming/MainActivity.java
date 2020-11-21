package id.ac.ui.cs.mobileprogramming;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        notificationManager = NotificationManagerCompat.from(this);

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







}