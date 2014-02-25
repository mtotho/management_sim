package mftoth.restaurantsim.logic;

public class Time{

	private int time_passed_ms;

	public Time(){
		time_passed_ms=0;
	}

	public void addMilliSecond(int ms){
		time_passed_ms+=ms;
	}


	public int getSeconds(){
		int seconds = (int)(time_passed_ms/1000);
		return seconds;
	}

	public int getMilliSeconds(){
		return time_passed_ms;
	}

	//getday, gethour, etc

}