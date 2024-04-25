#include <iostream>


const int length = 5;

int main (){
    int under = 0;
    int between = 0;
    int over = 0;
    printf("Du skal skrive inn 5 temperaturer.\n");
    for (int i = 0; i<length; i++){
        double temp = 0;
        scanf("%lf", &temp);
        if(temp < 10.0)under++;
        else if(temp >= 10.0 && temp <=20.0)between++;
        else over++;
    }
    printf("Count under 10 is %d\n", under);
    printf("Count between 10 and 20 is %d\n", between);
    printf("Count over 20 is %d\n", over);
    
}
