import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args) {
        testThreadPool();
    }
    static volatile int count = 0;
    public static void testThreadPool(){
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10);
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, queue, new ThreadFactory(){
            
            public Thread newThread(Runnable r) {
                return new Thread(r, "test_thread_pool—" + count++);
            };
        });

        for(int i = 0; i< 30; i++){
            executor.execute(new Runnable(){
                @Override
                public void run() {
                    System.out.println("thread run " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);                        
                    } catch (Exception e) {
                        e.fillInStackTrace();
                    }
                    System.out.println("thread end ---- " + Thread.currentThread().getName());
                }
            });
            System.out.println("queue size = " + queue.size());

        }
System.out.println("active count = " + executor.getActiveCount());
        while(executor.getActiveCount() > 0){
            try {
                Thread.sleep(1000);                
            } catch (Exception e) {
                
            }
        }

    }

}
