package com.example.nipange.landlord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nipange.R;

public class details_about_property extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_about_property);












        ImageButton Valuation;

        Valuation = (ImageButton)

                findViewById(R.id.Valuation);

        Valuation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), valuation.class);
                startActivity(intent);

            }
        });




        ImageButton MDA;

        MDA = (ImageButton)

                findViewById(R.id.MortgageDetailsArrow);

        MDA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), mortgageDetails.class);
                startActivity(intent);

            }
        });








        //SAVE BUTTON WILL REDIRECT TO PROPERTY MANAGER OPTIONS PAGE / CLASS.


        TextView save;

        save = (TextView)

                findViewById(R.id.SAVE);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), property_manager_options.class);
                startActivity(intent);

            }
        });

    }
}