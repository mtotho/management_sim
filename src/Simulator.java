//package restuarantsim;

class Simulator{

	//main()
	public static void main(String[] args) throws InterruptedException{

		new Simulator();
	}//end: main()

	public OGLDisplay display;

	//Constructor()
	public Simulator(){

		Restaurant restaurant = new Restaurant();

		System.out.println("Simulator started");

		display = new OGLDisplay();
		display.run();


	}//end: Constructor()
}