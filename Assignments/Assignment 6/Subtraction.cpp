/*
* Name: Richard Zhang, Ming Wang, Pai Liu
* MacID: zhang295, wangm27, liup27
* Student Number: 001450206, 001408077, 001419475
* Description: This is our HWK6 program. It contains one main class, and 6 other classes.
*/

#include "Subtraction.h"
#include <iostream>
#include <string>
using namespace std;

Subtraction::Subtraction()
{
}


Subtraction::~Subtraction()
{
}

string Subtraction::evaluate(){

	float value = convert(left->evaluate()) - convert(right->evaluate()); //calls the respective evaluate methods of the left and right objects then subtracts one from the other
	delete left; //frees up memory
	delete right; //frees up memory
	return to_string(value); //returns evaluted value as a string
}

void Subtraction::print(){

	left->print(); //prints left expression
	cout << " - "; //prints operator
	right->print(); //prints right expression
	delete left; //free up memory
	delete right; //free up memory
}