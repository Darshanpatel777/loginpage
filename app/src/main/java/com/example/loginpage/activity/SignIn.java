package com.example.loginpage.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;
import com.example.loginpage.database.MyDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class SignIn extends AppCompatActivity {

    TextView create;
    Button loginuser;
    TextInputEditText username;
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        create = findViewById(R.id.create);
        loginuser = findViewById(R.id.loginuser);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        MyDatabase db = new MyDatabase(SignIn.this);

        loginuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // user login id - password nakhva
                Cursor data = db.userlogin(username.getText().toString(), password.getText().toString());

                while (data.moveToNext()) {
                    Log.d("====d====", "onClick: id ==> " + data.getInt(0));
                    Log.d("====d====", "onClick: name ==> " + data.getString(1));
                    Log.d("====d====", "onClick: email ==> " + data.getString(2));


                    // last login chalu rakhava mate
                    SpaceScreen.edit.putBoolean("status", true);
                    SpaceScreen.edit.putInt("uid", data.getInt(0));
                    SpaceScreen.edit.apply();

                    // next main page ma java amate
                    startActivity(new Intent(SignIn.this, HomePage.class).
                            putExtra("userid", data.getInt(0))
                            .putExtra("name", data.getString(1)));


                }
            }
        });

        // new login create karava mate
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignIn.this, Signup.class));
                finish();
            }
        });


    }
}