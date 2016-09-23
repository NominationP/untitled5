package Thread;

import DecoratorMode.Ingredient;
import org.junit.experimental.theories.Theories;
import sun.awt.windows.ThemeReader;

import javax.swing.plaf.basic.BasicTreeUI;
import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * Created by zhangzexiang on 2016/9/10.
 */

public class SemaphoreTest2{
    public static void main(String[] args) {


        //采用新特性来启动和管理线程——内部使用线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // ExecutorService exec = Executors.newSingleThreadExecutor();
        //只允许1个线程同时访问
        final Semaphore semp = new Semaphore(1);
        //模拟10个客户端访问
        for (int index = 1; index <= 10; index++){
            final int num = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        //获取许可
                        semp.acquire();
                        System.out.println("线程" +
                                Thread.currentThread().getName() + "获得许可："  + num);
                        //模拟耗时的任务
//                        for (int i = 0; i < 999999; i++) ;
//
                        Thread.sleep(1000);


                        //释放许可
                        semp.release();
                        System.out.println("线程" +
                                Thread.currentThread().getName() + "释放许可："  + num);
                        System.out.println("当前允许进入的任务个数：" +
                                semp.availablePermits());
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        //关闭线程池
        exec.shutdown();
    }
}
