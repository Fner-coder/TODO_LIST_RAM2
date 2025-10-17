package com.frandler.tache;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
	
	private int id;
	private int duration;
	private String taskString;
	private ArrayList<String> assignedTo = new ArrayList<String>();
	private String startAt;
//  
//    private String taskDescriptionString;
//    LocalDateTime fin = LocalDateTime.now();
    
//	public enum Priority {
//	    HIGH,   
//	    MEDIUM, 
//	    LOW     
//	}
//	
	public enum TaskStatus {
	    To_do,
	    In_progress,  
	    Terminate,
	    Done
	}



	public Task(ArrayList<String> assignedTo) {
		this.assignedTo = assignedTo;
	}

	public int getId() {
		return id;
	}

	public ArrayList<String> getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(ArrayList<String> assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskString() {
		return taskString;
	}

	public void setTaskString(String taskString) {
		this.taskString = taskString;
	}
	
	public int getDuree() {
		return duration;
	}

	public void setDuree(int duree) {
		this.duration = duree;
	}


	public Task() {
		// TODO Auto-generated constructor stub
	}
	 
	public Task(int id, ArrayList<String> assignedTo , String taskString, int duration, String startAt){
		this.id = id;
		this.assignedTo = assignedTo;
		this.taskString = taskString;
		this.duration = duration;
		this.startAt = startAt;
	}

	public String getStartDate() {
		return startAt;
	}

	public void setStartDate(String startAt) {
		this.startAt = startAt;
	}


}
