package com.clover.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lkl on 2017/3/23.
 */
public class DateUtil {

    public static String getCurrentDate() {
        SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }

}
