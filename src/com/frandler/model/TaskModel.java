package com.frandler.model;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TaskModel {
	
	Map<Integer, String> masterTache = new HashMap<>();
	int taskId = 0;
	Random random = new Random();
	
	public TaskModel() {
		// TODO Auto-generated constructor stub
	}
	
	public int generateRandomId(int min, int max) {
	    return random.nextInt(max - min) + min;
	}
	
	public int saveTaks(String task) {
	    int taskId = generateRandomId(100, 1000);
	    masterTache.put(taskId, task);
		return taskId;
	}

	public String getTasks() {
        
    	String stringbuiler;
    	stringbuiler = "\n--------------- List of Task(s) ----------------------------------\n";
    	for (Integer key : masterTache.keySet()) {
    	    stringbuiler +="Task : " + key + " -> " + masterTache.get(key)+"\n";
    	}
    	stringbuiler += "-----------------------------------------------------------------\n";
		return stringbuiler;
	}
    
	public String getTaskById(int idEntered) {
    	return masterTache.get(idEntered);
	}
	
	public String removeTaskById(int taskIdEntered) {
		return masterTache.remove(taskIdEntered);
	}

}
