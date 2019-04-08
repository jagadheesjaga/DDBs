package com.myapplication1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.myapplication1.R;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Linestatus extends AppCompatActivity {

    // Button btn;
    // TextView txt;

    WebView wv;
    ProgressBar progressBar;
   // String url = "http://192.168.0.201:8085/Appview.aspx";
   // String url = "http://192.168.0.200:8039/Appview.aspx";
   String url = "http://192.168.0.201:8085/Appview.aspx";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_status);

        // btn = (Button) findViewById(R.id.button);
        // txt = (TextView) findViewById(R.id.editText);
        wv = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // wv.setWebViewClient(new MyBrowser());

        wv.setWebViewClient(new myWebClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setDisplayZoomControls(false);
        wv.loadUrl(url);
        Toast.makeText(getApplicationContext(), "\uD83D\uDE1E Please select the Line", Toast.LENGTH_LONG).show();

    }


    public  class myWebClient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }

    }

}