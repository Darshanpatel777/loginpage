package com.example.loginpage.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;
import com.example.loginpage.database.MyDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class Update extends AppCompatActivity {


    // contact data edit - delete karava

    Button save, cancel, delete;
    TextInputEditText oldnum, oldname;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);


        oldname = findViewById(R.id.newname);
        oldnum = findViewById(R.id.newnum);
        save = findViewById(R.id.sav);
        cancel = findViewById(R.id.cancel);
        delete = findViewById(R.id.delete);


        //data edit karva
        String updatename = getIntent().getStringExtra("name");
        String updatenum = getIntent().getStringExtra("num");
        int cid = getIntent().getIntExtra("cid", 90);


        oldname.setText(updatename);
        oldnum.setText(updatenum);

        int userid = getIntent().getIntExtra("userid", 50);

        // edit data save karva
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabase db = new MyDatabase(Update.this);
                db.editdata(oldname.getText().toString(), oldnum.getText().toString(), cid);


                startActivity(new Intent(Update.this, HomePage.class)
                        .putExtra("userid", userid));
                finish();


            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Update.this, HomePage.class)
                        .putExtra("userid", userid));
                finish();
            }
        });

        // data delete karava
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // dialog box open karava
                Dialog dialog = new Dialog(Update.this);
                dialog.setContentView(R.layout.dialogview_delete);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                TextView tex1 = dialog.findViewById(R.id.tex1);
                Button yes1 = dialog.findViewById(R.id.yes1);
                Button no1 = dialog.findViewById(R.id.no1);

                tex1.getText();

                yes1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MyDatabase dp = new MyDatabase(Update.this);
                        dp.deletedata(cid);

                        startActivity(new Intent(Update.this, HomePage.class)
                                .putExtra("userid", userid));
                        finish();
                    }
                });

                no1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
            }

        });

    }
}