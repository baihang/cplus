/**
 * 参考 邓俊辉教授所著的《数据结构》
 * */
typedef int Rank;
#define DEFAULT_CAPACITY 3

namespace hang
{

    template <typename T>
    class Vector
    {
    protected:
        Rank _size;
        int _capacity;
        T *_elem;

        void copyFrom(T *A, Rank lo, Rank hi);
        void expand();                 //扩容
        void shrink();                 //缩容
        bool bobble(Rank lo, Rank hi); //扫描交换
        void bubbleSort(Rank lo, Rank hi);
        Rank max(Rank lo, Rank hi);
        void selectionSort(Rank lo, Rank hi);
        void merge(Rank lo, Rank mi, Rank hi);
        void mergeSort(Rank lo, Rank hi);
        void partition(Rank lo, Rank hi); //轴点构造算法
        void quickSort(Rank lo, Rank hi);
        void heapSort(Rank lo, Rank hi);

        int main(){
            return 0;
        }
    };

} // namespace hang
