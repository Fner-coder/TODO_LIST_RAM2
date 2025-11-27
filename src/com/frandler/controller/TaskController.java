package com.frandler.controller;

import java.util.ArrayList;

import com.frandler.model.TaskModel;

public class TaskController {
	
	TaskModel tskmodel = new TaskModel();
	
	
	public TaskController() {
		// TODO Auto-generated constructor stub
	}
	
	 public String addTask(ArrayList<String> completeNameArrayList, String task, int duration, String startAt) {
	        String errorString = "";
	        String tmpTaskString = task.trim();
	        
			if (tmpTaskString.isEmpty()) {
				return "Error ! No task added.... Try again";
			}
			if (completeNameArrayList.isEmpty()) {
				return "Error ! No worker(s) assigned to this task....Try again";
			}
//			================================================
		    for (String name : completeNameArrayList) {
		        if (name == null || name.trim().isEmpty()) {
		            return "Error ! All workers must have a complete name....Try again";
		        }
		    }
//			================================================
	        tskmodel.addTask(completeNameArrayList, task, duration, startAt);
	        return errorString;
	        
	    }

	 public String getTasks() {
		 String tasks = tskmodel.getTasks();
		    
		    if (tasks == "" || tasks.isEmpty()) {
		        return "No task has been saved!\nChoose (1) in the menu to (+) ADD NEW TASK";
		    }
		    return tasks;
	}

//---------------------------------------------------------	 
	 public String getTaskById(int id) {
		 String task = tskmodel.getTaskById(id);
		 if (id < 0) {
			 return " ID (< 0) Invalid !";
		 }
		 if (task == "") 
		    return " Task "+ id + " not found";
		 return task;
	}
	 
//---------------------------------------------------------	 
	 public String removeTaskById(int tmpId) {
		 String errorString = "Task("+ tmpId +") Succesfully delete";
			if (!tskmodel.removeTaskById(tmpId)){
				errorString = "The task " + tmpId + " not found";
			}
			if (tmpId < 0) {
				errorString = "ID Invalid!";
			}
		return errorString;
	}
	 
//	 ---------------------------------------------------------------------
	 public String cancelTask(int tmpId) {
		 String errorString = "Task("+ tmpId +") cancelled";
			if (tskmodel.cancelTask(tmpId) == null){
				errorString = "The task " + tmpId + " not found";
			}
			if (tmpId < 0) {
				errorString = "ID Invalid!";
			}
		return errorString;
	}
	 
//	 public String isValidDate(String tmpDate) {
//		 boolean isValidate = tskmodel.isValidDate(tmpDate);
//		 while (!isValidate) {
//			 return "Date format invalid!";
//		 }
//		 return tmpDate;
//		 if (isValidate) {
//			return tmpDate;
//		}
//		 else {
//			return "Date format invalid!";
//		}
//	 }

}

