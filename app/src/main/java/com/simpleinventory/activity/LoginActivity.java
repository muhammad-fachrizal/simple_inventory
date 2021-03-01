package com.simpleinventory.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.simpleinventory.R;
import com.simpleinventory.helper.Config;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;


    String  status_login, string_id_admin_fix,
            string_admin, string_admin_hps, strD, strE, strN, strEnk_email, strEnk_password;

    EditText edt_login_email, edt_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);
        strE = sharedPreferences.getString("stringE", "");
        strD = sharedPreferences.getString("stringD", "");
        strN = sharedPreferences.getString("stringN", "");

        edt_login_email = (EditText) findViewById(R.id.edt_login_email);
        edt_login_password = (EditText) findViewById(R.id.edt_login_password);






        status_login = sharedPreferences.getString("string_id_admin","");
        if(status_login != null && !status_login.isEmpty())
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edt_login_email.getText().toString().isEmpty() &&
                        edt_login_password.getText().toString().length() > 7) {
                    tesLogin();
                    adminLogin();
                }

                else if(edt_login_email.getText().toString().isEmpty()  ||
                        edt_login_password.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Harap Isi Semua Input",
                        Toast.LENGTH_LONG).show();
                 }

                else if (edt_login_password.getText().toString().length() < 8)
                  {
                          Toast.makeText(getApplicationContext(), "Password minimal 8 karakter",
                                  Toast.LENGTH_LONG).show();
                   }
            }
        });
    }

    private void tesLogin() {
        strEnk_email = AlgoritmeRSA.Enkripsi(edt_login_email.getText().toString(), strE, strN);
        strEnk_password = AlgoritmeRSA.Enkripsi(edt_login_password.getText().toString(), strE, strN);

    }

    private void adminLogin() {
        AndroidNetworking.post( Config.host + "login.php")
                .addBodyParameter("email", strEnk_email)
                .addBodyParameter("password", strEnk_password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (!response.optString("response").equals("Email atau Password Salah")){
                                string_admin = response.optString("response");
                                string_admin_hps = string_admin.replaceAll("[^\\w\\s]","");
                                string_id_admin_fix = string_admin_hps.replaceAll("[^0-9]","");

                                editor = sharedPreferences.edit();
                                editor.putString("string_id_admin", string_id_admin_fix);
                                editor.apply();

                                Toast.makeText(getApplicationContext(), "Login Berhasil",
                                    Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Email atau Password Salah",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}