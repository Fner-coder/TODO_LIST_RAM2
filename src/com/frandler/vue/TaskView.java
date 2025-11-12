package com.frandler.vue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	    ArrayList<String> completeNameArrayList = new ArrayList<>();
	    int nbPeople;
	    String taskString = "";
	    String completeNameString = "";
	    int duration;
	    String startAt = null;
	   
	    
	    System.out.println("\n==== (+) NEW TASK ====");
	    System.out.print("New task : ");
	    taskString = mscanner.nextLine();

	    System.out.println("How much workers on task (" + taskString + ")?");
	    nbPeople = mscanner.nextInt();
	    mscanner.nextLine(); 
	    
	    while (nbPeople <= 0) {
	    	System.err.println("Error! Invalid number - Try again:");
	    	nbPeople = mscanner.nextInt();
		    mscanner.nextLine(); 
		}	    
//		for (int i = 0; i < nbPeople; i++) {
//	        System.out.print("------------------------------------------\nWorker(" + (i+1) + ") - Complete name: ");
//	        completeNameString = mscanner.nextLine();
//	        completeNameArrayList.add(completeNameString);
//		}
	    
	    for (int i = 0; i < nbPeople; i++) {
	    	boolean validName = false;
	    	
	    	while (!validName) {
	    		System.out.print("-------------------------------\nWorker(" + (i+1) + ") - Complete name: ");
	    		completeNameString = mscanner.nextLine().trim();
	    		
	    		if (completeNameString.isEmpty()) {
	                System.err.println("Error ! Name cannot be empty.\nPlease try again.");
	            } else {
	                validName = true;
	                completeNameArrayList.add(completeNameString);
	            }
	    	}
		}
	    
	    System.out.println("Duration task - " + taskString + " in minute ?");
	    duration = mscanner.nextInt();
//	    Duration duration;
	    mscanner.nextLine();
	    
	    System.out.print("Start date (format yyyy-MM-dd HH:mm) : ");
	    startAt = mscanner.nextLine();
	    
//	    LocalDateTime endDate = startAt.plus(duration);
	    var res = tskController.addTask(completeNameArrayList, taskString, duration, startAt);
	    if (!res.isEmpty()) {
	        System.err.println(res);
	    }
	}


//-----------------------------------------------------------------------------------------	
	public void getTasks() {
		System.out.println("\n==== TASK LIST====\n" + tskController.getTasks());
	}

//-----------------------------------------------------------------------------------------	
//	public void getTaskbyId() {
//	    try {
//	        System.out.print("------------------------------------------\nPlease enter ID of task : ");
//	        int idTaskInt = mscanner.nextInt();	    
//	    	mscanner.nextLine(); 
//
//	    	String task = tskController.getTaskById(idTaskInt);
//	        System.out.println("Task ("+ idTaskInt+ "): " + task);
//	    } catch (InputMismatchException e) {
//	        System.err.println("❌ ID task should be a number");
//	        mscanner.nextLine(); 
//	    }
//	}
	
//-----------------------------------------------------------------------------------------
//	public void removeTaskById() {
//		int idTaskInt = -1;
//		boolean delete = true;
//		
//		try {
//			System.out.print("------------------------------------------\nPlease enter ID of task : ");
//			idTaskInt = mscanner.nextInt();
//	    	
//			var err = tskController.removeTaskById(idTaskInt); 
//			if (err != "") {
//				System.err.println(err);
//				delete = false;
//			}
//
//			if (delete)
//				System.out.println("Task "+ "< " + idTaskInt + " >" + " has been deleted succesfully");
//
//		} catch (InputMismatchException e) {
//			System.err.println("ID task should be a number");
//			mscanner.nextLine();
//		}
//		
//	}

//-----------------------------------------------------------------------------------------	
	public void getMenu() {
		
		  // Menu simple
      boolean running = true;
      int choice = 0;
    	  while (running) {
              try {
            	  System.out.println("\n=== TASK MANAGEMENT ===");
                  System.out.println("1. (+) NEW Task");
                  System.out.println("2. LIST ALL Tasks");
                  System.out.println("3. LIST TASK BY ID");
                  System.out.println("4. DELETE Task");
                  System.out.println("5. LEAVE");
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
                    	  //getTaskbyId();
                            break;
                      case 4:
                    	  //removeTaskById();
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
