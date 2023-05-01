package com.yakushkin.url;

public enum OnlinerBaseUrl {

    MAIN_PAGE_URL("https://onliner.by"),
    CATALOG_PAGE_URL("https://catalog.onliner.by/");

    private final String url;

    OnlinerBaseUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
