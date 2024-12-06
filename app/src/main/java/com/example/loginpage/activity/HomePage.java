package com.example.loginpage.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.loginpage.R;
import com.example.loginpage.database.MyDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;

public class HomePage extends AppCompatActivity {

    SearchView search;
    ListView list;
    FloatingActionButton add, pop;


    ArrayList<ModelClass> datalist = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_page);


        add = findViewById(R.id.add);
        list = findViewById(R.id.list);
        pop = findViewById(R.id.pop);
        search = findViewById(R.id.search);

        ArrayList<ModelClass> searchList = new ArrayList<>();
        // list ma data search karva

        int userid = getIntent().getIntExtra("userid", 10);




        MyDatabase db = new MyDatabase(this);
        Cursor cr = db.selectcon(userid);

        while (cr.moveToNext()) {
            ModelClass d = new ModelClass();
            d.setName(cr.getString(2));
            d.setNum(cr.getString(3));
            d.setId(cr.getInt(0));
            datalist.add(d);
        }


        // contact list name alphabet ma aava mate
        ArrayList<String> namelist = new ArrayList<>();
        for (int i = 0; i < datalist.size(); i++) {
            namelist.add(datalist.get(i).getName());
        }
        namelist.sort(Comparator.naturalOrder());

        ArrayList<ModelClass> temp = new ArrayList<>();
        for (int i = 0; i < datalist.size(); i++) {
            for (int j = 0; j < datalist.size(); j++) {
                if (namelist.get(i) == datalist.get(j).getName()) {
                    temp.add(datalist.get(j));
                    break;
                }
            }
        }
        datalist = temp;

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override

            // submit ni click  thay taire data show karva
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            // text type thay taire data show karva
            @Override
            public boolean onQueryTextChange(String newText) {

                Log.d("=======", "onQueryTextChange:" + newText);
                searchList.clear();

                // datalist ma contact na data search karva mate
                for (int i = 0; i < datalist.size(); i++)
                {
//                    Log.d("=======", "onQueryTextChange: enter in loop");
                    if (datalist.get(i).getName().contains(newText))
                    {
                        searchList.add(datalist.get(i));
                    }
                }
                //search karavel data contact list ma show krava
                list.setAdapter(new MyAdapter(HomePage.this, userid, searchList));
                return true;
            }
        });

        // contact list mate
        list.setAdapter(new MyAdapter(this, userid, datalist));

        // new contact add karava
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(HomePage.this, Addcontact.class).putExtra("userid", userid));
                finish();


            }
        });




        // popup menu open karva
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu pmenu = new PopupMenu(HomePage.this, pop);

                pmenu.inflate(R.menu.mymenu);
                pmenu.show();

                pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getItemId() == R.id.logout) {
                            // exit karva mate no Dialog box open thase
                            Dialog dialog = new Dialog(HomePage.this);
                            dialog.setContentView(R.layout.dialogview);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.show();

                            TextView tex = dialog.findViewById(R.id.tex);
                            Button yes = dialog.findViewById(R.id.yes);
                            Button no = dialog.findViewById(R.id.no);

                            tex.getText();

                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    SpaceScreen.edit.putBoolean("status", false);
                                    SpaceScreen.edit.apply();

                                    startActivity(new Intent(HomePage.this, SignIn.class));
                                    finish();
                                }
                            });

                            no.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    dialog.dismiss();
                                }
                            });
                        } else if (item.getItemId() == R.id.setting) {
                            Toast.makeText(HomePage.this, "setting", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
            }
        });


    }
}