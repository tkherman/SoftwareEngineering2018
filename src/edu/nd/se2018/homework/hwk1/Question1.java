package edu.nd.se2018.homework.hwk1;
import java.util.HashSet;

public class Question1 {
		
	public Question1(){}
	
	public int getSumWithoutDuplicates(int[] numbers){
		HashSet<Integer> integerSet = new HashSet<Integer>();
		
		for (int number : numbers) {
			integerSet.add(number);
		}
		
		int sum = 0;
		
		for (int integer : integerSet) {
			sum += integer;
		}
		
		return sum;
	}
}
