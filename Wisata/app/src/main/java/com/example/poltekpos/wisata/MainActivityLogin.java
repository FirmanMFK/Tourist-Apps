package com.example.poltekpos.wisata;

/**
 * Created by Firmanz on 6/8/2016.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivityLogin extends AppCompatActivity {
    private EditText editTextNama;
    private EditText editTextPassword;
    private Context context;
    private Button buttonLogin;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = MainActivityLogin.this;
//Initializing views
        pDialog = new ProgressDialog(context);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
//Adding click listener
        CheckBox c = (CheckBox) findViewById(R.id.checkBox_pass);
        c.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else{
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    private void login() {
//Getting values from edit texts
        final String nama = editTextNama.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        pDialog.setMessage("Login Process...");
        showDialog();
//Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                AppVar.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
                        if (response.contains(AppVar.LOGIN_SUCCESS)) {
                            hideDialog();
                            gotoCourseActivity();
                        } else {
                            hideDialog();
//Displaying an error message on toast
                            Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                        hideDialog();
                        Toast.makeText(context, "The server unreachable",
                                Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//Adding parameters to request
                params.put(AppVar.KEY_NAMA, nama);
                params.put(AppVar.KEY_PASSWORD, password);
//returning parameter
                return params;
            }
        };
//Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private void gotoCourseActivity() {
        Intent intent = new Intent(context, Menu.class);
        startActivity(intent);
        finish();
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

