package com.example.fr_philippe.gestionemploye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CongeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Spinner for Conge
        Spinner staticSpinner = (Spinner) findViewById(R.id.spTypeConge);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.type_conge, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        //Spinner for date debut conge
        Spinner staticSpinnerDebut = (Spinner) findViewById(R.id.spVacationDateDebut);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapterDebutConge = ArrayAdapter.createFromResource(this, R.array.vacation_date_debut_conge, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapterDebutConge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinnerDebut.setAdapter(staticAdapterDebutConge);

        //Spinner for date debut conge
        Spinner staticSpinnerFin = (Spinner) findViewById(R.id.spVacationDateFin);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapterFinConge = ArrayAdapter.createFromResource(this, R.array.vacation_date_fin_conge, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapterFinConge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinnerFin.setAdapter(staticAdapterFinConge);

        //Spinner for date debut conge
        Spinner staticSpinnerStatus = (Spinner) findViewById(R.id.spStatus);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapterStatusConge = ArrayAdapter.createFromResource(this, R.array.status_conge, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapterStatusConge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinnerFin.setAdapter(staticAdapterStatusConge);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View mv=null;
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
