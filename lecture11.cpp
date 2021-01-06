#include <map>
#include <set>
#include <string>
#include <iostream>

//using std::string;
//using std::map;
using namespace std;

int main()
{
    map<string, size_t> word_count;
    string word;

    while (cin >> word)
    {
        if (word == "0")
        {
            break;
        }

        //insert 返回值 ret 是一个pair, first 成员是一个指向给定关键字的迭代器
        //second 是一个bool值， false 标示关键字已经存在于容器，insert未进行任何操作， true 标示关键字之前不存在，元素被插入容器。

        //pair<map<string, size_t>::iterator, bool> ret; ret类型
        auto ret = word_count.insert({word, 1});
        if (!ret.second)
        {
            ++ret.first->second;
        }
    }

    for (auto cword = word_count.cbegin(); cword != word_count.cend(); ++cword)
    {
        cout << "word = " << cword->first << " times = " << cword->second << " ";
    }

    cout << endl;

    return 0;
}