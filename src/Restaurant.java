//package restuarantsim;

import java.util.ArrayList;

public class Restaurant{

	private ArrayList<Employee> employees;
	private ArrayList<Customer> customers;
	private Scheduler scheduler;

	//Constructor()
	public Restaurant(){

		employees = new ArrayList<Employee>();
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

	public void Update(float delta){

		for(int i = 0; i < employees.size(); i++){

			if(employees.get(i).isBusy() != true){
				
				scheduler.findEmployeeTask(employees.get(i));

			}
			else{

				employees.get(i).doTask(delta);

			}


		}





	}
}