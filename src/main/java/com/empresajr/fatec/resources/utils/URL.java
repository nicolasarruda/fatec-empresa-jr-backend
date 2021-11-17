package com.empresajr.fatec.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

    public static String decodeParameter(String text){
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e){
            return "";
        }
    }

    public static Date convertDate(String textDate, Date defaulValue){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try{
            System.out.println(textDate);
            return sdf.parse(textDate);
        } catch (ParseException e){
            System.out.println(textDate);
            System.out.println(defaulValue);
            return defaulValue;
        }
    }
}
