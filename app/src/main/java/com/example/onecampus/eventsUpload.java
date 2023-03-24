package com.example.onecampus;

import static android.provider.CalendarContract.CalendarCache.URI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.LinkAddress;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.HashMap;

public class eventsUpload extends AppCompatActivity {

    TextView description,title,clg,link;
    ImageView uploadbtn,productImage;
    RelativeLayout relative;
    Button submit;
    Uri imageuri;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;

    private DatabaseReference databaseReference;

    private FirebaseUser user;
    ProgressDialog dialog;
    int idx=0;
//    public  Item(String getmil,String getClg,String getdes,String gettit,String getlin,String getimg){
//        this.getmil=getmil;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_upload);

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
        firebaseStorage=FirebaseStorage.getInstance();

        dialog=new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.setTitle("Event Uploading");
        dialog.setCanceledOnTouchOutside(false);

        description=findViewById(R.id.description);
        title=findViewById(R.id.title);
        clg=findViewById(R.id.clg);
        uploadbtn=findViewById(R.id.uploadbtn);
        relative=findViewById(R.id.relative);
        submit=findViewById(R.id.submit);
        productImage=findViewById(R.id.productImage);
        link= findViewById(R.id.Link);
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                relative.setVisibility(View.VISIBLE);
                uploadbtn.setVisibility(View.GONE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
                String getmil=user.getUid().toString();
                final StorageReference reference1=firebaseStorage.getReference().child(getmil+"")
                        .child(System.currentTimeMillis()+"");
                reference1.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                projectModel model=new projectModel();
                                model.setProductImage(uri.toString());
                                model.setClg(clg.getText().toString());
                                model.setDescription(description.getText().toString());
                                model.setTitle(title.getText().toString());
                                model.setLink(link.getText().toString());

                                database.getReference().child(getmil+"").push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                dialog.dismiss();
//                                                Toast.makeText(eventsUpload.this, "Event Uploaded Successfully", Toast.LENGTH_SHORT).show();
//                                                Intent i=new Intent(eventsUpload.this,userMain.class);
//                                                startActivity(i);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                dialog.dismiss();
//                                                Toast.makeText(eventsUpload.this, "Error Ocurred", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }
                });

              /*  String getmil=user.getEmail().toString();
//                Log.println(getmil);
                String getClg=clg.getText().toString();
                String getdes=description.getText().toString();
                String gettit=title.getText().toString();
                String getlin=link.getText().toString();
                String getimg=imageuri.toString();

                HashMap<String,Object> h=new HashMap<>();
                h.put("EMail",getmil);
                h.put("college",getClg);
                h.put("title",gettit);
                h.put("description",getdes);
                h.put("link",getlin);
                h.put("Image",getimg);

                databaseReference.child("Users").child(getmil)
                        .setValue(h).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(eventsUpload.this,"uploaded",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(eventsUpload.this,"!uploaded",Toast.LENGTH_SHORT).show();
                            }
                        });
*/

                final StorageReference reference=firebaseStorage.getReference().child("events")
                        .child(System.currentTimeMillis()+"");
                reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                projectModel model=new projectModel();
                                model.setProductImage(uri.toString());
                                model.setClg(clg.getText().toString());
                                model.setDescription(description.getText().toString());
                                model.setTitle(title.getText().toString());
                                model.setLink(link.getText().toString());

                                database.getReference().child("event").push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                dialog.dismiss();
                                                Toast.makeText(eventsUpload.this, "Event Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                                Intent i=new Intent(eventsUpload.this,userMain.class);
                                                startActivity(i);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                dialog.dismiss();
                                                Toast.makeText(eventsUpload.this, "Error Ocurred", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }
                });





            }
        });
    }

    private void uploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent=new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent,101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(eventsUpload.this,"permission denied",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode==RESULT_OK){
            imageuri=data.getData();
            productImage.setImageURI(imageuri);
        }
    }
}
