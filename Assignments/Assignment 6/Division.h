/*
* Name: Richard Zhang, Ming Wang, Pai Liu
* MacID: zhang295, wangm27, liup27
* Student Number: 001450206, 001408077, 001419475
* Description: This is our HWK6 program. It contains one main class, and 6 other classes.
*/

#pragma once
#include "ArithmeticExpression.h"
class Division :
	public ArithmeticExpression
{
public:
	Division();
	~Division();
	string evaluate();
	void print();
};

