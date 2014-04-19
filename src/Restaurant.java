package mftoth.restaurantsim.logic;
import mftoth.restaurantsim.ogl.*;
import pjwelch.restaurantsim.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Restaurant{

	private ArrayList<Employee> employees;
	private ArrayList<Customer> customers;
	private Scheduler scheduler;
	public Time timer;
	private int counter;
	private Foodline foodline;
	private HashMap<Customer, Task> customer2Task;
	public DBmapper db;
	public StateGame sg;
	public List<Items_model> menu;
	//Constructor()

	public Restaurant(DBmapper db){

		//initialize timer object which will keep track of and organize time
		timer = new Time();

		this.db = db;

		menu = db.select(new Items_model());

		employees = new ArrayList<Employee>();
		customers = new ArrayList<Customer>();
		customer2Task = new HashMap<Customer, Task>();

		//Customer c1 = new Customer();
		//c1.setWayPoint(Locations.FOODLINE);
		//customers.add(c1);
		//Scheduler taskQueue = new Scheduler;

		Employee e1 = new Employee();
		Employee e2 = new Employee();
		//Employee e2 = new Employee();

		e1.setLocation(Locations.REGISTER);
		e2.setLocation(Locations.KITCHEN);
		//e2.setLocation(Locations.MENSROOM);

		e1.setDuty(TaskType.CASHIER);
		e2.setDuty(TaskType.KITCHEN);
	
		employees.add(e1);
		employees.add(e2);
		//employees.add(e2);

		scheduler = new Scheduler();

	}//end: Constructor()

	//loop(): main restaurant loop
	public void loop(){

	}//end: loop()

	public void update(int delta){

		//add ms to timer object
		timer.addMilliSecond(delta);


		if(counter % 250==0){

			Customer cust = new Customer(this, menu);
			cust.setWayPoint(Locations.FOODLINE);
			customers.add(cust);

			//employees.get(1).setWayPoint(Locations.RANDOM);
		}	


		if(foodline.hasNext()){
			if(!foodline.isHelped()){
				if(foodline.atStart()){
					Customer cust = foodline.getNext();

					Task cashierTask = new Task(4000, TaskType.CASHIER, Locations.REGISTER, "Taking order");
					customer2Task.put(cust, cashierTask);
					scheduler.addTask(cashierTask);
				}
			}
			else{
				Customer cust = foodline.getNext();

				if(!customer2Task.get(cust).isTimeLeft()){
					foodline.next();
				}
			}
		}

		//System.out.privatentln("Has next Customer?: " + );

		//System.out.println(timer.getSeconds() + " seconds");

		//Loop through each employee and update task progress
		for(int i = 0; i < employees.size(); i++){

			Employee emp = employees.get(i);
			//Employee is not busy
			if(emp.isBusy() != true){
				

				//employee is not busy, look for next task
				Task task = scheduler.getNextTask(emp.getDuty());

				if(task!=null)
					emp.setTask(task); //set task

			//Employee is working on a task
			}else{
				//consume time on task. This will automatically set employee to not busy if the task runs out
				Task tempTask = emp.doTask(delta);
				if(tempTask!=null  && !tempTask.isTimeLeft()){
					for( Customer cust : customer2Task.keySet()){
						if(customer2Task.get(cust)==tempTask){
							if(tempTask.getDuty()==TaskType.CASHIER){
								Task kitchenTask = new Task(5000, TaskType.KITCHEN, Locations.KITCHEN, "Making food");
								
								//customer2Task.remove(cust);
								customer2Task.put(cust, kitchenTask);
								cust.setWayPoint(Locations.WAITLINE);
								scheduler.addTask(kitchenTask);
							}
							else if(tempTask.getDuty()==TaskType.KITCHEN){
								sg.waitline.removeCustomer(cust.getGLCustomer());
								customer2Task.remove(cust);
								cust.setWayPoint(Locations.EXIT);
								
							}
						}
						//System.out.println(cust.getLocation());
					}
					scheduler.removeTask(tempTask);
					//System.out.println("-------------------");
				}

			}//end if employee not busy
		}//end for
		
		counter++;
		
	}//end: Update();

	public void addCustomer(){
		Customer cust = new Customer(this, menu);
		cust.setWayPoint(Locations.RANDOM);
		customers.add(cust);
	}

	public Time getTimer(){
		return timer;	
	}

	public Scheduler getScheduler(){
		return scheduler;
	}

	public ArrayList<Customer> getCustomers(){
		return customers;
	}

	public void setFoodline(Foodline fl){
		foodline=fl;
	}

	public void setStateGame(StateGame sg){
		this.sg=sg;
	}

	public ArrayList<Employee> getEmployees(){
		return employees;
	}

}//end class restaurant