package com.example.onecampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class userLogin extends AppCompatActivity {
    EditText editTextEmail,editTextPassword;
    public  static  String PREFS_NAME="MyPrefsFile";
    Button userSigin;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        mAuth=FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.userEmailLogin);
        editTextPassword=findViewById(R.id.userPasswordLogin);
        userSigin=findViewById(R.id.userSignin);
        userSigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences(userLogin.PREFS_NAME,0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("hasLoggedin",true);
                editor.commit();
                startActivity(new Intent(userLogin.this,userMain.class));

                String email,password;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassword.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(userLogin.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(userLogin.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
//                                    FirebaseUser user = mAuth.getCurrentUser(); //justnow added
                                    Toast.makeText(userLogin.this, "Logedin successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(userLogin.this, userMain.class);
                                    startActivity(intent);
//                                    finish();
                                } else {
                                    Toast.makeText(userLogin.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}