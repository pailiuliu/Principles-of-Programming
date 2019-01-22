//============================================================================
/*
 * Richard Zhang
 * zhang295
 * 1450206
 * Homework 5; program that computes the average and standard deviation
 * of a set of user-inputted numbers
 */
//============================================================================

#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int main() {
	int counter = 0;
	string userinput;
	double numbers[100];

	while (true){
		cout << "Enter value " << (counter + 1) << ": ";
		cin >> userinput;
		if (userinput == "#"){
			break;
		}
		else {
			numbers[counter]= atof(userinput.c_str());
		}
		counter +=1;
	}

	double sum = 0;
	for (int n = 0; n < counter; n++){
		sum = sum + numbers[n];
	}
	double average = sum/counter;
	cout << "The average is " << int(average+0.5) << endl;

	double sum2;
	double S;
	for(int n = 0; n < counter; n++){
		sum2 += pow((numbers[n]-average),2);
	}
	S = sqrt(sum2/counter);
	cout << "The standard deviation is ";
	cout.precision(2);
	cout << fixed << S <<endl;


}
