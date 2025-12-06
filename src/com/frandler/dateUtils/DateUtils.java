package com.frandler.dateUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	    public DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	    
//	    public LocalDateTime parseDateTime(String dateTimeStr) {
//	        return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
//	    }
	    
	    public LocalDateTime parseDateTime(String dateTimeString) {
	        if (dateTimeString == null || dateTimeString.isBlank()) {
	            return null;
	        }
	        return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
	    }
	    
	    public String formatDateTime(LocalDateTime dateTime) {
	    	return dateTime.format(DATE_TIME_FORMATTER);
	    }
	    
	    public LocalDateTime calculateEndDate(String startDate, int durationInMinutes) {
	    	if (startDate == null) return null;
	    	if (durationInMinutes <= 0) return null;
	    	return parseDateTime(startDate).plusMinutes(durationInMinutes);
	    }
	    
	    public String calculateDurationBetweenDates(String startDate, String endAt) {
	    	LocalDateTime tmpStartDate = parseDateTime(startDate);
	        LocalDateTime endDate = parseDateTime(endAt);
	    	Duration duration = Duration.between(tmpStartDate, endDate);
	    		
	        return formatDuration((int) duration.toMinutes());
	    }
	    
	    public String formatDuration(int totalMinutes) {
	    	int days = totalMinutes / (60 * 24);
		    int hours = (totalMinutes % (60 * 24)) / 60;
		    int minutes = totalMinutes % 60;
		    StringBuilder result = new StringBuilder();
		    if (days > 0) 
		        result.append(days).append(days > 1 ? " days:" : " day:");
		    if (hours == 0) {
		    	result.append(String.format("%02dm", minutes));
		    }
		    else {
		    	result.append(String.format("%dh:%02dm", hours, minutes));
			}
		    return result.toString();
	    }
}