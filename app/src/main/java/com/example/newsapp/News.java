package com.example.newsapp;

public class News {
    private String mPillarName;

    private String mSectionName;

    private String mWebTitle;

    private String mDate;

    private String mUrl;

    public News(String pillarName, String sectionName, String webTitle, String date, String url) {
        mPillarName = pillarName;
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mDate = date;
        mUrl = url;
    }

    public String getPillarName() {
        return mPillarName;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}

