package managers;

import java.util.ArrayList;
import java.util.List;


/**
 * Call handling elevator (s)
 *  * @author Eugene Pomozov
 */



public class CallManager {
	static private int floorCount = 10;
	private List<Call> calls = new ArrayList<Call>();

	
	public void call(int floorStart, int floorEnd){
		
		synchronized(CallManager.class){	
			
				calls.add(new Call(floorStart,floorEnd));

		}
		}
	//Do we need to stay on this floor?
	public boolean isCall(int floor,boolean route){
		for(Call call: calls)
			if (call.isCall(floor)&& !(call.getRoute()>0 ^ route)) return true;
	return false;
	}
	//Are there any calls?
	public boolean isCalls(){
		return !calls.isEmpty();
	}
	//Do we need to go up, starting with the floor?
	public boolean isUpCall(int floor){
		for(int i = floor; i <= floorCount; i++)
			for(Call call: calls)
				if (call.isCall(i)) return true;
		return false;
	}
	//Do we need to go down, starting with the floor?
	public boolean isDownCall(int floor){
		for(int i = floor; i > 0; i--)
			for(Call call: calls)
				if (call.isCall(i)) return true;
		return false;
	}
	/**
	 * It puts people in the elevator
	 * 
	 * @param floor - current floor
	 * @return The number who have come into the elevator
	 */
	public int callsInside(int floor,boolean route){
		int count = 0;
		for(Call call: calls)
			/**
			 * !call.getInside() - he does not yet entered
			 * call.getStart()==floor - he called on the current floor
			 *  !(call.getRoute()>0 ^ Up) - It coincides with the direction of movement of the elevator
			 *  
			 */
			if (!call.getInside() && call.getStart()==floor && !(call.getRoute()>0 ^ route) ) {
				call.setInside(true);
				count++;
			}
		return count;
	}
	
	/**
	 * Remove calls to those who have already arrived at the elevator
	 * @param floor - current floor
	 * @return the amount withdrawn from the elevator
	 */
	public int removeCalls(int floor){
		int count = 0;
		List<Call> temp = new ArrayList<Call>();
		for(Call call:calls){
			if (call.getFinish()==floor && call.getInside()){
				temp.add(call);
				count++;
			}
		}
		calls.removeAll(temp);
		return count;
	}
	
	
	
	
	
	
	private class Call{
		int start;
		int finish;
		boolean inside = false;//in the elevator
		
		public Call(int start, int finish){
			this.start = start;
			this.finish = finish;
		}
		public int getRoute(){
			return finish - start;
		}
		
		//Should we go to this floor?
		public boolean isCall(int floor){
			return (start==floor)||((finish==floor)&&(inside==true));
		}
		
		//He entered to(went into) the elevator
		public void setInside(boolean in){
			this.inside = in;
		}
		//
		public boolean getInside(){
			return this.inside;
		}
		public int getStart(){
			return start;
		}
	public int getFinish(){
		return finish;
	}

	}
	
}
