package com.globant.training.mobile.util;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date time util class for Weather app
 * @author jtapia
 * @since 04/29/2019
 * */
@Component
public class DateTimeUtilImpl implements DateTimeUtil {
    private static final int ONE_THOUSAND = 1000;
    private SimpleDateFormat simpleDateFormat;
    
    @Inject
    public DateTimeUtilImpl(SimpleDateFormat simpleDateFormat) {
        Assert.notNull(simpleDateFormat, "simpleDateFormat must not be null!");
        this.simpleDateFormat = simpleDateFormat;
    }
    
    @Override
    public String convertUnixDateToString(String pattern, long unixDate) {
        simpleDateFormat.applyPattern(pattern);
        
        return simpleDateFormat.format(new Date(unixDate * ONE_THOUSAND));
    }
    
    @Override
    public String convertDateToString(String pattern, Date date) {
        simpleDateFormat.applyPattern(pattern);
    
        return simpleDateFormat.format(date);
    }
}
