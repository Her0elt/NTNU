#include <iostream>
using namespace std;

void printVariables(int i, int j, int* p, int* q);

void a(){
    int i = 3;
    int j = 5;
    int *p = &i;
    int *q = &j;
    printVariables(i, j , p, q);
}

void b(){
    int i = 3;
    int j = 5;
    int *p = &i;
    int *q = &j;
    *p = 7;
    *q += 4;
    *q = *p + 1;
    p = q;
    cout << *p << " " << *q << endl;

    printVariables(i, j , p, q);
    
}

int main(){
    b();
}

void printVariables(int i, int j, int* p, int* q ){
    cout << i << " value of i\n";
    cout << &i << " adress of i\n";
    cout << j << " value of j\n";
    cout << &j << " adress of j\n";
    cout << *p << " value of p\n";
    cout << p << " adress of p\n";
    cout << *q << " value of q\n";
    cout << q << " adress of q\n";
}