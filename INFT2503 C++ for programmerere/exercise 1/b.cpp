#include <iostream>
#include <fstream>
#include <vector>
using namespace std;


const int length = 5;

void read_temperatures(double temperatures[], int length);

int main(){
    int under = 0;
    int between = 0;
    int over = 0;
    double temps[length];
    read_temperatures(temps, length);
    for (int i =0;i < length; i++){
        
        if(temps[i] < 10.0)under++;
        else if(temps[i] >= 10.0 && temps[i] <=20.0)between++;
        else over++;
        printf("%lf \n", temps[i]);
    }
    printf("Count under 10 is %d\n", under);
    printf("Count between 10 and 20 is %d\n", between);
    printf("Count over 20 is %d\n", over);
}

void read_temperatures(double temperatures[], int length) {
    ifstream file;
    file.open("temps.txt");
     if (!file) {        
        fprintf(stderr, "%s", "File did not open properly\n"); 
        exit(EXIT_FAILURE);
    }
    double temp = 0;
    for (int i = 0; file >> temp || i<length; i++) {
        temperatures[i] += temp;
    }
    file.close();


}