package com.example.nipange.landlord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.nipange.R;

public class dashboard_land extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_land);


        ImageView image = findViewById(R.id.imageView21);
        image.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), property_manager_options.class);

                startActivity(intent);

            }
        });
    }
}
