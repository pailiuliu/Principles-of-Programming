/*
Name: PAI LIU
MacID: liup27
Student Number: 1419475
Description: This is the first part of my HWK3 assignment.
*/

public class HWK3_1_liup27{ //naming the class, correct naming convention used.

	public static void main(String[] args) { //defining the main method, with arguments in a String array
		int result = BinomialCoefficients(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		//converting the String arguments into integers, calling them in the BinomialCoefficients method as n and k, assigning value to a variable
		System.out.println(result); //prints out the variable "result"
	}
	public static int BinomialCoefficients(int n, int k){ 
		//defining the method BinomialCoefficients with two integers parameters	
		if(k == n || k == 0){ //condition statement, if k equals n OR k equals 0, then...
			return 1; //return a value of 1 as the result 
		}else{ //otherwise...
			return BinomialCoefficients(n-1,k-1) + BinomialCoefficients(n-1,k); 
			//recursion, call BinomialCoefficients method twice and sum their values, which will be sums of 1
		}
	}
}

