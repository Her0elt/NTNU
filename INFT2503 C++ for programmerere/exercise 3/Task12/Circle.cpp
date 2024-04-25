#include "Circle.hpp"
#include <iostream>

const double pi = 3.141592;


Circle::Circle(double _radius): radius(_radius){}

int  Circle::get_area () const {
  return pi * radius * radius;
}

double Circle::get_circumference() const {
  double circumference = 2.0 * pi * radius;
  return circumference;
  
}


int main() {
   Circle circle{5};

  double area = circle.get_area();
  std::cout << "Arealet er lik "<< area << std::endl;

  double circumference = circle.get_circumference();
  std::cout << "Omkretsen er lik " << circumference << std::endl;
}