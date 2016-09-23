package Thread;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/***
* 读写锁的锁定规则如下：
 *
 * 获得读锁后，其它线程可获得读锁而不能获取写锁
 * 获得写锁后，其它线程既不能获得读锁也不能获得写锁
*
* */


class ReadWriteLockDemo {

    public static void main(String[] args) {
        final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();
                try {
                    System.out.println(new Date() + "\tThread 1 started with read lock");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception ex) {
                    }
                    System.out.println(new Date() + "\tThread 1 ended");
                } finally {
                    readWriteLock.readLock().unlock();
                }

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();
                try {
                    System.out.println(new Date() + "\tThread 2 started with read lock");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception ex) {
                    }
                    System.out.println(new Date() + "\tThread 2 ended");
                } finally {
                    readWriteLock.readLock().unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Lock lock = readWriteLock.writeLock();
                lock.lock();
                try {
                    System.out.println(new Date() + "\tThread 3 started with write lock");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(new Date() + "\tThread 3 ended");
                } finally {
                    lock.unlock();
                }
            }
            }).start();


    }
}
