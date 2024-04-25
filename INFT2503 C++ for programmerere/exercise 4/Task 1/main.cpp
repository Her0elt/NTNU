#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main(){
    vector<double> vec{1.0,2.0,3.0,4.0,5.0};
    cout << "front " <<vec.front() << endl;
    cout << "back " <<vec.back() << endl;
    vec.emplace(vec.begin()+ 1, 8.0);
    cout << "First element: " << vec.front() << endl;
	cout << "Second element: " << vec[1] << endl;

	auto it = find(vec.begin(), vec.end(), 6);
	
	if(it != vec.end()) cout << "Value found: " << *it << endl;
}
