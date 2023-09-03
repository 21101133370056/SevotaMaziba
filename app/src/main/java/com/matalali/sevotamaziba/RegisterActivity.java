package com.matalali.sevotamaziba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username,address,student_phone,parent_phone,email,password,cpassword;
    Button regi;

    SevothaDB sevothaDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.editTextTextPersonName);
        address = (EditText) findViewById(R.id.addressID);
        student_phone = (EditText) findViewById(R.id.phoneNumber);
        parent_phone = (EditText) findViewById(R.id.parentPhoneNumber);
        email = (EditText) findViewById(R.id.emailID);
        password = (EditText) findViewById(R.id.password1);
        cpassword = (EditText) findViewById(R.id.cpassword);

        sevothaDB = new SevothaDB(this);

        regi = (Button) findViewById(R.id.buttonRegister);

        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameTX = username.getText().toString();
                String addressTX = address.getText().toString();
                String student_phoneTX = student_phone.getText().toString();
                String parent_phoneTX = parent_phone.getText().toString();
                String emailTX = email.getText().toString();
                String passwordTX = password.getText().toString();
                String cpasswordTX = cpassword.getText().toString();

                if (usernameTX.equals("")||addressTX.equals("")||student_phoneTX.equals("")||parent_phoneTX.equals("")||emailTX.equals("")||passwordTX.equals("")||cpasswordTX.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }else {
                    if (passwordTX.equals(cpasswordTX)){
                        Boolean checkDataInsert = sevothaDB.AddUser(usernameTX,addressTX,student_phoneTX,parent_phoneTX,emailTX,passwordTX);
                        if (checkDataInsert==true){
                            Toast.makeText(RegisterActivity.this, "Records inserted successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Records does not inserted", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}