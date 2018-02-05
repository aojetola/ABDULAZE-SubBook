package com.example.abdulazeezojetola.abdulaze_subbook;

import android.content.Intent;

import com.example.abdulazeezojetola.abdulaze_subbook.addSubActivity;

/**
 * Created by abdulazeezojetola on 2018-02-04.
 */



public abstract class editSub extends addSubActivity {

    String Name = getIntent().getStringExtra("sub_name");
    String Date = getIntent().getStringExtra("sub_date");
    String Price = getIntent().getStringExtra("sub_charge");
    editSub(String Name, String Date, String Price){
        super();
        this.subName.setText(Name);
        this.subDate.setText(Date);
        this.subPrice.setText(Price);





    }
}
