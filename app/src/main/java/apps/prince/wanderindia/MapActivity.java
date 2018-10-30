package apps.prince.wanderindia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.tomtom.online.sdk.common.location.GeoConstants;
import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.CameraPosition;
import com.tomtom.online.sdk.map.MapFragment;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.SimpleMarkerBalloon;
import com.tomtom.online.sdk.map.TomtomMap;
import com.tomtom.online.sdk.search.OnlineSearchApi;
import com.tomtom.online.sdk.search.SearchApi;
import com.tomtom.online.sdk.search.api.alongroute.AlongRouteSearchApi;
import com.tomtom.online.sdk.search.data.alongroute.AlongRouteSearchQueryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "MapActivity";
    private static String FINE_LOCATION= Manifest.permission.ACCESS_FINE_LOCATION;
    private static  String COARSE_LOCATION=Manifest.permission.ACCESS_COARSE_LOCATION;
    private FusedLocationProviderClient fusedlocator;
    private static  final long DEFAULT_ZOOM=15L;
        //vars
    private Boolean mLocationPermission=false;
    private TomtomMap tmap;
    private EditText tomTextbar;
    public SearchApi searchApi;
    private static final int LOCATION_PERMISSION_REQUEST_CODE=1234;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        tomTextbar=(EditText)findViewById(R.id.searchbar);
        getLocationpermission();

    }
    private void init()
    {
       tomTextbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
              if(actionId==EditorInfo.IME_ACTION_SEARCH||actionId==EditorInfo.IME_ACTION_DONE
                      ||keyEvent.getAction()==KeyEvent.ACTION_DOWN
                      ||keyEvent.getAction()==KeyEvent.KEYCODE_ENTER)
              {
                  searchLocate();
              }
               return false;
           }
       });
    }
    private void searchLocate()
    {
        String searchString=tomTextbar.getText().toString();
    }



    @Override
    public void onMapReady(@NonNull TomtomMap tomtomMap) {
        Log.d(TAG, "onMapReady: Map is Ready here ");
        Toast.makeText(this,"Map is ready",Toast.LENGTH_SHORT);
        tmap=tomtomMap;

    if(mLocationPermission) {
        getDeviceLocation();
        tmap.setMyLocationEnabled(true);
        init();
    }
    }
    private void initMap()
    {
        Log.d(TAG, "initMap: intializing Map");
        MapFragment mpfag=(MapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragtom);
        mpfag.getAsyncMap(this);

    }

    private void getDeviceLocation()
    {
        fusedlocator=LocationServices.getFusedLocationProviderClient(this);
        try
        {
            if(mLocationPermission)
            {
                Task location=fusedlocator.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful())
                    {
                        Log.d(TAG, "onComplete: found location");
                        Location cuurentLocation=(Location)task.getResult();
                        LatLng ind = new LatLng(cuurentLocation.getLatitude(),cuurentLocation.getLongitude());
                        SimpleMarkerBalloon balloon = new SimpleMarkerBalloon("Your Current Location");
                        tmap.addMarker(new MarkerBuilder(ind).markerBalloon(balloon));
                        tmap.centerOn(CameraPosition.builder(ind).zoom(DEFAULT_ZOOM).build());
                    }
                    else
                    {
                        Log.d(TAG, "onComplete: current location is null");
                        Toast.makeText(MapActivity.this,"Unable to find current location",Toast.LENGTH_SHORT);
                    }
                    }
                });

            }
        }catch (SecurityException e)
        {
            Log.e(TAG, "getDeviceLocation: SecurityException"+e.getMessage() );
        }

    }


    private void getLocationpermission()
    {
        Log.d(TAG, "getLocationpermission: getting location permission");
        String[] permission={Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            {
                mLocationPermission=true;
                initMap();
            }
            else {
                ActivityCompat.requestPermissions(this,permission,LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else {
            ActivityCompat.requestPermissions(this,permission,LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermission=false;
        switch (requestCode)
        {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                        if(grantResults.length>0){
                            for(int i=0;i<grantResults.length;i++)
                            {
                                if(grantResults[i]!=PackageManager.PERMISSION_GRANTED)
                                {mLocationPermission=false;
                                return;
                                }
                            }
                            mLocationPermission=true;
                            //initialize our map
                            initMap();
                        }
                }
        }
    }


}
