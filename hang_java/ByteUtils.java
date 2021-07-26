public class ByteUtils {
    
    public static int bytes2int(byte[] bytes, int start, int length){
        int sum = 0;
        int end = start + length;

        for(int i = start; i< end; i++){
            int n = 0xFF & bytes[i];
            n <<= --length * 8;
            sum += n;
        }
        return sum;
    }


}
