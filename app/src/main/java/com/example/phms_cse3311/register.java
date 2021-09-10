package com.example.phms_cse3311;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;
    EditText editName, editAge, editUsername, editPassword,editPhone;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = (EditText)findViewById(R.id.name);
        editAge = (EditText)findViewById(R.id.age);
        editUsername = (EditText)findViewById(R.id.usernameRegister);
        editPassword = (EditText)findViewById(R.id.passwordRegister);
        editPhone = (EditText)findViewById(R.id.phoneNumber);
        bRegister = (Button)findViewById(R.id.registerButton);

        bRegister.setOnClickListener(this);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                String name = editName.getText().toString();
                String age = editAge.getText().toString();
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                String phoneNumber = editPhone.getText().toString();
                userHelper helper = new userHelper(name,age,username,password,phoneNumber);

                reference.child(username).setValue(helper);

                break;
        }
        finish();
    }
}