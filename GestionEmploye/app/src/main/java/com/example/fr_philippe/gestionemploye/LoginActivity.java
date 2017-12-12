package com.example.fr_philippe.gestionemploye;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Email Edit View Object
    EditText emailET;
    // Passwprd Edit View Object
    EditText pwdET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Find Error Msg Text View control by ID
        errorMsg = (TextView) findViewById(R.id.login_error);
        // Find Email Edit View control by ID
        emailET = (EditText) findViewById(R.id.etemail);
        // Find Password Edit View control by ID
        pwdET = (EditText) findViewById(R.id.etpass);
        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
    }



    /**
     * Method gets triggered when Login button is clicked
     *
     * @param view
     */

    public void loginUser(View view) {
        // Get Email Edit View Value
        String email = emailET.getText().toString();
        // Get Password Edit View Value
        String password = pwdET.getText().toString();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When Email Edit View and Password Edit View have values other than Null
        if (Utility.isNotNull(email) && Utility.isNotNull(password)) {
            // When Email entered is Valid
            if (Utility.validate(email)) {
                // Put Http parameter username with value of Email Edit View control
                params.put("email", email);
                // Put Http parameter password with value of Password Edit Value control
                params.put("password", password);
                // Invoke RESTful Web Service with Http parameters
                 final String status=null;
              //  callWS(status,params);

                callWebService(params);





                email = "";
                password = "";

            }
            // When Email is invalid
            else {
                Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
            }
        }
        // When any of the Edit View control left blank
        else {
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void invokeWS(RequestParams params) {
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.128.144:8080/SinatEnterpriseWeb/login", params, new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                prgDialog.hide();
                try {
                    // JSON Object


                    JSONObject obj = new JSONObject(String.valueOf(responseBody));

                    // When the JSON response has status boolean value assigned with true
                    if (obj.getBoolean("success")) {
                        Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                        // Navigate to Home screen
                        //  navigatetoHomeActivity();
                    }
                    if (obj.getBoolean("success")) {
                        errorMsg.setText(obj.getString("errorMsg"));
                        Toast.makeText(getApplicationContext(), "Login Failed,incorrect Email or password", Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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




    public void callWebService(RequestParams params) {
        String url = "http://192.168.128.144:8080/SinatEnterpriseWeb/login";
        // Can specify query string params directly or through RequestParams.
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                prgDialog.hide();
                Intent i=new Intent(getApplicationContext(),NewEmployedActivity.class);
                startActivity(i);
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


    public void navigateTodashboard(){
        Intent i=new Intent(this,NewEmployedActivity.class);
        startActivity(i);
    }


    public void onSubmit(View v){
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }
}
