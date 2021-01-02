package id.ac.ui.cs.mobileprogramming;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LocationActivity extends AppCompatActivity {

    Button getLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getLocationButton = findViewById(R.id.button_get_location);

        ActivityCompat.requestPermissions(LocationActivity.this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
                }, 123
        );

        getLocationButton.setOnClickListener(v -> {
            GPSTracker tracker = new GPSTracker(getApplicationContext());
            Location location = tracker.getLocation();
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String info = "Lat: " + latitude + ", longitude: " + longitude;
                Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
            }
        });
    }
}
