#include <iostream>
int main(){
    double number;
    double *pointer = &number;
    double &ref = number;


    number = 33.0;
    printf("%lf \n", number);
    *pointer =  44.0;
    printf("%lf \n", number);
    ref = 55.0;
    printf("%lf \n", number);
}