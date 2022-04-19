package fr.enst.pact42.sportbox;

import static fr.enst.pact42.sportbox.R.menu.activity_info_casier_menu;
import static fr.enst.pact42.sportbox.R.menu.map_menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

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

        /*
        casier = new CasierModel (1,"Ballon de foot", "Correct", "Telecom Paris",43, 42, true);
        casiers = new ArrayList<CasierModel>();
        casiers.add(casier);
        casiers.add(new CasierModel (2,"Ballon de Basket", "Mauvais", "Télécom Paris",43,2, true));
        */

        casiers=new ArrayList<CasierModel>();
        Intent intent = getIntent();
        int length =intent.getIntExtra("length",-1);
        for (int i=0; i<=length; i++){
            try {
                JSONObject json = new JSONObject (intent.getStringExtra(String.valueOf(i)));
                casiers.add(new CasierModel (json.getInt("idCasier"),json.getString("Contenu"), "Bon état", "Gymnase de l'X",json.getInt("lat"), json.getInt("long"), json.get("Ouvreur")==null));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        casier=casiers.get(0);

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
            // The user just clicked on unlock
            AsyncHttps http= new AsyncHttps(InfoCasierActivity.this);
            http.execute("http://192.168.1.120/openCasier?token=token&idCasier=1");
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
        options.add("Choix du matérial: " + c.getTypeMaterial());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(activity_info_casier_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_sharp:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}