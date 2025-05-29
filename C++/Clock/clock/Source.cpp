/**
 * @Author Jeffrey VanMeter
 * This program displays the current time with a 12 and 24 hour clock and allows the user to add one hour, minute, or second to the time.
 */
#include <iostream>
#include <stdexcept>
#include <string>
#include <windows.h>
#include <chrono>
#include <thread>
#include <ctime>
#pragma warning(disable : 4996)


using namespace std;
int hour = 0;
int minute = 0;
int second = 0;

/**
 * Formats a number as 2 digits, with a leading 0 if needed
 * This method can be useful when converting a time like 9 hours, 12 minutes and 3 seconds to "09:12:03"
 *
 * @param n number to format, assumed between 0 and 59, inclusive
 * @return two digit string representation of number
 */
string twoDigitString(unsigned int n) {
    string numberStr;

    if ((n >= 0) && (n <= 9)) { //if number is single digit this adds a '0' in front
        numberStr = "0" + std::to_string(n);
    }
    else if ((n >= 10) && (n <= 59)) {// two digit numbers stay the same
        numberStr = std::to_string(n);
    }
    return numberStr;
}

/**
 * Returns a string of length n, each character a c.
 * For example, nCharString(5, '*') should return "*****"
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

/**
 * Formats the time in military format
 *
 * @param h, hours 0 to 23
 * @param m, minutes 0 to 59
 * @param s, seconds 0 to 59
 *
 * @return hh:mm:ss
 */
string formatTime24(unsigned int h, unsigned int m, unsigned int s) {
    string time;
    time = twoDigitString(h) + ":" + twoDigitString(m) + ":" + twoDigitString(s);
    return time;
}

/**
 * Formats the time in am/pm format
 *
 * @param h, hours 0 to 23
 * @param m, minutes 0 to 59
 * @param s, seconds 0 to 59
 *
 * @return hh:mm:ss A M or hh:mm:ss P M where hh is between 01 and 12, inclusive
 */
string formatTime12(unsigned int h, unsigned int m, unsigned int s) {
    string time;
    if (h == 0) {
        h = 12; //sets to 12 AM midnight
        time = twoDigitString(h) + ":" + twoDigitString(m) + ":" + twoDigitString(s) + " A M";
    }
    else if (h > 12) {//after 12 PM
        h -= 12;
        time = twoDigitString(h) + ":" + twoDigitString(m) + ":" + twoDigitString(s) + " P M";
    }
    else if (h == 12) {// 12PM afternoon
        time = twoDigitString(h) + ":" + twoDigitString(m) + ":" + twoDigitString(s) + " P M";
    }
    else {//after 12 AM
        time = twoDigitString(h) + ":" + twoDigitString(m) + ":" + twoDigitString(s) + " A M";
    }
    return time;
}

/**
 * Prints menu
 *
 * @param *strings[], nonempty array of choices
 * @param width, width of each line, will be comfortably bigger than the longest string
 *
 */
void printMenu(string strings[], unsigned int numStrings, unsigned char width) {
    cout << nCharString(width, '*') << endl;
    for (int i = 1; i <= numStrings; ++i) {
        cout << "* " << i << " - " << strings[i - 1] << nCharString((width - strings[i - 1].size()) - 8, ' ') << "*" << endl;
    }
    cout << nCharString(width, '*') << endl;
}

/**
 * Display the clocks
 *
 * @param h, hours 0 to 23
 * @param m, minutes 0 to 59
 * @param s, seconds 0 to 59
 */
void displayClocks(unsigned int h, unsigned int m, unsigned int s) {
    cout << nCharString(27, '*') << nCharString(3, ' ') << nCharString(27, '*') << endl;
    cout << nCharString(1, '*') << nCharString(6, ' ') << "12-HOUR CLOCK" << nCharString(6, ' ') << nCharString(1, '*') << nCharString(3, ' ');
    cout << nCharString(1, '*') << nCharString(6, ' ') << "24-HOUR CLOCK" << nCharString(6, ' ') << nCharString(1, '*') << endl;
    cout << endl;
    cout << nCharString(1, '*') << nCharString(6, ' ') << formatTime12(h, m, s) << nCharString(7, ' ') << nCharString(1, '*') << nCharString(3, ' ');
    cout << nCharString(1, '*') << nCharString(8, ' ') << formatTime24(h, m, s) << nCharString(9, ' ') << nCharString(1, '*') << endl;
    cout << nCharString(27, '*') << nCharString(3, ' ') << nCharString(27, '*') << endl;
}

 /**
  * Gets menu choice 1 through maxChoice, inclusive
  *
  * @param maxChoice, maximum choice index, a number between 2 and 9, inclusive
  * @return the first legal choice input, could be 1 through maxChoice, inclusive
  */
unsigned int getMenuChoice(unsigned int maxChoice) {
    int choice;
    cin >> choice;
    while (choice == 0 || choice > maxChoice) { //check input for 1-4 and forces unser to re-enter if not.
        cin >> choice;
    }
    return choice;
}

int getHour() {
    return hour;
}
void setHour(unsigned int h) {
    hour = h;
}
/**
 * Adds one hour
 */
void addOneHour() {
    if (getHour() >= 0 && getHour() <= 22) { //adds one hour if its not 11PM
        setHour(getHour() + 1);
    }
    else if (getHour() == 23) { //turns the clock to midnight
        setHour(0);
    }
}

int getMinute() {
    return minute;
}
void setMinute(unsigned int m) {
    minute = m;
}
/**
 * Adds one minute
 */
void addOneMinute() {
    if (getMinute() >= 0 && getMinute() <= 58) { //adds one minute
        setMinute(getMinute() + 1);
    }
    else if (getMinute() == 59) { //if the minutes are 59, turn the hour
        setMinute(0);
        addOneHour();
    }
}

int getSecond() {
    return second;
}
void setSecond(unsigned int s) {
    second = s;
}
/**
 * Adds one second
 */
void addOneSecond() {
    if (getSecond() >= 0 && getSecond() <= 58) { //adds one second
        setSecond(getSecond() + 1);
    }
    else if (getSecond() == 59) { //if seconds are 59, turn the minute
        setSecond(0);
        addOneMinute();
    }
}

 /**
  * repeats getting the user's choice and taking the appropriate action until the user chooses 4 for exit
  */
void main() {
    string menuStrings[] = {"Add One Hour", "Add One Minute", "Add One Second", "Exit Program"};
    int input;
    bool exit = false;
    time_t now;
    struct tm nowLocal;
    //the next block pulls and sets the time to the system time.
    now = time(NULL);
    nowLocal = *localtime(&now);
    setHour(nowLocal.tm_hour);
    setMinute(nowLocal.tm_min);
    setSecond(nowLocal.tm_sec);

    while (exit == false) {
        cout << "\x1B[2J\x1B[H"; //windows command to clear screen
        displayClocks(hour, minute, second);
        cout << "press esc to display menu. " << endl;
        addOneSecond();
        std::this_thread::sleep_for(1s); //waits one second
        if (GetAsyncKeyState(VK_ESCAPE)) {
            printMenu(menuStrings, 4, 28);
            cout << "Enter selection: ";
            input = getMenuChoice(4);
            cout << endl;
            if (input == 1) {
                addOneHour();
                cin.clear();
                cin.ignore(INT_MAX, '\n');
            }
            else if (input == 2) {
                addOneMinute();
                cin.clear();
                cin.ignore(INT_MAX, '\n');
            }
            else if (input == 3) {
                addOneSecond();
                cin.clear();
                cin.ignore(INT_MAX, '\n');
            }
            else if (input == 4) {
                break;
            }
        }
    }  
}