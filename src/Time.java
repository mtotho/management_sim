package mftoth.restaurantsim.logic;

public class Time{
	private final static int MS_MINUTE= 310;
	private final static int MS_HOUR = MS_MINUTE * 60;
	private final static int MS_DAY= MS_HOUR * 24;
	private int time_passed_ms;

	public Time(){
		time_passed_ms=0;
	}
	public Time(int time){
		time_passed_ms=time;
	}
	public void addMilliSecond(int ms){
		time_passed_ms+=ms;
	}


	public int getSeconds(){
		int seconds = (int)(time_passed_ms/5);
		return seconds;
	}

	public int getSecond(int ms){
		int time_in_minute = ms % MS_MINUTE;
		int second = time_in_minute/5;

		return second;
	}

	//get the current second of the minute
	public int getSecond(){
		return getSecond(time_passed_ms);
	}

	public int toSeconds(int ms){
		return (int)(ms/1000);
	}

	public int getMilliSeconds(){
		return time_passed_ms;
	}

	public int getDay(){
		int day = (time_passed_ms/MS_DAY)+1;
		return day;
	}

	//get current hour of day
	public int getHour(){
		int time_in_day = time_passed_ms % MS_DAY;
		int hour = time_in_day/MS_HOUR;
		return hour;
	}

	//get current minute of hour
	public int getMinute(){
		int ms_under_hour = time_passed_ms % MS_HOUR;
		int minute = ms_under_hour/MS_MINUTE;
		return minute;
	}

	public String getFormattedTime(){
		
		//int days = time_passed_ms/MS_DAY;
		String string="";
		
		int hour = getHour();
		int minute = getMinute();
		int second = getSecond();

		if(hour<10){
			string+="0"+hour;
		}else{
			string+=hour;
		}

		string+=":";

		if(minute<10){
			string+="0"+minute;
		}else{
			string+=minute;
		}

		/*string+=":";

		if(second<10){
			string+="0"+second;
		}else{
			string+=second;
		}*/

		

		//System.out.println(daysFloat);
		//if(days<)

		return string;

	}

	public void setTime(int ms){
		time_passed_ms=ms;
	}

	//getday, gethour, etc

}