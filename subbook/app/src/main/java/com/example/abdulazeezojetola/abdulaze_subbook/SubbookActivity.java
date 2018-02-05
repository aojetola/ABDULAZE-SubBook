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

public class SubbookActivity extends AppCompatActivity {

    private static final String FILENAME = "subfile.sav";
    public  ListView subList;
    public static final String EXTRA_MESSAGE = "com.example.abdulazeezojetola.abdulaze_subbook.MESSAGE";
    public static ArrayList<Subscription> totalSubs;
    public static ArrayAdapter<Subscription> adapter;
    public TextView overallPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subbook);


        subList = (ListView) findViewById(R.id.subList);

        //TotalAmount();
        subList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> subAdapter, View view, int i, long l) {

            }
        });
    }

    // loads from file
    public void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new subAdapter(this, totalSubs);
        subList.setAdapter(adapter);
        int len = totalSubs.size();
        Log.v("list length", String.valueOf(len));
        TotalAmount(totalSubs);




    }



    // When Add subscription button is tapped, open addSubActivity

    public void newSub(View view) {
        Intent newSubPage = new Intent(this, addSubActivity.class);
        startActivity(newSubPage);


    }
    // function method taken from Lab 3
    private void loadFromFile() {
        try {
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

    public void TotalAmount(ArrayList<Subscription> totalSubs){

        double total = 0.0;
        int length = totalSubs.size();
        int len = (length-1);
        Log.v("current Price", String.valueOf(totalSubs.get(len).getSubPrice()));

        if (length >0) {
            for (int i = 0; i < length; i++){
                total += totalSubs.get(i).getSubPrice();

            }
            Log.v("new total", String.valueOf(total));
        }
        overallPrice = (TextView) findViewById(R.id.overallAmount);
        overallPrice.setText(String.format("\t%.2f",total));
    }


}

