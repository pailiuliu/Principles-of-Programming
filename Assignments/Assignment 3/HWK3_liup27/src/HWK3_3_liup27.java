/*
Name: PAI LIU
MacID: liup27
Student Number: 1419475
Description: This is the third part of my HWK3 assignment.
*/

import java.util.ArrayList; //imports library ArrayList
import java.util.Arrays; //imports library Arrays

public class HWK3_3_liup27 { //defining class, naming convention used correctly.

	public static void main(String[] args) { //defining main method, with String array of arguments
		int L = args.length; //assignment L as number of arguments given
		int sum = Integer.parseInt(args[L-1]); //assignment sum as variable for last argument, which is sum
		int actualSum; //creates variable
		int[] newArray; //creates array
		int[] num = new int[L]; //creates array
		
		for(int i = 0; i < L; i++){ //iterates through all arguments
			num[i] = Integer.parseInt(args[i]);
			// puts the integer values of arguments into num array
		}
		for(int i = 0; i < L-1; i++){ //iterates through all arguments
			actualSum = 0; //assign initial value to actualSum
			if(num[i] <= sum){ //if an argument is less THAN or EQUAL to sum
				newArray = Arrays.copyOfRange(num, i, num.length); //assign subarray into new array, without i
				ArrayList<Integer> intAnswer = subsetSum(newArray); //creates integer array by calling method subsetSum
				for(int j : intAnswer){ //iterates through the array created by method subsetSum
					actualSum += j; //adds values of all the integers of array into actualSum variable
				}
				if(actualSum == sum){ //tests to see if the variable equals to the last argument
					System.out.println(intAnswer); //if so, prints the integer array
				}
			}
		}
	}
	
	public static ArrayList<Integer> subsetSum(int[] num){ 
		//creates method that takes an array as input parameter
		int sum = num[num.length - 1]; //assigns last matrix element as the sum
		ArrayList<Integer> numCurrent = new ArrayList<Integer>(); //creates integer array list
		if (num.length == 1){ //checks to see if there is only one element left, which is sum
			numCurrent.add(sum + 1); //this trick gets rid of using a number repeated from the set 
			return numCurrent; //returns array
		}else{ //otherwise
			if(num[0] == sum){ //if the subarray has a value already equal to the sum...
				numCurrent.add(num[0]); //that value is added to array
				return numCurrent; //array with only that value is printed
			}else if(num[0] < sum){ //if a number less than sum appears...
				int[] NewSumArray = Arrays.copyOfRange(num, 1, num.length); 
				//creates an integer array, containing subarray of num without the number being considered
				int newSum = sum - num[0]; //creates new sum variable with value of that number taken away
				NewSumArray[NewSumArray.length - 1] = newSum; //assigns the new sum to last element of the subarray
				numCurrent.add(num[0]); //append that number to the array containing the list of integers
				numCurrent.addAll(subsetSum(NewSumArray)); //recursion of subsetSum method with the new array
			}else if(num[0] > sum){ //if number is bigger than the sum...
				int[] NewSumArray = Arrays.copyOfRange(num, 1, num.length); //creates new integer subarray
				return subsetSum(NewSumArray); //calls recursion of sebsetSum method with new subarray
			}
		}
		return numCurrent; //returns and ends method
	}
}
