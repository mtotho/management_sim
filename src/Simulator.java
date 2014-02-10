//package restuarantsim;

class Simulator{

	//main()
	public static void main(String[] args) throws InterruptedException{

		new Simulator();
	}//end: main()

	private OGLDisplay display;

	//Constructor()
	public Simulator(){

		Restaurant restaurant = new Restaurant();

		System.out.println("Simulator started");

		display = new OGLDisplay();


	}//end: Constructor()
}