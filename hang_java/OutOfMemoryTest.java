import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import sun.misc.Unsafe;


public class OutOfMemoryTest {
    
    static TestFinalizeEscape escape = new TestFinalizeEscape();

    public static void main(String[] args) {
        System.out.println("OutOfMemoryTest");
        // testHeap();
        // testStack();
        // testLocalMethod();
        // testDirectMemory();
        
        testFinalize();
        testFinalize();
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

    /**
     * 测试方法区溢出
     * 使用CGLib动态生成大量的类，方法区载入类时内存不足会溢出
     * 
     */
    public static void testMethodArea(){
// Enhancer
    }

    /**
     * 测试直接内存溢出
     * 使用UNsafe分配直接内存
     * 
     *  如何查看直接内存？
     * 
     * -Xmx 20M -XX:MaxDirectMemorySize=10M
     */
    public static void testDirectMemory(){
        Field field =  sun.misc.Unsafe.class.getDeclaredFields()[0];
        System.out.println(field.getName());
        field.setAccessible(true);
        try{
            sun.misc.Unsafe unsafe = (Unsafe) field.get(null);
            while(true){
                unsafe.allocateMemory(1024 * 1024);//1MB
            }
        }catch(Exception e){
            e.printStackTrace();
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


    /**
     * 对象被回收之前会有一次使用finalize自救的机会，但是最多调用一次
     * 
     */
    public static void testFinalize(){
        escape = null;
        System.gc();
        try {
            //finalizer优先级低，等待它执行完
            Thread.sleep(2000);            
        } catch (Exception e) {
            //TODO: handle exception
        }
        if(escape != null){
            escape.isAlive();
        }else{
            System.out.println("escape is dead");
        }
    }

    public static class TestFinalizeEscape{
        public void isAlive(){
            System.out.println("is alive");
        }


        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize invoke");
            super.finalize();
            escape = this;
        }
    }
}
