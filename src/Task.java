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


	//Constructor(priority, completion_time)
	public Task(int completion_time, TaskType type){

		
		this.completion_time=completion_time;
		this.type = type;

	}//end: Constructor(priority, completion_time)

	//Constructor()
	public Task(){

	}//end: Constructor()

	public int getPriority(){
		return priority;
	}

	public void setPreemption(boolean preemption){
		allow_preemption=preemption;
	}//end setPreemption

	public boolean getPreemption(){
		return allow_preemption;
	}//end getPreemption

	public void setType(TaskType type){
		this.type=type;
	}//end setType

	public TaskType getType(){
		return type;
	}//end getType

	public boolean isTimeLeft(){
		if(time<=0){
			isTimeLeft=false;
		}else{
			isTimeLeft=true;
		}
		return isTimeLeft;
	}//end isTimeLeft


	public void consumeTime(int delta){

		time -= delta;

		
	}//end consumeTime
}
