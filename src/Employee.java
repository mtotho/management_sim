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
	private int tileX;
	private int tileY;

	//Constructor()
	public Employee(){
		busy=false;

		location = Locations.ENTRANCE;
		waypoint = Locations.GRILL;
	}//end: Constructor()


	public void update(int tileX, int tileY){
		this.tileX = tileX;
		this.tileY = tileY;
		if(tileX == 6 && tileY == 16){
			this.location = Locations.REGISTER;
		}
		else if(tileX == 3 && tileY == 6){
		//	this.location = Locations.KITCHEN;
		}
		else{
			//this.location = null;
		}
		
		
	}

	//setTask(): assign current task
	public void setTask(Task task){
		active_task = task;
		busy=true;
	
	}//end: setTask()

	public Task doTask(int delta){
		


		//consume time on task if time is left
		if(active_task.isTimeLeft()){


				if(location==active_task.getWaypoint()){
					active_task.consumeTime(delta);	

					//set busy to true/false depending on if there is time left
					//busy = active_task.isTimeLeft();

				}else{
					this.waypoint = active_task.getWaypoint();

				
				}
				//System.out.println(active_task.getTimeRemaining());
		}
		else if(!active_task.isTimeLeft() && active_task.hasEndPoint() && location!=active_task.getEndPoint()){
			
			//System.out.println("Setting endpoint to :" + active_task.getEndPoint());
			this.waypoint=active_task.getEndPoint();

		}else{
			busy = false;
			return active_task;
		}
		
		return null;
	}

	public void setDuty(TaskType duty){
		this.duty = duty;
	}

	public TaskType getDuty(){
		return duty;
	}

	public void setName(String name){
		this.name = name;
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

	public int getTileX(){
		return this.tileX;
	}

	public int getTileY(){
		return this.tileY;
	}

}