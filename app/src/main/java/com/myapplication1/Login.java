package com.myapplication1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    private final String NAMESPACE = "http://tempuri.org/";

    private final String SOAP_ACTION_unit = "http://tempuri.org/cbGetunitname";
    private final String METHOD_NAME_unit = "cbGetunitname";
    private final String SOAP_ACTION_line = "http://tempuri.org/cbgetlinename";
    private final String METHOD_NAME_line = "cbgetlinename";
    private final String SOAP_ACTION_result = "http://tempuri.org/cbLineLogin";
    private final String METHOD_NAME_result = "cbLineLogin";

    String Buyer;
    String Orderno;
    String Color;
    String Style;
    String GR,textt;
    String section;
    EditText Net;
    final Context context = this;
    String Line2,test;
    String Line, unit,Defectentry;
    TextView sec,PRo,Target,Tpro,Trework,Tobeachived,Backlock;
    String a, b, c, d, e, f, g, h, i, j, k, l, s;
    String defectlocation;
    Spinner Spin_unit,Oprname2, Spin_line, Spin_buyer, Spin_Order_no, Spin_color, Spin_size, Spin_defect, Spin_section, Spin_defectloc, Spin_style, Oprname, Spin_Gr;
    EditText bulk,Password;
    private GestureDetectorCompat gestureDetectorCompat;
    String[] listItems;
    boolean[] checkedItems;
    TextView defect,Result,Result2;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    Button Login1, fail,Rework,Lines,Quality;
    SoapPrimitive resultString;
    SoapObject resultString5;
    String TAG = "Response";
    //Array list
    List<String> Listunit = null;
    List<String> Listline = null;
    List<String> Listresult = null;
    List<String> Listresult2 = null;
    List<String> Listpassword = null;
    List<String> Listsection = null;

    private GoogleApiClient client;
    private long mBackPressed;
    //Zoom Control
  //webview.getSettings().setSupportZoom(true);       //Zoom Control on web

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //ID to Declaration
        Spin_unit = (Spinner) findViewById(R.id.spinner_unit);
        Spin_line = (Spinner) findViewById(R.id.spinner_line);

        Result = (TextView) findViewById(R.id.result);
        Result2 = (TextView) findViewById(R.id.result2);
        Password = (EditText) findViewById(R.id.Password);
        Login1  = (Button) findViewById(R.id.login) ;




        Getunit line = new Getunit();
        line.execute();

        Spin_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listunit!= null) && (Listunit.size() > 0)) {
                    Line2 = Listunit.get(position);
                    Log.i(TAG, "Line: " + Line2);
                    Getline orderno = new Getline();
                    orderno.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Listpassword = new ArrayList<String>();
                int cnt = Password.getText().length();

                if (cnt == 7) {


                }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
            Login1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    String textString = Password.getText().toString();
                    if (textString != null && textString.trim().length() == 0) {
                        Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please enter the Password...", Toast.LENGTH_LONG).show();

                    }else {
                        Getresult myRequest = new Getresult();
                        myRequest.execute();

                    }

                }

            });



       // Value get function from Webservices
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
       // checkConnection();
        //Keyboard Hidden

    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe left' action only
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {


            if (event2.getX() < event1.getX()) {
                Toast.makeText(getBaseContext(), "defect", Toast.LENGTH_SHORT).show();

                //switch another activity
                Intent intent = new Intent(Login.this, Rejection.class);
                startActivity(intent);
            }

            return true;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);

    }
     //Keyboard hidden when i click out of textbox
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(Login.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void alert(){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setTitle("Let's Start");
            builder.setMessage("Best of Luck  \uD83C\uDF89 Have a GOOG DAY");
            builder.setCancelable(true);
            final AlertDialog dlg = builder.create();
            dlg.show();

            final Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    dlg.dismiss(); // when the task active then close the dialog
                    t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                }
            }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.


    }
    // Network status check function
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
            Net.setBackgroundColor(android.graphics.Color.RED);
            Net.setText("Now You are in Offline");
            Net.setBackgroundColor(android.graphics.Color.WHITE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page Config.URL is correct.
                // Otherwise, set the Config.URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app Config.URL is correct.
                Uri.parse("android-app://com.cb/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page Config.URL is correct.
                // Otherwise, set the Config.URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app Config.URL is correct.
                Uri.parse("android-app://com.cb/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }







    @Override
    public void onResume() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();
    }

    public abstract class BasePortraitActivity extends Activity {

        @Override
        protected final void onCreate(Bundle state) {
            super.onCreate(state);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            performOnCreate(state);
        }

        protected abstract void performOnCreate(Bundle state);

    }


    private class Getresult extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a=Spin_unit.getSelectedItem().toString();
            b=Spin_line.getSelectedItem().toString();
            d=Password.getText().toString();
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_result);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
                Request.addProperty("password", d);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_result, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split("@@");
                if (result != null) {
                    Listresult = Arrays.asList(result);
                    Listresult2 = Arrays.asList(result);
                }
            /* else {
                    ListOprname.add("JJ");
                }*/

            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if ((Listresult != null) && (Listresult.size() > 0)) {
                Result.setText(Listresult.get(0));
                Result2.setText(Listresult2.get(1));

                a=Spin_unit.getSelectedItem().toString();
                b=Spin_line.getSelectedItem().toString();
                c= Result2.getText().toString();
                if(b.equals(c)) {

                    Bundle bundle = new Bundle();
                    bundle.putString("unit", a);
                    Bundle bundleb = new Bundle();
                    bundleb.putString("line", b);
                    //   Intent myIntent = new Intent(getApplicationContext(), SewingInspection.class);
                    Intent myIntent = new Intent(context, Sewing.class);
                    myIntent.putExtra("value", a);
                    myIntent.putExtras(bundle);
                    myIntent.putExtras(bundleb);
                    startActivity(myIntent);


         Password.setText("");
                }else {
                    Toast.makeText(getBaseContext(), " Please Enter Valid Password", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(getBaseContext(), " Please Enter Valid Password", Toast.LENGTH_SHORT).show();



            }
        }
    }
    private class Getunit extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_unit);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_unit, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listunit = Arrays.asList(result);
                }

            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if ((Listunit != null) && (Listunit.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Login.this, android.R.layout.simple_spinner_item, Listunit);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_unit.setAdapter(dataAdapter);
            }

        }
    }
    private class Getline extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_line);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_line, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listline = Arrays.asList(result);
                }

            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if ((Listline != null) && (Listline.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Login.this, android.R.layout.simple_spinner_item, Listline);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_line.setAdapter(dataAdapter);
            }

        }
    }
}
