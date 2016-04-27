package runner;

import lift.Lift;

public class Runner {

	public static void main(String[] args) throws InterruptedException {
		Lift lift = new Lift();
		
		Thread thread = new Thread(lift);
		thread.start();
		
		Thread.sleep(5000);
		lift.call(1, 8);
		lift.call(1,5);
		lift.call(3, 9);
		
		lift.call(5, 2);
		Thread.sleep(16000);
		lift.call(4, 7);

	}

}
