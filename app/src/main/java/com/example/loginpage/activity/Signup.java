package com.example.loginpage.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;
import com.example.loginpage.database.MyDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity {

    TextView login;
    Button createuser;
    TextInputEditText pass, conpass, user, email;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);


        login = findViewById(R.id.login);
        createuser = findViewById(R.id.createuser);
        pass = findViewById(R.id.pass);
        conpass = findViewById(R.id.conpass);
        user = findViewById(R.id.user);
        email = findViewById(R.id.email);

        MyDatabase db = new MyDatabase(Signup.this);


        // new user create karava mate
        createuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = pass.getText().toString();
                String confirmPassword = conpass.getText().toString();


                // Retrieve the email number input from the user
                String emailinput = email.getText().toString();
                //email required check
                if (emailinput.isEmpty()) {
                    email.setError("Email if required");
                    email.requestFocus();
                    return;
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()) {
                    email.setError("Please enter a valid email address");
                    // Check if the email format is valid
                    email.requestFocus();
                    return;
                }

                // password & confirmPassword same che te check karava
                if (!password.equals(confirmPassword)) {
                    conpass.setError("Passwords don't match");

                } else {
                    Boolean d = db.insertdata(user.getText().toString(), email.getText().toString(), password);

                    if (d == true) {
                        Toast.makeText(Signup.this, "User created successfully!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Signup.this, SignIn.class));
                        finish();

                    } else {
                        Toast.makeText(Signup.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // login account hot to login page ma back aavva
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Signup.this, SignIn.class));
                finish();
            }
        });

    }
}




