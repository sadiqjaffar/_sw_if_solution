package com.googleapi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    public Button button;
    public EditText n;
    public EditText em;
    public EditText emp;
    public Button buttonupdate;


    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        n=findViewById(R.id.PersonName);
        em=findViewById(R.id.EmailAddress);
        buttonupdate=findViewById(R.id.buttonupdate);
        emp=findViewById(R.id.employeeID);

        button = (Button) findViewById(R.id.buttonHome2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonupdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String name = n.getText().toString();
                String email=em.getText().toString();
                String employeeId=emp.getText().toString();

                database= FirebaseDatabase.getInstance();
                reference = database.getReference("EmployeesData").child("Employee Details").child(employeeId);
                employeeRegister profile=new employeeRegister(name,email,employeeId);
                Toast.makeText(register.this,"Profile updated successfully",Toast.LENGTH_SHORT).show();
                reference.setValue(profile);




            }






        });
    }
}