package com.example.filote_cosmin.timemanager.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.filote_cosmin.timemanager.R;

public class Project extends AppCompatActivity {
    TextView Nume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        Nume=(TextView)findViewById(R.id.numeProiect);

        int value;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            value = bundle.getInt("proiectID");
            String Nume1=Nume.getText().toString();
            Nume.setText(Nume1+" "+value);

        }


    }
}
