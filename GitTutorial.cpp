#include <string>
#include <iostream>
#include <cstdlib>
#include <ctime>
bool Guess(int number) {
<<<<<<< HEAD
 return false;
=======
    static int target = -1;
    srand(time(NULL));
    if(target == -1){

        target=rand() % 100 +1;
    }
    if(number == target)
        return true;
    return false;
>>>>>>> 534376d93c0de533fe75c29cef7b24c1408f083c
}
int main()
{
int guess;
do {
std::cout << "Choose a number between 1 - 100 :";
std::cin >> guess;
} while (!Guess(guess));
return 0;
}
