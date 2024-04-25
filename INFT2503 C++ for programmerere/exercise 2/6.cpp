#include <iostream>

int find_sum(const int *table, int length){
    int sum = 0;
    for (int i = 0; i<length; i++){
        sum += table[i];
    }
    return sum;
}

int main(){
    int length = 20;
    int table[length];
    for(int i = 0; i<length; i++){
        table[i] = i + 1;
    }
    int sum = find_sum(table, length-10);
    printf("sum %d \n", sum);
    sum = find_sum(&table[length-10], length-15);
    printf("sum %d \n", sum);
    sum = find_sum(&table[length-5], length-15);
    printf("sum %d \n", sum);

}
