package com.myapplication1;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class Sewing extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    private final String NAMESPACE = "http://tempuri.org/";

    

    private final String SOAP_ACTION_buyer = "http://tempuri.org/cbGetbuyer";
    private final String METHOD_NAME_buyer = "cbGetbuyer";
    private final String SOAP_ACTION_orderno = "http://tempuri.org/cbGetorderno";
    private final String METHOD_NAME_orderno = "cbGetorderno";
    private final String SOAP_ACTION_style = "http://tempuri.org/cbGetstyle";
    private final String METHOD_NAME_style = "cbGetstyle";
    private final String SOAP_ACTION_color = "http://tempuri.org/cbGetcolor";
    private final String METHOD_NAME_color = "cbGetcolor";
    private final String SOAP_ACTION_size = "http://tempuri.org/cbGetsize";
    private final String METHOD_NAME_size = "cbGetsize";
    private final String SOAP_ACTION_defectlocation = "http://tempuri.org/cbGetdefectlocation";
    private final String METHOD_NAME_defectlocation = "cbGetdefectlocation";
    private final String SOAP_ACTION_defect = "http://tempuri.org/cbGetdefectname";
    private final String METHOD_NAME_defect = "cbGetdefectname";
    private final String SOAP_ACTION_pass = "http://tempuri.org/cbproductionentrySEWING";
    private final String METHOD_NAME_pass = "cbproductionentrySEWING";
    private final String SOAP_ACTION_Fail = "http://tempuri.org/cbdefectentrySEWING";
    private final String METHOD_NAME_Fail = "cbdefectentrySEWING";
    private final String SOAP_ACTION_totalprodu = "http://tempuri.org/cbGettotalproductionperdayunitline";
    private final String METHOD_NAME_totalprodu = "cbGettotalproductionperdayunitline";
    private final String SOAP_ACTION_totaldefect = "http://tempuri.org/cbGettotaldefectcountunitline";
    private final String METHOD_NAME_totaldefect = "cbGettotaldefectcountunitline";
    private final String SOAP_ACTION_Rework = "http://tempuri.org/cbreworkentrySEWING";
    private final String METHOD_NAME_Rework = "cbreworkentrySEWING";
    private final String SOAP_ACTION_opr = "http://tempuri.org/cbGetsupervisor";
    private final String METHOD_NAME_opr = "cbGetsupervisor";
    private final String SOAP_ACTION_gr = "http://tempuri.org/cbGetgr";
    private final String METHOD_NAME_gr = "cbGetgr";
    private final String SOAP_ACTION_Tpro = "http://tempuri.org/cbGetbalanceproductiontopass";
    private final String METHOD_NAME_Tpro = "cbGetbalanceproductiontopass";
    private final String SOAP_ACTION_Trework = "http://tempuri.org/cbGettotalreworkforpo";
    private final String METHOD_NAME_Trework = "cbGettotalreworkforpo";
    private final String SOAP_ACTION_target = "http://tempuri.org/cbGettarget";
    private final String METHOD_NAME_target = "cbGettarget";
    private final String SOAP_ACTION_qc = "http://tempuri.org/cbGetQC";
    private final String METHOD_NAME_qc = "cbGetQC";
    private final String SOAP_ACTION_backlock = "http://tempuri.org/cbbacklock";
    private final String METHOD_NAME_backlock = "cbbacklock";
    Dialog myDialog;
    String Buyer;
    String Orderno;
    String Color;
    String Style;
    String GR, textt;
    String section;
    EditText Net;
    String Line2, test;
    String Line, unit, Defectentry;
    TextView sec, PRo, Target, Tpro, Trework, Tobeachived, Backlock, Spin_defectloc;
    String a, b, c, d, e, f, g, h, i, j, k, l, s;
    String A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V, W, X, Y, Z, AA, BB, CC, DD,
            EE, FF, GG, HH, II, JJ, KK, LL, MM, NN, OO, PP, QQ, RR, SS, TT, UU, VV, WW, XX, YY, ZZ, AAA, BBB, CCC, DDD, Xxx;
    String A1, B1, C1, D1, E1, F1, G1, H1, I1, J1, K1, L1, M1, N1, O1, P1, Q1, S1, T1, U1, V1, W1, X1, Y1, Z1, AA1, BB1, CC1, DD1,
            EE1, FF1, GG1, HH1, II1, JJ1, KK1, LL1, MM1, NN1, OO1, PP1, QQ1, RR1, SS1, TT1, UU1, VV1, WW1, XX1, YY1, ZZ1, AAA1, BBB1, CCC1, DDD1, Xxx1;

    String Sunit, Sline;
    Spinner  Spin_buyer, Spin_Order_no, Spin_color, Spin_size, Spin_section, Spin_style,  Spin_Gr;
    EditText bulk;
    String[] listItems;
    boolean[] checkedItems;
    TextView defect, Blink, Spin_unit, Spin_line, Spin_defect,Oprname2,Oprname;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    Button Pass, fail, Rework, Lines, Quality, Go;
    TextView First1, First2, First3, First4, First5, First6, First7, First8, First9, First10,
            First11, First12, First13, First14, First15, First16, First17, First18, First19, First20,
            First21, First22, First23, First24, First25, First26, First27, First28, First29, First30,
            First31, First32, First33, First34, First35, First36, First37, First38, First39, First40,
            First41, First42, First43, First44, First45, First46, First47, First48, First49, First50,
            First51, First52, First53, First54, First55, First56;
    TextView Secound1, Secound2, Secound3, Secound4, Secound5, Secound6, Secound7, Secound8, Secound9, Secound10,
            Secound11, Secound12, Secound13, Secound14, Secound15, Secound16, Secound17, Secound18, Secound19, Secound20,
            Secound21, Secound22, Secound23, Secound24, Secound25, Secound26, Secound27, Secound28, Secound29, Secound30,
            Secound31, Secound32, Secound33, Secound34, Secound35, Secound36, Secound37, Secound38, Secound39, Secound40,
            Secound41, Secound42, Secound43, Secound44, Secound45, Secound46, Secound47, Secound48, Secound49, Secound50,
            Secound51, Secound52, Secound53, Secound54, Secound55, Secound56;
    SoapPrimitive resultString;
    SoapObject resultString5;
    String TAG = "Response";
    //Array list
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
    List<String> Listtotalprodu = null;
    List<String> Listopr = null;
    List<String> Listqc = null;
    List<String> Listgr = null;
    List<String> Listtarget = null;
    List<String> Listdefectr = null;
    List<String> ListTpro = null;
    List<String> ListTrework = null;
    List<String> Listbacklock = null;
    List<String> Lists = null;
    private GestureDetectorCompat gestureDetectorCompat;
    private GoogleApiClient client;
    private long mBackPressed;
    //Zoom Control
    //webview.getSettings().setSupportZoom(true);       //Zoom Control on web

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
//    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
//        //handle 'swipe left' action only
//        @Override
//        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
//
//
//            if (event2.getX() < event1.getX()) {
//                Toast.makeText(getBaseContext(), "defect", Toast.LENGTH_SHORT).show();
//
//                //switch another activity
//                Intent intent = new Intent(Sewing.this, Rejection.class);
//                startActivity(intent);
//            }
//
//            return true;
//        }
//    }

//    public void ShowPopup(View v) {
//        TextView txtclose;
//        Button btnFollow;
//        myDialog.setContentView(R.layout.custompopoup);
//        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
//        txtclose.setText("M");
////        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
//        txtclose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                myDialog.dismiss();
//            }
//        });
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        myDialog.show();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sewing);

        //ID to Declaration
        Spin_unit = (TextView) findViewById(R.id.spinner_unit);
        Spin_line = (TextView) findViewById(R.id.spinner_line);
        Spin_buyer = (Spinner) findViewById(R.id.spinner_buyer);
        Spin_Order_no = (Spinner) findViewById(R.id.spinner_Order_No);
        Spin_style = (Spinner) findViewById(R.id.spinner_style);
        Spin_color = (Spinner) findViewById(R.id.spinner_color);
        Spin_size = (Spinner) findViewById(R.id.spinner_size);
        Spin_defectloc = (TextView) findViewById(R.id.spinner_sizelo);
        Spin_defect = (TextView) findViewById(R.id.spinner_size2);
        Oprname = (TextView) findViewById(R.id.spinner_name2);
        Oprname2 = (TextView) findViewById(R.id.spinner_name);
        Spin_Gr = (Spinner) findViewById(R.id.spinner_gar);
        Net = (EditText) findViewById(R.id.editText);
        Pass = (Button) findViewById(R.id.btnpass);
        Rework = (Button) findViewById(R.id.button9);
        fail = (Button) findViewById(R.id.btnFail);
        bulk = (EditText) findViewById(R.id.Bulk);
        sec = (TextView) findViewById(R.id.Sec);
        Tobeachived = (TextView) findViewById(R.id.textView28);
        Target = (TextView) findViewById(R.id.textView4);
        Tpro = (TextView) findViewById(R.id.Totelpro);
        Trework = (TextView) findViewById(R.id.textView16);
        PRo = (TextView) findViewById(R.id.textView5);
        defect = (TextView) findViewById(R.id.textView24);
        Rework = (Button) findViewById(R.id.button9);
        Lines = (Button) findViewById(R.id.button27);
        Quality = (Button) findViewById(R.id.button28);
        Blink = (TextView) findViewById(R.id.blink2);
        Backlock = (TextView) findViewById(R.id.blink);
        Go = (Button) findViewById(R.id.go);

//        First51	=	(TextView) myDialog.findViewById(R.id.	first51);
//        First52	=	(TextView) myDialog.findViewById(R.id.	first52);
//        First53	=	(TextView) myDialog.findViewById(R.id.	first53);
//        First54	=	(TextView) myDialog.findViewById(R.id.	first54);
//        First55	=	(TextView) myDialog.findViewById(R.id.	first55);


        // Value get function from Webservices
//        Getunit line = new Getunit();
//        line.execute();


        // Section Value from Main module to here

//        Bundle bundle1 = getIntent().getExtras();
//        section = bundle1.getString("value");
        section = "SEWING";

        Bundle bundle = getIntent().getExtras();
        Sunit = bundle.getString("unit");

        Spin_unit.setText(Sunit);
        Bundle bundleb = getIntent().getExtras();
        Sline = bundleb.getString("line");
        Spin_line.setText(Sline);
        sec.setText(section);

        String str = Build.SERIAL;
        sec.setText(str);
        //Spinner one by one function
        // values get for Line
//        Spin_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if ((Listunit!= null) && (Listunit.size() > 0)) {
//                    Line2 = Listunit.get(position);
//                    Log.i(TAG, "Line: " + Line2);
//                    Getline orderno = new Getline();
//                    orderno.execute();
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        // values get for buyer and total defect
//        Spin_line.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if ((Listline != null) && (Listline.size() > 0)) {
//
//                    Line = Listline.get(position);
//                    Log.i(TAG, "Line: " + Line);
//                    Getbuyer orderno = new Getbuyer();
//                    orderno.execute();
//                    Gettotaldefect defect = new Gettotaldefect();
//                    defect.execute();
//
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        // values get for order number


        Getbuyer orderno = new Getbuyer();
        orderno.execute();
        Gettotaldefect defect = new Gettotaldefect();
        defect.execute();
        Getopr opr = new Getopr();
        opr.execute();
        Getqc qc = new Getqc();
        qc.execute();
        Getgr gr = new Getgr();
        gr.execute();
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
        // values get for Style
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
        // Values get for color
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
        // values get for Size,target,Total rework, total productoin, Total balance production and Backlock
        Spin_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listcolor != null) && (Listcolor.size() > 0)) {

                    Color = Listcolor.get(position);
                    Log.i(TAG, "Line: " + Color);
                    Getsize Size = new Getsize();
                    Size.execute();
                    Gettotalprodu Pr = new Gettotalprodu();
                    Pr.execute();
                    Gettarget target = new Gettarget();
                    target.execute();
                    GetTrework trework = new GetTrework();
                    trework.execute();

                    Getbacklock BK = new Getbacklock();
                    BK.execute();

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // values get Defect location and defect
        Spin_Gr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((Listgr != null) && (Listgr.size() > 0)) {

                    GR = Listgr.get(position);
                    Log.i(TAG, "Line: " + GR);

                    Getdeceftlocation defectlocation1 = new Getdeceftlocation();
                    defectlocation1.execute();
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

                    s = Listsize.get(position);
                    Log.i(TAG, "Line: " + Listsize);

                    GetTpro tpro = new GetTpro();
                    tpro.execute();
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Net.setInputType(InputType.TYPE_NULL);
        //Values pass Android to server via Service
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
                        String B = bulk.getText().toString();
                        if (B.equals("")) {
                            String First1 = Tpro.getText().toString();
                            String Secount1 = "1";
                            int result11 = Integer.parseInt(First1);
                            int result21 = Integer.parseInt(Secount1);
                            if (result11 >= result21) {
                                getpass pass = new getpass();
                                pass.execute();
                            } else {
                                Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please Check the loading", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            String First = bulk.getText().toString();
                            String Secount = Tpro.getText().toString();
                            int result1 = Integer.parseInt(First);
                            int result2 = Integer.parseInt(Secount);
                            if (result1 <= result2) {
                                getpass pass = new getpass();
                                pass.execute();
                            } else {
                                Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please Check the Input Value  ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                } else {
                    Net.setBackgroundColor(android.graphics.Color.RED);
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(android.graphics.Color.WHITE);
                }
            }
        });
        //Values Fail Android to server via Service
        fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    Net.setText("");
                    Net.setBackgroundColor(android.graphics.Color.WHITE);
                    String textString1 = Spin_defect.getText().toString();
                    String textString2 = Spin_defectloc.getText().toString();
                    String textString = bulk.getText().toString();
                    if (textString1 == "") {
                        Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please select the Defect  ", Toast.LENGTH_LONG).show();
                    }else if(textString2 == ""){

                        Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please select the Defect location  ", Toast.LENGTH_LONG).show();
                    }
                    else{
                    if (textString != null && textString.trim().length() == 0) {
                        String First1 = Tpro.getText().toString();
                        String Secount1 = "0";
                        int result11 = Integer.parseInt(First1);
                        int result21 = Integer.parseInt(Secount1);
                        if (result11 > result21) {
                            getFail Fail = new getFail();
                            Fail.execute();
                        } else {
                            Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please Check the loading  ", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Bulk value not possible for FAIL ", Toast.LENGTH_LONG).show();
                    }
                } }else {
                    Net.setBackgroundColor(android.graphics.Color.RED);
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(android.graphics.Color.WHITE);
                }
            }
        });
        //Values Rework Android to server via Service
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
                        String First1 = Tpro.getText().toString();
                        String Secount1 = "0";
                        int result11 = Integer.parseInt(First1);
                        int result21 = Integer.parseInt(Secount1);
                        if (result11 > result21) {
                            getRework Fail = new getRework();
                            Fail.execute();
                        } else {

                            Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please Check the loading  ", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Net.setBackgroundColor(android.graphics.Color.RED);
                    Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Now You are in Offline ", Toast.LENGTH_LONG).show();
                    Net.setTextColor(android.graphics.Color.WHITE);
                }
            }
        });
//        Lines.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listItems = getResources().getStringArray(R.array.Select_One_defect);
//                checkedItems = new boolean[listItems.length];
//
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Sewing.this);
//                mBuilder.setTitle(R.string.dialog_title);
//                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
////                        if (isChecked) {
////                            if (!mUserItems.contains(position)) {
////                                mUserItems.add(position);
////                            }
////                        } else if (mUserItems.contains(position)) {
////                            mUserItems.remove(position);
////                        }
//
//                        if(isChecked){
//                            mUserItems.add(position);
//                        }else{
//                            mUserItems.remove((Integer.valueOf(position)));
//                        }
//                    }
//                });
//
//                mBuilder.setCancelable(true);
//                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//
//                        String item = "";
//                        for (int i = 0; i < mUserItems.size(); i++) {
//                            item = item + listItems[mUserItems.get(i)];
//                            if (i != mUserItems.size() - 1) {
//                                item = item + ", ";
//                            }
//                        }
//                        // String[] result = item.toString().split(",");
//
//                        Spin_defectloc.setText(item);
//                    }
//                });
//
//                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//
//                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        for (int i = 0; i < checkedItems.length; i++) {
//                            checkedItems[i] = false;
//                            mUserItems.clear();
//                            mItemSelected.setText("");
//                        }
//                    }
//                });
//
//                AlertDialog mDialog = mBuilder.create();
//                mDialog.show();
//
//            }
//        });
        Lines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sewing.this, Linestatus.class);
                startActivity(intent);
            }
        });
        Quality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sewing.this, Qualitystatus.class);
                startActivity(intent);
            }
        });


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        checkConnection();
        //Keyboard Hidden
        setupUI(findViewById(R.id.parent));
//        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        startBlinkingAnimation();
//For popup
        myDialog = new Dialog(this);
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
                    hideSoftKeyboard(Sewing.this);
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

    public void ShowPopup(View v) {
        final TextView txtclose;


        myDialog.setContentView(R.layout.custompopoup);
        // txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        //txtclose.setText("M");


        Spin_defectloc.setText("");
        Spin_defect.setText("");

        First1 = (TextView) myDialog.findViewById(R.id.first);
        First2 = (TextView) myDialog.findViewById(R.id.first2);
        First3 = (TextView) myDialog.findViewById(R.id.first3);
        First4 = (TextView) myDialog.findViewById(R.id.first4);
        First5 = (TextView) myDialog.findViewById(R.id.first5);
        First6 = (TextView) myDialog.findViewById(R.id.first6);
        First7 = (TextView) myDialog.findViewById(R.id.first7);
        First8 = (TextView) myDialog.findViewById(R.id.first8);
        First9 = (TextView) myDialog.findViewById(R.id.first9);
        First10 = (TextView) myDialog.findViewById(R.id.first10);
        First11 = (TextView) myDialog.findViewById(R.id.first11);
        First12 = (TextView) myDialog.findViewById(R.id.first12);
        First13 = (TextView) myDialog.findViewById(R.id.first13);
        First14 = (TextView) myDialog.findViewById(R.id.first14);
        First15 = (TextView) myDialog.findViewById(R.id.first15);
        First16 = (TextView) myDialog.findViewById(R.id.first16);
        First17 = (TextView) myDialog.findViewById(R.id.first17);
        First18 = (TextView) myDialog.findViewById(R.id.first18);
        First19 = (TextView) myDialog.findViewById(R.id.first19);
        First20 = (TextView) myDialog.findViewById(R.id.first20);
        First21 = (TextView) myDialog.findViewById(R.id.first21);
        First22 = (TextView) myDialog.findViewById(R.id.first22);
        First23 = (TextView) myDialog.findViewById(R.id.first23);
        First24 = (TextView) myDialog.findViewById(R.id.first24);
        First25 = (TextView) myDialog.findViewById(R.id.first25);
        First26 = (TextView) myDialog.findViewById(R.id.first26);
        First27 = (TextView) myDialog.findViewById(R.id.first27);
        First28 = (TextView) myDialog.findViewById(R.id.first28);
        First29 = (TextView) myDialog.findViewById(R.id.first29);
        First30 = (TextView) myDialog.findViewById(R.id.first30);
        First31 = (TextView) myDialog.findViewById(R.id.first31);
        First32 = (TextView) myDialog.findViewById(R.id.first32);
        First33 = (TextView) myDialog.findViewById(R.id.first33);
        First34 = (TextView) myDialog.findViewById(R.id.first34);
        First35 = (TextView) myDialog.findViewById(R.id.first35);
        First36 = (TextView) myDialog.findViewById(R.id.first36);
        First37 = (TextView) myDialog.findViewById(R.id.first37);
        First38 = (TextView) myDialog.findViewById(R.id.first38);
        First39 = (TextView) myDialog.findViewById(R.id.first39);
        First40 = (TextView) myDialog.findViewById(R.id.first40);
        First41 = (TextView) myDialog.findViewById(R.id.first41);
        First42 = (TextView) myDialog.findViewById(R.id.first42);
        First43 = (TextView) myDialog.findViewById(R.id.first43);
        First44 = (TextView) myDialog.findViewById(R.id.first44);
        First45 = (TextView) myDialog.findViewById(R.id.first45);
        First46 = (TextView) myDialog.findViewById(R.id.first46);
        First47 = (TextView) myDialog.findViewById(R.id.first47);
        First48 = (TextView) myDialog.findViewById(R.id.first48);
        First49 = (TextView) myDialog.findViewById(R.id.first49);
        First50 = (TextView) myDialog.findViewById(R.id.first50);
        First51 = (TextView) myDialog.findViewById(R.id.first51);
        First52 = (TextView) myDialog.findViewById(R.id.first53);


        First1.setText(A);
        First2.setText(B);
        First3.setText(C);
        First4.setText(D);
        First5.setText(E);
        First6.setText(F);
        First7.setText(G);
        First8.setText(H);
        First9.setText(I);
        First10.setText(J);
        First11.setText(K);
        First12.setText(L);
        First13.setText(M);
        First14.setText(N);
        First15.setText(O);
        First16.setText(P);
        First17.setText(Q);
        First19.setText(S);
        First20.setText(T);
        First21.setText(U);
        First22.setText(V);
        First18.setText(W);
        First23.setText(X);

        First24.setText(Y);
        First25.setText(Z);
        First26.setText(AA);
        First27.setText(BB);
        First28.setText(CC);
        First29.setText(DD);
        First30.setText(EE);
        First31.setText(FF);
        First32.setText(GG);
        First33.setText(HH);
        First34.setText(II);
        First35.setText(JJ);
        First36.setText(KK);
        First37.setText(LL);
        First38.setText(MM);
        First39.setText(NN);
        First40.setText(OO);
        First41.setText(PP);
        First42.setText(QQ);
        First43.setText(SS);
        First44.setText(TT);
        First45.setText(UU);
        First46.setText(VV);
        First47.setText(WW);
        First48.setText(XX);
        First49.setText(YY);
        First50.setText(ZZ);
        First51.setText(AAA);
        First52.setText(BBB);


        First1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(A);
                First1.setText("");
                First2.setText("");
                myDialog.dismiss();
            }
        });
        First2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(B);

                myDialog.dismiss();
            }
        });
        First3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(C);

                myDialog.dismiss();
            }
        });

        First4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(D);

                myDialog.dismiss();
            }
        });
        First5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(E);

                myDialog.dismiss();
            }
        });
        First6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(F);

                myDialog.dismiss();
            }
        });
        First7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(G);

                myDialog.dismiss();
            }
        });
        First8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(H);

                myDialog.dismiss();
            }
        });
        First9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(I);

                myDialog.dismiss();
            }
        });
        First10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(J);

                myDialog.dismiss();
            }
        });
        First11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(K);

                myDialog.dismiss();
            }
        });
        First12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(L);

                myDialog.dismiss();
            }
        });
        First13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(M);

                myDialog.dismiss();
            }
        });
        First14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(N);

                myDialog.dismiss();
            }
        });
        First15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(O);

                myDialog.dismiss();
            }
        });
        First16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(P);

                myDialog.dismiss();
            }
        });
        First17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(Q);

                myDialog.dismiss();
            }
        });
        First18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(W);

                myDialog.dismiss();
            }
        });
        First19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(S);

                myDialog.dismiss();
            }
        });
        First20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(T);

                myDialog.dismiss();
            }
        });
        First21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(U);

                myDialog.dismiss();
            }
        });
        First22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(V);

                myDialog.dismiss();
            }
        });
        First23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(X);

                myDialog.dismiss();
            }
        });
        First24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(Y);

                myDialog.dismiss();
            }
        });
        First25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(Z);

                myDialog.dismiss();
            }
        });
        First26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(AA);

                myDialog.dismiss();
            }
        });
        First27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(BB);

                myDialog.dismiss();
            }
        });
        First28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(CC);

                myDialog.dismiss();
            }
        });
        First29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(DD);

                myDialog.dismiss();
            }
        });
        First30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(EE);

                myDialog.dismiss();
            }
        });
        First31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(FF);

                myDialog.dismiss();
            }
        });
        First32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(GG);

                myDialog.dismiss();
            }
        });
        First33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(HH);

                myDialog.dismiss();
            }
        });
        First34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(II);

                myDialog.dismiss();
            }
        });
        First35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(JJ);

                myDialog.dismiss();
            }
        });
        First36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(KK);

                myDialog.dismiss();
            }
        });
        First37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(LL);

                myDialog.dismiss();
            }
        });
        First38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(MM);

                myDialog.dismiss();
            }
        });
        First39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(NN);

                myDialog.dismiss();
            }
        });
        First40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(OO);

                myDialog.dismiss();
            }
        });
        First41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(PP);

                myDialog.dismiss();
            }
        });
        First42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(QQ);

                myDialog.dismiss();
            }
        });
        First43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(SS);

                myDialog.dismiss();
            }
        });
        First44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(TT);

                myDialog.dismiss();
            }
        });
        First45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(UU);
                myDialog.dismiss();
            }
        });
        First46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(VV);

                myDialog.dismiss();
            }
        });
        First47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(WW);
                myDialog.dismiss();
            }
        });
        First48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(XX);
                myDialog.dismiss();
            }
        });
        First49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(YY);
                myDialog.dismiss();
            }
        });
        First50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(ZZ);
                myDialog.dismiss();
            }
        });
        First51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(AAA);
                myDialog.dismiss();
            }
        });
        First52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defectloc.setText(BBB);
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowPopup2(View v) {
        final TextView txtclose;


        myDialog.setContentView(R.layout.custompopoup2);
        // txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        //txtclose.setText("M");


        Spin_defect.setText("");

        Secound1 = (TextView) myDialog.findViewById(R.id.Secound);
        Secound2 = (TextView) myDialog.findViewById(R.id.Secound2);
        Secound3 = (TextView) myDialog.findViewById(R.id.Secound3);
        Secound4 = (TextView) myDialog.findViewById(R.id.Secound4);
        Secound5 = (TextView) myDialog.findViewById(R.id.Secound5);
        Secound6 = (TextView) myDialog.findViewById(R.id.Secound6);
        Secound7 = (TextView) myDialog.findViewById(R.id.Secound7);
        Secound8 = (TextView) myDialog.findViewById(R.id.Secound8);
        Secound9 = (TextView) myDialog.findViewById(R.id.Secound9);
        Secound10 = (TextView) myDialog.findViewById(R.id.Secound10);
        Secound11 = (TextView) myDialog.findViewById(R.id.Secound11);
        Secound12 = (TextView) myDialog.findViewById(R.id.Secound12);
        Secound13 = (TextView) myDialog.findViewById(R.id.Secound13);
        Secound14 = (TextView) myDialog.findViewById(R.id.Secound14);
        Secound15 = (TextView) myDialog.findViewById(R.id.Secound15);
        Secound16 = (TextView) myDialog.findViewById(R.id.Secound16);
        Secound17 = (TextView) myDialog.findViewById(R.id.Secound17);
        Secound18 = (TextView) myDialog.findViewById(R.id.Secound18);
        Secound19 = (TextView) myDialog.findViewById(R.id.Secound19);
        Secound20 = (TextView) myDialog.findViewById(R.id.Secound20);
        Secound21 = (TextView) myDialog.findViewById(R.id.Secound21);
        Secound22 = (TextView) myDialog.findViewById(R.id.Secound22);
        Secound23 = (TextView) myDialog.findViewById(R.id.Secound23);
        Secound24 = (TextView) myDialog.findViewById(R.id.Secound24);
        Secound25 = (TextView) myDialog.findViewById(R.id.Secound25);
        Secound26 = (TextView) myDialog.findViewById(R.id.Secound26);
        Secound27 = (TextView) myDialog.findViewById(R.id.Secound27);
        Secound28 = (TextView) myDialog.findViewById(R.id.Secound28);
        Secound29 = (TextView) myDialog.findViewById(R.id.Secound29);
        Secound30 = (TextView) myDialog.findViewById(R.id.Secound30);
        Secound31 = (TextView) myDialog.findViewById(R.id.Secound31);
        Secound32 = (TextView) myDialog.findViewById(R.id.Secound32);
        Secound33 = (TextView) myDialog.findViewById(R.id.Secound33);
        Secound34 = (TextView) myDialog.findViewById(R.id.Secound34);
        Secound35 = (TextView) myDialog.findViewById(R.id.Secound35);
        Secound36 = (TextView) myDialog.findViewById(R.id.Secound36);
        Secound37 = (TextView) myDialog.findViewById(R.id.Secound37);
        Secound38 = (TextView) myDialog.findViewById(R.id.Secound38);
        Secound39 = (TextView) myDialog.findViewById(R.id.Secound39);
        Secound40 = (TextView) myDialog.findViewById(R.id.Secound40);
        Secound41 = (TextView) myDialog.findViewById(R.id.Secound41);
        Secound42 = (TextView) myDialog.findViewById(R.id.Secound42);
        Secound43 = (TextView) myDialog.findViewById(R.id.Secound43);
        Secound44 = (TextView) myDialog.findViewById(R.id.Secound44);
        Secound45 = (TextView) myDialog.findViewById(R.id.Secound45);
        Secound46 = (TextView) myDialog.findViewById(R.id.Secound46);
        Secound47 = (TextView) myDialog.findViewById(R.id.Secound47);
        Secound48 = (TextView) myDialog.findViewById(R.id.Secound48);
        Secound49 = (TextView) myDialog.findViewById(R.id.Secound49);
        Secound50 = (TextView) myDialog.findViewById(R.id.Secound50);
        Secound51 = (TextView) myDialog.findViewById(R.id.Secound51);
        Secound52 = (TextView) myDialog.findViewById(R.id.Secound53);


        Secound1.setText(A1);
        Secound2.setText(B1);
        Secound3.setText(C1);
        Secound4.setText(D1);
        Secound5.setText(E1);
        Secound6.setText(F1);
        Secound7.setText(G1);
        Secound8.setText(H1);
        Secound9.setText(I1);
        Secound10.setText(J1);
        Secound11.setText(K1);
        Secound12.setText(L1);
        Secound13.setText(M1);
        Secound14.setText(N1);
        Secound15.setText(O1);
        Secound16.setText(P1);
        Secound17.setText(Q1);
        Secound19.setText(S1);
        Secound20.setText(T1);
        Secound21.setText(U1);
        Secound22.setText(V1);
        Secound18.setText(W1);
        Secound23.setText(X1);

        Secound24.setText(Y1);
        Secound25.setText(Z1);
        Secound26.setText(AA1);
        Secound27.setText(BB1);
        Secound28.setText(CC1);
        Secound29.setText(DD1);
        Secound30.setText(EE1);
        Secound31.setText(FF1);
        Secound32.setText(GG1);
        Secound33.setText(HH1);
        Secound34.setText(II1);
        Secound35.setText(JJ1);
        Secound36.setText(KK1);
        Secound37.setText(LL1);
        Secound38.setText(MM1);
        Secound39.setText(NN1);
        Secound40.setText(OO1);
        Secound41.setText(PP1);
        Secound42.setText(QQ1);
        Secound43.setText(SS1);
        Secound44.setText(TT1);
        Secound45.setText(UU1);
        Secound46.setText(VV1);
        Secound47.setText(WW1);
        Secound48.setText(XX1);
        Secound49.setText(YY1);
        Secound50.setText(ZZ1);
        Secound51.setText(AAA1);
        Secound52.setText(BBB1);


        Secound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(A1);

                myDialog.dismiss();
            }
        });
        Secound2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(B1);

                myDialog.dismiss();
            }
        });
        Secound3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(C1);

                myDialog.dismiss();
            }
        });

        Secound4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(D1);

                myDialog.dismiss();
            }
        });
        Secound5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(E1);

                myDialog.dismiss();
            }
        });
        Secound6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(F1);

                myDialog.dismiss();
            }
        });
        Secound7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(G1);

                myDialog.dismiss();
            }
        });
        Secound8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(H1);

                myDialog.dismiss();
            }
        });
        Secound9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(I1);

                myDialog.dismiss();
            }
        });
        Secound10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(J1);

                myDialog.dismiss();
            }
        });
        Secound11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(K1);

                myDialog.dismiss();
            }
        });
        Secound12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(L1);

                myDialog.dismiss();
            }
        });
        Secound13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(M1);

                myDialog.dismiss();
            }
        });
        Secound14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(N1);

                myDialog.dismiss();
            }
        });
        Secound15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(O1);

                myDialog.dismiss();
            }
        });
        Secound16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(P1);

                myDialog.dismiss();
            }
        });
        Secound17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(Q1);

                myDialog.dismiss();
            }
        });
        Secound18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(W1);

                myDialog.dismiss();
            }
        });
        Secound19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(S1);

                myDialog.dismiss();
            }
        });
        Secound20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(T1);

                myDialog.dismiss();
            }
        });
        Secound21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(U1);

                myDialog.dismiss();
            }
        });
        Secound22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(V1);

                myDialog.dismiss();
            }
        });
        Secound23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(X1);

                myDialog.dismiss();
            }
        });
        Secound24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(Y1);

                myDialog.dismiss();
            }
        });
        Secound25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(Z1);

                myDialog.dismiss();
            }
        });
        Secound26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(AA1);

                myDialog.dismiss();
            }
        });
        Secound27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(BB1);

                myDialog.dismiss();
            }
        });
        Secound28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(CC1);

                myDialog.dismiss();
            }
        });
        Secound29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(DD1);

                myDialog.dismiss();
            }
        });
        Secound30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(EE1);

                myDialog.dismiss();
            }
        });
        Secound31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(FF1);

                myDialog.dismiss();
            }
        });
        Secound32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(GG1);

                myDialog.dismiss();
            }
        });
        Secound33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(HH1);

                myDialog.dismiss();
            }
        });
        Secound34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(II1);

                myDialog.dismiss();
            }
        });
        Secound35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(JJ1);

                myDialog.dismiss();
            }
        });
        Secound36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(KK1);

                myDialog.dismiss();
            }
        });
        Secound37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(LL1);

                myDialog.dismiss();
            }
        });
        Secound38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(MM1);

                myDialog.dismiss();
            }
        });
        Secound39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(NN1);

                myDialog.dismiss();
            }
        });
        Secound40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(OO1);

                myDialog.dismiss();
            }
        });
        Secound41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(PP1);

                myDialog.dismiss();
            }
        });
        Secound42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(QQ1);

                myDialog.dismiss();
            }
        });
        Secound43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(SS1);

                myDialog.dismiss();
            }
        });
        Secound44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(TT1);

                myDialog.dismiss();
            }
        });
        Secound45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(UU1);
                myDialog.dismiss();
            }
        });
        Secound46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(VV1);

                myDialog.dismiss();
            }
        });
        Secound47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(WW1);
                myDialog.dismiss();
            }
        });
        Secound48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(XX1);
                myDialog.dismiss();
            }
        });
        Secound49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(YY1);
                myDialog.dismiss();
            }
        });
        Secound50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(ZZ1);
                myDialog.dismiss();
            }
        });
        Secound51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(AAA1);
                myDialog.dismiss();
            }
        });
        Secound52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin_defect.setText(BBB1);
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        myDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Sewing.this);
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

    public void startBlinkingAnimation() {

        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
        Blink.startAnimation(startAnimation);
        Tobeachived.startAnimation(startAnimation);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.user: {

//                Getunit unit = new Getunit();
//                unit.execute();
//                Getline line = new Getline();
//                line.execute();
                Getbuyer orderno = new Getbuyer();
                orderno.execute();
                Gettotaldefect defect = new Gettotaldefect();
                defect.execute();
                Getopr opr = new Getopr();
                opr.execute();
                Getqc qc = new Getqc();
                qc.execute();
                Getgr gr = new Getgr();
                gr.execute();
                bulk.setText("");
                return true;

            }
            case R.id.PRO: {


                return true;
            }
        }
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
            a = Spin_unit.getText().toString();
            b = Spin_line.getText().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();

            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            h = bulk.getText().toString();
            l = Oprname.getText().toString();

            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_pass);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
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


            Gettotalprodu Pr = new Gettotalprodu();
            Pr.execute();
            Gettotaldefect defect = new Gettotaldefect();
            defect.execute();
            Gettarget target = new Gettarget();
            target.execute();
            GetTpro tpro = new GetTpro();
            tpro.execute();
            Getbacklock BK = new Getbacklock();
            BK.execute();
            bulk.setText("");
        }
    }

    private class getFail extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getText().toString();
            b = Spin_line.getText().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();
            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();

            j = Spin_defectloc.getText().toString();
            l = Oprname2.getText().toString();
            k = Spin_defect.getText().toString();

            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_Fail);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
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


            Gettotaldefect defect = new Gettotaldefect();
            defect.execute();
        }
    }
