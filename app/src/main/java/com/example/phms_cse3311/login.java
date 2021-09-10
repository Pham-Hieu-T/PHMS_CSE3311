package com.example.phms_cse3311;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    Button login;
    TextInputLayout editUsername, editPassword;
    TextView registerHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);
        registerHere = findViewById(R.id.registerHere);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUserName() | !validatePassword()) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                        builder.setMessage("Try again or register new user.");
                        AlertDialog alert = builder.create();
                        alert.show();
                } else
                        isUser();
            }

        });
        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));
            }
        });
    }

    private Boolean validateUserName() {
        String inputUserName = editUsername.getEditText().getText().toString();

        if (inputUserName.isEmpty()) {
            editUsername.setError("Please enter your username");
            return false;
        } else if (inputUserName.length() > 16) {
            editUsername.setError("Username is too long. Try again");
            return false;
        } else {
            editUsername.setError(null);
            editUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String inputPassword = editPassword.getEditText().getText().toString();

        if (inputPassword.isEmpty()) {
            editPassword.setError("Please enter your password");
            return false;
        } else {
            editPassword.setError(null);
            editPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void isUser() {
        final String InputUsername = editUsername.getEditText().getText().toString().trim();
        final String InputPassword = editPassword.getEditText().getText().toString().trim();

        DatabaseReference refer = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = refer.orderByChild("username").equalTo(InputUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    editUsername.setError(null);
                    editUsername.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(InputUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(InputPassword)) {
                        editUsername.setError(null);
                        editUsername.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(InputUsername).child("name").getValue(String.class);
                        String ageFromDB = snapshot.child(InputUsername).child("age").getValue(String.class);
                        String phoneFromDB = snapshot.child(InputUsername).child("phone").getValue(String.class);

                        Intent intentHomescreen = new Intent(getApplicationContext(), Homescreen.class);
                        intentHomescreen.putExtra("name", nameFromDB);
                        intentHomescreen.putExtra("age", ageFromDB);
                        intentHomescreen.putExtra("phone", phoneFromDB);

                        startActivity(intentHomescreen);

                    } else {
                        editPassword.setError("Wrong Password");
                        editPassword.requestFocus();

                    }
                } else {
                    editUsername.setError("No user found");
                    editUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {
            }
        });

    }
}
