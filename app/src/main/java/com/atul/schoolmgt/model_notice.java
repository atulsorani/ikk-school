package com.atul.schoolmgt;

public class model_notice {
    private  String title, date, desc;


    public model_notice(String title, String date, String desc) {

        this.title = title;
        this.date = date;
        this.desc = desc;

    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }
}

