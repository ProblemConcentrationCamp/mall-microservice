package com.mall.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * <pre>
 *  make the string 2 data when the json change 2 bean
 * </pre>
 *
 * @author LCN
 * @date 2019-12-14 21:57
 */
public class String2DataConverter implements Converter<String, Date> {


    @Override
    public Date convert(String s) {

        // TODO
        return null;
    }

}
