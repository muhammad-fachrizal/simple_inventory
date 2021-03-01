package com.simpleinventory.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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


public class BarangKeluarActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

        ListView list_benda;
        Button btn_barang_masuk, btn_barang_keluar, btn_form_barang_keluar;
        SwipeRefreshLayout swipe_refresh;
        ArrayList<HashMap<String, String>> arrayBenda = new ArrayList<HashMap<String, String>>();

        public static String string_db_id_keluar, string_db_nama_peminjam;

        Cursor cursor;

        String  strD, strN, strDek_nama_peminjam, strDek_status, strDek_nama_barang, strDek_jumlah_keluar,
                strDek_tanggal_keluar;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_barang_keluar);

            sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);
            strD = sharedPreferences.getString("stringD", "");
            strN = sharedPreferences.getString("stringN", "");

            list_benda = findViewById(R.id.list_benda);
            swipe_refresh = findViewById(R.id.swipe_refresh);
            btn_barang_masuk = findViewById(R.id.btn_barang_masuk);
            btn_barang_keluar = findViewById(R.id.btn_barang_keluar);
            btn_form_barang_keluar = findViewById(R.id.btn_form_barang_keluar);

            selectMysql();

            swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    arrayBenda.clear();
                    selectMysql();

                }
            });



            btn_form_barang_keluar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(BarangKeluarActivity.this, FormBarangKeluarActivity.class));

                }
            });

        }

        private void selectMysql () {
            AndroidNetworking.get(Config.host + "list_detail_barang_keluar.php")
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
                                    strDek_nama_peminjam = AlgoritmeRSA.Dekripsi(responses.optString("nama_peminjam"), strD, strN);
                                    strDek_status = AlgoritmeRSA.Dekripsi(responses.optString("status"), strD, strN);
                                    strDek_nama_barang = AlgoritmeRSA.Dekripsi(responses.optString("nama_barang"), strD, strN);
                                    strDek_jumlah_keluar = AlgoritmeRSA.Dekripsi(responses.optString("jumlah_keluar"), strD, strN);
                                    strDek_tanggal_keluar = AlgoritmeRSA.Dekripsi(responses.optString("tanggal_keluar"), strD, strN);


                                    map.put("id_keluar", responses.optString("id_keluar"));
                                    map.put("nama_peminjam", "Peminjam = " + strDek_nama_peminjam);
                                    map.put("status", strDek_status);
                                    map.put("nama_barang", strDek_nama_barang);
                                    map.put("jumlah_keluar", strDek_jumlah_keluar);

                                    map.put("tanggal_keluar", strDek_tanggal_keluar);

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
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayBenda, R.layout.list_benda_keluar,
                    new String[]{
                            "id_keluar", "nama_peminjam", "status", "nama_barang", "jumlah_keluar",
                            "tanggal_keluar"

                    },
                    new int[]{
                            R.id.txt_kiri1, R.id.txt_kiri2, R.id.txt_kanan2, R.id.txt_kiri3, R.id.txt_kanan3,
                           R.id.txt_kiri4

                    }
            );

            list_benda.setAdapter(simpleAdapter);
            list_benda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    string_db_id_keluar = ((TextView) view.findViewById(R.id.txt_kiri1)).getText().toString();
                    string_db_nama_peminjam = ((TextView) view.findViewById(R.id.txt_kiri2)).getText().toString();
                }
            });

            swipe_refresh.setRefreshing(false);
        }





    }

