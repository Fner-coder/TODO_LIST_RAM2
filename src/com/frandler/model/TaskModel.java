package com.frandler.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.frandler.dateUtils.DateUtils;
import com.frandler.tache.Task;
import com.frandler.tache.Task.Priority;
import com.frandler.tache.Task.TaskStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class TaskModel{
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private DateUtils dateUtils = new DateUtils();	
	private ArrayList<Task> masterTaskList = new ArrayList<Task>();
	Task task = new Task();
	int compt = 1;
	
	private static final String FILE_PATH = "C:/Users/dell/TODO_LIST_RAM2/data/tasks.json";
	
	// SAVE ALL TASK IN JSON
	public int addTask(ArrayList<String> assignedTo, String taskString, int duration, String startAt) {
		int newId = compt++;
		Task newTask = new Task(newId, assignedTo, taskString, duration, startAt);
		File folder = new File("C:/Users/dell/TODO_LIST_RAM2/data");
	    if (!folder.exists()) {
	        folder.mkdirs();
	    }
	    try (FileWriter writer = new FileWriter(FILE_PATH)) {
	    	
	    	updateTaskStatusAndPriority(newTask);
	    	
	    	masterTaskList.add(newTask);
	        gson.toJson(masterTaskList, writer);
	    } catch (IOException e) {
	    }
		return newId;
	}

//======================================================================================    
    public String getTasks() {
        StringBuilder stb = new StringBuilder();

        for (Task t : masterTaskList) {

        	String workersString = String.join(", ", t.getAssignedTo());

            stb.append(" ID_task: ").append(t.getId()).append("\n")
              .append(" Priority: ").append(t.getPriority()).append("\n")
              .append(" Task: ").append(t.getTaskString()).append("\n")
              .append(" Assigned to: ").append(workersString).append("\n")
              .append(" Start Date: ").append(t.getStartDate()).append("\n")
              .append(" Duration: ").append(dateUtils.formatDuration(t.getDuree())).append("\n")
              .append(" End Date: ").append(dateUtils.formatDateTime(dateUtils.calculateEndDate(t.getStartDate(), t.getDuree()))).append("\n")
              .append(" Status: ").append(t.getStatus()).append("\n")
              .append("-----------------------------------------------------------------\n");
            
        }
        
        return stb.toString();
    }

    //========================================================================================
    public String getTaskById(int id) {	
   	 StringBuilder stb = new StringBuilder();

   	for(Task t: masterTaskList) {
              if (id == t.getId()) {
           	   String workersString = String.join(", ", t.getAssignedTo());

                  stb.append(" ID_task: ").append(t.getId()).append("\n")
                    .append(" Priority: ").append(t.getPriority()).append("\n")
                    .append(" Task: ").append(t.getTaskString()).append("\n")
                    .append(" Assigned to: ").append(workersString).append("\n")
                    .append(" Start Date: ").append(t.getStartDate()).append("\n")
                    .append(" Duration: ").append(dateUtils.formatDuration(t.getDuree())).append("\n")
                    .append(" End Date: ").append(dateUtils.calculateEndDate(t.getStartDate(), t.getDuree())).append("\n")
                    .append(" Status: ").append(t.getStatus()).append("\n")
                    .append("-----------------------------------------------------------------\n");
              }
          }
   	return stb.toString();
	}
   
//========================================================================================
    public String cancelTask(int id) {
 	   StringBuilder stb = new StringBuilder();
 	   for (Task t : masterTaskList) {
 		   TaskStatus status;
 		   Priority priority;
 		   
 			if (id == t.getId()) {
 				status = TaskStatus.Cancelled;
 				priority = Priority.NONE;
 				
 				t.setStatus(status);
 				t.setPriority(priority);
 				
 				String workersString = String.join(", ", t.getAssignedTo());

                 stb.append(" ID_task: ").append(t.getId()).append("\n")
                   .append(" Priority: ").append(priority).append("\n")
                   .append(" Task: ").append(t.getTaskString()).append("\n")
                   .append(" Assigned to: ").append(workersString).append("\n")
                   .append(" Start Date: ").append(t.getStartDate()).append("\n")
                   .append(" Duration: ").append(dateUtils.formatDuration(t.getDuree())).append("\n")
                   .append(" End Date: ").append(dateUtils.calculateEndDate(t.getStartDate(), t.getDuree())).append("\n")
                   .append(" Status: ").append(status).append("\n")
                   .append("-----------------------------------------------------------------\n");
             }
 			}
 	   return stb.toString();
    }
//========================================================================================
    public boolean removeTaskById(int id) {
    	for (Task t : masterTaskList) {
			if (id == t.getId()) {
				masterTaskList.remove(t);
		    	return true;
			}
		}
    	return false;
    }
//========================================================================================	
   public Priority PriorityOfTask(Task task) {
       LocalDateTime startDate = dateUtils.parseDateTime(task.getStartDate());
       TaskStatus status = task.getStatus();
       
       long hoursDiff = ChronoUnit.HOURS.between(LocalDateTime.now(), startDate);
       
       if (status == TaskStatus.To_do) {
    	   if (hoursDiff <= 24 && hoursDiff >= 0) {
        	   return Priority.HIGH;
           }
           else if(hoursDiff > 24 && hoursDiff <=48){
        	   return Priority.MEDIUM;
           }
           else if (hoursDiff > 48) {
               return Priority.LOW;
           }
       }
	   return Priority.NONE;
}
 //========================================================================================   
   public void updateTaskStatusAndPriority(Task task) {
		   LocalDateTime startDate = dateUtils.parseDateTime(task.getStartDate());
		   LocalDateTime endDate = dateUtils.calculateEndDate(task.getStartDate(), task.getDuree());
		   
		   TaskStatus status;
		   if (endDate.isBefore(LocalDateTime.now())) {
			   status = TaskStatus.Done;
		   }
		   else if(startDate.isAfter(LocalDateTime.now())){
			   status = TaskStatus.To_do;
		   }
		   else {
			   status = TaskStatus.In_progress;
		   }
		   task.setStatus(status);
		   Priority priority = PriorityOfTask(task);
		   task.setPriority(priority);
   }

//===============================================================================    
    
} 