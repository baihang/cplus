import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

    private String path;

    public CustomClassLoader(String path) {
        this.path = path;
    }

    /**
     * 在loadClass中调用。由loadClass保证双亲委托模式
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        byte[] data = loadData(name);
        if(data == null){
            throw new ClassNotFoundException();
        }else{
            clazz = defineClass(name, data, 0, data.length);
        }
        return clazz;
    }

    private byte[] loadData(String className) {
        String name = getClassName(className);
        System.out.println("name = " + name);
        File file = new File(path, name);
        InputStream inputStream = null;
        ByteArrayOutputStream out = null;

        try {
            inputStream = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[4 * 1024];
            int length = 0;
            while ((length = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            try{
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getClassName(String name) {
        int index = name.lastIndexOf('.', 0);
        if (index == -1) {
            return name + ".class";
        } else {
            return name.substring(index + 1) + ".class";
        }
    }
}
