package com.example.nipange.landlord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nipange.R;

public class property_manager_options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_manager_options);

     Button button = findViewById(R.id.button1);
     button.setOnClickListener(new View.OnClickListener(){


         @Override
         public void onClick(View v) {

             Intent intent = new Intent(v.getContext(), new_property_launch.class);

             startActivity(intent);
         }
     });

        Button buttonnnn = findViewById(R.id.button4);
        buttonnnn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), viewProperty.class);

                startActivity(intent);
            }
        });

    }
}
