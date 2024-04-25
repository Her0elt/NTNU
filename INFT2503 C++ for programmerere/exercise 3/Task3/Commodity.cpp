#include <iostream>
#include <string>
#include "Commodity.hpp"

using namespace std;

const double tax = 1.25;



Commodity::Commodity(std::string name, int id, double price) : name(name), id(id), price(price) {}


std::string Commodity::get_name(){
    return this->name;
}

int Commodity::get_id(){
    return this->price;
}

void Commodity::set_price(int price) {
    this->price = price;
}

double Commodity::get_price(int amount){
    return this->price * amount;
}
double Commodity::get_price(){
    return this->price;
}

double Commodity::get_price_with_sales_tax(int amount){
    return this->get_price(amount)*tax;
}
double Commodity::get_price_with_sales_tax(){
    return this->get_price()*tax;
}



int main() {
  const double quantity = 2.5;
  Commodity commodity("Norvegia", 123, 73.50);

  cout << "Varenavn: " << commodity.get_name() << ", varenr: " << commodity.get_id() << " Pris pr enhet: " << commodity.get_price() << endl;

  cout << "Kilopris: " << commodity.get_price() << endl;
  cout << "Prisen for " << quantity << " kg er " << commodity.get_price(quantity) << " uten moms" << endl;
  cout << "Prisen for " << quantity << " kg er " << commodity.get_price_with_sales_tax(quantity) << " med moms" << endl;

  commodity.set_price(79.60);
  cout << "Ny kilopris: " << commodity.get_price() << endl;
  cout << "Prisen for " << quantity << " kg er " << commodity.get_price(quantity) << " uten moms" << endl;
  cout << "Prisen for " << quantity << " kg er " << commodity.get_price_with_sales_tax(quantity) << " med moms" << endl;
}