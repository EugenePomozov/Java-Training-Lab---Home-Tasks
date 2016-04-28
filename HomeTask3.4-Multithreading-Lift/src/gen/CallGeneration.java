package gen;

import java.util.Random;

import managers.CallManager;
/**
 * Generation of random calls to lift
 * @author Eugene Pomozov
 */
public class CallGeneration implements Runnable {
	private CallManager callManager;
	
	public CallGeneration(CallManager callManager) {
		super();
		this.callManager = callManager;
	}

	@Override
	public void run() {
	 Random random = new Random();
	 int startFloor,endFloor;
		for(int i = 0; i<100;i++){
			startFloor = random.nextInt(9)+1;
			endFloor = random.nextInt(9)+1;
			callManager.call(startFloor,endFloor);
			System.out.print("\nCall from "+startFloor+" floor to "+endFloor+" floor\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
