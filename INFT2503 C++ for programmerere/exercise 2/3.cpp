#include <iostream>

using namespace std;

int main(){
    char text[5];
    char *pointer = text;
    char search_for = 'e';
    cin >> text;
    while (*pointer != search_for) {
        *pointer = search_for;
        pointer++;
        
    }
}