package org.shisen.web.beanCopier;

import lombok.SneakyThrows;
import org.springframework.cglib.core.Converter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

class AccountConverter implements Converter, net.sf.cglib.core.Converter {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @SneakyThrows
    @Override
    public Object convert(Object source, Class target, Object context) {

        System.out.println("source = " + source);
        System.out.println("target = " + target);
        System.out.println("context = " + context);
        System.out.println(" ================ ");


        Object o = target.newInstance();

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