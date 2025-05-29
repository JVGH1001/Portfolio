#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

/**
* @author Jeffrey VanMeter
* This program takes an input file of cities and Fahrenheit temps and converts them to Celsius temps then writes them to another file
*/

class City {
public:
    void SetCityName(string revCityName) {
        cityName = revCityName;
    }
    void SetTemp(double revTemp) {
        temp = revTemp;
    }
    string GetCityName() const { return cityName; }
    int GetTemp() const { return temp; }


private:
    string cityName = "NoName";
    int temp = -1;
};


void ReadCities(string& name, vector<City>& cityList) {
    ifstream inFS;     // Input file stream
    string cityName;
    double Temp;
    City currentCity;

    // Open file
    inFS.open("FahrenheitTemperature.txt");

    if (!inFS.is_open()) {
        cout << "Could not open file FahrenheitTemperature.txt." << endl;
    }

    inFS, cityName;

    while (!inFS.eof()) {
        inFS >> cityName;
        inFS >> Temp;

        if (!inFS.fail()) {
            currentCity.SetCityName(cityName);
            currentCity.SetTemp(Temp);
            cityList.push_back(currentCity);
        }
    }

    // done reading
    inFS.close();
}

void CalcTemp(vector<City>& cityList) {
    int i;
    double temp = 0;
    
    //converts all temps
    for (i = 0; i < cityList.size(); ++i) {
        temp = ((cityList.at(i).GetTemp() - 32) * (5.0/9.0));
        cityList.at(i).SetTemp(temp);
    }
}

void WriteToFile(const string& cityName, const vector<City>& cityList) {
    int i;
    ofstream outFS;
    outFS.open("CelsiusTemperature.txt");

    if (!outFS.is_open()) {
        cout << "Could not open CelsiusTemperature.txt" << endl;
    }

    //writes to file
    for (i = 0; i < cityList.size(); ++i) {
        outFS << cityList.at(i).GetCityName() << " " << cityList.at(i).GetTemp() << endl;
    }
    //done writing
    outFS.close();
}

int main() {
    vector<City> cityList;
    string cityName;

    ReadCities(cityName, cityList); //read input file
    CalcTemp(cityList); //converts temps
    WriteToFile(cityName, cityList); //write to another file

    return 0;
}