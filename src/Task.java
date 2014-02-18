enum TaskType{
	KITCHEN, CASHIER, CLEANING, MAINTENANCE
}

public class Task{

	private int completion_time;
	private boolean active;
	private boolean allow_preemption;
	private int priority;
	private TaskType type;
	private boolean isTimeLeft;

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

	public boolean isTimeLeft{

		return isTimeLeft;
	}//end isTimeLeft

	public consumeTime(int delta){

		time -= delta;

	}//end consumeTime
}
