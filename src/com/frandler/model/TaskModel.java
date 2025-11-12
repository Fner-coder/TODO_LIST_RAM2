package com.frandler.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.frandler.tache.Task;

public class TaskModel{
	
	Task task = new Task();
	
	int compt = 1;
	ArrayList<Task> mastesTaskList = new ArrayList<Task>();
	
	public int addTask(ArrayList<String> assignedTo, String taskString, int duration, String startAt) {
		int newId = compt++;
        Task newTask = new Task(newId, assignedTo, taskString, duration, startAt);
        mastesTaskList.add(newTask);
        
        return newId++;
    }
	//------------------------------------------------------------------------------------------
	private String formatDuration(int duration) {
	    int days = duration / (60 * 24);
	    int hours = (duration % (60 * 24)) / 60;
	    int minutes = duration % 60;
	    
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
 
	// Méthode pour calculer la date de fin
    public LocalDateTime calculateEndDate(String startAt, int durationInMinutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(startAt, formatter);
        
        // Ajouter la durée en minutes
        LocalDateTime endDate = startDate.plusMinutes(durationInMinutes);
        
        return endDate;
    }
   
    // Méthode pour calculer la durée entre deux dates
    public String calculateDurationBetweenDates(String startAt, String endAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(startAt, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endAt, formatter);
        
        Duration duration = Duration.between(startDate, endDate);
        long totalMinutes = duration.toMinutes();
        
        return formatDuration((int) totalMinutes);
    }
    
    
    
    
    
    
    
    
    
    
    
	
	
	
	public String getTasks() {
    	String stringbuilder = "";
    	String patternString = "yyyy-MM-dd HH:mm";
    	
 	    DateTimeFormatter Dateformatter = DateTimeFormatter.ofPattern(patternString);
 	   
    	for (Task t : mastesTaskList) {
    		
    		String workersString = "";
    		ArrayList<String> assignedTo = t.getAssignedTo();
    		
    		LocalDateTime startDate = LocalDateTime.parse(t.getStartDate(), Dateformatter);
    		
			for(int i = 0; i < assignedTo.size(); i++) {
				workersString += assignedTo.get(i);
				 if (i < assignedTo.size() - 1) {
					 workersString += ", ";
	                }
			}
			stringbuilder +=" ID_task: " + t.getId() +
    	    		"\n Task: " + t.getTaskString() +
    	    		"\n Assigned to: " + workersString +
    	    		"\n Duration: " + formatDuration(t.getDuree()) +
    	    		"\n Start date: " + startDate + "\n";
//					"\n End date: " + getEndDate(formatDuration(t.getDuree()), startDate) + "\n";
			
			stringbuilder += "-----------------------------------------------------------------\n";
    	}
    	
		return stringbuilder;
	}
	
} 