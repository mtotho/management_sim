public class Employee{

	private boolean busy;
	private Task active_task;


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

}