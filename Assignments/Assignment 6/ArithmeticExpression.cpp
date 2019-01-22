/*
* Name: Richard Zhang, Ming Wang, Pai Liu
* MacID: zhang295, wangm27, liup27
* Student Number: 001450206, 001408077, 001419475
* Description: This is our HWK6 program. It contains one main class, and 6 other classes. BONUS INCLUDED
*/

#include "ArithmeticExpression.h"
#include "Multiplication.h"
#include "Subtraction.h"
#include "Division.h"
#include "Addition.h"
#include <iostream>
#include <vector>
#include <string>
using namespace std;


ArithmeticExpression::ArithmeticExpression(string input1)
{
	input = input1; //sets the input variable if a parameter is passed into constructor
}

ArithmeticExpression::ArithmeticExpression()
{

}

ArithmeticExpression::~ArithmeticExpression()
{
}

float ArithmeticExpression::convert(string s){
	return stof(s); //returns the string converted to a float
} 

string ArithmeticExpression::evaluate(){

	int operatorIndex; //variable that keeps track of what index the operator character is located
	char operatorType; //keeps track of what type of operator you're splitting at

	int countO = 0; //counts number of open brackets
	int countC = 0; //counts number of closed brackets
	bool runLoop = true; //once this boolean is false it will exit out the while loop
	//this loop gets rid of unnecessary brackets: Ex. (3+5) -> 3+5
	while (input.at(0) == '(' && input.at(input.length() - 1) == ')' && runLoop){ //while the first and last characters of the string are brackets and the bool is still true
		bool change = true; //this 2nd boolean keeps track of whether the input should have the first and last brackets removed 
		//this is required because in some cases you don't want the brackets removed: Ex. (3+5)+(3+2), but you want it removed if they don't do anything such as (3+5)
		string temp = input.substr(1, input.length() - 2); //get substring without the first and last bracket Ex. (3+5)+(3+2) -> 3+5)+(3+2
		for (int i = 0; i < temp.length(); i++){ //loop through the changed string
			if (temp[i] == '('){ //count the number of opening brackets
				countO++;
			}
			if (temp[i] == ')'){ //count the number of closing brackets
				countC++;
			}
			if (countO == 0 && countC == 1){ //if there is ever the case where there is 1 closing bracket and zero opening brackets, the expression is obvious faulty and you don't want to change it so
				change = false; //set the boolean to false
				runLoop = false; //break out the while loop 
				break; //break out the for loop
			}
		}
		if (change){ //if you should actually change it
			input = temp; //set the actual string variable to the temp variable;
		}	
	}

	int count1 = 0; //counts number of open brackets
	int count2 = 0; //counts number of closed brackets
	bool operatorFound = false; 
	for (int i = 0; i < input.length(); i++){ //loop through string
		if (input[i] == '('){ //if there is an open bracket
			count1++; //increase by 1
		}
		if (input[i] == ')'){ //if there is a closed bracket
			count2++; //increase by 1
		}
		if ((count1 == count2) && (input[i] == '+' || input[i] == '-')){ //if the number of opening brackets and the number of closed brackets are equal at the specific point in the loop,
			// and the operator is a + or -, then you should split it there
			operatorIndex = i; // sets index
			operatorFound = true; // set boolean to true b/c operator is found
			if (input[i] == '+'){
				operatorType = '+'; //sets type
			}
			if (input[i] == '-'){
				operatorType = '-'; //sets type
			}
		}
	}
	int count3 = 0;
	int count4 = 0;
	if (operatorFound == false()){ //if a + or - isn't found as the operator, then do basically the same thing for * and /
		for (int i = 0; i < input.length(); i++){
			if (input[i] == '('){ //counts number of opening brackets
				count3++; 
			}
			if (input[i] == ')'){ //counts number of closing brackets
				count4++;
			}
			if ((count3 == count4) && (input[i] == '*' || input[i] == '/')){ //if the number of closing and opening brackets are equal, and the operator is a * or / then,
				// the string should be splitted at this point
				operatorIndex = i; //set index
				operatorFound = true; //set bool to true
				if (input[i] == '*'){
					operatorType = '*'; //set operator type
				}
				if (input[i] == '/'){
					operatorType = '/'; //set operator type
				}
			}
		}
	}
	if (operatorFound == false){ //if no operators are found, that means the base case is reached (which is just a lone number)
		return input; //so return that lone number 
	}

	string split1 = input.substr(0, operatorIndex); //split1 is the portion of string before operator
	string split2 = input.substr(operatorIndex + 1); //split2 is the portion of string after operator 
	//Ex. if (3+5)+(3+2) -> split1 = (3+5), split2 = (3+2)

	ArithmeticExpression *equation; //declares pointer to object
	
	if (operatorType == '+'){ // if its splitting at a +
		equation = new Addition(); //create an addition object
		equation->left = new ArithmeticExpression(); //sets left of the addition object to a new arithmeticExpression object
		equation->left->input = split1; //sets left's input variable to the first split
		equation->right = new ArithmeticExpression(); //sets right of the addition object to a new arithmeticExpression object
		equation->right->input = split2; //sets right's input variable to the first split
		string temp = equation->evaluate(); // evaluate the addition object
		delete equation; //free up memory by deleting the object
		return temp; //return the evaluated value
	}

	else if (operatorType == '-'){ // if its splitting at a -
		equation = new Subtraction(); //create a subtraction object
		equation->left = new ArithmeticExpression(); //sets left of the addition object to a new arithmeticExpression object
		equation->left->input = split1; //sets left's input variable to the first split
		equation->right = new ArithmeticExpression(); //sets right of the addition object to a new arithmeticExpression object
		equation->right->input = split2; //sets right's input variable to the first split
		string temp = equation->evaluate(); //evaluate the subtraction objet
		delete equation; //free up memory by deleting the object
		return temp; //return the evaluted value
	}

	else if (operatorType == '*'){//if its splitting at a *
		equation = new Multiplication();//create a multiplication object
		equation->left = new ArithmeticExpression();//set left to new object
		equation->left->input = split1;//set input of left to first split
		equation->right = new ArithmeticExpression();//set right to new object
		equation->right->input = split2;//set input of right to second split
		string temp = equation->evaluate();//evaluate the multiplication object
		delete equation; //free up memory
		return temp; //return the evaluated value
	}

	else if (operatorType == '/'){//if its splitting at a /
		equation = new Division();//create a division object
		equation->left = new ArithmeticExpression();//set left to new object
		equation->left->input = split1;//set input of left to first split
		equation->right = new ArithmeticExpression();//set right to new object
		equation->right->input = split2;//set input of right to second split
		string temp = equation->evaluate();//evaluate the division object
		delete equation;//free up memory
		return temp;//return the evaluated value
	}

	return "it should never get here"; //return statement so all paths return something
}

