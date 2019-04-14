package com.example.webapp;

import android.net.Uri;

import java.util.Map;
import java.util.Objects;

public class Proxy {
    public final static String ALL = "*";
    public final static String JS = "/js";
    public final static String CSS = "/css";
    public final static String FAVICON = "/favicon.ico";

    private Uri base;
    private Map<String, String> config;

    public Proxy(Uri base, Map<String, String> config) {
        this.base = base;
        this.config = config;
    }

    public String root() {
        return base.getScheme() + "://" + base.getAuthority();
    }

    public boolean isExternal(Uri url) {
        return !Objects.requireNonNull(url.getAuthority()).contentEquals(Objects.requireNonNull(base.getAuthority()));
    }

    public String get(Uri url) {
        for (Map.Entry<String, String> entry : config.entrySet()) {
            if (entry.getKey().contentEquals(ALL)) continue;
            if (Objects.requireNonNull(url.getPath()).startsWith(entry.getKey())) {
                return getFinalUrl(entry.getValue() + url.getPath().substring(entry.getKey().length()));
            }
        }
        return getFinalUrl(config.get(ALL));
    }

    private String getFinalUrl(String path) {
        return this.base.buildUpon().build().toString() + path;
    }
}
