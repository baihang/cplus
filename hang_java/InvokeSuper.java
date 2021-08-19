import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * 访问祖类中的方法
 */
public class InvokeSuper {

    private static class GrardFather{
        public void speak(){
            System.out.println("i am grand father");
        }
    }

    private static class Father extends GrardFather{
        @Override
        public void speak() {
            System.out.println("i am father");
        }
    }

    private static class Son extends Father{
        @Override
        public void speak() {
            System.out.println("i am son");
            //注意 void ！= Void
            MethodType mType = MethodType.methodType(void.class);
            System.out.println("typoe = " + mType);
            try {
                Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                field.setAccessible(true);
                MethodHandles.Lookup lookup = (MethodHandles.Lookup)field.get(null);
                System.out.println("lok up = " + lookup);
                //最后一个参数必须是 GrandFather.class,如果使用 getClass()，则拿到的父类的speak
                MethodHandle handle = lookup.findSpecial(GrardFather.class, "speak", mType, GrardFather.class);
                handle.invoke(this);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable e){
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {
        Son son = new Son();
        son.speak();
    }

}
