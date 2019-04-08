package com.myapplication1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    final Context context = this;
    Button button;
    Button button2;
    Button button3;

    String a,unit,line;
    EditText Net;
    private long mBackPressed;
    private GestureDetectorCompat gestureDetectorCompat;

    //Back pree function
    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Log.d("CDA", "onBackPressed Called");
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
        } else {
            Toast.makeText(getBaseContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        button = (Button) findViewById(R.id.button);
        Net = (EditText) findViewById(R.id.editText);
        // Sewing button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isOnline()) {
                    Net.setText("");
                    Net.setBackgroundColor(Color.LTGRAY);
//                    a = "SEWING";
//                    Bundle bundle1 = new Bundle();
//                    bundle1.putString("SEWING", a);
                    //Bundle bundle = new Bundle();
//                    bundle.putString("unit", unit);
//                    Bundle bundleb = new Bundle();
//                    bundleb.putString("line", line);
                    //   Intent myIntent = new Intent(getApplicationContext(), SewingInspection.class);
                    Intent myIntent = new Intent(context, Login.class);
                    myIntent.putExtra("value", a);
                   // myIntent.putExtras(bundle1);
//                    myIntent.putExtras(bundle);
//                    myIntent.putExtras(bundleb);
                    startActivity(myIntent);
                } else {
                    Net.setBackgroundColor(Color.RED);

                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(Color.WHITE);

                }
            }
        });

        Net.setInputType(InputType.TYPE_NULL);
        button2 = (Button) findViewById(R.id.Add);
        // Ironing button clicks
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isOnline()) {
                    Net.setText("");
                    Net.setBackgroundColor(Color.LTGRAY);
//                    a = "IRONING";
//                    Bundle bundle = new Bundle();
//                    bundle.putString("IRONING", a);
                    //Intent myIntent = new Intent(getApplicationContext(), SewingInspection.class);
                    Intent myIntent = new Intent(context, Ironinglogin.class);
//                    myIntent.putExtra("value", a);

//                    myIntent.putExtras(bundle);

                    startActivity(myIntent);
                } else {
                    Net.setBackgroundColor(Color.RED);

                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(Color.WHITE);

                }

                // Start NewActivity.class
//                Intent myIntent = new Intent(Main.this, Sewing.class);
//                startActivity(myIntent);
            }
        });
        button3 = (Button) findViewById(R.id.button3);
        // Packing button clicks
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isOnline()) {
                    Net.setText("");
                    Net.setBackgroundColor(Color.LTGRAY);
//                    a = "PACKING";
//                    Bundle bundle = new Bundle();
//                    bundle.putString("PACKING", a);
//                    //   Intent myIntent = new Intent(getApplicationContext(), SewingInspection.class);
                    Intent myIntent = new Intent(context, Packinglogin.class);
//                    myIntent.putExtra("value", a);
//                    myIntent.putExtras(bundle);
                    startActivity(myIntent);
                } else {
                    Net.setBackgroundColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(Color.WHITE);
                }
            }
        });
        Button cutting;
        cutting = (Button) findViewById(R.id.btn_cutting);
        // Packing button clicks
        cutting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isOnline()) {
                    Net.setText("");

                    Intent myIntent = new Intent(context, Cutlogin.class);
                    startActivity(myIntent);
                } else {
                    Net.setBackgroundColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(Color.WHITE);
                }
            }
        });
        Button Dp;
        Dp = (Button) findViewById(R.id.btndespatch);

        Dp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (isOnline()) {
                    Net.setText("");

                    Intent myIntent = new Intent(context, Despatchlogin.class);
                    startActivity(myIntent);
                } else {
                    Net.setBackgroundColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(Color.WHITE);
                }
            }
        });
        Button demo;
        demo = (Button) findViewById(R.id.demo);

        demo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Demo ", Toast.LENGTH_LONG).show();

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
