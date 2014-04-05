package mftoth.restaurantsim.logic;

import java.util.ArrayList;

public class Restaurant{

	private ArrayList<Employee> employees;
	private ArrayList<Customer> customers;
	private Scheduler scheduler;
	public Time timer;
	private int counter;
	//Constructor()
	public Restaurant(){

		//initialize timer object which will keep track of and organize time
		timer = new Time();

		employees = new ArrayList<Employee>();
		customers = new ArrayList<Customer>();

		//Customer c1 = new Customer();
		//c1.setWayPoint(Locations.FOODLINE);
		//customers.add(c1);
		//Scheduler taskQueue = new Scheduler;

		Employee e1 = new Employee();
		Employee e2 = new Employee();

	
		employees.add(e1);
		employees.add(e2);

		scheduler = new Scheduler();

	}//end: Constructor()

	//loop(): main restaurant loop
	public void loop(){

	}//end: loop()

	public void update(int delta){

		//add ms to timer object
		timer.addMilliSecond(delta);


		if(counter % 500==0){
			Customer cust = new Customer(this);
			cust.setWayPoint(Locations.RANDOM);
			customers.add(cust);
		}

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
				emp.doTask(delta);

			}//end if employee not busy
		}//end for
		
		counter++;
		
	}//end: Update();

	public void addCustomer(){
		Customer cust = new Customer(this);
		cust.setWayPoint(Locations.RANDOM);
		customers.add(cust);
	}

	public Time getTimer(){
		return timer;	
	}

	public ArrayList<Customer> getCustomers(){
		return customers;
	}

	public ArrayList<Employee> getEmployees(){
		return employees;
	}

}//end class restaurant