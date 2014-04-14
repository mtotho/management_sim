package mftoth.restaurantsim.logic;

public class Employee{

	private String name;
	private boolean busy;
	private Task active_task;
	private float hoursWorked;
	private int hours;
	private float mod;
	private String labor;
	private TaskType duty;
	private Locations waypoint;
	private Locations location;

	private String[] nameList = {"Mike", "Gordon", "Patrick", "Joey", "Jesus", "Melvin", "Eugene", "Dylan", "Ash", "Gary", "Brock", "Red"};

	//Constructor()
	public Employee(){
		int randNum = (int)(Math.random()*(nameList.length));
		this.name = nameList[randNum];
		busy=false;

		location = Locations.REGISTER;
		waypoint = null;
	}//end: Constructor()


	public void update(){
		
		
		
	}

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

	public void setDuty(TaskType duty){
		this.duty = duty;
	}

	public TaskType getDuty(){
		return duty;
	}

	public String getName(){
		return this.name;
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

	public boolean isBusy(){
		
		return busy;
	}

	public void setWayPoint(Locations waypoint){
		this.waypoint=waypoint;
	}

	public Locations getWaypoint(){
		return waypoint;
	}
	public Locations getLocation(){
		return location;
	}
	public void setLocation(Locations location){
		this.location=location;
	}

}