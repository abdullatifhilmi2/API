package com.example.apilatif;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class IsiListJS extends AppCompatActivity {
    TextView btndeskripsi, btnlogo, btnhello, btnlink;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_list);
        btndeskripsi = findViewById(R.id.btndeskripsi);
        btndeskripsi.setMovementMethod(new ScrollingMovementMethod());
        btnlogo = findViewById(R.id.btnlogo);
        btnhello = findViewById(R.id.btnhello);
        btnlink = findViewById(R.id.btnlink);

        tampil();
    }
    private void tampil() {
        loading = ProgressDialog.show(IsiListJS.this, "memuat data","harap tunggu");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://ewinsutriandi.github.io/mockapi/bahasa_pemrograman.json";
        JSONObject jsonObject = new JSONObject();
        final String requestBody = jsonObject.toString();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo = new JSONObject(response.toString());
                    String deskripsi = jo.getJSONObject("Javascript").getString("description");
                    String logo = jo.getJSONObject("Javascript").getString("logo");
                    String hello = jo.getJSONObject("Javascript").getString("hello_world");
                    String link = jo.getJSONObject("Javascript").getString("read_more");

                    btndeskripsi.setText(deskripsi);
                    btnlogo.setText(logo);
                    btnhello.setText(hello);
                    btnlink.setText(link);
                    loading.cancel();
                    Toast.makeText(IsiListJS.this, "Berhasil", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.cancel();
                Toast.makeText(IsiListJS.this, "gagal ambil data" + error, Toast.LENGTH_SHORT).show();
            }
        }
        );
        queue.add(stringRequest);
    }
}