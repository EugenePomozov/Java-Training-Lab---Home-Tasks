package lift;

import java.util.ArrayList;
import java.util.List;

public class Lift implements Runnable{
	private boolean state = true;
	private boolean Up = true;
	static private int floorCount = 10;
	private int currentFloor = 1;

	private List<Call> calls = new ArrayList<Call>();
	int[] countStart = new int[floorCount];
	int[] countEnd = new int[floorCount];

	@Override
	public void run() {
		while(state){
			while(!calls.isEmpty()){
			  if (Up) 
				  goUp();
			  else
				  goDown();
			//System.out.println("���� �� "+currentFloor+" �����");	
			System.out.println(calls.size());
			}
			System.out.println("���� ������� �����������");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void call(int floorStart, int floorEnd){
		
	synchronized(Lift.class){	
		if (state){
			calls.add(new Call(floorStart,floorEnd));
		}
	}
	}
	
	public void stop(){
		state = false;
	}
	public void go(){
		state = true;
	}
	
	/*private boolean isEmpty(){
		for(int i =0; i< floorCount;i++){
			if ((countStart[i]>0) || (countEnd[i]>0)) return false;
		}
		return true;
	}*/
	private int goUp(){
		int count = currentFloor;
		try {
		for (;isUpCall(currentFloor); currentFloor++){//����� ����� �� ����?
		
			Thread.sleep(800);
		
			System.out.print("\n���� �� "+currentFloor+" ����� ---");
				
			if(isCall(currentFloor)){//����-������ ���-������ ����� �� ���������� �����
				
				System.out.print("����� ����� ������� ---");
				
				int in  = callsInside(currentFloor);//�������� ����� � ����, ���� ����� ����
				
				int out = removeCalls(currentFloor);//������� ����� �� �����, ���� ����� ����
				
				Thread.sleep(2000);
				
				System.out.println(	"����� �� ����� - "+out
									+" ----����� � ���� - "+in
									+" ---����� �������");
						
				
			}//if
		}//for
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.Up = false;//����� ���� ����
		return count;
	}
	
	private int goDown(){
		int count = currentFloor;
		try{
		for (;isDownCall(currentFloor); currentFloor--){
			
			Thread.sleep(800);
			
			System.out.print("\n���� �� "+currentFloor+" ����� ");
			
			if(isCall(currentFloor)){//����-������ ���-������ ����� �� ���������� �����
				System.out.print("����� ����� ������� ---");
				
				int in  = callsInside(currentFloor);//�������� ����� � ����, ���� ����� ����
				
				int out = removeCalls(currentFloor);//������� ����� �� �����, ���� ����� ����
				
				Thread.sleep(2000);
				
				System.out.println(	"����� �� ����� - "+out
									+" ---- ����� � ���� - "+in
									+" ---����� �������");
						
			}//if
		}//for
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		this.Up = true;
		return count;
	}
	//����� �� ������������ �� ���� �����
	private boolean isCall(int floor){
		for(Call call: calls)
			if (call.isCall(floor)&& !(call.getRoute()>0 ^ Up)) return true;
	return false;
	}
	//����� �� ����� �� ����, ������� � floor
	private boolean isUpCall(int floor){
		for(int i = floor; i <= floorCount; i++)
			for(Call call: calls)
				if (call.isCall(i)) return true;
		return false;
	}
	//����� �� ����� ����, ������� � floor
	private boolean isDownCall(int floor){
		for(int i = floor; i > 0; i--)
			for(Call call: calls)
				if (call.isCall(i)) return true;
		return false;
	}
	/**
	 * �������� ����� � ����
	 * 
	 * @param floor - ������� ����
	 * @return ���������� �������� � ����
	 */
	//��������� � ����
	private int callsInside(int floor){
		int count = 0;
		for(Call call: calls)
			/**
			 * !call.getInside() - ��� �� �����
			 * call.getStart()==floor - ������ �� ������� �����
			 *  !(call.getRoute()>0 ^ Up) - ��������� � ������������ �������� �����
			 *  
			 */
			if (!call.getInside() && call.getStart()==floor && !(call.getRoute()>0 ^ Up) ) {
				call.setInside(true);
				count++;
			}
		return count;
	}
	
	/**
	 * ������� ������ ���, ��� ��� ������� �� �����
	 * @param floor - ������� ����
	 * @return ���������� ��������� �� �����
	 */
	//������� ������ ���, ��� ��� ������� �� �����
	private int removeCalls(int floor){
		int count = 0;
		List<Call> temp = new ArrayList<Call>();
		for(Call call:calls){
			if (call.getFinish()==floor && call.getInside()){
				temp.add(call);
				//calls.remove(call);
				count++;
			}
		}
		calls.removeAll(temp);
		return count;
	}
	
	private class Call{
		int start;
		int finish;
		boolean inside = false;//� �����
		
		public Call(int start, int finish){
			this.start = start;
			this.finish = finish;
		}
		public int getRoute(){
			return finish - start;
		}
		
		//����� �� ����� �� ��� ����
		public boolean isCall(int floor){
			return (start==floor)||((finish==floor)&&(inside==true));
		}
		
		//�����/����� � ����
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
	@Override
	public String toString(){
		return start+";"+finish+";"+inside;
	}
	}
	
	
	
	
	
}
