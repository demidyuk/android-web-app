package com.example.webapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webapp.util.Proxy;
import com.google.webviewlocalserver.WebViewLocalServer;

import java.util.HashMap;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

public class WebViewContainer {

    private final static String SUBDOMAIN = "SUBDOMAIN";

    private WebView webView;
    private WebViewLocalServer assetServer;
    private Proxy proxy;

    private SharedPreferences sPref;

    public WebViewContainer(WebView webView) {
        this.webView = webView;
        sPref = webView.getContext().getSharedPreferences("webapp", MODE_PRIVATE);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        webSettings.setDomStorageEnabled(true);

        webView.addJavascriptInterface(new WebAppInterface(webView.getContext()), "__ANDROID__");

        assetServer = new WebViewLocalServer(webView.getContext(), getSubdomain());
        WebViewLocalServer.AssetHostingDetails details =
                assetServer.hostAssets("www");

        proxy = new Proxy(details.getHttpPrefix().buildUpon().build(),
                new HashMap<String, String>() {{
                    put(Proxy.ALL, "/index.html");
                    put(Proxy.JS, "/js");
                    put(Proxy.CSS, "/css");
                    put(Proxy.FAVICON, "/favicon.ico");
                }});

        webView.setWebViewClient(new WebClient());
    }

    public void loadApp(){
        webView.loadUrl(proxy.root());
    }

    private String getSubdomain() {
        String subdomain = loadSubdomain();
        if (subdomain == null) {
            subdomain = UUID.randomUUID().toString();
            saveSubdomain(subdomain);
        }
        return subdomain;
    }

    private void saveSubdomain(String subdomain) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SUBDOMAIN, subdomain);
        ed.apply();
    }

    private String loadSubdomain() {
        return sPref.getString(SUBDOMAIN, null);
    }

    public boolean goBack() {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return false;
    }

    class WebClient extends WebViewClient {
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return assetServer.shouldInterceptRequest(proxy.get(request.getUrl()));
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (proxy.isExternal(Uri.parse(url))) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            }
            return false;
        }
    }
}
