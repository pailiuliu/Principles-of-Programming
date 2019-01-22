/*
Name: Pai Liu
MacID: liup27
Student Number: 1419475
Description: This is my HWK5 program
*/

#include <iostream> //import iostream library; cin, cout
#include <cmath> //import math library; pow
#include <string> //import string library; atof
using namespace std; //use names in std standard way

int main() { //calling main function

	double valuesArr[100]; //creates array of doubles of 100 length
	int counter = 0; //declares counter variable and initiates at 0

	while(true){ //declares while loop with boolean condition True
		counter = counter + 1; //increment counter by 1 each loop
		cout << "Enter value " << counter << ":"; //prints out Enter value <counter>:
		string input; //declares variable input of type string
		cin >> input; //takes user input as variable
		if (input == "#"){ //if # is inputted, exits while loop
			break; //exits while loop
		}else{ //otherwise
			valuesArr[counter-1] = atof(input.c_str());
			//converts user input into double and puts into array
		}
	}
	double sum = 0; //declares variable sum and initiates value of 0
	for(int i = 0; i < counter-1; i = i+1){ //for loop, number of times as array size
		sum = sum + valuesArr[i]; //sums up all values in array
	}
	//cout << sum << endl;
	int N = counter-1; //array size equals number of input
	//cout << N << endl;
	double average = sum/N; //declares variable average of type double with value of sum/N
	cout << "The average is " << average << endl; //prints out The average is average variable

	double summation = 0; //declares variable summation of type double and initiates value of 0
	for(int i = 0; i < N; i++){ //loops number of times as array size/number of inputs
		summation = summation + pow(valuesArr[i]-average, 2); //summation function, add square of difference between average and value
	}
	double Sn = sqrt(summation/N); ////declares variable Sn assign square root of summation/N
	cout << "The standard deviation is ";
	cout.precision(2); //all output value will be maximum of 2 decimal places
	cout << fixed << Sn << endl; //prints Sn to 2 decimals places, 0's in decimal places are printed

	return 0; //ends main function
}
