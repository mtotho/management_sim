package mftoth.restaurantsim.logic;

enum TaskType{
	KITCHEN, CASHIER, CLEANING, MAINTENANCE
}

public class Task{

	private int completion_time;
	private boolean active;
	private boolean allow_preemption;
	private TaskType type;
	private boolean isTimeLeft;
	private int time; //ms
	private int priority;
	private Locations waypoint;
	private String name;


	//Constructor(priority, completion_time)
	public Task(int completion_time, TaskType type, Locations waypoint, String name){

		
		this.completion_time=completion_time;
		this.type = type;
		this.time = this.completion_time;
		this.waypoint = waypoint;
		this.name = name;

	}//end: Constructor(priority, completion_time)

	//Constructor()
	public Task(){

	}//end: Constructor()

	public int getPriority(){
		return priority;
	}

	public Locations getWaypoint(){
		return waypoint;
	}

	public void setPreemption(boolean preemption){
		allow_preemption=preemption;
	}//end setPreemption

	public boolean getPreemption(){
		return allow_preemption;
	}//end getPreemption

	public void setDuty(TaskType type){
		this.type=type;
	}//end setType

	public String getName(){
		return name;
	}

	public TaskType getDuty(){
		return type;
	}//end getType

	public int getTimeRemaining(){
		return time;
	}

	public boolean isTimeLeft(){
		if(time>0){
			isTimeLeft=true;
		}
		else{
			isTimeLeft=false;
		}
		return isTimeLeft;
	}//end isTimeLeft


	public void consumeTime(int delta){

		time -= delta;

		
	}//end consumeTime
}
