package com.simpleinventory.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.simpleinventory.R;
import com.simpleinventory.helper.Config;

import org.json.JSONObject;

import java.math.BigInteger;

public class AlgoritmeRSA extends AppCompatActivity {

    private static int k;
    private static int setDfix;
    private static int integerDekAscii;
    private static String strAsciEnc;
    private static String strDekAwal;
    private static String strDekAscii;
    private static String strDekAngka;
    private static String strDekAsli;
    private static String strDekAsliFix;
    private static char charDekAscii;


    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    String strP, strQ, strE, nilai_p, nilai_q, nilai_n, nilai_m, nilai_e, nilai_d;
    Integer integerN, integerPminus, integerQminus, integerM, integerE;
    int intE,intD, intM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algoritme_rsa);



        sharedPreferences = getSharedPreferences("mainsession", Context.MODE_PRIVATE);

        nilai_p = sharedPreferences.getString("string_pRandom", "");
        nilai_q = sharedPreferences.getString("string_qRandom", "");
        nilai_n = sharedPreferences.getString("stringN","");
        nilai_m = sharedPreferences.getString("stringM","");
        nilai_e = sharedPreferences.getString("stringE","");
        nilai_d = sharedPreferences.getString("stringD","");

        String status_kunci = sharedPreferences.getString("string_pRandom","");
        if(!status_kunci.isEmpty()) {

            simpanKunci();

            startActivity(new Intent(AlgoritmeRSA.this, LoginActivity.class));
            finish();
        }

        else {
            while (true) {
                int pRandom = (int) (Math.random() * (30 - 5) + 5);
                if (isPrime(pRandom)) {
                    strP = String.valueOf(pRandom);
                    editor = sharedPreferences.edit();
                    editor.putString("string_pRandom", strP);
                    editor.apply();
                    break;
                }
            }

            while(true){
                int qRandom = (int) (Math.random() * (30 - 5) + 5);
                if(isPrime(qRandom)){
                    strQ = String.valueOf(qRandom);
                    editor = sharedPreferences.edit();
                    editor.putString("string_qRandom", strQ);
                    editor.apply();
                    break;
                }
            }



            integerN = Integer.parseInt(strP) * Integer.parseInt(strQ);
            integerPminus = Integer.parseInt(strP) - 1;
            integerQminus = Integer.parseInt(strQ) - 1;
            integerM = integerPminus * integerQminus;
            intM = (int) integerM;

            editor = sharedPreferences.edit();
            editor.putString("stringN", String.valueOf(integerN));
            editor.putString("stringM", String.valueOf(integerM));
            editor.apply();

            while(true){
                int eRandom = (int) (Math.random() * (integerM - 2) + 2);
                if(isPrime(eRandom)){
                    intE = eRandom;
                    break;
                }
            }

            while (true) {
                if(hitungGCD(intE, intM)) {
                    strE = String.valueOf(intE);
                    editor = sharedPreferences.edit();
                    editor.putString("stringE", strE);
                    editor.apply();
                    break;
                }
            }

            while (true) {
                if (cariD(k, intE, intM)) {
                    int setD = k * intM + 1;
                    setDfix = setD / intE;
                    intD = setDfix;
                    String strD = String.valueOf(intD);
                    editor = sharedPreferences.edit();
                    editor.putString("stringD", strD);
                    editor.apply();
                    break;
                }
                else {
                    k=k+1;
                }

            }
            startActivity(new Intent(AlgoritmeRSA.this, LoginActivity.class));
        }
        //startActivity(new Intent(RSAActivity.this, MainActivity.class));

    }

    private static boolean isPrime(int n) {
        int i;
        for(i=2;i<=Math.sqrt(n);i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    private static boolean hitungGCD(int e, int m) {
        BigInteger b1 = BigInteger.valueOf(e);
        BigInteger b2 = BigInteger.valueOf(m);
        BigInteger gcd = b1.gcd(b2);
        if(gcd.intValue() == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean cariD(int k, int e, int m) {
        int intcariD = k * m + 1;
        Double doubleD = Double.parseDouble(String.valueOf(intcariD)) / Double.parseDouble(String.valueOf(e));
        if(doubleD % 1 == 0){
            return true;
        }
        return false;
    }

    public static String Enkripsi (String strEnkripsi, String strE, String strN) {
        Integer integerEnkE = Integer.parseInt(strE);
        BigInteger bigIntegerEnkN = BigInteger.valueOf(Integer.parseInt(strN));
        StringBuilder sbEnk = new StringBuilder();
        char[] charsEnk = strEnkripsi.toCharArray();
        for (char cEnk : charsEnk) {
            BigInteger bigIntegerEnk = BigInteger.valueOf((int) cEnk);
            BigInteger bigIntegerEnkPow = bigIntegerEnk.pow(integerEnkE);
            BigInteger bigIntegerEnkMod = bigIntegerEnkPow.mod(bigIntegerEnkN);

            sbEnk.append(bigIntegerEnkMod + " ");
            strAsciEnc = sbEnk.toString();
        }
        return strAsciEnc;
    }

    public static String Dekripsi (String strDekripsi, String strDekD, String strDekN) {
        int integerDecD = Integer.parseInt(strDekD);
        BigInteger bigIntegerDekN = BigInteger.valueOf(Integer.parseInt(strDekN));

        StringBuilder sbDekFix = new StringBuilder();
        StringBuilder sbDek = new StringBuilder();
        char[] charsDek = strDekripsi.toCharArray();
        for(char cDek : charsDek) {

            strDekAwal = String.valueOf(cDek);

            if(strDekAwal.equals(" ")) {
                //rumus dekrip
                BigInteger bigIntegerDec = BigInteger.valueOf(Integer.parseInt(strDekAngka));
                BigInteger bigIntegerDecPow = bigIntegerDec.pow(integerDecD);
                BigInteger bigIntegerDecMod = bigIntegerDecPow.mod(bigIntegerDekN);
                strDekAscii = bigIntegerDecMod.toString();
                integerDekAscii = Integer.parseInt(strDekAscii);
                charDekAscii = (char) integerDekAscii;
                strDekAsli = Character.toString((char) charDekAscii);
                sbDekFix.append(strDekAsli);
                strDekAsliFix = sbDekFix.toString();
                sbDek.setLength(0);
                //return strAsliDecFix;
            }
            else {
                sbDek.append(cDek);
                strDekAngka = sbDek.toString();
            }
            //return strAsliDecFix;
        }
        String dek = strDekAsliFix;
        return strDekAsliFix;
    }


    private void simpanKunci(){
        AndroidNetworking.post( Config.host + "simpan_kunci.php")
                .addBodyParameter("nilai_p", nilai_p)
                .addBodyParameter("nilai_q", nilai_q)
                .addBodyParameter("nilai_n", nilai_n)
                .addBodyParameter("nilai_m", nilai_m)
                .addBodyParameter("nilai_e", nilai_e)
                .addBodyParameter("nilai_d", nilai_d)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString() );

                        if (response.optString("response").equals("simpan_kunci success")){
                            //startActivity(new Intent(AddBarangBaruActivity.this, MainActivity.class));
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