void ArithmeticExpression::print(){

	int operatorIndex; //variable that stores index of the operator in the string
	char operatorType; //stores type of operator
	bool basecase = false; //keeps track of whether it reached the base case or not, if true the string is just a lone number

	if (input.find("+") != string::npos || input.find("-") != string::npos){ //if there exists a + or - in the string
		if (input.find("+") != string::npos){ //if a + is found
			operatorIndex = input.find("+"); //set the index
			operatorType = '+'; //set the type
		}
		else { //otherwise (if its a subtraction )
			operatorIndex = input.find("-"); //set the index
			operatorType = '-'; //set the type
		}
	}

	else if (input.find("*") != string::npos || input.find("/") != string::npos){ // if a + or - isn't found, but a * or / is found then
		if (input.find("*") != string::npos){ //if a * was found
			operatorIndex = input.find("*"); //set the index
			operatorType = '*'; //set the type
		}
		else { //otherwise (if its a division) 
			operatorIndex = input.find("/"); //set the index
			operatorType = '/'; //set the type
		}
	}

	else { //otherwise (it will not reach here unless no operators are found)
		cout << input; //print out the input, which should be just a lone number, this is the BASE CASE of the recursion
		basecase = true;  // set the basecase boolean to true
	}

	if (basecase == false){ //if it didn't reach the base case
		string split1 = input.substr(0, operatorIndex); //set split1 to portion of string before operator
		string split2 = input.substr(operatorIndex + 1); //set split2 to portion of string after operator

		ArithmeticExpression *equation; //create new object
		 
		if (operatorType == '+'){ //if the operator is a +
			equation = new Addition(); //create a new addition object
			equation->left = new ArithmeticExpression(); //create left object
			equation->left->input = split1; //set input of left object
			equation->right = new ArithmeticExpression(); //create right object
			equation->right->input = split2; //set input of right object
			equation->print(); //recursively print out the expression
			delete equation; //free up memory
		}

		else if (operatorType == '-'){ //if the operator is a - 
			equation = new Subtraction(); //create a new subtraction object
			equation->left = new ArithmeticExpression(); //create left object
			equation->left->input = split1; //set input of left object
			equation->right = new ArithmeticExpression(); //create right object
			equation->right->input = split2; //set input of right object
			equation->print(); //recursively print out the expression
			delete equation; //free up memory
		}
		else if (operatorType == '*'){//if the operator is a *
			equation = new Multiplication(); //create a new multiplication object
			equation->left = new ArithmeticExpression();//create left object
			equation->left->input = split1;//set input of left object
			equation->right = new ArithmeticExpression();//create right object
			equation->right->input = split2;//set input of right object
			equation->print();//recursively print out the expression
			delete equation;//free up memory
		}
		else if (operatorType == '/'){//if the operator is a /
			equation = new Division();//create a new division object
			equation->left = new ArithmeticExpression();//create a left object
			equation->left->input = split1;//set input of left object
			equation->right = new ArithmeticExpression();//create a right object
			equation->right->input = split2;//set input of right object
			equation->print();//recursively print out the expression
			delete equation;//free up memory
		}
	}

}

