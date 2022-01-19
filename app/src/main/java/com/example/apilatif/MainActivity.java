package com.example.apilatif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputText1;
    EditText inputText2;
    TextView tampilText;

    Button btnHitung;
    Button btnbahasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        inputText1 = (EditText) findViewById(R.id.editText1);
        inputText2 = (EditText) findViewById(R.id.editText2);
        tampilText = (TextView) findViewById(R.id.hasil1);
        btnHitung = (Button) findViewById(R.id.btnHitung);
        btnbahasa = (Button) findViewById(R.id.bahasa);

        btnHitung.setOnClickListener(this);
        btnbahasa.setOnClickListener(this);

        btnbahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListPemrograman.class);
                startActivity(intent);
            }
        });


        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((inputText1.getText().length()>0) && (inputText2.getText().length()>0))
                {
                    double input1 = Double.parseDouble(inputText1.getText().toString());
                    double input2 = Double.parseDouble(inputText2.getText().toString());
                    double result = input1 % input2 + 1;
                    tampilText.setText(Double.toString(result));
                }
                else {
                    Toast toast = Toast.makeText(MainActivity.this, "Mohon masukkan Angka pertama & Kedua", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnHitung:
                tampilText.setText(btnHitung.getText().toString());
                break;

        }
    }
}