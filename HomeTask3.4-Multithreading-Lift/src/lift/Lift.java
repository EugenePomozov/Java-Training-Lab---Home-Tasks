package lift;


import managers.CallManager;
/**
 * class Lift
 * @author Eugene Pomozov
 */
public class Lift implements Runnable{
	/**
	 * CallManager - It contains information about the elevator call
	 * state - elevator operation condition( true - work)
	 * currentFloor - 
	 * route - (true - up, false - down)
	 */
	private CallManager callManager;
	private boolean state = true;
	private int currentFloor = 1;
	private boolean route = true;


	
	public Lift(CallManager callManager){
		this.callManager = callManager;
	}
	@Override
	public void run() {
		while(state){
			while(callManager.isCalls()){
			  if (route) 
				  goUp();
			  else
				  goDown();
			}
			System.out.println("Elevator awaits visitors");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				stop();
				e.printStackTrace();
			}
		}
	}
	
	
	public void stop(){
		System.err.println("Elevator is not working :(");
		state = false;
	}
	public void go(){
		state = true;
	}
	
	//up
	private int goUp(){
		int count = currentFloor;
		for (;callManager.isUpCall(currentFloor); currentFloor++){//We need to go up?
			liftService();
		}//for
		this.route = false;//Else go down
		return count;
	}
	
	//Down
	private int goDown(){
		int count = currentFloor;
		
		for (;callManager.isDownCall(currentFloor); currentFloor--){//We need to go down?
			liftService();
		}//for
		
		this.route = true;//Else go up
		return count;
	}
/**
 * A lift serves the people
 */
	private void liftService(){
		try{
		Thread.sleep(800);
		
		System.out.print("\nElevator on "+currentFloor+" floor ");
		
		if(callManager.isCall(currentFloor, route)){//Does anyone need anything on a particular floor
			System.out.print("The elevator door opens ---");
			
			int in  = callManager.callsInside(currentFloor, route);//it put people in the elevator, if any
			
			int out = callManager.removeCalls(currentFloor);//It takes people out of the elevator, if any
			
			Thread.sleep(2000);
			
			System.out.println(	"It turned out of the elevator - "+out
								+" ---- It went into the elevator - "+in
								+" ---the door is closed");
					
		}//if
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
