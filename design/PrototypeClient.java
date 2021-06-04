package design;

import java.util.ArrayList;

/**
 * 原型模式
 * 
 *  Specify the kinds of objects to create using a prototypical
 *  instance, and create new object by copying this prototype.
 * 
 *  用原型实例指定创建对象的种类，并通过拷贝创建对象
 * 
 *  核心是实现cloneable 接口 
 * 
 *  通过直接复制对象，性能优于new。
 *  不会执行构造函数
 * 
 *  注意深拷贝和浅拷贝， 默认拷贝为浅拷贝，内部数据，引用不会拷贝，多个对象会持有相同的引用
 * 
 *  适用于先产生一个复杂对象，然后拷贝，仅修改副本的一些细节信息的情况
 */

public class PrototypeClient {
    
    public static void main(String[] args) {
        int count = 100000000;

        long t1 = System.currentTimeMillis();
        System.out.println(t1);
        for(int i = 0; i < count; i++){
            new PrototypeClass(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        PrototypeClass p = new PrototypeClass(0);
        for(int i = 0; i < count; i++){
            PrototypeClass cp =  (PrototypeClass)p.clone();
            cp.setStr(i);
        }
        long t3 = System.currentTimeMillis();
        System.out.println(t3 - t2);
    }


    public static class PrototypeClass implements Cloneable{

        private ArrayList<String> list = new ArrayList<>();

        private String name = "";

        public PrototypeClass(int i){
            list.add("test" + i);
            setStr(i);
        }

        public void setStr(int i){
            name = i+"";
        }

        @Override
        protected Object clone() {
            PrototypeClass prototype = null;
            try {
                prototype = (PrototypeClass)super.clone();
                //深拷贝
                prototype.list = (ArrayList<String>)list.clone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return prototype;
        }
    }

}
