#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

ostream &operator<<(ostream &out, const vector<int> &table) {
  for (auto &e : table)
    out << e << " ";
  return out;
}

int main(){
    vector<int> v1 = {3, 3, 12, 14, 17, 25, 30};
    cout << "v1: " << v1 << endl;
    vector<int> v2 = {2, 3, 12, 14, 24};
    cout << "v2: " << v2 << endl;

    auto a = find_if(v1.begin(), v1.end(),[](int a){
        return a > 15;
    } );
    cout << *a <<endl;

    auto b1 = equal(v1.begin(), v1.begin()+5, v2.data(), [](int a, int b){
        return abs(a - b) < 2;
    });
    auto b2 = equal(v1.begin(), v1.begin()+4, v2.data(), [](int a, int b){
        return abs(a - b) < 2;
    });

    cout << b1 << endl;
    cout << b2 << endl;

    replace_copy_if(v1.begin(),v1.end(), v1.begin(), [](int a){
        return a % 2 != 0;
    } ,100);

    cout << v1 << endl;
}