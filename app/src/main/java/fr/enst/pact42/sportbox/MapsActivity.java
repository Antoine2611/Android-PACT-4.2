package fr.enst.pact42.sportbox;

import static fr.enst.pact42.sportbox.R.menu.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

import fr.enst.pact42.sportbox.databinding.ActivityMapsBinding;

/** I change FragmentActivity to AppCompatActivity in order to implement my search menu */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        mMap.addMarker(new MarkerOptions().position(new LatLng(48.71201566810882, 2.2149531159803737)).title("Gymnase de l'ENSTA").icon(BitmapDescriptorFactory.defaultMarker(0)));

        LatLng tetechLatLng = new LatLng(48.71256936082508, 2.200860956792873);
        mMap.addMarker(new MarkerOptions().position(tetechLatLng).title("Télécom Paris").icon(BitmapDescriptorFactory.defaultMarker(150)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tetechLatLng, mMap.getMaxZoomLevel()-5));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Random r = new Random();
        int colorHue = r.nextInt(360);
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(colorHue));

        Intent intent = new Intent(MapsActivity.this, InfoCasierActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(map_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_map_recherche:
                return true;
            case R.id.menu_map_casier:
                return true;
            case R.id.menu_map_evenement:
                return true;
            case R.id.menu_map_mon_profil:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendMessage(MenuItem item) {
        Intent intent = new Intent(MapsActivity.this, MonProfilActivity.class);
        startActivity(intent);
    }

    public void disconnect(MenuItem item) {
        //item déconnection
    }

}