package com.myapplication1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.List;

public class Cutting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    private final String NAMESPACE = "http://tempuri.org/";

    private final String SOAP_ACTION_buyer = "http://tempuri.org/cbGetbuyerQ";
    private final String METHOD_NAME_buyer = "cbGetbuyerQ";
    private final String SOAP_ACTION_order = "http://tempuri.org/cbGetordernoQ";
    private final String METHOD_NAME_order = "cbGetordernoQ";
    private final String SOAP_ACTION_style = "http://tempuri.org/cbGetstyleQ";
    private final String METHOD_NAME_style = "cbGetstyleQ";
    private final String SOAP_ACTION_color = "http://tempuri.org/cbGetcolorQ";
    private final String METHOD_NAME_color = "cbGetcolorQ";
    private final String SOAP_ACTION_size = "http://tempuri.org/cbGetsizeQ";
    private final String METHOD_NAME_size = "cbGetsizeQ";
    private final String SOAP_ACTION_reject = "http://tempuri.org/cbGetRejectionslistQ";
    private final String METHOD_NAME_reject = "cbGetRejectionslistQ";
    private final String SOAP_ACTION_part = "http://tempuri.org/cbGetpartnamelistQ";
    private final String METHOD_NAME_part = "cbGetpartnamelistQ";
    private final String SOAP_ACTION_pass = "http://tempuri.org/cbrejectionentryforcutprintembwashQ";
    private final String METHOD_NAME_pass = "cbrejectionentryforcutprintembwashQ";


    String a, b, c, d, e, f, g, h, i;
    String Orderno;
    String BuyerS;
    String section;
    String ColorS;
    Spinner Spin_unit, Spin_Color, Spin_orderno, Spin_section, Spin_defectloc, Buyer, Spin_size, Spin_re, Spin_part;
    EditText Style, Noof;
    TextView dateView;

    Button Add, Submit, Clear,Reload;
    SoapPrimitive resultString;
    SoapObject resultString5;
    String TAG = "Response";

    List<String> Listorderno = null;
    List<String> Liststyle = null;
    List<String> Listbuyer = null;
    List<String> Listcolor = null;
    List<String> Listsize = null;
    List<String> Listre = null;
    List<String> Listpart = null;


    private GestureDetectorCompat gestureDetectorCompat;
    private GoogleApiClient client;
    private String selectedOprID4 = "";
    //Date view
    private int year, month, day;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cutting);
        Spin_unit = (Spinner) findViewById(R.id.spinner_order);
        Style = (EditText) findViewById(R.id.spinner_style);
        Buyer = (Spinner) findViewById(R.id.spinner_buyer);
        Spin_defectloc = (Spinner) findViewById(R.id.spinner_rejection);
        Spin_section = (Spinner) findViewById(R.id.spinner_part);
        Spin_orderno = (Spinner) findViewById(R.id.spinner_order);
        Spin_size = (Spinner) findViewById(R.id.spinner_size);
        Spin_re = (Spinner) findViewById(R.id.spinner_rejection);
        Spin_part = (Spinner) findViewById(R.id.spinner_part);
        Noof = (EditText) findViewById(R.id.rejectoincnt);
        Add = (Button) findViewById(R.id.Add);
        Submit = (Button) findViewById(R.id.Submit);
        Clear = (Button) findViewById(R.id.Clear);
        Spin_Color=(Spinner)findViewById(R.id.spinner_color);
        dateView = (TextView) findViewById(R.id.date);

//
//        Bundle bundle = getIntent().getExtras();
//
//
//        section = bundle.getString("value");
//

        //Date view
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        Getbuyer style = new Getbuyer();
        style.execute();
        Getre re = new Getre();
        re.execute();

        Buyer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listbuyer != null) && (Listbuyer.size() > 0)) {

                    BuyerS = Listbuyer.get(position);
                    Log.i(TAG, "Line: " + BuyerS);
                    Getorderno style = new Getorderno();
                    style.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spin_orderno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listorderno != null) && (Listorderno.size() > 0)) {

                    Orderno = Listorderno.get(position);
                    Log.i(TAG, "Line: " + Orderno);
                    Getstyle style = new Getstyle();
                    style.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spin_Color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listcolor != null) && (Listcolor.size() > 0)) {

                    ColorS = Listcolor.get(position);
                    Log.i(TAG, "Line: " + ColorS);
                    Getsize size = new Getsize();
                    size.execute();
                    Getpart part = new Getpart();
                    part.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Style.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Liststyle = new ArrayList<String>();
                int cnt = Style.getText().length();

                if (cnt == 5) {
                    Getcolor Color = new Getcolor();
                    Color.execute();

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textString2 = Noof.getText().toString();
                if (textString2 != null && textString2.trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please enter the Rejection count...", Toast.LENGTH_LONG).show();
                } else {
                    passdata pass = new passdata();
                    pass.execute();
                Noof.setText("");
                }

            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textString2 = Noof.getText().toString();
                if (textString2 != null && textString2.trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please enter the Rejection count...", Toast.LENGTH_LONG).show();
                } else {
                passdata pass = new passdata();
                pass.execute();
                Buyer.setAdapter(null);
                Spin_orderno.setAdapter(null);
                Style.setText("");
                    Spin_Color.setAdapter(null);
                Spin_re.setAdapter(null);
                Spin_part.setAdapter(null);
                Spin_size.setAdapter(null);
                Noof.setText("");
            }}
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Buyer.setAdapter(null);
                Spin_orderno.setAdapter(null);
                Style.setText("");
                Spin_Color.setAdapter(null);
                Spin_re.setAdapter(null);
                Spin_part.setAdapter(null);
                Spin_size.setAdapter(null);
                Noof.setText("");
            }
        });

        Style.setInputType(InputType.TYPE_NULL);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);

    }

    //Date view function
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public  boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.user:{
                Getbuyer style = new Getbuyer();
                style.execute();
                Getre re = new Getre();
                re.execute();
                return true;
            }
        }
        return true;
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }
    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Main Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page Config.URL is correct.
