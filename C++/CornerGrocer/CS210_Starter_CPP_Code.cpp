#include <Python.h>
#include <iostream>
#include <Windows.h>
#include <cmath>
#include <string>
#include <fstream>
#include <vector>

using namespace std;
HANDLE h = GetStdHandle(STD_OUTPUT_HANDLE);// allows set console text attribute

/**
* @author Jeffrey VanMeter
* This program takes an input file of items sold. It can:
* Count the items and provide them in a list.
* Search for items and tell you how many were sold. 
* Lastly, it can create a file of the items sold then a graphical histogram of the items sold will display on the screen.
*/


// To call this function, simply pass the function name in Python
void CallProcedure(string pName)
{
	char* procname = new char[pName.length() + 1];
	std::strcpy(procname, pName.c_str());

	Py_Initialize();
	PyObject* my_module = PyImport_ImportModule("CS210_Starter_PY_Code");
	PyErr_Print();
	PyObject* my_function = PyObject_GetAttrString(my_module, procname);
	PyObject* my_result = PyObject_CallObject(my_function, NULL);
	Py_Finalize();

	delete[] procname;
}

// To call this function, pass the name of the Python function with a parameter
int CallProcedurePar(string proc, string param)
{
	char* procname = new char[proc.length() + 1];
	std::strcpy(procname, proc.c_str());

	char* paramval = new char[param.length() + 1];
	std::strcpy(paramval, param.c_str());


	PyObject* pName, * pModule, * pDict, * pFunc, * pValue = nullptr, * presult = nullptr;
	// Initialize the Python Interpreter
	Py_Initialize();
	// Build the name object
	pName = PyUnicode_FromString((char*)"CS210_Starter_PY_Code");
	// Load the module object
	pModule = PyImport_Import(pName);
	// pDict is a borrowed reference 
	pDict = PyModule_GetDict(pModule);
	// pFunc is also a borrowed reference 
	pFunc = PyDict_GetItemString(pDict, procname);
	if (PyCallable_Check(pFunc))
	{
		pValue = Py_BuildValue("(z)", paramval);
		PyErr_Print();
		presult = PyObject_CallObject(pFunc, pValue);
		PyErr_Print();
	}
	else
	{
		PyErr_Print();
	}
	//printf("Result is %d\n", _PyLong_AsInt(presult));
	Py_DECREF(pValue);
	// Clean up
	Py_DECREF(pModule);
	Py_DECREF(pName);
	// Finish the Python Interpreter
	Py_Finalize();

	// clean 
	delete[] procname;
	delete[] paramval;

	return _PyLong_AsInt(presult);
}

/**
* Returns a string of length n, each character a c.
* For example, nCharString(5, '*') should return *****
*
* @param n string length, >=0
* @return string of n c's
*/
string nCharString(size_t n, char c) {
	string str;
	int i;
	for (i = 0; i < n; ++i) {
		str += c;
	}
	return str;
}


// Displays the graphical histrogram based off the file created from CallProcedure("createList")
void createHisto() {
	ifstream inFS;
	string line;
	string product;
	int freq;
	
	cout << "\n##############################################################################\n";
	cout << "#                         Total Produce Purchased Today                      #\n";
	cout << "##############################################################################\n\n";

	inFS.open("frequency.dat");
	
	if (!inFS.is_open()) {
		cout << "Could not open frequency.dat\n";
	}
	
	getline(inFS, line);

	while (!inFS.fail()) {
		product = line.substr(0, line.find(" "));
		freq = stoi(line.substr(line.find(" "), line.length()));
		SetConsoleTextAttribute(h, 6);
		cout << product << " ";
		SetConsoleTextAttribute(h, 2);
		cout << nCharString(freq, '$') << endl;
		inFS.clear();
		getline(inFS, line);
	}
	cout << endl;
	
	inFS.close();
}

void displayMenu()
{
	cout << "\n||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
	cout << "|||                       Please Select an option                          |||\n";
	cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n\n";
	cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
	cout << "|||   1. Print List of All Purchased Items and How Many Were Purchased     |||\n";
	cout << "|||   2. Print Total Amount Purchased of a Specific Item                   |||\n";
	cout << "|||   3. Display Text Histogram                                            |||\n";
	cout << "|||   4. Exit The Program                                                  |||\n";
	cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n\n";
}

int main()
{
	int menuOption;
	bool isExit = false;
	string search;
	
	while (!isExit) {
		cout << "\x1B[2J\x1B[H"; //windows command to clear screen
		
		SetConsoleTextAttribute(h, 7);
		displayMenu();
		cout << "\nSelect menu option: ";
		cin >> menuOption;
		cout << endl;
		switch (menuOption) {
			case 1:
				SetConsoleTextAttribute(h, 1);
				CallProcedure("createCount");
				system("pause"); //Waits for user to continue
				break;
			case 2:
				SetConsoleTextAttribute(h, 4);
				cout << "What would you like to search for?\n";
				cin >> search;
				cout << "\nThere were " << CallProcedurePar("itemSearch", search) << " " << search << " sold today!\n";
				cin.clear();
				cin.ignore(INT_MAX, '\n');
				system("pause");
				break;
			case 3:
				SetConsoleTextAttribute(h, 6);
				CallProcedure("createList");
				createHisto();
				SetConsoleTextAttribute(h, 6);
				system("pause");
				break;
			case 4:
				isExit = true;
				break;
			default:
				cout << "Please enter a number 1-4";
		}
	}
	
	//Message when user exits program
	SetConsoleTextAttribute(h, 4);
	cout << "\n||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
	SetConsoleTextAttribute(h, 7);
	cout << "|||||||||||||||           Program Shutting Down...             |||||||||||||||\n";
	SetConsoleTextAttribute(h, 1);
	cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
	SetConsoleTextAttribute(h, 8);

	return 0;
}