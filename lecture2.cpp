/**
 * @file lecture2.cpp
 * @author hang (1448863597@qq.com)
 * @brief 
 * @version 0.1
 * @date 2021-04-12
 * 
 * @copyright Copyright (c) 2021
 * 
 * C++ 基本数据类型，C++类型具体大小跟机器相关
 *  整形，浮点型，字符型
 * 
 *  int 至少和 short 一般大
 *  long 至少和一个int 一般大
 *  long long 至少和 一个long 一般大
 *  
 *  float 和 double 一般都有7 和 16 个有效位
 * 
 *  char, unsigned char, signed char
 *  字符的表现形式只有带符号和不带符号两种，但是 char 和 signed char并不一样。char 带不带符号由编译器决定
 * 
 *  当明确数值不可能为负时，选择无符号类型
 *  使用int做整数运算，short往往太小而long一般和int尺寸一样。数值较大时使用long long
 *  算术表达书中一般不适用char或者bool。char在不同的机器上是否有符号不一定，容易出问题。
 *  浮点预算使用double。float通常精度不够并且float和double计算代价相差无几。
 * 
 *  算术表达式中既有int 又有无符号数时，int将默认转换为无符号数 (实测不会转换，具体行为取决于机器)所以尽量不要在表达式中同时出现带符号数和无符号数
 * 
 * 0 开头的字面值表示 8进制
 * 0x 开头的字面值表示 16进制
 * 具体字面值常量的类型将是可以容纳其值的最小的类型（不含short）。如果最大的数据类型都无法容纳将报错。
 * 浮点型字面值常量默认是double
 * 
 * 编译器将在字符串结尾添加空字符 ’\0‘,所以字符串字面值比实际长度长一个
 * 
 * 初始化和赋值是两个概念
 * 
 * 
 */

#include <iostream>

using namespace std;

int main(){
    int i = -9;
    unsigned char ui = 10;

    cout <<" result = "<< i + ui << endl;
    
    return 0;
}