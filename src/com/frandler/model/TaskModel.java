package com.frandler.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.frandler.dateUtils.DateUtils;
import com.frandler.localDateAdapter.LocalDateTimeAdapter;
import com.frandler.tache.Task;
import com.frandler.tache.Task.Priority;
import com.frandler.tache.Task.TaskStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class TaskModel{
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private DateUtils dateUtils = new DateUtils();	
	private ArrayList<Task> masterTaskList = new ArrayList<Task>();
	Task task = new Task();
	int compt = 1;
	
	private static final String FILE_PATH = "C:/Users/dell/TODO_LIST_RAM2/data/tasks.json";
	
	// Constructor
    public TaskModel() {
        gson = new GsonBuilder()
        		.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
        		.setPrettyPrinting()
        		.create();
        
        //loadTasksFromJson();
    }
    
 //======================================================================================    
  	 private void saveTasksToJson() {
  		File folder = new File("C:/Users/dell/TODO_LIST_RAM2/data");
	    if (!folder.exists()) {
	        folder.mkdirs();
	    }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(masterTaskList, writer);
        } catch (IOException e) {
            System.err.println("❌ Writing to JSON error: " + e.getMessage());
        }
  	 }
//======================================================================================    
	public int addTask(ArrayList<String> assignedTo, String taskString, int duration, String startAt) {
		int newId = compt++;
		LocalDateTime endDate = endDateTime();
		Task newTask = new Task(newId, assignedTo, taskString, duration, startAt, endDate);
		
		updateTaskStatusAndPriority(newTask);
		masterTaskList.add(newTask);
		saveTasksToJson();
		
		return newId;
	}
//======================================================================================    
    public ArrayList<Task> loadTasksFromJson() {
    	File file = new File(FILE_PATH);
    	
    	if (!file.exists()) {
            return masterTaskList = new ArrayList<>();
        }
    	
    	try (FileReader reader = new FileReader(FILE_PATH)) {
    		
            Type listType = new TypeToken<ArrayList<Task>>() {}.getType();
            
            ArrayList<Task> tmpTasksFromFile = gson.fromJson(reader, listType);
            
            masterTaskList = new ArrayList<Task>();
            
            if (tmpTasksFromFile != null) {
	        	for(Task t : tmpTasksFromFile) {
	        		if (t != null) {
						updateTaskStatusAndPriority(t);
						masterTaskList.add(t);
					}
            	}
	        	
			}
            updateNextId();
            return masterTaskList;
            
        } catch (IOException e) {
        	 return masterTaskList = new ArrayList<Task>();
            
        }
    }
    
    public String listTaskLoadedFromJson() {
		ArrayList<Task> tasks = loadTasksFromJson();
		StringBuilder stb = new StringBuilder();
		if (tasks.isEmpty()) {
			return "tasks found!";
		}
		else {
			for(Task t : tasks) {
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
		}
		return stb.toString();
        
}
//====================================================================================== 
    private void updateNextId() {
        int maxId = 0;

        for (Task t : masterTaskList) {
            if (t.getId() > maxId) {
                maxId = t.getId();
            }
        }

        compt = maxId + 1;	
    }
//=======================================================================================
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
 //===============================================================================================
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
                    .append(" End Date: ").append(dateUtils.formatDateTime(dateUtils.calculateEndDate(t.getStartDate(), t.getDuree()))).append("\n")
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
    	   if (hoursDiff <= 24 && hoursDiff >= 0)  return Priority.HIGH;
           if(hoursDiff > 24 && hoursDiff <=48) return Priority.MEDIUM;
           if (hoursDiff > 48) return Priority.LOW;
       }
       
	   return Priority.NONE;
   }
 //========================================================================================   
   public void updateTaskStatusAndPriority(Task task) {
	   LocalDateTime startDate = dateUtils.parseDateTime(task.getStartDate());
	   LocalDateTime endDate = task.getEndDate();
	    // Vérifier la startDate
	    if (task.getStartDate() == null || task.getStartDate().isBlank()) {
	        task.setStatus(TaskStatus.To_do);
	        task.setPriority(Priority.NONE);
	        return;
	    }
//	    // Si startDate est invalide => tâche mise en "To_do"
	    if (startDate == null) {
	        task.setStatus(TaskStatus.To_do);
	        task.setPriority(Priority.NONE);
	        return;
	    }
	    
	 // Si endDate provenant du JSON est NULL, on le recalcule
	    if (endDate == null) {
	        endDate = dateUtils.calculateEndDate(task.getStartDate(), task.getDuree());
	        task.setEndDate(endDate);
	    }

	    // Vérifier que la durée est valide
	    if (task.getDuree() <= 0) {
	        task.setStatus(TaskStatus.To_do);
	        task.setPriority(Priority.NONE);
	        return;
	    }

	    // Si endDate impossible à calculer => fallback
	    if (endDate == null) {
	        task.setStatus(TaskStatus.To_do);
	        task.setPriority(Priority.NONE);
	        return;
	    }
	    
	    if (task.getEndDate() == null) {
	        LocalDateTime newEndDate = dateUtils.calculateEndDate(task.getStartDate(), task.getDuree());
	        task.setEndDate(newEndDate);
	    }

	    TaskStatus status;
	    if (endDate.isBefore(LocalDateTime.now())) {
	        status = TaskStatus.Done;
	    }
	    else if (startDate.isAfter(LocalDateTime.now())) {
	        status = TaskStatus.To_do;
	    }
	    else {
	        status = TaskStatus.In_progress;
	    }

	    task.setStatus(status);
	    task.setPriority(PriorityOfTask(task));
	}
   
//===============================================================================    

   public LocalDateTime endDateTime() {
	   LocalDateTime endDate = dateUtils.calculateEndDate(task.getStartDate(), task.getDuree());
	return endDate;
   }
//===============================================================================    

   public ArrayList<Task> getMasterTaskList() {
	   return masterTaskList;
   }

    
} 