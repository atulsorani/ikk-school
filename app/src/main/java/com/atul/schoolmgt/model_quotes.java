package com.atul.schoolmgt;

public class model_quotes {
    private String date, quotes, desc;

    public model_quotes(String date, String quotes, String desc) {
        this.date = date;
        this.quotes = quotes;
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public String getQuotes() {
        return quotes;
    }

    public String getDesc() {
        return desc;
    }
}
