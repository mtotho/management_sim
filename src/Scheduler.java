//package restuarantsim;

import java.util.ArrayList;

public class Scheduler{

	private ArrayList<Task> queue;
	int index;
	private Task nextTask;


	//Constructor()
	public Scheduler(){

		queue = new ArrayList<Task>();

	}//end: Constructor()

	public void addTask(Task task){
		queue.add(task);
	}

	//getTask(): get the next task
	public Task getTask(){
		//Switch which task to get based on priority

		//for now just get the last task
		return queue.get(queue.size()-1);

	}//end: getTask()

	public void setPriority(int priority){

		queue.get(index).setPriority(priority);

	}// end setTaskPriority()

	public int getPriority(){

		return queue.get(index).getPriority();
	}//end getPriority()

	public Task getNext(Task){


		return nextTask;
	}//end getNext

}