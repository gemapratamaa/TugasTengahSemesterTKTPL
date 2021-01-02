package id.ac.ui.cs.mobileprogramming.gemaPratamaAditya.funPicsAndQuotes;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
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
        denyPermissionText.setText("INFO: This app needs a permission to turn on GPS to give you a " +
                "latitude and longitude of your current location.");

        ActivityCompat.requestPermissions(LocationActivity.this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
                }, 123
        );

        getLocationButton.setOnClickListener(v -> {
            GPSTracker tracker = new GPSTracker(getApplicationContext());
            Location location = tracker.getLocation();
            if (location != null) {
                String latitude = "Latitude: " + location.getLatitude();
                String longitude = "Longitude: " + location.getLongitude();
                denyPermissionText.setText("");
                latitudeTv.setText(latitude);
                longitudeTv.setText(longitude);
            } else {
                //denyPermissionText.setText("This app needs a permission to turn on GPS to give you a latitude and longitude of your current location.");
                getLocationButton.setVisibility(View.GONE);
                latitudeTv.setText("");
                longitudeTv.setText("");
            }
        });
    }
}
