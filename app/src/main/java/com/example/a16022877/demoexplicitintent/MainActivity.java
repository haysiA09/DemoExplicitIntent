package com.example.a16022877.demoexplicitintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity  {
    TextView tvSuperman;
    TextView tvBatman;
    // These request identify who started the second activity
    int requestCodeForSupermanStats = 1;
    int requestCodeForBatmanStats = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvSuperman=(TextView)findViewById(R.id.textViewSuperman);
        tvBatman=(TextView)findViewById(R.id.textViewBatman);

        //Set Listener to handle the clicking of superman Text View
        tvSuperman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create com.example.a16022877.demoexplicitintent.Hero object of strength 100 & technical,60
                Hero superman = new Hero("Superman", 100, 60);
                Intent i = new Intent(MainActivity.this, HeroStatsActivity.class);
                //Put hero objevt in intent
                i.putExtra("hero", superman);
                // Start activity with requestCodeForSupermanStats to
                // identify it was started by clicking on Superman
                startActivityForResult(i,requestCodeForSupermanStats);

            }});

        //Set listener to handle the clciking of Batman TextView
        tvBatman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Hero batman = new Hero("Batman", 60, 90);
                Intent i = new Intent(MainActivity.this,
                        HeroStatsActivity.class);
                // Put hero object in intent
                i.putExtra("hero", batman);
                // Start activity with requestCodeForBatmanStats to
                // identify started by clicking Batman
                startActivityForResult(i, requestCodeForBatmanStats);


            }


        });
        }
    @Override
    protected void onActivityResult(int requestCode, int 				resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        //  and data contains something
        if(resultCode == RESULT_OK){
            if (data != null) {
                // Get data passed back from 2nd activity
                String like = data.getStringExtra("like");
                String statement = "";
                // If it is activity started by clicking 				//  Superman, create corresponding String
                if(requestCode == requestCodeForSupermanStats){
                    statement = "You " + like + " Superman";
                }
                // If 2nd activity started by clicking
                //  Batman, create a corresponding String
                if(requestCode == requestCodeForBatmanStats){
                    statement = "You " + like + " Batman";
                }

                Toast.makeText(MainActivity.this, statement,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}




