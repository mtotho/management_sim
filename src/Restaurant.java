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
	private HashMap<Task, Customer> task2customer;
	public DBmapper db;
	public StateGame sg;
	
	public List<Items_model> menu;
	public int last_ms;

	//The most previous delta
	//public int delta;
	public List<Inventory_model> inventory;
	//public List<Transactions_model> transactions;
	public List<Restaurant_model> restaurantData;
	public Player_model player;
	public ArrayList<ArrayList> orders;
	public Items_model item;
	public double money;


	public int customer_timer;

	private Employee e1;
	private Employee e2;;

	//Constructor()

	public Restaurant(DBmapper db){
		last_ms=0;
		//initialize timer object which will keep track of and organize time
		timer = new Time();

		this.db = db;


		employees = new ArrayList<Employee>();
		customers = new ArrayList<Customer>();
		customer2Task = new HashMap<Customer, Task>();

		task2customer = new HashMap<Task, Customer>();
		
	

		scheduler = new Scheduler();

		customer_timer=0;

		newGame();

	}//end: Constructor()

	public void newGame(){

		timer.setTime(0);

		menu = db.select(new Items_model());
		inventory = db.select(new Inventory_model());

		employees.clear();
		customers.clear();
		customer2Task.clear();
		task2customer.clear();
		scheduler = new Scheduler();

		e1 = new Employee();
		e2 = new Employee();

		e1.setLocation(Locations.REGISTER);
		e2.setLocation(Locations.KITCHEN);
		//e2.setLocation(Locations.MENSROOM);

		e1.setDuty(TaskType.CASHIER);
		e2.setDuty(TaskType.KITCHEN);

		employees.add(e1);
		employees.add(e2);

		//employees.add(e2);

		scheduler = new Scheduler();

		//System.out.println(menu.toString());

		orders = new ArrayList<ArrayList>();

	}


	

	public void updateTime(int delta){
		timer.addMilliSecond(delta);
	}

	public void update(){

		int delta = timer.getMilliSeconds()-last_ms;
		//add ms to timer object
		//timer.addMilliSecond(delta);





		customer_timer+=delta;
		double mod = Math.random();

		//System.out.println(mod);
		if(mod>0.45){
			customer_timer=0;

			if(!sg.waitline.isFull() && !foodline.isFull()){
				Customer cust = new Customer(this, menu, inventory);

				cust.setWayPoint(Locations.FOODLINE);
				customers.add(cust);
			}

		}

		if(counter % 20==0){

			
			//employees.get(1).setWayPoint(Locations.RANDOM);
		}	


		if(foodline.hasNext()){
			if(!foodline.isHelped()){
				if(foodline.atStart()){
					Customer cust = foodline.getNext();


					Task cashierTask = new Task(3000, TaskType.CASHIER, Locations.REGISTER, "Taking order");

					orders.add(cust.giveOrder());
					//System.out.println(orders.toString());
					for(int i = 0; i < orders.get(orders.size()-1).size(); i++){

							//System.out.println(orders.get(orders.size()-1).get(i) + " i: " +i);
							String item = orders.get(orders.size()-1).get(i).toString();
							//System.out.println("ITEM STRING: " + item);
							int id = item.charAt(0) - 48;
							//System.out.println(id + "=ITEM ID");

							inventory.get(id).setQuantity(inventory.get(id).getQuantity() -1);
							//item = orders.get(orders.size()-1).get(i));
							//System.out.println(item.getID());

							//inventory.get(orders.get(orders.size()-1).get(i).getID()).setQuantity(inventory.get(i).getQuantity()-1);

					} 

					customer2Task.put(cust, cashierTask);
					task2customer.put(cashierTask, cust);
					scheduler.addTask(cashierTask);
				}else{
					foodline.advanceLine();
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
						
					if(task2customer.containsKey(tempTask)){
						Customer cust = task2customer.get(tempTask);

						if(tempTask.getDuty()==TaskType.CASHIER){
							Task kitchenTask = new Task(6500, TaskType.KITCHEN, Locations.KITCHEN, "Making food");
							
							//customer2Task.remove(cust);
							customer2Task.put(cust, kitchenTask);
							task2customer.put(kitchenTask, cust);

							cust.setWayPoint(Locations.WAITLINE);
							scheduler.addTask(kitchenTask);
						}
						else if(tempTask.getDuty()==TaskType.KITCHEN){
							sg.waitline.removeCustomer(cust.getGLCustomer());
							customer2Task.remove(cust);
							task2customer.remove(tempTask);
							cust.setWayPoint(Locations.PICKUPWINDOW);
							orders.remove(cust.getOrder());
								
						}
					}

				//	for( Customer cust : customer2Task.keySet()){
				//		if(customer2Task.get(cust)==tempTask){
							
						//}
						//System.out.println(cust.getLocation());
					//}
					scheduler.removeTask(tempTask);
					//System.out.println("-------------------");
				}

			}//end if employee not busy
		}//end for
		
		counter++;
		last_ms=timer.getMilliSeconds();
		
	}//end: Update();

	public void addCustomer(){
		Customer cust = new Customer(this, menu, inventory);
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

	//Load a game from the database
	public void loadGame(Player_model player){
		//default all the values
		newGame();

		this.player = player;
		inventory = db.selectData(new Inventory_model(), player);
		restaurantData = db.selectData(new Restaurant_model(), player);



	}

	public void updateDatabase(){

		db.updateInventory(inventory);
		db.updateRestaurant(restaurantData);

	}
}//end class restaurant