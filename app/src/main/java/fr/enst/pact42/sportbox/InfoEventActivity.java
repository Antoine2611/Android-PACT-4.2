package fr.enst.pact42.sportbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoEventActivity extends AppCompatActivity {

    private TextView typeEvents;
    private TextView localisation;
    private TextView participations;
    private TextView level;
    private Button participate;
    private EventModel event =new EventModel("Cours de Yoga", "Télécom Paris", "Débutant", 15);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_event);

        typeEvents = findViewById(R.id.type_events);
        participations=findViewById(R.id.participations);
        localisation= findViewById(R.id.localisation_event);
        level= findViewById(R.id.level);
        participate=findViewById(R.id.participate);

        participate.setEnabled(event.isAvailable());

        typeEvents.setText("Type d'événement: "+ event.getTypeEvent());
        localisation.setText("Lieu: "+ event.getLocalisation());
        level.setText("Niveau: "+ event.getLevel());
        participations.setText("Participations: "+ String.valueOf(event.getNbParticipations()) + " / " + String.valueOf(event.getLimitParticipations()));

        participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add one participant

            }
        });
    }
}