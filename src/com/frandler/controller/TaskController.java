package com.frandler.controller;

import com.frandler.model.TaskModel;

public class TaskController {
	TaskModel tskmodel = new TaskModel();
	public TaskController() {
		// TODO Auto-generated constructor stub
	}

//---------------------------------------------------------	
	public String addTask(String task) {
		String errorString = "";
		String tmpTaskTrimmedString = task.trim();
		
		if (tmpTaskTrimmedString.isEmpty() || tmpTaskTrimmedString == null) {
			errorString= "The task entered is invalid....";
		}
		tskmodel.saveTaks(tmpTaskTrimmedString);
		return errorString;
	}
	
//---------------------------------------------------------	
	 public String getTasks() {
		return tskmodel.getTasks();
	 }

//---------------------------------------------------------	 
	 public String getTaskById(int id) {
		 String task = tskmodel.getTaskById(id);
		 if (id <= 0) {
			 return "Invalid! ID must > 0)";
		 }
		 if (task == null) 
		    return " not found";
		 
		 return task;
	}
	 
//---------------------------------------------------------	 
	 public String removeTaskById(int tmpId) {
			String errorString = "";
			String tmpTaskString = tskmodel.getTaskById(tmpId);
			
			if (tmpTaskString == null) {
				errorString= "The task " + tmpId +" not found....";
			}
			
			if (tmpId <= 0) {
				errorString = "Invalid! ID must > 0";
			}
			tskmodel.removeTaskById(tmpId);
			return errorString;
	}
}

