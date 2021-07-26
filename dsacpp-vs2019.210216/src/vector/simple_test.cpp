
#include "vector.h"

int main(int argc, char *argv[]){
    Vector<int> *v = new Vector<int>(3, 5, -1);
    print(v->size())
    return 1;
}