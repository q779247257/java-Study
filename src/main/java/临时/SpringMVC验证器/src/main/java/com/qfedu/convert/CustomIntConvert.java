package com.qfedu.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class CustomIntConvert implements Converter<String, Integer> {
    @Nullable
    @Override
    public Integer convert(String s) {
        int v = 0;
        try {
            v = Integer.parseInt(s);
        } catch (NumberFormatException e) {
        }

        return v;
    }
}
