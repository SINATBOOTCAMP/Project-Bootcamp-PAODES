package com.example.fr_philippe.gestionemploye;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class PayrollActivity extends AppCompatActivity {
    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    Spinner spinnerType;
    EditText codeEmpl;
    EditText dateP;
    EditText montant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        spinnerType = (Spinner) findViewById(R.id.spTypePaiement);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.payroll_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerType.setAdapter(staticAdapter);

codeEmpl=(EditText) findViewById(R.id.etCodeEmplPayroll);
montant=(EditText) findViewById(R.id.etPayrollMontant);
dateP=(EditText) findViewById(R.id.etDatePayroll);
    }


    public void savePayroll(View view) {
        // Get Email Edit View Value
        String code = codeEmpl.getText().toString();
        // Get Password Edit View Value
        String datePayroll = dateP.getText().toString();
        double somme =Double.parseDouble(montant.getText().toString()) ;
        String type = (String) spinnerType.getSelectedItem();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When Email Edit View and Password Edit View have values other than Null
        if (Utility.isNotNull(code) && Utility.isNotNull(datePayroll)) {
            // When Email entered is Valid

            // Put Http parameter username with value of Email Edit View control
            params.put("idEmpl", code);
            // Put Http parameter password with value of Password Edit Value control
            params.put("date", datePayroll);
            params.put("montant", somme);
            params.put("type", type);
            // Invoke RESTful Web Service with Http parameters
            final String status=null;
         callWebService(params);

            callWebService(params);
        }

        // When any of the Edit View control left blank
        else {
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

    }


    public void callWebService(RequestParams params) {
        String url = "http://192.168.128.144:8080/SinatEnterpriseWeb/savePayroll";
        // Can specify query string params directly or through RequestParams.
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                prgDialog.hide();
                Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
