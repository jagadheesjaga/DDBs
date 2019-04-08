package com.myapplication1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Rejection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final Context context = this;

    String a;
    String b;
    Button defect1;
    String Defect =" ";
    Button defect2,defect3,defect4,defect5,defect6,defect7,defect8,defect9,defect10,defect11,defect12,defect13,
            defect14,defect15,defect16,defect17,defect18,defect19,defect20,defect21,defect22,defect23;;
    Button Set;

    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    List<String> Listone = null;
    List<String> Listtwo = null;
    private GestureDetectorCompat gestureDetectorCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rejection);

        defect1=(Button) findViewById(R.id.button6);
        defect2=(Button) findViewById(R.id.button7);
        defect3=(Button) findViewById(R.id.button13);
        defect4=(Button) findViewById(R.id.button15);
        defect5=(Button) findViewById(R.id.button5);
        defect6=(Button) findViewById(R.id.button10);
        defect7=(Button) findViewById(R.id.button8);
        defect8=(Button) findViewById(R.id.button12);
        defect9=(Button) findViewById(R.id.button14);
        defect10=(Button) findViewById(R.id.button16);
        defect11=(Button) findViewById(R.id.but);
        defect12=(Button) findViewById(R.id.button23);
        defect13=(Button) findViewById(R.id.button18);
        defect14=(Button) findViewById(R.id.button17);
        defect15=(Button) findViewById(R.id.button20);
        defect16=(Button) findViewById(R.id.button21);
        defect17=(Button) findViewById(R.id.button11);
        defect18=(Button) findViewById(R.id.button22);
        defect19=(Button) findViewById(R.id.button4);
        defect20=(Button) findViewById(R.id.button19);

        defect1.setOnClickListener(new View.OnClickListener() {


            // Start NewActivity.class
            @Override
            public void onClick(View arg0) {

                 a=defect1.getText().toString();

                Bundle bundleCC = new Bundle();
                bundleCC.putString("de", a);

                Intent myIntent = new Intent(context, Sewing.class);
                myIntent.putExtra("de", a);

                myIntent.putExtras(bundleCC);
               // myIntent.putExtras(bundle2);
                startActivity(myIntent);

            }


        });
        defect2.setOnClickListener(new View.OnClickListener() {


            // Start NewActivity.class
            @Override
            public void onClick(View arg0) {
                a=defect1.getText().toString();
                Bundle bundleCC = new Bundle();
                bundleCC.putString("SPI VARIATION", a);

                Intent myIntent = new Intent(context, Sewing.class);
                myIntent.putExtra("de", a);
                myIntent.putExtras(bundleCC);
                startActivity(myIntent);
            }

        });
        defect3.setOnClickListener(new View.OnClickListener() {


            // Start NewActivity.class
            @Override
            public void onClick(View arg0) {
                a=defect1.getText().toString();
                Bundle bundleCC = new Bundle();
                bundleCC.putString("SPI VARIATION", a);

                Intent myIntent = new Intent(context, Sewing.class);
                myIntent.putExtra("de", a);
                myIntent.putExtras(bundleCC);
                startActivity(myIntent);
            }

        });
        defect3.setOnClickListener(new View.OnClickListener() {


            // Start NewActivity.class
            @Override
            public void onClick(View arg0) {
                a=defect1.getText().toString();
                Bundle bundleCC = new Bundle();
                bundleCC.putString("SPI VARIATION", a);

                Intent myIntent = new Intent(context, Sewing.class);
                myIntent.putExtra("de", a);
                myIntent.putExtras(bundleCC);
                startActivity(myIntent);
            }

        });



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

//        listItems = getResources().getStringArray(R.array.shopping_item);
//        checkedItems = new boolean[listItems.length];
//
//        Set.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Defect.this);
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
//                        if(isChecked){
//                            mUserItems.add(position);
//                        }else{
//                            mUserItems.remove((Integer.valueOf(position)));
//                        }
//                    }
//                });
//
//                mBuilder.setCancelable(false);
//                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//
//                        String item = "";
//                        for (int i = 0; i < mUserItems.size(); i++) {
//                            item = item + listItems[mUserItems.get(i)];
//                            if (i != mUserItems.size() - 1) {
//                                item = item + ",";
//                            }
//                        }
//                        String[] result = item.toString().split(",");
//                        Listone = Arrays.asList(result);
//                        Listtwo = Arrays.asList(result);
//                        defect1.setText(Listone.get(0));
//                        defect2.setText(Listtwo.get(1));
//                        //  mItemSelected.setText(item);
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
//            }
//        });
//
//
    }




    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }


    @Override
    public void onResume() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }






    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }



}
