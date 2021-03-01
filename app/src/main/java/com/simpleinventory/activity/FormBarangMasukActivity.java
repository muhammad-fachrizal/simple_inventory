package com.simpleinventory.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.simpleinventory.R;
import com.simpleinventory.adapter.AdapterBenda;
import com.simpleinventory.controller.AppController;
import com.simpleinventory.data.DataBenda;
import com.simpleinventory.helper.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

public class FormBarangMasukActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    ProgressDialog pDialog;
    AdapterBenda adapterBenda;
    List<DataBenda> listBenda = new ArrayList<DataBenda>();

    String string_id, angkajudul, string_id_plus,
            string_barang_masuk1, string_barang_masuk2, string_barang_masuk3, string_barang_masuk4, string_barang_masuk5,
            string_barang_masuk6, string_barang_masuk7, string_barang_masuk8, string_barang_masuk9, string_barang_masuk10,

    string_stok_barang_masuk1, string_stok_barang_masuk2, string_stok_barang_masuk3, string_stok_barang_masuk4, string_stok_barang_masuk5,
            string_stok_barang_masuk6, string_stok_barang_masuk7, string_stok_barang_masuk8, string_stok_barang_masuk9, string_stok_barang_masuk10,

    string_jml_barang_masuk1, string_jml_barang_masuk2, string_jml_barang_masuk3, string_jml_barang_masuk4, string_jml_barang_masuk5,
    string_jml_barang_masuk6, string_jml_barang_masuk7, string_jml_barang_masuk8, string_jml_barang_masuk9, string_jml_barang_masuk10,

    string_id_barang_masuk1, string_id_barang_masuk2, string_id_barang_masuk3, string_id_barang_masuk4, string_id_barang_masuk5,
            string_id_barang_masuk6, string_id_barang_masuk7, string_id_barang_masuk8, string_id_barang_masuk9, string_id_barang_masuk10,

    string_alert = "", string_id_admin,

    string_nama_pengembali,

    strE,strN,  strEnk_total_jumlah_masuk, strEnk_nama_pengembali, todayString,
            strEnk_todayString,

    strEnk_jumlah_masuk1, strEnk_jumlah_masuk2, strEnk_jumlah_masuk3, strEnk_jumlah_masuk4, strEnk_jumlah_masuk5,
            strEnk_jumlah_masuk6, strEnk_jumlah_masuk7, strEnk_jumlah_masuk8, strEnk_jumlah_masuk9, strEnk_jumlah_masuk10,

    strEnk_upd_stok_masuk1, strEnk_upd_stok_masuk2, strEnk_upd_stok_masuk3, strEnk_upd_stok_masuk4, strEnk_upd_stok_masuk5,
            strEnk_upd_stok_masuk6, strEnk_upd_stok_masuk7, strEnk_upd_stok_masuk8, strEnk_upd_stok_masuk9, strEnk_upd_stok_masuk10,

    string_flag_masuk1 = "", string_flag_masuk2 = "", string_flag_masuk3 = "", string_flag_masuk4 = "", string_flag_masuk5 = "",
            string_flag_masuk6 = "", string_flag_masuk7 = "", string_flag_masuk8 = "", string_flag_masuk9 = "", string_flag_masuk10 = "";



    Integer int_id, int_id_plus,
            int_jml_barang_masuk1, int_jml_barang_masuk2, int_jml_barang_masuk3, int_jml_barang_masuk4, int_jml_barang_masuk5,
            int_jml_barang_masuk6, int_jml_barang_masuk7, int_jml_barang_masuk8, int_jml_barang_masuk9, int_jml_barang_masuk10,

    int_upd_stok_masuk1, int_upd_stok_masuk2, int_upd_stok_masuk3, int_upd_stok_masuk4, int_upd_stok_masuk5,
            int_upd_stok_masuk6, int_upd_stok_masuk7, int_upd_stok_masuk8, int_upd_stok_masuk9, int_upd_stok_masuk10,

    int_stok_barang_masuk1, int_stok_barang_masuk2, int_stok_barang_masuk3, int_stok_barang_masuk4, int_stok_barang_masuk5,
    int_stok_barang_masuk6, int_stok_barang_masuk7, int_stok_barang_masuk8, int_stok_barang_masuk9, int_stok_barang_masuk10,

    int_total_barang_masuk = 0;


    LinearLayout linear_barang_masuk1, linear_barang_masuk2, linear_barang_masuk3, linear_barang_masuk4,
            linear_barang_masuk5, linear_barang_masuk6, linear_barang_masuk7, linear_barang_masuk8,
            linear_barang_masuk9, linear_barang_masuk10;

    TextView txt_tambah_sp_barang_masuk1, txt_tambah_sp_barang_masuk2, txt_tambah_sp_barang_masuk3,
            txt_tambah_sp_barang_masuk4, txt_tambah_sp_barang_masuk5, txt_tambah_sp_barang_masuk6, txt_tambah_sp_barang_masuk7,
            txt_tambah_sp_barang_masuk8, txt_tambah_sp_barang_masuk9, txt_tambah_sp_barang_masuk10, txt_judul_form_masuk;

    Spinner spinner_barang_masuk1, spinner_barang_masuk2, spinner_barang_masuk3, spinner_barang_masuk4, spinner_barang_masuk5,
            spinner_barang_masuk6, spinner_barang_masuk7, spinner_barang_masuk8, spinner_barang_masuk9, spinner_barang_masuk10;

    EditText edt_jml_barang_masuk1, edt_jml_barang_masuk2, edt_jml_barang_masuk3, edt_jml_barang_masuk4, edt_jml_barang_masuk5,
            edt_jml_barang_masuk6, edt_jml_barang_masuk7, edt_jml_barang_masuk8, edt_jml_barang_masuk9, edt_jml_barang_masuk10,
            edt_nama_pengembali;



    Button btn_simpan_barang_masuk;

    public static final String url = Config.host + "get_benda.php";
    private static final String TAG = FormBarangMasukActivity.class.getSimpleName();

    public static final String TAG_ID_BARANG = "id_barang";
    public static final String TAG_NAMA_BARANG = "nama_barang";
    public static final String TAG_STOK = "stok";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_barang_masuk);

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        todayString = formatter.format(currentTime);

        sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);

        string_id_admin = sharedPreferences.getString("string_id_admin","");
        strE = sharedPreferences.getString("stringE", "");
        strN = sharedPreferences.getString("stringN", "");

        linear_barang_masuk1 = (LinearLayout) findViewById(R.id.linear_barang_masuk1);
        linear_barang_masuk2 = (LinearLayout) findViewById(R.id.linear_barang_masuk2);
        linear_barang_masuk3 = (LinearLayout) findViewById(R.id.linear_barang_masuk3);
        linear_barang_masuk4 = (LinearLayout) findViewById(R.id.linear_barang_masuk4);
        linear_barang_masuk5 = (LinearLayout) findViewById(R.id.linear_barang_masuk5);
        linear_barang_masuk6 = (LinearLayout) findViewById(R.id.linear_barang_masuk6);
        linear_barang_masuk7 = (LinearLayout) findViewById(R.id.linear_barang_masuk7);
        linear_barang_masuk8 = (LinearLayout) findViewById(R.id.linear_barang_masuk8);
        linear_barang_masuk9 = (LinearLayout) findViewById(R.id.linear_barang_masuk9);
        linear_barang_masuk10 = (LinearLayout) findViewById(R.id.linear_barang_masuk10);

        txt_tambah_sp_barang_masuk1 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk1);
        txt_tambah_sp_barang_masuk2 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk2);
        txt_tambah_sp_barang_masuk3 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk3);
        txt_tambah_sp_barang_masuk4 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk4);
        txt_tambah_sp_barang_masuk5 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk5);
        txt_tambah_sp_barang_masuk6 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk6);
        txt_tambah_sp_barang_masuk7 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk7);
        txt_tambah_sp_barang_masuk8 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk8);
        txt_tambah_sp_barang_masuk9 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk9);
        txt_tambah_sp_barang_masuk10 = (TextView) findViewById(R.id.txt_tambah_sp_barang_masuk10);
        txt_judul_form_masuk = findViewById(R.id.txt_judul_form_masuk);



        edt_nama_pengembali = (EditText) findViewById(R.id.edt_nama_pengembali);
        edt_jml_barang_masuk1 = (EditText) findViewById(R.id.edt_jml_barang_masuk1);
        edt_jml_barang_masuk2 = (EditText) findViewById(R.id.edt_jml_barang_masuk2);
        edt_jml_barang_masuk3 = (EditText) findViewById(R.id.edt_jml_barang_masuk3);
        edt_jml_barang_masuk4 = (EditText) findViewById(R.id.edt_jml_barang_masuk4);
        edt_jml_barang_masuk5 = (EditText) findViewById(R.id.edt_jml_barang_masuk5);
        edt_jml_barang_masuk6 = (EditText) findViewById(R.id.edt_jml_barang_masuk6);
        edt_jml_barang_masuk7 = (EditText) findViewById(R.id.edt_jml_barang_masuk7);
        edt_jml_barang_masuk8 = (EditText) findViewById(R.id.edt_jml_barang_masuk8);
        edt_jml_barang_masuk9 = (EditText) findViewById(R.id.edt_jml_barang_masuk9);
        edt_jml_barang_masuk10 = (EditText) findViewById(R.id.edt_jml_barang_masuk10);


        btn_simpan_barang_masuk = (Button) findViewById(R.id.btn_simpan_barang_masuk);

        spinner_barang_masuk1 = (Spinner) findViewById(R.id.spinner_barang_masuk1);
        spinner_barang_masuk2 = (Spinner) findViewById(R.id.spinner_barang_masuk2);
        spinner_barang_masuk3 = (Spinner) findViewById(R.id.spinner_barang_masuk3);
        spinner_barang_masuk4 = (Spinner) findViewById(R.id.spinner_barang_masuk4);
        spinner_barang_masuk5 = (Spinner) findViewById(R.id.spinner_barang_masuk5);
        spinner_barang_masuk6 = (Spinner) findViewById(R.id.spinner_barang_masuk6);
        spinner_barang_masuk7 = (Spinner) findViewById(R.id.spinner_barang_masuk7);
        spinner_barang_masuk8 = (Spinner) findViewById(R.id.spinner_barang_masuk8);
        spinner_barang_masuk9 = (Spinner) findViewById(R.id.spinner_barang_masuk9);
        spinner_barang_masuk10 = (Spinner) findViewById(R.id.spinner_barang_masuk10);

        adapterBenda = new AdapterBenda(FormBarangMasukActivity.this, listBenda);


        txt_tambah_sp_barang_masuk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               linear_barang_masuk2.setVisibility(view.VISIBLE);
            }
        });

        txt_tambah_sp_barang_masuk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_masuk3.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_masuk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_masuk4.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_masuk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_masuk5.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_masuk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_masuk6.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_masuk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_masuk7.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_masuk7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_masuk8.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_masuk8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_masuk9.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_masuk9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_masuk10.setVisibility(view.VISIBLE);
            }
        });


        spinner_barang_masuk1.setAdapter(adapterBenda);
        spinner_barang_masuk1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk1 = listBenda.get(position).getId_Benda();
                string_barang_masuk1 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk1 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_masuk2.setAdapter(adapterBenda);
        spinner_barang_masuk2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk2 = listBenda.get(position).getId_Benda();
                string_barang_masuk2 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk2 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_masuk3.setAdapter(adapterBenda);
        spinner_barang_masuk3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk3 = listBenda.get(position).getId_Benda();
                string_barang_masuk3 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk3 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_masuk4.setAdapter(adapterBenda);
        spinner_barang_masuk4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk4 = listBenda.get(position).getId_Benda();
                string_barang_masuk4 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk4 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_masuk5.setAdapter(adapterBenda);
        spinner_barang_masuk5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk5 = listBenda.get(position).getId_Benda();
                string_barang_masuk5 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk5 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_masuk6.setAdapter(adapterBenda);
        spinner_barang_masuk6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk6 = listBenda.get(position).getId_Benda();
                string_barang_masuk6 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk6 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_masuk7.setAdapter(adapterBenda);
        spinner_barang_masuk7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk7 = listBenda.get(position).getId_Benda();
                string_barang_masuk7 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk7 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_masuk8.setAdapter(adapterBenda);
        spinner_barang_masuk8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk8 = listBenda.get(position).getId_Benda();
                string_barang_masuk8 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk8 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_masuk9.setAdapter(adapterBenda);
        spinner_barang_masuk9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk9 = listBenda.get(position).getId_Benda();
                string_barang_masuk9 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk9 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_masuk10.setAdapter(adapterBenda);
        spinner_barang_masuk10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_masuk10 = listBenda.get(position).getId_Benda();
                string_barang_masuk10 = listBenda.get(position).getNama_Barang();
                string_stok_barang_masuk10 = listBenda.get(position).getStok();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        callDataBenda();
        getAngkaId();


        btn_simpan_barang_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!edt_jml_barang_masuk1.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk1.getText().toString().equals("0") &&
                        !edt_nama_pengembali.getText().toString().isEmpty())
                {
                    string_jml_barang_masuk1 = edt_jml_barang_masuk1.getText().toString();
                    string_alert = string_alert + string_barang_masuk1 + " = " + string_jml_barang_masuk1 + "\n";

                    int_jml_barang_masuk1 = Integer.parseInt(string_jml_barang_masuk1);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk1;

                    int_stok_barang_masuk1 = Integer.parseInt(string_stok_barang_masuk1);
                    int_upd_stok_masuk1 = int_stok_barang_masuk1 + int_jml_barang_masuk1;

                    string_flag_masuk1 = "terisi";
                }

                if(!edt_jml_barang_masuk2.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk2.getText().toString().equals("0"))
                {
                    string_jml_barang_masuk2 = edt_jml_barang_masuk2.getText().toString();
                    string_alert = string_alert + string_barang_masuk2 + " = " + string_jml_barang_masuk2 + "\n";

                    int_jml_barang_masuk2 = Integer.parseInt(string_jml_barang_masuk2);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk2;

                    int_stok_barang_masuk2 = Integer.parseInt(string_stok_barang_masuk2);
                    int_upd_stok_masuk2 = int_stok_barang_masuk2 + int_jml_barang_masuk2;

                    string_flag_masuk2 = "terisi";
                }

                if(!edt_jml_barang_masuk3.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk3.getText().toString().equals("0"))
                {
                    string_jml_barang_masuk3 = edt_jml_barang_masuk3.getText().toString();
                    string_alert = string_alert + string_barang_masuk3 + "=" + string_jml_barang_masuk3 + "\n";

                    int_jml_barang_masuk3 = Integer.parseInt(string_jml_barang_masuk3);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk3;

                    int_stok_barang_masuk3 = Integer.parseInt(string_stok_barang_masuk3);
                    int_upd_stok_masuk3 = int_stok_barang_masuk3 + int_jml_barang_masuk3;

                    string_flag_masuk3 = "terisi";
                }

                if(!edt_jml_barang_masuk4.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk4.getText().toString().equals("0"))
                {
                    string_jml_barang_masuk4 = edt_jml_barang_masuk4.getText().toString();
                    string_alert = string_alert + string_barang_masuk4 + "=" + string_jml_barang_masuk4 + "\n";

                    int_jml_barang_masuk4 = Integer.parseInt(string_jml_barang_masuk4);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk4;

                    int_stok_barang_masuk4 = Integer.parseInt(string_stok_barang_masuk4);
                    int_upd_stok_masuk4 = int_stok_barang_masuk4 + int_jml_barang_masuk4;

                    string_flag_masuk4 = "terisi";

                }

                if(!edt_jml_barang_masuk5.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk5.getText().toString().equals("0"))
                {
                    string_jml_barang_masuk5 = edt_jml_barang_masuk5.getText().toString();
                    string_alert = string_alert + string_barang_masuk5 + "=" + string_jml_barang_masuk5 + "\n";

                    int_jml_barang_masuk5 = Integer.parseInt(string_jml_barang_masuk5);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk5;

                    int_stok_barang_masuk5 = Integer.parseInt(string_stok_barang_masuk5);
                    int_upd_stok_masuk5 = int_stok_barang_masuk5 + int_jml_barang_masuk5;

                    string_flag_masuk5 = "terisi";

                }

                if(!edt_jml_barang_masuk6.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk6.getText().toString().equals("0"))
                {
                    string_jml_barang_masuk6 = edt_jml_barang_masuk6.getText().toString();
                    string_alert = string_alert + string_barang_masuk6 + "=" + string_jml_barang_masuk6 + "\n";

                    int_jml_barang_masuk6 = Integer.parseInt(string_jml_barang_masuk6);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk6;

                    int_stok_barang_masuk6 = Integer.parseInt(string_stok_barang_masuk6);
                    int_upd_stok_masuk6 = int_stok_barang_masuk6 + int_jml_barang_masuk6;

                    string_flag_masuk6 = "terisi";

                }

                if(!edt_jml_barang_masuk7.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk7.getText().toString().equals("0"))
                {
                    string_jml_barang_masuk7 = edt_jml_barang_masuk7.getText().toString();
                    string_alert = string_alert + string_barang_masuk7 + "=" + string_jml_barang_masuk7 + "\n";

                    int_jml_barang_masuk7 = Integer.parseInt(string_jml_barang_masuk7);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk7;

                    int_stok_barang_masuk7 = Integer.parseInt(string_stok_barang_masuk7);
                    int_upd_stok_masuk7 = int_stok_barang_masuk7 + int_jml_barang_masuk7;

                    string_flag_masuk7 = "terisi";

                }

                if(!edt_jml_barang_masuk8.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk8.getText().toString().equals("0"))
                {
                    string_jml_barang_masuk8 = edt_jml_barang_masuk8.getText().toString();
                    string_alert = string_alert + string_barang_masuk8 + "=" + string_jml_barang_masuk8 + "\n";

                    int_jml_barang_masuk8 = Integer.parseInt(string_jml_barang_masuk8);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk8;

                    int_stok_barang_masuk8 = Integer.parseInt(string_stok_barang_masuk8);
                    int_upd_stok_masuk8 = int_stok_barang_masuk8 + int_jml_barang_masuk8;

                    string_flag_masuk8 = "terisi";

                }

                if(!edt_jml_barang_masuk9.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk9.getText().toString().equals("0"))
                {
                    string_jml_barang_masuk9 = edt_jml_barang_masuk9.getText().toString();
                    string_alert = string_alert + string_barang_masuk9 + "=" + string_jml_barang_masuk9 + "\n";

                    int_jml_barang_masuk9 = Integer.parseInt(string_jml_barang_masuk9);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk9;

                    int_stok_barang_masuk9 = Integer.parseInt(string_stok_barang_masuk9);
                    int_upd_stok_masuk9 = int_stok_barang_masuk9 + int_jml_barang_masuk9;

                    string_flag_masuk9 = "terisi";

                }

                if(!edt_jml_barang_masuk10.getText().toString().isEmpty() &&
                        !edt_jml_barang_masuk10.getText().toString().equals("0"))
                {
                    string_jml_barang_masuk10 = edt_jml_barang_masuk10.getText().toString();
                    string_alert = string_alert + string_barang_masuk10 + "=" + string_jml_barang_masuk10 + "\n";

                    int_jml_barang_masuk10 = Integer.parseInt(string_jml_barang_masuk10);
                    int_total_barang_masuk = int_total_barang_masuk + int_jml_barang_masuk10;

                    int_stok_barang_masuk10 = Integer.parseInt(string_stok_barang_masuk10);
                    int_upd_stok_masuk10 = int_stok_barang_masuk10 + int_jml_barang_masuk10;

                    string_flag_masuk10 = "terisi";

                }

                if(int_total_barang_masuk <= 0  && edt_nama_pengembali.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Silakan Isi Nma & Jumlah Barang",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    string_nama_pengembali = edt_nama_pengembali.getText().toString();
                    alertDialog();
                }


            }
        });


    }



    private void callDataBenda() {
        listBenda.clear();

        pDialog = new ProgressDialog(FormBarangMasukActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();

        // Creating volley request obj


        JsonArrayRequest jArr = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);

                                DataBenda item = new DataBenda();

                                item.setId_Benda(obj.getString(TAG_ID_BARANG));
                                item.setNama_Barang(obj.getString(TAG_NAMA_BARANG));
                                item.setStok(obj.getString(TAG_STOK));


                                listBenda.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        adapterBenda.notifyDataSetChanged();

                        hideDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(FormBarangMasukActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(jArr);
    }

    private void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Nama Pengembali = " + string_nama_pengembali + "\n"
                + string_alert + "Total Barang = " + int_total_barang_masuk + "\n"

               );
        builder.setPositiveButton(
                "Oke",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        tesSimpanTotalMasuk();
                        simpanTotalMasuk();


                       if(string_flag_masuk1.equals("terisi"))
                       {
                           strEnk_jumlah_masuk1 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk1.getText().toString(), strE, strN);
                           strEnk_upd_stok_masuk1 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk1), strE, strN);
                           simpanBarangMasuk1();
                           updateBarangMasuk1();
                       }

                       if(string_flag_masuk2.equals("terisi"))
                       {
                           strEnk_jumlah_masuk2 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk2.getText().toString(), strE, strN);
                           strEnk_upd_stok_masuk2 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk2), strE, strN);
                           simpanBarangMasuk2();
                           updateBarangMasuk2();
                       }

                       if(string_flag_masuk3.equals("terisi"))
                       {
                           strEnk_jumlah_masuk3 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk3.getText().toString(), strE, strN);
                           strEnk_upd_stok_masuk3 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk3), strE, strN);
                           updateBarangMasuk3();
                           simpanBarangMasuk3();

                       }

                        if(string_flag_masuk4.equals("terisi"))
                        {
                            strEnk_jumlah_masuk4 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk4.getText().toString(), strE, strN);
                            strEnk_upd_stok_masuk4 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk4), strE, strN);
                            simpanBarangMasuk4();
                            updateBarangMasuk4();
                        }

                        if(string_flag_masuk5.equals("terisi"))
                        {
                            strEnk_jumlah_masuk5 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk5.getText().toString(), strE, strN);
                            strEnk_upd_stok_masuk5 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk5), strE, strN);
                            simpanBarangMasuk5();
                            updateBarangMasuk5();
                        }

                        if(string_flag_masuk6.equals("terisi"))
                        {
                            strEnk_jumlah_masuk6 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk6.getText().toString(), strE, strN);
                            strEnk_upd_stok_masuk6 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk6), strE, strN);
                            simpanBarangMasuk6();
                            updateBarangMasuk6();
                        }

                        if(string_flag_masuk7.equals("terisi"))
                        {
                            strEnk_jumlah_masuk7 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk7.getText().toString(), strE, strN);
                            strEnk_upd_stok_masuk7 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk7), strE, strN);
                            simpanBarangMasuk7();
                            updateBarangMasuk7();
                        }

                        if(string_flag_masuk8.equals("terisi"))
                        {
                            strEnk_jumlah_masuk8 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk8.getText().toString(), strE, strN);
                            strEnk_upd_stok_masuk8 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk8), strE, strN);
                            simpanBarangMasuk8();
                            updateBarangMasuk8();
                        }

                        if(string_flag_masuk9.equals("terisi"))
                        {
                            strEnk_jumlah_masuk9 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk9.getText().toString(), strE, strN);
                            strEnk_upd_stok_masuk9 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk9), strE, strN);
                            simpanBarangMasuk9();
                            updateBarangMasuk9();
                        }

                        if(string_flag_masuk10.equals("terisi"))
                        {
                            strEnk_jumlah_masuk10 = AlgoritmeRSA.Enkripsi(edt_jml_barang_masuk10.getText().toString(), strE, strN);
                            strEnk_upd_stok_masuk10 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_masuk10), strE, strN);
                            simpanBarangMasuk10();
                            updateBarangMasuk10();
                        }

                        if(!string_flag_masuk1.equals("terisi"))
                        {
                            Toast.makeText(getApplicationContext(), "Silakan Isi Jumlah Benda",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            startActivity(new Intent(FormBarangMasukActivity.this, MainActivity.class));
                            finish();
                        }



                    }
                });

        builder.setNegativeButton(
                "Batal",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        string_alert = "";
                        int_total_barang_masuk = 0;
                        dialog.dismiss();
                    }
                });

        builder.show();
    }

    private void simpanBarangMasuk1(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk1)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk1)
                .addBodyParameter("id_masuk", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangMasuk1 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangMasuk2(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk2)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk2)
                .addBodyParameter("id_masuk", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanbarangMasuk2 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangMasuk3(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk3)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk3)
                .addBodyParameter("id_masuk", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), " simpanbarangMasuk3 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangMasuk4(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk4)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk4)
                .addBodyParameter("id_masuk", string_id_plus)

                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanbarangMasuk4 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangMasuk5(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk5)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk5)
                .addBodyParameter("id_masuk", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangMasuk6(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk6)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk6)
                .addBodyParameter("id_masuk", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangMasuk7(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk7)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk7)
                .addBodyParameter("id_masuk", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangMasuk8(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk8)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk8)
                .addBodyParameter("id_masuk", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangMasuk9(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk9)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk9)
                .addBodyParameter("id_masuk", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangMasuk10(){
        AndroidNetworking.post( Config.host + "add_detail_barang_masuk.php")
                .addBodyParameter("id_barang", string_id_barang_masuk10)
                .addBodyParameter("jumlah_masuk", strEnk_jumlah_masuk10)
                .addBodyParameter("id_masuk", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }



    private void updateBarangMasuk1(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk1)
                .addBodyParameter("stok", strEnk_upd_stok_masuk1)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "updatebarangMasuk1 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void updateBarangMasuk2(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk2)
                .addBodyParameter("stok", strEnk_upd_stok_masuk2)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "updatebarangMasuk2 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void updateBarangMasuk3(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk3)
                .addBodyParameter("stok", strEnk_upd_stok_masuk3)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "updatebarangMasuk3 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void updateBarangMasuk4(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk4)
                .addBodyParameter("stok", strEnk_upd_stok_masuk4)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "updatebarangMasuk4 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void updateBarangMasuk5(){
         AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk5)
                .addBodyParameter("stok", strEnk_upd_stok_masuk5)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void updateBarangMasuk6(){
          AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk6)
                .addBodyParameter("stok", strEnk_upd_stok_masuk6)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void updateBarangMasuk7(){
          AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk7)
                .addBodyParameter("stok", strEnk_upd_stok_masuk7)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void updateBarangMasuk8(){
          AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk8)
                .addBodyParameter("stok", strEnk_upd_stok_masuk8)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void updateBarangMasuk9(){
         AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk9)
                .addBodyParameter("stok", strEnk_upd_stok_masuk9)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void updateBarangMasuk10(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_masuk10)
                .addBodyParameter("stok", strEnk_upd_stok_masuk10)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("update_stok success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    private void simpanTotalMasuk() {
        AndroidNetworking.post( Config.host + "add_total_barang_masuk.php")
                .addBodyParameter("total_jumlah_masuk", strEnk_total_jumlah_masuk)
                .addBodyParameter("id_masuk", string_id_plus)
                .addBodyParameter("nama_pengembali", strEnk_nama_pengembali)
                .addBodyParameter("tanggal_masuk", strEnk_todayString)
                .addBodyParameter("id_admin", string_id_admin)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_total_barang_masuk success")){
                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal simpan total",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }





    private void getAngkaId() {
        AndroidNetworking.get(Config.host + "get_angka_id_masuk.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            if(response.length()>0) {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);

                                    string_id = data.getString("0");

                                    int_id = parseInt(string_id);
                                    int_id_plus = int_id + 2;

                                    string_id_plus = Integer.toString(int_id_plus);

                                }
                            }
                            else
                            {
                                string_id_plus = "1001";
                            }

                        } catch (JSONException e) {
                            string_id_plus = "1001";

                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        string_id_plus = "1001";

                    }
                });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void tesSimpanTotalMasuk() {
        strEnk_todayString = AlgoritmeRSA.Enkripsi(todayString, strE, strN);
        strEnk_total_jumlah_masuk = AlgoritmeRSA.Enkripsi(String.valueOf(int_total_barang_masuk), strE, strN);
        strEnk_nama_pengembali = AlgoritmeRSA.Enkripsi(string_nama_pengembali, strE, strN);
    }
}
