
#pragma once
#ifndef CIRCLE_HPP
#define CIRCLE_HPP
class Circle {
  public:
    Circle(double _radius);
    int get_area() const;
    double get_circumference() const;
  private:
    double radius;
};
#endif 