//                // Otherwise, set the Config.URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app Config.URL is correct.
//                Uri.parse("android-app://com.Quality/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Main Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page Config.URL is correct.
//                // Otherwise, set the Config.URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app Config.URL is correct.
//                Uri.parse("android-app://com.Quality/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onResume() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();
    }

    public void data() {


    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe left' action only

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

            if (event2.getX() < event1.getX()) {
                Toast.makeText(getBaseContext(), "defect", Toast.LENGTH_SHORT).show();
                //switch another activity
                Intent intent = new Intent(Cutting.this, Rejection.class);
                startActivity(intent);
            }

            return true;
        }
    }

    private class Getbuyer extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {


            super.onPreExecute();
            progressDialog = new ProgressDialog(Cutting.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_buyer);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_buyer, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listbuyer = Arrays.asList(result);
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
            if ((Listbuyer != null) && (Listbuyer.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Cutting.this, android.R.layout.simple_spinner_item, Listbuyer);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Buyer.setAdapter(dataAdapter);
            }

        }
    }

    private class passdata extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            a = Buyer.getSelectedItem().toString();
            b = Style.getText().toString();
            c = Spin_Color.getSelectedItem().toString();
            e = Spin_orderno.getSelectedItem().toString();
            f = Spin_size.getSelectedItem().toString();
            g = Spin_re.getSelectedItem().toString();
            h = Spin_part.getSelectedItem().toString();
            i = Noof.getText().toString();


            progressDialog = new ProgressDialog(Cutting.this);
            progressDialog.setMessage("Processing....");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_pass);
                Request.addProperty("buyer", a);
                Request.addProperty("orderno", e);
                Request.addProperty("style", b);
                Request.addProperty("color", c);
                Request.addProperty("size", f);
                Request.addProperty("section", section);
                Request.addProperty("typeofrejection", g);
                Request.addProperty("partname", h);
                Request.addProperty("noofrejection", i);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_pass, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                Log.i(TAG, "paas: " + resultString);
            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), "Success to add the data", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            }
    }

    private class Getorderno extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            a = Buyer.getSelectedItem().toString();
            super.onPreExecute();
            progressDialog = new ProgressDialog(Cutting.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_order);
                Request.addProperty("strbuyer", a);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_order, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listorderno = Arrays.asList(result);
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
            if ((Listorderno != null) && (Listorderno.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Cutting.this, android.R.layout.simple_spinner_item, Listorderno);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_orderno.setAdapter(dataAdapter);
            }

        }
    }

    private class Getstyle extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Cutting.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_style);
                Request.addProperty("strbuyer", a);
                Request.addProperty("strorderno", Orderno);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_style, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Liststyle = Arrays.asList(result);
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
            if ((Liststyle != null) && (Liststyle.size() > 0)) {

                Style.setText(Liststyle.get(0));
            }
            Getcolor Color = new Getcolor();
            Color.execute();
        }
    }

    private class Getcolor extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            b = Style.getText().toString();
            super.onPreExecute();
            progressDialog = new ProgressDialog(Cutting.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_color);
                Request.addProperty("strbuyer", a);
                Request.addProperty("strorderno", Orderno);
                Request.addProperty("strstyle", b);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_color, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listcolor = Arrays.asList(result);
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
            if ((Listcolor != null) && (Listcolor.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Cutting.this, android.R.layout.simple_spinner_item, Listcolor);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_Color.setAdapter(dataAdapter);
                ;
            }

        }
    }

    private class Getsize extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            a = Buyer.getSelectedItem().toString();
            b = Style.getText().toString();
            c = Spin_Color.getSelectedItem().toString();
            super.onPreExecute();
            progressDialog = new ProgressDialog(Cutting.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_size);
                Request.addProperty("strbuyer", a);
                Request.addProperty("strorderno", Orderno);
                Request.addProperty("strstyle", b);
                Request.addProperty("strcolor", c);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_size, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listsize = Arrays.asList(result);
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
            if ((Listsize != null) && (Listsize.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Cutting.this, android.R.layout.simple_spinner_item, Listsize);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_size.setAdapter(dataAdapter);
            }

        }
    }

    private class Getre extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {


            super.onPreExecute();
            progressDialog = new ProgressDialog(Cutting.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_reject);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_reject, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listre = Arrays.asList(result);
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
            if ((Listre != null) && (Listre.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Cutting.this, android.R.layout.simple_spinner_item, Listre);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_re.setAdapter(dataAdapter);
            }

        }
    }

    private class Getpart extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            a = Buyer.getSelectedItem().toString();
            b = Style.getText().toString();
            c = Spin_Color.getSelectedItem().toString();
            super.onPreExecute();
            progressDialog = new ProgressDialog(Cutting.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_part);
                Request.addProperty("buyer", a);
                Request.addProperty("orderno", Orderno);
                Request.addProperty("style", b);
                Request.addProperty("color", c);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_part, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listpart = Arrays.asList(result);
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
            if ((Listpart != null) && (Listpart.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Cutting.this, android.R.layout.simple_spinner_item, Listpart);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_part.setAdapter(dataAdapter);
            }

        }
    }
}
