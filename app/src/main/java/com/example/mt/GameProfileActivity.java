package com.example.mt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameProfileActivity extends AppCompatActivity {

    TextView tvTitle,tvRate,tvPrice,tvDescription;

    String sTitle, sRate, sPrice, sDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_game_profile);

        sTitle = getIntent().getStringExtra("name");
        sRate = getIntent().getStringExtra("rating");
        sPrice = getIntent().getStringExtra("price");
        sDescription = getIntent().getStringExtra("des");


        tvTitle = findViewById(R.id.tv_Profile_Title);
        tvTitle.setText(sTitle);

        tvRate = findViewById(R.id.tv_Profile_rate);
        tvRate.setText(sRate);

        tvPrice = findViewById(R.id.tv_Profile_Price);
        tvPrice.setText(sPrice);

        tvDescription = findViewById(R.id.tv_Profile_Description);
        tvDescription.setText(sDescription);

    }


}
