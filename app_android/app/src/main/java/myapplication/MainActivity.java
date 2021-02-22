package com.googleapi.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static com.googleapi.myapplication.R.id.textView;
import static com.googleapi.myapplication.R.id.textView9;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    BluetoothSocket bluetoothSocket = null;
    InputStream inputStream;
    int i, heartRate, spO2, temperature;
    String bloodPressure, sdfString;
    String deviceName = "";
    @SuppressLint("NewApi")
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public Button button;
    Random rand = new Random();


    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView10 = (TextView) findViewById(R.id.textView10);
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        TextView textView13 = (TextView) findViewById(R.id.textView13);
        TextView textView14 = (TextView) findViewById(R.id.textView14);
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        sdfString = sdf.format(new Date());
        try {
            BluetoothDevice hc05 = bluetoothAdapter.getRemoteDevice("00:18:E4:00:43:6C");
            bluetoothSocket = hc05.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            bluetoothSocket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                if (device.getAddress().equals("00:18:E4:00:43:6C")) {
                    deviceName += device.getName();
                    break;
                }
            }
        }
        Thread th = new Thread() {

            @Override
            public void run() {

                while (!isInterrupted()) {
                    try {
                        Thread.sleep(30000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    inputStream = bluetoothSocket.getInputStream();
                                    byte[] buffer = new byte[30];
                                    inputStream.read(buffer);
                                    for(i = 29; buffer[i] != 35 && buffer[i] != 64; i--);
                                    heartRate = 0;
                                    spO2 = 0;
                                    if(buffer[i] == 64) {
                                        for(i--; buffer[i] != 35; i--)
                                            heartRate = (heartRate * 10) + (buffer[i] - 48);
                                        for(i--; buffer[i] != 64; i--)
                                            spO2 = (spO2 * 10) + (buffer[i] - 48);
                                    }
                                    else {
                                        for(i--; buffer[i] != 64; i--)
                                            heartRate = (heartRate * 10) + (buffer[i] - 48);
                                        for(i--; buffer[i] != 35; i--)
                                            spO2 = (spO2 * 10) + (buffer[i] - 48);
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                temperature = 37;
                                bloodPressure = 119 + "/" + 79;
                                textView10.setText(String.valueOf(heartRate));
                                textView12.setText(String.valueOf(spO2));
                                textView13.setText(String.valueOf(temperature));
                                textView14.setText(String.valueOf(bloodPressure));

                                //employeeRegister er= new employeeRegister();

                                database= FirebaseDatabase.getInstance();
                                reference = database.getReference("EmployeesData").child("Health Device Name").child(deviceName).child(sdfString);
                                EmployeesData Data= new EmployeesData(heartRate, spO2, temperature, bloodPressure);
                                reference.setValue(Data);





                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        th.start();


            button = (Button) findViewById(R.id.button7);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    startActivity(intent);
                }


            });

            button = (Button) findViewById(R.id.buttonlog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }


        });





        }
    }