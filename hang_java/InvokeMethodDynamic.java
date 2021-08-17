/**
 * 目标：动态加载临时调试代码，可多次加载，不依赖特定jdk特性，不改变原程序运行，执行结果返回。
 * 
 * 思路：
 * 1. 如何编译临时代码
 * 2. 如何执行编译后的临时代码
 *    使用自定义加载器加载指定代码，反射调用目标方法。加载的类支持卸载回收。
 * 3. 如何收集返回的结果
 *    动态修改字节码中的符号引用，使自定义的PrintStream代替System.out输出
 * 
 * 
 */
public class InvokeMethodDynamic {
    
    /**
     * 实现一个类的代码可多次加载
     */
    public static class HotSwapClassLoader extends ClassLoader{
        public HotSwapClassLoader(){
            super(HotSwapClassLoader.class.getClassLoader());
        }

        public Class loadByte(byte[] classBytes){
            return defineClass(null, classBytes, 0, classBytes.length);
        }
    }

    /**
     * 修改class文件常量池，替换System为HackSystem
     */
    public static class ClassModifier{

        /**
         * class文件中常量池起始位置
         */
        private static final int CONSTANT_POOL_COUNT_INDEX = 8;

        /**
         * utf8常量的tag标志
         */
        private static final int CONSTANT_UTF8_INFO = 1;

        /**+
         * 常量池中11种类型的长度，utf8除外，它不定长
         */
        private static final int[] CONSTANT_ITEN_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

        private static final int u1 = 1;
        private static final int u2 = 2;

        private byte[] classBytes;

        public ClassModifier(byte[] classBytes){
            this.classBytes = classBytes;
        }

        /**
         * 操作依赖ByteUtils
         * @return
         */
        public int getConstantPoolCount(){
            return ByteUtils.bytes2int(classBytes, CONSTANT_POOL_COUNT_INDEX, u2);
        }

        /**
         * 修改常量池中的utf8类型指定内容
         * @param old 修改前字符串
         * @param newStr 目标字符串
         * @return 修改后的字节码数组
         */
        // private byte[] modifyUTF8Constant(String old, String newStr){

        // }
    }

    

}
