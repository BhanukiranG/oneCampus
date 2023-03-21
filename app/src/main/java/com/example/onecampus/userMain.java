package com.example.onecampus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userMain extends AppCompatActivity {
    Button upload;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        upload=findViewById(R.id.upload);
        recyclerView=findViewById(R.id.recyclerView);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(userMain.this, eventsUpload.class);
                startActivity(intent);
            }
        });

}}