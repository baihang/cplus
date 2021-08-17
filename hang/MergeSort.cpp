#include <iostream>
#include <vector>

using namespace std;

void my_merge(vector<int> v, int lo, int mid, int hi)
{
    int values[hi - lo + 1];
    int start = lo, end1 = mid, index = 0;
    for(int start = 0; start < hi - lo + 1; start++)
    {
        // if(lo == end1){

        // }
        if (v[lo] <= v[mid])
        {
            values[start] = v[lo++];
        }
        else
        {
            values[start] = v[mid++];
        }
    }

    for (int i = 0; i < start; i++)
    {
        cout << values[i] << " - ";
    }
    cout << endl;
}

// void mergeSort(vector<int> *v, int lo, int hi)
// {
//     if (hi == lo)
//     {
//         return;
//     }
//     int mid = (hi + lo) / 2;
//     mergeSort(v, lo, mid);
//     mergeSort(v, mid + 1, hi);
//     merge(v, lo, mid + 1, hi);
// }

int main()
{
    vector<int> v = {4, 5,  8, 9, 1, 0, 10, 15 };
    my_merge(v, 0, 4, 7);
    return 0;
}