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
	
	// Méthode pour formater la durée (votre méthode existante améliorée)
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
    
    
//    // Méthode pour afficher les détails complets de la tâche
//    public String getTasks() {
//    	String task;
//    	String startAt = null;
//    	int durationInMinutes = 0;
//    	String stringbuilder = "";
//    	
//    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime startDate = LocalDateTime.parse(startAt, formatter);
//        LocalDateTime endDate = calculateEndDate(startAt, durationInMinutes);
//    	
//    	for (Task t : mastesTaskList) {
//    		String workersString = "";
//    		ArrayList<String> assignedTo = t.getAssignedTo();
//    		
//    		for(int i = 0; i < assignedTo.size(); i++) {
//				workersString += assignedTo.get(i);
//				 if (i < assignedTo.size() - 1) {
//					 workersString += ", ";
//	                }
//			}
//    		
//    		stringbuilder +=" ID_task: " + t.getId() +
//					"\n Task: " + t.getTaskString() +
//					"\n Assigned to: " + t.getTaskString() +
//					"\n Start Date: " + startDate.format(formatter) +
//					"\n Duration: " + formatDuration(t.getDuree()) +
//					"\n End Date: " + calculateEndDate(startAt, durationInMinutes) +"\n";
//    		stringbuilder += "-----------------------------------------------------------------\n";
//    	}
//        
//        return stringbuilder ;
//    }
//    

    public String getTasks() {
        if (mastesTaskList == null || mastesTaskList.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Task t : mastesTaskList) {
            // Construire la liste des workers avec String.join (plus élégant)
            String workersString = String.join(", ", t.getAssignedTo());

            // Utiliser les données de la tâche
            LocalDateTime startDate = LocalDateTime.parse(t.getStartDate(), formatter);
            LocalDateTime endDate = calculateEndDate(t.getStartDate(), t.getDuree());

            sb.append(" ID_task: ").append(t.getId()).append("\n")
              .append(" Task: ").append(t.getTaskString()).append("\n")
              .append(" Assigned to: ").append(workersString).append("\n")
              .append(" Start Date: ").append(startDate.format(formatter)).append("\n")
              .append(" Duration: ").append(formatDuration(t.getDuree())).append("\n")
              .append(" End Date: ").append(endDate.format(formatter)).append("\n")
              .append("-----------------------------------------------------------------\n");
        }

        return sb.toString();
    }
	
//	public String getTasks() {
//    	String stringbuilder = "";
//    	String patternString = "yyyy-MM-dd HH:mm";
//    	String startAt = null;
//    	int durationInMinutes = 0;
//    	
// 	    DateTimeFormatter Dateformatter = DateTimeFormatter.ofPattern(patternString);
// 	    LocalDateTime startDate = LocalDateTime.parse(startAt, Dateformatter);
// 	    LocalDateTime endDate = calculateEndDate(startAt, durationInMinutes);
// 	   
//    	for (Task t : mastesTaskList) {
//    		
//    		String workersString = "";
//    		ArrayList<String> assignedTo = t.getAssignedTo();
//    		
//    		//LocalDateTime startDate = LocalDateTime.parse(t.getStartDate(), Dateformatter);
//    		
//			for(int i = 0; i < assignedTo.size(); i++) {
//				workersString += assignedTo.get(i);
//				 if (i < assignedTo.size() - 1) {
//					 workersString += ", ";
//	                }
//			}
//			stringbuilder +=" ID_task: " + t.getId() +
//    	    		"\n Task: " + t.getTaskString() +
//    	    		"\n Assigned to: " + workersString +
//    	    		"\n Duration: " + formatDuration(t.getDuree()) +
//    	    		"\n Start date: " + startDate + 
//					"\n End date: " + calculateEndDate(formatDuration(t.getDuree()), durationInMinutes) + "\n";
//			
//			stringbuilder += "-----------------------------------------------------------------\n";
//    	}
//    	
//		return stringbuilder;
//	}
	
} 