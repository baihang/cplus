#include <iostream>

int main(){
    int val = 0;

    std::cin >> val;
    std::cout << val << std::endl;

    int i = 42;
    int &r = i; // 引用,引用本身不是对象，必须初始化
    int *p; //指针， 指针本身是对象

    p = &i; //&符号出现在表达式中，在此表示 取地址符
    *p = i; // *符号出现自表达式中 表示解引用符，给p指针所指对象赋值

    int &r2 = *p; // &是声明的一部分， *解引用符

    const int c = 3; //常量
    int *const cp = &i; //常量指针
    const int &k = c;//常量引用，指向常量的引用   --需要确认一下

    //const expression  常量表达式。可以在编译时获取结果的表达式
    //declarator  声明符
    //declatype  类型说明符
    //header guard  头文件保护符

    return 0;

}