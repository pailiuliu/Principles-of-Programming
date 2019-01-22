/*
Name: PAI LIU
MacID: liup27
Student Number: 1419475
Description: This is the second part of my HWK3 assignment.
*/

public class HWK3_2_liup27{ //defining class, naming convention used correctly.

	public static void main(String[] args) { //defining main method, with String array of arguments
		nonContiguousSubstring(args[0]); //calls recursive method
	}
	
	public static void nonContiguousSubstring(String args) { //defines method with one String type argument
		int L = args.length(); //assign variable to length of string
		if(L == 2){ //if the string only have two numbers...
			//non-continguous substring CANNOT be formed, end program
		}else{ 
			for(int i = 1; i < L -1; i++){ //iterates through the length of the string, not the characters
				System.out.println("{"+args.substring(0, i)+","+args.substring(i+1, L)+"}"); 
				//prints out one type of noncontinguous substring, where there is only ONE missing character in middle 
				if(args.substring(i+1,L).length() > 1){ //conditional statement if substrings towards end are greater than 1 length
					System.out.println("{"+args.substring(0, i)+","+args.charAt(i+1)+"}");
					//print out second type of noncontinguous substring, beginning a substring and one after the skipped character
				}
			}
			if(args.indexOf(args.charAt(L-1)) - args.indexOf(args.charAt(0)) > 2){ 
				//conditional statement if the string is longer than 2
				System.out.println("{"+args.substring(0,1)+","+args.substring(L - 1, L)+"}");
				//print out third type of noncontinguous substring, first character and second last character
			}
			for(int i = 1; i < L-1; i++){ //iterates length of string
				nonContiguousSubstring(args.substring(i, L)); 
				//use recursion to find more noncontinguous substring, starting with a second letter over
			}
		}	
	}
}
