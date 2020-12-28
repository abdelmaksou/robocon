package com.abdelmaksoud.robocon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class choose extends AppCompatActivity {
    public static String gender ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ImageView batman = (ImageView) findViewById(R.id.batman);
        batman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "boy";
                Intent a = new Intent(choose.this,Main.class);
                startActivity(a);
            }
        });

        ImageView flower = (ImageView) findViewById(R.id.flower);
        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "girl";
                Intent b = new Intent(choose.this,Main_g.class);
                startActivity(b);
            }
        });
    }
}
