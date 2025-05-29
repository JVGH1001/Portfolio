/*
 * Calculator.cpp
 *
 *  Date: 11/6/22
 *  Author: Jeffrey VanMeter
 */

#include <iostream>
#include <stdexcept>
using namespace std;

void main()
{
	char statement[100];
	double op1, op2; // changed to double to allow decimals
	char operation;
	char answer = 'Y'; //changed "" to '' and added ; to complete line

	while (tolower(answer) == 'y') {//added tolower so user can enter y or Y

		cout << "Enter expression" << endl;
		cin >> op1 >> operation >> op2; //changed position of op1 & op2

		//this while loop check to see if the user enters the correct input.
		while (cin.fail() || (operation != '+' && operation != '-' && operation != '*' && operation != '/')) {
			cin.clear(); // clear input buffer to restore cin to a usable state
			cin.ignore(INT_MAX, '\n'); // ignore last input
			cout << "Please enter a proper expression with numbers\n";
			cin >> op1 >> operation >> op2;
		}



		if (operation == '+') { //changed "" to '' which is required for characters and ; to { which allows the next lines to be included with conditional
			cout << op1 << " + " << op2 << " = " << op1 + op2 << endl; //changed >> to <<
			cin.clear();
			cin.ignore(INT_MAX, '\n');
		}
		if (operation == '-') { //changed ; to { 
			cout << op1 << " - " << op2 << " = " << op1 - op2 << endl;//changed >> to << which is required for cout operations
			cin.clear();
			cin.ignore(INT_MAX, '\n');
		}
		if (operation == '*') { //added {
			cout << op1 << " * " << op2 << " = " << op1 * op2 << endl; //added ; also changed display of / to *
			cin.clear();
			cin.ignore(INT_MAX, '\n');
		}
		// cannot divide by 0, else division is valid
		if (operation == '/' && op2 == 0) {
			cout << "Invalid operation, you can not divide by 0";
		}
		else if (operation == '/') { //added {
			cout << op1 << " / " << op2 << " = " << op1 / op2 << endl; //changed display of * to /
			cin.clear();
			cin.ignore(INT_MAX, '\n');
		}


		cout << "Do you wish to evaluate another expression? (y/n)" << endl;
		cin >> statement;

		//added to verify user enters correct input to continue using the program or not.
		while (tolower(statement[0]) != 'y' && tolower(statement[0]) != 'n') {
			cin.clear();
			cin.ignore(INT_MAX, '\n');
			cout << "please enter (y/n)" << endl;
			cin >> statement;
			answer = statement[0];
		}
		//lets the user know the program has terminated. 
		if (tolower(statement[0]) == 'n') {
			cout << "Program Finished.";
		}
		answer = statement[0];

	}
}

