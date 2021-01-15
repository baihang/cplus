#include <map>
#include <set>
#include <string>
#include <iostream>
#include <fstream>
#include <sstream>

//using std::string;
//using std::map;
using namespace std;

map<string, string> buildMap(fstream &file_in)
{
    map<string, string> maps;
    string key, value;

    //先读取key， 剩下的内容为value
    // >> 符号不可读取空格，
    while (file_in >> key && getline(file_in, value))
    {
        if (value.size() > 1)
        { //使用下标添加map值，将保留最后一次添加值
            maps[key] = value.substr(value.find_first_not_of(' '), value.find_last_not_of(' '));
            cout << "debug key = " << key << " value = " << maps[key] << endl;
        }
        else
        {
            throw runtime_error("no roul for " + key);
        }
    }
    return maps;
}

string transform(string word, map<string, string> maps)
{
    //find 返回指定元素的迭代器
    auto result = maps.find(word);
    if (result != maps.end())
    {
        return result->second;
    }
    else
    {
        return word;
    }
}

/**
 * 读取给定的输入文件创建转换规则map
 * */
void word_transform(fstream &in_map, fstream &input)
{
    string str;
    auto maps = buildMap(in_map);

    while (getline(input, str))
    {
        istringstream str_stream(str);
        string word;
        bool first = true; //控制打印空格
        while (str_stream >> word)
        {
            if (first)
            {
                first = false;
            }
            else
            {
                cout << " ";
            }
            cout << transform(word, maps);
        }
        cout << endl;
    }
}

/**
 *  11.3.6 单词转换程序
 * 功能： 给定一个string, 根据给定规则转换为另一个string
 * */
void wordMap()
{
    fstream finput("lecture11_input_file");
    fstream fmap("lecture11_keymap_file");
    if(finput && fmap){
        word_transform(fmap, finput);
    }
}

int main()
{

    wordMap();

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