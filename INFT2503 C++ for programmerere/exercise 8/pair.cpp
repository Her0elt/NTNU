#include <iostream>
using namespace std;
// Du forutseter at alle + > operatorer er implementert som ønsket, 
//for hver type som skal bli brukt av pair
template <typename a, typename b> 
class Pair{
    public:
        a first;
        b second;
        Pair(a _first, b _second): first(_first), second(_second){}

        Pair operator+(const Pair &other) {
            Pair pair = *this;
            pair.first += other.first;
            pair.second += other.second;
            return pair;
        }

        bool operator>(const Pair &other){
            return this->first > other.first && this->second > other.second;
        }
};



int main() {
  Pair<double, int> p1(3.5, 14);
  Pair<double, int> p2(2.1, 7);
  cout << "p1: " << p1.first << ", " << p1.second << endl;
  cout << "p2: " << p2.first << ", " << p2.second << endl;

  if (p1 > p2)
    cout << "p1 er størst" << endl;
  else
    cout << "p2 er størst" << endl;

  auto sum = p1 + p2;
  cout << "Sum: " << sum.first << ", " << sum.second << endl;
}
