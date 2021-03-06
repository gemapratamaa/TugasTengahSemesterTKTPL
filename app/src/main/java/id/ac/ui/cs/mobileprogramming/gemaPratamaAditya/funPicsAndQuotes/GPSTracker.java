package id.ac.ui.cs.mobileprogramming.gemaPratamaAditya.funPicsAndQuotes;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class GPSTracker implements LocationListener {

    Context context;

    public GPSTracker(Context context) {
        this.context = context;
    }

    public Location getLocation() {

        if (ContextCompat.checkSelfPermission(this.context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show();
            return null;
        }

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isGPSEnabled) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            // keknya yg permission di sini
            String info = "This app needs a permission to turn on GPS to give you a latitude and longitude of your current location.";
            Toast.makeText(context, info, Toast.LENGTH_LONG).show();
        }

        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}
