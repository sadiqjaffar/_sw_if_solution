package com.googleapi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Activity2 extends AppCompatActivity {
    public Button button;
    public Button button2;
    public Button editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        button = (Button) findViewById(R.id.buttonHome);
        button2 = (Button) findViewById(R.id.button);
        editText = (Button)findViewById(R.id.Name);

        editText.setOnClickListener(v -> {
            SelectPDF();
        });
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){

                Toast.makeText(Activity2.this,"Data uploaded successfully",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void SelectPDF() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12);
    }

}