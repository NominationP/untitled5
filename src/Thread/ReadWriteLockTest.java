package Thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的案例
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {

        final Queues queues = new Queues();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            new Thread() {
                public void run() {
                    //此处打标记A，下面注释会提到
                    /*if(j<10)*/
                    while (true) {
                        queues.get();
                    }
                }
            }.start();
            new Thread() {
                public void run() {
                    /*if(j<10)*/
                    while (true) {
                        queues.put(new Random().nextInt(10000));
                    }
                }
            }.start();
        }
    }
}

class Queues {
    // 共享数据;只能有一个线程能写改数据，但能有多个线程同时读数据
    private Object data = null;
    /*这里如果这么写：
     *   ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
          上面的标记A处，如果用while(true)会一直写不读，但是如果不用while死循环则可以正确执行，
          比如用if(i<10);目前还没找到原因，希望大牛们看到后指点迷津;
         个人猜测：没有用面向接口编程，上锁后，死循环中的内容会无穷之的执行，执行不完不会开锁
    */
    ReadWriteLock rwl = new ReentrantReadWriteLock();

    // 读的方法，用的是读锁readLock()
    public void get() {
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()
                    + " be ready to read data！");
            Thread.sleep((long) Math.random() * 1000);
            System.out.println(Thread.currentThread().getName()
                    + " have read data:" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }

    // 写的方法；用到写的锁：writeLock()
    public void put(Object data) {
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()
                    + " be ready to write data！");
            Thread.sleep((long) Math.random() * 1000);
            this.data = data;
            System.out.println(Thread.currentThread().getName()
                    + " have write data:" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
