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


/*
*   Custom Adapter made for each listview iterm, they have been set to Listen for clicks,
*   record the position in the list and then call the activity to edit the item
*
*
*
* */


public class subAdapter extends ArrayAdapter<Subscription>{


    public subAdapter(Context context, ArrayList<Subscription> subscriptions){
        super(context, R.layout.activity_sub_adapter, subscriptions);
    }
    @Override

    // getView takes the position and parent
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inf = LayoutInflater.from(getContext());
        final View data = inf.inflate(R.layout.activity_sub_adapter, parent, false);

        // Get the name, date and price from the current subscription
        String subscriptionName = getItem(pos).getSubName();
        String subscriptionDate = getItem(pos).getSubDate();
        double subscriptionCharge = getItem(pos).getSubPrice();

        // Then show the subscription in the layout provided in sub_Adapter.xml
        // it is set to adapt to the size of the data
        final Subscription subscription = SubbookActivity.totalSubs.get(pos);
        TextView sub_name = (TextView) data.findViewById(R.id.Item);
        TextView sub_date = (TextView) data.findViewById(R.id.dateValue);
        TextView sub_charge = (TextView) data.findViewById(R.id.priceAmount);

        LinearLayout item = (LinearLayout) data.findViewById(R.id.list_main);

        //set the textviews to show the information we want summaried, name, date, price
        sub_name.setText(subscriptionName);
        sub_date.setText(String.format("\t%s", subscriptionDate));

        String sub_charge_string = String.valueOf(subscriptionCharge);
        sub_charge.setText(String.format("\t%s",sub_charge_string));

        // the onclick listener is set to call editSubscription when the item is clicked
        // and passes the position of the subscription and subscription as arguments
        item.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editSubscription(pos, subscription);
            }
        });
        return data;
    }

    // edit subscription method taking, subscription and its position in the list to allow for
    // editting and deleting
    public void editSubscription(int pos, Subscription subscription){

        Intent editThisSub = new Intent(getContext(), addSubActivity.class);

        //create extra strings to pass through to editSub class to use for existing subscriptions


        //add the current item descriptions with the intent to be called conditionally in addSubActivity class
        editThisSub.putExtra("sub_pos", pos);
        editThisSub.putExtra("sub", subscription);

        //Start the activity or editting
        ((Activity)getContext()).startActivityForResult(editThisSub, 0);

    }

}

