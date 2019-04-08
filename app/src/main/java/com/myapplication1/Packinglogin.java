package com.myapplication1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Packinglogin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    final Context context = this;
    Button button;
    Button button2;
    Button button3;
    String A="Packing@123";
    String a,unit,line;
    EditText Net;
    EditText Password;
    private long mBackPressed;
    private GestureDetectorCompat gestureDetectorCompat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packinglogin);


        button = (Button) findViewById(R.id.Loginiron);
        Password = (EditText) findViewById(R.id.editText4);
        Net = (EditText) findViewById(R.id.editText);
        // Sewing button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isOnline()) {
                    Net.setText("");
                    Net.setBackgroundColor(Color.LTGRAY);
                    String textString = Password.getText().toString();
                    if (textString != null && textString.trim().length() == 0) {
                        Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please Enter the password ", Toast.LENGTH_LONG).show();
                    } else{

                        if(textString.equals(A)){

                            Intent myIntent = new Intent(context, Packing.class);
                            startActivity(myIntent);
                            Password.setText("");
                        }
                        else {

                            Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please Enter correct Password ", Toast.LENGTH_LONG).show();
                        }
                    }



                } else {
                    Net.setBackgroundColor(Color.RED);

                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(Color.WHITE);

                }
            }
        });


        checkConnection();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
//Net work Alert
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public void checkConnection() {
        if (isOnline()) {
            Net.setText("");


        } else {
            Net.setBackgroundColor(Color.RED);
            Net.setText("Now You are in Offline");
            Net.setTextColor(Color.WHITE);
        }
    }
}
