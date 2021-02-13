package com.deepak.newsapp.headlines;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeFormate {
    private static final String TAG = "TimeFormat";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String currentTimeIs(String publishedAt){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
     dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = Calendar.getInstance().getTime();
        return dateFormat.format(date);
    }
}
