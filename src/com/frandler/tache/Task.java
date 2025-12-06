package com.frandler.tache;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
	
	public enum TaskStatus {
	    To_do,
	    In_progress,  
	    Cancelled,
	    Done 
	}  
	
	public enum Priority {
	    HIGH,   
	    MEDIUM, 
	    LOW,
	    NONE
	}
	

	private int id;
	private int duration;
	private String taskString;
	private ArrayList<String> assignedTo = new ArrayList<String>();
	private String startDate;
	private TaskStatus status;
	private Priority priority;
	private LocalDateTime endDate;
	

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public LocalDateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate ;
	}
	
	public Task() {
		// TODO Auto-generated constructor stub
	}
	
	public Task(int id, ArrayList<String> assignedTo , String taskString, int duration, String startAt, LocalDateTime enDate){
		this.id = id;
		this.assignedTo = assignedTo;
		this.taskString = taskString;
		this.duration = duration;
		this.startDate = startAt;
		this.endDate = enDate;
	}


}
