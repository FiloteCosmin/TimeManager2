package com.example.filote_cosmin.timemanager.Activities;

import android.app.ProgressDialog;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filote_cosmin.timemanager.Model.User;
import com.example.filote_cosmin.timemanager.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextToken;
    private TextView textViewErrorFields;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG="TAG";
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail=(EditText)findViewById(R.id.et_email);
        editTextPassword=(EditText)findViewById(R.id.et_password);
        editTextToken=(EditText)findViewById(R.id.et_token);
        btnRegister=(Button)findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
        textViewErrorFields=(TextView)findViewById(R.id.fields);


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
        databaseReference= FirebaseDatabase.getInstance().getReference();
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
        if(v.equals(btnRegister)){
            registerUser();
        }
    }

    private void registerUser() {

        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //email is empty
           textViewErrorFields.setVisibility(View.VISIBLE);
            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                    // You don't need anything here
                }

                public void onFinish() {
                    textViewErrorFields.setVisibility(View.GONE);
                }
            }.start();
            //stop the function to progress
            return;
        }
        final String emailFinal=email;

        if(TextUtils.isEmpty(password)) {
            //password is empty
            textViewErrorFields.setVisibility(View.VISIBLE);
           ; new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                    // You don't need anything here
                }

                public void onFinish() {
                    textViewErrorFields.setVisibility(View.GONE);
                }
            }.start();
            //stop the function to progress
            return;
        }
        final String passwordFinal=password;

        //if validations are ok show progresDialog
        progressDialog.setMessage("Registering user.");
        progressDialog.show();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this,"Register succesfully !",
                                    Toast.LENGTH_SHORT).show();
                            User userNou = new User(emailFinal,passwordFinal);
                            FirebaseUser user = mAuth.getCurrentUser();
                            databaseReference.child(user.getUid()).setValue(userNou);
                        }
                        else{
                            Toast.makeText(Register.this,"Register failed !",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                    }
                });
    }
}
