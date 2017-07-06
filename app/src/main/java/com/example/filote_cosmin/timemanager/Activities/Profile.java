package com.example.filote_cosmin.timemanager.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.filote_cosmin.timemanager.Adapter.ProjectAdapter;
import com.example.filote_cosmin.timemanager.Model.Proiect;
import com.example.filote_cosmin.timemanager.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Profile extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "TAG";
    private Button btnLogout;
    private List<Proiect> projectList=new ArrayList<>();
    private ListView listViewProject;
    private ImageView closeButton;
    private Button btnProject;
    private Button btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        projectList.add(new Proiect(1,"TimeManager",new Date(),3));
        projectList.add(new Proiect(2,"Metrorex",new Date(),7));
        projectList.add(new Proiect(3,"Uber",new Date(),20));
        projectList.add(new Proiect(4,"Facebook",new Date(),5));


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
        listViewProject=(ListView)findViewById(R.id.lv_project);
        ProjectAdapter projectAdapter=new ProjectAdapter(projectList,Profile.this);
        listViewProject.setAdapter(projectAdapter);
        closeButton=(ImageView)findViewById(R.id.im_close_button);
        closeButton.setOnClickListener(this);
        listViewProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Proiect proiect=projectList.get(position);
                Toast.makeText(Profile.this,proiect.getNume(),Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(Profile.this, Project.class);
                myIntent.putExtra("proiectID",proiect.getId());
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent);


            }

    });
        btnProject=(Button)findViewById(R.id.btn_project);
        btnProject.setOnClickListener(this);
        btnEditProfile=(Button)findViewById(R.id.btn_edit);
        btnEditProfile.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.equals(btnLogout)) {
            //logout
            mAuth.signOut();
            finish();
            startActivity(new Intent(Profile.this, Login.class));
        }
        if(v.equals(closeButton)){
            listViewProject.setVisibility(View.GONE);
            closeButton.setVisibility(View.GONE);

        }
        if(v.equals(btnProject)){
            listViewProject.setVisibility(View.VISIBLE);
            closeButton.setVisibility(View.VISIBLE);
        }
        if(v.equals(btnEditProfile)){
            Toast.makeText(Profile.this,"Ai apasat pe editProfile",Toast.LENGTH_LONG).show();
            String id=mAuth.getCurrentUser().getUid().toString();
            Intent myIntent = new Intent(Profile.this, EditProfile.class);
            myIntent.putExtra("userID",id);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(myIntent);
        }
    }
}
