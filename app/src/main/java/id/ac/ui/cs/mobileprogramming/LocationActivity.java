package id.ac.ui.cs.mobileprogramming;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LocationActivity extends AppCompatActivity {

    Button getLocationButton;

    TextView denyPermissionText;
    TextView longitudeTv;
    TextView latitudeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getLocationButton = findViewById(R.id.button_get_location);

        denyPermissionText = findViewById(R.id.deny_text);
        longitudeTv = findViewById(R.id.longitude);
        latitudeTv = findViewById(R.id.latitude);
        ActivityCompat.requestPermissions(LocationActivity.this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
                }, 123
        );

        /*
        if (ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            denyPermissionText.setText("This app needs a permission to turn on GPS to give you a latitude and longitude of your current location.");
            getLocationButton.setVisibility(View.GONE);
        } else {
            denyPermissionText.setText("");
        }


         */
        getLocationButton.setOnClickListener(v -> {
            GPSTracker tracker = new GPSTracker(getApplicationContext());
            Location location = tracker.getLocation();
            if (location != null) {
                String latitude = String.valueOf(location.getLatitude());
                String longitude = String.valueOf(location.getLongitude());
                latitudeTv.setText(latitude);
                longitudeTv.setText(longitude);
            }
        });
    }
}