//    private class Getunit extends AsyncTask<Void, Void, Void> {
//        ProgressDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(Sewing.this);
//            progressDialog.setMessage("Loading please wait !!!");
//            progressDialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            try {
//                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_unit);
//
//                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                soapEnvelope.dotNet = true;
//                soapEnvelope.setOutputSoapObject(Request);
//                HttpTransportSE transport = new HttpTransportSE(Config.URL);
//                transport.call(SOAP_ACTION_unit, soapEnvelope);
//                resultString = (SoapPrimitive) soapEnvelope.getResponse();
//                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
//
//                Log.i(TAG, "OPR Name " + resultString);
//                String[] result = resultString.toString().split(",");
//                if (result != null && result.length > 0) {
//                    Listunit = Arrays.asList(result);
//                }
//
//            } catch (Exception ex) {
//                Log.e(TAG, "Error: " + ex.getMessage());
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            progressDialog.dismiss();
//            if ((Listunit != null) && (Listunit.size() > 0)) {
//                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Sewing.this, android.R.layout.simple_spinner_item, Listunit);
//                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
//                Spin_unit.setAdapter(dataAdapter);
//            }
//
//        }
//    }
//    private class Getline extends AsyncTask<Void, Void, Void> {
//        ProgressDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(Sewing.this);
//            progressDialog.setMessage("Loading please wait !!!");
//            progressDialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            try {
//                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_line);
//
//                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                soapEnvelope.dotNet = true;
//                soapEnvelope.setOutputSoapObject(Request);
//                HttpTransportSE transport = new HttpTransportSE(Config.URL);
//                transport.call(SOAP_ACTION_line, soapEnvelope);
//                resultString = (SoapPrimitive) soapEnvelope.getResponse();
//                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
//
//                Log.i(TAG, "OPR Name " + resultString);
//                String[] result = resultString.toString().split(",");
//                if (result != null && result.length > 0) {
//                    Listline = Arrays.asList(result);
//                }
//
//            } catch (Exception ex) {
//                Log.e(TAG, "Error: " + ex.getMessage());
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            progressDialog.dismiss();
//            if ((Listline != null) && (Listline.size() > 0)) {
//                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Sewing.this, android.R.layout.simple_spinner_item, Listline);
//                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
//                Spin_line.setAdapter(dataAdapter);
//            }
//
//        }
//    }

    private class Getopr extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a=Spin_unit.getText().toString();
            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_opr);
                Request.addProperty("unit",a);
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

                Oprname.setText(ListTpro.get(0));
            }

        }
    }

    private class Getbuyer extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            unit = Spin_unit.getText().toString();
            Line = Spin_line.getText().toString();
            super.onPreExecute();
            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_buyer);
                Request.addProperty("unit", unit);
                Request.addProperty("line", Line);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Sewing.this, android.R.layout.simple_spinner_item, Listbuyer);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_buyer.setAdapter(dataAdapter);
            }

        }
    }

    private class Getorderno extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            unit = Spin_unit.getText().toString();
            Line = Spin_line.getText().toString();
            super.onPreExecute();
            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_orderno);

                Request.addProperty("unit", unit);
                Request.addProperty("line", Line);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Sewing.this, android.R.layout.simple_spinner_item, Listorderno);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// attaching data adapter to spinner
                Spin_Order_no.setAdapter(dataAdapter);
            }

        }
    }

    private class Getstyle extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            unit = Spin_unit.getText().toString();
            Line = Spin_line.getText().toString();
            super.onPreExecute();
            progressDialog = new ProgressDialog(Sewing.this);
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
                Request.addProperty("line", Line);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Sewing.this, android.R.layout.simple_spinner_item, Liststyle);
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
            unit = Spin_unit.getText().toString();
            Line = Spin_line.getText().toString();
            progressDialog = new ProgressDialog(Sewing.this);
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
                Request.addProperty("line", Line);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Sewing.this, android.R.layout.simple_spinner_item, Listcolor);
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
            unit = Spin_unit.getText().toString();
            Line = Spin_line.getText().toString();
            progressDialog = new ProgressDialog(Sewing.this);
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
                Request.addProperty("line", Line);


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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Sewing.this, android.R.layout.simple_spinner_item, Listsize);
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

        }
    }

    private class Getdeceftlocation extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Sewing.this);
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
//                A="hi";

            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();


            if ((Listdefectloc != null) && (Listdefectloc.size() == 1))

            {
                A = (Listdefectloc.get(0));
            } else

            {
                if ((Listdefectloc != null) && (Listdefectloc.size() == 2)) {
                    A = (Listdefectloc.get(0));
                    B = (Listdefectloc.get(1));
                } else {
                    if ((Listdefectloc != null) && (Listdefectloc.size() == 3)) {
                        A = (Listdefectloc.get(0));
                        B = (Listdefectloc.get(1));
                        C = (Listdefectloc.get(2));
                    } else {
                        if ((Listdefectloc != null) && (Listdefectloc.size() == 4)) {
                            A = (Listdefectloc.get(0));
                            B = (Listdefectloc.get(1));
                            C = (Listdefectloc.get(2));
                            D = (Listdefectloc.get(3));

                        } else {
                            if ((Listdefectloc != null) && (Listdefectloc.size() == 5)) {
                                A = (Listdefectloc.get(0));
                                B = (Listdefectloc.get(1));
                                C = (Listdefectloc.get(2));
                                D = (Listdefectloc.get(3));
                                E = (Listdefectloc.get(4));

                            } else {
                                if ((Listdefectloc != null) && (Listdefectloc.size() == 6)) {
                                    A = (Listdefectloc.get(0));

                                    B = (Listdefectloc.get(1));
                                    C = (Listdefectloc.get(2));
                                    D = (Listdefectloc.get(3));
                                    E = (Listdefectloc.get(4));
                                    F = (Listdefectloc.get(5));

                                } else {
                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 7)) {
                                        A = (Listdefectloc.get(0));

                                        B = (Listdefectloc.get(1));
                                        C = (Listdefectloc.get(2));
                                        D = (Listdefectloc.get(3));
                                        E = (Listdefectloc.get(4));
                                        F = (Listdefectloc.get(5));
                                        G = (Listdefectloc.get(6));

                                    } else {
                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 8)) {
                                            A = (Listdefectloc.get(0));

                                            B = (Listdefectloc.get(1));
                                            C = (Listdefectloc.get(2));
                                            D = (Listdefectloc.get(3));
                                            E = (Listdefectloc.get(4));
                                            F = (Listdefectloc.get(5));
                                            G = (Listdefectloc.get(6));
                                            H = (Listdefectloc.get(7));

                                        } else {
                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 9)) {
                                                A = (Listdefectloc.get(0));

                                                B = (Listdefectloc.get(1));
                                                C = (Listdefectloc.get(2));
                                                D = (Listdefectloc.get(3));
                                                E = (Listdefectloc.get(4));
                                                F = (Listdefectloc.get(5));
                                                G = (Listdefectloc.get(6));
                                                H = (Listdefectloc.get(7));
                                                I = (Listdefectloc.get(8));

                                            } else {
                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 10)) {
                                                    A = (Listdefectloc.get(0));

                                                    B = (Listdefectloc.get(1));
                                                    C = (Listdefectloc.get(2));
                                                    D = (Listdefectloc.get(3));
                                                    E = (Listdefectloc.get(4));
                                                    F = (Listdefectloc.get(5));
                                                    G = (Listdefectloc.get(6));
                                                    H = (Listdefectloc.get(7));
                                                    I = (Listdefectloc.get(8));
                                                    J = (Listdefectloc.get(9));

                                                } else {
                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 11)) {
                                                        A = (Listdefectloc.get(0));
                                                        B = (Listdefectloc.get(1));
                                                        C = (Listdefectloc.get(2));
                                                        D = (Listdefectloc.get(3));
                                                        E = (Listdefectloc.get(4));
                                                        F = (Listdefectloc.get(5));
                                                        G = (Listdefectloc.get(6));
                                                        H = (Listdefectloc.get(7));
                                                        I = (Listdefectloc.get(8));
                                                        J = (Listdefectloc.get(9));
                                                        K = (Listdefectloc.get(10));

                                                    } else {
                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 12)) {
                                                            A = (Listdefectloc.get(0));

                                                            B = (Listdefectloc.get(1));
                                                            C = (Listdefectloc.get(2));
                                                            D = (Listdefectloc.get(3));
                                                            E = (Listdefectloc.get(4));
                                                            F = (Listdefectloc.get(5));
                                                            G = (Listdefectloc.get(6));
                                                            H = (Listdefectloc.get(7));
                                                            I = (Listdefectloc.get(8));
                                                            J = (Listdefectloc.get(9));
                                                            K = (Listdefectloc.get(10));
                                                            L = (Listdefectloc.get(11));

                                                        } else {
                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 13)) {
                                                                A = (Listdefectloc.get(0));

                                                                B = (Listdefectloc.get(1));
                                                                C = (Listdefectloc.get(2));
                                                                D = (Listdefectloc.get(3));
                                                                E = (Listdefectloc.get(4));
                                                                F = (Listdefectloc.get(5));
                                                                G = (Listdefectloc.get(6));
                                                                H = (Listdefectloc.get(7));
                                                                I = (Listdefectloc.get(8));
                                                                J = (Listdefectloc.get(9));
                                                                K = (Listdefectloc.get(10));
                                                                L = (Listdefectloc.get(11));
                                                                M = (Listdefectloc.get(12));

                                                            } else {
                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 14)) {
                                                                    A = (Listdefectloc.get(0));

                                                                    B = (Listdefectloc.get(1));
                                                                    C = (Listdefectloc.get(2));
                                                                    D = (Listdefectloc.get(3));
                                                                    E = (Listdefectloc.get(4));
                                                                    F = (Listdefectloc.get(5));
                                                                    G = (Listdefectloc.get(6));
                                                                    H = (Listdefectloc.get(7));
                                                                    I = (Listdefectloc.get(8));
                                                                    J = (Listdefectloc.get(9));
                                                                    K = (Listdefectloc.get(10));
                                                                    L = (Listdefectloc.get(11));
                                                                    M = (Listdefectloc.get(12));
                                                                    N = (Listdefectloc.get(13));

                                                                } else {
                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 15)) {
                                                                        A = (Listdefectloc.get(0));

                                                                        B = (Listdefectloc.get(1));
                                                                        C = (Listdefectloc.get(2));
                                                                        D = (Listdefectloc.get(3));
                                                                        E = (Listdefectloc.get(4));
                                                                        F = (Listdefectloc.get(5));
                                                                        G = (Listdefectloc.get(6));
                                                                        H = (Listdefectloc.get(7));
                                                                        I = (Listdefectloc.get(8));
                                                                        J = (Listdefectloc.get(9));
                                                                        K = (Listdefectloc.get(10));
                                                                        L = (Listdefectloc.get(11));
                                                                        M = (Listdefectloc.get(12));
                                                                        N = (Listdefectloc.get(13));
                                                                        O = (Listdefectloc.get(14));

                                                                    } else {
                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 16)) {
                                                                            A = (Listdefectloc.get(0));

                                                                            B = (Listdefectloc.get(1));
                                                                            C = (Listdefectloc.get(2));
                                                                            D = (Listdefectloc.get(3));
                                                                            E = (Listdefectloc.get(4));
                                                                            F = (Listdefectloc.get(5));
                                                                            G = (Listdefectloc.get(6));
                                                                            H = (Listdefectloc.get(7));
                                                                            I = (Listdefectloc.get(8));
                                                                            J = (Listdefectloc.get(9));
                                                                            K = (Listdefectloc.get(10));
                                                                            L = (Listdefectloc.get(11));
                                                                            M = (Listdefectloc.get(12));
                                                                            N = (Listdefectloc.get(13));
                                                                            O = (Listdefectloc.get(14));
                                                                            P = (Listdefectloc.get(15));

                                                                        } else {
                                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 17)) {
                                                                                A = (Listdefectloc.get(0));

                                                                                B = (Listdefectloc.get(1));
                                                                                C = (Listdefectloc.get(2));
                                                                                D = (Listdefectloc.get(3));
                                                                                E = (Listdefectloc.get(4));
                                                                                F = (Listdefectloc.get(5));
                                                                                G = (Listdefectloc.get(6));
                                                                                H = (Listdefectloc.get(7));
                                                                                I = (Listdefectloc.get(8));
                                                                                J = (Listdefectloc.get(9));
                                                                                K = (Listdefectloc.get(10));
                                                                                L = (Listdefectloc.get(11));
                                                                                M = (Listdefectloc.get(12));
                                                                                N = (Listdefectloc.get(13));
                                                                                O = (Listdefectloc.get(14));
                                                                                P = (Listdefectloc.get(15));
                                                                                Q = (Listdefectloc.get(16));

                                                                            } else {
                                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 18)) {
                                                                                    A = (Listdefectloc.get(0));

                                                                                    B = (Listdefectloc.get(1));
                                                                                    C = (Listdefectloc.get(2));
                                                                                    D = (Listdefectloc.get(3));
                                                                                    E = (Listdefectloc.get(4));
                                                                                    F = (Listdefectloc.get(5));
                                                                                    G = (Listdefectloc.get(6));
                                                                                    H = (Listdefectloc.get(7));
                                                                                    I = (Listdefectloc.get(8));
                                                                                    J = (Listdefectloc.get(9));
                                                                                    K = (Listdefectloc.get(10));
                                                                                    L = (Listdefectloc.get(11));
                                                                                    M = (Listdefectloc.get(12));
                                                                                    N = (Listdefectloc.get(13));
                                                                                    O = (Listdefectloc.get(14));
                                                                                    P = (Listdefectloc.get(15));
                                                                                    Q = (Listdefectloc.get(16));
                                                                                    S = (Listdefectloc.get(17));
                                                                                } else {
                                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 19)) {
                                                                                        A = (Listdefectloc.get(0));

                                                                                        B = (Listdefectloc.get(1));
                                                                                        C = (Listdefectloc.get(2));
                                                                                        D = (Listdefectloc.get(3));
                                                                                        E = (Listdefectloc.get(4));
                                                                                        F = (Listdefectloc.get(5));
                                                                                        G = (Listdefectloc.get(6));
                                                                                        H = (Listdefectloc.get(7));
                                                                                        I = (Listdefectloc.get(8));
                                                                                        J = (Listdefectloc.get(9));
                                                                                        K = (Listdefectloc.get(10));
                                                                                        L = (Listdefectloc.get(11));
                                                                                        M = (Listdefectloc.get(12));
                                                                                        N = (Listdefectloc.get(13));
                                                                                        O = (Listdefectloc.get(14));
                                                                                        P = (Listdefectloc.get(15));
                                                                                        Q = (Listdefectloc.get(16));
                                                                                        S = (Listdefectloc.get(17));
                                                                                        T = (Listdefectloc.get(18));

                                                                                    } else {


                                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 20)) {
                                                                                            A = (Listdefectloc.get(0));

                                                                                            B = (Listdefectloc.get(1));
                                                                                            C = (Listdefectloc.get(2));
                                                                                            D = (Listdefectloc.get(3));
                                                                                            E = (Listdefectloc.get(4));
                                                                                            F = (Listdefectloc.get(5));
                                                                                            G = (Listdefectloc.get(6));
                                                                                            H = (Listdefectloc.get(7));
                                                                                            I = (Listdefectloc.get(8));
                                                                                            J = (Listdefectloc.get(9));
                                                                                            K = (Listdefectloc.get(10));
                                                                                            L = (Listdefectloc.get(11));
                                                                                            M = (Listdefectloc.get(12));
                                                                                            N = (Listdefectloc.get(13));
                                                                                            O = (Listdefectloc.get(14));
                                                                                            P = (Listdefectloc.get(15));
                                                                                            Q = (Listdefectloc.get(16));
                                                                                            S = (Listdefectloc.get(17));
                                                                                            T = (Listdefectloc.get(18));
                                                                                            U = (Listdefectloc.get(19));

                                                                                        } else {
                                                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 21)) {
                                                                                                A = (Listdefectloc.get(0));

                                                                                                B = (Listdefectloc.get(1));
                                                                                                C = (Listdefectloc.get(2));
                                                                                                D = (Listdefectloc.get(3));
                                                                                                E = (Listdefectloc.get(4));
                                                                                                F = (Listdefectloc.get(5));
                                                                                                G = (Listdefectloc.get(6));
                                                                                                H = (Listdefectloc.get(7));
                                                                                                I = (Listdefectloc.get(8));
                                                                                                J = (Listdefectloc.get(9));
                                                                                                K = (Listdefectloc.get(10));
                                                                                                L = (Listdefectloc.get(11));
                                                                                                M = (Listdefectloc.get(12));
                                                                                                N = (Listdefectloc.get(13));
                                                                                                O = (Listdefectloc.get(14));
                                                                                                P = (Listdefectloc.get(15));
                                                                                                Q = (Listdefectloc.get(16));
                                                                                                S = (Listdefectloc.get(17));
                                                                                                T = (Listdefectloc.get(18));
                                                                                                U = (Listdefectloc.get(19));
                                                                                                V = (Listdefectloc.get(20));

                                                                                            } else {
                                                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 22)) {
                                                                                                    A = (Listdefectloc.get(0));

                                                                                                    B = (Listdefectloc.get(1));
                                                                                                    C = (Listdefectloc.get(2));
                                                                                                    D = (Listdefectloc.get(3));
                                                                                                    E = (Listdefectloc.get(4));
                                                                                                    F = (Listdefectloc.get(5));
                                                                                                    G = (Listdefectloc.get(6));
                                                                                                    H = (Listdefectloc.get(7));
                                                                                                    I = (Listdefectloc.get(8));
                                                                                                    J = (Listdefectloc.get(9));
                                                                                                    K = (Listdefectloc.get(10));
                                                                                                    L = (Listdefectloc.get(11));
                                                                                                    M = (Listdefectloc.get(12));
                                                                                                    N = (Listdefectloc.get(13));
                                                                                                    O = (Listdefectloc.get(14));
                                                                                                    P = (Listdefectloc.get(15));
                                                                                                    Q = (Listdefectloc.get(16));
                                                                                                    S = (Listdefectloc.get(17));
                                                                                                    T = (Listdefectloc.get(18));
                                                                                                    U = (Listdefectloc.get(19));
                                                                                                    V = (Listdefectloc.get(20));
                                                                                                    W = (Listdefectloc.get(21));

                                                                                                } else {
                                                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 23)) {
                                                                                                        A = (Listdefectloc.get(0));

                                                                                                        B = (Listdefectloc.get(1));
                                                                                                        C = (Listdefectloc.get(2));
                                                                                                        D = (Listdefectloc.get(3));
                                                                                                        E = (Listdefectloc.get(4));
                                                                                                        F = (Listdefectloc.get(5));
                                                                                                        G = (Listdefectloc.get(6));
                                                                                                        H = (Listdefectloc.get(7));
                                                                                                        I = (Listdefectloc.get(8));
                                                                                                        J = (Listdefectloc.get(9));
                                                                                                        K = (Listdefectloc.get(10));
                                                                                                        L = (Listdefectloc.get(11));
                                                                                                        M = (Listdefectloc.get(12));
                                                                                                        N = (Listdefectloc.get(13));
                                                                                                        O = (Listdefectloc.get(14));
                                                                                                        P = (Listdefectloc.get(15));
                                                                                                        Q = (Listdefectloc.get(16));
                                                                                                        S = (Listdefectloc.get(17));
                                                                                                        T = (Listdefectloc.get(18));
                                                                                                        U = (Listdefectloc.get(19));
                                                                                                        V = (Listdefectloc.get(20));
                                                                                                        W = (Listdefectloc.get(21));
                                                                                                        X = (Listdefectloc.get(22));

                                                                                                    } else {
                                                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 24)) {
                                                                                                            A = (Listdefectloc.get(0));

                                                                                                            B = (Listdefectloc.get(1));
                                                                                                            C = (Listdefectloc.get(2));
                                                                                                            D = (Listdefectloc.get(3));
                                                                                                            E = (Listdefectloc.get(4));
                                                                                                            F = (Listdefectloc.get(5));
                                                                                                            G = (Listdefectloc.get(6));
                                                                                                            H = (Listdefectloc.get(7));
                                                                                                            I = (Listdefectloc.get(8));
                                                                                                            J = (Listdefectloc.get(9));
                                                                                                            K = (Listdefectloc.get(10));
                                                                                                            L = (Listdefectloc.get(11));
                                                                                                            M = (Listdefectloc.get(12));
                                                                                                            N = (Listdefectloc.get(13));
                                                                                                            O = (Listdefectloc.get(14));
                                                                                                            P = (Listdefectloc.get(15));
                                                                                                            Q = (Listdefectloc.get(16));
                                                                                                            S = (Listdefectloc.get(17));
                                                                                                            T = (Listdefectloc.get(18));
                                                                                                            U = (Listdefectloc.get(19));
                                                                                                            V = (Listdefectloc.get(20));
                                                                                                            W = (Listdefectloc.get(21));
                                                                                                            X = (Listdefectloc.get(22));
                                                                                                            Y = (Listdefectloc.get(23));

                                                                                                        } else {
                                                                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 25)) {
                                                                                                                A = (Listdefectloc.get(0));

                                                                                                                B = (Listdefectloc.get(1));
                                                                                                                C = (Listdefectloc.get(2));
                                                                                                                D = (Listdefectloc.get(3));
                                                                                                                E = (Listdefectloc.get(4));
                                                                                                                F = (Listdefectloc.get(5));
                                                                                                                G = (Listdefectloc.get(6));
                                                                                                                H = (Listdefectloc.get(7));
                                                                                                                I = (Listdefectloc.get(8));
                                                                                                                J = (Listdefectloc.get(9));
                                                                                                                K = (Listdefectloc.get(10));
                                                                                                                L = (Listdefectloc.get(11));
                                                                                                                M = (Listdefectloc.get(12));
                                                                                                                N = (Listdefectloc.get(13));
                                                                                                                O = (Listdefectloc.get(14));
                                                                                                                P = (Listdefectloc.get(15));
                                                                                                                Q = (Listdefectloc.get(16));
                                                                                                                S = (Listdefectloc.get(17));
                                                                                                                T = (Listdefectloc.get(18));
                                                                                                                U = (Listdefectloc.get(19));
                                                                                                                V = (Listdefectloc.get(20));
                                                                                                                W = (Listdefectloc.get(21));
                                                                                                                X = (Listdefectloc.get(22));
                                                                                                                Y = (Listdefectloc.get(23));
                                                                                                                Z = (Listdefectloc.get(24));

                                                                                                            } else {
                                                                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 26)) {
                                                                                                                    A = (Listdefectloc.get(0));

                                                                                                                    B = (Listdefectloc.get(1));
                                                                                                                    C = (Listdefectloc.get(2));
                                                                                                                    D = (Listdefectloc.get(3));
                                                                                                                    E = (Listdefectloc.get(4));
                                                                                                                    F = (Listdefectloc.get(5));
                                                                                                                    G = (Listdefectloc.get(6));
                                                                                                                    H = (Listdefectloc.get(7));
                                                                                                                    I = (Listdefectloc.get(8));
                                                                                                                    J = (Listdefectloc.get(9));
                                                                                                                    K = (Listdefectloc.get(10));
                                                                                                                    L = (Listdefectloc.get(11));
                                                                                                                    M = (Listdefectloc.get(12));
                                                                                                                    N = (Listdefectloc.get(13));
                                                                                                                    O = (Listdefectloc.get(14));
                                                                                                                    P = (Listdefectloc.get(15));
                                                                                                                    Q = (Listdefectloc.get(16));
                                                                                                                    S = (Listdefectloc.get(17));
                                                                                                                    T = (Listdefectloc.get(18));
                                                                                                                    U = (Listdefectloc.get(19));
                                                                                                                    V = (Listdefectloc.get(20));
                                                                                                                    W = (Listdefectloc.get(21));
                                                                                                                    X = (Listdefectloc.get(22));
                                                                                                                    Y = (Listdefectloc.get(23));
                                                                                                                    Z = (Listdefectloc.get(24));
                                                                                                                    AA = (Listdefectloc.get(25));
                                                                                                                } else {
                                                                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 27)) {
                                                                                                                        A = (Listdefectloc.get(0));

                                                                                                                        B = (Listdefectloc.get(1));
                                                                                                                        C = (Listdefectloc.get(2));
                                                                                                                        D = (Listdefectloc.get(3));
                                                                                                                        E = (Listdefectloc.get(4));
                                                                                                                        F = (Listdefectloc.get(5));
                                                                                                                        G = (Listdefectloc.get(6));
                                                                                                                        H = (Listdefectloc.get(7));
                                                                                                                        I = (Listdefectloc.get(8));
                                                                                                                        J = (Listdefectloc.get(9));
                                                                                                                        K = (Listdefectloc.get(10));
                                                                                                                        L = (Listdefectloc.get(11));
                                                                                                                        M = (Listdefectloc.get(12));
                                                                                                                        N = (Listdefectloc.get(13));
                                                                                                                        O = (Listdefectloc.get(14));
                                                                                                                        P = (Listdefectloc.get(15));
                                                                                                                        Q = (Listdefectloc.get(16));
                                                                                                                        S = (Listdefectloc.get(17));
                                                                                                                        T = (Listdefectloc.get(18));
                                                                                                                        U = (Listdefectloc.get(19));
                                                                                                                        V = (Listdefectloc.get(20));
                                                                                                                        W = (Listdefectloc.get(21));
                                                                                                                        X = (Listdefectloc.get(22));
                                                                                                                        Y = (Listdefectloc.get(23));
                                                                                                                        Z = (Listdefectloc.get(24));
                                                                                                                        AA = (Listdefectloc.get(25));
                                                                                                                        BB = (Listdefectloc.get(26));

                                                                                                                    } else {
                                                                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 28)) {
                                                                                                                            A = (Listdefectloc.get(0));

                                                                                                                            B = (Listdefectloc.get(1));
                                                                                                                            C = (Listdefectloc.get(2));
                                                                                                                            D = (Listdefectloc.get(3));
                                                                                                                            E = (Listdefectloc.get(4));
                                                                                                                            F = (Listdefectloc.get(5));
                                                                                                                            G = (Listdefectloc.get(6));
                                                                                                                            H = (Listdefectloc.get(7));
                                                                                                                            I = (Listdefectloc.get(8));
                                                                                                                            J = (Listdefectloc.get(9));
                                                                                                                            K = (Listdefectloc.get(10));
                                                                                                                            L = (Listdefectloc.get(11));
                                                                                                                            M = (Listdefectloc.get(12));
                                                                                                                            N = (Listdefectloc.get(13));
                                                                                                                            O = (Listdefectloc.get(14));
                                                                                                                            P = (Listdefectloc.get(15));
                                                                                                                            Q = (Listdefectloc.get(16));
                                                                                                                            S = (Listdefectloc.get(17));
                                                                                                                            T = (Listdefectloc.get(18));
                                                                                                                            U = (Listdefectloc.get(19));
                                                                                                                            V = (Listdefectloc.get(20));
                                                                                                                            W = (Listdefectloc.get(21));
                                                                                                                            X = (Listdefectloc.get(22));
                                                                                                                            Y = (Listdefectloc.get(23));
                                                                                                                            Z = (Listdefectloc.get(24));
                                                                                                                            AA = (Listdefectloc.get(25));
                                                                                                                            BB = (Listdefectloc.get(26));
                                                                                                                            CC = (Listdefectloc.get(27));

                                                                                                                        } else {
                                                                                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 29)) {
                                                                                                                                A = (Listdefectloc.get(0));

                                                                                                                                B = (Listdefectloc.get(1));
                                                                                                                                C = (Listdefectloc.get(2));
                                                                                                                                D = (Listdefectloc.get(3));
                                                                                                                                E = (Listdefectloc.get(4));
                                                                                                                                F = (Listdefectloc.get(5));
                                                                                                                                G = (Listdefectloc.get(6));
                                                                                                                                H = (Listdefectloc.get(7));
                                                                                                                                I = (Listdefectloc.get(8));
                                                                                                                                J = (Listdefectloc.get(9));
                                                                                                                                K = (Listdefectloc.get(10));
                                                                                                                                L = (Listdefectloc.get(11));
                                                                                                                                M = (Listdefectloc.get(12));
                                                                                                                                N = (Listdefectloc.get(13));
                                                                                                                                O = (Listdefectloc.get(14));
                                                                                                                                P = (Listdefectloc.get(15));
                                                                                                                                Q = (Listdefectloc.get(16));
                                                                                                                                S = (Listdefectloc.get(17));
                                                                                                                                T = (Listdefectloc.get(18));
                                                                                                                                U = (Listdefectloc.get(19));
                                                                                                                                V = (Listdefectloc.get(20));
                                                                                                                                W = (Listdefectloc.get(21));
                                                                                                                                X = (Listdefectloc.get(22));
                                                                                                                                Y = (Listdefectloc.get(23));
                                                                                                                                Z = (Listdefectloc.get(24));
                                                                                                                                AA = (Listdefectloc.get(25));
                                                                                                                                BB = (Listdefectloc.get(26));
                                                                                                                                CC = (Listdefectloc.get(27));
                                                                                                                                DD = (Listdefectloc.get(28));

                                                                                                                            } else {
                                                                                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 30)) {
                                                                                                                                    A = (Listdefectloc.get(0));

                                                                                                                                    B = (Listdefectloc.get(1));
                                                                                                                                    C = (Listdefectloc.get(2));
                                                                                                                                    D = (Listdefectloc.get(3));
                                                                                                                                    E = (Listdefectloc.get(4));
                                                                                                                                    F = (Listdefectloc.get(5));
                                                                                                                                    G = (Listdefectloc.get(6));
                                                                                                                                    H = (Listdefectloc.get(7));
                                                                                                                                    I = (Listdefectloc.get(8));
                                                                                                                                    J = (Listdefectloc.get(9));
                                                                                                                                    K = (Listdefectloc.get(10));
                                                                                                                                    L = (Listdefectloc.get(11));
                                                                                                                                    M = (Listdefectloc.get(12));
                                                                                                                                    N = (Listdefectloc.get(13));
                                                                                                                                    O = (Listdefectloc.get(14));
                                                                                                                                    P = (Listdefectloc.get(15));
                                                                                                                                    Q = (Listdefectloc.get(16));
                                                                                                                                    S = (Listdefectloc.get(17));
                                                                                                                                    T = (Listdefectloc.get(18));
                                                                                                                                    U = (Listdefectloc.get(19));
                                                                                                                                    V = (Listdefectloc.get(20));
                                                                                                                                    W = (Listdefectloc.get(21));
                                                                                                                                    X = (Listdefectloc.get(22));
                                                                                                                                    Y = (Listdefectloc.get(23));
                                                                                                                                    Z = (Listdefectloc.get(24));
                                                                                                                                    AA = (Listdefectloc.get(25));
                                                                                                                                    BB = (Listdefectloc.get(26));
                                                                                                                                    CC = (Listdefectloc.get(27));
                                                                                                                                    DD = (Listdefectloc.get(28));
                                                                                                                                    EE = (Listdefectloc.get(29));

                                                                                                                                } else {
                                                                                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 31)) {
                                                                                                                                        A = (Listdefectloc.get(0));

                                                                                                                                        B = (Listdefectloc.get(1));
                                                                                                                                        C = (Listdefectloc.get(2));
                                                                                                                                        D = (Listdefectloc.get(3));
                                                                                                                                        E = (Listdefectloc.get(4));
                                                                                                                                        F = (Listdefectloc.get(5));
                                                                                                                                        G = (Listdefectloc.get(6));
                                                                                                                                        H = (Listdefectloc.get(7));
                                                                                                                                        I = (Listdefectloc.get(8));
                                                                                                                                        J = (Listdefectloc.get(9));
                                                                                                                                        K = (Listdefectloc.get(10));
                                                                                                                                        L = (Listdefectloc.get(11));
                                                                                                                                        M = (Listdefectloc.get(12));
                                                                                                                                        N = (Listdefectloc.get(13));
                                                                                                                                        O = (Listdefectloc.get(14));
                                                                                                                                        P = (Listdefectloc.get(15));
                                                                                                                                        Q = (Listdefectloc.get(16));
                                                                                                                                        S = (Listdefectloc.get(17));
                                                                                                                                        T = (Listdefectloc.get(18));
                                                                                                                                        U = (Listdefectloc.get(19));
                                                                                                                                        V = (Listdefectloc.get(20));
                                                                                                                                        W = (Listdefectloc.get(21));
                                                                                                                                        X = (Listdefectloc.get(22));
                                                                                                                                        Y = (Listdefectloc.get(23));
                                                                                                                                        Z = (Listdefectloc.get(24));
                                                                                                                                        AA = (Listdefectloc.get(25));
                                                                                                                                        BB = (Listdefectloc.get(26));
                                                                                                                                        CC = (Listdefectloc.get(27));
                                                                                                                                        DD = (Listdefectloc.get(28));
                                                                                                                                        EE = (Listdefectloc.get(29));
                                                                                                                                        FF = (Listdefectloc.get(30));

                                                                                                                                    } else {
                                                                                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 32)) {
                                                                                                                                            A = (Listdefectloc.get(0));

                                                                                                                                            B = (Listdefectloc.get(1));
                                                                                                                                            C = (Listdefectloc.get(2));
                                                                                                                                            D = (Listdefectloc.get(3));
                                                                                                                                            E = (Listdefectloc.get(4));
                                                                                                                                            F = (Listdefectloc.get(5));
                                                                                                                                            G = (Listdefectloc.get(6));
                                                                                                                                            H = (Listdefectloc.get(7));
                                                                                                                                            I = (Listdefectloc.get(8));
                                                                                                                                            J = (Listdefectloc.get(9));
                                                                                                                                            K = (Listdefectloc.get(10));
                                                                                                                                            L = (Listdefectloc.get(11));
                                                                                                                                            M = (Listdefectloc.get(12));
                                                                                                                                            N = (Listdefectloc.get(13));
                                                                                                                                            O = (Listdefectloc.get(14));
                                                                                                                                            P = (Listdefectloc.get(15));
                                                                                                                                            Q = (Listdefectloc.get(16));
                                                                                                                                            S = (Listdefectloc.get(17));
                                                                                                                                            T = (Listdefectloc.get(18));
                                                                                                                                            U = (Listdefectloc.get(19));
                                                                                                                                            V = (Listdefectloc.get(20));
                                                                                                                                            W = (Listdefectloc.get(21));
                                                                                                                                            X = (Listdefectloc.get(22));
                                                                                                                                            Y = (Listdefectloc.get(23));
                                                                                                                                            Z = (Listdefectloc.get(24));
                                                                                                                                            AA = (Listdefectloc.get(25));
                                                                                                                                            BB = (Listdefectloc.get(26));
                                                                                                                                            CC = (Listdefectloc.get(27));
                                                                                                                                            DD = (Listdefectloc.get(28));
                                                                                                                                            EE = (Listdefectloc.get(29));
                                                                                                                                            FF = (Listdefectloc.get(30));
                                                                                                                                            GG = (Listdefectloc.get(31));

                                                                                                                                        } else {
                                                                                                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 33)) {
                                                                                                                                                A = (Listdefectloc.get(0));

                                                                                                                                                B = (Listdefectloc.get(1));
                                                                                                                                                C = (Listdefectloc.get(2));
                                                                                                                                                D = (Listdefectloc.get(3));
                                                                                                                                                E = (Listdefectloc.get(4));
                                                                                                                                                F = (Listdefectloc.get(5));
                                                                                                                                                G = (Listdefectloc.get(6));
                                                                                                                                                H = (Listdefectloc.get(7));
                                                                                                                                                I = (Listdefectloc.get(8));
                                                                                                                                                J = (Listdefectloc.get(9));
                                                                                                                                                K = (Listdefectloc.get(10));
                                                                                                                                                L = (Listdefectloc.get(11));
                                                                                                                                                M = (Listdefectloc.get(12));
                                                                                                                                                N = (Listdefectloc.get(13));
                                                                                                                                                O = (Listdefectloc.get(14));
                                                                                                                                                P = (Listdefectloc.get(15));
                                                                                                                                                Q = (Listdefectloc.get(16));
                                                                                                                                                S = (Listdefectloc.get(17));
                                                                                                                                                T = (Listdefectloc.get(18));
                                                                                                                                                U = (Listdefectloc.get(19));
                                                                                                                                                V = (Listdefectloc.get(20));
                                                                                                                                                W = (Listdefectloc.get(21));
                                                                                                                                                X = (Listdefectloc.get(22));
                                                                                                                                                Y = (Listdefectloc.get(23));
                                                                                                                                                Z = (Listdefectloc.get(24));
                                                                                                                                                AA = (Listdefectloc.get(25));
                                                                                                                                                BB = (Listdefectloc.get(26));
                                                                                                                                                CC = (Listdefectloc.get(27));
                                                                                                                                                DD = (Listdefectloc.get(28));
                                                                                                                                                EE = (Listdefectloc.get(29));
                                                                                                                                                FF = (Listdefectloc.get(30));
                                                                                                                                                GG = (Listdefectloc.get(31));
                                                                                                                                                HH = (Listdefectloc.get(32));

                                                                                                                                            } else {
                                                                                                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 34)) {
                                                                                                                                                    A = (Listdefectloc.get(0));

                                                                                                                                                    B = (Listdefectloc.get(1));
                                                                                                                                                    C = (Listdefectloc.get(2));
                                                                                                                                                    D = (Listdefectloc.get(3));
                                                                                                                                                    E = (Listdefectloc.get(4));
                                                                                                                                                    F = (Listdefectloc.get(5));
                                                                                                                                                    G = (Listdefectloc.get(6));
                                                                                                                                                    H = (Listdefectloc.get(7));
                                                                                                                                                    I = (Listdefectloc.get(8));
                                                                                                                                                    J = (Listdefectloc.get(9));
                                                                                                                                                    K = (Listdefectloc.get(10));
                                                                                                                                                    L = (Listdefectloc.get(11));
                                                                                                                                                    M = (Listdefectloc.get(12));
                                                                                                                                                    N = (Listdefectloc.get(13));
                                                                                                                                                    O = (Listdefectloc.get(14));
                                                                                                                                                    P = (Listdefectloc.get(15));
                                                                                                                                                    Q = (Listdefectloc.get(16));
                                                                                                                                                    S = (Listdefectloc.get(17));
                                                                                                                                                    T = (Listdefectloc.get(18));
                                                                                                                                                    U = (Listdefectloc.get(19));
                                                                                                                                                    V = (Listdefectloc.get(20));
                                                                                                                                                    W = (Listdefectloc.get(21));
                                                                                                                                                    X = (Listdefectloc.get(22));
                                                                                                                                                    Y = (Listdefectloc.get(23));
                                                                                                                                                    Z = (Listdefectloc.get(24));
                                                                                                                                                    AA = (Listdefectloc.get(25));
                                                                                                                                                    BB = (Listdefectloc.get(26));
                                                                                                                                                    CC = (Listdefectloc.get(27));
                                                                                                                                                    DD = (Listdefectloc.get(28));
                                                                                                                                                    EE = (Listdefectloc.get(29));
                                                                                                                                                    FF = (Listdefectloc.get(30));
                                                                                                                                                    GG = (Listdefectloc.get(31));
                                                                                                                                                    HH = (Listdefectloc.get(32));


                                                                                                                                                } else {
                                                                                                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 35)) {
                                                                                                                                                        A = (Listdefectloc.get(0));

                                                                                                                                                        B = (Listdefectloc.get(1));
                                                                                                                                                        C = (Listdefectloc.get(2));
                                                                                                                                                        D = (Listdefectloc.get(3));
                                                                                                                                                        E = (Listdefectloc.get(4));
                                                                                                                                                        F = (Listdefectloc.get(5));
                                                                                                                                                        G = (Listdefectloc.get(6));
                                                                                                                                                        H = (Listdefectloc.get(7));
                                                                                                                                                        I = (Listdefectloc.get(8));
                                                                                                                                                        J = (Listdefectloc.get(9));
                                                                                                                                                        K = (Listdefectloc.get(10));
                                                                                                                                                        L = (Listdefectloc.get(11));
                                                                                                                                                        M = (Listdefectloc.get(12));
                                                                                                                                                        N = (Listdefectloc.get(13));
                                                                                                                                                        O = (Listdefectloc.get(14));
                                                                                                                                                        P = (Listdefectloc.get(15));
                                                                                                                                                        Q = (Listdefectloc.get(16));
                                                                                                                                                        S = (Listdefectloc.get(17));
                                                                                                                                                        T = (Listdefectloc.get(18));
                                                                                                                                                        U = (Listdefectloc.get(19));
                                                                                                                                                        V = (Listdefectloc.get(20));
                                                                                                                                                        W = (Listdefectloc.get(21));
                                                                                                                                                        X = (Listdefectloc.get(22));
                                                                                                                                                        Y = (Listdefectloc.get(23));
                                                                                                                                                        Z = (Listdefectloc.get(24));
                                                                                                                                                        AA = (Listdefectloc.get(25));
                                                                                                                                                        BB = (Listdefectloc.get(26));
                                                                                                                                                        CC = (Listdefectloc.get(27));
                                                                                                                                                        DD = (Listdefectloc.get(28));
                                                                                                                                                        EE = (Listdefectloc.get(29));
                                                                                                                                                        FF = (Listdefectloc.get(30));
                                                                                                                                                        GG = (Listdefectloc.get(31));
                                                                                                                                                        HH = (Listdefectloc.get(32));
                                                                                                                                                        II = (Listdefectloc.get(33));
                                                                                                                                                        JJ = (Listdefectloc.get(34));

                                                                                                                                                    } else {
                                                                                                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 36)) {
                                                                                                                                                            A = (Listdefectloc.get(0));

                                                                                                                                                            B = (Listdefectloc.get(1));
                                                                                                                                                            C = (Listdefectloc.get(2));
                                                                                                                                                            D = (Listdefectloc.get(3));
                                                                                                                                                            E = (Listdefectloc.get(4));
                                                                                                                                                            F = (Listdefectloc.get(5));
                                                                                                                                                            G = (Listdefectloc.get(6));
                                                                                                                                                            H = (Listdefectloc.get(7));
                                                                                                                                                            I = (Listdefectloc.get(8));
                                                                                                                                                            J = (Listdefectloc.get(9));
                                                                                                                                                            K = (Listdefectloc.get(10));
                                                                                                                                                            L = (Listdefectloc.get(11));
                                                                                                                                                            M = (Listdefectloc.get(12));
                                                                                                                                                            N = (Listdefectloc.get(13));
                                                                                                                                                            O = (Listdefectloc.get(14));
                                                                                                                                                            P = (Listdefectloc.get(15));
                                                                                                                                                            Q = (Listdefectloc.get(16));
                                                                                                                                                            S = (Listdefectloc.get(17));
                                                                                                                                                            T = (Listdefectloc.get(18));
                                                                                                                                                            U = (Listdefectloc.get(19));
                                                                                                                                                            V = (Listdefectloc.get(20));
                                                                                                                                                            W = (Listdefectloc.get(21));
                                                                                                                                                            X = (Listdefectloc.get(22));
                                                                                                                                                            Y = (Listdefectloc.get(23));
                                                                                                                                                            Z = (Listdefectloc.get(24));
                                                                                                                                                            AA = (Listdefectloc.get(25));
                                                                                                                                                            BB = (Listdefectloc.get(26));
                                                                                                                                                            CC = (Listdefectloc.get(27));
                                                                                                                                                            DD = (Listdefectloc.get(28));
                                                                                                                                                            EE = (Listdefectloc.get(29));
                                                                                                                                                            FF = (Listdefectloc.get(30));
                                                                                                                                                            GG = (Listdefectloc.get(31));
                                                                                                                                                            HH = (Listdefectloc.get(32));
                                                                                                                                                            II = (Listdefectloc.get(33));
                                                                                                                                                            JJ = (Listdefectloc.get(34));
                                                                                                                                                            KK = (Listdefectloc.get(35));

                                                                                                                                                        } else {
                                                                                                                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 37)) {
                                                                                                                                                                A = (Listdefectloc.get(0));

                                                                                                                                                                B = (Listdefectloc.get(1));
                                                                                                                                                                C = (Listdefectloc.get(2));
                                                                                                                                                                D = (Listdefectloc.get(3));
                                                                                                                                                                E = (Listdefectloc.get(4));
                                                                                                                                                                F = (Listdefectloc.get(5));
                                                                                                                                                                G = (Listdefectloc.get(6));
                                                                                                                                                                H = (Listdefectloc.get(7));
                                                                                                                                                                I = (Listdefectloc.get(8));
                                                                                                                                                                J = (Listdefectloc.get(9));
                                                                                                                                                                K = (Listdefectloc.get(10));
                                                                                                                                                                L = (Listdefectloc.get(11));
                                                                                                                                                                M = (Listdefectloc.get(12));
                                                                                                                                                                N = (Listdefectloc.get(13));
                                                                                                                                                                O = (Listdefectloc.get(14));
                                                                                                                                                                P = (Listdefectloc.get(15));
                                                                                                                                                                Q = (Listdefectloc.get(16));
                                                                                                                                                                S = (Listdefectloc.get(17));
                                                                                                                                                                T = (Listdefectloc.get(18));
                                                                                                                                                                U = (Listdefectloc.get(19));
                                                                                                                                                                V = (Listdefectloc.get(20));
                                                                                                                                                                W = (Listdefectloc.get(21));
                                                                                                                                                                X = (Listdefectloc.get(22));
                                                                                                                                                                Y = (Listdefectloc.get(23));
                                                                                                                                                                Z = (Listdefectloc.get(24));
                                                                                                                                                                AA = (Listdefectloc.get(25));
                                                                                                                                                                BB = (Listdefectloc.get(26));
                                                                                                                                                                CC = (Listdefectloc.get(27));
                                                                                                                                                                DD = (Listdefectloc.get(28));
                                                                                                                                                                EE = (Listdefectloc.get(29));
                                                                                                                                                                FF = (Listdefectloc.get(30));
                                                                                                                                                                GG = (Listdefectloc.get(31));
                                                                                                                                                                HH = (Listdefectloc.get(32));
                                                                                                                                                                II = (Listdefectloc.get(33));
                                                                                                                                                                JJ = (Listdefectloc.get(34));
                                                                                                                                                                KK = (Listdefectloc.get(35));
                                                                                                                                                                LL = (Listdefectloc.get(36));

                                                                                                                                                            } else {
                                                                                                                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 38)) {
                                                                                                                                                                    A = (Listdefectloc.get(0));
                                                                                                                                                                    B = (Listdefectloc.get(1));
                                                                                                                                                                    C = (Listdefectloc.get(2));
                                                                                                                                                                    D = (Listdefectloc.get(3));
                                                                                                                                                                    E = (Listdefectloc.get(4));
                                                                                                                                                                    F = (Listdefectloc.get(5));
                                                                                                                                                                    G = (Listdefectloc.get(6));
                                                                                                                                                                    H = (Listdefectloc.get(7));
                                                                                                                                                                    I = (Listdefectloc.get(8));
                                                                                                                                                                    J = (Listdefectloc.get(9));
                                                                                                                                                                    K = (Listdefectloc.get(10));
                                                                                                                                                                    L = (Listdefectloc.get(11));
                                                                                                                                                                    M = (Listdefectloc.get(12));
                                                                                                                                                                    N = (Listdefectloc.get(13));
                                                                                                                                                                    O = (Listdefectloc.get(14));
                                                                                                                                                                    P = (Listdefectloc.get(15));
                                                                                                                                                                    Q = (Listdefectloc.get(16));
                                                                                                                                                                    S = (Listdefectloc.get(17));
                                                                                                                                                                    T = (Listdefectloc.get(18));
                                                                                                                                                                    U = (Listdefectloc.get(19));
                                                                                                                                                                    V = (Listdefectloc.get(20));
                                                                                                                                                                    W = (Listdefectloc.get(21));
                                                                                                                                                                    X = (Listdefectloc.get(22));
                                                                                                                                                                    Y = (Listdefectloc.get(23));
                                                                                                                                                                    Z = (Listdefectloc.get(24));
                                                                                                                                                                    AA = (Listdefectloc.get(25));
                                                                                                                                                                    BB = (Listdefectloc.get(26));
                                                                                                                                                                    CC = (Listdefectloc.get(27));
                                                                                                                                                                    DD = (Listdefectloc.get(28));
                                                                                                                                                                    EE = (Listdefectloc.get(29));
                                                                                                                                                                    FF = (Listdefectloc.get(30));
                                                                                                                                                                    GG = (Listdefectloc.get(31));
                                                                                                                                                                    HH = (Listdefectloc.get(32));
                                                                                                                                                                    II = (Listdefectloc.get(33));
                                                                                                                                                                    JJ = (Listdefectloc.get(34));
                                                                                                                                                                    KK = (Listdefectloc.get(35));
                                                                                                                                                                    LL = (Listdefectloc.get(36));
                                                                                                                                                                    MM = (Listdefectloc.get(37));

                                                                                                                                                                } else {
                                                                                                                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 39)) {
                                                                                                                                                                        A = (Listdefectloc.get(0));

                                                                                                                                                                        B = (Listdefectloc.get(1));
                                                                                                                                                                        C = (Listdefectloc.get(2));
                                                                                                                                                                        D = (Listdefectloc.get(3));
                                                                                                                                                                        E = (Listdefectloc.get(4));
                                                                                                                                                                        F = (Listdefectloc.get(5));
                                                                                                                                                                        G = (Listdefectloc.get(6));
                                                                                                                                                                        H = (Listdefectloc.get(7));
                                                                                                                                                                        I = (Listdefectloc.get(8));
                                                                                                                                                                        J = (Listdefectloc.get(9));
                                                                                                                                                                        K = (Listdefectloc.get(10));
                                                                                                                                                                        L = (Listdefectloc.get(11));
                                                                                                                                                                        M = (Listdefectloc.get(12));
                                                                                                                                                                        N = (Listdefectloc.get(13));
                                                                                                                                                                        O = (Listdefectloc.get(14));
                                                                                                                                                                        P = (Listdefectloc.get(15));
                                                                                                                                                                        Q = (Listdefectloc.get(16));
                                                                                                                                                                        S = (Listdefectloc.get(17));
                                                                                                                                                                        T = (Listdefectloc.get(18));
                                                                                                                                                                        U = (Listdefectloc.get(19));
                                                                                                                                                                        V = (Listdefectloc.get(20));
                                                                                                                                                                        W = (Listdefectloc.get(21));
                                                                                                                                                                        X = (Listdefectloc.get(22));
                                                                                                                                                                        Y = (Listdefectloc.get(23));
                                                                                                                                                                        Z = (Listdefectloc.get(24));
                                                                                                                                                                        AA = (Listdefectloc.get(25));
                                                                                                                                                                        BB = (Listdefectloc.get(26));
                                                                                                                                                                        CC = (Listdefectloc.get(27));
                                                                                                                                                                        DD = (Listdefectloc.get(28));
                                                                                                                                                                        EE = (Listdefectloc.get(29));
                                                                                                                                                                        FF = (Listdefectloc.get(30));
                                                                                                                                                                        GG = (Listdefectloc.get(31));
                                                                                                                                                                        HH = (Listdefectloc.get(32));
                                                                                                                                                                        II = (Listdefectloc.get(33));
                                                                                                                                                                        JJ = (Listdefectloc.get(34));
                                                                                                                                                                        KK = (Listdefectloc.get(35));
                                                                                                                                                                        LL = (Listdefectloc.get(36));
                                                                                                                                                                        MM = (Listdefectloc.get(37));
                                                                                                                                                                        NN = (Listdefectloc.get(38));

                                                                                                                                                                    } else {
                                                                                                                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 40)) {
                                                                                                                                                                            A = (Listdefectloc.get(0));

                                                                                                                                                                            B = (Listdefectloc.get(1));
                                                                                                                                                                            C = (Listdefectloc.get(2));
                                                                                                                                                                            D = (Listdefectloc.get(3));
                                                                                                                                                                            E = (Listdefectloc.get(4));
                                                                                                                                                                            F = (Listdefectloc.get(5));
                                                                                                                                                                            G = (Listdefectloc.get(6));
                                                                                                                                                                            H = (Listdefectloc.get(7));
                                                                                                                                                                            I = (Listdefectloc.get(8));
                                                                                                                                                                            J = (Listdefectloc.get(9));
                                                                                                                                                                            K = (Listdefectloc.get(10));
                                                                                                                                                                            L = (Listdefectloc.get(11));
                                                                                                                                                                            M = (Listdefectloc.get(12));
                                                                                                                                                                            N = (Listdefectloc.get(13));
                                                                                                                                                                            O = (Listdefectloc.get(14));
                                                                                                                                                                            P = (Listdefectloc.get(15));
                                                                                                                                                                            Q = (Listdefectloc.get(16));
                                                                                                                                                                            S = (Listdefectloc.get(17));
                                                                                                                                                                            T = (Listdefectloc.get(18));
                                                                                                                                                                            U = (Listdefectloc.get(19));
                                                                                                                                                                            V = (Listdefectloc.get(20));
                                                                                                                                                                            W = (Listdefectloc.get(21));
                                                                                                                                                                            X = (Listdefectloc.get(22));
                                                                                                                                                                            Y = (Listdefectloc.get(23));
                                                                                                                                                                            Z = (Listdefectloc.get(24));
                                                                                                                                                                            AA = (Listdefectloc.get(25));
                                                                                                                                                                            BB = (Listdefectloc.get(26));
                                                                                                                                                                            CC = (Listdefectloc.get(27));
                                                                                                                                                                            DD = (Listdefectloc.get(28));
                                                                                                                                                                            EE = (Listdefectloc.get(29));
                                                                                                                                                                            FF = (Listdefectloc.get(30));
                                                                                                                                                                            GG = (Listdefectloc.get(31));
                                                                                                                                                                            HH = (Listdefectloc.get(32));
                                                                                                                                                                            II = (Listdefectloc.get(33));
                                                                                                                                                                            JJ = (Listdefectloc.get(34));
                                                                                                                                                                            KK = (Listdefectloc.get(35));
                                                                                                                                                                            LL = (Listdefectloc.get(36));
                                                                                                                                                                            MM = (Listdefectloc.get(37));
                                                                                                                                                                            NN = (Listdefectloc.get(38));
                                                                                                                                                                            OO = (Listdefectloc.get(39));

                                                                                                                                                                        } else {
                                                                                                                                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 41)) {
                                                                                                                                                                                A = (Listdefectloc.get(0));

                                                                                                                                                                                B = (Listdefectloc.get(1));
                                                                                                                                                                                C = (Listdefectloc.get(2));
                                                                                                                                                                                D = (Listdefectloc.get(3));
                                                                                                                                                                                E = (Listdefectloc.get(4));
                                                                                                                                                                                F = (Listdefectloc.get(5));
                                                                                                                                                                                G = (Listdefectloc.get(6));
                                                                                                                                                                                H = (Listdefectloc.get(7));
                                                                                                                                                                                I = (Listdefectloc.get(8));
                                                                                                                                                                                J = (Listdefectloc.get(9));
                                                                                                                                                                                K = (Listdefectloc.get(10));
                                                                                                                                                                                L = (Listdefectloc.get(11));
                                                                                                                                                                                M = (Listdefectloc.get(12));
                                                                                                                                                                                N = (Listdefectloc.get(13));
                                                                                                                                                                                O = (Listdefectloc.get(14));
                                                                                                                                                                                P = (Listdefectloc.get(15));
                                                                                                                                                                                Q = (Listdefectloc.get(16));
                                                                                                                                                                                S = (Listdefectloc.get(17));
                                                                                                                                                                                T = (Listdefectloc.get(18));
                                                                                                                                                                                U = (Listdefectloc.get(19));
                                                                                                                                                                                V = (Listdefectloc.get(20));
                                                                                                                                                                                W = (Listdefectloc.get(21));
                                                                                                                                                                                X = (Listdefectloc.get(22));
                                                                                                                                                                                Y = (Listdefectloc.get(23));
                                                                                                                                                                                Z = (Listdefectloc.get(24));
                                                                                                                                                                                AA = (Listdefectloc.get(25));
                                                                                                                                                                                BB = (Listdefectloc.get(26));
                                                                                                                                                                                CC = (Listdefectloc.get(27));
                                                                                                                                                                                DD = (Listdefectloc.get(28));
                                                                                                                                                                                EE = (Listdefectloc.get(29));
                                                                                                                                                                                FF = (Listdefectloc.get(30));
                                                                                                                                                                                GG = (Listdefectloc.get(31));
                                                                                                                                                                                HH = (Listdefectloc.get(32));
                                                                                                                                                                                II = (Listdefectloc.get(33));
                                                                                                                                                                                JJ = (Listdefectloc.get(34));
                                                                                                                                                                                KK = (Listdefectloc.get(35));
                                                                                                                                                                                LL = (Listdefectloc.get(36));
                                                                                                                                                                                MM = (Listdefectloc.get(37));
                                                                                                                                                                                NN = (Listdefectloc.get(38));
                                                                                                                                                                                OO = (Listdefectloc.get(39));
                                                                                                                                                                                PP = (Listdefectloc.get(40));

                                                                                                                                                                            } else {
                                                                                                                                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 42)) {
                                                                                                                                                                                    A = (Listdefectloc.get(0));

                                                                                                                                                                                    B = (Listdefectloc.get(1));
                                                                                                                                                                                    C = (Listdefectloc.get(2));
                                                                                                                                                                                    D = (Listdefectloc.get(3));
                                                                                                                                                                                    E = (Listdefectloc.get(4));
                                                                                                                                                                                    F = (Listdefectloc.get(5));
                                                                                                                                                                                    G = (Listdefectloc.get(6));
                                                                                                                                                                                    H = (Listdefectloc.get(7));
                                                                                                                                                                                    I = (Listdefectloc.get(8));
                                                                                                                                                                                    J = (Listdefectloc.get(9));
                                                                                                                                                                                    K = (Listdefectloc.get(10));
                                                                                                                                                                                    L = (Listdefectloc.get(11));
                                                                                                                                                                                    M = (Listdefectloc.get(12));
                                                                                                                                                                                    N = (Listdefectloc.get(13));
                                                                                                                                                                                    O = (Listdefectloc.get(14));
                                                                                                                                                                                    P = (Listdefectloc.get(15));
                                                                                                                                                                                    Q = (Listdefectloc.get(16));
                                                                                                                                                                                    S = (Listdefectloc.get(17));
                                                                                                                                                                                    T = (Listdefectloc.get(18));
                                                                                                                                                                                    U = (Listdefectloc.get(19));
                                                                                                                                                                                    V = (Listdefectloc.get(20));
                                                                                                                                                                                    W = (Listdefectloc.get(21));
                                                                                                                                                                                    X = (Listdefectloc.get(22));
                                                                                                                                                                                    Y = (Listdefectloc.get(23));
                                                                                                                                                                                    Z = (Listdefectloc.get(24));
                                                                                                                                                                                    AA = (Listdefectloc.get(25));
                                                                                                                                                                                    BB = (Listdefectloc.get(26));
                                                                                                                                                                                    CC = (Listdefectloc.get(27));
                                                                                                                                                                                    DD = (Listdefectloc.get(28));
                                                                                                                                                                                    EE = (Listdefectloc.get(29));
                                                                                                                                                                                    FF = (Listdefectloc.get(30));
                                                                                                                                                                                    GG = (Listdefectloc.get(31));
                                                                                                                                                                                    HH = (Listdefectloc.get(32));
                                                                                                                                                                                    II = (Listdefectloc.get(33));
                                                                                                                                                                                    JJ = (Listdefectloc.get(34));
                                                                                                                                                                                    KK = (Listdefectloc.get(35));
                                                                                                                                                                                    LL = (Listdefectloc.get(36));
                                                                                                                                                                                    MM = (Listdefectloc.get(37));
                                                                                                                                                                                    NN = (Listdefectloc.get(38));
                                                                                                                                                                                    OO = (Listdefectloc.get(39));
                                                                                                                                                                                    PP = (Listdefectloc.get(40));
                                                                                                                                                                                    QQ = (Listdefectloc.get(41));

                                                                                                                                                                                } else {
                                                                                                                                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 43)) {
                                                                                                                                                                                        A = (Listdefectloc.get(0));

                                                                                                                                                                                        B = (Listdefectloc.get(1));
                                                                                                                                                                                        C = (Listdefectloc.get(2));
                                                                                                                                                                                        D = (Listdefectloc.get(3));
                                                                                                                                                                                        E = (Listdefectloc.get(4));
                                                                                                                                                                                        F = (Listdefectloc.get(5));
                                                                                                                                                                                        G = (Listdefectloc.get(6));
                                                                                                                                                                                        H = (Listdefectloc.get(7));
                                                                                                                                                                                        I = (Listdefectloc.get(8));
                                                                                                                                                                                        J = (Listdefectloc.get(9));
                                                                                                                                                                                        K = (Listdefectloc.get(10));
                                                                                                                                                                                        L = (Listdefectloc.get(11));
                                                                                                                                                                                        M = (Listdefectloc.get(12));
                                                                                                                                                                                        N = (Listdefectloc.get(13));
                                                                                                                                                                                        O = (Listdefectloc.get(14));
                                                                                                                                                                                        P = (Listdefectloc.get(15));
                                                                                                                                                                                        Q = (Listdefectloc.get(16));
                                                                                                                                                                                        S = (Listdefectloc.get(17));
                                                                                                                                                                                        T = (Listdefectloc.get(18));
                                                                                                                                                                                        U = (Listdefectloc.get(19));
                                                                                                                                                                                        V = (Listdefectloc.get(20));
                                                                                                                                                                                        W = (Listdefectloc.get(21));
                                                                                                                                                                                        X = (Listdefectloc.get(22));
                                                                                                                                                                                        Y = (Listdefectloc.get(23));
                                                                                                                                                                                        Z = (Listdefectloc.get(24));
                                                                                                                                                                                        AA = (Listdefectloc.get(25));
                                                                                                                                                                                        BB = (Listdefectloc.get(26));
                                                                                                                                                                                        CC = (Listdefectloc.get(27));
                                                                                                                                                                                        DD = (Listdefectloc.get(28));
                                                                                                                                                                                        EE = (Listdefectloc.get(29));
                                                                                                                                                                                        FF = (Listdefectloc.get(30));
                                                                                                                                                                                        GG = (Listdefectloc.get(31));
                                                                                                                                                                                        HH = (Listdefectloc.get(32));
                                                                                                                                                                                        II = (Listdefectloc.get(33));
                                                                                                                                                                                        JJ = (Listdefectloc.get(34));
                                                                                                                                                                                        KK = (Listdefectloc.get(35));
                                                                                                                                                                                        LL = (Listdefectloc.get(36));
                                                                                                                                                                                        MM = (Listdefectloc.get(37));
                                                                                                                                                                                        NN = (Listdefectloc.get(38));
                                                                                                                                                                                        OO = (Listdefectloc.get(39));
                                                                                                                                                                                        PP = (Listdefectloc.get(40));
                                                                                                                                                                                        QQ = (Listdefectloc.get(41));
                                                                                                                                                                                        SS = (Listdefectloc.get(42));

                                                                                                                                                                                    } else {
                                                                                                                                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 44)) {
                                                                                                                                                                                            A = (Listdefectloc.get(0));

                                                                                                                                                                                            B = (Listdefectloc.get(1));
                                                                                                                                                                                            C = (Listdefectloc.get(2));
                                                                                                                                                                                            D = (Listdefectloc.get(3));
                                                                                                                                                                                            E = (Listdefectloc.get(4));
                                                                                                                                                                                            F = (Listdefectloc.get(5));
                                                                                                                                                                                            G = (Listdefectloc.get(6));
                                                                                                                                                                                            H = (Listdefectloc.get(7));
                                                                                                                                                                                            I = (Listdefectloc.get(8));
                                                                                                                                                                                            J = (Listdefectloc.get(9));
                                                                                                                                                                                            K = (Listdefectloc.get(10));
                                                                                                                                                                                            L = (Listdefectloc.get(11));
                                                                                                                                                                                            M = (Listdefectloc.get(12));
                                                                                                                                                                                            N = (Listdefectloc.get(13));
                                                                                                                                                                                            O = (Listdefectloc.get(14));
                                                                                                                                                                                            P = (Listdefectloc.get(15));
                                                                                                                                                                                            Q = (Listdefectloc.get(16));
                                                                                                                                                                                            S = (Listdefectloc.get(17));
                                                                                                                                                                                            T = (Listdefectloc.get(18));
                                                                                                                                                                                            U = (Listdefectloc.get(19));
                                                                                                                                                                                            V = (Listdefectloc.get(20));
                                                                                                                                                                                            W = (Listdefectloc.get(21));
                                                                                                                                                                                            X = (Listdefectloc.get(22));
                                                                                                                                                                                            Y = (Listdefectloc.get(23));
                                                                                                                                                                                            Z = (Listdefectloc.get(24));
                                                                                                                                                                                            AA = (Listdefectloc.get(25));
                                                                                                                                                                                            BB = (Listdefectloc.get(26));
                                                                                                                                                                                            CC = (Listdefectloc.get(27));
                                                                                                                                                                                            DD = (Listdefectloc.get(28));
                                                                                                                                                                                            EE = (Listdefectloc.get(29));
                                                                                                                                                                                            FF = (Listdefectloc.get(30));
                                                                                                                                                                                            GG = (Listdefectloc.get(31));
                                                                                                                                                                                            HH = (Listdefectloc.get(32));
                                                                                                                                                                                            II = (Listdefectloc.get(33));
                                                                                                                                                                                            JJ = (Listdefectloc.get(34));
                                                                                                                                                                                            KK = (Listdefectloc.get(35));
                                                                                                                                                                                            LL = (Listdefectloc.get(36));
                                                                                                                                                                                            MM = (Listdefectloc.get(37));
                                                                                                                                                                                            NN = (Listdefectloc.get(38));
                                                                                                                                                                                            OO = (Listdefectloc.get(39));
                                                                                                                                                                                            PP = (Listdefectloc.get(40));
                                                                                                                                                                                            QQ = (Listdefectloc.get(41));
                                                                                                                                                                                            SS = (Listdefectloc.get(42));
                                                                                                                                                                                            TT = (Listdefectloc.get(43));

                                                                                                                                                                                        } else {
                                                                                                                                                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 45)) {
                                                                                                                                                                                                A = (Listdefectloc.get(0));

                                                                                                                                                                                                B = (Listdefectloc.get(1));
                                                                                                                                                                                                C = (Listdefectloc.get(2));
                                                                                                                                                                                                D = (Listdefectloc.get(3));
                                                                                                                                                                                                E = (Listdefectloc.get(4));
                                                                                                                                                                                                F = (Listdefectloc.get(5));
                                                                                                                                                                                                G = (Listdefectloc.get(6));
                                                                                                                                                                                                H = (Listdefectloc.get(7));
                                                                                                                                                                                                I = (Listdefectloc.get(8));
                                                                                                                                                                                                J = (Listdefectloc.get(9));
                                                                                                                                                                                                K = (Listdefectloc.get(10));
                                                                                                                                                                                                L = (Listdefectloc.get(11));
                                                                                                                                                                                                M = (Listdefectloc.get(12));
                                                                                                                                                                                                N = (Listdefectloc.get(13));
                                                                                                                                                                                                O = (Listdefectloc.get(14));
                                                                                                                                                                                                P = (Listdefectloc.get(15));
                                                                                                                                                                                                Q = (Listdefectloc.get(16));
                                                                                                                                                                                                S = (Listdefectloc.get(17));
                                                                                                                                                                                                T = (Listdefectloc.get(18));
                                                                                                                                                                                                U = (Listdefectloc.get(19));
                                                                                                                                                                                                V = (Listdefectloc.get(20));
                                                                                                                                                                                                W = (Listdefectloc.get(21));
                                                                                                                                                                                                X = (Listdefectloc.get(22));
                                                                                                                                                                                                Y = (Listdefectloc.get(23));
                                                                                                                                                                                                Z = (Listdefectloc.get(24));
                                                                                                                                                                                                AA = (Listdefectloc.get(25));
                                                                                                                                                                                                BB = (Listdefectloc.get(26));
                                                                                                                                                                                                CC = (Listdefectloc.get(27));
                                                                                                                                                                                                DD = (Listdefectloc.get(28));
                                                                                                                                                                                                EE = (Listdefectloc.get(29));
                                                                                                                                                                                                FF = (Listdefectloc.get(30));
                                                                                                                                                                                                GG = (Listdefectloc.get(31));
                                                                                                                                                                                                HH = (Listdefectloc.get(32));
                                                                                                                                                                                                II = (Listdefectloc.get(33));
                                                                                                                                                                                                JJ = (Listdefectloc.get(34));
                                                                                                                                                                                                KK = (Listdefectloc.get(35));
                                                                                                                                                                                                LL = (Listdefectloc.get(36));
                                                                                                                                                                                                MM = (Listdefectloc.get(37));
                                                                                                                                                                                                NN = (Listdefectloc.get(38));
                                                                                                                                                                                                OO = (Listdefectloc.get(39));
                                                                                                                                                                                                PP = (Listdefectloc.get(40));
                                                                                                                                                                                                QQ = (Listdefectloc.get(41));
                                                                                                                                                                                                SS = (Listdefectloc.get(42));
                                                                                                                                                                                                TT = (Listdefectloc.get(43));
                                                                                                                                                                                                UU = (Listdefectloc.get(44));

                                                                                                                                                                                            } else {
                                                                                                                                                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 46)) {
                                                                                                                                                                                                    A = (Listdefectloc.get(0));

                                                                                                                                                                                                    B = (Listdefectloc.get(1));
                                                                                                                                                                                                    C = (Listdefectloc.get(2));
                                                                                                                                                                                                    D = (Listdefectloc.get(3));
                                                                                                                                                                                                    E = (Listdefectloc.get(4));
                                                                                                                                                                                                    F = (Listdefectloc.get(5));
                                                                                                                                                                                                    G = (Listdefectloc.get(6));
                                                                                                                                                                                                    H = (Listdefectloc.get(7));
                                                                                                                                                                                                    I = (Listdefectloc.get(8));
                                                                                                                                                                                                    J = (Listdefectloc.get(9));
                                                                                                                                                                                                    K = (Listdefectloc.get(10));
                                                                                                                                                                                                    L = (Listdefectloc.get(11));
                                                                                                                                                                                                    M = (Listdefectloc.get(12));
                                                                                                                                                                                                    N = (Listdefectloc.get(13));
                                                                                                                                                                                                    O = (Listdefectloc.get(14));
                                                                                                                                                                                                    P = (Listdefectloc.get(15));
                                                                                                                                                                                                    Q = (Listdefectloc.get(16));
                                                                                                                                                                                                    S = (Listdefectloc.get(17));
                                                                                                                                                                                                    T = (Listdefectloc.get(18));
                                                                                                                                                                                                    U = (Listdefectloc.get(19));
                                                                                                                                                                                                    V = (Listdefectloc.get(20));
                                                                                                                                                                                                    W = (Listdefectloc.get(21));
                                                                                                                                                                                                    X = (Listdefectloc.get(22));
                                                                                                                                                                                                    Y = (Listdefectloc.get(23));
                                                                                                                                                                                                    Z = (Listdefectloc.get(24));
                                                                                                                                                                                                    AA = (Listdefectloc.get(25));
                                                                                                                                                                                                    BB = (Listdefectloc.get(26));
                                                                                                                                                                                                    CC = (Listdefectloc.get(27));
                                                                                                                                                                                                    DD = (Listdefectloc.get(28));
                                                                                                                                                                                                    EE = (Listdefectloc.get(29));
                                                                                                                                                                                                    FF = (Listdefectloc.get(30));
                                                                                                                                                                                                    GG = (Listdefectloc.get(31));
                                                                                                                                                                                                    HH = (Listdefectloc.get(32));
                                                                                                                                                                                                    II = (Listdefectloc.get(33));
                                                                                                                                                                                                    JJ = (Listdefectloc.get(34));
                                                                                                                                                                                                    KK = (Listdefectloc.get(35));
                                                                                                                                                                                                    LL = (Listdefectloc.get(36));
                                                                                                                                                                                                    MM = (Listdefectloc.get(37));
                                                                                                                                                                                                    NN = (Listdefectloc.get(38));
                                                                                                                                                                                                    OO = (Listdefectloc.get(39));
                                                                                                                                                                                                    PP = (Listdefectloc.get(40));
                                                                                                                                                                                                    QQ = (Listdefectloc.get(41));
                                                                                                                                                                                                    SS = (Listdefectloc.get(42));
                                                                                                                                                                                                    TT = (Listdefectloc.get(43));
                                                                                                                                                                                                    UU = (Listdefectloc.get(44));
                                                                                                                                                                                                    VV = (Listdefectloc.get(45));

                                                                                                                                                                                                } else {
                                                                                                                                                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 47)) {
                                                                                                                                                                                                        A = (Listdefectloc.get(0));

                                                                                                                                                                                                        B = (Listdefectloc.get(1));
                                                                                                                                                                                                        C = (Listdefectloc.get(2));
                                                                                                                                                                                                        D = (Listdefectloc.get(3));
                                                                                                                                                                                                        E = (Listdefectloc.get(4));
                                                                                                                                                                                                        F = (Listdefectloc.get(5));
                                                                                                                                                                                                        G = (Listdefectloc.get(6));
                                                                                                                                                                                                        H = (Listdefectloc.get(7));
                                                                                                                                                                                                        I = (Listdefectloc.get(8));
                                                                                                                                                                                                        J = (Listdefectloc.get(9));
                                                                                                                                                                                                        K = (Listdefectloc.get(10));
                                                                                                                                                                                                        L = (Listdefectloc.get(11));
                                                                                                                                                                                                        M = (Listdefectloc.get(12));
                                                                                                                                                                                                        N = (Listdefectloc.get(13));
                                                                                                                                                                                                        O = (Listdefectloc.get(14));
                                                                                                                                                                                                        P = (Listdefectloc.get(15));
                                                                                                                                                                                                        Q = (Listdefectloc.get(16));
                                                                                                                                                                                                        S = (Listdefectloc.get(17));
                                                                                                                                                                                                        T = (Listdefectloc.get(18));
                                                                                                                                                                                                        U = (Listdefectloc.get(19));
                                                                                                                                                                                                        V = (Listdefectloc.get(20));
                                                                                                                                                                                                        W = (Listdefectloc.get(21));
                                                                                                                                                                                                        X = (Listdefectloc.get(22));
                                                                                                                                                                                                        Y = (Listdefectloc.get(23));
                                                                                                                                                                                                        Z = (Listdefectloc.get(24));
                                                                                                                                                                                                        AA = (Listdefectloc.get(25));
                                                                                                                                                                                                        BB = (Listdefectloc.get(26));
                                                                                                                                                                                                        CC = (Listdefectloc.get(27));
                                                                                                                                                                                                        DD = (Listdefectloc.get(28));
                                                                                                                                                                                                        EE = (Listdefectloc.get(29));
                                                                                                                                                                                                        FF = (Listdefectloc.get(30));
                                                                                                                                                                                                        GG = (Listdefectloc.get(31));
                                                                                                                                                                                                        HH = (Listdefectloc.get(32));
                                                                                                                                                                                                        II = (Listdefectloc.get(33));
                                                                                                                                                                                                        JJ = (Listdefectloc.get(34));
                                                                                                                                                                                                        KK = (Listdefectloc.get(35));
                                                                                                                                                                                                        LL = (Listdefectloc.get(36));
                                                                                                                                                                                                        MM = (Listdefectloc.get(37));
                                                                                                                                                                                                        NN = (Listdefectloc.get(38));
                                                                                                                                                                                                        OO = (Listdefectloc.get(39));
                                                                                                                                                                                                        PP = (Listdefectloc.get(40));
                                                                                                                                                                                                        QQ = (Listdefectloc.get(41));
                                                                                                                                                                                                        SS = (Listdefectloc.get(42));
                                                                                                                                                                                                        TT = (Listdefectloc.get(43));
                                                                                                                                                                                                        UU = (Listdefectloc.get(44));
                                                                                                                                                                                                        VV = (Listdefectloc.get(45));
                                                                                                                                                                                                        WW = (Listdefectloc.get(46));

                                                                                                                                                                                                    } else {
                                                                                                                                                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 48)) {
                                                                                                                                                                                                            A = (Listdefectloc.get(0));

                                                                                                                                                                                                            B = (Listdefectloc.get(1));
                                                                                                                                                                                                            C = (Listdefectloc.get(2));
                                                                                                                                                                                                            D = (Listdefectloc.get(3));
                                                                                                                                                                                                            E = (Listdefectloc.get(4));
                                                                                                                                                                                                            F = (Listdefectloc.get(5));
                                                                                                                                                                                                            G = (Listdefectloc.get(6));
                                                                                                                                                                                                            H = (Listdefectloc.get(7));
                                                                                                                                                                                                            I = (Listdefectloc.get(8));
                                                                                                                                                                                                            J = (Listdefectloc.get(9));
                                                                                                                                                                                                            K = (Listdefectloc.get(10));
                                                                                                                                                                                                            L = (Listdefectloc.get(11));
                                                                                                                                                                                                            M = (Listdefectloc.get(12));
                                                                                                                                                                                                            N = (Listdefectloc.get(13));
                                                                                                                                                                                                            O = (Listdefectloc.get(14));
                                                                                                                                                                                                            P = (Listdefectloc.get(15));
                                                                                                                                                                                                            Q = (Listdefectloc.get(16));
                                                                                                                                                                                                            S = (Listdefectloc.get(17));
                                                                                                                                                                                                            T = (Listdefectloc.get(18));
                                                                                                                                                                                                            U = (Listdefectloc.get(19));
                                                                                                                                                                                                            V = (Listdefectloc.get(20));
                                                                                                                                                                                                            W = (Listdefectloc.get(21));
                                                                                                                                                                                                            X = (Listdefectloc.get(22));
                                                                                                                                                                                                            Y = (Listdefectloc.get(23));
                                                                                                                                                                                                            Z = (Listdefectloc.get(24));
                                                                                                                                                                                                            AA = (Listdefectloc.get(25));
                                                                                                                                                                                                            BB = (Listdefectloc.get(26));
                                                                                                                                                                                                            CC = (Listdefectloc.get(27));
                                                                                                                                                                                                            DD = (Listdefectloc.get(28));
                                                                                                                                                                                                            EE = (Listdefectloc.get(29));
                                                                                                                                                                                                            FF = (Listdefectloc.get(30));
                                                                                                                                                                                                            GG = (Listdefectloc.get(31));
                                                                                                                                                                                                            HH = (Listdefectloc.get(32));
                                                                                                                                                                                                            II = (Listdefectloc.get(33));
                                                                                                                                                                                                            JJ = (Listdefectloc.get(34));
                                                                                                                                                                                                            KK = (Listdefectloc.get(35));
                                                                                                                                                                                                            LL = (Listdefectloc.get(36));
                                                                                                                                                                                                            MM = (Listdefectloc.get(37));
                                                                                                                                                                                                            NN = (Listdefectloc.get(38));
                                                                                                                                                                                                            OO = (Listdefectloc.get(39));
                                                                                                                                                                                                            PP = (Listdefectloc.get(40));
                                                                                                                                                                                                            QQ = (Listdefectloc.get(41));
                                                                                                                                                                                                            SS = (Listdefectloc.get(42));
                                                                                                                                                                                                            TT = (Listdefectloc.get(43));
                                                                                                                                                                                                            UU = (Listdefectloc.get(44));
                                                                                                                                                                                                            VV = (Listdefectloc.get(45));
                                                                                                                                                                                                            WW = (Listdefectloc.get(46));
                                                                                                                                                                                                            XX = (Listdefectloc.get(47));

                                                                                                                                                                                                        } else {
                                                                                                                                                                                                            if ((Listdefectloc != null) && (Listdefectloc.size() == 49)) {
                                                                                                                                                                                                                A = (Listdefectloc.get(0));

                                                                                                                                                                                                                B = (Listdefectloc.get(1));
                                                                                                                                                                                                                C = (Listdefectloc.get(2));
                                                                                                                                                                                                                D = (Listdefectloc.get(3));
                                                                                                                                                                                                                E = (Listdefectloc.get(4));
                                                                                                                                                                                                                F = (Listdefectloc.get(5));
                                                                                                                                                                                                                G = (Listdefectloc.get(6));
                                                                                                                                                                                                                H = (Listdefectloc.get(7));
                                                                                                                                                                                                                I = (Listdefectloc.get(8));
                                                                                                                                                                                                                J = (Listdefectloc.get(9));
                                                                                                                                                                                                                K = (Listdefectloc.get(10));
                                                                                                                                                                                                                L = (Listdefectloc.get(11));
                                                                                                                                                                                                                M = (Listdefectloc.get(12));
                                                                                                                                                                                                                N = (Listdefectloc.get(13));
                                                                                                                                                                                                                O = (Listdefectloc.get(14));
                                                                                                                                                                                                                P = (Listdefectloc.get(15));
                                                                                                                                                                                                                Q = (Listdefectloc.get(16));
                                                                                                                                                                                                                S = (Listdefectloc.get(17));
                                                                                                                                                                                                                T = (Listdefectloc.get(18));
                                                                                                                                                                                                                U = (Listdefectloc.get(19));
                                                                                                                                                                                                                V = (Listdefectloc.get(20));
                                                                                                                                                                                                                W = (Listdefectloc.get(21));
                                                                                                                                                                                                                X = (Listdefectloc.get(22));
                                                                                                                                                                                                                Y = (Listdefectloc.get(23));
                                                                                                                                                                                                                Z = (Listdefectloc.get(24));
                                                                                                                                                                                                                AA = (Listdefectloc.get(25));
                                                                                                                                                                                                                BB = (Listdefectloc.get(26));
                                                                                                                                                                                                                CC = (Listdefectloc.get(27));
                                                                                                                                                                                                                DD = (Listdefectloc.get(28));
                                                                                                                                                                                                                EE = (Listdefectloc.get(29));
                                                                                                                                                                                                                FF = (Listdefectloc.get(30));
                                                                                                                                                                                                                GG = (Listdefectloc.get(31));
                                                                                                                                                                                                                HH = (Listdefectloc.get(32));
                                                                                                                                                                                                                II = (Listdefectloc.get(33));
                                                                                                                                                                                                                JJ = (Listdefectloc.get(34));
                                                                                                                                                                                                                KK = (Listdefectloc.get(35));
                                                                                                                                                                                                                LL = (Listdefectloc.get(36));
                                                                                                                                                                                                                MM = (Listdefectloc.get(37));
                                                                                                                                                                                                                NN = (Listdefectloc.get(38));
                                                                                                                                                                                                                OO = (Listdefectloc.get(39));
                                                                                                                                                                                                                PP = (Listdefectloc.get(40));
                                                                                                                                                                                                                QQ = (Listdefectloc.get(41));
                                                                                                                                                                                                                SS = (Listdefectloc.get(42));
                                                                                                                                                                                                                TT = (Listdefectloc.get(43));
                                                                                                                                                                                                                UU = (Listdefectloc.get(44));
                                                                                                                                                                                                                VV = (Listdefectloc.get(45));
                                                                                                                                                                                                                WW = (Listdefectloc.get(46));
                                                                                                                                                                                                                XX = (Listdefectloc.get(47));
                                                                                                                                                                                                                YY = (Listdefectloc.get(48));

                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                if ((Listdefectloc != null) && (Listdefectloc.size() == 50)) {
                                                                                                                                                                                                                    A = (Listdefectloc.get(0));

                                                                                                                                                                                                                    B = (Listdefectloc.get(1));
                                                                                                                                                                                                                    C = (Listdefectloc.get(2));
                                                                                                                                                                                                                    D = (Listdefectloc.get(3));
                                                                                                                                                                                                                    E = (Listdefectloc.get(4));
                                                                                                                                                                                                                    F = (Listdefectloc.get(5));
                                                                                                                                                                                                                    G = (Listdefectloc.get(6));
                                                                                                                                                                                                                    H = (Listdefectloc.get(7));
                                                                                                                                                                                                                    I = (Listdefectloc.get(8));
                                                                                                                                                                                                                    J = (Listdefectloc.get(9));
                                                                                                                                                                                                                    K = (Listdefectloc.get(10));
                                                                                                                                                                                                                    L = (Listdefectloc.get(11));
                                                                                                                                                                                                                    M = (Listdefectloc.get(12));
                                                                                                                                                                                                                    N = (Listdefectloc.get(13));
                                                                                                                                                                                                                    O = (Listdefectloc.get(14));
                                                                                                                                                                                                                    P = (Listdefectloc.get(15));
                                                                                                                                                                                                                    Q = (Listdefectloc.get(16));
                                                                                                                                                                                                                    S = (Listdefectloc.get(17));
                                                                                                                                                                                                                    T = (Listdefectloc.get(18));
                                                                                                                                                                                                                    U = (Listdefectloc.get(19));
                                                                                                                                                                                                                    V = (Listdefectloc.get(20));
                                                                                                                                                                                                                    W = (Listdefectloc.get(21));
                                                                                                                                                                                                                    X = (Listdefectloc.get(22));
                                                                                                                                                                                                                    Y = (Listdefectloc.get(23));
                                                                                                                                                                                                                    Z = (Listdefectloc.get(24));
                                                                                                                                                                                                                    AA = (Listdefectloc.get(25));
                                                                                                                                                                                                                    BB = (Listdefectloc.get(26));
                                                                                                                                                                                                                    CC = (Listdefectloc.get(27));
                                                                                                                                                                                                                    DD = (Listdefectloc.get(28));
                                                                                                                                                                                                                    EE = (Listdefectloc.get(29));
                                                                                                                                                                                                                    FF = (Listdefectloc.get(30));
                                                                                                                                                                                                                    GG = (Listdefectloc.get(31));
                                                                                                                                                                                                                    HH = (Listdefectloc.get(32));
                                                                                                                                                                                                                    II = (Listdefectloc.get(33));
                                                                                                                                                                                                                    JJ = (Listdefectloc.get(34));
                                                                                                                                                                                                                    KK = (Listdefectloc.get(35));
                                                                                                                                                                                                                    LL = (Listdefectloc.get(36));
                                                                                                                                                                                                                    MM = (Listdefectloc.get(37));
                                                                                                                                                                                                                    NN = (Listdefectloc.get(38));
                                                                                                                                                                                                                    OO = (Listdefectloc.get(39));
                                                                                                                                                                                                                    PP = (Listdefectloc.get(40));
                                                                                                                                                                                                                    QQ = (Listdefectloc.get(41));
                                                                                                                                                                                                                    SS = (Listdefectloc.get(42));
                                                                                                                                                                                                                    TT = (Listdefectloc.get(43));
                                                                                                                                                                                                                    UU = (Listdefectloc.get(44));
                                                                                                                                                                                                                    VV = (Listdefectloc.get(45));
                                                                                                                                                                                                                    WW = (Listdefectloc.get(46));
                                                                                                                                                                                                                    XX = (Listdefectloc.get(47));
                                                                                                                                                                                                                    YY = (Listdefectloc.get(48));
                                                                                                                                                                                                                    ZZ = (Listdefectloc.get(49));

                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                    if ((Listdefectloc != null) && (Listdefectloc.size() == 51)) {
                                                                                                                                                                                                                        A = (Listdefectloc.get(0));

                                                                                                                                                                                                                        B = (Listdefectloc.get(1));
                                                                                                                                                                                                                        C = (Listdefectloc.get(2));
                                                                                                                                                                                                                        D = (Listdefectloc.get(3));
                                                                                                                                                                                                                        E = (Listdefectloc.get(4));
                                                                                                                                                                                                                        F = (Listdefectloc.get(5));
                                                                                                                                                                                                                        G = (Listdefectloc.get(6));
                                                                                                                                                                                                                        H = (Listdefectloc.get(7));
                                                                                                                                                                                                                        I = (Listdefectloc.get(8));
                                                                                                                                                                                                                        J = (Listdefectloc.get(9));
                                                                                                                                                                                                                        K = (Listdefectloc.get(10));
                                                                                                                                                                                                                        L = (Listdefectloc.get(11));
                                                                                                                                                                                                                        M = (Listdefectloc.get(12));
                                                                                                                                                                                                                        N = (Listdefectloc.get(13));
                                                                                                                                                                                                                        O = (Listdefectloc.get(14));
                                                                                                                                                                                                                        P = (Listdefectloc.get(15));
                                                                                                                                                                                                                        Q = (Listdefectloc.get(16));
                                                                                                                                                                                                                        S = (Listdefectloc.get(17));
                                                                                                                                                                                                                        T = (Listdefectloc.get(18));
                                                                                                                                                                                                                        U = (Listdefectloc.get(19));
                                                                                                                                                                                                                        V = (Listdefectloc.get(20));
                                                                                                                                                                                                                        W = (Listdefectloc.get(21));
                                                                                                                                                                                                                        X = (Listdefectloc.get(22));
                                                                                                                                                                                                                        Y = (Listdefectloc.get(23));
                                                                                                                                                                                                                        Z = (Listdefectloc.get(24));
                                                                                                                                                                                                                        AA = (Listdefectloc.get(25));
                                                                                                                                                                                                                        BB = (Listdefectloc.get(26));
                                                                                                                                                                                                                        CC = (Listdefectloc.get(27));
                                                                                                                                                                                                                        DD = (Listdefectloc.get(28));
                                                                                                                                                                                                                        EE = (Listdefectloc.get(29));
                                                                                                                                                                                                                        FF = (Listdefectloc.get(30));
                                                                                                                                                                                                                        GG = (Listdefectloc.get(31));
                                                                                                                                                                                                                        HH = (Listdefectloc.get(32));
                                                                                                                                                                                                                        II = (Listdefectloc.get(33));
                                                                                                                                                                                                                        JJ = (Listdefectloc.get(34));
                                                                                                                                                                                                                        KK = (Listdefectloc.get(35));
                                                                                                                                                                                                                        LL = (Listdefectloc.get(36));
                                                                                                                                                                                                                        MM = (Listdefectloc.get(37));
                                                                                                                                                                                                                        NN = (Listdefectloc.get(38));
                                                                                                                                                                                                                        OO = (Listdefectloc.get(39));
                                                                                                                                                                                                                        PP = (Listdefectloc.get(40));
                                                                                                                                                                                                                        QQ = (Listdefectloc.get(41));
                                                                                                                                                                                                                        SS = (Listdefectloc.get(42));
                                                                                                                                                                                                                        TT = (Listdefectloc.get(43));
                                                                                                                                                                                                                        UU = (Listdefectloc.get(44));
                                                                                                                                                                                                                        VV = (Listdefectloc.get(45));
                                                                                                                                                                                                                        WW = (Listdefectloc.get(46));
                                                                                                                                                                                                                        XX = (Listdefectloc.get(47));
                                                                                                                                                                                                                        YY = (Listdefectloc.get(48));
                                                                                                                                                                                                                        ZZ = (Listdefectloc.get(49));
                                                                                                                                                                                                                        AAA = (Listdefectloc.get(50));

                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                        if ((Listdefectloc != null) && (Listdefectloc.size() == 52)) {
                                                                                                                                                                                                                            A = (Listdefectloc.get(0));

                                                                                                                                                                                                                            B = (Listdefectloc.get(1));
                                                                                                                                                                                                                            C = (Listdefectloc.get(2));
                                                                                                                                                                                                                            D = (Listdefectloc.get(3));
                                                                                                                                                                                                                            E = (Listdefectloc.get(4));
                                                                                                                                                                                                                            F = (Listdefectloc.get(5));
                                                                                                                                                                                                                            G = (Listdefectloc.get(6));
                                                                                                                                                                                                                            H = (Listdefectloc.get(7));
                                                                                                                                                                                                                            I = (Listdefectloc.get(8));
                                                                                                                                                                                                                            J = (Listdefectloc.get(9));
                                                                                                                                                                                                                            K = (Listdefectloc.get(10));
                                                                                                                                                                                                                            L = (Listdefectloc.get(11));
                                                                                                                                                                                                                            M = (Listdefectloc.get(12));
                                                                                                                                                                                                                            N = (Listdefectloc.get(13));
                                                                                                                                                                                                                            O = (Listdefectloc.get(14));
                                                                                                                                                                                                                            P = (Listdefectloc.get(15));
                                                                                                                                                                                                                            Q = (Listdefectloc.get(16));
                                                                                                                                                                                                                            S = (Listdefectloc.get(17));
                                                                                                                                                                                                                            T = (Listdefectloc.get(18));
                                                                                                                                                                                                                            U = (Listdefectloc.get(19));
                                                                                                                                                                                                                            V = (Listdefectloc.get(20));
                                                                                                                                                                                                                            W = (Listdefectloc.get(21));
                                                                                                                                                                                                                            X = (Listdefectloc.get(22));
                                                                                                                                                                                                                            Y = (Listdefectloc.get(23));
                                                                                                                                                                                                                            Z = (Listdefectloc.get(24));
                                                                                                                                                                                                                            AA = (Listdefectloc.get(25));
                                                                                                                                                                                                                            BB = (Listdefectloc.get(26));
                                                                                                                                                                                                                            CC = (Listdefectloc.get(27));
                                                                                                                                                                                                                            DD = (Listdefectloc.get(28));
                                                                                                                                                                                                                            EE = (Listdefectloc.get(29));
                                                                                                                                                                                                                            FF = (Listdefectloc.get(30));
                                                                                                                                                                                                                            GG = (Listdefectloc.get(31));
                                                                                                                                                                                                                            HH = (Listdefectloc.get(32));
                                                                                                                                                                                                                            II = (Listdefectloc.get(33));
                                                                                                                                                                                                                            JJ = (Listdefectloc.get(34));
                                                                                                                                                                                                                            KK = (Listdefectloc.get(35));
                                                                                                                                                                                                                            LL = (Listdefectloc.get(36));
                                                                                                                                                                                                                            MM = (Listdefectloc.get(37));
                                                                                                                                                                                                                            NN = (Listdefectloc.get(38));
                                                                                                                                                                                                                            OO = (Listdefectloc.get(39));
                                                                                                                                                                                                                            PP = (Listdefectloc.get(40));
                                                                                                                                                                                                                            QQ = (Listdefectloc.get(41));
                                                                                                                                                                                                                            SS = (Listdefectloc.get(42));
                                                                                                                                                                                                                            TT = (Listdefectloc.get(43));
                                                                                                                                                                                                                            UU = (Listdefectloc.get(44));
                                                                                                                                                                                                                            VV = (Listdefectloc.get(45));
                                                                                                                                                                                                                            WW = (Listdefectloc.get(46));
                                                                                                                                                                                                                            XX = (Listdefectloc.get(47));
                                                                                                                                                                                                                            YY = (Listdefectloc.get(48));
                                                                                                                                                                                                                            ZZ = (Listdefectloc.get(49));
                                                                                                                                                                                                                            AAA = (Listdefectloc.get(50));
                                                                                                                                                                                                                            BBB = (Listdefectloc.get(51));

                                                                                                                                                                                                                        } else

                                                                                                                                                                                                                        {
                                                                                                                                                                                                                            Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Contact to ISSS ", Toast.LENGTH_LONG).show();
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }

                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


        }


    }

    private class Getgr extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Sewing.this);
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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Sewing.this, android.R.layout.simple_spinner_item, Listgr);
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
            progressDialog = new ProgressDialog(Sewing.this);
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


            if ((Listdefect != null) && (Listdefect.size() == 1))

            {
                A1 = (Listdefect.get(0));
            } else

            {
                if ((Listdefect != null) && (Listdefect.size() == 2)) {
                    A1 = (Listdefect.get(0));
                    B1 = (Listdefect.get(1));
                } else {
                    if ((Listdefect != null) && (Listdefect.size() == 3)) {
                        A1 = (Listdefect.get(0));
                        B1 = (Listdefect.get(1));
                        C1 = (Listdefect.get(2));
                    } else {
                        if ((Listdefect != null) && (Listdefect.size() == 4)) {
                            A1 = (Listdefect.get(0));
                            B1 = (Listdefect.get(1));
                            C1 = (Listdefect.get(2));
                            D1 = (Listdefect.get(3));

                        } else {
                            if ((Listdefect != null) && (Listdefect.size() == 5)) {
                                A1 = (Listdefect.get(0));
                                B1 = (Listdefect.get(1));
                                C1 = (Listdefect.get(2));
                                D1 = (Listdefect.get(3));
                                E1 = (Listdefect.get(4));

                            } else {
                                if ((Listdefect != null) && (Listdefect.size() == 6)) {
                                    A1 = (Listdefect.get(0));

                                    B1 = (Listdefect.get(1));
                                    C1 = (Listdefect.get(2));
                                    D1 = (Listdefect.get(3));
                                    E1 = (Listdefect.get(4));
                                    F1 = (Listdefect.get(5));

                                } else {
                                    if ((Listdefect != null) && (Listdefect.size() == 7)) {
                                        A1 = (Listdefect.get(0));

                                        B1 = (Listdefect.get(1));
                                        C1 = (Listdefect.get(2));
                                        D1 = (Listdefect.get(3));
                                        E1 = (Listdefect.get(4));
                                        F1 = (Listdefect.get(5));
                                        G1 = (Listdefect.get(6));

                                    } else {
                                        if ((Listdefect != null) && (Listdefect.size() == 8)) {
                                            A1 = (Listdefect.get(0));

                                            B1 = (Listdefect.get(1));
                                            C1 = (Listdefect.get(2));
                                            D1 = (Listdefect.get(3));
                                            E1 = (Listdefect.get(4));
                                            F1 = (Listdefect.get(5));
                                            G1 = (Listdefect.get(6));
                                            H1 = (Listdefect.get(7));

                                        } else {
                                            if ((Listdefect != null) && (Listdefect.size() == 9)) {
                                                A1 = (Listdefect.get(0));

                                                B1 = (Listdefect.get(1));
                                                C1 = (Listdefect.get(2));
                                                D1 = (Listdefect.get(3));
                                                E1 = (Listdefect.get(4));
                                                F1 = (Listdefect.get(5));
                                                G1 = (Listdefect.get(6));
                                                H1 = (Listdefect.get(7));
                                                I1 = (Listdefect.get(8));

                                            } else {
                                                if ((Listdefect != null) && (Listdefect.size() == 10)) {
                                                    A1 = (Listdefect.get(0));

                                                    B1 = (Listdefect.get(1));
                                                    C1 = (Listdefect.get(2));
                                                    D1 = (Listdefect.get(3));
                                                    E1 = (Listdefect.get(4));
                                                    F1 = (Listdefect.get(5));
                                                    G1 = (Listdefect.get(6));
                                                    H1 = (Listdefect.get(7));
                                                    I1 = (Listdefect.get(8));
                                                    J1 = (Listdefect.get(9));

                                                } else {
                                                    if ((Listdefect != null) && (Listdefect.size() == 11)) {
                                                        A1 = (Listdefect.get(0));
                                                        B1 = (Listdefect.get(1));
                                                        C1 = (Listdefect.get(2));
                                                        D1 = (Listdefect.get(3));
                                                        E1 = (Listdefect.get(4));
                                                        F1 = (Listdefect.get(5));
                                                        G1 = (Listdefect.get(6));
                                                        H1 = (Listdefect.get(7));
                                                        I1 = (Listdefect.get(8));
                                                        J1 = (Listdefect.get(9));
                                                        K1 = (Listdefect.get(10));

                                                    } else {
                                                        if ((Listdefect != null) && (Listdefect.size() == 12)) {
                                                            A1 = (Listdefect.get(0));

                                                            B1 = (Listdefect.get(1));
                                                            C1 = (Listdefect.get(2));
                                                            D1 = (Listdefect.get(3));
                                                            E1 = (Listdefect.get(4));
                                                            F1 = (Listdefect.get(5));
                                                            G1 = (Listdefect.get(6));
                                                            H1 = (Listdefect.get(7));
                                                            I1 = (Listdefect.get(8));
                                                            J1 = (Listdefect.get(9));
                                                            K1 = (Listdefect.get(10));
                                                            L1 = (Listdefect.get(11));

                                                        } else {
                                                            if ((Listdefect != null) && (Listdefect.size() == 13)) {
                                                                A1 = (Listdefect.get(0));

                                                                B1 = (Listdefect.get(1));
                                                                C1 = (Listdefect.get(2));
                                                                D1 = (Listdefect.get(3));
                                                                E1 = (Listdefect.get(4));
                                                                F1 = (Listdefect.get(5));
                                                                G1 = (Listdefect.get(6));
                                                                H1 = (Listdefect.get(7));
                                                                I1 = (Listdefect.get(8));
                                                                J1 = (Listdefect.get(9));
                                                                K1 = (Listdefect.get(10));
                                                                L1 = (Listdefect.get(11));
                                                                M1 = (Listdefect.get(12));

                                                            } else {
                                                                if ((Listdefect != null) && (Listdefect.size() == 14)) {
                                                                    A1 = (Listdefect.get(0));

                                                                    B1 = (Listdefect.get(1));
                                                                    C1 = (Listdefect.get(2));
                                                                    D1 = (Listdefect.get(3));
                                                                    E1 = (Listdefect.get(4));
                                                                    F1 = (Listdefect.get(5));
                                                                    G1 = (Listdefect.get(6));
                                                                    H1 = (Listdefect.get(7));
                                                                    I1 = (Listdefect.get(8));
                                                                    J1 = (Listdefect.get(9));
                                                                    K1 = (Listdefect.get(10));
                                                                    L1 = (Listdefect.get(11));
                                                                    M1 = (Listdefect.get(12));
                                                                    N1 = (Listdefect.get(13));

                                                                } else {
                                                                    if ((Listdefect != null) && (Listdefect.size() == 15)) {
                                                                        A1 = (Listdefect.get(0));

                                                                        B1 = (Listdefect.get(1));
                                                                        C1 = (Listdefect.get(2));
                                                                        D1 = (Listdefect.get(3));
                                                                        E1 = (Listdefect.get(4));
                                                                        F1 = (Listdefect.get(5));
                                                                        G1 = (Listdefect.get(6));
                                                                        H1 = (Listdefect.get(7));
                                                                        I1 = (Listdefect.get(8));
                                                                        J1 = (Listdefect.get(9));
                                                                        K1 = (Listdefect.get(10));
                                                                        L1 = (Listdefect.get(11));
                                                                        M1 = (Listdefect.get(12));
                                                                        N1 = (Listdefect.get(13));
                                                                        O1 = (Listdefect.get(14));

                                                                    } else {
                                                                        if ((Listdefect != null) && (Listdefect.size() == 16)) {
                                                                            A1 = (Listdefect.get(0));

                                                                            B1 = (Listdefect.get(1));
                                                                            C1 = (Listdefect.get(2));
                                                                            D1 = (Listdefect.get(3));
                                                                            E1 = (Listdefect.get(4));
                                                                            F1 = (Listdefect.get(5));
                                                                            G1 = (Listdefect.get(6));
                                                                            H1 = (Listdefect.get(7));
                                                                            I1 = (Listdefect.get(8));
                                                                            J1 = (Listdefect.get(9));
                                                                            K1 = (Listdefect.get(10));
                                                                            L1 = (Listdefect.get(11));
                                                                            M1 = (Listdefect.get(12));
                                                                            N1 = (Listdefect.get(13));
                                                                            O1 = (Listdefect.get(14));
                                                                            P1 = (Listdefect.get(15));

                                                                        } else {
                                                                            if ((Listdefect != null) && (Listdefect.size() == 17)) {
                                                                                A1 = (Listdefect.get(0));

                                                                                B1 = (Listdefect.get(1));
                                                                                C1 = (Listdefect.get(2));
                                                                                D1 = (Listdefect.get(3));
                                                                                E1 = (Listdefect.get(4));
                                                                                F1 = (Listdefect.get(5));
                                                                                G1 = (Listdefect.get(6));
                                                                                H1 = (Listdefect.get(7));
                                                                                I1 = (Listdefect.get(8));
                                                                                J1 = (Listdefect.get(9));
                                                                                K1 = (Listdefect.get(10));
                                                                                L1 = (Listdefect.get(11));
                                                                                M1 = (Listdefect.get(12));
                                                                                N1 = (Listdefect.get(13));
                                                                                O1 = (Listdefect.get(14));
                                                                                P1 = (Listdefect.get(15));
                                                                                Q1 = (Listdefect.get(16));

                                                                            } else {
                                                                                if ((Listdefect != null) && (Listdefect.size() == 18)) {
                                                                                    A1 = (Listdefect.get(0));

                                                                                    B1 = (Listdefect.get(1));
                                                                                    C1 = (Listdefect.get(2));
                                                                                    D1 = (Listdefect.get(3));
                                                                                    E1 = (Listdefect.get(4));
                                                                                    F1 = (Listdefect.get(5));
                                                                                    G1 = (Listdefect.get(6));
                                                                                    H1 = (Listdefect.get(7));
                                                                                    I1 = (Listdefect.get(8));
                                                                                    J1 = (Listdefect.get(9));
                                                                                    K1 = (Listdefect.get(10));
                                                                                    L1 = (Listdefect.get(11));
                                                                                    M1 = (Listdefect.get(12));
                                                                                    N1 = (Listdefect.get(13));
                                                                                    O1 = (Listdefect.get(14));
                                                                                    P1 = (Listdefect.get(15));
                                                                                    Q1 = (Listdefect.get(16));
                                                                                    S1 = (Listdefect.get(17));
                                                                                } else {
                                                                                    if ((Listdefect != null) && (Listdefect.size() == 19)) {
                                                                                        A1 = (Listdefect.get(0));

                                                                                        B1 = (Listdefect.get(1));
                                                                                        C1 = (Listdefect.get(2));
                                                                                        D1 = (Listdefect.get(3));
                                                                                        E1 = (Listdefect.get(4));
                                                                                        F1 = (Listdefect.get(5));
                                                                                        G1 = (Listdefect.get(6));
                                                                                        H1 = (Listdefect.get(7));
                                                                                        I1 = (Listdefect.get(8));
                                                                                        J1 = (Listdefect.get(9));
                                                                                        K1 = (Listdefect.get(10));
                                                                                        L1 = (Listdefect.get(11));
                                                                                        M1 = (Listdefect.get(12));
                                                                                        N1 = (Listdefect.get(13));
                                                                                        O1 = (Listdefect.get(14));
                                                                                        P1 = (Listdefect.get(15));
                                                                                        Q1 = (Listdefect.get(16));
                                                                                        S1 = (Listdefect.get(17));
                                                                                        T1 = (Listdefect.get(18));

                                                                                    } else {


                                                                                        if ((Listdefect != null) && (Listdefect.size() == 20)) {
                                                                                            A1 = (Listdefect.get(0));

                                                                                            B1 = (Listdefect.get(1));
                                                                                            C1 = (Listdefect.get(2));
                                                                                            D1 = (Listdefect.get(3));
                                                                                            E1 = (Listdefect.get(4));
                                                                                            F1 = (Listdefect.get(5));
                                                                                            G1 = (Listdefect.get(6));
                                                                                            H1 = (Listdefect.get(7));
                                                                                            I1 = (Listdefect.get(8));
                                                                                            J1 = (Listdefect.get(9));
                                                                                            K1 = (Listdefect.get(10));
                                                                                            L1 = (Listdefect.get(11));
                                                                                            M1 = (Listdefect.get(12));
                                                                                            N1 = (Listdefect.get(13));
                                                                                            O1 = (Listdefect.get(14));
                                                                                            P1 = (Listdefect.get(15));
                                                                                            Q1 = (Listdefect.get(16));
                                                                                            S1 = (Listdefect.get(17));
                                                                                            T1 = (Listdefect.get(18));
                                                                                            U1 = (Listdefect.get(19));

                                                                                        } else {
                                                                                            if ((Listdefect != null) && (Listdefect.size() == 21)) {
                                                                                                A1 = (Listdefect.get(0));

                                                                                                B1 = (Listdefect.get(1));
                                                                                                C1 = (Listdefect.get(2));
                                                                                                D1 = (Listdefect.get(3));
                                                                                                E1 = (Listdefect.get(4));
                                                                                                F1 = (Listdefect.get(5));
                                                                                                G1 = (Listdefect.get(6));
                                                                                                H1 = (Listdefect.get(7));
                                                                                                I1 = (Listdefect.get(8));
                                                                                                J1 = (Listdefect.get(9));
                                                                                                K1 = (Listdefect.get(10));
                                                                                                L1 = (Listdefect.get(11));
                                                                                                M1 = (Listdefect.get(12));
                                                                                                N1 = (Listdefect.get(13));
                                                                                                O1 = (Listdefect.get(14));
                                                                                                P1 = (Listdefect.get(15));
                                                                                                Q1 = (Listdefect.get(16));
                                                                                                S1 = (Listdefect.get(17));
                                                                                                T1 = (Listdefect.get(18));
                                                                                                U1 = (Listdefect.get(19));
                                                                                                V1 = (Listdefect.get(20));

                                                                                            } else {
                                                                                                if ((Listdefect != null) && (Listdefect.size() == 22)) {
                                                                                                    A1 = (Listdefect.get(0));

                                                                                                    B1 = (Listdefect.get(1));
                                                                                                    C1 = (Listdefect.get(2));
                                                                                                    D1 = (Listdefect.get(3));
                                                                                                    E1 = (Listdefect.get(4));
                                                                                                    F1 = (Listdefect.get(5));
                                                                                                    G1 = (Listdefect.get(6));
                                                                                                    H1 = (Listdefect.get(7));
                                                                                                    I1 = (Listdefect.get(8));
                                                                                                    J1 = (Listdefect.get(9));
                                                                                                    K1 = (Listdefect.get(10));
                                                                                                    L1 = (Listdefect.get(11));
                                                                                                    M1 = (Listdefect.get(12));
                                                                                                    N1 = (Listdefect.get(13));
                                                                                                    O1 = (Listdefect.get(14));
                                                                                                    P1 = (Listdefect.get(15));
                                                                                                    Q1 = (Listdefect.get(16));
                                                                                                    S1 = (Listdefect.get(17));
                                                                                                    T1 = (Listdefect.get(18));
                                                                                                    U1 = (Listdefect.get(19));
                                                                                                    V1 = (Listdefect.get(20));
                                                                                                    W1 = (Listdefect.get(21));

                                                                                                } else {
                                                                                                    if ((Listdefect != null) && (Listdefect.size() == 23)) {
                                                                                                        A1 = (Listdefect.get(0));

                                                                                                        B1 = (Listdefect.get(1));
                                                                                                        C1 = (Listdefect.get(2));
                                                                                                        D1 = (Listdefect.get(3));
                                                                                                        E1 = (Listdefect.get(4));
                                                                                                        F1 = (Listdefect.get(5));
                                                                                                        G1 = (Listdefect.get(6));
                                                                                                        H1 = (Listdefect.get(7));
                                                                                                        I1 = (Listdefect.get(8));
                                                                                                        J1 = (Listdefect.get(9));
                                                                                                        K1 = (Listdefect.get(10));
                                                                                                        L1 = (Listdefect.get(11));
                                                                                                        M1 = (Listdefect.get(12));
                                                                                                        N1 = (Listdefect.get(13));
                                                                                                        O1 = (Listdefect.get(14));
                                                                                                        P1 = (Listdefect.get(15));
                                                                                                        Q1 = (Listdefect.get(16));
                                                                                                        S1 = (Listdefect.get(17));
                                                                                                        T1 = (Listdefect.get(18));
                                                                                                        U1 = (Listdefect.get(19));
                                                                                                        V1 = (Listdefect.get(20));
                                                                                                        W1 = (Listdefect.get(21));
                                                                                                        X1 = (Listdefect.get(22));

                                                                                                    } else {
                                                                                                        if ((Listdefect != null) && (Listdefect.size() == 24)) {
                                                                                                            A1 = (Listdefect.get(0));

                                                                                                            B1 = (Listdefect.get(1));
                                                                                                            C1 = (Listdefect.get(2));
                                                                                                            D1 = (Listdefect.get(3));
                                                                                                            E1 = (Listdefect.get(4));
                                                                                                            F1 = (Listdefect.get(5));
                                                                                                            G1 = (Listdefect.get(6));
                                                                                                            H1 = (Listdefect.get(7));
                                                                                                            I1 = (Listdefect.get(8));
                                                                                                            J1 = (Listdefect.get(9));
                                                                                                            K1 = (Listdefect.get(10));
                                                                                                            L1 = (Listdefect.get(11));
                                                                                                            M1 = (Listdefect.get(12));
                                                                                                            N1 = (Listdefect.get(13));
                                                                                                            O1 = (Listdefect.get(14));
                                                                                                            P1 = (Listdefect.get(15));
                                                                                                            Q1 = (Listdefect.get(16));
                                                                                                            S1 = (Listdefect.get(17));
                                                                                                            T1 = (Listdefect.get(18));
                                                                                                            U1 = (Listdefect.get(19));
                                                                                                            V1 = (Listdefect.get(20));
                                                                                                            W1 = (Listdefect.get(21));
                                                                                                            X1 = (Listdefect.get(22));
                                                                                                            Y1 = (Listdefect.get(23));

                                                                                                        } else {
                                                                                                            if ((Listdefect != null) && (Listdefect.size() == 25)) {
                                                                                                                A1 = (Listdefect.get(0));

                                                                                                                B1 = (Listdefect.get(1));
                                                                                                                C1 = (Listdefect.get(2));
                                                                                                                D1 = (Listdefect.get(3));
                                                                                                                E1 = (Listdefect.get(4));
                                                                                                                F1 = (Listdefect.get(5));
                                                                                                                G1 = (Listdefect.get(6));
                                                                                                                H1 = (Listdefect.get(7));
                                                                                                                I1 = (Listdefect.get(8));
                                                                                                                J1 = (Listdefect.get(9));
                                                                                                                K1 = (Listdefect.get(10));
                                                                                                                L1 = (Listdefect.get(11));
                                                                                                                M1 = (Listdefect.get(12));
                                                                                                                N1 = (Listdefect.get(13));
                                                                                                                O1 = (Listdefect.get(14));
                                                                                                                P1 = (Listdefect.get(15));
                                                                                                                Q1 = (Listdefect.get(16));
                                                                                                                S1 = (Listdefect.get(17));
                                                                                                                T1 = (Listdefect.get(18));
                                                                                                                U1 = (Listdefect.get(19));
                                                                                                                V1 = (Listdefect.get(20));
                                                                                                                W1 = (Listdefect.get(21));
                                                                                                                X1 = (Listdefect.get(22));
                                                                                                                Y1 = (Listdefect.get(23));
                                                                                                                Z1 = (Listdefect.get(24));

                                                                                                            } else {
                                                                                                                if ((Listdefect != null) && (Listdefect.size() == 26)) {
                                                                                                                    A1 = (Listdefect.get(0));

                                                                                                                    B1 = (Listdefect.get(1));
                                                                                                                    C1 = (Listdefect.get(2));
                                                                                                                    D1 = (Listdefect.get(3));
                                                                                                                    E1 = (Listdefect.get(4));
                                                                                                                    F1 = (Listdefect.get(5));
                                                                                                                    G1 = (Listdefect.get(6));
                                                                                                                    H1 = (Listdefect.get(7));
                                                                                                                    I1 = (Listdefect.get(8));
                                                                                                                    J1 = (Listdefect.get(9));
                                                                                                                    K1 = (Listdefect.get(10));
                                                                                                                    L1 = (Listdefect.get(11));
                                                                                                                    M1 = (Listdefect.get(12));
                                                                                                                    N1 = (Listdefect.get(13));
                                                                                                                    O1 = (Listdefect.get(14));
                                                                                                                    P1 = (Listdefect.get(15));
                                                                                                                    Q1 = (Listdefect.get(16));
                                                                                                                    S1 = (Listdefect.get(17));
                                                                                                                    T1 = (Listdefect.get(18));
                                                                                                                    U1 = (Listdefect.get(19));
                                                                                                                    V1 = (Listdefect.get(20));
                                                                                                                    W1 = (Listdefect.get(21));
                                                                                                                    X1 = (Listdefect.get(22));
                                                                                                                    Y1 = (Listdefect.get(23));
                                                                                                                    Z1 = (Listdefect.get(24));
                                                                                                                    AA1 = (Listdefect.get(25));
                                                                                                                } else {
                                                                                                                    if ((Listdefect != null) && (Listdefect.size() == 27)) {
                                                                                                                        A1 = (Listdefect.get(0));

                                                                                                                        B1 = (Listdefect.get(1));
                                                                                                                        C1 = (Listdefect.get(2));
                                                                                                                        D1 = (Listdefect.get(3));
                                                                                                                        E1 = (Listdefect.get(4));
                                                                                                                        F1 = (Listdefect.get(5));
                                                                                                                        G1 = (Listdefect.get(6));
                                                                                                                        H1 = (Listdefect.get(7));
                                                                                                                        I1 = (Listdefect.get(8));
                                                                                                                        J1 = (Listdefect.get(9));
                                                                                                                        K1 = (Listdefect.get(10));
                                                                                                                        L1 = (Listdefect.get(11));
                                                                                                                        M1 = (Listdefect.get(12));
                                                                                                                        N1 = (Listdefect.get(13));
                                                                                                                        O1 = (Listdefect.get(14));
                                                                                                                        P1 = (Listdefect.get(15));
                                                                                                                        Q1 = (Listdefect.get(16));
                                                                                                                        S1 = (Listdefect.get(17));
                                                                                                                        T1 = (Listdefect.get(18));
                                                                                                                        U1 = (Listdefect.get(19));
                                                                                                                        V1 = (Listdefect.get(20));
                                                                                                                        W1 = (Listdefect.get(21));
                                                                                                                        X1 = (Listdefect.get(22));
                                                                                                                        Y1 = (Listdefect.get(23));
                                                                                                                        Z1 = (Listdefect.get(24));
                                                                                                                        AA1 = (Listdefect.get(25));
                                                                                                                        BB1 = (Listdefect.get(26));

                                                                                                                    } else {
                                                                                                                        if ((Listdefect != null) && (Listdefect.size() == 28)) {
                                                                                                                            A1 = (Listdefect.get(0));

                                                                                                                            B1 = (Listdefect.get(1));
                                                                                                                            C1 = (Listdefect.get(2));
                                                                                                                            D1 = (Listdefect.get(3));
                                                                                                                            E1 = (Listdefect.get(4));
                                                                                                                            F1 = (Listdefect.get(5));
                                                                                                                            G1 = (Listdefect.get(6));
                                                                                                                            H1 = (Listdefect.get(7));
                                                                                                                            I1 = (Listdefect.get(8));
                                                                                                                            J1 = (Listdefect.get(9));
                                                                                                                            K1 = (Listdefect.get(10));
                                                                                                                            L1 = (Listdefect.get(11));
                                                                                                                            M1 = (Listdefect.get(12));
                                                                                                                            N1 = (Listdefect.get(13));
                                                                                                                            O1 = (Listdefect.get(14));
                                                                                                                            P1 = (Listdefect.get(15));
                                                                                                                            Q1 = (Listdefect.get(16));
                                                                                                                            S1 = (Listdefect.get(17));
                                                                                                                            T1 = (Listdefect.get(18));
                                                                                                                            U1 = (Listdefect.get(19));
                                                                                                                            V1 = (Listdefect.get(20));
                                                                                                                            W1 = (Listdefect.get(21));
                                                                                                                            X1 = (Listdefect.get(22));
                                                                                                                            Y1 = (Listdefect.get(23));
                                                                                                                            Z1 = (Listdefect.get(24));
                                                                                                                            AA1 = (Listdefect.get(25));
                                                                                                                            BB1 = (Listdefect.get(26));
                                                                                                                            CC1 = (Listdefect.get(27));

                                                                                                                        } else {
                                                                                                                            if ((Listdefect != null) && (Listdefect.size() == 29)) {
                                                                                                                                A1 = (Listdefect.get(0));

                                                                                                                                B1 = (Listdefect.get(1));
                                                                                                                                C1 = (Listdefect.get(2));
                                                                                                                                D1 = (Listdefect.get(3));
                                                                                                                                E1 = (Listdefect.get(4));
                                                                                                                                F1 = (Listdefect.get(5));
                                                                                                                                G1 = (Listdefect.get(6));
                                                                                                                                H1 = (Listdefect.get(7));
                                                                                                                                I1 = (Listdefect.get(8));
                                                                                                                                J1 = (Listdefect.get(9));
                                                                                                                                K1 = (Listdefect.get(10));
                                                                                                                                L1 = (Listdefect.get(11));
                                                                                                                                M1 = (Listdefect.get(12));
                                                                                                                                N1 = (Listdefect.get(13));
                                                                                                                                O1 = (Listdefect.get(14));
                                                                                                                                P1 = (Listdefect.get(15));
                                                                                                                                Q1 = (Listdefect.get(16));
                                                                                                                                S1 = (Listdefect.get(17));
                                                                                                                                T1 = (Listdefect.get(18));
                                                                                                                                U1 = (Listdefect.get(19));
                                                                                                                                V1 = (Listdefect.get(20));
                                                                                                                                W1 = (Listdefect.get(21));
                                                                                                                                X1 = (Listdefect.get(22));
                                                                                                                                Y1 = (Listdefect.get(23));
                                                                                                                                Z1 = (Listdefect.get(24));
                                                                                                                                AA1 = (Listdefect.get(25));
                                                                                                                                BB1 = (Listdefect.get(26));
                                                                                                                                CC1 = (Listdefect.get(27));
                                                                                                                                DD1 = (Listdefect.get(28));

                                                                                                                            } else {
                                                                                                                                if ((Listdefect != null) && (Listdefect.size() == 30)) {
                                                                                                                                    A1 = (Listdefect.get(0));

                                                                                                                                    B1 = (Listdefect.get(1));
                                                                                                                                    C1 = (Listdefect.get(2));
                                                                                                                                    D1 = (Listdefect.get(3));
                                                                                                                                    E1 = (Listdefect.get(4));
                                                                                                                                    F1 = (Listdefect.get(5));
                                                                                                                                    G1 = (Listdefect.get(6));
                                                                                                                                    H1 = (Listdefect.get(7));
                                                                                                                                    I1 = (Listdefect.get(8));
                                                                                                                                    J1 = (Listdefect.get(9));
                                                                                                                                    K1 = (Listdefect.get(10));
                                                                                                                                    L1 = (Listdefect.get(11));
                                                                                                                                    M1 = (Listdefect.get(12));
                                                                                                                                    N1 = (Listdefect.get(13));
                                                                                                                                    O1 = (Listdefect.get(14));
                                                                                                                                    P1 = (Listdefect.get(15));
                                                                                                                                    Q1 = (Listdefect.get(16));
                                                                                                                                    S1 = (Listdefect.get(17));
                                                                                                                                    T1 = (Listdefect.get(18));
                                                                                                                                    U1 = (Listdefect.get(19));
                                                                                                                                    V1 = (Listdefect.get(20));
                                                                                                                                    W1 = (Listdefect.get(21));
                                                                                                                                    X1 = (Listdefect.get(22));
                                                                                                                                    Y1 = (Listdefect.get(23));
                                                                                                                                    Z1 = (Listdefect.get(24));
                                                                                                                                    AA1 = (Listdefect.get(25));
                                                                                                                                    BB1 = (Listdefect.get(26));
                                                                                                                                    CC1 = (Listdefect.get(27));
                                                                                                                                    DD1 = (Listdefect.get(28));
                                                                                                                                    EE1 = (Listdefect.get(29));

                                                                                                                                } else {
                                                                                                                                    if ((Listdefect != null) && (Listdefect.size() == 31)) {
                                                                                                                                        A1 = (Listdefect.get(0));

                                                                                                                                        B1 = (Listdefect.get(1));
                                                                                                                                        C1 = (Listdefect.get(2));
                                                                                                                                        D1 = (Listdefect.get(3));
                                                                                                                                        E1 = (Listdefect.get(4));
                                                                                                                                        F1 = (Listdefect.get(5));
                                                                                                                                        G1 = (Listdefect.get(6));
                                                                                                                                        H1 = (Listdefect.get(7));
                                                                                                                                        I1 = (Listdefect.get(8));
                                                                                                                                        J1 = (Listdefect.get(9));
                                                                                                                                        K1 = (Listdefect.get(10));
                                                                                                                                        L1 = (Listdefect.get(11));
                                                                                                                                        M1 = (Listdefect.get(12));
                                                                                                                                        N1 = (Listdefect.get(13));
                                                                                                                                        O1 = (Listdefect.get(14));
                                                                                                                                        P1 = (Listdefect.get(15));
                                                                                                                                        Q1 = (Listdefect.get(16));
                                                                                                                                        S1 = (Listdefect.get(17));
                                                                                                                                        T1 = (Listdefect.get(18));
                                                                                                                                        U1 = (Listdefect.get(19));
                                                                                                                                        V1 = (Listdefect.get(20));
                                                                                                                                        W1 = (Listdefect.get(21));
                                                                                                                                        X1 = (Listdefect.get(22));
                                                                                                                                        Y = (Listdefect.get(23));
                                                                                                                                        Z1 = (Listdefect.get(24));
                                                                                                                                        AA1 = (Listdefect.get(25));
                                                                                                                                        BB1 = (Listdefect.get(26));
                                                                                                                                        CC1 = (Listdefect.get(27));
                                                                                                                                        DD1 = (Listdefect.get(28));
                                                                                                                                        EE1 = (Listdefect.get(29));
                                                                                                                                        FF1 = (Listdefect.get(30));

                                                                                                                                    } else {
                                                                                                                                        if ((Listdefect != null) && (Listdefect.size() == 32)) {
                                                                                                                                            A1 = (Listdefect.get(0));

                                                                                                                                            B1 = (Listdefect.get(1));
                                                                                                                                            C1 = (Listdefect.get(2));
                                                                                                                                            D1 = (Listdefect.get(3));
                                                                                                                                            E1 = (Listdefect.get(4));
                                                                                                                                            F1 = (Listdefect.get(5));
                                                                                                                                            G1 = (Listdefect.get(6));
                                                                                                                                            H1 = (Listdefect.get(7));
                                                                                                                                            I1 = (Listdefect.get(8));
                                                                                                                                            J1 = (Listdefect.get(9));
                                                                                                                                            K1 = (Listdefect.get(10));
                                                                                                                                            L1 = (Listdefect.get(11));
                                                                                                                                            M1 = (Listdefect.get(12));
                                                                                                                                            N1 = (Listdefect.get(13));
                                                                                                                                            O1 = (Listdefect.get(14));
                                                                                                                                            P1 = (Listdefect.get(15));
                                                                                                                                            Q1 = (Listdefect.get(16));
                                                                                                                                            S1 = (Listdefect.get(17));
                                                                                                                                            T1 = (Listdefect.get(18));
                                                                                                                                            U1 = (Listdefect.get(19));
                                                                                                                                            V1 = (Listdefect.get(20));
                                                                                                                                            W1 = (Listdefect.get(21));
                                                                                                                                            X1 = (Listdefect.get(22));
                                                                                                                                            Y1 = (Listdefect.get(23));
                                                                                                                                            Z1 = (Listdefect.get(24));
                                                                                                                                            AA1 = (Listdefect.get(25));
                                                                                                                                            BB1 = (Listdefect.get(26));
                                                                                                                                            CC1 = (Listdefect.get(27));
                                                                                                                                            DD1 = (Listdefect.get(28));
                                                                                                                                            EE1 = (Listdefect.get(29));
                                                                                                                                            FF1 = (Listdefect.get(30));
                                                                                                                                            GG1 = (Listdefect.get(31));

                                                                                                                                        } else {
                                                                                                                                            if ((Listdefect != null) && (Listdefect.size() == 33)) {
                                                                                                                                                A1 = (Listdefect.get(0));

                                                                                                                                                B1 = (Listdefect.get(1));
                                                                                                                                                C1 = (Listdefect.get(2));
                                                                                                                                                D1 = (Listdefect.get(3));
                                                                                                                                                E1 = (Listdefect.get(4));
                                                                                                                                                F1 = (Listdefect.get(5));
                                                                                                                                                G1 = (Listdefect.get(6));
                                                                                                                                                H1 = (Listdefect.get(7));
                                                                                                                                                I1 = (Listdefect.get(8));
                                                                                                                                                J1 = (Listdefect.get(9));
                                                                                                                                                K1 = (Listdefect.get(10));
                                                                                                                                                L1 = (Listdefect.get(11));
                                                                                                                                                M1 = (Listdefect.get(12));
                                                                                                                                                N1 = (Listdefect.get(13));
                                                                                                                                                O1 = (Listdefect.get(14));
                                                                                                                                                P1 = (Listdefect.get(15));
                                                                                                                                                Q1 = (Listdefect.get(16));
                                                                                                                                                S1 = (Listdefect.get(17));
                                                                                                                                                T1 = (Listdefect.get(18));
                                                                                                                                                U1 = (Listdefect.get(19));
                                                                                                                                                V1 = (Listdefect.get(20));
                                                                                                                                                W1 = (Listdefect.get(21));
                                                                                                                                                X1 = (Listdefect.get(22));
                                                                                                                                                Y1 = (Listdefect.get(23));
                                                                                                                                                Z1 = (Listdefect.get(24));
                                                                                                                                                AA1 = (Listdefect.get(25));
                                                                                                                                                BB1 = (Listdefect.get(26));
                                                                                                                                                CC1 = (Listdefect.get(27));
                                                                                                                                                DD1 = (Listdefect.get(28));
                                                                                                                                                EE1 = (Listdefect.get(29));
                                                                                                                                                FF1 = (Listdefect.get(30));
                                                                                                                                                GG1 = (Listdefect.get(31));
                                                                                                                                                HH1 = (Listdefect.get(32));

                                                                                                                                            } else {
                                                                                                                                                if ((Listdefect != null) && (Listdefect.size() == 34)) {
                                                                                                                                                    A1 = (Listdefect.get(0));

                                                                                                                                                    B1 = (Listdefect.get(1));
                                                                                                                                                    C1 = (Listdefect.get(2));
                                                                                                                                                    D1 = (Listdefect.get(3));
                                                                                                                                                    E1 = (Listdefect.get(4));
                                                                                                                                                    F1 = (Listdefect.get(5));
                                                                                                                                                    G1 = (Listdefect.get(6));
                                                                                                                                                    H1 = (Listdefect.get(7));
                                                                                                                                                    I1 = (Listdefect.get(8));
                                                                                                                                                    J1 = (Listdefect.get(9));
                                                                                                                                                    K1 = (Listdefect.get(10));
                                                                                                                                                    L1 = (Listdefect.get(11));
                                                                                                                                                    M1 = (Listdefect.get(12));
                                                                                                                                                    N1 = (Listdefect.get(13));
                                                                                                                                                    O1 = (Listdefect.get(14));
                                                                                                                                                    P1 = (Listdefect.get(15));
                                                                                                                                                    Q1 = (Listdefect.get(16));
                                                                                                                                                    S1 = (Listdefect.get(17));
                                                                                                                                                    T1 = (Listdefect.get(18));
                                                                                                                                                    U1 = (Listdefect.get(19));
                                                                                                                                                    V1 = (Listdefect.get(20));
                                                                                                                                                    W1 = (Listdefect.get(21));
                                                                                                                                                    X1 = (Listdefect.get(22));
                                                                                                                                                    Y1 = (Listdefect.get(23));
                                                                                                                                                    Z1 = (Listdefect.get(24));
                                                                                                                                                    AA1 = (Listdefect.get(25));
                                                                                                                                                    BB1 = (Listdefect.get(26));
                                                                                                                                                    CC1 = (Listdefect.get(27));
                                                                                                                                                    DD1 = (Listdefect.get(28));
                                                                                                                                                    EE1 = (Listdefect.get(29));
                                                                                                                                                    FF1 = (Listdefect.get(30));
                                                                                                                                                    GG1 = (Listdefect.get(31));
                                                                                                                                                    HH1 = (Listdefect.get(32));
                                                                                                                                                    II1 = (Listdefect.get(33));

                                                                                                                                                } else {
                                                                                                                                                    if ((Listdefect != null) && (Listdefect.size() == 35)) {
                                                                                                                                                        A1 = (Listdefect.get(0));

                                                                                                                                                        B1 = (Listdefect.get(1));
                                                                                                                                                        C1 = (Listdefect.get(2));
                                                                                                                                                        D1 = (Listdefect.get(3));
                                                                                                                                                        E1 = (Listdefect.get(4));
                                                                                                                                                        F1 = (Listdefect.get(5));
                                                                                                                                                        G1 = (Listdefect.get(6));
                                                                                                                                                        H1 = (Listdefect.get(7));
                                                                                                                                                        I1 = (Listdefect.get(8));
                                                                                                                                                        J1 = (Listdefect.get(9));
                                                                                                                                                        K1 = (Listdefect.get(10));
                                                                                                                                                        L1 = (Listdefect.get(11));
                                                                                                                                                        M1 = (Listdefect.get(12));
                                                                                                                                                        N1 = (Listdefect.get(13));
                                                                                                                                                        O1 = (Listdefect.get(14));
                                                                                                                                                        P1 = (Listdefect.get(15));
                                                                                                                                                        Q1 = (Listdefect.get(16));
                                                                                                                                                        S1 = (Listdefect.get(17));
                                                                                                                                                        T1 = (Listdefect.get(18));
                                                                                                                                                        U1 = (Listdefect.get(19));
                                                                                                                                                        V1 = (Listdefect.get(20));
                                                                                                                                                        W1 = (Listdefect.get(21));
                                                                                                                                                        X1 = (Listdefect.get(22));
                                                                                                                                                        Y1 = (Listdefect.get(23));
                                                                                                                                                        Z1 = (Listdefect.get(24));
                                                                                                                                                        AA1 = (Listdefect.get(25));
                                                                                                                                                        BB1 = (Listdefect.get(26));
                                                                                                                                                        CC1 = (Listdefect.get(27));
                                                                                                                                                        DD1 = (Listdefect.get(28));
                                                                                                                                                        EE1 = (Listdefect.get(29));
                                                                                                                                                        FF1 = (Listdefect.get(30));
                                                                                                                                                        GG1 = (Listdefect.get(31));
                                                                                                                                                        HH1 = (Listdefect.get(32));
                                                                                                                                                        II1 = (Listdefect.get(33));
                                                                                                                                                        JJ1 = (Listdefect.get(34));

                                                                                                                                                    } else {
                                                                                                                                                        if ((Listdefect != null) && (Listdefect.size() == 36)) {
                                                                                                                                                            A1 = (Listdefect.get(0));
                                                                                                                                                            B1 = (Listdefect.get(1));
                                                                                                                                                            C1 = (Listdefect.get(2));
                                                                                                                                                            D1 = (Listdefect.get(3));
                                                                                                                                                            E1 = (Listdefect.get(4));
                                                                                                                                                            F1 = (Listdefect.get(5));
                                                                                                                                                            G1 = (Listdefect.get(6));
                                                                                                                                                            H1 = (Listdefect.get(7));
                                                                                                                                                            I1 = (Listdefect.get(8));
                                                                                                                                                            J1 = (Listdefect.get(9));
                                                                                                                                                            K1 = (Listdefect.get(10));
                                                                                                                                                            L1 = (Listdefect.get(11));
                                                                                                                                                            M1 = (Listdefect.get(12));
                                                                                                                                                            N1 = (Listdefect.get(13));
                                                                                                                                                            O1 = (Listdefect.get(14));
                                                                                                                                                            P1 = (Listdefect.get(15));
                                                                                                                                                            Q1 = (Listdefect.get(16));
                                                                                                                                                            S1 = (Listdefect.get(17));
                                                                                                                                                            T1 = (Listdefect.get(18));
                                                                                                                                                            U1 = (Listdefect.get(19));
                                                                                                                                                            V1 = (Listdefect.get(20));
                                                                                                                                                            W1 = (Listdefect.get(21));
                                                                                                                                                            X1 = (Listdefect.get(22));
                                                                                                                                                            Y1 = (Listdefect.get(23));
                                                                                                                                                            Z1 = (Listdefect.get(24));
                                                                                                                                                            AA1 = (Listdefect.get(25));
                                                                                                                                                            BB1 = (Listdefect.get(26));
                                                                                                                                                            CC1 = (Listdefect.get(27));
                                                                                                                                                            DD1 = (Listdefect.get(28));
                                                                                                                                                            EE1 = (Listdefect.get(29));
                                                                                                                                                            FF1 = (Listdefect.get(30));
                                                                                                                                                            GG1 = (Listdefect.get(31));
                                                                                                                                                            HH1 = (Listdefect.get(32));
                                                                                                                                                            II1 = (Listdefect.get(33));
                                                                                                                                                            JJ1 = (Listdefect.get(34));
                                                                                                                                                            KK1 = (Listdefect.get(35));

                                                                                                                                                        } else {
                                                                                                                                                            if ((Listdefect != null) && (Listdefect.size() == 37)) {
                                                                                                                                                                A1 = (Listdefect.get(0));
                                                                                                                                                                B1 = (Listdefect.get(1));
                                                                                                                                                                C1 = (Listdefect.get(2));
                                                                                                                                                                D1 = (Listdefect.get(3));
                                                                                                                                                                E1 = (Listdefect.get(4));
                                                                                                                                                                F1 = (Listdefect.get(5));
                                                                                                                                                                G1 = (Listdefect.get(6));
                                                                                                                                                                H1 = (Listdefect.get(7));
                                                                                                                                                                I1 = (Listdefect.get(8));
                                                                                                                                                                J1 = (Listdefect.get(9));
                                                                                                                                                                K1 = (Listdefect.get(10));
                                                                                                                                                                L1 = (Listdefect.get(11));
                                                                                                                                                                M1 = (Listdefect.get(12));
                                                                                                                                                                N1 = (Listdefect.get(13));
                                                                                                                                                                O1 = (Listdefect.get(14));
                                                                                                                                                                P1 = (Listdefect.get(15));
                                                                                                                                                                Q1 = (Listdefect.get(16));
                                                                                                                                                                S1 = (Listdefect.get(17));
                                                                                                                                                                T1 = (Listdefect.get(18));
                                                                                                                                                                U1 = (Listdefect.get(19));
                                                                                                                                                                V1 = (Listdefect.get(20));
                                                                                                                                                                W1 = (Listdefect.get(21));
                                                                                                                                                                X1 = (Listdefect.get(22));
                                                                                                                                                                Y1 = (Listdefect.get(23));
                                                                                                                                                                Z1 = (Listdefect.get(24));
                                                                                                                                                                AA1 = (Listdefect.get(25));
                                                                                                                                                                BB1 = (Listdefect.get(26));
                                                                                                                                                                CC1 = (Listdefect.get(27));
                                                                                                                                                                DD1 = (Listdefect.get(28));
                                                                                                                                                                EE1 = (Listdefect.get(29));
                                                                                                                                                                FF1 = (Listdefect.get(30));
                                                                                                                                                                GG1 = (Listdefect.get(31));
                                                                                                                                                                HH1 = (Listdefect.get(32));
                                                                                                                                                                II1 = (Listdefect.get(33));
                                                                                                                                                                JJ1 = (Listdefect.get(34));
                                                                                                                                                                KK1 = (Listdefect.get(35));
                                                                                                                                                                LL1 = (Listdefect.get(36));

                                                                                                                                                            } else {
                                                                                                                                                                if ((Listdefect != null) && (Listdefect.size() == 38)) {
                                                                                                                                                                    A1 = (Listdefect.get(0));
                                                                                                                                                                    B1 = (Listdefect.get(1));
                                                                                                                                                                    C1 = (Listdefect.get(2));
                                                                                                                                                                    D1 = (Listdefect.get(3));
                                                                                                                                                                    E1 = (Listdefect.get(4));
                                                                                                                                                                    F1 = (Listdefect.get(5));
                                                                                                                                                                    G1 = (Listdefect.get(6));
                                                                                                                                                                    H1 = (Listdefect.get(7));
                                                                                                                                                                    I1 = (Listdefect.get(8));
                                                                                                                                                                    J1 = (Listdefect.get(9));
                                                                                                                                                                    K1 = (Listdefect.get(10));
                                                                                                                                                                    L1 = (Listdefect.get(11));
                                                                                                                                                                    M1 = (Listdefect.get(12));
                                                                                                                                                                    N1 = (Listdefect.get(13));
                                                                                                                                                                    O1 = (Listdefect.get(14));
                                                                                                                                                                    P1 = (Listdefect.get(15));
                                                                                                                                                                    Q1 = (Listdefect.get(16));
                                                                                                                                                                    S1 = (Listdefect.get(17));
                                                                                                                                                                    T1 = (Listdefect.get(18));
                                                                                                                                                                    U1 = (Listdefect.get(19));
                                                                                                                                                                    V1 = (Listdefect.get(20));
                                                                                                                                                                    W1 = (Listdefect.get(21));
                                                                                                                                                                    X1 = (Listdefect.get(22));
                                                                                                                                                                    Y1 = (Listdefect.get(23));
                                                                                                                                                                    Z1 = (Listdefect.get(24));
                                                                                                                                                                    AA1 = (Listdefect.get(25));
                                                                                                                                                                    BB1 = (Listdefect.get(26));
                                                                                                                                                                    CC1 = (Listdefect.get(27));
                                                                                                                                                                    DD1 = (Listdefect.get(28));
                                                                                                                                                                    EE1 = (Listdefect.get(29));
                                                                                                                                                                    FF1 = (Listdefect.get(30));
                                                                                                                                                                    GG1 = (Listdefect.get(31));
                                                                                                                                                                    HH1 = (Listdefect.get(32));
                                                                                                                                                                    II1 = (Listdefect.get(33));
                                                                                                                                                                    JJ1 = (Listdefect.get(34));
                                                                                                                                                                    KK1 = (Listdefect.get(35));
                                                                                                                                                                    LL1 = (Listdefect.get(36));
                                                                                                                                                                    MM1 = (Listdefect.get(37));

                                                                                                                                                                } else {
                                                                                                                                                                    if ((Listdefect != null) && (Listdefect.size() == 39)) {
                                                                                                                                                                        A1 = (Listdefect.get(0));

                                                                                                                                                                        B1 = (Listdefect.get(1));
                                                                                                                                                                        C1 = (Listdefect.get(2));
                                                                                                                                                                        D1 = (Listdefect.get(3));
                                                                                                                                                                        E1 = (Listdefect.get(4));
                                                                                                                                                                        F1 = (Listdefect.get(5));
                                                                                                                                                                        G1 = (Listdefect.get(6));
                                                                                                                                                                        H1 = (Listdefect.get(7));
                                                                                                                                                                        I1 = (Listdefect.get(8));
                                                                                                                                                                        J1 = (Listdefect.get(9));
                                                                                                                                                                        K1 = (Listdefect.get(10));
                                                                                                                                                                        L1 = (Listdefect.get(11));
                                                                                                                                                                        M1 = (Listdefect.get(12));
                                                                                                                                                                        N1 = (Listdefect.get(13));
                                                                                                                                                                        O1 = (Listdefect.get(14));
                                                                                                                                                                        P1 = (Listdefect.get(15));
                                                                                                                                                                        Q1 = (Listdefect.get(16));
                                                                                                                                                                        S1 = (Listdefect.get(17));
                                                                                                                                                                        T1 = (Listdefect.get(18));
                                                                                                                                                                        U1 = (Listdefect.get(19));
                                                                                                                                                                        V1 = (Listdefect.get(20));
                                                                                                                                                                        W1 = (Listdefect.get(21));
                                                                                                                                                                        X1 = (Listdefect.get(22));
                                                                                                                                                                        Y1 = (Listdefect.get(23));
                                                                                                                                                                        Z1 = (Listdefect.get(24));
                                                                                                                                                                        AA1 = (Listdefect.get(25));
                                                                                                                                                                        BB1 = (Listdefect.get(26));
                                                                                                                                                                        CC1 = (Listdefect.get(27));
                                                                                                                                                                        DD1 = (Listdefect.get(28));
                                                                                                                                                                        EE1 = (Listdefect.get(29));
                                                                                                                                                                        FF1 = (Listdefect.get(30));
                                                                                                                                                                        GG1 = (Listdefect.get(31));
                                                                                                                                                                        HH1 = (Listdefect.get(32));
                                                                                                                                                                        II1 = (Listdefect.get(33));
                                                                                                                                                                        JJ1 = (Listdefect.get(34));
                                                                                                                                                                        KK1 = (Listdefect.get(35));
                                                                                                                                                                        LL1 = (Listdefect.get(36));
                                                                                                                                                                        MM1 = (Listdefect.get(37));
                                                                                                                                                                        NN1 = (Listdefect.get(38));

                                                                                                                                                                    } else {
                                                                                                                                                                        if ((Listdefect != null) && (Listdefect.size() == 40)) {
                                                                                                                                                                            A1 = (Listdefect.get(0));

                                                                                                                                                                            B1 = (Listdefect.get(1));
                                                                                                                                                                            C1 = (Listdefect.get(2));
                                                                                                                                                                            D1 = (Listdefect.get(3));
                                                                                                                                                                            E1 = (Listdefect.get(4));
                                                                                                                                                                            F1 = (Listdefect.get(5));
                                                                                                                                                                            G1 = (Listdefect.get(6));
                                                                                                                                                                            H1 = (Listdefect.get(7));
                                                                                                                                                                            I1 = (Listdefect.get(8));
                                                                                                                                                                            J1 = (Listdefect.get(9));
                                                                                                                                                                            K1 = (Listdefect.get(10));
                                                                                                                                                                            L1 = (Listdefect.get(11));
                                                                                                                                                                            M1 = (Listdefect.get(12));
                                                                                                                                                                            N1 = (Listdefect.get(13));
                                                                                                                                                                            O1 = (Listdefect.get(14));
                                                                                                                                                                            P1 = (Listdefect.get(15));
                                                                                                                                                                            Q1 = (Listdefect.get(16));
                                                                                                                                                                            S1 = (Listdefect.get(17));
                                                                                                                                                                            T1 = (Listdefect.get(18));
                                                                                                                                                                            U1 = (Listdefect.get(19));
                                                                                                                                                                            V1 = (Listdefect.get(20));
                                                                                                                                                                            W1 = (Listdefect.get(21));
                                                                                                                                                                            X1 = (Listdefect.get(22));
                                                                                                                                                                            Y1 = (Listdefect.get(23));
                                                                                                                                                                            Z1 = (Listdefect.get(24));
                                                                                                                                                                            AA1 = (Listdefect.get(25));
                                                                                                                                                                            BB1 = (Listdefect.get(26));
                                                                                                                                                                            CC1 = (Listdefect.get(27));
                                                                                                                                                                            DD1 = (Listdefect.get(28));
                                                                                                                                                                            EE1 = (Listdefect.get(29));
                                                                                                                                                                            FF1 = (Listdefect.get(30));
                                                                                                                                                                            GG1 = (Listdefect.get(31));
                                                                                                                                                                            HH1 = (Listdefect.get(32));
                                                                                                                                                                            II1 = (Listdefect.get(33));
                                                                                                                                                                            JJ1 = (Listdefect.get(34));
                                                                                                                                                                            KK1 = (Listdefect.get(35));
                                                                                                                                                                            LL1 = (Listdefect.get(36));
                                                                                                                                                                            MM1 = (Listdefect.get(37));
                                                                                                                                                                            NN1 = (Listdefect.get(38));
                                                                                                                                                                            OO1 = (Listdefect.get(39));

                                                                                                                                                                        } else {
                                                                                                                                                                            if ((Listdefect != null) && (Listdefect.size() == 41)) {
                                                                                                                                                                                A1 = (Listdefect.get(0));

                                                                                                                                                                                B1 = (Listdefect.get(1));
                                                                                                                                                                                C1 = (Listdefect.get(2));
                                                                                                                                                                                D1 = (Listdefect.get(3));
                                                                                                                                                                                E1 = (Listdefect.get(4));
                                                                                                                                                                                F1 = (Listdefect.get(5));
                                                                                                                                                                                G1 = (Listdefect.get(6));
                                                                                                                                                                                H1 = (Listdefect.get(7));
                                                                                                                                                                                I1 = (Listdefect.get(8));
                                                                                                                                                                                J1 = (Listdefect.get(9));
                                                                                                                                                                                K1 = (Listdefect.get(10));
                                                                                                                                                                                L1 = (Listdefect.get(11));
                                                                                                                                                                                M1 = (Listdefect.get(12));
                                                                                                                                                                                N1 = (Listdefect.get(13));
                                                                                                                                                                                O1 = (Listdefect.get(14));
                                                                                                                                                                                P1 = (Listdefect.get(15));
                                                                                                                                                                                Q1 = (Listdefect.get(16));
                                                                                                                                                                                S1 = (Listdefect.get(17));
                                                                                                                                                                                T1 = (Listdefect.get(18));
                                                                                                                                                                                U1 = (Listdefect.get(19));
                                                                                                                                                                                V1 = (Listdefect.get(20));
                                                                                                                                                                                W1 = (Listdefect.get(21));
                                                                                                                                                                                X1 = (Listdefect.get(22));
                                                                                                                                                                                Y1 = (Listdefect.get(23));
                                                                                                                                                                                Z1 = (Listdefect.get(24));
                                                                                                                                                                                AA1 = (Listdefect.get(25));
                                                                                                                                                                                BB1 = (Listdefect.get(26));
                                                                                                                                                                                CC1 = (Listdefect.get(27));
                                                                                                                                                                                DD1 = (Listdefect.get(28));
                                                                                                                                                                                EE1 = (Listdefect.get(29));
                                                                                                                                                                                FF1 = (Listdefect.get(30));
                                                                                                                                                                                GG1 = (Listdefect.get(31));
                                                                                                                                                                                HH1 = (Listdefect.get(32));
                                                                                                                                                                                II1 = (Listdefect.get(33));
                                                                                                                                                                                JJ1 = (Listdefect.get(34));
                                                                                                                                                                                KK1 = (Listdefect.get(35));
                                                                                                                                                                                LL1 = (Listdefect.get(36));
                                                                                                                                                                                MM1 = (Listdefect.get(37));
                                                                                                                                                                                NN1 = (Listdefect.get(38));
                                                                                                                                                                                OO1 = (Listdefect.get(39));
                                                                                                                                                                                PP1 = (Listdefect.get(40));

                                                                                                                                                                            } else {
                                                                                                                                                                                if ((Listdefect != null) && (Listdefect.size() == 42)) {
                                                                                                                                                                                    A1 = (Listdefect.get(0));

                                                                                                                                                                                    B1 = (Listdefect.get(1));
                                                                                                                                                                                    C1 = (Listdefect.get(2));
                                                                                                                                                                                    D1 = (Listdefect.get(3));
                                                                                                                                                                                    E1 = (Listdefect.get(4));
                                                                                                                                                                                    F1 = (Listdefect.get(5));
                                                                                                                                                                                    G1 = (Listdefect.get(6));
                                                                                                                                                                                    H1 = (Listdefect.get(7));
                                                                                                                                                                                    I1 = (Listdefect.get(8));
                                                                                                                                                                                    J1 = (Listdefect.get(9));
                                                                                                                                                                                    K1 = (Listdefect.get(10));
                                                                                                                                                                                    L1 = (Listdefect.get(11));
                                                                                                                                                                                    M1 = (Listdefect.get(12));
                                                                                                                                                                                    N1 = (Listdefect.get(13));
                                                                                                                                                                                    O1 = (Listdefect.get(14));
                                                                                                                                                                                    P1 = (Listdefect.get(15));
                                                                                                                                                                                    Q1 = (Listdefect.get(16));
                                                                                                                                                                                    S1 = (Listdefect.get(17));
                                                                                                                                                                                    T1 = (Listdefect.get(18));
                                                                                                                                                                                    U1 = (Listdefect.get(19));
                                                                                                                                                                                    V1 = (Listdefect.get(20));
                                                                                                                                                                                    W1 = (Listdefect.get(21));
                                                                                                                                                                                    X1 = (Listdefect.get(22));
                                                                                                                                                                                    Y1 = (Listdefect.get(23));
                                                                                                                                                                                    Z1 = (Listdefect.get(24));
                                                                                                                                                                                    AA1 = (Listdefect.get(25));
                                                                                                                                                                                    BB1 = (Listdefect.get(26));
                                                                                                                                                                                    CC1 = (Listdefect.get(27));
                                                                                                                                                                                    DD1 = (Listdefect.get(28));
                                                                                                                                                                                    EE = (Listdefect.get(29));
                                                                                                                                                                                    FF1 = (Listdefect.get(30));
                                                                                                                                                                                    GG1 = (Listdefect.get(31));
                                                                                                                                                                                    HH1 = (Listdefect.get(32));
                                                                                                                                                                                    II1 = (Listdefect.get(33));
                                                                                                                                                                                    JJ1 = (Listdefect.get(34));
                                                                                                                                                                                    KK1 = (Listdefect.get(35));
                                                                                                                                                                                    LL1 = (Listdefect.get(36));
                                                                                                                                                                                    MM1 = (Listdefect.get(37));
                                                                                                                                                                                    NN1 = (Listdefect.get(38));
                                                                                                                                                                                    OO1 = (Listdefect.get(39));
                                                                                                                                                                                    PP1 = (Listdefect.get(40));
                                                                                                                                                                                    QQ1 = (Listdefect.get(41));

                                                                                                                                                                                } else {
                                                                                                                                                                                    if ((Listdefect != null) && (Listdefect.size() == 43)) {
                                                                                                                                                                                        A1 = (Listdefect.get(0));

                                                                                                                                                                                        B1 = (Listdefect.get(1));
                                                                                                                                                                                        C1 = (Listdefect.get(2));
                                                                                                                                                                                        D1 = (Listdefect.get(3));
                                                                                                                                                                                        E1 = (Listdefect.get(4));
                                                                                                                                                                                        F1 = (Listdefect.get(5));
                                                                                                                                                                                        G1 = (Listdefect.get(6));
                                                                                                                                                                                        H1 = (Listdefect.get(7));
                                                                                                                                                                                        I1 = (Listdefect.get(8));
                                                                                                                                                                                        J1 = (Listdefect.get(9));
                                                                                                                                                                                        K1 = (Listdefect.get(10));
                                                                                                                                                                                        L1 = (Listdefect.get(11));
                                                                                                                                                                                        M1 = (Listdefect.get(12));
                                                                                                                                                                                        N1 = (Listdefect.get(13));
                                                                                                                                                                                        O1 = (Listdefect.get(14));
                                                                                                                                                                                        P1 = (Listdefect.get(15));
                                                                                                                                                                                        Q1 = (Listdefect.get(16));
                                                                                                                                                                                        S1 = (Listdefect.get(17));
                                                                                                                                                                                        T1 = (Listdefect.get(18));
                                                                                                                                                                                        U1 = (Listdefect.get(19));
                                                                                                                                                                                        V1 = (Listdefect.get(20));
                                                                                                                                                                                        W1 = (Listdefect.get(21));
                                                                                                                                                                                        X1 = (Listdefect.get(22));
                                                                                                                                                                                        Y1 = (Listdefect.get(23));
                                                                                                                                                                                        Z1 = (Listdefect.get(24));
                                                                                                                                                                                        AA1 = (Listdefect.get(25));
                                                                                                                                                                                        BB1 = (Listdefect.get(26));
                                                                                                                                                                                        CC1 = (Listdefect.get(27));
                                                                                                                                                                                        DD1 = (Listdefect.get(28));
                                                                                                                                                                                        EE1 = (Listdefect.get(29));
                                                                                                                                                                                        FF1 = (Listdefect.get(30));
                                                                                                                                                                                        GG1 = (Listdefect.get(31));
                                                                                                                                                                                        HH1 = (Listdefect.get(32));
                                                                                                                                                                                        II1 = (Listdefect.get(33));
                                                                                                                                                                                        JJ1 = (Listdefect.get(34));
                                                                                                                                                                                        KK1 = (Listdefect.get(35));
                                                                                                                                                                                        LL1 = (Listdefect.get(36));
                                                                                                                                                                                        MM1 = (Listdefect.get(37));
                                                                                                                                                                                        NN1 = (Listdefect.get(38));
                                                                                                                                                                                        OO1 = (Listdefect.get(39));
                                                                                                                                                                                        PP1 = (Listdefect.get(40));
                                                                                                                                                                                        QQ1 = (Listdefect.get(41));
                                                                                                                                                                                        SS1 = (Listdefect.get(42));

                                                                                                                                                                                    } else {
                                                                                                                                                                                        if ((Listdefect != null) && (Listdefect.size() == 44)) {
                                                                                                                                                                                            A1 = (Listdefect.get(0));

                                                                                                                                                                                            B1 = (Listdefect.get(1));
                                                                                                                                                                                            C1 = (Listdefect.get(2));
                                                                                                                                                                                            D1 = (Listdefect.get(3));
                                                                                                                                                                                            E1 = (Listdefect.get(4));
                                                                                                                                                                                            F1 = (Listdefect.get(5));
                                                                                                                                                                                            G1 = (Listdefect.get(6));
                                                                                                                                                                                            H1 = (Listdefect.get(7));
                                                                                                                                                                                            I1 = (Listdefect.get(8));
                                                                                                                                                                                            J1 = (Listdefect.get(9));
                                                                                                                                                                                            K1 = (Listdefect.get(10));
                                                                                                                                                                                            L1 = (Listdefect.get(11));
                                                                                                                                                                                            M1 = (Listdefect.get(12));
                                                                                                                                                                                            N1 = (Listdefect.get(13));
                                                                                                                                                                                            O1 = (Listdefect.get(14));
                                                                                                                                                                                            P1 = (Listdefect.get(15));
                                                                                                                                                                                            Q1 = (Listdefect.get(16));
                                                                                                                                                                                            S1 = (Listdefect.get(17));
                                                                                                                                                                                            T1 = (Listdefect.get(18));
                                                                                                                                                                                            U1 = (Listdefect.get(19));
                                                                                                                                                                                            V1 = (Listdefect.get(20));
                                                                                                                                                                                            W1 = (Listdefect.get(21));
                                                                                                                                                                                            X1 = (Listdefect.get(22));
                                                                                                                                                                                            Y1 = (Listdefect.get(23));
                                                                                                                                                                                            Z1 = (Listdefect.get(24));
                                                                                                                                                                                            AA1 = (Listdefect.get(25));
                                                                                                                                                                                            BB1 = (Listdefect.get(26));
                                                                                                                                                                                            CC1 = (Listdefect.get(27));
                                                                                                                                                                                            DD1 = (Listdefect.get(28));
                                                                                                                                                                                            EE1 = (Listdefect.get(29));
                                                                                                                                                                                            FF1 = (Listdefect.get(30));
                                                                                                                                                                                            GG1 = (Listdefect.get(31));
                                                                                                                                                                                            HH1 = (Listdefect.get(32));
                                                                                                                                                                                            II1 = (Listdefect.get(33));
                                                                                                                                                                                            JJ1 = (Listdefect.get(34));
                                                                                                                                                                                            KK1 = (Listdefect.get(35));
                                                                                                                                                                                            LL1 = (Listdefect.get(36));
                                                                                                                                                                                            MM1 = (Listdefect.get(37));
                                                                                                                                                                                            NN1 = (Listdefect.get(38));
                                                                                                                                                                                            OO1 = (Listdefect.get(39));
                                                                                                                                                                                            PP1 = (Listdefect.get(40));
                                                                                                                                                                                            QQ1 = (Listdefect.get(41));
                                                                                                                                                                                            SS1 = (Listdefect.get(42));
                                                                                                                                                                                            TT1 = (Listdefect.get(43));

                                                                                                                                                                                        } else {
                                                                                                                                                                                            if ((Listdefect != null) && (Listdefect.size() == 45)) {
                                                                                                                                                                                                A1 = (Listdefect.get(0));

                                                                                                                                                                                                B1 = (Listdefect.get(1));
                                                                                                                                                                                                C1 = (Listdefect.get(2));
                                                                                                                                                                                                D1 = (Listdefect.get(3));
                                                                                                                                                                                                E1 = (Listdefect.get(4));
                                                                                                                                                                                                F1 = (Listdefect.get(5));
                                                                                                                                                                                                G1 = (Listdefect.get(6));
                                                                                                                                                                                                H1 = (Listdefect.get(7));
                                                                                                                                                                                                I1 = (Listdefect.get(8));
                                                                                                                                                                                                J1 = (Listdefect.get(9));
                                                                                                                                                                                                K1 = (Listdefect.get(10));
                                                                                                                                                                                                L1 = (Listdefect.get(11));
                                                                                                                                                                                                M1 = (Listdefect.get(12));
                                                                                                                                                                                                N1 = (Listdefect.get(13));
                                                                                                                                                                                                O1 = (Listdefect.get(14));
                                                                                                                                                                                                P1 = (Listdefect.get(15));
                                                                                                                                                                                                Q1 = (Listdefect.get(16));
                                                                                                                                                                                                S1 = (Listdefect.get(17));
                                                                                                                                                                                                T1 = (Listdefect.get(18));
                                                                                                                                                                                                U1 = (Listdefect.get(19));
                                                                                                                                                                                                V1 = (Listdefect.get(20));
                                                                                                                                                                                                W1 = (Listdefect.get(21));
                                                                                                                                                                                                X1 = (Listdefect.get(22));
                                                                                                                                                                                                Y1 = (Listdefect.get(23));
                                                                                                                                                                                                Z1 = (Listdefect.get(24));
                                                                                                                                                                                                AA1 = (Listdefect.get(25));
                                                                                                                                                                                                BB1 = (Listdefect.get(26));
                                                                                                                                                                                                CC1 = (Listdefect.get(27));
                                                                                                                                                                                                DD1 = (Listdefect.get(28));
                                                                                                                                                                                                EE1 = (Listdefect.get(29));
                                                                                                                                                                                                FF1 = (Listdefect.get(30));
                                                                                                                                                                                                GG1 = (Listdefect.get(31));
                                                                                                                                                                                                HH1 = (Listdefect.get(32));
                                                                                                                                                                                                II1 = (Listdefect.get(33));
                                                                                                                                                                                                JJ1 = (Listdefect.get(34));
                                                                                                                                                                                                KK1 = (Listdefect.get(35));
                                                                                                                                                                                                LL1 = (Listdefect.get(36));
                                                                                                                                                                                                MM1 = (Listdefect.get(37));
                                                                                                                                                                                                NN1 = (Listdefect.get(38));
                                                                                                                                                                                                OO1 = (Listdefect.get(39));
                                                                                                                                                                                                PP1 = (Listdefect.get(40));
                                                                                                                                                                                                QQ1 = (Listdefect.get(41));
                                                                                                                                                                                                SS1 = (Listdefect.get(42));
                                                                                                                                                                                                TT1 = (Listdefect.get(43));
                                                                                                                                                                                                UU1 = (Listdefect.get(44));

                                                                                                                                                                                            } else {
                                                                                                                                                                                                if ((Listdefect != null) && (Listdefect.size() == 46)) {
                                                                                                                                                                                                    A1 = (Listdefect.get(0));
                                                                                                                                                                                                    B1 = (Listdefect.get(1));
                                                                                                                                                                                                    C1 = (Listdefect.get(2));
                                                                                                                                                                                                    D1 = (Listdefect.get(3));
                                                                                                                                                                                                    E1 = (Listdefect.get(4));
                                                                                                                                                                                                    F1 = (Listdefect.get(5));
                                                                                                                                                                                                    G1 = (Listdefect.get(6));
                                                                                                                                                                                                    H1 = (Listdefect.get(7));
                                                                                                                                                                                                    I1 = (Listdefect.get(8));
                                                                                                                                                                                                    J1 = (Listdefect.get(9));
                                                                                                                                                                                                    K1 = (Listdefect.get(10));
                                                                                                                                                                                                    L1 = (Listdefect.get(11));
                                                                                                                                                                                                    M1 = (Listdefect.get(12));
                                                                                                                                                                                                    N1 = (Listdefect.get(13));
                                                                                                                                                                                                    O1 = (Listdefect.get(14));
                                                                                                                                                                                                    P1 = (Listdefect.get(15));
                                                                                                                                                                                                    Q1 = (Listdefect.get(16));
                                                                                                                                                                                                    S1 = (Listdefect.get(17));
                                                                                                                                                                                                    T1 = (Listdefect.get(18));
                                                                                                                                                                                                    U1 = (Listdefect.get(19));
                                                                                                                                                                                                    V1 = (Listdefect.get(20));
                                                                                                                                                                                                    W1 = (Listdefect.get(21));
                                                                                                                                                                                                    X1 = (Listdefect.get(22));
                                                                                                                                                                                                    Y1 = (Listdefect.get(23));
                                                                                                                                                                                                    Z1 = (Listdefect.get(24));
                                                                                                                                                                                                    AA1 = (Listdefect.get(25));
                                                                                                                                                                                                    BB1 = (Listdefect.get(26));
                                                                                                                                                                                                    CC1 = (Listdefect.get(27));
                                                                                                                                                                                                    DD1 = (Listdefect.get(28));
                                                                                                                                                                                                    EE1 = (Listdefect.get(29));
                                                                                                                                                                                                    FF1 = (Listdefect.get(30));
                                                                                                                                                                                                    GG1 = (Listdefect.get(31));
                                                                                                                                                                                                    HH1 = (Listdefect.get(32));
                                                                                                                                                                                                    II1 = (Listdefect.get(33));
                                                                                                                                                                                                    JJ1 = (Listdefect.get(34));
                                                                                                                                                                                                    KK1 = (Listdefect.get(35));
                                                                                                                                                                                                    LL1 = (Listdefect.get(36));
                                                                                                                                                                                                    MM1 = (Listdefect.get(37));
                                                                                                                                                                                                    NN1 = (Listdefect.get(38));
                                                                                                                                                                                                    OO1 = (Listdefect.get(39));
                                                                                                                                                                                                    PP1 = (Listdefect.get(40));
                                                                                                                                                                                                    QQ1 = (Listdefect.get(41));
                                                                                                                                                                                                    SS1 = (Listdefect.get(42));
                                                                                                                                                                                                    TT1 = (Listdefect.get(43));
                                                                                                                                                                                                    UU1 = (Listdefect.get(44));
                                                                                                                                                                                                    VV1 = (Listdefect.get(45));

                                                                                                                                                                                                } else {
                                                                                                                                                                                                    if ((Listdefect != null) && (Listdefect.size() == 47)) {
                                                                                                                                                                                                        A1 = (Listdefect.get(0));
                                                                                                                                                                                                        B1 = (Listdefect.get(1));
                                                                                                                                                                                                        C1 = (Listdefect.get(2));
                                                                                                                                                                                                        D1 = (Listdefect.get(3));
                                                                                                                                                                                                        E1 = (Listdefect.get(4));
                                                                                                                                                                                                        F1 = (Listdefect.get(5));
                                                                                                                                                                                                        G1 = (Listdefect.get(6));
                                                                                                                                                                                                        H1 = (Listdefect.get(7));
                                                                                                                                                                                                        I1 = (Listdefect.get(8));
                                                                                                                                                                                                        J1 = (Listdefect.get(9));
                                                                                                                                                                                                        K1 = (Listdefect.get(10));
                                                                                                                                                                                                        L1 = (Listdefect.get(11));
                                                                                                                                                                                                        M1 = (Listdefect.get(12));
                                                                                                                                                                                                        N1 = (Listdefect.get(13));
                                                                                                                                                                                                        O1 = (Listdefect.get(14));
                                                                                                                                                                                                        P1 = (Listdefect.get(15));
                                                                                                                                                                                                        Q1 = (Listdefect.get(16));
                                                                                                                                                                                                        S1 = (Listdefect.get(17));
                                                                                                                                                                                                        T1 = (Listdefect.get(18));
                                                                                                                                                                                                        U1 = (Listdefect.get(19));
                                                                                                                                                                                                        V1 = (Listdefect.get(20));
                                                                                                                                                                                                        W1 = (Listdefect.get(21));
                                                                                                                                                                                                        X1 = (Listdefect.get(22));
                                                                                                                                                                                                        Y1 = (Listdefect.get(23));
                                                                                                                                                                                                        Z1 = (Listdefect.get(24));
                                                                                                                                                                                                        AA1 = (Listdefect.get(25));
                                                                                                                                                                                                        BB1 = (Listdefect.get(26));
                                                                                                                                                                                                        CC1 = (Listdefect.get(27));
                                                                                                                                                                                                        DD1 = (Listdefect.get(28));
                                                                                                                                                                                                        EE1 = (Listdefect.get(29));
                                                                                                                                                                                                        FF1 = (Listdefect.get(30));
                                                                                                                                                                                                        GG1 = (Listdefect.get(31));
                                                                                                                                                                                                        HH1 = (Listdefect.get(32));
                                                                                                                                                                                                        II1 = (Listdefect.get(33));
                                                                                                                                                                                                        JJ1 = (Listdefect.get(34));
                                                                                                                                                                                                        KK1 = (Listdefect.get(35));
                                                                                                                                                                                                        LL1 = (Listdefect.get(36));
                                                                                                                                                                                                        MM1 = (Listdefect.get(37));
                                                                                                                                                                                                        NN1 = (Listdefect.get(38));
                                                                                                                                                                                                        OO1 = (Listdefect.get(39));
                                                                                                                                                                                                        PP1 = (Listdefect.get(40));
                                                                                                                                                                                                        QQ1 = (Listdefect.get(41));
                                                                                                                                                                                                        SS1 = (Listdefect.get(42));
                                                                                                                                                                                                        TT1 = (Listdefect.get(43));
                                                                                                                                                                                                        UU1 = (Listdefect.get(44));
                                                                                                                                                                                                        VV1 = (Listdefect.get(45));
                                                                                                                                                                                                        WW1 = (Listdefect.get(46));

                                                                                                                                                                                                    } else {
                                                                                                                                                                                                        if ((Listdefect != null) && (Listdefect.size() == 48)) {
                                                                                                                                                                                                            A1 = (Listdefect.get(0));

                                                                                                                                                                                                            B1 = (Listdefect.get(1));
                                                                                                                                                                                                            C1 = (Listdefect.get(2));
                                                                                                                                                                                                            D1 = (Listdefect.get(3));
                                                                                                                                                                                                            E1 = (Listdefect.get(4));
                                                                                                                                                                                                            F1 = (Listdefect.get(5));
                                                                                                                                                                                                            G1 = (Listdefect.get(6));
                                                                                                                                                                                                            H1 = (Listdefect.get(7));
                                                                                                                                                                                                            I1 = (Listdefect.get(8));
                                                                                                                                                                                                            J1 = (Listdefect.get(9));
                                                                                                                                                                                                            K1 = (Listdefect.get(10));
                                                                                                                                                                                                            L1 = (Listdefect.get(11));
                                                                                                                                                                                                            M1 = (Listdefect.get(12));
                                                                                                                                                                                                            N1 = (Listdefect.get(13));
                                                                                                                                                                                                            O1 = (Listdefect.get(14));
                                                                                                                                                                                                            P1 = (Listdefect.get(15));
                                                                                                                                                                                                            Q1 = (Listdefect.get(16));
                                                                                                                                                                                                            S1 = (Listdefect.get(17));
                                                                                                                                                                                                            T1 = (Listdefect.get(18));
                                                                                                                                                                                                            U1 = (Listdefect.get(19));
                                                                                                                                                                                                            V1 = (Listdefect.get(20));
                                                                                                                                                                                                            W1 = (Listdefect.get(21));
                                                                                                                                                                                                            X1 = (Listdefect.get(22));
                                                                                                                                                                                                            Y1 = (Listdefect.get(23));
                                                                                                                                                                                                            Z1 = (Listdefect.get(24));
                                                                                                                                                                                                            AA1 = (Listdefect.get(25));
                                                                                                                                                                                                            BB1 = (Listdefect.get(26));
                                                                                                                                                                                                            CC1 = (Listdefect.get(27));
                                                                                                                                                                                                            DD1 = (Listdefect.get(28));
                                                                                                                                                                                                            EE1 = (Listdefect.get(29));
                                                                                                                                                                                                            FF1 = (Listdefect.get(30));
                                                                                                                                                                                                            GG1 = (Listdefect.get(31));
                                                                                                                                                                                                            HH1 = (Listdefect.get(32));
                                                                                                                                                                                                            II1 = (Listdefect.get(33));
                                                                                                                                                                                                            JJ1 = (Listdefect.get(34));
                                                                                                                                                                                                            KK1 = (Listdefect.get(35));
                                                                                                                                                                                                            LL1 = (Listdefect.get(36));
                                                                                                                                                                                                            MM1 = (Listdefect.get(37));
                                                                                                                                                                                                            NN1 = (Listdefect.get(38));
                                                                                                                                                                                                            OO1 = (Listdefect.get(39));
                                                                                                                                                                                                            PP1 = (Listdefect.get(40));
                                                                                                                                                                                                            QQ1 = (Listdefect.get(41));
                                                                                                                                                                                                            SS1 = (Listdefect.get(42));
                                                                                                                                                                                                            TT1 = (Listdefect.get(43));
                                                                                                                                                                                                            UU1 = (Listdefect.get(44));
                                                                                                                                                                                                            VV1 = (Listdefect.get(45));
                                                                                                                                                                                                            WW1 = (Listdefect.get(46));
                                                                                                                                                                                                            XX1 = (Listdefect.get(47));

                                                                                                                                                                                                        } else {
                                                                                                                                                                                                            if ((Listdefect != null) && (Listdefect.size() == 49)) {
                                                                                                                                                                                                                A1 = (Listdefect.get(0));

                                                                                                                                                                                                                B1 = (Listdefect.get(1));
                                                                                                                                                                                                                C1 = (Listdefect.get(2));
                                                                                                                                                                                                                D1 = (Listdefect.get(3));
                                                                                                                                                                                                                E1 = (Listdefect.get(4));
                                                                                                                                                                                                                F1 = (Listdefect.get(5));
                                                                                                                                                                                                                G1 = (Listdefect.get(6));
                                                                                                                                                                                                                H1 = (Listdefect.get(7));
                                                                                                                                                                                                                I1 = (Listdefect.get(8));
                                                                                                                                                                                                                J1 = (Listdefect.get(9));
                                                                                                                                                                                                                K1 = (Listdefect.get(10));
                                                                                                                                                                                                                L1 = (Listdefect.get(11));
                                                                                                                                                                                                                M1 = (Listdefect.get(12));
                                                                                                                                                                                                                N1 = (Listdefect.get(13));
                                                                                                                                                                                                                O1 = (Listdefect.get(14));
                                                                                                                                                                                                                P1 = (Listdefect.get(15));
                                                                                                                                                                                                                Q1 = (Listdefect.get(16));
                                                                                                                                                                                                                S1 = (Listdefect.get(17));
                                                                                                                                                                                                                T1 = (Listdefect.get(18));
                                                                                                                                                                                                                U1 = (Listdefect.get(19));
                                                                                                                                                                                                                V1 = (Listdefect.get(20));
                                                                                                                                                                                                                W1 = (Listdefect.get(21));
                                                                                                                                                                                                                X1 = (Listdefect.get(22));
                                                                                                                                                                                                                Y1 = (Listdefect.get(23));
                                                                                                                                                                                                                Z1 = (Listdefect.get(24));
                                                                                                                                                                                                                AA1 = (Listdefect.get(25));
                                                                                                                                                                                                                BB1 = (Listdefect.get(26));
                                                                                                                                                                                                                CC1 = (Listdefect.get(27));
                                                                                                                                                                                                                DD1 = (Listdefect.get(28));
                                                                                                                                                                                                                EE1 = (Listdefect.get(29));
                                                                                                                                                                                                                FF1 = (Listdefect.get(30));
                                                                                                                                                                                                                GG1 = (Listdefect.get(31));
                                                                                                                                                                                                                HH1 = (Listdefect.get(32));
                                                                                                                                                                                                                II1 = (Listdefect.get(33));
                                                                                                                                                                                                                JJ1 = (Listdefect.get(34));
                                                                                                                                                                                                                KK1 = (Listdefect.get(35));
                                                                                                                                                                                                                LL1 = (Listdefect.get(36));
                                                                                                                                                                                                                MM1 = (Listdefect.get(37));
                                                                                                                                                                                                                NN1 = (Listdefect.get(38));
                                                                                                                                                                                                                OO1 = (Listdefect.get(39));
                                                                                                                                                                                                                PP1 = (Listdefect.get(40));
                                                                                                                                                                                                                QQ1 = (Listdefect.get(41));
                                                                                                                                                                                                                SS1 = (Listdefect.get(42));
                                                                                                                                                                                                                TT1 = (Listdefect.get(43));
                                                                                                                                                                                                                UU1 = (Listdefect.get(44));
                                                                                                                                                                                                                VV1 = (Listdefect.get(45));
                                                                                                                                                                                                                WW1 = (Listdefect.get(46));
                                                                                                                                                                                                                XX1 = (Listdefect.get(47));
                                                                                                                                                                                                                YY1 = (Listdefect.get(48));

                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                if ((Listdefect != null) && (Listdefect.size() == 50)) {
                                                                                                                                                                                                                    A1 = (Listdefect.get(0));

                                                                                                                                                                                                                    B1 = (Listdefect.get(1));
                                                                                                                                                                                                                    C1 = (Listdefect.get(2));
                                                                                                                                                                                                                    D1 = (Listdefect.get(3));
                                                                                                                                                                                                                    E1 = (Listdefect.get(4));
                                                                                                                                                                                                                    F1 = (Listdefect.get(5));
                                                                                                                                                                                                                    G1 = (Listdefect.get(6));
                                                                                                                                                                                                                    H1 = (Listdefect.get(7));
                                                                                                                                                                                                                    I1 = (Listdefect.get(8));
                                                                                                                                                                                                                    J1 = (Listdefect.get(9));
                                                                                                                                                                                                                    K1 = (Listdefect.get(10));
                                                                                                                                                                                                                    L1 = (Listdefect.get(11));
                                                                                                                                                                                                                    M1 = (Listdefect.get(12));
                                                                                                                                                                                                                    N1 = (Listdefect.get(13));
                                                                                                                                                                                                                    O1 = (Listdefect.get(14));
                                                                                                                                                                                                                    P1 = (Listdefect.get(15));
                                                                                                                                                                                                                    Q1 = (Listdefect.get(16));
                                                                                                                                                                                                                    S1 = (Listdefect.get(17));
                                                                                                                                                                                                                    T1 = (Listdefect.get(18));
                                                                                                                                                                                                                    U1 = (Listdefect.get(19));
                                                                                                                                                                                                                    V1 = (Listdefect.get(20));
                                                                                                                                                                                                                    W1 = (Listdefect.get(21));
                                                                                                                                                                                                                    X1 = (Listdefect.get(22));
                                                                                                                                                                                                                    Y1 = (Listdefect.get(23));
                                                                                                                                                                                                                    Z1 = (Listdefect.get(24));
                                                                                                                                                                                                                    AA1 = (Listdefect.get(25));
                                                                                                                                                                                                                    BB1 = (Listdefect.get(26));
                                                                                                                                                                                                                    CC1 = (Listdefect.get(27));
                                                                                                                                                                                                                    DD1 = (Listdefect.get(28));
                                                                                                                                                                                                                    EE1 = (Listdefect.get(29));
                                                                                                                                                                                                                    FF1 = (Listdefect.get(30));
                                                                                                                                                                                                                    GG1 = (Listdefect.get(31));
                                                                                                                                                                                                                    HH1 = (Listdefect.get(32));
                                                                                                                                                                                                                    II1 = (Listdefect.get(33));
                                                                                                                                                                                                                    JJ1 = (Listdefect.get(34));
                                                                                                                                                                                                                    KK1 = (Listdefect.get(35));
                                                                                                                                                                                                                    LL1 = (Listdefect.get(36));
                                                                                                                                                                                                                    MM1 = (Listdefect.get(37));
                                                                                                                                                                                                                    NN1 = (Listdefect.get(38));
                                                                                                                                                                                                                    OO1 = (Listdefect.get(39));
                                                                                                                                                                                                                    PP1 = (Listdefect.get(40));
                                                                                                                                                                                                                    QQ1 = (Listdefect.get(41));
                                                                                                                                                                                                                    SS1 = (Listdefect.get(42));
                                                                                                                                                                                                                    TT1 = (Listdefect.get(43));
                                                                                                                                                                                                                    UU1 = (Listdefect.get(44));
                                                                                                                                                                                                                    VV1 = (Listdefect.get(45));
                                                                                                                                                                                                                    WW1 = (Listdefect.get(46));
                                                                                                                                                                                                                    XX1 = (Listdefect.get(47));
                                                                                                                                                                                                                    YY1 = (Listdefect.get(48));
                                                                                                                                                                                                                    ZZ1 = (Listdefect.get(49));

                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                    if ((Listdefect != null) && (Listdefect.size() == 51)) {
                                                                                                                                                                                                                        A1 = (Listdefect.get(0));

                                                                                                                                                                                                                        B1 = (Listdefect.get(1));
                                                                                                                                                                                                                        C1 = (Listdefect.get(2));
                                                                                                                                                                                                                        D1 = (Listdefect.get(3));
                                                                                                                                                                                                                        E1 = (Listdefect.get(4));
                                                                                                                                                                                                                        F1 = (Listdefect.get(5));
                                                                                                                                                                                                                        G1 = (Listdefect.get(6));
                                                                                                                                                                                                                        H1 = (Listdefect.get(7));
                                                                                                                                                                                                                        I1 = (Listdefect.get(8));
                                                                                                                                                                                                                        J1 = (Listdefect.get(9));
                                                                                                                                                                                                                        K1 = (Listdefect.get(10));
                                                                                                                                                                                                                        L1 = (Listdefect.get(11));
                                                                                                                                                                                                                        M1 = (Listdefect.get(12));
                                                                                                                                                                                                                        N1 = (Listdefect.get(13));
                                                                                                                                                                                                                        O1 = (Listdefect.get(14));
                                                                                                                                                                                                                        P1 = (Listdefect.get(15));
                                                                                                                                                                                                                        Q1 = (Listdefect.get(16));
                                                                                                                                                                                                                        S1 = (Listdefect.get(17));
                                                                                                                                                                                                                        T1 = (Listdefect.get(18));
                                                                                                                                                                                                                        U1 = (Listdefect.get(19));
                                                                                                                                                                                                                        V1 = (Listdefect.get(20));
                                                                                                                                                                                                                        W1 = (Listdefect.get(21));
                                                                                                                                                                                                                        X1 = (Listdefect.get(22));
                                                                                                                                                                                                                        Y1 = (Listdefect.get(23));
                                                                                                                                                                                                                        Z1 = (Listdefect.get(24));
                                                                                                                                                                                                                        AA1 = (Listdefect.get(25));
                                                                                                                                                                                                                        BB1 = (Listdefect.get(26));
                                                                                                                                                                                                                        CC1 = (Listdefect.get(27));
                                                                                                                                                                                                                        DD1 = (Listdefect.get(28));
                                                                                                                                                                                                                        EE1 = (Listdefect.get(29));
                                                                                                                                                                                                                        FF1 = (Listdefect.get(30));
                                                                                                                                                                                                                        GG1 = (Listdefect.get(31));
                                                                                                                                                                                                                        HH1 = (Listdefect.get(32));
                                                                                                                                                                                                                        II1 = (Listdefect.get(33));
                                                                                                                                                                                                                        JJ1 = (Listdefect.get(34));
                                                                                                                                                                                                                        KK1 = (Listdefect.get(35));
                                                                                                                                                                                                                        LL1 = (Listdefect.get(36));
                                                                                                                                                                                                                        MM1 = (Listdefect.get(37));
                                                                                                                                                                                                                        NN1 = (Listdefect.get(38));
                                                                                                                                                                                                                        OO1 = (Listdefect.get(39));
                                                                                                                                                                                                                        PP1 = (Listdefect.get(40));
                                                                                                                                                                                                                        QQ1 = (Listdefect.get(41));
                                                                                                                                                                                                                        SS1 = (Listdefect.get(42));
                                                                                                                                                                                                                        TT1 = (Listdefect.get(43));
                                                                                                                                                                                                                        UU1 = (Listdefect.get(44));
                                                                                                                                                                                                                        VV1 = (Listdefect.get(45));
                                                                                                                                                                                                                        WW1 = (Listdefect.get(46));
                                                                                                                                                                                                                        XX1 = (Listdefect.get(47));
                                                                                                                                                                                                                        YY1 = (Listdefect.get(48));
                                                                                                                                                                                                                        ZZ1 = (Listdefect.get(49));
                                                                                                                                                                                                                        AAA1 = (Listdefect.get(50));

                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                        if ((Listdefect != null) && (Listdefect.size() == 52)) {
                                                                                                                                                                                                                            A1 = (Listdefect.get(0));

                                                                                                                                                                                                                            B1 = (Listdefect.get(1));
                                                                                                                                                                                                                            C1 = (Listdefect.get(2));
                                                                                                                                                                                                                            D1 = (Listdefect.get(3));
                                                                                                                                                                                                                            E1 = (Listdefect.get(4));
                                                                                                                                                                                                                            F1 = (Listdefect.get(5));
                                                                                                                                                                                                                            G1 = (Listdefect.get(6));
                                                                                                                                                                                                                            H1 = (Listdefect.get(7));
                                                                                                                                                                                                                            I1 = (Listdefect.get(8));
                                                                                                                                                                                                                            J1 = (Listdefect.get(9));
                                                                                                                                                                                                                            K1 = (Listdefect.get(10));
                                                                                                                                                                                                                            L1 = (Listdefect.get(11));
                                                                                                                                                                                                                            M1 = (Listdefect.get(12));
                                                                                                                                                                                                                            N1 = (Listdefect.get(13));
                                                                                                                                                                                                                            O1 = (Listdefect.get(14));
                                                                                                                                                                                                                            P1 = (Listdefect.get(15));
                                                                                                                                                                                                                            Q1 = (Listdefect.get(16));
                                                                                                                                                                                                                            S1 = (Listdefect.get(17));
                                                                                                                                                                                                                            T1 = (Listdefect.get(18));
                                                                                                                                                                                                                            U1 = (Listdefect.get(19));
                                                                                                                                                                                                                            V1 = (Listdefect.get(20));
                                                                                                                                                                                                                            W1 = (Listdefect.get(21));
                                                                                                                                                                                                                            X1 = (Listdefect.get(22));
                                                                                                                                                                                                                            Y1 = (Listdefect.get(23));
                                                                                                                                                                                                                            Z1 = (Listdefect.get(24));
                                                                                                                                                                                                                            AA1 = (Listdefect.get(25));
                                                                                                                                                                                                                            BB1 = (Listdefect.get(26));
                                                                                                                                                                                                                            CC1 = (Listdefect.get(27));
                                                                                                                                                                                                                            DD1 = (Listdefect.get(28));
                                                                                                                                                                                                                            EE1 = (Listdefect.get(29));
                                                                                                                                                                                                                            FF1 = (Listdefect.get(30));
                                                                                                                                                                                                                            GG1 = (Listdefect.get(31));
                                                                                                                                                                                                                            HH1 = (Listdefect.get(32));
                                                                                                                                                                                                                            II1 = (Listdefect.get(33));
                                                                                                                                                                                                                            JJ1 = (Listdefect.get(34));
                                                                                                                                                                                                                            KK1 = (Listdefect.get(35));
                                                                                                                                                                                                                            LL1 = (Listdefect.get(36));
                                                                                                                                                                                                                            MM1 = (Listdefect.get(37));
                                                                                                                                                                                                                            NN1 = (Listdefect.get(38));
                                                                                                                                                                                                                            OO1 = (Listdefect.get(39));
                                                                                                                                                                                                                            PP1 = (Listdefect.get(40));
                                                                                                                                                                                                                            QQ1 = (Listdefect.get(41));
                                                                                                                                                                                                                            SS1 = (Listdefect.get(42));
                                                                                                                                                                                                                            TT1 = (Listdefect.get(43));
                                                                                                                                                                                                                            UU1 = (Listdefect.get(44));
                                                                                                                                                                                                                            VV1 = (Listdefect.get(45));
                                                                                                                                                                                                                            WW1 = (Listdefect.get(46));
                                                                                                                                                                                                                            XX1 = (Listdefect.get(47));
                                                                                                                                                                                                                            YY1 = (Listdefect.get(48));
                                                                                                                                                                                                                            ZZ1 = (Listdefect.get(49));
                                                                                                                                                                                                                            AAA1 = (Listdefect.get(50));
                                                                                                                                                                                                                            BBB1 = (Listdefect.get(51));

                                                                                                                                                                                                                        } else

                                                                                                                                                                                                                        {
                                                                                                                                                                                                                            Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Contact to ISSS ", Toast.LENGTH_LONG).show();
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }

                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


    }


    private class Gettotalprodu extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getText().toString();
            b = Spin_line.getText().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_totalprodu);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
                Request.addProperty("buyer", c);
                Request.addProperty("style", g);
                Request.addProperty("orderno", d);
                Request.addProperty("color", e);
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

    private class Gettotaldefect extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getText().toString();
            b = Spin_line.getText().toString();
            progressDialog = new ProgressDialog(Sewing.this);
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

    private class Gettarget extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getText().toString();
            b = Spin_line.getText().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();


            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_target);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
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
            String Secountp = Target.getText().toString();
            String Nill = "NILL";
            if (Secountp.equals(Nill)) {
                Toast.makeText(getApplicationContext(), "Please select Target", Toast.LENGTH_LONG).show();
            } else {
                String First1 = PRo.getText().toString();
                String Nill2 = "";
                if (First1.equals(Nill2)) {
                    Toast.makeText(getApplicationContext(), "NO Production", Toast.LENGTH_LONG).show();
                } else {
                    int result1 = Integer.parseInt(First1);
                    int resultp = Integer.parseInt(Secountp);
                    int a = resultp - result1;
                    String numberAsString = Integer.toString(a);
                    Blink.setText(numberAsString);
                }
            }
        }
    }

    private class GetTpro extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getText().toString();
            b = Spin_line.getText().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            h = Spin_size.getSelectedItem().toString();


            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_Tpro);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
                Request.addProperty("section", section);
                Request.addProperty("buyer", c);
                Request.addProperty("po", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);
                Request.addProperty("size", h);


                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_Tpro, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null) {
                    ListTpro = Arrays.asList(result);
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
            if ((ListTpro != null) && (ListTpro.size() > 0)) {
                Tpro.setText(ListTpro.get(0));
            } else {


            }


        }
    }

    private class Getbacklock extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getText().toString();
            b = Spin_line.getText().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();


            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_backlock);
                Request.addProperty("unitname", a);
                Request.addProperty("line", b);
                Request.addProperty("buyer", c);
                Request.addProperty("orderno", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);


                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(Config.URL);
                transport.call(SOAP_ACTION_backlock, soapEnvelope);
                resultString = (SoapPrimitive) soapEnvelope.getResponse();
                //  SoapObject res=(SoapObject) soapEnvelope.getResponse();
                Log.i(TAG, "OPR Name " + resultString);
                String[] result = resultString.toString().split(",");
                if (result != null) {
                    Listbacklock = Arrays.asList(result);
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
            if ((Listbacklock != null) && (Listbacklock.size() > 0)) {
                Backlock.setText(Listbacklock.get(0));
            } else {


            }


        }
    }

    private class GetTrework extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getText().toString();
            b = Spin_line.getText().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();


            g = Spin_style.getSelectedItem().toString();
            h = bulk.getText().toString();
            l = Oprname.getText().toString();

            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_Trework);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
                Request.addProperty("buyer", c);
                Request.addProperty("po", d);
                Request.addProperty("style", g);
                Request.addProperty("color", e);
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

            a = Spin_unit.getText().toString();
            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_qc);
                 Request.addProperty("unit",a);
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
                Oprname2.setText(ListTpro.get(0));
            }

        }
    }

    private class getRework extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a = Spin_unit.getText().toString();
            b = Spin_line.getText().toString();
            c = Spin_buyer.getSelectedItem().toString();
            d = Spin_Order_no.getSelectedItem().toString();
            e = Spin_color.getSelectedItem().toString();
            f = Spin_size.getSelectedItem().toString();
            g = Spin_style.getSelectedItem().toString();
            h = bulk.getText().toString();
            l = Oprname.getText().toString();

            progressDialog = new ProgressDialog(Sewing.this);
            progressDialog.setMessage("Loading please wait !!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME_Rework);
                Request.addProperty("unit", a);
                Request.addProperty("line", b);
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

            Gettotalprodu Pr = new Gettotalprodu();
            Pr.execute();
            GetTrework trework = new GetTrework();
            trework.execute();
        }
    }


}