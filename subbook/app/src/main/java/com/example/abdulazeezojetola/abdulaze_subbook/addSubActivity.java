package com.example.abdulazeezojetola.abdulaze_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
* This class is called from 2 different activities (Subbook when newSub is called) or subAdapter
* (from the editSub method) along with the putExtra methods to pass on
* the subscription and the position of the subscription in the ArrayList
*
*
* */

public class addSubActivity extends AppCompatActivity {

    // Declare the EditText variables and the attributes to be put into the Subscription
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

        // Collect the intent from either subAdapter(taken from editSub) or from newSub()
        // method taken from Subbook activity (main activity);
        //
        Intent SubIntent = getIntent();

        // this subscription could either be Null or an existing subscription for the list
        Subscription subscription = (Subscription) SubIntent.getSerializableExtra("sub");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);

        // If the subscription is not null, then set all the edit text values
        // to the current subscription attributes - name, price, comment etc

        if (subscription != null) {
            oldName = subscription.getSubName();
            oldDate = subscription.getSubDate();
            oldPrice = subscription.getSubPrice();
            oldComment = subscription.getSubComment();
            Set();

            // If the subscription is null, (empty), then leave the hints in the Edit Text
        }   else {

            subName = (EditText) findViewById(R.id.subName);
            subPrice = (EditText) findViewById(R.id.subPrice);
            subDate = (EditText) findViewById(R.id.subDate);
            subComment = (EditText) findViewById(R.id.subComment);
        }
    }

    // Method Sets the Edittext widgets to the matching subscription attributes
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

    // onClick method on the AddSub Activity first sets the text to the corresponding data
    // then sets the subscription using getters and setters from the Subscription class
    // Also handles instances of inadequate information.

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

            //Returns to parents activity (main)
            finish();

        }

    }
    // attributed to onClick button (Edit)
    // gets Intent from subAdapter which handles onItemClicks of the listview then opens
    // the list item on Sub Activity. First deletes the subscription at its pos
    // creates a new one then re-saves it in the list.
    // then returns to main
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
    // Attributed to remove button in add_sub.xml
    // Deletes the current selected subscrption which is passed through an Intent only from Edit Page
    // saves an empty item then returns to main.
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