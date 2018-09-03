package edu.nd.se2018.homework.hwk1;


public class Question3 {
	
	public Question3(){}
	
	public int getMirrorCount(int[] numbers) {
		if (numbers.length == 0)
			return 0;
		
		int[] reversedNumbers = getReversedArray(numbers);
		
		int max = 0;
		int mirrorCount = 0;
		// Loop through the two arrays and count the max 
		// consecutive match
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == reversedNumbers[i]) {
				mirrorCount++;
				if (mirrorCount > max)
					max = mirrorCount;
			// reset mirrorCount if it's not continuous
			} else {
				mirrorCount = 0;
			}
		}
		
		return max;
	}
	
	// Clone the array and return a reversed version of it
	private int[] getReversedArray(int[] arr) {
		int[] reversedArray = arr.clone();
		int start = 0, end = arr.length - 1;
		
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
		
		return reversedArray;
	}
}
