/*
* Name: Richard Zhang, Ming Wang, Pai Liu
* MacID: zhang295, wangm27, liup27
* Student Number: 001450206, 001408077, 001419475
* Description: This is our HWK6 program. It contains one main class, and 6 other classes.
*/

#include "Addition.h"
#include <iostream>
#include <string>
using namespace std;

Addition::Addition()
{
}


Addition::~Addition()
{
}


string Addition::evaluate(){
	float value = convert(left->evaluate()) + convert(right->evaluate()); //calls the respective evaluate methods of the left and right objects then adds them together
	delete left; //frees memory
	delete right; //frees memory
	return to_string(value); //returns the computed value as a string

}

void Addition::print(){

	left->print(); //prints the left hand side of the expression
	cout << " + "; //prints +
	right->print(); //prints the right hand side
	delete left; //frees up memory
	delete right; //frees up memory

}