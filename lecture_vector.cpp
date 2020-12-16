#include <iostream>
#include <vector>

using std::string;
using std::vector;
/**
 * vector 属于类模板
 * vector 可以高效增长，所以即使知道最终长度。也推荐初始化为空vector对象，所以与Java等语言预先指定容量的做法不一致
 * 
 * 不能用下标形式添加元素，需要使用push_back。可以使用下标访问已经存在的元素，但是不可以添加元素。
 * 通过下标访问不存在的元素会导致缓冲区溢出，导致安全问题。
 * 
 * 迭代器：当且仅当它支持一套操作，使我们可以访问容器元素或者从一个元素移动到另一个元素
 * 
 * 使用迭代器（iterator）代替下标运算，所有的标准容器都支持迭代器，少数支持下标运算符。
 * 迭代器包含 begin 和 end 两个成员
 * end 指向容器 ”尾元素的下一位置 （off the end）“,仅仅是个标记，不指示某个元素，所以无法解引用。
 * 
 * iterator
 * const_iteratoe
 * 
 * 箭头运算符（->）:箭头运算符把解引用和成员访问两个操作结合在一起 it -> mem 等价于 (*it).mem
 * 
 * 任何一个会改变vector容量的操作都会导致迭代器失效(为了扩容拷贝到了内存其他位置)
 * 
 * */
int main()
{
    //初始化
    vector<string> s1 = {"hello"};  //列表初始化(list initialize)，s1含有一个 ‘hello’ 元素
    vector<string> s2{10};          //类型不一致，无法列表初始化，所以编译器尝试使用默认值初始化， s2含有10个默认初始值的元素
    vector<string> s3(10);          //默认值初始化(construct)，10个默认元素
    vector<string> s4(10, "hello"); // 含有10个 ‘hello’
    vector<string> s5{10, "hello"}; // 无法列表初始化，使用默认值初始化，相当于（10， ”hello“）含有10个 ‘hello’

    //二分查找
    vector<int> integers{1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12};
    auto begin = integers.begin();
    auto end = integers.end();
    auto mid = begin + (end - begin) / 2;

    std::cout << "diff = " << (end - begin) / 2 << std::endl;

    int target = 1;

    while (begin != end && *mid != target)
    {
        std::cout << "begin = " << *begin
                  << "end = " << *end
                  << "mid = " << *mid << std::endl;
        if (target < *mid)
        {
            end = mid;
        }
        else
        {
            begin = mid + 1;
        }
        mid = begin + (end - begin) / 2;
        
    }

    return 0;
}