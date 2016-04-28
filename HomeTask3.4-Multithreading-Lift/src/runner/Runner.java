package runner;

import gen.CallGeneration;
import lift.Lift;
import managers.CallManager;

public class Runner {

	public static void main(String[] args) {
		CallManager callManager = new CallManager();
		Lift lift = new Lift(callManager);
		
		Thread liftThread = new Thread(lift);
		liftThread.start();
		
				
		CallGeneration cGen = new CallGeneration(callManager);
		Thread genThread = new Thread(cGen);
		genThread.start();
		
	}

}
