
#include <vector>

using namespace std;

typedef int Rank;

class sort
{
    int _elem[];


    void swap(Rank lo, Rank hi)
    {
    }
    /**
 * 寻找快速排序的轴点，轴点未必存在，充要条件是此元素在初始序列和排序后的序列中位置一致。
 * 轴点不存在时需要人为构造轴点
 * */
    Rank partation(Rank lo, Rank hi)
    {
        swap(lo, (lo + random() % (hi - lo + 1)));//随机选取轴点
        int pivot = _elem[lo];

        while(lo < hi){
            while((lo < hi)&& pivot <= _elem[hi]){
                --hi;
            }
            _elem[lo] = _elem[hi];
            while ((lo < hi) && _elem[lo] <= pivot)
            {
                ++lo;
            }
            _elem[hi] = _elem[lo];
        }
        _elem[lo] = pivot;
        return lo;
    }

    /**
 * 快速排序
 * 
 * 分支策略，和归并排序一致。归并排序耗时在合并子向量上
 * 快速排序耗时在分割子向量上，也就是寻找分割的轴点
 * 
 * 最坏情况下复杂度O(n2) 每一步选择的轴点都在一侧导致一个子向量为空
 * 
 * 平均复杂度 O(nlogn)
 * */
    void quicksort(Rank lo, Rank hi)
    {
        if (hi - lo < 2)
        {
            return;
        }
        Rank mi = partation(hi, lo);
        quicksort(lo, mi);
        quicksort(mi, hi);
    }

    int main(int argc, char const *argv[])
    {

        return 0;
    }
};
