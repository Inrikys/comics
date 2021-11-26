package com.estudo.designpattern.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;

public class DateUtil {

    public static Integer getCurrentDayOfWeek() {
        DayOfWeek dow = Instant.now().atZone(ZoneId.systemDefault()).getDayOfWeek();
        return dow.getValue();
    }

}