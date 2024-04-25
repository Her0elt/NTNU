#include "set.hpp"
#include <vector>
#include <algorithm>
#include <string>
#include <iostream>


Set::Set(){
    this->set = std::vector<int>();
}

Set::Set(std::vector<int> set){
    for (size_t i = 0; i< set.size(); i++){
        this->operator+(set[i]);
    }
}
bool Set::contains(int num){
    bool contain = false;
    for (int i = 0; i<this->size(); i++){
        if(this->set.at(i) == num){
            contain = true;
            break;
        }
    }
    return contain;
}
size_t Set::size() {
    return this->set.size();
}

int& Set::operator[](int i)  {
    return this->set[i];
}
int& Set::operator[](size_t i)  {
    return this->set[i];
}
void Set::operator+(int nr) {
    if (!this->contains(nr)) {
        this->set.emplace_back(nr);
    }
}

void Set::operator=(Set &other)  {
    this->set.clear();
    for (size_t i = 0; i < other.size(); i++){
        this->operator+(other[i]);
    }
     
}

Set Set::operator+(Set &other) {
    Set newSet{};
    for (size_t i = 0; i< this->size() || i < other.size(); i++){
        if (i< this->size() &&!newSet.contains(this->set[i])) {
            newSet +( this->operator[](i));
        }
        if (i < other.size() && !newSet.contains(other.set[i])){
            newSet + (other[i]);
        }
    }
    return newSet;
}




std::string Set::print()  {
    std::string output = "{";
    for (size_t i = 0; i< this->size(); i++){
            int num = this->operator[](i);
            output.append(std::to_string(num) + ", ");
    }
    output.append("}");
    return output;
}
