package com.h2.hotelmangement.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParsing {
    public static String convertDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }
}
