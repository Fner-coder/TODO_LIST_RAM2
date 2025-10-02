package com.frandler.vue;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.frandler.controller.TaskController;

public class TaskView {

	TaskController tskController = new TaskController();
	private Scanner mscanner = new Scanner(System.in);
	public TaskView() {
		// TODO Auto-generated constructor stub
	}

//-----------------------------------------------------------------------------------------	
	public void addTask() {
		String taskString ="";
		System.out.print("------------------------------------------\nAdd new task :  ");
		taskString = mscanner.nextLine();
		var res = tskController.addTask(taskString);
		if(res!="") 
			System.err.println(res);
		
	}

//-----------------------------------------------------------------------------------------	
	public void getTasks() {
		System.out.println(tskController.getTasks());
	}

//-----------------------------------------------------------------------------------------	
	public void getTaskbyId() {
	    try {
	        System.out.print("------------------------------------------\nPlease enter ID of task : ");
	        int idTaskInt = mscanner.nextInt();
	        
	        String tmpString = String.valueOf(idTaskInt);
	    	
	    	while (tmpString.length() > 3) {
	    	    System.out.println("ID must contain at least 3 characters\nTry again:");
	    	    idTaskInt = mscanner.nextInt();
	    	    
	    	    mscanner.nextLine(); 
	    	    tmpString = String.valueOf(idTaskInt); // on met à jour pour re-tester
	    	}
	    	
	        String task = tskController.getTaskById(idTaskInt);
	        System.out.println("Task ("+ idTaskInt+ "): " + task);
	    } catch (InputMismatchException e) {
	        System.err.println("❌ ID task should be a number");
	        mscanner.nextLine(); 
	    }
	}
	
//-----------------------------------------------------------------------------------------
	public void removeTaskById() {
		int idTaskInt = -1;
		boolean delete = true;
		
		try {
			System.out.print("------------------------------------------\nPlease enter ID of task : ");
			idTaskInt = mscanner.nextInt();
			
			String taskString = tskController.getTaskById(idTaskInt);
			String tmpString = String.valueOf(idTaskInt);
	    	
	    	while (tmpString.length() > 3) {
	    	    System.out.println("** At least 3 characters required - \nTry again:");
	    	    idTaskInt = mscanner.nextInt();
	    	    
	    	    mscanner.nextLine(); 
	    	    tmpString = String.valueOf(idTaskInt); // mise a jour pour re-tester
	    	}
	    	
			var err = tskController.removeTaskById(idTaskInt); 
			if (err != "") {
				System.err.println(err);
				delete = false;
			}

			if (delete)
				System.out.println("Task "+ "< " + taskString + " >" + " has been deleted succesfully");

		} catch (InputMismatchException e) {
			System.err.println("ID task should be a number");
			mscanner.nextLine();
		}
		
	}

//-----------------------------------------------------------------------------------------	
	public void getMenu() {
		
		  // Menu simple
      boolean running = true;
      int choice = 0;
    	  while (running) {
              try {
            	  System.out.println("\n=== TASK MANAGEMENT ===");
                  System.out.println("1. Add Task");
                  System.out.println("2. List all Tasks");
                  System.out.println("3. List task by ID");
                  System.out.println("4. Delete Task");
                  System.out.println("5. Leave");
                  System.out.print("Your Choice : ");
                  
                  choice = mscanner.nextInt();
                  mscanner.nextLine(); 
                  
                  switch (choice) {
                      case 1:
                      	addTask();
                          break;
                      case 2:
                      	getTasks();
                          break;
                      case 3:
                    	  getTaskbyId();
                            break;
                      case 4:
                    	  removeTaskById();
                    	  break;
                      case 5:
                          running = false;
                          System.out.println("Bye Bye! !");
                          break;
                      default:
                          System.err.println("Invalid choice!");
                  }
			}catch (Exception e) {
				System.err.println("Invalid choice!\nChoose between 1....5");
				mscanner.nextLine();
			}
          } // fin while
      mscanner.close();
      	
	}
}
