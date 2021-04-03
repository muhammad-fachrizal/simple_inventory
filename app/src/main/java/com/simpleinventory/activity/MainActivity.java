package com.simpleinventory.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.simpleinventory.helper.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


import com.simpleinventory.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    EditText edt_search, edt_kunciRSA;
    ListView list_utama;
    Button btn_barang_masuk, btn_barang_keluar,btn_search;
    SwipeRefreshLayout swipe_refresh;
    ArrayList<HashMap<String, String>> arrayutama = new ArrayList<HashMap<String, String>>();


    public static String id_benda, nama_barang, stok, tahun, publicStrD, publicStrN;

    String status, jumlah, keterangan, tanggal, tanggal2,
            string_id_admin, string_nama_admin, string_kunciRSA, strE, strD, strN, strSearch,
            strDek_nama_barang, strDek_stok, strDek_tahun_didapatkan, strDek_nama_barang_search,
            strDek_stok_search, strDek_tahun_didapatkan_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);


        status = ""; jumlah = ""; keterangan = "";
        id_benda = ""; nama_barang = ""; stok = ""; tahun = ""; tanggal = ""; tanggal2 = "";

        arrayutama.clear();

        string_id_admin = sharedPreferences.getString("string_id_admin","");

        strE = sharedPreferences.getString("stringE", "");
        strD = sharedPreferences.getString("stringD", "");
        strN = sharedPreferences.getString("stringN", "");
        publicStrD = sharedPreferences.getString("stringD", "");
        publicStrN = sharedPreferences.getString("stringN", "");

        string_kunciRSA = "p = " + sharedPreferences.getString("string_pRandom", "") + " " +
                "q = " + sharedPreferences.getString("string_qRandom", "") + " " +
                "n = " + sharedPreferences.getString("stringN","") + " " +
                "m = " + sharedPreferences.getString("stringM","") + " " +
                "e = " + sharedPreferences.getString("stringE","") + " " +
                "d = " + sharedPreferences.getString("stringD","");



        list_utama           = findViewById(R.id.list_utama);
        swipe_refresh        = findViewById(R.id.swipe_refresh);
        btn_barang_masuk       = findViewById(R.id.btn_barang_masuk);
        btn_barang_keluar      = findViewById(R.id.btn_barang_keluar);
        edt_search           = findViewById(R.id.edt_search);
        edt_kunciRSA         = findViewById(R.id.edt_kunciRSA);
        btn_search           = findViewById(R.id.btn_search);


        selectMysql();

        edt_kunciRSA.setText(string_kunciRSA);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddBarangBaruActivity.class));
            }
        });

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                arrayutama.clear();
                selectMysql();

            }
        });



        btn_barang_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BarangMasukActivity.class));

            }
        });

        btn_barang_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BarangKeluarActivity.class));

            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strSearch = AlgoritmeRSA.Enkripsi(edt_search.getText().toString(), strE, strN);
                hasilSearch();

            }
        });


    }


    private void selectMysql() {
        AndroidNetworking.get(Config.host + "list.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray jsonArray = response.getJSONArray("result");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject responses    = jsonArray.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<String, String>();
                                strDek_nama_barang = AlgoritmeRSA.Dekripsi(responses.optString("nama_barang"), strD, strN);
                                strDek_stok = AlgoritmeRSA.Dekripsi(responses.optString("stok"), strD, strN);
                                strDek_tahun_didapatkan = AlgoritmeRSA.Dekripsi(responses.optString("tahun_didapatkan"), strD, strN);

                                map.put("id_benda",    responses.optString("id_barang"));
                                map.put("nama_barang",     strDek_nama_barang);
                                map.put("stok",          strDek_stok);
                                map.put("tahun",         strDek_tahun_didapatkan);

                                arrayutama.add(map);
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




    private void Adapter(){
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayutama, R.layout.list_utama,
                new String[] {
                        "id_benda", "nama_barang", "stok", "tahun", "tahun2",
                },
                new int[] {
                    R.id.text_id_benda, R.id.text_nama_barang, R.id.db_stok, R.id.db_tahun, R.id.db_tahun2,

                }
        );

        list_utama.setAdapter(simpleAdapter);
            list_utama.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    id_benda = ((TextView) view.findViewById(R.id.text_id_benda)).getText().toString();
                    nama_barang = ((TextView) view.findViewById(R.id.text_nama_barang)).getText().toString();
                    stok = ((TextView) view.findViewById(R.id.db_stok)).getText().toString();
                    tahun = ((TextView) view.findViewById(R.id.db_tahun)).getText().toString();
                    Log.e("tahun2", tahun);

                    ListMenu();
                }
            });

        //}

        swipe_refresh.setRefreshing(false);
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    private void ListMenu(){

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.list_menu);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        TextView text_edit  = dialog.findViewById(R.id.text_edit);
        TextView text_hapus = dialog.findViewById(R.id.text_hapus);
        dialog.show();

        text_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });
        text_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Hapus2();
            }
        });
    }



    private void Hapus2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Yakin ingin hapus?");
        builder.setPositiveButton(
                "Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                        AndroidNetworking.post( Config.host + "delete.php")
                                .addBodyParameter("id_barang", id_benda)
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        if (response.optString("response").equals("success")){
                                            Toast.makeText(getApplicationContext(), "Data berhasil dihapus",
                                                    Toast.LENGTH_LONG).show();
                                            selectMysql();
                                            startActivity(new Intent(MainActivity.this, MainActivity.class));

                                        } else {
                                            Toast.makeText(getApplicationContext(), "Gagal Dihapus",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    @Override
                                    public void onError(ANError error) {
                                        // handle error
                                    }
                                });

                    }
                });

        builder.setNegativeButton(
                "Batal",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_logout) {
            editor = sharedPreferences.edit();
            editor.putString("string_id_admin", "");
            editor.apply();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();

            return true;
        }


        if (id == R.id.menu_register) {
                startActivity(new Intent(MainActivity.this, RegistrasiActivity.class));
                return true;
        }
        return true;
    }

    private void hasilSearch() {
        arrayutama.clear();
        AndroidNetworking.post( Config.host + "hasil_search.php")
                .addBodyParameter("nama_barang", strSearch)
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
                                strDek_nama_barang_search = AlgoritmeRSA.Dekripsi(responses.optString("nama_barang"), strD, strN);
                                strDek_stok_search = AlgoritmeRSA.Dekripsi(responses.optString("stok"), strD, strN);
                                strDek_tahun_didapatkan_search = AlgoritmeRSA.Dekripsi(responses.optString("tahun_didapatkan"), strD, strN);


                                map.put("id_benda",    responses.optString("id_barang"));
                                map.put("nama_barang",     strDek_nama_barang_search);
                                map.put("stok",          strDek_stok_search);
                                map.put("tahun",        strDek_tahun_didapatkan_search);
                                arrayutama.add(map);

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


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Yakin Ingin Keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor = sharedPreferences.edit();
                editor.putString("string_id_admin", "");
                editor.apply();
                finishAffinity();
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
