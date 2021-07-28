import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] data = { 6, 3, 5, 7, 2, 4, 1, 9 };
        sort(data, 0, data.length - 1);
    }

    public static void sort(int[] data, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        System.out.println("lo = " + lo + " hi = " + hi);
        swip(data, lo, ((int)Math.random() % (hi - lo)) + lo);
        int start = lo, end = hi;
        /**
         * 选择首元素作为轴点，选取之前随机选取元素与首元素互换，以免完全逆序的情况下退化至O(n^2)
         */
        
        int partition = data[lo];
        while (lo != hi) {
            while (lo != hi) {
                if (data[hi] < partition) {
                    data[lo++] = data[hi];
                    break;
                } else {
                    hi--;
                }
            }
            while (lo != hi) {
                if (partition < data[lo]) {
                    data[hi--] = data[lo];
                    break;
                } else {
                    lo++;
                }
            }
        }
        data[lo] = partition;
        for (int i : data) {
            System.out.println(i);
        }
        sort(data, start, lo - 1);
        sort(data, lo + 1, end);
    }

    public static void swip(int[] data, int i, int j){
        int a = data[i];
        data[i] = data[j];
        data[j] = a;
    }

}
