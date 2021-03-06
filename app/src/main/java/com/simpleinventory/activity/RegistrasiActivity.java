package com.simpleinventory.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class RegistrasiActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    EditText edt_reg_nama_admin, edt_reg_email, edt_reg_password;
    Button btn_daftar, btn_pindah_login;
    String strE, strN, strEnk_nama_admin, strEnk_email, strEnk_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);

        edt_reg_nama_admin = (EditText) findViewById(R.id.edt_reg_nama_admin);
        edt_reg_email = (EditText) findViewById(R.id.edt_reg_email);
        edt_reg_password = (EditText) findViewById(R.id.edt_reg_password);

        btn_daftar = (Button) findViewById(R.id.btn_daftar);
        btn_pindah_login = (Button) findViewById(R.id.btn_pindah_login);

        strE = sharedPreferences.getString("stringE", "");
        strN = sharedPreferences.getString("stringN", "");

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edt_reg_nama_admin.getText().toString().isEmpty() &&
                        !edt_reg_email.getText().toString().isEmpty() &&
                        edt_reg_password.getText().toString().length() > 7) {
                    registrasi();
                } else {
                    Toast.makeText(getApplicationContext(), "Harap Isi Semua Input",
                            Toast.LENGTH_LONG).show();
                }

                if (edt_reg_password.getText().toString().length() < 8)
                {
                    Toast.makeText(getApplicationContext(), "Password minimal 8 karakter",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_pindah_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrasiActivity.this, LoginActivity.class));
            }
        });

    }

    private void tesRegistrasi() {
        strEnk_nama_admin = AlgoritmeRSA.Enkripsi(edt_reg_nama_admin.getText().toString(), strE, strN);
        strEnk_email = AlgoritmeRSA.Enkripsi(edt_reg_email.getText().toString(), strE, strN);
        strEnk_password = AlgoritmeRSA.Enkripsi(edt_reg_password.getText().toString(), strE, strN);
    }


    private void registrasi() {
        strEnk_nama_admin = AlgoritmeRSA.Enkripsi(edt_reg_nama_admin.getText().toString(), strE, strN);
        strEnk_email = AlgoritmeRSA.Enkripsi(edt_reg_email.getText().toString(), strE, strN);
        strEnk_password = AlgoritmeRSA.Enkripsi(edt_reg_password.getText().toString(), strE, strN);
        AndroidNetworking.post( Config.host + "registrasi.php")
                .addBodyParameter("nama_admin", strEnk_nama_admin)
                .addBodyParameter("email", strEnk_email)
                .addBodyParameter("password", strEnk_password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("Registrasi Berhasil")){
                                Toast.makeText(getApplicationContext(), "Registrasi Berhasil",
                                    Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegistrasiActivity.this, LoginActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registrasi Gagal",
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