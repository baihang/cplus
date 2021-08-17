import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BiggerSum {

    public static void main(String[] args) {
        // System.out.println(resolve2("9", "99999999999999999999999999979999999999999999999999999999999994"));
        // maxbigger(new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7, 0,2,  6, 7 ,8});
       System.out.println(numMulti(1235564, 4567));
       BigDecimal a = new BigDecimal("2.0");
       a = a.subtract(new BigDecimal("1.1"));
       System.out.println(a);

    }

    public static String solve(String s, String t) {
        // write code here
        int length = Math.max(s.length(), t.length());
        char[] sr = s.toCharArray(), tr = t.toCharArray();
        StringBuilder builder = new StringBuilder();
        int out = 0;
        for (int i = s.length() - 1, j = t.length() - 1; i >= 0 && j >= 0; i--, j--) {
            int k = sr[i] + tr[j] - '0' * 2 + out;
            out = k / 10;
            k = k % 10;
            builder.append(k);
        }
        String maxStr = s.length() > t.length() ? s : t;
        char[] max = maxStr.toCharArray();
        int index = s.length() > t.length() ? tr.length : sr.length;
        index = length - index - 1;
        while (out > 0) {
            if (index < 0) {
                builder.append(1);
                break;
            }
            int k = max[index] - '0' + out;
            out = k / 10;
            k = k % 10;
            builder.append(k);
            index--;
        }
        if (index > 0) {
            maxStr = maxStr.substring(0, index);
        } else {
            maxStr = "";
        }
        return maxStr + builder.reverse().toString();
    }

    //未完成，待续
    public static String resolve2(String s, String t) {
        int length = String.valueOf(Integer.MAX_VALUE).length() - 1;
        int maxOut = 1 << length;
        String max, min;
        if (s.length() > t.length()) {
            max = s;
            min = t;
        } else {
            max = t;
            min = s;
        }
        int si = max.length() , ti = min.length();
        int out = 0;
        StringBuilder builder = new StringBuilder();
        for (; si >=0  && ti >= 0; si -= length, ti -= length) {
            int ss = si > length ? si - length : 0;
            int ts = ti > length ? ti - length : 0;
            int sv = Integer.parseInt(max.substring(ss, si));
            int tv = Integer.parseInt(min.substring(ts, ti));
            int result = sv + tv + out;
            out = result >= maxOut ? 1 : 0;
            builder.append(String.valueOf(result).substring(out));
        }
        String maxStr = "";
        if (si > 0) {
            maxStr = max.substring(0, si);
        }
        return maxStr + builder.reverse().toString();
    }

    //最长递增子序列，现在可以找到最长递增子序列的长度，接下来需要寻找字典序最小的那一组解
    public static void maxbigger(int[] data){
        int[] status = new int[data.length];
        for(int i = 0; i< status.length; i++){
            status[i] = 1;
        }
        int max = 1;
        for(int i = 1; i< data.length; i++){
            for(int j = i; j >= 0; j--){
                if(data[i] > data[j]){
                    status[i] = status[j] + 1;
                    max = max >= status[i] ? max : i;
                    break;
                }
            }
        }
        for(int i : status){
            System.out.println(i);
        }
        
    }

    /**
     * 乘法，将a, b 分解
     * 注意越界
     */
    public static long numMulti(int a, int b){
        if(a < 99 || b < 99){
            return a * b;
        }
        int aSize = String.valueOf(a).length();
        int bSize = String.valueOf(b).length();

        int size = Math.max(aSize, bSize);
        if(size % 2 != 0)
        {
            size++;
        }

        int pow = (int)(Math.pow(10, size / 2));
        int c = a / pow, d = a % pow;
        int m = b / pow, n = b % pow;

        long cm = numMulti(c, m);
        long dn = numMulti(d, n);
        long dcmn = numMulti(d + c, m + n) - dn - cm;

        long result = cm * (int)Math.pow(10, size) + dcmn * pow + dn;
        return result;
    }

}
