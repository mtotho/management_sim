package mftoth.restaurantsim.logic;


public class Employee{

	private boolean busy;
	private Task active_task;
	private float hoursWorked;
	private int hours;
	private float mod;
	private String labor;
	private TaskType preferred_task;



	//Constructor()
	public Employee(){
		busy=false;
	}//end: Constructor()

	//setTask(): assign current task
	public void setTask(Task task){


		active_task = task;
		busy=true;
		//If employee is busy, check whether the new task is more important
		
		/*if(busy){

			//if(task.getPreemption() && task.getPriority()>active_task.getPriority()){
			active_task=task;
			//}

		}
		else{

			active_task=task;

		}*/
		
	}//end: setTask()

	public int doTask(int delta){
		
		//consume time on task if time is left
		if(active_task.isTimeLeft()){

			active_task.consumeTime(delta);

			//set busy to true/false depending on if there is time left
			busy = active_task.isTimeLeft();

		}else{
			busy = false;
		}
		
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

	public TaskType getPreferredTask(){	

		return preferred_task;

	}
	public boolean isBusy(){
		
		return busy;
	}


}