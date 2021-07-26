/**
 * @file regular.cpp
 * @author hang (1448863597@qq.com)
 * @brief 
 * @version 0.1
 * @date 2021-01-23
 * 
 * @copyright Copyright (c) 2021
 * leetcode 正则表达式匹配
 *  . 匹配单个任意字符
 *  * 匹配任意数量的前一个字符
 */

#include <string>
#include <iostream>
#include <vector>
#include <utility>
#include <stack>
#include <iterator>

using namespace std;

bool compare(char a, char b)
{
    // cout << a << " compare to " << b << endl;
    return a == b || a == '.';
}

bool compare(pair<char, int> *a, pair<char, int> *b)
{
    //处理边界
    if (a == nullptr)
        return b == nullptr;
    if (b == nullptr)
        return a->second == 0;
    //匹配
    if (compare(a->first, b->first))
    {
        if (a->second <= 0)
            return -(a->second) <= b->second;
        else
            return a->second == b->second;
    }
    return false;
}

/**
 * @brief 预处理
 * 
 */
pair<vector<pair<char, int> >, vector<pair<char, int> > > preProcess(string s, string p)
{
    //预处理，删除开头的*和连续的*
    vector<pair<char, int> > sv, pv;

    for (auto begin = s.begin(), end = s.end(); begin != end; ++begin)
    {
        if (sv.size() > 0 && *begin == sv.rbegin()->first)
        {
            sv.rbegin()->second++;
        }
        else
        {
            pair<char, int> item(*begin, 1);
            sv.push_back(item);
        }
    }
    for (auto begin = p.begin(), end = p.end(); begin != end; ++begin)
    {
        if (pv.size() == 0)
        {
            //首字符是*的过滤掉
            if (*begin == '*')
            {
                continue;
            }
            pv.push_back(make_pair(*begin, 1));
            continue;
        }
        //过滤连续的*
        if (*begin == '*' && *(begin - 1) == '*')
        {
            continue;
        }

        auto pv_end = pv.rbegin();

        if (*begin == pv_end->first)
        {
            pv_end->second > 0 ? pv_end->second++ : pv_end->second--;
        }
        else
        {
            if (*begin == '*')
                pv_end->second = pv_end->second > 0 ? -(pv_end->second - 1) : pv_end->second + 1;
            else
            {
                pair<char, int> item(*begin, 1);
                pv.push_back(item);
            }
        }
    }
    return make_pair(sv, pv);
}

bool match(string s, string p)
{
    if (s == p)
    {
        return true;
    }
    if (s.length() == 0 || p.length() == 0)
    {
        return false;
    }
    auto pre = preProcess(s, p);
    vector<pair<char, int> > sv = pre.first, pv = pre.second;
    cout << "p size = " << pv.size() << endl;
    auto pbegin = pv.begin(), pend = pv.end();
    auto sbegin = sv.begin(), send = sv.end();
    vector<vector<int> > result;
    stack<pair<int, int> > back;

    int index_w = 0, index_h = 0;

    while (true)
    {
        if (pbegin == pend && sbegin == send && back.empty())
        {
            break;
        }
        if (pbegin->first == '.' && pbegin->second == 0)
        {
            back.push(make_pair(pend - pbegin, send - sbegin));
        }
        if (compare(&*pbegin, &*sbegin))
        {
            pbegin++;
            sbegin++;
            index_w++;
            index_h++;
            result[index_w][index_h] = 1;
        }
        else
        {
            //回退至 .*
            auto b = back.top();
            pbegin = pend - b.first;
            sbegin = send - b.second;
            back.pop();
        }
    }
    return result[pv.size()][sv.size()] == 1;
}

void toString(vector<vector<int> > result, string s, string p)
{

    for (auto begin = s.begin(); begin != s.end(); begin++)
    {
        cout << "  " << *begin;
    }
    cout << endl;
    auto p_head = p.begin();

    for (auto begin = result.begin(); begin != result.end(); begin++)
    {
        cout << *p_head;
        p_head++;
        for (auto sbegin = (*begin).begin(); sbegin != (*begin).end(); sbegin++)
        {
            cout << "  " << *sbegin;
        }
        cout << endl;
    }
}

bool match_recursion(string s, string p)
{
    if (s.length() == 0 || p.length() == 0)
    {
        return s.length() == 0 && p.length() == 0;
    }
    string::iterator pbegin = p.begin(), pend = p.end();
    auto sbegin = s.begin(), send = s.end();

    while (sbegin != send || pbegin != pend)
    {
        if (*pbegin == '*')
        {
            return match_recursion(string(sbegin, send), string(--pbegin, pend));
        }
        else if (compare(*pbegin, *sbegin))
        {
            pbegin++;
            sbegin++;
        }
        else
        {
            return false;
        }
    }
    return sbegin != send && pbegin != pend;
}

bool match_iteration(string s, string p)
{
    cout << s << endl
         << p << endl;
    vector<vector<int> > result(p.length());
    for (int j = 0; j < p.length(); j++)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if (p[j] == '*' && j != 0)
            {
                result[j - 1][i] *= 2;
            }
            // result[i].push_back(compare(p[j - 1], s[i]));
            else
                result[j].push_back(compare(p[j], s[i]));
        }
    }
    cout << "compare iterator" << endl;
    toString(result, s, p);
    match_recursion(s, p);
    return false;
}

int main(int argc, char const *argv[])
{
    string s[] = {"aab", "aaabc", "mississippi", "aab", "aabfghacdb"};
    string p[] = {".*...*cc*c", "a*abc", "mis*is*p*.", "a.b", "aaa*.*aaaa*cdb"};
    // s = argv[1];
    // p = argv[2];
    for (size_t i = 0; i < 5; i++)
    {
        // bool result = match(s[i], p[i]);
        match_iteration(s[i], p[i]);
        // cout << "match " << s[i] << " and " << p[i] << " = "
        //      << result << endl;
    }

    return 0;
}
