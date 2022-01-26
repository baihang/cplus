
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * compare sleep and wait
 * 
 * wait会释放锁，sleep不会。所以sleep可以用在同步块内和同步快外，wait只能用于同步块内
 * 
 * await 相比 wait来说更加灵活
 * 
 * LockSupport的 park unpark 也可以用来阻塞/唤醒线程
 * 
 * Thread.join 也可以阻塞线程
 */

class CompareSlppeWait {

    public static void main(String[] args) {

        testVolatile();

        // LockException exThread = new LockException("ex");
        // exThread.start();

        // SleepThread sleepThread = new SleepThread("sleep");
        // sleepThread.start();
        // WaitThread waitThread = new WaitThread("wait");
        // waitThread.start();
        // ReentrainThread reentrainThread = new ReentrainThread("reentrain");
        // reentrainThread.start();
    }

    private static class SleepThread extends Thread {

        public SleepThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("sleep thread run");
            try {
                Thread.sleep(1000);

                System.out.println("sleep 1s end");
                synchronized (CompareSlppeWait.class) {
                    System.out.println("sleep thread enter lock");
                    Thread.sleep(5000);
                    System.out.println("sleep 5s in lock end");
                    CompareSlppeWait.class.notify();
                    System.out.println("sleep thread notify wait");

                }

                lock.lock();
                System.out.println("sleep thread lock reetain");
                conditionC.signal();
                System.out.println("confition C signal");
                conditionA.await();
                conditionB.signal();
                lock.unlock();
                System.out.println("sleep thread end lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleep thread end");
        }
    }

    private static class WaitThread extends Thread {

        public WaitThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("wait therad run");
            try {
                System.out.println("wait 1s end");
                synchronized (CompareSlppeWait.class) {
                    System.out.println("wait thread enter lock");
                    CompareSlppeWait.class.wait(1000);
                    System.out.println("wait thread wait 1s end");
                    CompareSlppeWait.class.wait();
                    System.out.println("wait thread wait end");
                    CompareSlppeWait.class.wait(5000);
                    System.out.println("wait thread wait 5s end");
                }

                lock.lock();
                System.out.println("wait thread lock reetain");
                // conditionB.await();
                lock.unlock();
                System.out.println("wait thread end lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait thread end");
        }
    }

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    private static class ReentrainThread extends Thread {

        public ReentrainThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            lock.lock();
            try {
                conditionC.await();
                conditionA.signal();
                System.out.println("confition A signal");
                Thread.sleep(5000);
                conditionB.signal();
                System.out.println("confition B signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    /**
     * 测试同步块内出现异常，是否会释放锁
     */
    private static class LockException extends Thread {

        public LockException(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("lock exception start");
            try {
                lock.lock();
                int i = 10 / 0;
                lock.unlock();
            } catch (Exception e) {
                System.out.println("exception");
            } finally {
                //异常时锁不会默认被虚拟机释放，需要手动释放
                lock.unlock();
            }

            try {
                synchronized(CompareSlppeWait.class){
                    //异常时锁自动释放
                    int i = 10 / 0;
                }
            } catch (Exception e) {
                System.out.println("sync exception");
            }

        }
    }


    public static volatile int num = 1;
    /**
     * 最后结果值小于200000，说明volatile无法保证线程安全，因为 ++ 指令由四条
     * 字节码完成，volatile只能保证读取到的值为最新的，但是后续指令执行的时候值可能
     * 被其他线程更改导致后续操作使用的值为旧值。
     */
    private static void testVolatile(){
        int count = 20;
        for(int i = 0; i< count; i++){
            new Thread(){
                public void run() {
                    for(int j = 0; j < 10000; j++){
                        num++;

                    }
                };
            }.start();
        }
        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println("num = " + num);
    }
}