package com.example.abdulazeezojetola.abdulaze_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abdulazeezojetola on 2018-01-31.
 */

public class subAdapter extends ArrayAdapter<Subscription>{


    public subAdapter(Context context, ArrayList<Subscription> subscriptions){
        super(context, R.layout.activity_sub_adapter, subscriptions);
    }
    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inf = LayoutInflater.from(getContext());
        final View data = inf.inflate(R.layout.activity_sub_adapter, parent, false);

        String subscriptionName = getItem(pos).getSubName();
        String subscriptionDate = getItem(pos).getSubDate();
        double subscriptionCharge = getItem(pos).getSubPrice();


        final Subscription subscription = SubbookActivity.totalSubs.get(pos);
        TextView sub_name = (TextView) data.findViewById(R.id.Item);
        TextView sub_date = (TextView) data.findViewById(R.id.dateValue);
        TextView sub_charge = (TextView) data.findViewById(R.id.priceAmount);

        LinearLayout item = (LinearLayout) data.findViewById(R.id.list_main);


        sub_name.setText(subscriptionName);
        sub_date.setText(String.format("\t%s", subscriptionDate));

        String sub_charge_string = String.valueOf(subscriptionCharge);
        sub_charge.setText(String.format("\t%s",sub_charge_string));

        item.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editSubscription(pos, subscription);
            }
        });
        return data;
    }

    public void editSubscription(int pos, Subscription subscription){

        Intent editThisSub = new Intent(getContext(), addSubActivity.class);

        //create extra strings to pass through to editSub class to use for existing subscriptions


        //add the current item descriptions with the intent to be called conditionally in addSubActivity class
        editThisSub.putExtra("sub_pos", pos);
        editThisSub.putExtra("sub", subscription);

        ((Activity)getContext()).startActivityForResult(editThisSub, 0);

    }

}

