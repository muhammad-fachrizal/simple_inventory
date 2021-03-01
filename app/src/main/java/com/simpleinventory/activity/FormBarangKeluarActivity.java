package com.simpleinventory.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.annotation.IdRes;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FormBarangKeluarActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    RadioGroup radio_status;
    ProgressDialog pDialog;
    AdapterBenda adapterBenda;
    List<DataBenda> listBenda = new ArrayList<DataBenda>();

    String string_id, angkajudul, string_id_plus,
            string_barang_keluar1, string_barang_keluar2, string_barang_keluar3, string_barang_keluar4, string_barang_keluar5,
            string_barang_keluar6, string_barang_keluar7, string_barang_keluar8, string_barang_keluar9, string_barang_keluar10,

    string_stok_barang_keluar1, string_stok_barang_keluar2, string_stok_barang_keluar3, string_stok_barang_keluar4, string_stok_barang_keluar5,
            string_stok_barang_keluar6, string_stok_barang_keluar7, string_stok_barang_keluar8, string_stok_barang_keluar9, string_stok_barang_keluar10,

    string_jml_barang_keluar1, string_jml_barang_keluar2, string_jml_barang_keluar3, string_jml_barang_keluar4, string_jml_barang_keluar5,
    string_jml_barang_keluar6, string_jml_barang_keluar7, string_jml_barang_keluar8, string_jml_barang_keluar9, string_jml_barang_keluar10,

    string_id_barang_keluar1, string_id_barang_keluar2, string_id_barang_keluar3, string_id_barang_keluar4, string_id_barang_keluar5,
            string_id_barang_keluar6, string_id_barang_keluar7, string_id_barang_keluar8, string_id_barang_keluar9, string_id_barang_keluar10,


    string_alert = "", status = "", string_nama_peminjam, string_id_admin,

    string_flag_keluar1 = "", string_flag_keluar2 = "", string_flag_keluar3 = "", string_flag_keluar4 = "", string_flag_keluar5 = "",
            string_flag_keluar6 = "", string_flag_keluar7 = "", string_flag_keluar8 = "", string_flag_keluar9 = "", string_flag_keluar10 = "",
    flag_habis = "",

    strN, strE, todayString, strEnk_todayString, strEnk_total_jumlah_keluar, strEnk_nama_peminjam, strEnk_status,

    strEnk_jumlah_keluar1, strEnk_jumlah_keluar2, strEnk_jumlah_keluar3, strEnk_jumlah_keluar4, strEnk_jumlah_keluar5,
    strEnk_jumlah_keluar6, strEnk_jumlah_keluar7, strEnk_jumlah_keluar8, strEnk_jumlah_keluar9, strEnk_jumlah_keluar10,

    strEnk_upd_stok_keluar1, strEnk_upd_stok_keluar2, strEnk_upd_stok_keluar3, strEnk_upd_stok_keluar4, strEnk_upd_stok_keluar5,
             strEnk_upd_stok_keluar6, strEnk_upd_stok_keluar7, strEnk_upd_stok_keluar8, strEnk_upd_stok_keluar9, strEnk_upd_stok_keluar10;




    Integer int_id, int_id_plus,
            int_jml_barang_keluar1, int_jml_barang_keluar2, int_jml_barang_keluar3, int_jml_barang_keluar4, int_jml_barang_keluar5,
            int_jml_barang_keluar6, int_jml_barang_keluar7, int_jml_barang_keluar8, int_jml_barang_keluar9, int_jml_barang_keluar10,

    int_upd_stok_keluar1, int_upd_stok_keluar2, int_upd_stok_keluar3, int_upd_stok_keluar4, int_upd_stok_keluar5,
            int_upd_stok_keluar6, int_upd_stok_keluar7, int_upd_stok_keluar8, int_upd_stok_keluar9, int_upd_stok_keluar10,

    int_stok_barang_keluar1, int_stok_barang_keluar2, int_stok_barang_keluar3, int_stok_barang_keluar4, int_stok_barang_keluar5,
    int_stok_barang_keluar6, int_stok_barang_keluar7, int_stok_barang_keluar8, int_stok_barang_keluar9, int_stok_barang_keluar10,

    int_total_barang_keluar = 0;



    LinearLayout linear_barang_keluar1, linear_barang_keluar2, linear_barang_keluar3, linear_barang_keluar4,
            linear_barang_keluar5, linear_barang_keluar6, linear_barang_keluar7, linear_barang_keluar8,
            linear_barang_keluar9, linear_barang_keluar10;

    TextView txt_judul, txt_tambah_sp_barang_keluar1, txt_tambah_sp_barang_keluar2, txt_tambah_sp_barang_keluar3,
            txt_tambah_sp_barang_keluar4, txt_tambah_sp_barang_keluar5, txt_tambah_sp_barang_keluar6, txt_tambah_sp_barang_keluar7,
            txt_tambah_sp_barang_keluar8, txt_tambah_sp_barang_keluar9, txt_tambah_sp_barang_keluar10,

            txt_stok_tersedia5, txt_stok_tersedia4, txt_stok_tersedia3, txt_stok_tersedia2, txt_stok_tersedia1,
            txt_stok_tersedia6, txt_stok_tersedia7, txt_stok_tersedia8, txt_stok_tersedia9, txt_stok_tersedia10;

    Spinner spinner_barang_keluar1, spinner_barang_keluar2, spinner_barang_keluar3, spinner_barang_keluar4, spinner_barang_keluar5,
            spinner_barang_keluar6, spinner_barang_keluar7, spinner_barang_keluar8, spinner_barang_keluar9, spinner_barang_keluar10;

    EditText edt_jml_barang_keluar1, edt_jml_barang_keluar2, edt_jml_barang_keluar3, edt_jml_barang_keluar4, edt_jml_barang_keluar5,
            edt_jml_barang_keluar6, edt_jml_barang_keluar7, edt_jml_barang_keluar8, edt_jml_barang_keluar9, edt_jml_barang_keluar10,
    edt_nama_peminjam;

    Button btn_simpan_barang_keluar;

    public static final String url = Config.host + "get_benda.php";
    private static final String TAG = FormBarangKeluarActivity.class.getSimpleName();

    public static final String TAG_ID_BARANG = "id_barang";
    public static final String TAG_NAMA_BARANG = "nama_barang";
    public static final String TAG_STOK = "stok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_barang_keluar);

        sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);

        string_id_admin = sharedPreferences.getString("string_id_admin","");
        strE = sharedPreferences.getString("stringE", "");
        strN = sharedPreferences.getString("stringN", "");

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        todayString = formatter.format(currentTime);

        linear_barang_keluar1 = (LinearLayout) findViewById(R.id.linear_barang_keluar1);
        linear_barang_keluar2 = (LinearLayout) findViewById(R.id.linear_barang_keluar2);
        linear_barang_keluar3 = (LinearLayout) findViewById(R.id.linear_barang_keluar3);
        linear_barang_keluar4 = (LinearLayout) findViewById(R.id.linear_barang_keluar4);
        linear_barang_keluar5 = (LinearLayout) findViewById(R.id.linear_barang_keluar5);
        linear_barang_keluar6 = (LinearLayout) findViewById(R.id.linear_barang_keluar6);
        linear_barang_keluar7 = (LinearLayout) findViewById(R.id.linear_barang_keluar7);
        linear_barang_keluar8 = (LinearLayout) findViewById(R.id.linear_barang_keluar8);
        linear_barang_keluar9 = (LinearLayout) findViewById(R.id.linear_barang_keluar9);
        linear_barang_keluar10 = (LinearLayout) findViewById(R.id.linear_barang_keluar10);

        txt_judul = (TextView) findViewById(R.id.txt_judul);
        txt_tambah_sp_barang_keluar1 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar1);
        txt_tambah_sp_barang_keluar2 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar2);
        txt_tambah_sp_barang_keluar3 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar3);
        txt_tambah_sp_barang_keluar4 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar4);
        txt_tambah_sp_barang_keluar5 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar5);
        txt_tambah_sp_barang_keluar6 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar6);
        txt_tambah_sp_barang_keluar7 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar7);
        txt_tambah_sp_barang_keluar8 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar8);
        txt_tambah_sp_barang_keluar9 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar9);
        txt_tambah_sp_barang_keluar10 = (TextView) findViewById(R.id.txt_tambah_sp_barang_keluar10);

        txt_stok_tersedia1 = (TextView) findViewById(R.id.txt_stok_tersedia1);
        txt_stok_tersedia2 = (TextView) findViewById(R.id.txt_stok_tersedia2);
        txt_stok_tersedia3 = (TextView) findViewById(R.id.txt_stok_tersedia3);
        txt_stok_tersedia4 = (TextView) findViewById(R.id.txt_stok_tersedia4);
        txt_stok_tersedia5 = (TextView) findViewById(R.id.txt_stok_tersedia5);
        txt_stok_tersedia6 = (TextView) findViewById(R.id.txt_stok_tersedia6);
        txt_stok_tersedia7 = (TextView) findViewById(R.id.txt_stok_tersedia7);
        txt_stok_tersedia8 = (TextView) findViewById(R.id.txt_stok_tersedia8);
        txt_stok_tersedia9 = (TextView) findViewById(R.id.txt_stok_tersedia9);
        txt_stok_tersedia10 = (TextView) findViewById(R.id.txt_stok_tersedia10);

        edt_nama_peminjam = (EditText) findViewById(R.id.edt_nama_peminjam);
        edt_jml_barang_keluar1 = (EditText) findViewById(R.id.edt_jml_barang_keluar1);
        edt_jml_barang_keluar2 = (EditText) findViewById(R.id.edt_jml_barang_keluar2);
        edt_jml_barang_keluar3 = (EditText) findViewById(R.id.edt_jml_barang_keluar3);
        edt_jml_barang_keluar4 = (EditText) findViewById(R.id.edt_jml_barang_keluar4);
        edt_jml_barang_keluar5 = (EditText) findViewById(R.id.edt_jml_barang_keluar5);
        edt_jml_barang_keluar6 = (EditText) findViewById(R.id.edt_jml_barang_keluar6);
        edt_jml_barang_keluar7 = (EditText) findViewById(R.id.edt_jml_barang_keluar7);
        edt_jml_barang_keluar8 = (EditText) findViewById(R.id.edt_jml_barang_keluar8);
        edt_jml_barang_keluar9 = (EditText) findViewById(R.id.edt_jml_barang_keluar9);
        edt_jml_barang_keluar10 = (EditText) findViewById(R.id.edt_jml_barang_keluar10);

        btn_simpan_barang_keluar = (Button) findViewById(R.id.btn_simpan_barang_keluar);

        spinner_barang_keluar1 = (Spinner) findViewById(R.id.spinner_barang_keluar1);
        spinner_barang_keluar2 = (Spinner) findViewById(R.id.spinner_barang_keluar2);
        spinner_barang_keluar3 = (Spinner) findViewById(R.id.spinner_barang_keluar3);
        spinner_barang_keluar4 = (Spinner) findViewById(R.id.spinner_barang_keluar4);
        spinner_barang_keluar5 = (Spinner) findViewById(R.id.spinner_barang_keluar5);
        spinner_barang_keluar6 = (Spinner) findViewById(R.id.spinner_barang_keluar6);
        spinner_barang_keluar7 = (Spinner) findViewById(R.id.spinner_barang_keluar7);
        spinner_barang_keluar8 = (Spinner) findViewById(R.id.spinner_barang_keluar8);
        spinner_barang_keluar9 = (Spinner) findViewById(R.id.spinner_barang_keluar9);
        spinner_barang_keluar10 = (Spinner) findViewById(R.id.spinner_barang_keluar10);

        radio_status = (RadioGroup) findViewById(R.id.radio_status);

        adapterBenda = new AdapterBenda(FormBarangKeluarActivity.this, listBenda);

        radio_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.radio_pinjam:
                        status = "Pinjam";
                        break;
                    case R.id.radio_buang:
                        status = "Buang";
                        break;

                }

                Log.d("Log status", status);
            }
        });



        txt_tambah_sp_barang_keluar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               linear_barang_keluar2.setVisibility(view.VISIBLE);
            }
        });

        txt_tambah_sp_barang_keluar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_keluar3.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_keluar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_keluar4.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_keluar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_keluar5.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_keluar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_keluar6.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_keluar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_keluar7.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_keluar7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_keluar8.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_keluar8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_keluar9.setVisibility(view.VISIBLE);
            }
        });


        txt_tambah_sp_barang_keluar9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_barang_keluar10.setVisibility(view.VISIBLE);
            }
        });



        spinner_barang_keluar1.setAdapter(adapterBenda);
        spinner_barang_keluar1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar1 = listBenda.get(position).getId_Benda();
                string_barang_keluar1 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar1 = listBenda.get(position).getStok();

                txt_stok_tersedia1.setText("Stok Tersedia = " + string_stok_barang_keluar1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_keluar2.setAdapter(adapterBenda);
        spinner_barang_keluar2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar2 = listBenda.get(position).getId_Benda();
                string_barang_keluar2 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar2 = listBenda.get(position).getStok();

                txt_stok_tersedia2.setText("Stok Tersedia = " + string_stok_barang_keluar2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_keluar3.setAdapter(adapterBenda);
        spinner_barang_keluar3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar3 = listBenda.get(position).getId_Benda();
                string_barang_keluar3 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar3 = listBenda.get(position).getStok();

                txt_stok_tersedia3.setText("Stok Tersedia = " + string_stok_barang_keluar3);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_keluar4.setAdapter(adapterBenda);
        spinner_barang_keluar4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar4 = listBenda.get(position).getId_Benda();
                string_barang_keluar4 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar4 = listBenda.get(position).getStok();

                txt_stok_tersedia4.setText("Stok Tersedia = " + string_stok_barang_keluar4);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_keluar5.setAdapter(adapterBenda);
        spinner_barang_keluar5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar5 = listBenda.get(position).getId_Benda();
                string_barang_keluar5 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar5 = listBenda.get(position).getStok();

                txt_stok_tersedia5.setText("Stok Tersedia = " + string_stok_barang_keluar5);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_keluar6.setAdapter(adapterBenda);
        spinner_barang_keluar6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar6 = listBenda.get(position).getId_Benda();
                string_barang_keluar6 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar6 = listBenda.get(position).getStok();

                txt_stok_tersedia6.setText("Stok Tersedia = " + string_stok_barang_keluar6);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_keluar7.setAdapter(adapterBenda);
        spinner_barang_keluar7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar7 = listBenda.get(position).getId_Benda();
                string_barang_keluar7 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar7 = listBenda.get(position).getStok();

                txt_stok_tersedia7.setText("Stok Tersedia = " + string_stok_barang_keluar7);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_keluar8.setAdapter(adapterBenda);
        spinner_barang_keluar8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar8 = listBenda.get(position).getId_Benda();
                string_barang_keluar8 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar8 = listBenda.get(position).getStok();

                txt_stok_tersedia8.setText("Stok Tersedia = " + string_stok_barang_keluar8);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_keluar9.setAdapter(adapterBenda);
        spinner_barang_keluar9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar9 = listBenda.get(position).getId_Benda();
                string_barang_keluar9 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar9 = listBenda.get(position).getStok();

                txt_stok_tersedia9.setText("Stok Tersedia = " + string_stok_barang_keluar9);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner_barang_keluar10.setAdapter(adapterBenda);
        spinner_barang_keluar10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                string_id_barang_keluar10 = listBenda.get(position).getId_Benda();
                string_barang_keluar10 = listBenda.get(position).getNama_Barang();

                string_stok_barang_keluar10 = listBenda.get(position).getStok();

                txt_stok_tersedia10.setText("Stok Tersedia = " + string_stok_barang_keluar10);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        callDataBenda();
        getAngkaIdKeluar();

        btn_simpan_barang_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_habis="";
                string_alert="";
                int_total_barang_keluar=0;
                if(!edt_nama_peminjam.getText().toString().isEmpty() && !status.isEmpty())
                {
                    string_nama_peminjam = edt_nama_peminjam.getText().toString();

                    if(!edt_jml_barang_keluar1.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar1.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar1 = edt_jml_barang_keluar1.getText().toString();
                        string_alert = string_alert + string_barang_keluar1 + " = " + string_jml_barang_keluar1 + "\n";

                        int_jml_barang_keluar1 = Integer.parseInt(string_jml_barang_keluar1);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar1;


                        int_stok_barang_keluar1 = Integer.parseInt(string_stok_barang_keluar1);
                        int_upd_stok_keluar1 = int_stok_barang_keluar1 - int_jml_barang_keluar1;

                        if(int_upd_stok_keluar1 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar1 + "\n";
                        }

                        string_flag_keluar1 = "terisi";
                    }

                    if(!edt_jml_barang_keluar2.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar2.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar2 = edt_jml_barang_keluar2.getText().toString();
                        string_alert = string_alert + string_barang_keluar2 + " = " + string_jml_barang_keluar2 + "\n";

                        int_jml_barang_keluar2 = Integer.parseInt(string_jml_barang_keluar2);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar2;


                        int_stok_barang_keluar2 = Integer.parseInt(string_stok_barang_keluar2);
                        int_upd_stok_keluar2 = int_stok_barang_keluar2 - int_jml_barang_keluar2;

                        if(int_upd_stok_keluar2 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar2 + "\n";
                        }

                        string_flag_keluar2 = "terisi";
                    }

                    if(!edt_jml_barang_keluar3.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar3.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar3 = edt_jml_barang_keluar3.getText().toString();
                        string_alert = string_alert + string_barang_keluar3 + "=" + string_jml_barang_keluar3 + "\n";

                        int_jml_barang_keluar3 = Integer.parseInt(string_jml_barang_keluar3);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar3;


                        int_stok_barang_keluar3 = Integer.parseInt(string_stok_barang_keluar3);
                        int_upd_stok_keluar3 = int_stok_barang_keluar3 - int_jml_barang_keluar3;

                        if(int_upd_stok_keluar3 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar3 + "\n";
                        }

                        string_flag_keluar3 = "terisi";
                    }

                    if(!edt_jml_barang_keluar4.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar4.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar4 = edt_jml_barang_keluar4.getText().toString();
                        string_alert = string_alert + string_barang_keluar4 + "=" + string_jml_barang_keluar4 + "\n";

                        int_jml_barang_keluar4 = Integer.parseInt(string_jml_barang_keluar4);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar4;


                        int_stok_barang_keluar4 = Integer.parseInt(string_stok_barang_keluar4);
                        int_upd_stok_keluar4 = int_stok_barang_keluar4 - int_jml_barang_keluar4;

                        if(int_upd_stok_keluar4 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar4 + "\n";
                        }

                        string_flag_keluar4 = "terisi";
                    }

                    if(!edt_jml_barang_keluar5.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar5.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar5 = edt_jml_barang_keluar5.getText().toString();
                        string_alert = string_alert + string_barang_keluar5 + "=" + string_jml_barang_keluar5 + "\n";

                        int_jml_barang_keluar5 = Integer.parseInt(string_jml_barang_keluar5);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar5;


                        int_stok_barang_keluar5 = Integer.parseInt(string_stok_barang_keluar5);
                        int_upd_stok_keluar5 = int_stok_barang_keluar5 - int_jml_barang_keluar5;

                        if(int_upd_stok_keluar5 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar5 + "\n";
                        }

                        string_flag_keluar5 = "terisi";

                    }

                    if(!edt_jml_barang_keluar6.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar6.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar6 = edt_jml_barang_keluar6.getText().toString();
                        string_alert = string_alert + string_barang_keluar6 + "=" + string_jml_barang_keluar6 + "\n";

                        int_jml_barang_keluar6 = Integer.parseInt(string_jml_barang_keluar6);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar6;


                        int_stok_barang_keluar6 = Integer.parseInt(string_stok_barang_keluar6);
                        int_upd_stok_keluar6 = int_stok_barang_keluar6 - int_jml_barang_keluar6;

                        if(int_upd_stok_keluar6 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar6 + "\n";
                        }

                        string_flag_keluar6 = "terisi";

                    }

                    if(!edt_jml_barang_keluar7.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar7.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar7 = edt_jml_barang_keluar7.getText().toString();
                        string_alert = string_alert + string_barang_keluar7 + "=" + string_jml_barang_keluar7 + "\n";

                        int_jml_barang_keluar7 = Integer.parseInt(string_jml_barang_keluar7);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar7;


                        int_stok_barang_keluar7 = Integer.parseInt(string_stok_barang_keluar7);
                        int_upd_stok_keluar7 = int_stok_barang_keluar7 - int_jml_barang_keluar7;

                        if(int_upd_stok_keluar7 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar7 + "\n";
                        }

                        string_flag_keluar7 = "terisi";

                    }

                    if(!edt_jml_barang_keluar8.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar8.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar8 = edt_jml_barang_keluar8.getText().toString();
                        string_alert = string_alert + string_barang_keluar8 + "=" + string_jml_barang_keluar8 + "\n";

                        int_jml_barang_keluar8 = Integer.parseInt(string_jml_barang_keluar8);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar8;


                        int_stok_barang_keluar8 = Integer.parseInt(string_stok_barang_keluar8);
                        int_upd_stok_keluar8 = int_stok_barang_keluar8 - int_jml_barang_keluar8;

                        if(int_upd_stok_keluar8 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar8 + "\n";
                        }

                        string_flag_keluar8 = "terisi";

                    }

                    if(!edt_jml_barang_keluar9.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar9.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar9 = edt_jml_barang_keluar9.getText().toString();
                        string_alert = string_alert + string_barang_keluar9 + "=" + string_jml_barang_keluar9 + "\n";

                        int_jml_barang_keluar9 = Integer.parseInt(string_jml_barang_keluar9);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar9;


                        int_stok_barang_keluar9 = Integer.parseInt(string_stok_barang_keluar9);
                        int_upd_stok_keluar9 = int_stok_barang_keluar9 - int_jml_barang_keluar9;

                        if(int_upd_stok_keluar9 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar9 + "\n";
                        }

                        string_flag_keluar9 = "terisi";

                    }

                    if(!edt_jml_barang_keluar10.getText().toString().isEmpty() &&
                            !edt_jml_barang_keluar10.getText().toString().equals("0"))
                    {
                        string_jml_barang_keluar10 = edt_jml_barang_keluar10.getText().toString();
                        string_alert = string_alert + string_barang_keluar10 + "=" + string_jml_barang_keluar10 + "\n";

                        int_jml_barang_keluar10 = Integer.parseInt(string_jml_barang_keluar10);
                        int_total_barang_keluar = int_total_barang_keluar + int_jml_barang_keluar10;


                        int_stok_barang_keluar10 = Integer.parseInt(string_stok_barang_keluar10);
                        int_upd_stok_keluar10 = int_stok_barang_keluar10 - int_jml_barang_keluar10;

                        if(int_upd_stok_keluar10 < 0) {
                            flag_habis = flag_habis + "-" + string_barang_keluar10 + "\n";
                        }

                        string_flag_keluar10 = "terisi";

                    }

                    if(!flag_habis.equals("")){
                        alertDialogFlagHabis();
                    }
                    else {
                        alertDialog();
                    }

                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Harap Isi Nama dan Status",
                            Toast.LENGTH_LONG).show();
                }


                //alertDialog();

            }
        });

    }



    private void callDataBenda() {
        listBenda.clear();

        pDialog = new ProgressDialog(FormBarangKeluarActivity.this);
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
                Toast.makeText(FormBarangKeluarActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(jArr);
    }

    private void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Nama Peminjam = " + edt_nama_peminjam.getText().toString() + "\n"
                + "Status = " + status + "\n"
                + string_alert
                + "\n"
                + "Total Barang = " + int_total_barang_keluar);
        builder.setPositiveButton(
                "Oke",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        tesSimpanTotalKeluar();
                        simpanTotalkeluar();

                        if(string_flag_keluar1.equals("terisi"))
                        {
                            strEnk_jumlah_keluar1 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar1.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar1 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar1), strE, strN);
                            simpanBarangkeluar1();
                            updateStokBenda1();
                        }

                        if(string_flag_keluar2.equals("terisi"))
                        {
                            strEnk_jumlah_keluar2 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar2.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar2 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar2), strE, strN);
                            simpanBarangkeluar2();
                            updateStokBenda2();
                        }

                        if(string_flag_keluar3.equals("terisi"))
                        {
                            strEnk_jumlah_keluar3 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar3.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar3 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar3), strE, strN);
                            simpanBarangkeluar3();
                            updateStokBenda3();
                        }

                        if(string_flag_keluar4.equals("terisi"))
                        {
                            strEnk_jumlah_keluar4 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar4.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar4 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar4), strE, strN);
                            simpanBarangkeluar4();
                            updateStokBenda4();
                        }

                        if(string_flag_keluar5.equals("terisi"))
                        {
                            strEnk_jumlah_keluar5 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar5.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar5 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar5), strE, strN);
                            simpanBarangkeluar5();
                            updateStokBenda5();
                        }

                        if(string_flag_keluar6.equals("terisi"))
                        {
                            strEnk_jumlah_keluar6 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar6.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar6 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar6), strE, strN);
                            simpanBarangkeluar6();
                            updateStokBenda6();
                        }

                        if(string_flag_keluar7.equals("terisi"))
                        {
                            strEnk_jumlah_keluar7 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar7.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar7 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar7), strE, strN);
                            simpanBarangkeluar7();
                            updateStokBenda7();
                        }

                        if(string_flag_keluar8.equals("terisi"))
                        {
                            strEnk_jumlah_keluar8 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar8.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar8 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar8), strE, strN);
                            simpanBarangkeluar8();
                            updateStokBenda8();
                        }

                        if(string_flag_keluar9.equals("terisi"))
                        {
                            strEnk_jumlah_keluar9 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar9.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar9 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar9), strE, strN);
                            simpanBarangkeluar9();
                            updateStokBenda9();
                        }

                        if(string_flag_keluar10.equals("terisi"))
                        {
                            strEnk_jumlah_keluar10 = AlgoritmeRSA.Enkripsi(edt_jml_barang_keluar10.getText().toString(), strE, strN);
                            strEnk_upd_stok_keluar10 = AlgoritmeRSA.Enkripsi(String.valueOf(int_upd_stok_keluar10), strE, strN);
                            simpanBarangkeluar10();
                            updateStokBenda10();
                        }

                        if(!string_flag_keluar1.equals("terisi"))
                        {
                            Toast.makeText(getApplicationContext(), "Silakan Isi Jumlah Benda",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            startActivity(new Intent(FormBarangKeluarActivity.this, MainActivity.class));
                            finish();
                        }

                    }
                });

        builder.setNegativeButton(
                "Batal",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        string_alert = "";
                        int_total_barang_keluar = 0;
                        dialog.dismiss();
                    }
                });

        builder.show();
    }


    private void alertDialogFlagHabis(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Stok Minus");
        builder.setMessage("Berikut adalah daftar barang yang jumlah keluarnya melebihi stok yang tersedia = \n"
                + flag_habis
                + "\nHarap ubah jumlah keluar sehingga tidak melebihi stok tersedia pada barang tersebut");

        builder.setPositiveButton(
                "Oke",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });



        builder.show();
    }





    private void simpanBarangkeluar1(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar1)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar1)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar1 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangkeluar2(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar2)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar2)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar2 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangkeluar3(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar3)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar3)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar3 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangkeluar4(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar4)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar4)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar4 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangkeluar5(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar5)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar5)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar5 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangkeluar6(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar6)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar6)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar6 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangkeluar7(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar7)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar7)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar7 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangkeluar8(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar8)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar8)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar8 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangkeluar9(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar9)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar9)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar9 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private void simpanBarangkeluar10(){
        AndroidNetworking.post( Config.host + "add_detail_barang_keluar.php")
                .addBodyParameter("id_barang", string_id_barang_keluar10)
                .addBodyParameter("jumlah_keluar", strEnk_jumlah_keluar10)
                .addBodyParameter("id_keluar", string_id_plus)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_detail_barang_keluar success")){

                            Toast.makeText(getApplicationContext(), "Berhasil disimpan",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "simpanBarangkeluar10 Gagal",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }






    private void updateStokBenda1(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar1)
                .addBodyParameter("stok", strEnk_upd_stok_keluar1)
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

    private void updateStokBenda2(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar2)
                .addBodyParameter("stok", strEnk_upd_stok_keluar2)
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

    private void updateStokBenda3(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar3)
                .addBodyParameter("stok", strEnk_upd_stok_keluar3)
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

    private void updateStokBenda4(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar4)
                .addBodyParameter("stok", strEnk_upd_stok_keluar4)
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

    private void updateStokBenda5(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar5)
                .addBodyParameter("stok", strEnk_upd_stok_keluar5)
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

    private void updateStokBenda6(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar6)
                .addBodyParameter("stok", strEnk_upd_stok_keluar6)
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

    private void updateStokBenda7(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar7)
                .addBodyParameter("stok", strEnk_upd_stok_keluar7)
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

    private void updateStokBenda8(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar8)
                .addBodyParameter("stok", strEnk_upd_stok_keluar8)
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

    private void updateStokBenda9(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar9)
                .addBodyParameter("stok", strEnk_upd_stok_keluar9)
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

    private void updateStokBenda10(){
        AndroidNetworking.post( Config.host + "update_stok.php")
                .addBodyParameter("id_barang", string_id_barang_keluar10)
                .addBodyParameter("stok", strEnk_upd_stok_keluar10)
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




    private void simpanTotalkeluar() {
        AndroidNetworking.post( Config.host + "add_total_barang_keluar.php")
                .addBodyParameter("status", strEnk_status)
                .addBodyParameter("nama_peminjam", strEnk_nama_peminjam)
                .addBodyParameter("id_admin", string_id_admin)
                .addBodyParameter("total_jumlah_keluar", strEnk_total_jumlah_keluar)
                .addBodyParameter("id_keluar", string_id_plus)
                .addBodyParameter("tanggal_keluar", strEnk_todayString)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("add_total_barang_keluar success")){
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





    private void getAngkaIdKeluar() {
        AndroidNetworking.get(Config.host + "get_angka_id_keluar.php")
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
                                string_id_plus = "1000";
                            }

                        } catch (JSONException e) {
                            string_id_plus = "1000";

                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        string_id_plus = "1000";

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

    private void tesSimpanTotalKeluar() {
        strEnk_status = AlgoritmeRSA.Enkripsi(status, strE, strN);
        strEnk_todayString = AlgoritmeRSA.Enkripsi(todayString, strE, strN);
        strEnk_total_jumlah_keluar = AlgoritmeRSA.Enkripsi(String.valueOf(int_total_barang_keluar), strE, strN);
        strEnk_nama_peminjam = AlgoritmeRSA.Enkripsi(string_nama_peminjam, strE, strN);
    }
}
