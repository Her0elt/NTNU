#include "fraction.hpp"
#include "set.hpp"
#include <iostream>
#include <string>
#include <vector>

using namespace std;

void print(const string &text, const Fraction &broek) {
  cout << text << broek.numerator << " / " << broek.denominator << endl;
}
void print(const Fraction &broek, const string &text) {
  cout << broek.numerator << " / " << broek.denominator <<  text << endl;
}

void fracMain(){
  Fraction a(10, 20);
  Fraction b(3, 4);
  Fraction c;
  c.set(5);
  Fraction d = a / b;

  print("a = ", a);
  print("b = ", b);
  print("c = ", c);
  print("d = ", d);

  b += a;
  ++c;
  d *= d;

  print("b = ", b);
  print("c = ", c);
  print("d = ", d);

  c = a + b - d * a;
  c = -c;

  print("c = ", c);

  if (a + b != c + d)
    cout << "a + b != c + d" << endl;
  else
    cout << " a + b == c + d" << endl;
  while (b > a)
    b -= a;
  print("b = ", b);
  print("5-", b);
  print("=", 5-b);
  print(b, " - 5");
  print("test", 5 - 3 - a - 7 - b);
}

void setMain(){
  vector<int> vec1 {1, 4, 3};
  vector<int> vec2 {4, 7};

  Set set(vec1);
  Set set2(vec2);
  Set set3 = set + set2;
  cout << set3.print() << endl;
  set + 2;
  cout << set.print() << endl;
  set3 = set;
  cout << set3.print() << endl;
}

int main() {
  cout << "\nTask 1\n" << endl;
  fracMain();
  cout << "\nTask 2\n" << endl;
  setMain();
  


}
