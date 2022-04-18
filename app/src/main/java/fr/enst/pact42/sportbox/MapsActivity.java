package fr.enst.pact42.sportbox;

import static fr.enst.pact42.sportbox.R.menu.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


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

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import fr.enst.pact42.sportbox.databinding.ActivityMapsBinding;

/** I change FragmentActivity to AppCompatActivity in order to implement my search menu */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static boolean goToMarkerActivity;
    private Profil profil=null;
    private ArrayList<MarkerOptions> markerCasiers;
    private ArrayList<MarkerOptions> markerEvents;
    private JSONArray casiers;
    private JSONArray events;



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

        googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        markerCasiers=new ArrayList<MarkerOptions>();
        markerEvents=new ArrayList<MarkerOptions>();

        /*
        AsyncHttps https= new AsyncHttps(MapsActivity.this);
        AsyncHttps https2= new AsyncHttps(MapsActivity.this);
        https.execute("https://sportbox.r2.enst.fr/api/getCasiers");
        https2.execute("https://sportbox.r2.enst.fr/api/getEvents");

        try{casiers= new JSONArray(https.get().getString("data"));
            events= new JSONArray(https2.get().getString("data"));}
        catch (Exception e){e.printStackTrace();}

        for (int i=0; i<casiers.length(); i++){
            JSONObject c = null;
            try {
                c = casiers.getJSONObject(i);
                markerCasiers.add(new MarkerOptions().position(new LatLng(c.getInt("lat"), c.getInt("long"))).title(c.getString("Contenu")).icon(BitmapDescriptorFactory.defaultMarker(0)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        for (int i=0; i<events.length(); i++){
            JSONObject e =null;
            try {
                e=events.getJSONObject(i);
                markerEvents.add(new MarkerOptions().position(new LatLng(e.getInt("lat"), e.getInt("long"))).title(e.getString("nom")).icon(BitmapDescriptorFactory.defaultMarker(150)));
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }

        markerCasiers();

        */


        markerCasiers.add(new MarkerOptions().position(new LatLng(48.71256936082508, 2.200860956792873)).title("Télécom Paris").icon(BitmapDescriptorFactory.defaultMarker(0)));
        markerEvents.add(new MarkerOptions().position(new LatLng(48.71201566810882, 2.2149531159803737)).title("Gymnase de l'ENSTA").icon(BitmapDescriptorFactory.defaultMarker(0)));
        markerEvents.add(new MarkerOptions().position(new LatLng(48.71523056366527, 2.211139913619598)).title("Coupe de l'X").icon(BitmapDescriptorFactory.defaultMarker(150)));

        markerCasiers();


        mMap.addMarker(new MarkerOptions().position(new LatLng(48.71201566810882, 2.2149531159803737)).title("Gymnase de l'ENSTA").icon(BitmapDescriptorFactory.defaultMarker(0)));

        LatLng tetechLatLng = new LatLng(48.71256936082508, 2.200860956792873);
        mMap.addMarker(new MarkerOptions().position(tetechLatLng).title("Télécom Paris").icon(BitmapDescriptorFactory.defaultMarker(0)));

        mMap.addMarker(new MarkerOptions().position(new LatLng(48.71523056366527, 2.211139913619598)).title("Coupe de l'X").icon(BitmapDescriptorFactory.defaultMarker(150)));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.71256936082508, 2.200860956792873), mMap.getMaxZoomLevel()-5));



    }

    public static class MarkerDialogFragment extends DialogFragment {

        Intent markerActivity;
        String marker_desc;

        public MarkerDialogFragment(Intent markerActivity, String marker_desc) {
            this.markerActivity = markerActivity;
            this.marker_desc = marker_desc;
        }
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            return new AlertDialog.Builder(requireContext())
                    .setMessage(marker_desc)
                    .setPositiveButton(getString(R.string.subscribe), (dialog, which) -> {
                        startActivity(markerActivity);
                        dismiss();
                    })
                    .create();
        }

        public static String TAG = "MarkerDialog";

    }

    public boolean onMarkerClick(Marker marker) {

        System.out.println(marker.getId());

        if(marker.getId().equals("m0")) {

            new MarkerDialogFragment(new Intent(MapsActivity.this, InfoCasierActivity.class), getString(R.string.gym_ensta_desc)).show(
                    getSupportFragmentManager(), MarkerDialogFragment.TAG);

        }

        if(marker.getId().equals("m1")) {

            new MarkerDialogFragment(new Intent(MapsActivity.this, InfoCasierActivity.class), getString(R.string.telecom_desc)).show(
                    getSupportFragmentManager(), MarkerDialogFragment.TAG);

        }

        if(marker.getId().equals("m2")) {

            new MarkerDialogFragment(new Intent(MapsActivity.this, InfoEventActivity.class), getString(R.string.coupe_x_desc)).show(
                    getSupportFragmentManager(), MarkerDialogFragment.TAG);

        }


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
            case R.id.menu_map_marker_events:
                markerEvents();
                return true;
            case R.id.menu_map_marker_casiers:
                markerCasiers();
                return true;
            case R.id.menu_map_recherche:

                /* code test pour récupérer les casiers à partir de requête https*/
                AsyncHttps https2= new AsyncHttps(MapsActivity.this);
                https2.execute("https://sportbox.r2.enst.fr/api/getCasiers");
                String resultRequest;
                JSONArray resultRequest1=null;
                String resultRequest2="Nope";
                try{
                    resultRequest=https2.get().getString("data");
                    resultRequest.replace("\\","");
                    Log.i("info0",resultRequest);
                    JSONParser parser = new JSONParser();
                    Object object = (Object) parser.parse(resultRequest);
                    resultRequest1 = (JSONArray) object;
                    resultRequest2= resultRequest1.getJSONObject(0).getString("Contenu");
                }
                catch (Exception e){
                }
                if (resultRequest1==null){
                    Log.i("info1","mauvaise conversion");}
                Log.i("info2", resultRequest2);

                return true;
            case R.id.menu_map_casier:
                return true;
            case R.id.menu_map_evenement:
                return true;
            case R.id.menu_map_connexion:
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

    public void connect(MenuItem item) {
        //item de connexion/inscription ou déconnexion
        if (profil==null){
            Intent intent = new Intent(MapsActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            profil=null;
        }

    }

    public void changeItemConnect(){
        MenuItem item= findViewById(R.id.menu_map_connexion);
        if(profil==null){
            item.setTitle("Connexion");
        }else{
            item.setTitle("Deconnexion");
        }
    }

    public void markerCasiers(){
        mMap.clear();
        for (MarkerOptions marker: markerCasiers){
            mMap.addMarker(marker);
        }
    }
    public void markerEvents(){
        mMap.clear();

        for (MarkerOptions marker: markerEvents){
            mMap.addMarker(marker);
        }
    }


}