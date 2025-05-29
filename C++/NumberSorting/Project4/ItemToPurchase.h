#include <iostream>
#include <vector>
using namespace std;

class Watch {
public:
    void SetHours(int watchHours) {
        hours = watchHours;
    }

    void SetMins(int watchMins) {
        mins = watchMins;
    }

    virtual void PrintItem() {
        cout << hours << ":" << mins << endl;
    }

protected:
    int hours;
    int mins;
};

class SmartWatch : public Watch {
public:
    void SetPercentage(int watchPercentage) {
        batteryPercentage = watchPercentage;
    }

    void PrintItem() {
        cout << hours << ":" << mins << " " << batteryPercentage << "%" << endl;
    }

private:
    int batteryPercentage;
};

int main() {
    Watch* watch1;
    SmartWatch* watch2;
    Watch* watch3;

    vector<Watch*> watchList;
    unsigned int i;

    watch1 = new Watch();
    watch1->SetHours(1);
    watch1->SetMins(21);

    watch2 = new SmartWatch();
    watch2->SetHours(12);
    watch2->SetMins(29);
    watch2->SetPercentage(19);

    watch3 = new Watch();
    watch3->SetHours(5);
    watch3->SetMins(33);

    watchList.push_back(watch2);
    watchList.push_back(watch1);
    watchList.push_back(watch3);

    for (i = 0; i < watchList.size(); ++i) {
        watchList.at(i)->PrintItem();
    }

    return 0;
}