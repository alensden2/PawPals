package com.asdc.pawpals.validators;

import com.asdc.pawpals.utils.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class AppointmentValidators {

  public static boolean isValidDate(String dateStr) {
    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
    sdf.setLenient(false);
    try {
      Date date = sdf.parse(dateStr);
      return dateStr.equals(sdf.format(date));
    } catch (ParseException e) {
      return false;
    }
  }

  public static boolean isValidTime(String timeStr) {
    SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
    sdf.setLenient(false);
    try {
      Date time = sdf.parse(timeStr);
      return timeStr.equals(sdf.format(time));
    } catch (ParseException e) {
      return false;
    }
  }

  public static boolean isValidStatus(String statusStr) {
    return Arrays.asList(Constants.STATUS).contains(statusStr.toUpperCase());
  }

  public static boolean isValidStartTimeBeforeEndTime(
    String startTimeStr,
    String endTimeStr
  ) {
    SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
    sdf.setLenient(false);
    try {
      Date startTime = sdf.parse(startTimeStr);
      Date endTime = sdf.parse(endTimeStr);
      return startTime.before(endTime);
    } catch (ParseException e) {
      return false;
    }
  }

  public static boolean isValidAppointment(
    String dateStr,
    String startTimeStr,
    String endTimeStr,
    String statusStr
  ) {
    boolean validDate = isValidDate(dateStr);
    boolean validTimeStart = isValidTime(startTimeStr);
    boolean validTimeEnd = isValidTime(endTimeStr);
    boolean validStaus = isValidStatus(statusStr);
    boolean validStartBeforEnd = isValidStartTimeBeforeEndTime(
      startTimeStr,
      endTimeStr
    );

    return (
      validDate &&
      validTimeStart &&
      validTimeEnd &&
      validStaus &&
      validStartBeforEnd
    );
  }
}
