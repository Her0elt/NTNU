#include <iostream>
#include <condition_variable>
#include <thread>
#include <functional>
#include <list>
#include <chrono>

using namespace std;
using namespace chrono;

class PrimeFinder{

  mutex primes_mutex;
  list<thread> thread_list;
  list<int> primes;

public:
    PrimeFinder(int start, int end, int thread_amount){
        int low = start - 1;
        int num_of_elements = end - low;
        for (int i = 0; i < thread_amount; i++){
            thread_list.emplace_back([this, i, num_of_elements, thread_amount, low] {
            pair<int, int> pair{(i * num_of_elements / thread_amount) + low,((i + 1) * num_of_elements / thread_amount) + low};
            for (int n = pair.first + 1; n < pair.second; n++){
                if (isPrime(n)){
                    lock_guard<mutex> guard(primes_mutex);
                    primes.emplace_back(n);
                }
            }
            });
        }
        for (auto &thread : thread_list){
            thread.join();
        }
    };

    bool isPrime(int n){
        if (n == 0 || n == 1){
            return false;
        }
        for (int i = 2; i <= n / 2; ++i){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    ~PrimeFinder(){
        thread_list.clear();
        primes.sort();
        for (auto &prime : primes){
            printf("%d ", prime);
        }
        printf("\n");
    }
};

int main(){
    auto t1 = high_resolution_clock::now();
    {
    PrimeFinder(1, 50000000, 100);  
    }
    auto t2 = high_resolution_clock::now();
    double milli_sec = duration_cast<milliseconds>(t2-t1).count();
    printf("%f seconds\n",milli_sec/1000); 
}