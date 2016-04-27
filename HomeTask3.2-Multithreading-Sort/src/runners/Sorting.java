package runners;

public class Sorting implements Runnable {
	private int[] digits;
	private int left;
	private int right;
	private boolean ok=false;

	@Override
	public void run() {
		int temp;
		for (int i = left; i < right - 1; i++) {
			int min = digits[i];
			int minj=i;
			for (int j = i + 1; j < right; j++)
				if (digits[j] < min) {
					min=digits[j];
					minj=j;
				}
			
			temp = digits[i];
			digits[i] = digits[minj];
			digits[minj] = temp;

				
		}
ok=true;
	}

	public Sorting(int[] digits, int left, int right) {
		super();
		this.digits = digits;
		this.left = left;
		this.right = right;
	}
	public boolean isDone(){
		return ok;
	}

}
