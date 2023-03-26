package com.example.onecampus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button signin;
    TextView signup;

    private static int SPLASH_TIME_OUT=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences(userLogin.PREFS_NAME,0);
                Boolean hasLoggedin=sharedPreferences.getBoolean("hasLoggedin",false);
                if(hasLoggedin){
                    Intent intent=new Intent(MainActivity.this,userMain.class);
                    startActivity(intent);
                }
            }
        },SPLASH_TIME_OUT);


        signin=findViewById(R.id.signIn);
        signup=findViewById(R.id.signUp);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,userLogin.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,userRegister.class);
                startActivity(intent);
            }
        });
    }
}