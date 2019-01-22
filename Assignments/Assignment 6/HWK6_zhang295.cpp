/*
* Name: Richard Zhang, Ming Wang, Pai Liu
* MacID: zhang295, wangm27, liup27
* Student Number: 001450206, 001408077, 001419475
* Description: This is our HWK6 program. It contains one main class, and 6 other classes. BONUS INCLUDED
*/

#include <string>	//import string library
#include <iostream>	//import iostream library
#include <stdio.h>	//import stdio.h library
#include <ctype.h>	//import ctype.h library
#include <algorithm>	//import algorithm library
using namespace std;	//using standard for namespace

#include "Expression.h"	//accessed Expression class
#include "ArithmeticExpression.h"	//accessed ArithmeticExpression class

bool IsOperator(string op); //initiates IsOperator function (below main)
bool ValidInput(string exp);  //initiates ValidInput function (below main)


int main() //initiates main function
{
	bool run = true;  //declares bool run and initiates true
	string previousInput = "";

	while (run) {  //while loop while run = true
		string input; //declares string input
		cout << "Please enter an expression: "; //prints out Please enter an expression: 
		getline(cin, input); //receives a string line (with blankspace) and assigns to input 

		if (input.compare("#") == 0) { //if user enters #
			run = false; //stop while loop
			exit(1); //exits program
		}
		if (input.compare("@") == 0){ //if users enters @
			if (previousInput.empty() == false){ //if the previous imput is not just an empty string then
				ArithmeticExpression *equation = new ArithmeticExpression(); //dynamically create object
				equation->input = previousInput; //set the new object's variable to the previous input
				equation->increment(); //increments equation
				equation->print(); //print the expression recursively
				cout << " = ";  // prints equal sign
				cout.setf(ios::fixed); //sets format to fixed
				cout.setf(ios::showpoint); //make sure it shows decimal
				cout.precision(2); //shows only 2 decimal places
				cout << stof(equation->evaluate()) << endl; //recursively evaluate the expression, then convert it to a float so it prints out only 2 decimal places
			}
			else{
				cout << "You cannot use that function yet" << endl;
			}
		}
		else if (!(ValidInput(input))) { //if ValidInput function with parameter input return false (improper input)
			cout << "Expression is not well formed" << endl; //prints out "Expression is not well formed"
			cin.clear(); //clears input, loops back to while loop
		}
		else { //if input is correct
			input.erase(remove(input.begin(), input.end(), ' '), input.end()); //removes ALL whitespaces from input
			previousInput = input; //sets previousInput variable to input
			ArithmeticExpression *equation = new ArithmeticExpression(); //dynamically create object
			equation->input = input; //set the new object's variable to the input
			equation->print(); //print the expression recursively
			cout << " = ";  // prints equal sign
			cout.setf(ios::fixed); //sets format to fixed
			cout.setf(ios::showpoint); //make sure it shows decimal
			cout.precision(2); //shows only 2 decimal places
			cout << stof(equation->evaluate()) << endl; //recursively evaluate the expression, then convert it to a float so it prints out only 2 decimal places
		}
	}
	return 0; //ends main function
}

bool IsOperator(char op) //function IsOperator with single char parameter that returns bool value
{
	if (op == '+' || op == '-' || op == '*' || op == '/') {  //if char op is either +, -, *, or /
		return true; //return true to function call
	}
	else { //otherwise
		return false;  //return false to function call
	}
}

bool ValidInput(string exp)  //function ValidInput with single string parameter that returns bool value
{
	int counter1 = 0; //declares int counter1 and initiates to 0
	int counter2 = 0; //declares int counter1 and initiates to 0
	int L = exp.length(); //declares int L and initiate to length of exp
	if (exp.length() == 0){
		return false;
	}

	for (int i = 0; i < L; i++) //iterates through exp
	{
		char ele = exp.at(i); //set ele to be the character at each iteration
		if (ele == '(') { //if ele character is (
			counter1++; //increment counter1
		}
		if (ele == ')') { //if ele character is ) 
			counter2++; //increment counter2
		}
		if (isalpha(ele)) { //if ele character is a letter of the alphabet
			return false; //return false to ValidInput
		}
		if (!(isdigit(ele)) && ele != '(' && ele != ')' && !(IsOperator(ele)) && ele != ' ') {
			//if ele character is anything character BUT (, ), +, -, /, * , blackspace, or a digit
			return false; //return false to ValidInput
		}
	}
	if (counter1 != counter2) { //if opening brackets does NOT equal closing brackets
		return false; //return false to ValidInput
	}
	for (int i = 0; i < L - 1; i++) //iterates through exp except last element
	{
		char ele = exp.at(i); //declares char ele and initiates to loop variable
		char ele1 = exp.at(i + 1); //declares char ele1 and initiates to loop variable +1
		char last = exp.at(L - 1); //declares char last and initiates to last character in string
		char first = exp.at(0); //declares char first and initiates to first character in string

		if (IsOperator(first) && !(first == '-')) { //if first character is operator other than -
			return false; //return false to ValidInput
		}
		else if (IsOperator(last)) { //if last character is an operator
			return false; //return false to ValidInput
		}
		else if (IsOperator(ele) && IsOperator(ele1)) { //if two operators are side by side
			return false; //return false to ValidInput
		}
		else if (ele == ')' && !(IsOperator(ele1) || ele1 == ')')) { //if there is a ), the next character must be ) or an operator
			return false; //return false to ValidInput
		}
		else if ((ele1 == '(') && !(IsOperator(ele) || ele == '(')) { //if there is a (, the previous character must be ( or an operator
			return false; //return false to ValidInput
		}
	}
	for (int i = 0; i < L - 2; i++)  //iterates through exp except last two elements
	{
		char ele = exp.at(i); //declares char ele and initiates to loop variable
		char ele1 = exp.at(i + 1); //declares char ele1 and initiates to loop variable +1
		char ele2 = exp.at(i + 2); //declares char ele1 and initiates to loop variable +2

		if (isdigit(ele) && ele1 == ' ' && isdigit(ele2)) { //if there is a space seperating two digits
			return false; //return false to ValidInput
		}
		else if (ele == ' ' && ele1 == ' ' && ele2 == ' ') { //if there are three spaces in a row
			return false; //return false to ValidInput
		}
	}

	for (int i = 0; i < L - 3; i++) //iterates through exp except last three elements
	{
		char ele = exp.at(i); //declares char ele and initiates to loop variable
		char ele1 = exp.at(i + 1); //declares char ele1 and initiates to loop variable +1
		char ele2 = exp.at(i + 2); //declares char ele1 and initiates to loop variable +2
		char ele3 = exp.at(i + 3); //declares char ele1 and initiates to loop variable +3

		if (isdigit(ele) && ele1 == ' ' && ele2 == ' ' && isdigit(ele3)) { //if two digits are seperated by two blankspaces
			return false; //return false to ValidInput
		}
		else if (ele == ' ' && ele1 == ' ' && ele2 == ' ' && ele3 == ' ') { //if there are four blank spaces in a row
			return false; //return false to ValidInput
		}
	}
	return true; //return true to ValidInput if no error is caught to return false
}

