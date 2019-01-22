/*
* Name: Richard Zhang, Ming Wang, Pai Liu
* MacID: zhang295, wangm27, liup27
* Student Number: 001450206, 001408077, 001419475
* Description: This is our HWK6 program. It contains one main class, and 6 other classes.
*/

#include "Division.h"
#include <iostream>
#include <string>
using namespace std;


Division::Division()
{
}


Division::~Division()
{
}

string Division::evaluate(){

	float value = convert(left->evaluate()) / convert(right->evaluate()); //calls the respective evaluate methods of the left and right objects then divides them together
	delete left; //free up memory
	delete right; //free up memory
	return to_string(value); //returns value converted as string
}

void Division::print(){

	left->print(); //print the left expression
	cout << " / "; //print symbol
	right->print(); //print right expression
	delete left; //free up memory
	delete right; //free up memory
}
