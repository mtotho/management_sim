//package restuarantsim;

enum TaskType{
	KITCHEN, CASHIER, CLEANING, MAINTENANCE
}

public class Task{

	private int completion_time;
	private boolean active;
	private boolean allow_preemption;
	private int priority;
	private TaskType type;

	//Constructor(priority, completion_time)
	public Task(int priority, int completion_time){

		this.priority=priority;
		this.completion_time=completion_time;

	}//end: Constructor(priority, completion_time)

	//Constructor(priority)
	public Task(int priority){

		this.priority=priority;

	}//end: Constructor(priority)

	//Constructor()
	public Task(){

	}//end: Constructor()

	//setPriority(): set the priority
	public int setPriority(int priority){
		this.priority=priority;
		return this.priority;
	}//end: setPriority()

	//getPriority(): return the priority 
	public int getPriority(){
		return priority;
	}//end: getPriority()

	public void setPreemption(boolean preemption){
		allow_preemption=preemption;
	}

	public boolean getPreemption(){
		return allow_preemption;
	}

	public void setType(TaskType type){
		this.type=type;
	}

	public TaskType getType(){
		return type;
	}
}
