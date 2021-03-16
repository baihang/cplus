package hang_java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * compare sleep and wait
 * 
 * wait会释放锁，sleep不会。所以sleep可以用在同步块内和同步快外，wait只能用于同步块内
 * 
 * await 相比 wait来说更加灵活
 */

class CompareSlppeWait {

    public static void main(String[] args) {
        SleepThread sleepThread = new SleepThread();
        sleepThread.start();
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        ReentrainThread reentrainThread = new ReentrainThread();
        reentrainThread.start();
    }

    private static class SleepThread extends Thread {
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

}