package com.matthewgeorgiev.project.MoviePreview.utils;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class Helper {
    public Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    public static ZoneId defaultZoneId = TimeZone.getDefault().toZoneId();
    public static Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    public static LocalDateTime timeStampToLocalDateTime(Timestamp timestamp) {
        return LocalDateTime.ofInstant(timestamp.toInstant(), defaultZoneId);
    }

}
