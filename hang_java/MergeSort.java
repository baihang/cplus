public class MergeSort {
    static int[] result; 
    public static void main(String[] args) {
        int[] data = {5, 3, 6, 4, 9, 1, 0, 10, 8};
        // merge(data, 0, 4, 7);
        result = new int[data.length];
        mergeSort(data);
    }
    

    public static void mergeSort(int[] data){
        if(data == null || data.length == 0){
            return;
        }
        try {
            mergeSort(data, 0, data.length);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mergeSort(int[] data, int lo, int hi){
        System.out.println("lo = " + lo + " hi = " + hi);
        if(hi - lo < 2){
            return;
        }
        int mid = (hi + lo) / 2;
        mergeSort(data, lo, mid);
        mergeSort(data, mid, hi);
        merge(data, lo, mid, hi);
    }

    public static void merge(int[] target, int start, int mid, int end){
        int end1 = mid, index = 0, start_back = start;
        while(start != end1){
            if(start == end1){
                result[index] = target[mid++];
            }else if(mid == end){
                result[index] = target[start++];
            }else{
                result[index] = target[start] <= target[mid] ? target[start++] : target[mid++];
            }
            index++;
        }
        for (int i = 0; i< index; i++) {
            target[start_back + i] = result[i];
        }

        for (int i : target) {
            System.out.println(i);
        }
    }
}
