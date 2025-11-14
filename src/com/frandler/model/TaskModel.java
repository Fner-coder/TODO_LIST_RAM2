package com.frandler.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.frandler.tache.Task;
import com.frandler.tache.Task.TaskStatus;

public class TaskModel{
	
	Task task = new Task();
	
	int compt = 1;
	ArrayList<Task> masterTaskList = new ArrayList<Task>();
	
	public int addTask(ArrayList<String> assignedTo, String taskString, int duration, String startAt) {
		int newId = compt++;
        Task newTask = new Task(newId, assignedTo, taskString, duration, startAt);
        masterTaskList.add(newTask);
        return newId++;
    }
	
// Duration FORMATING ==========================================================
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
 
// Method for Ending date================================================================
    public LocalDateTime calculateEndDate(String startAt, int durationInMinutes) {
        
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(startAt, formatter);
        // duration setup in minutes
        LocalDateTime endDate = startDate.plusMinutes(durationInMinutes);
        return endDate;
    }
  
// Method for duration between two dates==================================================
    public String calculateDurationBetweenDates(String startAt, String endAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(startAt, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endAt, formatter);
        Duration duration = Duration.between(startDate, endDate);
        long totalMinutes = duration.toMinutes();
        
        return formatDuration((int) totalMinutes);
    }
    
// GET LIST OF TASKS METHOD ==============================================================
    public String getTasks() {
    	String stringbuilder = "";
    	TaskStatus statusString = null;
    	
    	for (Task t : masterTaskList) {
    		LocalDateTime endDate = calculateEndDate(t.getStartDate(), t.getDuree());
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime startDate = LocalDateTime.parse(t.getStartDate(), formatter);
            
            LocalDateTime now = LocalDateTime.now();
            
            if (!endDate.isAfter(now))
            	statusString = TaskStatus.Done;
            if (startDate.isAfter(now))
            	statusString = TaskStatus.To_do;
            if (startDate.isBefore(now) && endDate.isBefore(now))
            	statusString = TaskStatus.In_progress;
            
    		String workersString = "";
    		ArrayList<String> assignedTo = t.getAssignedTo();

    		for(int i = 0; i < assignedTo.size(); i++) {
				workersString += assignedTo.get(i);
				 if (i < assignedTo.size() - 1) {
					 workersString += ", ";
	                }
			}
    		stringbuilder +=" ID_task: " + t.getId() +
					"\n Task: " + t.getTaskString() +
					"\n Assigned to: " + workersString +
					"\n Start Date: " + startDate.format(formatter) +
					"\n Duration: " + formatDuration(t.getDuree()) +
					"\n End Date: " + endDate.format(formatter) +
					"\n Status: " + statusString +"\n";
    		
    		stringbuilder += "-----------------------------------------------------------------\n";
    	}
        
        return stringbuilder ;
    }
    
    public String getTaskById(int id) {
//  ======================================================================================     
    	String stringbuilder = "";

    	for(Task t: masterTaskList) {   
    		LocalDateTime endDate = calculateEndDate(t.getStartDate(), t.getDuree());
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime startDate = LocalDateTime.parse(t.getStartDate(), formatter);
            
    		String workersString = "";
    		ArrayList<String> assignedTo = t.getAssignedTo();
    		
    		for(int i = 0; i < assignedTo.size(); i++) {
				workersString += assignedTo.get(i);
				 if (i < assignedTo.size() - 1) {
					 workersString += ", ";
	                }
			}
               if (id == t.getId()) {
            	   stringbuilder =" Task: " + t.getTaskString() +
       					"\n Assigned to: " + workersString +
       					"\n Start Date: " + startDate.format(formatter) +
       					"\n Duration: " + formatDuration(t.getDuree()) +
       					"\n End Date: " + endDate.format(formatter);
			}
               stringbuilder += "\n----------------------------------------------------------------------\n";
    	}
    	
		return stringbuilder;
	}
    
    public boolean removeTaskById(int id) {
    	for (Task t : masterTaskList) {
			if (id == t.getId()) {
				masterTaskList.remove(t);
		    	return true;
			}
		}
    	return false;
    }
    
    
    
////  GET LIST OF TASKS METHOD _2
//    public String getTasks() {
//        StringBuilder stb = new StringBuilder();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

//        for (Task t : mastesTaskList) {
//            // WORKERS LIST WITH String.join
//            String workersString = String.join(", ", t.getAssignedTo());
//
//            // USE THE DATAS'TASK
//            LocalDateTime startDate = LocalDateTime.parse(t.getStartDate(), formatter);
//            LocalDateTime endDate = calculateEndDate(t.getStartDate(), t.getDuree());
//
//            stb.append(" ID_task: ").append(t.getId()).append("\n")
//              .append(" Task: ").append(t.getTaskString()).append("\n")
//              .append(" Assigned to: ").append(workersString).append("\n")
//              .append(" Start Date: ").append(startDate.format(formatter)).append("\n")
//              .append(" Duration: ").append(formatDuration(t.getDuree())).append("\n")
//              .append(" End Date: ").append(endDate.format(formatter)).append("\n")
//              .append("-----------------------------------------------------------------\n");
//        }
//
//        return sb.toString();
//    }
	

} 