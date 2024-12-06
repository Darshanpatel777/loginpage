package com.example.loginpage.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpage.R;


public class SpaceScreen extends AppCompatActivity {

    // app open thay taire koi fixx app design logo aave aena mate

    static SharedPreferences sp;
    static SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_screen);

        sp = getSharedPreferences("myshare", MODE_PRIVATE);
        edit = sp.edit();

        // user 1st time id -password create kari login krava mate
        Boolean userstatus = sp.getBoolean("status", false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // user login 6e ke nai ae chek karva
                if (userstatus) {
                    startActivity(new Intent(SpaceScreen.this, HomePage.class).
                            putExtra("userid",sp.getInt("uid",0)));
                    finish();
                } else {
                    startActivity(new Intent(SpaceScreen.this, SignIn.class));
                    finish();
                }
            }
        }
        // ketla milli secound delay karvu che aena mate
        , 2000);


    }
}