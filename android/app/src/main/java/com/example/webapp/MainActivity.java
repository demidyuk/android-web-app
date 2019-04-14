package com.example.webapp;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    private WebViewContainer container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = new WebViewContainer(findViewById(R.id.main_webview));

        // load app from assets
        container.loadApp();
    }

    @Override
    public void onBackPressed() {
        if (!container.goBack()) {
            super.onBackPressed();
        }
    }
}
