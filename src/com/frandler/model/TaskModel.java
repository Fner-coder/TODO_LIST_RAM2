package com.frandler.model;

import java.util.ArrayList;

import com.frandler.tache.Task;

public class TaskModel{
	
	Task task = new Task();
	
	int compt = 1;
	ArrayList<Task> mastesTaskList = new ArrayList<Task>();
	
	public int addTask(ArrayList<String> assignedTo, String taskString, int duration) {
		int newId = compt++;
        Task newTask = new Task(newId, assignedTo, taskString, duration);
        mastesTaskList.add(newTask);
        
        return newId++;
    }
	
	private String formatDuration(int duration) {
	    int days = duration / (60 * 24);
	    int hours = (duration % (60 * 24)) / 60;
	    int minutes = duration % 60;
	    //int seconds = 0 % 60;
	    
	    StringBuilder result = new StringBuilder();
	    if (days > 0) 
	        result.append(days).append(days > 1 ? " days:" : " day:");
	    if (hours == 0) {
	    	//result.append(hours);
	    	result.append(String.format("%02dm", minutes));
	    }
	    else {
	    	result.append(String.format("%dh:%02dm", hours, minutes));
		}
	    
	    return result.toString();
	}

	public String getTasks() {
    	String stringbuilder = "";
    	
    	for (Task t : mastesTaskList) {
    		
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
    	    		"\n Worker(s): " + workersString +
    	    		"\n Duration: " + formatDuration(t.getDuree()) + "\n";
			stringbuilder += "-----------------------------------------------------------------\n";
    	}
    	
		return stringbuilder;
	}
	
} 