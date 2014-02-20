//package restuarantsim;

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


	public void setPriority(String priority, Task task){

		if(task.getPriority() == "Low"){

			if (priority == "High"){

				highPriority.add(task);
				lowPriority.remove(task);

			}
			if (priority == "Medium"){

				midPriority.add(task);
				lowPriority.remove(task);

			}

		}

		if(task.getPriority() == "Medium"){

			if (priority == "Low"){

				lowPriority.add(task);
				midPriority.remove(task);

			}

			if (priority == "High"){

				highPriority.add(task);
				midPriority.remove(task);

			}

		}

		if(task.getPrioriy() == "High"){

			if (priority == "Low"){

				lowPriority.add(task);
				highPriority.remove(task);

			}

			if (priority == "Medium"){

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

	public void findEmployeeTask(Employee e){

		for(int i = 0; i < highPriority.size(); i++){
			if(e.getJob() == highPriority.getType()){
				e.setTask(highPriority.get(i));
				return;
			}

		}

		for(int i = 0; i < midPriority.size(); i++){
			if(e.getJob() == midPriority.getType()){
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


	}//end findEmployeeTask(Employee e)

}