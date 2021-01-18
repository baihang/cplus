/**
 *  10.3.2 节 
 * */

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using std::cout;
using std::string;
using std::vector;

/**
 * 字典序 判断大小
 * */
bool compareStr(const string &s1, const string &s2)
{
    auto begin = s1.begin();
    auto end = s1.end();
    auto s2begin = s2.begin();
    auto s2end = s2.end();

    while (begin != end && s2begin != s2end)
    {
        if (*begin > *s2begin)
        {
            return true;
        }
        else if (*begin < *s2begin)
        {
            return false;
        }
        ++begin;
        ++s2begin;
    }
    //如果是子序列，则判断长短
    return s1.size() > s2.size();
}

/**
 * 按字典排序，删除重复单词
 * */
void elimDups(vector<string> &words)
{
    //选择排序
    for (auto begin = words.begin(), end = words.end(); begin != end; ++begin)
    {
        auto maxPtr = begin;
        for (auto start = begin + 1; start != end; ++start)
        {
            if (compareStr(*start, *maxPtr))
            {
                maxPtr = start;
            }
        }
        auto str = *begin;
        *begin = *maxPtr;
        *maxPtr = str;
    }

    //去重
    auto size = words.begin();
    for (auto begin = words.begin() + 1, end = words.end(); begin != end; ++begin)
    {
        if (*begin != *size)
        {
            size++;
            *size = *begin;
        }
    }

    words.erase(++size, words.end());
}

string make_plural(unsigned int count, string s, string suf)
{
    return count > 1 ? s + suf : s;
}

void beggies(vector<string> &words, string::size_type sz)
{
    elimDups(words);

    stable_sort(words.begin(), words.end(), [](const string &s1, const string &s2) {
        return s1.size() < s2.size();
    });

    auto w = find_if(words.begin(), words.end(), [sz](const string &s) {
        return s.size() >= sz;
    });

    auto count = words.end() - w;

    cout << "has " << count << make_plural(count, " word", "s") << " of length" << std::endl;

    for_each(w, words.end(), [](const string &s) {
        cout << s << " ";
    });
    cout << std::endl;
}

int main()
{

    vector<string> words = {"hello", "hello", "hello", "test", "a", "ba", "bc"};

    beggies(words, 5);

    string s1 = "hzllo";
    string s2 = "horld";
    cout << " s1 = " << s1 << " s2 = " << s2 << " compare " << compareStr(s2, s1) << std::endl;
    return 0;
}