package fr.enst.pact42.sportbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


    }
}