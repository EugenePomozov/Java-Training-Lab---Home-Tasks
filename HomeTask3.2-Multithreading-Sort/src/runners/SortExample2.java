package runners;

import java.util.Random;

public class SortExample2 {
	private static int left_index;
	private static int right_index;
	private static int agr;
	private static int[] digits;

	public static int searchLower() {// поиск большего
		
		for (; agr < digits[right_index]; right_index--) {
		}
		return right_index;
	}

	public static int searchLarger() {// поиск меньшего

		for (int i = left_index; agr > digits[i]; i++, left_index++) {

		}
		return left_index;
	}

	public static void main(String[] args) {
		int size=1000000;
		digits = new int[size];
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			digits[i] = rand.nextInt(3000000);

			//System.out.print(digits[i] + " ");
		}
java.util.Date time1 = new java.util.Date();
		int min = digits[0], min_j, temp, max = digits[0], max_j;

		for (int i:digits) {
			if (i < min) {
				min = i;
				
			}
			if (i > max) {
				max = i;
			
			}

		}
		System.out.println(max + " " + min + "\n");

		agr = ((int) (max + min) / 2);

		right_index = size-1;
		left_index = 0;

		while (right_index - left_index > 1) {
			if (digits[right_index] < agr)
				swap(right_index, searchLarger());
			else
				right_index--;
			
			if (digits[left_index] > agr)
				swap(left_index, searchLower());
			else
				left_index++;
		}
		java.util.Date time2 = new java.util.Date();
		System.out.println("\n Time = "+(time2.getTime()-time1.getTime()));
		/*for (int i = 0; i < size; i++) {
			System.out.print(digits[i] + " ");
		}*/
		
		System.out.println("\n"+left_index+"   "+right_index);
	}

	private static void swap(int i, int j) {
		int temp = digits[i];
		digits[i] = digits[j];
		digits[j] = temp;
		right_index--;
		left_index++;
	}

}
