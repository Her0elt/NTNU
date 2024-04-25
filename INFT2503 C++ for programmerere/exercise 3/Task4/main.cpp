#include <string>
#include <iostream>
#include <algorithm>

using namespace std;

int main(){
    string word1;
    string word2;
    string word3;

    printf("Write 3 word and press enter for each \n");
    cin>> word1;
    cin >>word2;
    cin >>word3;

    string sentence = word1 + (" ") + (word2) + (" ") + (word3) + (".");
    

    cout <<"Length of word 1 " << word1.length() <<endl;
    cout <<"Length of word 2 "<< word2.length() << endl;
    cout <<"Length of word 3 "<<word3.length() << endl;
    cout << "Length of sentence "<< sentence.length()<<endl;

    string sentence2 = sentence;
    for(int i =10; i<=12; i++){
        if(sentence2[i]){
            sentence2[i] = 'x';
        }
    }
    cout <<"sentence "<< sentence << endl;
    cout <<"sentence2 "<< sentence2 << endl;
    string sentence_start = "";
    for(int i = 0; i<5; i++){
        if(sentence[i]){
           sentence_start += (sentence2[i]);
        }
    }

    cout <<"sentence "<<sentence<<endl;
    cout <<"sentence_start" << sentence_start<<endl;
    cout << "Does sentence contain Hello " << (sentence.find("Hello") != std::string::npos) << endl;
    string find = "er";
    int count = 0;
    size_t nPos = sentence.find(find, 0);
    while(nPos != string::npos)
    {
        count++;
        nPos = sentence.find(find, nPos + 1);
    }
    cout << "How many times does er appear " << count << endl;

}