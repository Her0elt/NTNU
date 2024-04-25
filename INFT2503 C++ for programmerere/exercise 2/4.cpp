int main(){
    int a = 5;
    //You can't create a varible as &
    //int &b;
    int *b;
    int *c;
    // &b will return a point to a pointer so you either have to re define c og remove &
    c = b;
    //*a return the value of a pointer which a is not 
    a = *b + *c;
    //you cant edit &b because it is a operation that returns a value remove it 
    //and sett the value of b to 2 with *b
    *b = 2;
}