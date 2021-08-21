package com.xinshuo.treehole.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
