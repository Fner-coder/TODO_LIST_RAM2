package com.frandler.vue;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.frandler.controller.TaskController;
import com.frandler.dateUtils.DateUtils;
import com.frandler.model.TaskModel;

public class TaskView {

	TaskController tskController = new TaskController();
	DateUtils dateUtils = new DateUtils();	
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
	    int duration = 0;
	    String startDate;
	    
	    System.out.println("\n==== (+) NEW TASK ====");
	    while (taskString.isEmpty()) {
	    	System.out.print("New task : ");
		    taskString = mscanner.nextLine();
		}

	    System.out.println("How much workers on task (" + taskString + ")?");
	    nbPeople = mscanner.nextInt();
	    mscanner.nextLine(); 
	    
	    while (nbPeople <= 0) {
	    	System.err.println("Error! Invalid number - Try again:");
	    	nbPeople = mscanner.nextInt();
		    mscanner.nextLine(); 
		}    	    
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
	    
	    System.out.println("Duration of task - ('" + taskString + "') in minute ?");
	    duration = mscanner.nextInt();
	    try {
	    	while (duration <= 0) {
		    	System.err.print("Invalid number..."); System.out.println("Try again :");
			    duration = mscanner.nextInt();
			    mscanner.nextLine();
			}
		} catch (InputMismatchException e) {
			System.err.println("❌ Duration should be a number");
			mscanner.nextLine();
		}
	    
	    mscanner.nextLine();
	    
		while (true) {
			System.out.print("Start date (dd-MM-yyyy HH:mm): ");
		    startDate = mscanner.nextLine();

		    if (startDate == null || startDate.isBlank()) {
		        System.out.println("Start date requiered: ");
		        continue;
		    }
		    try {
		        LocalDateTime.parse(startDate, dateUtils.DATE_TIME_FORMATTER);
		        break;
		    } catch (DateTimeParseException e) {
		        System.err.println("❌ Date format invalid! Try again...");
		    }
		}
		
	    var res = tskController.addTask(completeNameArrayList, taskString, duration, startDate);
	    if (!res.isEmpty()) {
	        System.err.println(res);
	    }
	}


//-----------------------------------------------------------------------------------------	
	public void getTasks() {
		System.out.println("\n==== TASK LIST====\n" + tskController.getTasks());
	}

//-----------------------------------------------------------------------------------------	
	public void getTaskbyId() {
	    try {
	        System.out.print("------------------------------------------\nPlease enter ID of task : ");
	        int idTaskInt = mscanner.nextInt();	    
	    	mscanner.nextLine(); 

	    	System.out.println(tskController.getTaskById(idTaskInt));
	        
	    } catch (InputMismatchException e) {
	        System.err.println("❌ ID task should be a number");
	        mscanner.nextLine(); 
	    }
	}
//	2025-11-15 12:30
//-----------------------------------------------------------------------------------------
	public void removeTaskById() {
		int idTaskInt = -1;
		try {
			System.out.print("------------------------------------------\nPlease enter ID of task : ");
			idTaskInt = mscanner.nextInt();
	    	
			var err = tskController.removeTaskById(idTaskInt); 
			if (err != "") {
				System.err.println(err);
			}

		} catch (InputMismatchException e) {
			System.err.println("ID task should be a number");
			mscanner.nextLine();
		}
		
	}
	
//	public void cancelTask() {
//		int idTaskInt = -1;
//		try {
//			System.out.print("------------------------------------------\nPlease enter ID of task : ");
//			idTaskInt = mscanner.nextInt();
//	    	
//			var err = tskController.cancelTask(idTaskInt); 
//			if (err != "") {
//				System.err.println(err);
//			}
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
                  System.out.println("4. CANCEL a Task");
                  System.out.println("5. DELETE Task");
                  System.out.println("6. LEAVE");
                  System.out.print("Your Choice : ");
                  
                  choice = mscanner.nextInt();
                  mscanner.nextLine(); 
                  
                  switch (choice) {
                      case 1:
                      	addTask();
                          break;
                      case 2:
                    	  try {
                              getTasks();
                          } catch (Exception e) {
                              System.err.println("Error while listing tasks: " + e.getMessage());
                          }
                          break;
                      case 3:
                    	  getTaskbyId();
                            break;
                      case 4:
                    	  //cancelTask();
                    	  break;
                      case 5:
                    	  removeTaskById();
                          break;
                      case 6:
                          running = false;
                          System.out.println("Bye Bye! !");
                          break;
                      default:
                          System.err.println("Invalid choice!");
                  }
              } catch (InputMismatchException e) {
                  System.err.println("Invalid input! Please enter a number between 1 and 5");
                  mscanner.nextLine(); // Important : cleaning buffer
              } catch (Exception e) {
                  System.err.println("Error: " + e.getMessage());
                  e.printStackTrace(); // debug
              }
          } // fin while
      mscanner.close();
      	
	}
}
