/*
* Name: Richard Zhang, Ming Wang, Pai Liu
* MacID: zhang295, wangm27, liup27
* Student Number: 001450206, 001408077, 001419475
* Description: This is our HWK6 program. It contains one main class, and 6 other classes.
*/

#pragma once
#include "Expression.h"
#include <iostream>
#include <string>
using namespace std;

class ArithmeticExpression :
	public Expression
{
public:
	ArithmeticExpression();
	ArithmeticExpression(string input1);
	~ArithmeticExpression();
	Expression *left;
	Expression *right;
	string evaluate();
	float convert(string s);
	void print();
	void increment();
};

