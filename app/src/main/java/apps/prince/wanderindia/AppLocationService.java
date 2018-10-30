package apps.prince.wanderindia;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class AppLocationService extends Service implements LocationListener {
    private static final long MIN_DISTANCE_FOR_UPDATE = 10L;
    private static final long MIN_TIME_FOR_UPDATE = 1000L;
    Location location;
    protected LocationManager locationManager;


    @SuppressLint("WrongConstant")
    public AppLocationService(Context params) {
        this.locationManager = ((LocationManager)params.getSystemService("location"));
    }

    @SuppressLint("MissingPermission")
    public Location getLocation(String paramString) {

       if(this.locationManager.isProviderEnabled(paramString))
       {
           this.locationManager.requestLocationUpdates(paramString,MIN_TIME_FOR_UPDATE,10.0F,this);
    if(this.locationManager!=null)
    {
        this.locationManager.requestLocationUpdates(paramString,MIN_TIME_FOR_UPDATE,10.0F,this);
        return this.location;
    }
       }return null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
