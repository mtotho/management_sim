package mftoth.restaurantsim.logic;

import java.util.ArrayList;

public class Scheduler{

	private ArrayList<Task> lowPriority;
	private ArrayList<Task> midPriority;
	private ArrayList<Task> highPriority;


	//Constructor()
	public Scheduler(){

		lowPriority = new ArrayList<Task>();
		midPriority = new ArrayList<Task>();
		highPriority = new ArrayList<Task>();

	}//end: Constructor()

	public void addTask(Task task){
		
		lowPriority.add(task);

	}//end addTask(Task task)


	public void setPriority(int priority, Task task){
		//low=0, medium=1, high=2
		if(task.getPriority() == 0){

			if (priority == 1){

				highPriority.add(task);
				lowPriority.remove(task);

			}
			if (priority == 2){

				midPriority.add(task);
				lowPriority.remove(task);

			}

		}

		if(task.getPriority() == 1){

			if (priority == 0){

				lowPriority.add(task);
				midPriority.remove(task);

			}

			if (priority == 2){

				highPriority.add(task);
				midPriority.remove(task);

			}

		}

		if(task.getPriority() == 2){

			if (priority == 0){

				lowPriority.add(task);
				highPriority.remove(task);

			}

			if (priority == 1){

				midPriority.add(task);
				highPriority.remove(task);

			}

		}



	}// end setTaskPriority()

	public String getPriority(Task task){

		if(lowPriority.contains(task)){

			return "Low";

		}

		else if(midPriority.contains(task)){

			return "Medium";

		}

		else if(highPriority.contains(task)){

			return "High";

		}

		else {

			return "ERROR - task not found";

		}


	}//end getPriority()

	public Task getNextTask(TaskType type){

		for(int i = 0; i < highPriority.size(); i++){
			if(type == highPriority.get(i).getType()){
				Task task = highPriority.get(i);
				highPriority.remove(i); //remove this task from queue
				return task;
			}

		}

		for(int i = 0; i < midPriority.size(); i++){
			if(type == midPriority.get(i).getType()){
				Task task = midPriority.get(i);
				midPriority.remove(i);
				//e.setTask(highPriority.get(i));
				return task;
			}

		}

		for(int i = 0; i < lowPriority.size(); i++){
			if(type == lowPriority.get(i).getType()){
				Task task = lowPriority.get(i);
				lowPriority.remove(i);
				//e.setTask(lowPriority.get(i));
				return task;
			}

		}

		return null;

	}//end: getNextTask()

	public void findEmployeeTask(Employee e){

		//This isnt going to work.. e.getJob returns string and getType returns TaskType enum
		/*
		for(int i = 0; i < highPriority.size(); i++){
			if(e.getJob() == highPriority(i).getType()){
				e.setTask(highPriority.get(i));
				return;
			}

		}

		for(int i = 0; i < midPriority.size(); i++){
			if(e.getJob() == midPriority(i).getType()){
				e.setTask(highPriority.get(i));
				return;
			}

		}

		for(int i = 0; i < lowPriority.size(); i++){
			if( e.getJob() == lowPriority(i).getType()){
				e.setTask(lowPriority.get(i));
				return;
			}


		}
*/

	}//end findEmployeeTask(Employee e)

}