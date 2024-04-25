
#pragma once
#include <string>


class Commodity{
    private:
        std::string name;
        int id;
        double price;

    public:
        Commodity(std::string name, int id, double price);
        std::string get_name();
        int get_id();
        double get_price();
        double get_price(int amount);
        void set_price(int price);
        double get_price_with_sales_tax();
        double get_price_with_sales_tax(int amount);


};