package com.example.onecampus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class eventDetails extends AppCompatActivity {
        TextView singleLink,singleDiscription,singleCollege,singleEventName;
        ImageView singleImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        singleLink=findViewById(R.id.singleLink);
        singleDiscription=findViewById(R.id.singleDiscription);
        singleCollege=findViewById(R.id.singleCollege);
        singleEventName=findViewById(R.id.singleEventName);
        singleImage=findViewById(R.id.singleImage);
        Picasso.get().load(getIntent().getStringExtra("singleImage"))
                .placeholder(R.drawable.mobilelogin)
                .into(singleImage);
        singleEventName.setText(getIntent().getStringExtra("singleEventName"));
        singleCollege.setText(getIntent().getStringExtra("singleCollege"));
        singleDiscription.setText(getIntent().getStringExtra("singleDiscription"));
        singleLink.setText(getIntent().getStringExtra("singleLink"));
        Linkify.addLinks(singleLink, Linkify.ALL);
    }
}