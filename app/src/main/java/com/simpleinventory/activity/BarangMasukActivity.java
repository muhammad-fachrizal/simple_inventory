package com.simpleinventory.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.simpleinventory.R;
import com.simpleinventory.helper.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class BarangMasukActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

        ListView list_benda;
        Button btn_form_barang_masuk;
        SwipeRefreshLayout swipe_refresh;
        ArrayList<HashMap<String, String>> arrayBenda = new ArrayList<HashMap<String, String>>();

        String strD, strN, strDek_nama_barang,
                strDek_nama_pengembali, strDek_jumlah_masuk, strDek_tanggal_masuk;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_barang_masuk);

            sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);
            strD = sharedPreferences.getString("stringD", "");
            strN = sharedPreferences.getString("stringN", "");

            list_benda              = findViewById(R.id.list_benda);
            swipe_refresh           = findViewById(R.id.swipe_refresh);
            btn_form_barang_masuk   = findViewById(R.id.btn_form_barang_masuk);

            selectMysql();

            swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    arrayBenda.clear();
                    selectMysql();

                }
            });

            btn_form_barang_masuk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(BarangMasukActivity.this, FormBarangMasukActivity.class));

                }
            });



        }


        private void selectMysql () {
            AndroidNetworking.get(Config.host + "list_detail_barang_masuk.php")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("result");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject responses = jsonArray.getJSONObject(i);
                                    HashMap<String, String> map = new HashMap<String, String>();
                                    strDek_nama_pengembali = AlgoritmeRSA.Dekripsi(responses.optString("nama_pengembali"), strD, strN);
                                    strDek_nama_barang = AlgoritmeRSA.Dekripsi(responses.optString("nama_barang"), strD, strN);
                                    strDek_jumlah_masuk = AlgoritmeRSA.Dekripsi(responses.optString("jumlah_masuk"), strD, strN);
                                    strDek_tanggal_masuk = AlgoritmeRSA.Dekripsi(responses.optString("tanggal_masuk"), strD, strN);


                                    map.put("id_masuk", responses.optString("id_masuk"));
                                    map.put("id_benda", responses.optString("id_barang"));
                                    map.put("nama_pengembali", "Pengembali = " + strDek_nama_pengembali);
                                    map.put("nama_barang", strDek_nama_barang);
                                    map.put("jumlah_masuk", "Jumlah = " + strDek_jumlah_masuk);
                                    map.put("tanggal_masuk", strDek_tanggal_masuk);

                                    arrayBenda.add(map);

                                }
                                Adapter();

                            } catch (JSONException e) {

                            }


                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
        }


        private void Adapter () {
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayBenda, R.layout.list_benda_masuk,
                    new String[]{
                            "id_masuk", "tanggal_masuk",
                            "nama_barang", "jumlah_masuk", "nama_pengembali"

                    },
                    new int[]{

                            R.id.txt_kiri1, R.id.txt_kanan1,
                             R.id.txt_kiri2, R.id.txt_kanan2, R.id.txt_kiri3
                    }
            );

            list_benda.setAdapter(simpleAdapter);


            swipe_refresh.setRefreshing(false);
        }


        }




