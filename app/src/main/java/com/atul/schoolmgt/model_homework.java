package com.atul.schoolmgt;

public class model_homework {
    private String date,quotes;

    public model_homework(String date, String quotes) {
        this.date = date;
        this.quotes = quotes;
    }

    public String getDate() {
        return date;
    }

    public String getQuotes() {
        return quotes;
    }
}
