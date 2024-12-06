package com.example.loginpage.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;
import com.example.loginpage.database.MyDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class Addcontact extends AppCompatActivity {

    Button save, cancel;
    TextInputEditText name, num, em, ad;

    // new contact data add karava

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        name = findViewById(R.id.name);
        num = findViewById(R.id.num);
        em = findViewById(R.id.em);
        ad = findViewById(R.id.ad);


        int userid = getIntent().getIntExtra("userid", 20);

        //  new contact data add karava
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabase db = new MyDatabase(Addcontact.this);

                // Retrieve the phone number input from the user
                String email = em.getText().toString();
                //email required check
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    em.setError("Please enter a valid email address");
                    return;
                }

                //number  required check
                String number = num.getText().toString();
                if (number.isEmpty()) {
                    num.setError("number if required");
                    return;
                }
                else if (number.length() != 10) // number 10 digit ma j aava aena mate
                {
                    num.setError("Phone number cannot exceed 10 digits");
                    return;
                }
                //  contact data input  karava
                Boolean b = db.addcontact(userid, name.getText().toString()
                        , num.getText().toString(), em.getText().toString()
                        , ad.getText().toString());

                // all input data OK che ae chek karava
                if (b)
                {
                    startActivity(new Intent(Addcontact.this, HomePage.class)
                            .putExtra("userid", userid));
                    finish();

                }
            }
        });


        // back main page ma aavaa
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Addcontact.this, HomePage.class).putExtra("userid", userid));
                finish();

            }
        });


    }
}


