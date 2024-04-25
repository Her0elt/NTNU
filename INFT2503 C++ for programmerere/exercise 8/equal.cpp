#include <iostream>
using namespace std;
template <typename Type> bool equal(Type a, Type b){
    cout << "Normal" << endl;
    return a == b;
}

bool equal(double a, double b){
    cout << "Double" << endl;
    return  a-b < 0.00001;
}

int main(int, char**) {
    cout << equal(2, 2) << endl;
    cout << equal(2.0000003, 2.0000004) << endl;
}
