public class TestClass {
    private int num;

    public void inc(){
        Integer key = 0;
        synchronized(null){
            System.out.println("sync");
        }
    }

    public static void main(String[] args) {
        
    }
}
