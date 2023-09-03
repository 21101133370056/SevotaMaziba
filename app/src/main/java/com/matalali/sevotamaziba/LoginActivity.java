package com.matalali.sevotamaziba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //variable declaration
    EditText name,password;
    Button log;
    TextView fog;
    SevothaDB sevothaDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //variable initialization

        name = (EditText) findViewById(R.id.editTextTextPersonName);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        log = (Button) findViewById(R.id.button);
        fog = (TextView) findViewById(R.id.textViewForget);

        sevothaDB = new SevothaDB(this);

        fog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nameTX = name.getText().toString();
               String passwordTX = password.getText().toString();

               if (nameTX.equals("")||passwordTX.equals("")){
                   Toast.makeText(LoginActivity.this, "Fill all records", Toast.LENGTH_SHORT).show();
               }else {
                   Boolean loginData = sevothaDB.login(nameTX,passwordTX);
                   if (loginData==true){
                       Toast.makeText(LoginActivity.this, "You have longed in and successful", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                   }else {
                       Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });
    }
}