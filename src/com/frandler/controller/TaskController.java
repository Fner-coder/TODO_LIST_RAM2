package com.frandler.controller;

import java.util.ArrayList;

import com.frandler.model.TaskModel;

public class TaskController {
	
	TaskModel tskmodel = new TaskModel();
	
	
	public TaskController() {
		// TODO Auto-generated constructor stub
	}
	
	 public String addTask(ArrayList<String> completeNameArrayList, String task, int duration) {
	        String errorString = "";
	        String tmpTaskString = task.trim();
	 
			if (tmpTaskString == null) {
				errorString= "No task added....";
			}
	        
	        tskmodel.addTask(completeNameArrayList, task, duration);
	        return errorString;
	        
	    }

	 public String getTasks() {
		 if (tskmodel.getTasks().isEmpty()) {
			 return "No task has been saved!\nChoose (1) in the menu to (+) ADD NEW TASK";
		 } 
		 return tskmodel.getTasks();
	}
//---------------------------------------------------------	
	
//	public String addTask(String task) {
//		String errorString = "";
//		String tmpTaskTrimmedString = task.trim();
//		
//		if (tmpTaskTrimmedString.isEmpty() || tmpTaskTrimmedString == null) {
//			errorString= "The task entered is invalid....";
//		}
//		tskmodel.saveTaks(tmpTaskTrimmedString);
//		return errorString;
//	}
	
//---------------------------------------------------------	
//	 public List<Tasks> getTasks() {
//		return tskmodel.getAllTasks();
//	 }

//---------------------------------------------------------	 
//	 public String getTaskById(int id) {
//		 String task = tskmodel.getTaskById(id);
//		 if (id < 0) {
//			 return "Invalid! ID must > 0)";
//		 }
//		 if (task == null) 
//		    return " not found";
//		 
//		 return task;
//	}
	 
//---------------------------------------------------------	 
//	 public String removeTaskById(int tmpId) {
//		 String errorString = "";
//		 
//			if (tskmodel.removeTaskById(tmpId) == null || tskmodel.removeTaskById(tmpId).isEmpty()) {
//				errorString = "The task " + tmpId +" not found....";
//			}
//			
//			if (tmpId < 0) {
//				errorString = "Invalid! ID must be >= 0";
//			}
//		return errorString;
//	}
}

