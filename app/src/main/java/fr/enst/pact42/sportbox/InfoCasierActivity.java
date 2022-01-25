package fr.enst.pact42.sportbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoCasierActivity extends AppCompatActivity {

    private TextView typeMaterial;
    private TextView condition;
    private TextView localisation;
    private Button unlock;
    private Button report;
    private CasierModel casier = new CasierModel ("Ballon de foot", "Correct", "Télécom Paris");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_casier);
    typeMaterial = findViewById(R.id.type_material);
    condition=findViewById(R.id.condition);
    localisation= findViewById(R.id.localisation);
    unlock=findViewById(R.id.unlock);
    report=findViewById(R.id.report);
    unlock.setEnabled(casier.getAccess());
    report.setEnabled(true);
    typeMaterial.setText("Type de matériel:   "+ casier.getTypeMaterial());
    condition.setText("Etat:   "+ casier.getCondition());
    localisation.setText("Lieu:   "+ casier.getLocalisation());

    unlock.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // The user just clicked on unlock button
        }
    });

    report.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // The user just clicked on report button
        }
    });


    }
}