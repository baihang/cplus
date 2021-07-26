import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestClassLoader {
    public static void main(String[] args) {
        CustomClassLoader classLoader = new CustomClassLoader("/Users/edz/work/cplus/hang_java");
        try {
            Class c = classLoader.findClass("TestClass");
            if(c != null){
                try {
                    Object ob = c.newInstance();
                    Method method = c.getDeclaredMethod("inc", null);
                    method.invoke(ob, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
