package tech.getarrays.employeemanager.utils;

import tech.getarrays.employeemanager.entity.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date getDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String newDate = new String(date).replace("T", " ");
        try {
            return dateFormat.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
