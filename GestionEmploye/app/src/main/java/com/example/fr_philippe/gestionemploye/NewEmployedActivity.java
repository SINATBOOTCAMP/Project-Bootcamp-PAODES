package com.example.fr_philippe.gestionemploye;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class NewEmployedActivity extends AppCompatActivity {
    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Email Edit View Object
    TextInputLayout email;
    // Passwprd Edit View Object
    TextInputLayout idEmpl;
    TextInputLayout firstnameEmpl;
    TextInputLayout lastnameEmpl;
    TextInputLayout fonctionEmpl;
    TextInputLayout serviceEmpl;
    TextInputLayout dateBirthEmpl;
    TextInputLayout phoneEmpl;
    TextInputLayout streetEmpl;
    TextInputLayout regionEmpl;
    TextInputLayout numberEmpl;
    TextInputLayout communeEmpl;
    Spinner spinnerSex;
    Spinner spinnerMaritalStatus;
    Spinner spinnerType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employed);
/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        */
        // Find Error Msg Text View control by ID
      //  errorMsg =(TextView) findViewById(R.id.message_error);
        // Find Email Edit View control by ID
        email =(TextInputLayout) findViewById(R.id.email_input_text_input_layout);
        // Find Password Edit View control by ID
        firstnameEmpl=(TextInputLayout) findViewById(R.id.username_text_input_layout);
        lastnameEmpl=(TextInputLayout) findViewById(R.id.userlastName_text_input_layout);
        idEmpl=(TextInputLayout) findViewById(R.id.etNumSocial_text_input_layout);
        dateBirthEmpl=(TextInputLayout) findViewById(R.id.date_input_text_input_layout);
        phoneEmpl=(TextInputLayout) findViewById(R.id.etTell_text_input_layout);
        serviceEmpl=(TextInputLayout) findViewById(R.id.etService_input_text_input_layout);
        fonctionEmpl=(TextInputLayout) findViewById(R.id.etFonction_text_input_layout);
        communeEmpl=(TextInputLayout) findViewById(R.id.etCommune_text_input_layout);
        numberEmpl=(TextInputLayout) findViewById(R.id.etNumero_input_text_input_layout);


        // Instantiate Progress Dialog object
        prgDialog =new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
        //Spinner for Sexe
        spinnerSex= (Spinner) findViewById(R.id.spSex);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.sex_employed, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerSex.setAdapter(staticAdapter);

        // Spinner for status
        spinnerMaritalStatus = (Spinner) findViewById(R.id.spStatus);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapterStatus = ArrayAdapter.createFromResource(this, R.array.status_employed, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerMaritalStatus.setAdapter(staticAdapterStatus);

        //Spinner for Type contrat
        // Spinner for status
        spinnerType = (Spinner) findViewById(R.id.spTypeEmploye);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapterContrat = ArrayAdapter.createFromResource(this, R.array.type_employed, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapterContrat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerType.setAdapter(staticAdapterContrat);



    }









    public void saveEmploye(View view) {
        try {
            // Get Email Edit View Value
            String emails = email.getEditText().getText().toString();
            // Get Password Edit View Value
            String ids = idEmpl.getEditText().getText().toString();
            String firstnames = firstnameEmpl.getEditText().getText().toString();
            String lastnames = lastnameEmpl.getEditText().getText().toString();
            String fonctions = fonctionEmpl.getEditText().getText().toString();
            String services = serviceEmpl.getEditText().getText().toString();
            String dateBirths = dateBirthEmpl.getEditText().getText().toString();
            String phones = phoneEmpl.getEditText().getText().toString();
            String streets = streetEmpl.getEditText().getText().toString();
            String regions = regionEmpl.getEditText().getText().toString();
            String numbers = numberEmpl.getEditText().getText().toString();
            String communes = communeEmpl.getEditText().getText().toString();
            String sex = (String) spinnerSex.getSelectedItem();
            String maritalStatus = (String) spinnerMaritalStatus.getSelectedItem();
            String type = (String) spinnerType.getSelectedItem();

            RequestParams params = new RequestParams();

            // String streets = streetEmpl.getText().;


            // When Email Edit View and Password Edit View have values other than Null
            if (Utility.isNotNull(emails) && Utility.isNotNull(ids)
                    && Utility.isNotNull(firstnames) && Utility.isNotNull(lastnames)
                    && Utility.isNotNull(fonctions) && Utility.isNotNull(services)
                    && Utility.isNotNull(dateBirths) && Utility.isNotNull(phones)
                    && Utility.isNotNull(streets) && Utility.isNotNull(regions)
                    && Utility.isNotNull(numbers) && Utility.isNotNull(communes)
                    && Utility.isNotNull(sex) && Utility.isNotNull(maritalStatus)
                    && Utility.isNotNull(type)) {
                // When Email entered is Valid
                if (Utility.validate(emails)) {
                    // Put Http parameter username with value of Email Edit View control
                    params.put("email", emails);
                    // Put Http parameter password with value of Password Edit Value control
                    params.put("phone", phones);
                    params.put("fonction", fonctions);
                    params.put("service", services);
                    params.put("nom", firstnames);
                    params.put("prenom", lastnames);
                    params.put("nif_cin", ids);
                    params.put("dateNaiss", dateBirths);
                    params.put("rue", streets);
                    params.put("numero", numbers);
                    params.put("commune", communes);
                    params.put("departement", regions);
                    params.put("statut", maritalStatus);
                    params.put("sexe", sex);
                    params.put("type", type);

                    // Invoke RESTful Web Service with Http parameters
                    callWebservice(params);

                }else {
                    Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
                }
            }// When any of the Edit View control left blank
            else {
                Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
            }


        }catch(Exception e){
            System.out.println(e.getMessage());
            Toast.makeText(getApplicationContext(), "error"+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }











    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void callWebservice (RequestParams params){
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.128.144:8080/SinatEnterpriseWeb/saveEmploye", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                prgDialog.show();
                //     try {
                // JSON Object
                //  JSONObject obj = new JSONObject(response);
                // When the JSON response has status boolean value assigned with true
                //     if(obj.getBoolean("status")){
                // Navigate to Home screen

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
            // client.get("http://192.168.43.17:9999/useraccount/login/dologin",params ,new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'

        });
    }










    public void logout(View view){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.128.144:8080/SinatEnterpriseWeb/logout",  new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                prgDialog.show();
                //     try {
                // JSON Object
                //  JSONObject obj = new JSONObject(response);
                // When the JSON response has status boolean value assigned with true
                //     if(obj.getBoolean("status")){
                // Navigate to Home screen
                Toast.makeText(getApplicationContext(), "Logout succesfully", Toast.LENGTH_LONG).show();
                prgDialog.hide();
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
            // client.get("http://192.168.43.17:9999/useraccount/login/dologin",params ,new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'

        });

    }






















    public void onClick (View v)
    {
       // Intent i = new Intent(this, NewEmploye.class);
     //   startActivity(i);
    }






        /*
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });*/



    /*private void setupFloatingLabelError() {
        final TextInputLayout floatingUsernameLabel = (TextInputLayout) findViewById(R.id.username_text_input_layout);
        floatingUsernameLabel.getEditText().addTextChangedListener(new TextWatcher() {
            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() > 0 && text.length() <= 4) {
                    floatingUsernameLabel.setError(getString(R.string.username_required));
                    floatingUsernameLabel.setErrorEnabled(true);
                } else {
                    floatingUsernameLabel.setErrorEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }*/




}
