package com.example.onecampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userMain extends AppCompatActivity {
    FirebaseAuth auth;
    Button b;
    TextView textView;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        auth=FirebaseAuth.getInstance();
        textView=findViewById(R.id.textView4);
        user=auth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(getApplicationContext(),userLogin.class);
            startActivity(intent);
            finish();
        }else{
            textView.setText(user.getEmail());
        }

}}