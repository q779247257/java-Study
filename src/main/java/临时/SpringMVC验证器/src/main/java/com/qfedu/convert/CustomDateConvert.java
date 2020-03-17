package com.qfedu.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 自定义转换器，第一个类型,表示待转换的数据的类型；第二个类型，表示转换后类型
public class CustomDateConvert implements Converter<String, Date> {
    @Nullable
    @Override
    public Date convert(String s) {
        SimpleDateFormat[] sdfs = new SimpleDateFormat[]{
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("yyyyMMdd"),
            new SimpleDateFormat("yyyy年MM月dd日"),
    };

        for (SimpleDateFormat sdf: sdfs) {
            try {
                return sdf.parse(s);
            } catch (ParseException e) {
                //e.printStackTrace();
                continue;
            }
        }
        return null;
    }
}
