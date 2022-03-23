package fr.enst.pact42.sportbox;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InfoCasierActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private TextView typeMaterial;
    private TextView condition;
    private TextView localisation;
    private Button unlock;
    private Button report;
    private ArrayList<CasierModel> casiers;
    private CasierModel casier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_casier);
        casier = new CasierModel ("Ballon de foot", "Correct", "Télécom Paris", true);
        casiers = new ArrayList<CasierModel>();
        casiers.add(casier);
        casiers.add(new CasierModel ("Ballon de Basket", "Mauvais", "Télécom Paris", true));

    typeMaterial = findViewById(R.id.type_material);
    condition=findViewById(R.id.condition);
    localisation= findViewById(R.id.localisation);
    unlock=findViewById(R.id.unlock);
    report=findViewById(R.id.report);
    spinner=findViewById(R.id.spinner);
    updateActivity();

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

    ArrayList<String> options= new ArrayList<String>();

    for (CasierModel c: casiers){
        options.add(c.getTypeMaterial());
    }
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,options);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);
    }

    public void updateActivity(){
        unlock.setEnabled(casier.getAccess());
        report.setEnabled(true);
        typeMaterial.setText("Type de matériel: "+ casier.getTypeMaterial());
        condition.setText("Etat: "+ casier.getCondition());
        localisation.setText("Lieu: "+ casier.getLocalisation());
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        casier=casiers.get(position);
        updateActivity();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}