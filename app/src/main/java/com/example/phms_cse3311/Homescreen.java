package com.example.phms_cse3311;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Homescreen extends AppCompatActivity {
    Button vitalSignButton;
    Button medicationButton;
    Button dietButton;
    Button noteButton;
    Button searchButton;
    Button communicationButton;
    Button monitoringButton;
    Button exerciseButton;
    Button logoutButton;
    EditText nameHomescreen, ageHomescreen, phoneHomescreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        nameHomescreen = (EditText)findViewById(R.id.userNameHomescreen);
        ageHomescreen = (EditText)findViewById(R.id.userAgeHomescreen);
        phoneHomescreen = (EditText)findViewById(R.id.userPhoneHomescreen);
        showUserData();




        //------------------------------------------------------------------//
        // Vital Signs

        vitalSignButton = findViewById(R.id.vitalButton);
        vitalSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runVitalSigns = new Intent(Homescreen.this, vitalSigns.class);
                startActivity(runVitalSigns);
            }
        });
        //------------------------------------------------------------------//
        // Medications

        medicationButton = findViewById(R.id.medicationButton);
        medicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runMedication = new Intent(Homescreen.this, medications.class);
                startActivity(runMedication);
            }
        });
        //------------------------------------------------------------------//
        // Diet

        dietButton = findViewById(R.id.dietButton);
        dietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runDiet = new Intent(Homescreen.this, diet.class);
                startActivity(runDiet);
            }
        });
        //------------------------------------------------------------------//
        // Note

        noteButton = findViewById(R.id.noteButton);
        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runNote = new Intent(Homescreen.this, notes.class);
                startActivity(runNote);
            }
        });
        //------------------------------------------------------------------//
        // Searching

        searchButton = findViewById(R.id.searchingButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runSearching = new Intent(Homescreen.this, searching.class);
                startActivity(runSearching);
            }
        });
        //------------------------------------------------------------------//
        // Communication

        communicationButton = findViewById(R.id.communicationButton);
        communicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runCommunication = new Intent(Homescreen.this, communication.class);
                startActivity(runCommunication);
            }
        });
        //------------------------------------------------------------------//
        // Monitoring System

        monitoringButton = findViewById(R.id.monitoringButton);
        monitoringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runMonitoring = new Intent(Homescreen.this, monitoringSystem.class);
                startActivity(runMonitoring);
            }
        });
        //------------------------------------------------------------------//
        // Exercise

        exerciseButton = findViewById(R.id.exerciseButton);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runExercise = new Intent(Homescreen.this, exercise.class);
                startActivity(runExercise);
            }
        });
        //------------------------------------------------------------------------------
        //------------------------------------------------------------------------------
        // Logout
        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Homescreen.this);
                builder.setMessage("Do you want to exit ?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        //------------------------------------------------------------------------------
    }

    private void showUserData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String phone = intent.getStringExtra("phone");

        nameHomescreen.setText(name);
        ageHomescreen.setText(age);
        phoneHomescreen.setText(phone);
    }
}