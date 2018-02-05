package com.example.abdulazeezojetola.abdulaze_subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/*
* This is This main activity renamed Subbook;
*
* @author Abdulazeez Ojetola 1391012
*
* @see Subscription - Serializable class that holds information
*
* @see addSubActivity - Activity tied to the "new subscription" button, opened by Intent
* on
*
* @see subAdapter - Custom Adapter made for each list item to automatically adapt
*  to the information added
*
*
* */

public class SubbookActivity extends AppCompatActivity {

    // create a filename to be used for saving the arraylist
    private static final String FILENAME = "subfile.sav";

    //ListView on main page
    public  ListView subList;
    public static final String EXTRA_MESSAGE = "com.example.abdulazeezojetola.abdulaze_subbook.MESSAGE";

    //ArrayList to hold multiple Subscription classes and an adapter for each Subscription
    public static ArrayList<Subscription> totalSubs;
    public static ArrayAdapter<Subscription> adapter;

    //This is the total monthly price for all the subscriptions, calculated by method called
    // TotalAmount()
    public TextView overallPrice;

    // Shows the contents of this activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subbook);


        subList = (ListView) findViewById(R.id.subList);

        subList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> subAdapter, View view, int i, long l) {

            }
        });
    }

    // loads each subscription from the list each time page Starts

    public void onStart() {
        super.onStart();

        // Load from file
        loadFromFile();
        adapter = new subAdapter(this, totalSubs);
        subList.setAdapter(adapter);

        //Show the total price using TotalAmount() method
        TotalAmount(totalSubs);




    }



    // When Add subscription button is tapped, open addSubActivity
    // newSub() method onClick connected to the new Subscription button

    public void newSub(View view) {

        //Initialize an empty subscription class per intent, pass it with the intent
        // through to addSubAcitivity class to add it to the subscription list
        // Then start an Activity for the new subscription
        Subscription newSub = (Subscription) null;
        Intent newSubPage = new Intent(this, addSubActivity.class);
        newSubPage.putExtra("sub", newSub);
        startActivity(newSubPage);


    }
    // function method taken from Lab 3
    private void loadFromFile() {
        try {
            // Create an input stream catching input errors
            // load from filename declared at the top of the activity

            FileInputStream inputFile = openFileInput(FILENAME);
            BufferedReader input = new BufferedReader(new InputStreamReader(inputFile));

            // Gson method taken from lab 3 and Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-2-2
            // loads subs from json using gson

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Subscription>>() {
            }.getType();

            totalSubs = gson.fromJson(input, listType);
        } catch (FileNotFoundException e) {
            totalSubs = new ArrayList<Subscription>();

        }

    }
    // function taken from lab 3, saves all subscriptions in the file to be used every time addSubActivity calls it
    public static void saveInFile(Context context) {
        try {
            //declares output streams to direct to the filename
            // Called to use in AddSubActivity to be saved onClick with Add button
            FileOutputStream outputFile = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(outputFile));

            Gson gson = new Gson();
            gson.toJson(totalSubs, output);
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // method used to index the subscription list and add the subscription prices together
    // to display on the page
    public void TotalAmount(ArrayList<Subscription> totalSubs){
        //get size using .size() method and index to length-1
        double total = 0.0;
        int length = totalSubs.size();
        int len = (length-1);

        // if the list is not empty then continue with indexing and add all
        // prices.
        if (length >0) {
            for (int i = 0; i < len; i++){

                total += totalSubs.get(i).getSubPrice();
            }
            total += totalSubs.get(len).getSubPrice();
        }

        // Set the Textview at the bottom of page to the total amount calculated
        overallPrice = (TextView) findViewById(R.id.overallAmount);
        overallPrice.setText(String.format("\t%.2f",total));
    }


}

