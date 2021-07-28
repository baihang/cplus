public class HeapSort {
    public static void main(String[] args) {
        int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        sort(data);
    }

    public static void sort(int[] data){
        heapBuild(data);
        int n = data.length;
        while(--n >= 0){
            swip(data, 0, n);
            percolateDown(data, 0, n);
        }
        for (int i : data) {
            System.out.println(i);
        }
    }

    /**
     * 建堆，节点不小于子节点 从最后一个非叶节点开始下滤。最后一个非叶节点为 n / 2 - 1
     */
    public static void heapBuild(int[] data) {
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            percolateDown(data, i, data.length);
        }
    }

    public static void percolateDown(int[] data, int i, int n){
        int result = i;
        while ((result = compareAndSwip(data, i, n)) != i) {
            i = result;
        }
    }

    public static int compareAndSwip(int[] data, int i, int n) {
        int leftIndex = i * 2 + 1;
        int rightIndex = leftIndex + 1;
        if (rightIndex >= n) {
            rightIndex = i;
        }
        if (leftIndex >= n) {
            leftIndex = i;
        }
        int result = max(data, i, leftIndex, rightIndex);
        swip(data, i, result);
        return result;
    }

    public static int max(int[] data, int root, int lc, int rc) {
        if (data[root] >= data[lc] && data[root] >= data[rc]) {
            return root;
        } else if (data[lc] > data[root] && data[lc] >= data[rc]) {
            return lc;
        } else {
            return rc;
        }
    }

    public static void swip(int[] data, int i, int j) {
        if (i == j)
            return;
        int a = data[i];
        data[i] = data[j];
        data[j] = a;
    }
}
