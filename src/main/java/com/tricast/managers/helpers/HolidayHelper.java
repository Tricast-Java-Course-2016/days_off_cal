package com.tricast.managers.helpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.tricast.managers.beans.Holiday;

public class HolidayHelper {

	private static SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
	private static List<Integer> weekend = new ArrayList<Integer>(
			Arrays.asList(Calendar.SATURDAY, Calendar.SUNDAY));

	private static Calendar convertDayStringToCalendar(String dayString) {
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.set(Calendar.YEAR,
				Integer.parseInt(dayString.substring(0, 4)));
		newCalendar.set(Calendar.MONTH,
				Integer.parseInt(dayString.substring(4, 6)) - 1);
		newCalendar.set(Calendar.DAY_OF_MONTH,
				Integer.parseInt(dayString.substring(6, 8)));
		newCalendar.set(Calendar.HOUR_OF_DAY, 0);
		newCalendar.set(Calendar.MINUTE, 0);
		newCalendar.set(Calendar.SECOND, 0);
		newCalendar.set(Calendar.MILLISECOND, 0);
		return newCalendar;
	}

	private static String convertCalendarToDayString(Calendar calendar) {
		return sdf.format(calendar.getTime());
	}

	public static int getActualNumberOfDaysForLeave(Holiday leave,
			List<String> blockedDays) {
		int actualDays = 0;
		Calendar currentDay = convertDayStringToCalendar(leave.getFromDay());
		Calendar endDay = convertDayStringToCalendar(leave.getToDay());
		do {
			if (blockedDays.contains(convertCalendarToDayString(currentDay))) {
				currentDay.add(Calendar.DAY_OF_MONTH, 1);
				continue;
			}
			if (weekend.contains(currentDay.get(Calendar.DAY_OF_WEEK))) {
				currentDay.add(Calendar.DAY_OF_MONTH, 1);
				continue;
			}
			actualDays++;
			currentDay.add(Calendar.DAY_OF_MONTH, 1);
		} while (currentDay.compareTo(endDay) <= 0);
		return actualDays;
	}

	public static boolean isOverlapping(List<Holiday> holidays,
			Holiday newHoliday) {
		boolean result = false;
		Calendar newStart = convertDayStringToCalendar(newHoliday.getFromDay());
		Calendar newEnd = convertDayStringToCalendar(newHoliday.getToDay());
		for (Holiday oldHoliday : holidays) {
			Calendar oldStart = convertDayStringToCalendar(oldHoliday
					.getFromDay());
			Calendar oldEnd = convertDayStringToCalendar(oldHoliday.getToDay());
			if (!((newStart.before(oldStart) && newEnd.before(oldStart)) || (newStart
					.after(oldEnd) && newEnd.after(oldEnd)))) {
				result = true;
				break;
			}
		}
		return result;
	}
}
