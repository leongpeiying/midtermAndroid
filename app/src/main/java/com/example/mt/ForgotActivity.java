package com.example.mt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity {

    EditText email;
    TextView back, submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        findID();
        forgot();
    }

    private void findID(){
        email=findViewById(R.id.email);
        back=findViewById(R.id.back);
        submit=findViewById(R.id.submit);
    }

    private void forgot(){

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String EMAIL = email.getText().toString();

                if(TextUtils.isEmpty(EMAIL)){
                    Toast.makeText(ForgotActivity.this,"Enter the E-mail",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ForgotActivity.this,"Check your MailBox",Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgotActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

}
