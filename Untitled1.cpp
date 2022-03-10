#include <string.h>
#include <iostream>
using namespace std;
int main(){
    string defaultPath = "C:\\Users\\thegr\\Desktop\\線性代數_解\\ch2\\";

    string command;
    //for(int i = 1; i <= 40; i++){
        string s = to_string(1);
        command = "mkdir -p " + defaultPath + s;
        system(command.c_str());
    //}

    return 0;
}
