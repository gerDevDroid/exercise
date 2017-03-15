package com.gerus.reddit.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;


/**
 * Created by gerus-mac on 03/10/16.
 */

public class UDate {

    public static final String FORMAT_ALL_DATE = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_COMPLETE_DATE = "E, d MMM yyyy   hh:mm a";
    public static final String FORMAT_DAY_DATE = "E, d MMM yyyy";
    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String FORMAT_HOUR = "hh:mm a";

    public static String getFormatByStringCompleteDate(String psDate) throws Exception {
        SimpleDateFormat voFormat= new SimpleDateFormat(FORMAT_ALL_DATE);
        Date voDate = voFormat.parse(psDate);
        return getDayFormat(voDate);
    }

    public static String getFormatByStringOnlyDate(String psDate) throws Exception {
        SimpleDateFormat voFormat= new SimpleDateFormat(FORMAT_ALL_DATE);
        Date voDate = voFormat.parse(psDate);
        return getDayFormat(voDate);
    }

    public static String getFormatDate(Date poDate){
        return new SimpleDateFormat(FORMAT_DATE).format(poDate);
    }


    public static String getCompleteDate(Date poDate){
        return new SimpleDateFormat(FORMAT_COMPLETE_DATE).format(poDate);
    }

    public static String getServerDate(Date poDate){
        return new SimpleDateFormat(FORMAT_ALL_DATE).format(poDate);
    }

    public static String getDayFormat(Date poDate){
        return new SimpleDateFormat(FORMAT_DAY_DATE).format(poDate);
    }

    public static String getFormatHour(Date poDate){
        return new SimpleDateFormat(FORMAT_HOUR).format(poDate);
    }

    public static void getTimeArray(TreeMap<Long,String> poValues, Calendar poCalendar){
        Calendar mCalendar = poCalendar;
        mCalendar.set(Calendar.HOUR_OF_DAY, 8);
        mCalendar.set(Calendar.MINUTE, 0);
        mCalendar.set(Calendar.SECOND, 0);
        int viHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        final int viMaxHour = 17; //horas son las 5:00 p.m.
        while (viHour < viMaxHour){
            poValues.put(mCalendar.getTime().getTime(),null);
            mCalendar.add(Calendar.MINUTE, 30);
            //Log.d("**FECHA ARRAY ***", ""+UDate.getServerDate(mCalendar.getTime()));
            viHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        }
    }



}
