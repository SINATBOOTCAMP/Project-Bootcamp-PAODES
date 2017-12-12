package com.example.fr_philippe.gestionemploye;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class CongeActivity extends AppCompatActivity {
    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
Spinner spinnerType;
    EditText codeEmpl;
    EditText dateFinF;
    EditText dateDebutF;
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
        spinnerType = (Spinner) findViewById(R.id.spTypeConge);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.type_conge, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerType.setAdapter(staticAdapter);
/*
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
*/

        codeEmpl=(EditText) findViewById(R.id.etCodeEmpConge);
        dateFinF=(EditText) findViewById(R.id.etDateFinConge);
       dateDebutF=(EditText) findViewById(R.id.etDateDebutConge);
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

    public void saveConge(View view) {
        // Get Email Edit View Value
        String code = codeEmpl.getText().toString();
        // Get Password Edit View Value
        String dateD = dateDebutF.getText().toString();
        String dateF = dateFinF.getText().toString();
        String type = (String) spinnerType.getSelectedItem();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When Email Edit View and Password Edit View have values other than Null
        if (Utility.isNotNull(code) && Utility.isNotNull(dateD)&& Utility.isNotNull(dateF)) {
            // When Email entered is Valid

                // Put Http parameter username with value of Email Edit View control
                params.put("idEmpl", code);
                // Put Http parameter password with value of Password Edit Value control
           params.put("dateDebut", dateD);
            params.put("dateFin", dateF);
            params.put("type", type);
                // Invoke RESTful Web Service with Http parameters
                final String status=null;
                //  callWS(status,params);

                callWebService(params);
 }

        // When any of the Edit View control left blank
        else {
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

    }


    public void callWebService(RequestParams params) {
        String url = "http://192.168.128.144:8080/SinatEnterpriseWeb/saveConge";
        // Can specify query string params directly or through RequestParams.
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                prgDialog.hide();

               /* try {
                    String response=new String(responseBody.toString());
                    JSONObject object=new JSONObject(response);
                    if(object.getBoolean("success")){

                        Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Hide Progress Dialog
                prgDialog.hide();
                // When Http response code is '404'
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
