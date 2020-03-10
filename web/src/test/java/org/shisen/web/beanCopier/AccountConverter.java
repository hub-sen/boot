package org.shisen.web.beanCopier;

import org.springframework.cglib.core.Converter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

class AccountConverter implements Converter {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object convert(Object source, Class target, Object context) {
        if (source instanceof Integer) {
            return (Integer) source;
        } else if (source instanceof Date) {
            Date date = (Date) source;
            return sdf.format(date);
        } else if (source instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) source;
            return bd.toPlainString();
        }



        return null;
    }
}