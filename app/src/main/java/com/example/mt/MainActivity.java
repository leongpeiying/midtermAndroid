package com.example.mt;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mt.dataModel.Accounts;
import com.example.mt.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private EditText etUserName, etPassword;
    private Button btn_Login;
    private TextView tvForgot;
    private CheckBox cbShowPassword;

    private ArrayList<Accounts> arrayPassword = new ArrayList<>();
    private AdapterActivity2 adapter;
    Accounts accounts = new Accounts();
    String username = accounts.getUsername();
    String password = accounts.getPassword();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        findview();
        new GetDataTask().execute();
        Login();
        adapter = new AdapterActivity2(arrayPassword, this);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GamesActivity.class);
                i.putExtra("username", accounts.getUsername());
                i.putExtra("password", accounts.getPassword());
                startActivity(i);
            }
        });
    }

    private void findview(){
        etUserName = findViewById(R.id.UserName);
        etPassword = findViewById(R.id.Password);
        btn_Login = findViewById(R.id.btn_Login);
        tvForgot = findViewById(R.id.ForgotPasswrd);
        cbShowPassword = findViewById(R.id.ShowPassword);
    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            JSONObject jsonObject = JSONParser.getPasswordFromWeb();

            try {

                if (jsonObject != null) {
                    if(jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray("accounts");

                        int lenArray = array.length();
                        if(lenArray > 0) {
                            for(int jIndex = 0; jIndex < lenArray; jIndex++) {
                                Accounts accounts = new Accounts();
                                JSONObject account = array.getJSONObject(jIndex);
                                String username = account.getString("username");
                                String password = account.getString("password");

                                accounts.setUsername(username);
                                accounts.setPassword(password);

                                arrayPassword.add(accounts);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(arrayPassword.size() > 0) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void Login(){
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputU = etUserName.getText().toString();
                String inputP = SHA(etPassword.getText().toString());
                if (inputU.equals(username) && inputP.equals(password)){
                    Intent g = new Intent(MainActivity.this,GamesActivity.class);
                    startActivity(g);
                    Toast.makeText(MainActivity.this,"Enter successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"invalid username or password",Toast.LENGTH_SHORT).show();
                }

            }
        });

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ForgotActivity.class);
                startActivity(i);
            }
        });

    }

    private String SHA(String x){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(x.getBytes());

            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest){
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
//http://www.pratikbutani.com/2016/01/android-json-parsing-using-okhttp-example-with-custom-view-imageview-textview/