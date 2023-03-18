package com.example.onecampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class register extends AppCompatActivity {
    ImageButton userRegister,adminRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userRegister=findViewById(R.id.userRegister);
        adminRegister=findViewById(R.id.adminRegister);
        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(register.this,userRegister.class);
                startActivity(intent);
            }
        });
        adminRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(register.this,adminRegister.class);
                startActivity(intent);
            }
        });
    }
}