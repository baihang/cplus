import java.util.ArrayList;
import java.util.List;



public class OutOfMemoryTest {
    

    public static void main(String[] args) {
        System.out.println("OutOfMemoryTest");
        // testHeap();
        // testStack();
        testLocalMethod();
    }

    /**
     * 测试堆溢出
     * java  -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError OutOfMemoryTest.java
     * VM arg: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * 
     * -Xms  设置堆最小值
     * -XX:+HeapDumpOnOutOfMemoryError  溢出时dump出快照
     */
    public static void testHeap(){
        List<OomObject> list = new ArrayList<>();
        while(true){
            list.add(new OomObject());
        }
    }

    /**
     * 测试栈溢出，如果虚拟机不支持栈拓展，只会抛出 StackOverflowError
     * 如果支持拓展，既可以有 StackOverflowError 也可以 OutOfMemoryError
     * 
     * 多线程下，如果栈容量过大，线程过多会导致内存容量耗尽，无法为新线程分配栈空间，从而导致 OutOfMemoryError
     * 这种情况下如果无法减少线程数量，由于操作系统分配给进程的内存容量有限，可能需要减少堆内存来增大栈可用内存。
     * VM -Xss128k
     * 
     *  -Xss 减少栈容量，
     */
    public static void testStack(){
        StackClass stack = new StackClass();
        try {
            stack.test();            
        } catch (Exception e) {
            System.out.println(stack.stackLength);

            // e.printStackTrace();
        }
    }

    /**
     * 测试运行时常量池和方法区（运行时常量池是方法区一部分）
     * java 7之前使用永久代，Java 7开始使用元空间，在堆中实现
     * java 7之前使用 -XX:PermSize 和 -XX:MaxPermSize 限制永久代大小
     * Java 7及之后限制堆大小达到目的  -Xmx6m
     * 
     * String::intern 将不在常量池中的字符串放入常量池
     */
    public static void testLocalMethod(){
        int i = 0;
        List<String> list = new ArrayList<>();
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }

    static class StackClass{
        private int stackLength = 1;

        public void test(){
            stackLength++;
            test();
        }
    }


    static class OomObject{}
}
