package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore工具类的使用案例
 * 跟互斥锁有点相似，只是互斥锁只有一把，信号灯可以有多个
 * Semaphore：信号灯
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        //
        final Semaphore sp = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    try {
                        //acquire:获得；下面方法是获取信号灯
                        sp.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //availablePermits():可以获得的许可
                    System.out.println("线程 " + Thread.currentThread().getName() + " 进入，当前已有 " + (3 - sp.availablePermits()) + " 个并发！");

                    try {
                        Thread.sleep((long) Math.random() * 10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程 " + Thread.currentThread().getName() + " 即将离开！");
                    //释放信号灯
                    sp.release();
                    //下面代码有时候执行不准确
                    System.out.println("线程 " + Thread.currentThread().getName() + " 离开，当前已有 " + (3 - sp.availablePermits()) + " 个并发！");

                }
            };
            service.execute(runnable);
        }

    }
}

/*
 * 运行结果：
线程 pool-1-thread-1 进入，当前已有 1 个并发！
线程 pool-1-thread-1 即将离开！
线程 pool-1-thread-1 离开，当前已有 0 个并发！
线程 pool-1-thread-1 进入，当前已有 1 个并发！
线程 pool-1-thread-1 即将离开！
线程 pool-1-thread-1 离开，当前已有 0 个并发！
线程 pool-1-thread-1 进入，当前已有 1 个并发！
线程 pool-1-thread-3 进入，当前已有 2 个并发！
线程 pool-1-thread-1 即将离开！
线程 pool-1-thread-1 离开，当前已有 1 个并发！
线程 pool-1-thread-3 即将离开！
线程 pool-1-thread-3 离开，当前已有 0 个并发！
线程 pool-1-thread-3 进入，当前已有 1 个并发！
线程 pool-1-thread-1 进入，当前已有 2 个并发！
线程 pool-1-thread-3 即将离开！
线程 pool-1-thread-3 离开，当前已有 1 个并发！
线程 pool-1-thread-1 即将离开！
线程 pool-1-thread-1 离开，当前已有 0 个并发！
线程 pool-1-thread-1 进入，当前已有 1 个并发！
线程 pool-1-thread-5 进入，当前已有 2 个并发！
线程 pool-1-thread-1 即将离开！
线程 pool-1-thread-1 离开，当前已有 1 个并发！
线程 pool-1-thread-5 即将离开！
线程 pool-1-thread-5 离开，当前已有 0 个并发！
线程 pool-1-thread-2 进入，当前已有 1 个并发！
线程 pool-1-thread-2 即将离开！
线程 pool-1-thread-2 离开，当前已有 0 个并发！
线程 pool-1-thread-4 进入，当前已有 1 个并发！
线程 pool-1-thread-4 即将离开！
线程 pool-1-thread-4 离开，当前已有 0 个并发！

 */
