//package restuarantsim;

public class Employee{

	private boolean busy;
	private Task active_task;
	private float hoursWorked;
	private int hours;



	//Constructor()
	public Employee(){

	}//end: Constructor()

	//setTask(): assign current task
	public void setTask(Task task){

		//If employee is busy, check whether the new task is more important
		if(busy){
			if(task.getPreemption() && task.getPriority()>active_task.getPriority()){
				active_task=task;
			}
		}else{
			active_task=task;
		}
		
	}//end: setTask()

	public int doTask(){
		//blah blah
		
		return 1;
	}

	public Task getActiveTask(){

		return active_task;
	}//end getActiveTask()

	public void setTraits(){


	}//end setTraits()

	public void getTraits(){



	}// end getTraits()

	public float getHoursWorked(){

		return hoursWorked;
	}//end getHoursWorked()

	public void setHours(){

		
	}//end setHours

	public int getHours(){

		return hours;
	}// end getHours()


}