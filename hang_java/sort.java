public class sort {
    
    public static void main(String[] args) {
        int[] data = new int[]{5,4,3,2,1,5,7,9,76,43,32,12,21,4,35};
        sort(data, 0, data.length-1);
        for(int i : data){
            System.out.println(i);
        }
    }


    public static void sort(int[] data, int lo, int hi){
        if(hi <= lo){
            return ;
        }
        System.out.println("hi = " + hi + " lo = "+ lo);
        int round = (hi - lo) / 2 + lo;
        int parten = data[round];
        data[round] = data[lo];
        int start = lo, end = hi;

        while(hi > lo){
            while(parten < data[hi] && hi > lo){
                hi--;
                // System.out.println("h -- " + data[hi] );
            }
            if(hi <= lo){
                break;
            }
            data[lo++] = data[hi];
            while(parten > data[lo] && hi > lo){
                lo++;
                // System.out.println("lo ++ " + data[lo] );
            }
            if(hi <= lo){
                break;
            }
            data[hi--] = data[lo];
        }
        data[lo] = parten;
        sort(data, start, lo - 1);
        sort(data, lo + 1, end);
    }


}
