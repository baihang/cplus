#include <string>
#include <iostream>

using std::string;

int main()
{

    const string hexdigits = "0123456789ABCDEF";
    std::cout << "Enter a series of numbers , Hit Enter when finish" << std::endl;

    string result;
    string::size_type index = 0;  //使用size_type 定义 index
    // decltype(result.size()) index = 0; //使用size_type 定义 index 2

    while(std::cin >> index){
        if(index < hexdigits.size()){
            result += hexdigits[index];
        }
    }

    std::cout << "result = " << result << std::endl;

    return 0;
}