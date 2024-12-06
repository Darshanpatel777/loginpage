package com.example.loginpage.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.loginpage.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {


    ArrayList<ModelClass> datalist = new ArrayList<>();
    Context context;
    int uid;

    MyAdapter(Context context, int uid, ArrayList<ModelClass> datalist) {
        this.context = context;
        this.uid = uid;
        this.datalist = datalist;
    }


    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View vv = LayoutInflater.from(context).inflate(R.layout.myview, parent, false);

        TextView name = vv.findViewById(R.id.sname), num = vv.findViewById(R.id.snum);

        name.setText(datalist.get(position).getName());
        num.setText(datalist.get(position).getNum());


        // date edit-delete karava mate & complete thay gaya  bad main page ma aava mate
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                context.startActivity(new Intent(context, Update.class).putExtra("name", name.getText()).putExtra("num", num.getText()).putExtra("cid", datalist.get(position).getId()).putExtra("userid", uid));


            }
        });


        return vv;
    }
}
