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

import org.json.JSONObject;




import com.simpleinventory.R;
import com.simpleinventory.helper.Config;


public class EditActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    MainActivity M = new MainActivity();
    EditText edt_nama_barang, edt_stok, edt_tahun;
    String strE, strN, strEnk_nama_barang, strEnk_stok, strEnk_tahun_didapatkan;
    Button btn_simpan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);
        strE = sharedPreferences.getString("stringE", "");
        strN = sharedPreferences.getString("stringN", "");

        edt_nama_barang = findViewById(R.id.edt_nama_barang);
        edt_stok = findViewById(R.id.edt_stok);
        edt_tahun = findViewById(R.id.edt_tahun);

        btn_simpan    = findViewById(R.id.btn_simpan);

        edt_nama_barang.setText(M.nama_barang);
        edt_stok.setText(M.stok);
        edt_tahun.setText(M.tahun);


        Detail2();

    }





    private void Detail2(){
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_nama_barang.getText().toString().equals("") ||
                        edt_tahun.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Isi data dengan benar",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    tesEdit();

                    AndroidNetworking.post( Config.host + "update.php")
                            .addBodyParameter("id_barang",   M.id_benda)
                            .addBodyParameter("nama_barang",      strEnk_nama_barang)
                            .addBodyParameter("stok",         strEnk_stok)
                            .addBodyParameter("tahun_didapatkan",          strEnk_tahun_didapatkan)

                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // do anything with response
                                    Log.d("response", response.toString() );

                                    if (response.optString("response").equals("success")){
                                        Toast.makeText(getApplicationContext(), "Perubahan berhasil disimpan",
                                                Toast.LENGTH_LONG).show();
                                        finish();
                                        startActivity(new Intent(EditActivity.this, MainActivity.class));
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Gagal Mengubah",
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
        });

    }

    private void tesEdit() {
        strEnk_nama_barang = AlgoritmeRSA.Enkripsi(edt_nama_barang.getText().toString(), strE, strN);
        strEnk_stok = AlgoritmeRSA.Enkripsi(edt_stok.getText().toString(), strE, strN);
        strEnk_tahun_didapatkan = AlgoritmeRSA.Enkripsi(edt_tahun.getText().toString(), strE, strN);
    }
}
