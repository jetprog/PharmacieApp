package com.applicaion.pharmacie.pharmaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


 // Activity login  user can use username/password

public class LoginActivity extends AppCompatActivity  {


    // UI references.
    private EditText userName;
    private EditText password;
    private  Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        userName = (EditText) findViewById(R.id.edtUserName);


        password = (EditText) findViewById(R.id.edtPassword);


        login = (Button) findViewById(R.id.btLogin);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });

    }


}

