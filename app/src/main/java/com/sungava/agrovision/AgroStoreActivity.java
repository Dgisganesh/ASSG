package com.sungava.agrovision;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class AgroStoreActivity extends AppCompatActivity {

    private WebView webViewAgroStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_agro_store
        );


        webViewAgroStore =
                findViewById(
                        R.id.webViewAgroStore
                );


        webViewAgroStore.setWebViewClient(
                new WebViewClient()
        );


        webViewAgroStore.getSettings()
                .setJavaScriptEnabled(true);


        webViewAgroStore.loadUrl(
                "file:///android_asset/agro_store.html"
        );
    }
}