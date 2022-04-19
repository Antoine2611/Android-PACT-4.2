package fr.enst.pact42.sportbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InfoEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private TextView typeEvents;
    private TextView localisation;
    private TextView participations;
    private TextView level;
    private Button participate;
    private ArrayList<EventModel> events;
    private EventModel event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_event);
        /*
        event=new EventModel("Cours de Yoga", "Télécom Paris", "Débutant", 0,15);
        events= new ArrayList<EventModel>();
        events.add(event);
        events.add(new EventModel("Match de foot", "Télécom Paris", "Professionnel", 3, 20));
        */

        events=new ArrayList<EventModel>();
        Intent intent = getIntent();
        int length =intent.getIntExtra("length",-1);
        for (int i=0; i<=length; i++){
            try {
                JSONObject json = new JSONObject (intent.getStringExtra(String.valueOf(i)));
                events.add(new EventModel(json.getString("nom"), "Gymnase de l'X", json.getString("Difficulte"), 0,json.getInt("maxJoin")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        event=events.get(0);

        spinner=findViewById(R.id.spinner2);
        typeEvents = findViewById(R.id.type_events);
        participations=findViewById(R.id.participations);
        localisation= findViewById(R.id.localisation_event);
        level= findViewById(R.id.level);
        participate=findViewById(R.id.participate);
        updateActivity();


        participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add one participant

            }
        });

        ArrayList<String> options= new ArrayList<String>();

        for (EventModel e: events){
            options.add("Choix de l'évenement: " + e.getTypeEvent());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void updateActivity(){
        participate.setEnabled(event.isAvailable());

        typeEvents.setText("Type d'événement: "+ event.getTypeEvent());
        localisation.setText("Lieu: "+ event.getLocalisation());
        level.setText("Niveau: "+ event.getLevel());
        participations.setText("Participations: "+ String.valueOf(event.getNbParticipations()) + " / " + String.valueOf(event.getLimitParticipations()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        event=events.get(position);
        updateActivity();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}