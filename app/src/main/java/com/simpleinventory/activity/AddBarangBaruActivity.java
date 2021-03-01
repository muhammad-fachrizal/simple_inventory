package com.simpleinventory.activity;
import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class AddBarangBaruActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    EditText edt_nama_barang_baru, edt_tahun_barang_baru;
    Button btn_simpan_barang_baru;
    String strE, strN, strEnk_nama_barang, strEnk_tahun_didapatkan, strEnk_stok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang_baru);

        sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);
        strE = sharedPreferences.getString("stringE", "");
        strN = sharedPreferences.getString("stringN", "");

        edt_nama_barang_baru = (EditText) findViewById(R.id.edt_nama_barang_baru);
        edt_tahun_barang_baru = (EditText) findViewById(R.id.edt_tahun_barang_baru);


        btn_simpan_barang_baru = (Button) findViewById(R.id.btn_simpan_barang_baru);

        btn_simpan_barang_baru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_nama_barang_baru.getText().toString().equals("") || edt_tahun_barang_baru.getText().toString().equals("")
                        || btn_simpan_barang_baru.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Harap Isi Semua",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    tesBarangBaru();
                    simpanBarangBaru();
                }
            }
        });

    }

    private void tesBarangBaru() {
        strEnk_nama_barang = AlgoritmeRSA.Enkripsi(edt_nama_barang_baru.getText().toString(), strE, strN);
        strEnk_stok = AlgoritmeRSA.Enkripsi("0", strE, strN);
        strEnk_tahun_didapatkan = AlgoritmeRSA.Enkripsi(edt_tahun_barang_baru.getText().toString(), strE, strN);
    }

    private void simpanBarangBaru(){
        AndroidNetworking.post( Config.host + "add_barang_baru.php")
                .addBodyParameter("nama_barang", strEnk_nama_barang)
                .addBodyParameter("stok", strEnk_stok)
                .addBodyParameter("tahun_didapatkan", strEnk_tahun_didapatkan)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_barang_baru success")){
                            startActivity(new Intent(AddBarangBaruActivity.this, MainActivity.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal disimpan",
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