//bonus
void ArithmeticExpression::increment() { //function definition
	for (int i = 0; i < input.length(); i++) { //iterate through string
		if (input.length() >= 7) { //if string is lenght of 1
			if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3]) && isdigit(input[i + 4]) && isdigit(input[i + 5]) && isdigit(input[i + 6])) {
				//checks for the last letter of a 7 digit integer
				input[i + 6]++; //increment element by 1
				i = i + 6; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3]) && isdigit(input[i + 4]) && isdigit(input[i + 5])) {
				//checks for the last letter of a 6 digit integer
				input[i + 5]++; //increment element by 1
				i = i + 5; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3]) && isdigit(input[i + 4])) {
				//checks for the last letter of a 5 digit integer
				input[i + 4]++;//increment element by 1
				i = i + 4; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3])) {
				//checks for the last letter of 4 digit integer
				input[i + 3]++; //increment element by 1
				i = i + 3; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2])) { //checks for the last letter of 3 digit integer
				input[i + 2]++; //increment element by 1
				i = i + 2; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1])) { //checks for the last letter of 2 digit integer
				input[i + 1]++; //increment element by 1
				i = i + 1; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i])) {
				//checks for the last letter
				input[i]++; //increment element by 1
				i = i; //set i to skip the rest of the integer in string
			}
		}
		else if (input.length() >= 6) { //if string is lenght of 6
			if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3]) && isdigit(input[i + 4]) && isdigit(input[i + 5])) {
				//checks for the last letter of a 6 digit integer
				input[i + 5]++; //increment element by 1
				i = i + 5; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3]) && isdigit(input[i + 4])) {
				//checks for the last letter of a 5 digit integer
				input[i + 4]++; //increment element by 1
				i = i + 4; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3])) { //checks for the last letter of 4 digit integer
				input[i + 3]++; //increment element by 1
				i = i + 3; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2])) { //checks for the last letter of 3 digit integer
				input[i + 21]++; //increment element by 1
				i = i + 2; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1])) { //checks for the last letter of 2 digit integer
				input[i + 1]++; //increment element by 1
				i = i + 1; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i])) { //checks for the last letter
				input[i]++; //increment element by 1
				i = i; //set i to skip the rest of the integer in string
			}
		}
		else if (input.length() >= 5) { //if string is lenght of 5
			if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3]) && isdigit(input[i + 4])) {
				//checks for the last letter of a 5 digit integer
				input[i + 4]++; //increment element by 1
				i = i + 4; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3])) {
				//checks for the last letter of 4 digit integer
				input[i + 3]++; //increment element by 1
				i = i + 3; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2])) { //checks for the last letter of 3 digit integer
				input[i + 2]++; //increment element by 1
				i = i + 2;
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1])) { //checks for the last letter of 2 digit integer
				input[i + 1]++; //increment element by 1
				i = i + 1; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i])) { //checks for the last letter
				input[i]++; //increment element by 1
				i = i; //set i to skip the rest of the integer in string
			}
		}
		else if (input.length() >= 4) { //if string is lenght of 4
			if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2]) && isdigit(input[i + 3])) {
				input[i + 3]++; //increment element by 1
				i = i + 3; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2])) { //checks for the last letter of 3 digit integer
				input[i + 2]++; //increment element by 1
				i = i + 2; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1])) { //checks for the last letter of 2 digit integer
				input[i + 1]++; //increment element by 1
				i = i + 1; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i])) { //checks for the last letter
				input[i]++; //increment element by 1
				i = i; //set i to skip the rest of the integer in string
			}
		}
		else if (input.length() >= 3) { //if string is lenght of 3
			if (isdigit(input[i]) && isdigit(input[i + 1]) && isdigit(input[i + 2])) { //checks for the last letter of 3 digit integer
				input[i + 2]++; //increment element by 1
				i = i + 2; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i]) && isdigit(input[i + 1])) { //checks for the last letter of 2 digit integer
				input[i + 1]++; //increment element by 1
				i = i + 1; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i])) { //checks for the last letter
				input[i]++; //increment element by 1
				i = i; //set i to skip the rest of the integer in string
			}
		}
		else if (input.length() >= 2) { //if string is lenght of 2
			if (isdigit(input[i]) && isdigit(input[i + 1])) { //checks for the last letter of 2 digit integer
				input[i + 1]++; //increment element by 1
				i = i + 1; //set i to skip the rest of the integer in string
			}
			else if (isdigit(input[i])) { //checks for the last letter
				input[i]++; //increment element by 1
				i = i; //set i to skip the rest of the integer in string
			}
		}
		else if (input.length() >= 1) { //if string is lenght of 1
			if (isdigit(input[i])) { //checks for the last letter
				input[i]++; //increment element by 1
				i = i; //set i to skip the rest of the integer in string
			}
		}
	}
}

