package runners;

import java.util.Random;

public class SortExample {

	public static void main(String[] args) {
	 int[] digits = new int[100];
		Random rand = new Random();
		for(int i = 0;i<100;i++){
			digits[i] = rand.nextInt(300);

		System.out.print(digits[i]+" ");
		}

		int min,min_j,temp,max,max_j;
		for (int i=0,k=99;i<50;i++,k--){
			min = digits[i];
			min_j=i;
			max=digits[k];
			max_j=100-i;
			for(int j=i,l=99-i;j<100-i;j++,l--)
			{
				if(digits[j]<min){
					min=digits[j];
					min_j=j;
				}
				if(digits[l]>max){
					max=digits[l];
					max_j=l;
				}
			}
			if(i!=min_j){
				temp=digits[i];
				digits[i]=digits[min_j];
				digits[min_j]=temp;
			}
			if(i!=max_j){
				temp=digits[k];
				digits[k]=digits[max_j];
				digits[max_j]=temp;
			}
		}
		System.out.println();
		
		for(int i = 0;i<100;i++){
		System.out.print(digits[i]+" ");
		}	
		
	}

}
