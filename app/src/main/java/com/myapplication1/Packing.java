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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class Packing extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    private final String NAMESPACE = "http://tempuri.org/";
  
    private final String SOAP_ACTION_unit = "http://tempuri.org/cbGetunitname";
    private final String METHOD_NAME_unit = "cbGetunitname";
    private final String SOAP_ACTION_buyer = "http://tempuri.org/cbGetbuyerFinishing";
    private final String METHOD_NAME_buyer = "cbGetbuyerFinishing";
    private final String SOAP_ACTION_orderno = "http://tempuri.org/cbGetordernoFinishing";
    private final String METHOD_NAME_orderno = "cbGetordernoFinishing";
    private final String SOAP_ACTION_style = "http://tempuri.org/cbGetstyleFinishing";
    private final String METHOD_NAME_style = "cbGetstyleFinishing";
    private final String SOAP_ACTION_color = "http://tempuri.org/cbGetcolorFinishing";
    private final String METHOD_NAME_color = "cbGetcolorFinishing";
    private final String SOAP_ACTION_size = "http://tempuri.org/cbGetsizeFinishing";
    private final String METHOD_NAME_size = "cbGetsizeFinishing";
    private final String SOAP_ACTION_defectlocation = "http://tempuri.org/cbGetdefectlocation";
    private final String METHOD_NAME_defectlocation = "cbGetdefectlocation";
    private final String SOAP_ACTION_totalprodu = "http://tempuri.org/cbGettotalproductionperdayunitline";
    private final String METHOD_NAME_totalprodu = "cbGettotalproductionperdayunitline";
    private final String SOAP_ACTION_Rework = "http://tempuri.org/cbreworkentryPACKING";
    private final String METHOD_NAME_Rework = "cbreworkentryPACKING";
    private final String SOAP_ACTION_defect = "http://tempuri.org/cbGetdefectname";
    private final String METHOD_NAME_defect = "cbGetdefectname";
    private final String SOAP_ACTION_pass = "http://tempuri.org/cbproductionentryPACKING";
    private final String METHOD_NAME_pass = "cbproductionentryPACKING";
    private final String SOAP_ACTION_Fail = "http://tempuri.org/cbdefectentryIPACKING";
    private final String METHOD_NAME_Fail = "cbdefectentryIPACKING";
    private final String SOAP_ACTION_totaldefect = "http://tempuri.org/cbGettotaldefectcountunitline";
    private final String METHOD_NAME_totaldefect = "cbGettotaldefectcountunitline";
    private final String SOAP_ACTION_totaldefectphr = "http://tempuri.org/cbGettotaldefectcountperday";
    private final String METHOD_NAME_totaldefectphr = "cbGettotaldefectcountperday";
    private final String SOAP_ACTION_totalproductionphr = "http://tempuri.org/cbGettotalproductionperday";
    private final String METHOD_NAME_totalproductionphr = "cbGettotalproductionperday";
    private final String SOAP_ACTION_opr = "http://tempuri.org/cbGetsupervisor";
    private final String METHOD_NAME_opr = "cbGetsupervisor";
    private final String SOAP_ACTION_gr = "http://tempuri.org/cbGetgr";
    private final String METHOD_NAME_gr = "cbGetgr";
    private final String SOAP_ACTION_target = "http://tempuri.org/cbGettargetpacking";
    private final String METHOD_NAME_target = "cbGettargetpacking";
    private final String SOAP_ACTION_qc = "http://tempuri.org/cbGetQC";
    private final String METHOD_NAME_qc = "cbGetQC";
    private final String SOAP_ACTION_Trework = "http://tempuri.org/cbGettotalreworkforpo";
    private final String METHOD_NAME_Trework = "cbGettotalreworkforpo2";
    private final String SOAP_ACTION_Prolock = "http://tempuri.org/cbGetbalanceproductiontopasspacking";
    private final String METHOD_NAME_Prolock = "cbGetbalanceproductiontopasspacking";

    String Buyer;
    String Orderno;
    String Color;
    String Style;
    String GR,textt;
    String section;
    EditText Net;
    String Line2,test;
    String Line, unit,Defectentry;
    TextView sec,PRo,Tpro,Trework,Prolock;
    String a, b, c, d, e, f, g, h, i, j, k, l, s;
    String defectlocation;
    Spinner Spin_unit, Oprname2,Spin_buyer, Spin_Order_no, Spin_color,
            Spin_size, Spin_defect, Spin_section, Spin_defectloc, Spin_style, Oprname, Spin_Gr;
    EditText bulk, Totaldefectphr , Totalproductionphr,Backlock;

    String[] listItems;
    boolean[] checkedItems;
    TextView Target,defect;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    Button Pass, fail, Defect,Rework,Production;
    SoapPrimitive resultString;
    SoapObject resultString5;
    String TAG = "Response";
    List<String> Listunit = null;
    List<String> Listline = null;
    List<String> Listbuyer = null;
    List<String> Listorderno = null;
    List<String> Liststyle = null;
    List<String> Listcolor = null;
    List<String> Listsize = null;
    List<String> Listdefectloc = null;
    List<String> Listsection = null;
    List<String> Listdefect = null;
    List<String> Listtotaldefect = null;
    List<String> Listtotaldefectphr = null;
    List<String> Listtotalproduction = null;
    List<String> Listtotalproductionphr = null;
    List<String> Listtotalprodu = null;
    List<String> Listopr = null;
    List<String> Listgr = null;
    List<String> Listtarget = null;
    List<String> Listdefectr = null;
    List<String> Listqc = null;
    List<String> ListTpro = null;
    List<String> ListTrework = null;
    List<String> ListBacklock = null;
    List<String> Listprolock = null;
    private GoogleApiClient client;

    private long mBackPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packing);
        Spin_unit = (Spinner) findViewById(R.id.spinner_unit);
        Spin_buyer = (Spinner) findViewById(R.id.spinner_buyer);
        Spin_Order_no = (Spinner) findViewById(R.id.spinner_Order_No);
        Spin_style = (Spinner) findViewById(R.id.spinner_style);
        Spin_color = (Spinner) findViewById(R.id.spinner_color);
        Spin_size = (Spinner) findViewById(R.id.spinner_size);
        Spin_defectloc = (Spinner) findViewById(R.id.spinner_sizelo);
        Spin_defect = (Spinner) findViewById(R.id.spinner_size2);
        Oprname = (Spinner) findViewById(R.id.spinner_name);
        Oprname2 = (Spinner) findViewById(R.id.spinner_name3);
        Spin_Gr = (Spinner) findViewById(R.id.spinner_gar);
        Totaldefectphr = (EditText) findViewById(R.id.totaldphr);
        Totalproductionphr = (EditText) findViewById(R.id.prophr);
        Net = (EditText) findViewById(R.id.editText);
        Pass = (Button) findViewById(R.id.btnpass);
        fail = (Button) findViewById(R.id.btnFail);
        Defect = (Button) findViewById(R.id.button2);
        bulk = (EditText) findViewById(R.id.Bulk);
        Prolock = (TextView) findViewById(R.id.Prolock2);
        sec = (TextView) findViewById(R.id.Sec);
        Rework = (Button) findViewById(R.id.button9);
        PRo = (TextView) findViewById(R.id.textView4);
        Trework= (TextView) findViewById(R.id.textView25);
        Production = (Button) findViewById(R.id.production);
        Target = (TextView) findViewById(R.id.textView18);
        defect = (TextView) findViewById(R.id.textView10);
        Getunit line = new Getunit();
        line.execute();
        Getopr opr = new Getopr();
        opr.execute();
        Getgr gr = new Getgr();
        gr.execute();
        Getqc qc = new Getqc();
        qc.execute();
        section = "PACKING";
        sec.setText(section);
        Spin_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listunit!= null) && (Listunit.size() > 0)) {
                    unit = Listunit.get(position);
                    Log.i(TAG, "Line: " + unit);
                    Getbuyer orderno = new Getbuyer();
                    orderno.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spin_buyer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listbuyer != null) && (Listbuyer.size() > 0)) {

                    Buyer = Listbuyer.get(position);
                    Log.i(TAG, "Line: " + Buyer);
                    Getorderno orderno = new Getorderno();
                    orderno.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spin_Order_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listorderno != null) && (Listorderno.size() > 0)) {

                    Orderno = Listorderno.get(position);
                    Log.i(TAG, "Line: " + Orderno);
                    Getstyle Style = new Getstyle();
                    Style.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spin_style.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Liststyle != null) && (Liststyle.size() > 0)) {

                    Style = Liststyle.get(position);
                    Log.i(TAG, "Line: " + Style);
                    Getcolor Color = new Getcolor();
                    Color.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spin_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listcolor != null) && (Listcolor.size() > 0)) {

                    Color = Listcolor.get(position);
                    Log.i(TAG, "Line: " + Color);
                    Getsize Size = new Getsize();
                    Size.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spin_Gr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listgr != null) && (Listgr.size() > 0)) {

                    GR = Listgr.get(position);
                    Log.i(TAG, "Line: " + GR);

                    Getdeceftlocation defectlocation1 = new Getdeceftlocation();
                    defectlocation1.execute();

                } else {

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spin_defectloc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listdefectloc != null) && (Listdefectloc.size() > 0)) {

                    defectlocation = Listdefectloc.get(position);
                    Log.i(TAG, "Line: " + defectlocation);
                    Getdefect defect = new Getdefect();
                    defect.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spin_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listsize != null) && (Listsize.size() > 0)) {
//                    s = Listsize.get(position);
                    Log.i(TAG, "Line: " + Listsize);
                    Gettotalproductionphr Pr4 = new Gettotalproductionphr();
                    Pr4.execute();
                    Gettotaldefectphr Pr2 = new Gettotaldefectphr();
                    Pr2.execute();
                    Gettotalprodu Pr = new Gettotalprodu();
                    Pr.execute();
                    Gettarget target = new Gettarget();
                    target.execute();
                    Gettotaldefect defect = new Gettotaldefect();
                    defect.execute();
                    Getprolock pro=new Getprolock();
                    pro.execute();
                    GetTrework trework =new GetTrework();
                    trework.execute();
                } else {

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Net.setInputType(InputType.TYPE_NULL);
        Totaldefectphr.setInputType(InputType.TYPE_NULL);
        Totalproductionphr.setInputType(InputType.TYPE_NULL);
        Pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    Net.setText("");
                    Net.setBackgroundColor(android.graphics.Color.WHITE);
                    textt = Spin_color.getSelectedItem().toString();
                    if (textt == "") {
                        Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please select the values  ", Toast.LENGTH_LONG).show();
                    } else {
//                        {
                            String B = bulk.getText().toString();
                            if(B.equals("")){
                                String First1 = Prolock.getText().toString();
                                String Secount1 ="1" ;
                                int result11 = Integer.parseInt(First1);
                                int result21 = Integer.parseInt(Secount1);
                                if (result11 >= result21) {
                                    getpass pass = new getpass();
                                    pass.execute();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please Check the loading",     Toast.LENGTH_LONG).show();
                                }
                            }else {
                                String First = bulk.getText().toString();
                                String Secount = Prolock.getText().toString();
                                int result1 = Integer.parseInt(First);
                                int result2 = Integer.parseInt(Secount);
                                if (result1 <= result2) {
                                    getpass pass = new getpass();
                                    pass.execute();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please Check the Input Value  ", Toast.LENGTH_LONG).show();
                                }}

                    }
                } else {
                    Net.setBackgroundColor(android.graphics.Color.RED);

                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(android.graphics.Color.WHITE);

                }
                bulk.setText("");
            }
        });
        Rework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    Net.setText("");
                    Net.setBackgroundColor(android.graphics.Color.WHITE);
                    String textString = Spin_color.getSelectedItem().toString();
                    if (textString != null && textString.trim().length() == 0) {
                        Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please select the values  ", Toast.LENGTH_LONG).show();
                    } else {
                        String First1 = Prolock.getText().toString();
                        String Secount1 ="1" ;
                        int result11 = Integer.parseInt(First1);
                        int result21 = Integer.parseInt(Secount1);
                        if (result11 > result21) {
                            getRework Fail = new getRework();
                            Fail.execute();
                        }
                        else {

                            Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please Check the loading  ", Toast.LENGTH_LONG).show();
                        }
                    }}else {
                    Net.setBackgroundColor(android.graphics.Color.RED);
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(android.graphics.Color.WHITE);
                }
            }
        });
        Production.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Packing.this, Packingstatus.class);
                startActivity(intent);
            }
        });
        fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    Net.setText("");
                    Net.setBackgroundColor(android.graphics.Color.WHITE);
                    String textString = Spin_color.getSelectedItem().toString();
                    if (textString != null && textString.trim().length() == 0) {
                        Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please select the values  ", Toast.LENGTH_LONG).show();
                    } else {
                        getFail Fail = new getFail();
                        Fail.execute();
                    }}else {
                    Net.setBackgroundColor(android.graphics.Color.RED);
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(android.graphics.Color.WHITE);
                }
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        checkConnection();
        //Keyboard Hidden
        setupUI(findViewById(R.id.parent));
    }
    //Keyboard hidden when i click out of textbox
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(Packing.this);
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


    public void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Packing.this);
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

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.user: {

                Getunit unit = new Getunit();
                unit.execute();
                Getopr opr = new Getopr();
                opr.execute();
                Getgr gr = new Getgr();
                gr.execute();
                bulk.setText("");

                return true;
            }


        }
        return true;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
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

    private class getpass extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();

            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();

            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            h = bulk.getText().toString();
            l = Oprname.getSelectedItem().toString();

            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_pass);
                Request.addProperty("unit", a);

                Request.addProperty("buyer", c);
                Request.addProperty("orderno", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);
                Request.addProperty("size", f);
                Request.addProperty("valuecount", h);
                Request.addProperty("section", section);
                Request.addProperty("supervisor", l);


                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_pass, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                Log.i(TAG, "Production: " + resultString);
            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "\uD83D\uDE0A Data Saved ", Toast.LENGTH_LONG).show();

            Gettotalproductionphr Pr4 = new Gettotalproductionphr();
            Pr4.execute();
            Getprolock pro=new Getprolock();
            pro.execute();
            Gettotalprodu Pr = new Gettotalprodu();
            Pr.execute();
        }
    }
    private class getRework extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();


            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();

            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            h = bulk.getText().toString();
            l = Oprname.getSelectedItem().toString();

            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_Rework);
                Request.addProperty("unit", a);

                Request.addProperty("buyer", c);
                Request.addProperty("orderno", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);
                Request.addProperty("size", f);
                Request.addProperty("valuecount", h);
                Request.addProperty("section", section);
                Request.addProperty("supervisor", l);


                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_Rework, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                Log.i(TAG, "Production: " + resultString);
            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "\uD83D\uDE0A Data Saved ", Toast.LENGTH_LONG).show();

            Gettotalproductionphr Pr4 = new Gettotalproductionphr();
            Pr4.execute();
            Gettotalprodu Pr = new Gettotalprodu();
            Pr.execute();

        }
    }
    private class Gettotalprodu extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();

            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_totalprodu);
                Request.addProperty("unit", a);

                Request.addProperty("section", section);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_totalprodu, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null) {
                    Listtotalprodu = Arrays.asList(result);
                }
            /*    else {
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
            if ((Listtotalprodu != null) && (Listtotalprodu.size() > 0)) {
                PRo.setText(Listtotalprodu.get(0));
            } else {


            }

        }
    }

    private class getFail extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();

            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();
            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            j = Spin_defectloc.getSelectedItem().toString();
            l = Oprname2.getSelectedItem().toString();
            k = Spin_defect.getSelectedItem().toString();

            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_Fail);
                Request.addProperty("unit", a);
                Request.addProperty("buyer", c);
                Request.addProperty("orderno", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);
                Request.addProperty("size", f);
                Request.addProperty("section", section);
                Request.addProperty("supervisor", l);
                Request.addProperty("defectlocation", j);
                Request.addProperty("defectname", k);


                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_Fail, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                Log.i(TAG, "Production: " + resultString);
            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "\uD83D\uDE0A Data Saved ", Toast.LENGTH_LONG).show();

            Gettotaldefectphr Pr2 = new Gettotaldefectphr();
            Pr2.execute();
            Gettotaldefect defect = new Gettotaldefect();
            defect.execute();
        }
    }
    private class Gettotaldefect extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();

            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_totaldefect);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
                Request.addProperty("section", section);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_totaldefect, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null) {
                    Listdefectr = Arrays.asList(result);
                }
            /*    else {
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
            if ((Listdefectr != null) && (Listdefectr.size() > 0)) {
                defect.setText(Listdefectr.get(0));
            } else {


            }

        }
    }

    private class Getunit extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Packing.this);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listunit);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_unit.setAdapter(dataAdapter);
            }

        }
    }

    private class Getopr extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_opr);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_opr, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listopr = Arrays.asList(result);
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
            if ((Listopr != null) && (Listopr.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listopr);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Oprname.setAdapter(dataAdapter);
            }

        }
    }
    private class Getprolock extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            f = Spin_size.getSelectedItem().toString();


            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_Prolock);
                Request.addProperty("unit", a);
                Request.addProperty("section", section);
                Request.addProperty("buyer", c);
                Request.addProperty("po", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);
                Request.addProperty("size", f);



                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_Prolock, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null) {
                    Listprolock = Arrays.asList(result);
                }
            /*    else {
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
            if ((Listprolock != null) && (Listprolock.size() > 0)) {
                Prolock.setText(Listprolock.get(0));
            } else {


            }


        }
    }
    private class GetTrework extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();
            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            h = bulk.getText().toString();
            l = Oprname.getSelectedItem().toString();

            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_Trework);
                Request.addProperty("unit", a);
                Request.addProperty("buyer",c);
                Request.addProperty("po", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);
                Request.addProperty("size", f);
                Request.addProperty("section", section);



                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_Trework, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null) {
                    ListTrework = Arrays.asList(result);
                }
            /*    else {
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
            if ((ListTrework != null) && (ListTrework.size() > 0)) {
                Trework.setText(ListTrework.get(0));
            } else {


            }

        }
    }
    private class Getqc extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_qc);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_qc, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listqc = Arrays.asList(result);
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
            if ((Listqc != null) && (Listqc.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listqc);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Oprname2.setAdapter(dataAdapter);
            }

        }
    }
    private class Getbuyer extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            unit = Spin_unit.getSelectedItem().toString();

            super.onPreExecute();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_buyer);
                Request.addProperty("unit", unit);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listbuyer);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_buyer.setAdapter(dataAdapter);
            }

        }
    }
    private class Getorderno extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            unit = Spin_unit.getSelectedItem().toString();

            super.onPreExecute();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_orderno);

                Request.addProperty("unit", unit);
                Request.addProperty("strbuyer", Buyer);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_orderno, soapEnvelope);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listorderno);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_Order_no.setAdapter(dataAdapter);
            }

        }
    }
    private class Getstyle extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            unit = Spin_unit.getSelectedItem().toString();

            super.onPreExecute();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_style);
                Request.addProperty("strbuyer", Buyer);
                Request.addProperty("strorderno", Orderno);
                Request.addProperty("unit", unit);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Liststyle);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_style.setAdapter(dataAdapter);
            }

        }
    }
    private class Getcolor extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            unit = Spin_unit.getSelectedItem().toString();

            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_color);
                Request.addProperty("strbuyer", Buyer);
                Request.addProperty("strorderno", Orderno);
                Request.addProperty("strstyle", Style);
                Request.addProperty("unit", unit);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_color, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "Color " + resultString);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listcolor);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_color.setAdapter(dataAdapter);


            }

        }
    }
    private class Getsize extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            unit = Spin_unit.getSelectedItem().toString();

            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_size);
                Request.addProperty("strbuyer", Buyer);
                Request.addProperty("strorderno", Orderno);
                Request.addProperty("strstyle", Style);
                Request.addProperty("strcolor", Color);
                Request.addProperty("unit", unit);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_size, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "Color " + resultString);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listsize);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_size.setAdapter(dataAdapter);

//                Gettotaldefect Pr1 = new Gettotaldefect();
//                Pr1.execute();

//                Gettotalproduction Pr3 = new Gettotalproduction();
//                Pr3.execute();
//                Gettotalproductionphr Pr4 = new Gettotalproductionphr();
//                Pr4.execute();
//                Gettotaldefectphr Pr2 = new Gettotaldefectphr();
//                Pr2.execute();
            }
            alert();
        }
    }
    private class Getdeceftlocation extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_defectlocation);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                Request.addProperty("strgarmenttype", GR);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_defectlocation, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listdefectloc = Arrays.asList(result);
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
            if ((Listdefectloc != null) && (Listdefectloc.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listdefectloc);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_defectloc.setAdapter(dataAdapter);
            }

        }
    }
    private class Getgr extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_gr);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_gr, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listgr = Arrays.asList(result);
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
            if ((Listgr != null) && (Listgr.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listgr);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_Gr.setAdapter(dataAdapter);
            }

        }
    }
    private class Getdefect extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_defect);


                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_defect, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();

                Log.i(TAG, "Color " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null && result.length > 0) {
                    Listdefect = Arrays.asList(result);
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
            if ((Listdefect != null) && (Listdefect.size() > 0)) {
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Packing.this, android.R.layout.simple_spinner_item, Listdefect);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_defect.setAdapter(dataAdapter);
            }

        }
    }
    private class Gettarget extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();

            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();

            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            h = bulk.getText().toString();

            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_target);
                Request.addProperty("unit", a);

                Request.addProperty("buyer", c);
                Request.addProperty("orderno", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);


                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_target, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null) {
                    Listtarget = Arrays.asList(result);
                }
            /*    else {
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
            if ((Listtarget != null) && (Listtarget.size() > 0)) {
                Target.setText(Listtarget.get(0));
            } else {


            }

        }
    }
    private class Gettotaldefectphr extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();

            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();

            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            h = bulk.getText().toString();

            j = Spin_defectloc.getSelectedItem().toString();
            k = Spin_defect.getSelectedItem().toString();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_totaldefectphr);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
                Request.addProperty("buyer", c);
                Request.addProperty("orderno", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);
                Request.addProperty("size", f);
                Request.addProperty("section", section);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_totaldefectphr, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null) {
                    Listtotaldefectphr = Arrays.asList(result);
                }
            /*    else {
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
            if ((Listtotaldefectphr != null) && (Listtotaldefectphr.size() > 0)) {
                Totaldefectphr.setText(Listtotaldefectphr.get(0));
            } else {


            }

        }
    }
    private class Gettotalproductionphr extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getSelectedItem().toString();

            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();

            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            h = bulk.getText().toString();

            j = Spin_defectloc.getSelectedItem().toString();
            k = Spin_defect.getSelectedItem().toString();
            progressDialog = new ProgressDialog(Packing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_totalproductionphr);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
                Request.addProperty("buyer", c);
                Request.addProperty("orderno", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);
                Request.addProperty("size", f);
                Request.addProperty("section", section);

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_totalproductionphr, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null) {
                    Listtotalproductionphr = Arrays.asList(result);
                }
            /*    else {
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
            if ((Listtotalproductionphr != null) && (Listtotalproductionphr.size() > 0)) {
                Totalproductionphr.setText(Listtotalproductionphr.get(0));
            } else {


            }

        }
    }

}
