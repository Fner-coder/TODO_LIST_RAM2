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
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	//private static final String String = null;
	private DateUtils dateUtils = new DateUtils();	
	private ArrayList<Task> masterTaskList = new ArrayList<Task>();
	Task task = new Task();
	int compt = 1;
	
	private static final String FILE_PATH = "C:/Users/dell/TODO_LIST_RAM2/data/tasks.json";
	
	
//	public int addTask(ArrayList<String> assignedTo, String taskString, int duration, String startAt) {
//		int newId = compt++;
//        Task newTask = new Task(newId, assignedTo, taskString, duration, startAt);
//        masterTaskList.add(newTask);
//        return newId;
//    }
	
	// SAVE ALL TASK IN JSON
	public int addTask(ArrayList<String> assignedTo, String taskString, int duration, String startAt) {
		int newId = compt++;
		Task newTask = new Task(newId, assignedTo, taskString, duration, startAt);
		File folder = new File("C:/Users/dell/TODO_LIST_RAM2/data");
	    if (!folder.exists()) {
	        folder.mkdirs();
	    }
	    try (FileWriter writer = new FileWriter(FILE_PATH)) {
	    	masterTaskList.add(newTask);
	        gson.toJson(masterTaskList, writer);
	    } catch (IOException e) {
	    }
		return newId;
	}
	
 // PRIORITY METHOD
   public Priority PriorityOfTask(Task task) {
       LocalDateTime startDate = dateUtils.parseDateTime(task.getStartDate());
       LocalDateTime now = LocalDateTime.now();
       TaskStatus status = task.getStatus();
       
       long hoursDiff = ChronoUnit.HOURS.between(now, startDate);
       
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
    
   public TaskStatus taskStatus(Task task) {
//	   for (Task t : masterTaskList) {
		   LocalDateTime startDate = dateUtils.parseDateTime(task.getStartDate());
		   LocalDateTime endDate = dateUtils.calculateEndDate(task.getStartDate(), task.getDuree());
		   LocalDateTime now = LocalDateTime.now();
		   
		   if (endDate.isBefore(now)) {
			   return TaskStatus.Done;
		   }
		   else if(startDate.isAfter(now)){
			   return TaskStatus.To_do;
		   }
		   else {
			   return TaskStatus.In_progress;
		   }
   }
// cancel task Method
//   public String cancelTask(int id) {
//	   String stringbuilder = "";
//	   for (Task t : masterTaskList) {
//			if (id == t.getId()) {
//				stringbuilder =" Task: " + t.getTaskString() +
//            			" Priority: " + PriorityOfTask(t.getStartDate())+
//       					"\n Assigned to: " + String.join(", ", t.getAssignedTo()) +
//       					"\n Start Date: " + t.getStartDate() +
//       					"\n Duration: " + dateUtils.formatDuration(t.getDuree()) +
//       					"\n End Date: " + dateUtils.calculateEndDate(t.getStartDate(), t.getDuree()) +
//       					"\n Status: " + TaskStatus.Cancelled + "\n";
//               }
//           		stringbuilder += "-----------------------------------------------------------------\n";
//			}
//	   return stringbuilder;
//   }
// GET LIST OF TASKS METHOD ==============================================================
    public String getTasks() {
    	String stringbuilder = "";
    	for (Task t : masterTaskList) {
    		
    		TaskStatus status = taskStatus(t);
    	    Priority priority = PriorityOfTask(t);
    		
    	    t.setStatus(status);
    	    t.setPriority(priority);
    	    
//			- ArrayList<String> assignedTo = t.getAssignedTo();
//    		for(int i = 0; i < assignedTo.size(); i++) {
//				workersString += assignedTo.get(i);
//				 if (i < assignedTo.size() - 1) {
//					 workersString += ", ";
//	                }
//			}
//    	   2025-12-12 12:30 
//    	    2025-11-24 20:10
//    	    2025-11-24 20:07
    		// ameliorated version of listing people into the list "assignedTo" 
    		String workersString = "";
    		workersString = String.join(", ", t.getAssignedTo());

    		stringbuilder =" ID_task: " + t.getId() +  " - priority: " + t.getPriority() +
					"\n Task: " + t.getTaskString() +
					"\n Assigned to: " + workersString +
					"\n Start Date: " + t.getStartDate() +
					"\n Duration: " + dateUtils.formatDuration(t.getDuree()) +
					"\n End Date: " + dateUtils.calculateEndDate(t.getStartDate(), t.getDuree()) +
					"\n Status: " + t.getStatus()+ "\n";
    		stringbuilder += "-------------------------------------------";
    	}
    	
    	return stringbuilder;
    }
//  ======================================================================================     
    public String getTaskById(int id) {	
    	String stringbuilder = "";

    	for(Task t: masterTaskList) {   
    		String workersString = "";
    		
               if (id == t.getId()) {
            	   stringbuilder =" Task: " + t.getTaskString() +
            			"\n Priority: " + t.getPriority()+
       					"\n Assigned to: " + workersString +
       					"\n Start Date: " + t.getStartDate() +
       					"\n Duration: " + dateUtils.formatDuration(t.getDuree()) +
       					"\n End Date: " + dateUtils.calculateEndDate(t.getStartDate(), t.getDuree()) +
       					"\n Status: " + t.getStatus() + "\n";
               }
           		stringbuilder += "-----------------------------------------------------------------\n";
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
    
////  GET LIST OF TASKS METHOD with a better StringBuilder
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