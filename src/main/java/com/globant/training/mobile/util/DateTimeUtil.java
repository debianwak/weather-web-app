package com.globant.training.mobile.util;

import java.util.Date;

public interface DateTimeUtil {

    String convertUnixDateToString(String pattern, long unixDate);
    String convertDateToString(String pattern, Date date);
}
