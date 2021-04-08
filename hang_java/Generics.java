package hang_java;

import java.lang.Iterable;
import java.util.Iterator;

import jdk.javadoc.internal.doclets.formats.html.resources.standard;

/**
 * java 泛型
 * 
 * 使用类或者接口作为参数可以实现类似泛型的功能，这样所有的派生类都可以作为参数 所以如果使用Object作为参数则任何类都可以使用。
 * 
 * 但是使用泛型的地方并不是不指定类型而是稍后指定类型。
 * 
 * 所以泛型的核心概念：告诉编译器想要什么类型，然后由编译器实现细节
 * 
 * 基本类型无法作为类型参数
 */
public class Generics {

    // 使用泛型生成一个通用元组
    // 由于return仅返回一个对象，所以元组通常用来解决需要返回多个对象时，将其打包为一个对象
    public static class DoubleTuple<A, B> {
        // final 保证元素无法被修改
        private final A first;
        private final B second;

        public DoubleTuple(A a, B b) {
            first = a;
            second = b;
        }
    }

    public static class MethodGen<A>{
        //泛型方法，类型在返回值之前 
        public <T> void getGen(){

        }

        //static 方法无法获取类的类型参数，所以必须成为泛型方法
        public static <A> void getA(){

        }
    }

    // 生成器，专门用来创建对象的类，工厂模式的一种
    public static interface Geterator<T> {
        T next();
    }

    public static abstract class Coffe {

    }

    public static class Coffe1 extends Coffe {
    }

    public static class Coffe2 extends Coffe {
    }

    public static class CoffeGenerator implements Geterator<Coffe> {
        private static Class[] classes = { Coffe1.class, Coffe2.class };

        @Override
        public Coffe next() {
            try {
                return (Coffe) classes[0].newInstance();
            } catch (SecurityException | InstantiationException | IllegalAccessException e) {
                // TODO: handle exception
            }
            return null;
        }
    }
    // -----------

    // 基本类型无法作为类型参数，所以需要使用Integer 而不是int
    public static class Fibonacci implements Geterator<Integer> {
        private int n = 0;

        @Override
        public Integer next() {
            return fib(n++);
        }

        private int fib(int k) {
            if (k < 2)
                return 1;
            return fib(k - 2) + fib(k - 1);
        }

        public static void main(String[] args) {
            Fibonacci fibonacci = new Fibonacci();
            for (int i = 0; i < 10; i++) {
                System.out.println(fibonacci.next());
            }
        }

    }
    //使用装饰器模式，在不改变Fibonacci的情况下实现迭代器
    public static class IteratorFibonacci extends Fibonacci implements Iterable<Integer> {

        private int count;

        public IteratorFibonacci(int n) {
            count = n;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                @Override
                public boolean hasNext() {
                    return count > 0;
                }

                @Override
                public Integer next() {
                    count--;
                    return IteratorFibonacci.this.next();
                }
            };
        }

        public static void main(String[] args) {
            IteratorFibonacci fab = new IteratorFibonacci(10);
            for (int i : fab) {
                System.out.println(i);
            }
        }

    }

}
