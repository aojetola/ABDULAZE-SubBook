package com.example.abdulazeezojetola.abdulaze_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addSubActivity extends AppCompatActivity {
    public EditText subName;
    public EditText subDate;
    public EditText subComment;
    public EditText subPrice;
    public static String oldName;
    public static String oldDate;
    public static String oldComment;
    public static double oldPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent editSubIntent = getIntent();
        Subscription subscription = (Subscription) editSubIntent.getSerializableExtra("sub");
        int pos = editSubIntent.getIntExtra("sub_pos", 0);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);

        Button addButton = (Button) findViewById(R.id.addButton);
        Button removeButton = (Button) findViewById(R.id.removeButton);


        if (subscription != null) {
            oldName = subscription.getSubName();
            oldDate = subscription.getSubDate();
            oldPrice = subscription.getSubPrice();
            oldComment = subscription.getSubComment();

        }
        Set();
    }


    public void Set() {

        subName = (EditText) findViewById(R.id.subName);
        subName.setText(oldName);


        subPrice = (EditText) findViewById(R.id.subPrice);
        subPrice.setText(Double.toString(oldPrice));


        subDate = (EditText) findViewById(R.id.subDate);
        subDate.setText(oldDate);


        subComment = (EditText) findViewById(R.id.subComment);
        subComment.setText(oldComment);

    }

    public void Add(View view) {
        EditText Name = (EditText) findViewById(R.id.subName);
        String sName = subName.getText().toString();

        EditText Date = (EditText) findViewById(R.id.subDate);
        String sDate = subDate.getText().toString();

        EditText Comment = (EditText) findViewById(R.id.subComment);
        String sComment = subComment.getText().toString();

        EditText Price = (EditText) findViewById(R.id.subPrice);
        double sPrice = Double.valueOf(subPrice.getText().toString());
        if (Name.length() == 0 || sDate.length() == 0) {
            finish();
        } else {
            Subscription totalSubs = new Subscription(sName, sDate, sComment, sPrice);
            totalSubs.setSub(sName, sPrice, sDate, sComment);
            SubbookActivity.totalSubs.add(totalSubs);
            SubbookActivity.adapter.notifyDataSetChanged();
            SubbookActivity.saveInFile(addSubActivity.this);
            setContentView(R.layout.activity_subbook);
            finish();

        }

    }

    public void change(View view) {
        Intent editSubIntent = getIntent();
        int pos = editSubIntent.getIntExtra("sub_pos", 0);
        EditText Name = (EditText) findViewById(R.id.subName);
        String sName = subName.getText().toString();

        EditText Date = (EditText) findViewById(R.id.subDate);
        String sDate = subDate.getText().toString();

        EditText Comment = (EditText) findViewById(R.id.subComment);
        String sComment = subComment.getText().toString();

        EditText Price = (EditText) findViewById(R.id.subPrice);
        double sPrice = Double.valueOf(subPrice.getText().toString());

        Subscription totalSubs = new Subscription(sName, sDate, sComment, sPrice);
        totalSubs.setSub(sName, sPrice, sDate, sComment);
        SubbookActivity.totalSubs.remove(pos);
        SubbookActivity.totalSubs.add(totalSubs);
        SubbookActivity.adapter.notifyDataSetChanged();;
        SubbookActivity.saveInFile(addSubActivity.this);
        finish();


    }
    public void Remove(View view){
        Intent removeSub = getIntent();
        int pos = removeSub.getIntExtra("sub_pos",0);
        Subscription subscription = (Subscription) removeSub.getSerializableExtra("sub");
        if (subscription == null){
            finish();
        }else {
            SubbookActivity.totalSubs.remove(pos);
            SubbookActivity.adapter.notifyDataSetChanged();
            SubbookActivity.saveInFile(addSubActivity.this);
            finish();
        }
    }
}