package runners;

import java.util.Random;

public class SortExample {
	private static int agr;
	private static int[] digits;
	private static int size = 10000;

	public static void main(String[] args) throws InterruptedException {

		digits = new int[size];
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			digits[i] = rand.nextInt(100000);

		}

		int rightIndex = divide(0, size - 1);

		Sorting s1 = new Sorting(digits,0,rightIndex);
		new Thread(s1).start();;

		Sorting s2 = new Sorting(digits,rightIndex,size);
		 new Thread(s2).start();;		

		System.out.println("\n");
		
		while(!s1.isDone() || !s2.isDone()){
			Thread.sleep(5);
		}
		
		for (int i = 0; i < size; i++) {
			if (i == rightIndex)
				System.out.println("\n");
			System.out.print(digits[i] + " ");
			if(i==1000) break;

		}
		
	}
/**
 * Allocates an array relative to the average values of the array.
 * Values less than the average are left, great - right
 * @param left
 * @param right
 * @return number average
 */
	private static int divide(int left, int right) {

		int min = digits[left], max = digits[left];

		for (int i = left; i < right; i++) {
			if (digits[i] < min) {
				min = digits[i];

			}
			if (digits[i] > max) {
				max = digits[i];

			}

		}

		agr = ((int) (max + min) / 2);

		int j = size - 1;
		int i = 0;
		for (; i < j; i++) {
			if (digits[i] > agr)
				for (; j > i; j--) {
					if (digits[j] <= agr) {
						swap(i, j);
						break;
					}
				}

		}

		return j;
	}

	private static void swap(int i, int j) {
		int temp = digits[i];
		digits[i] = digits[j];
		digits[j] = temp;

	}

}
