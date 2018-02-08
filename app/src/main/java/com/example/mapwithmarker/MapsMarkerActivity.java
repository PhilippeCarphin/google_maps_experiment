package com.example.mapwithmarker;
import java.lang.Math;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        // This seems to make a link with the class the xml file that controls layout.
        // It looks like said view will render the map.
        // To me this seems like we're setting the parent of this class.
        setContentView(R.layout.activity_maps);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // This is meant to create a list of LatLng objects that would maybe be passed to "me"
        LatLng sydney = new LatLng(-33.852, 151.211);
        LatLng montreal = new LatLng(45.5017, -73.5673);
        double dist = 0.3;
        int N_POINTS = 30;
        MarkerOptions markerOptions[] = new MarkerOptions[N_POINTS];
        for(int i = 0; i < N_POINTS; ++i){
            double lat = montreal.latitude + Math.cos(i * (2 * 3.1415 / N_POINTS)) * dist;
            double lng = montreal.longitude + Math.sin(i * (2 * 3.1415 / N_POINTS)) * dist;
            LatLng coord = new LatLng(lat,lng);
            markerOptions[i] = new MarkerOptions()
                    .position(coord)
                    .title("Wifi Hotspot " + String.valueOf(i));
        }

        // "I" will iterate through this list and put markers on the googleMap for each object in
        // list that I would have received from some other module.
        for(MarkerOptions mo : markerOptions){
            googleMap.addMarker(mo);
        }

        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));

        googleMap.addMarker(new MarkerOptions().position(montreal)
                .title("Greatest city on Earth"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(montreal));
    }
}
