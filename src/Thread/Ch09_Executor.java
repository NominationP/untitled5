
package Thread;
/**
 * Created by zhangzexiang on 2016/9/11.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
/** * Created by yang on 16-7-11. */
public class Ch09_Executor {
    private static void run(ExecutorService threadPool) {
        for (int i = 1; i < 5; i++) {
            final int taskID=i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i=1;i<5;i++){
                        try{
                            Thread.sleep(1000);
                        }catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("第"+taskID+"次任务的第"+i+"次执行");
                    }
                }
            });
        }
        threadPool.shutdown();
    }
    public static void main(String[] args) {
        //创建可以容纳3个线程的线程池
        ExecutorService fixedThreadPool= Executors.newFixedThreadPool(3);
        //线程池的大小会根据执行的任务动态的分配
        ExecutorService cacheThreadPool=Executors.newCachedThreadPool();
        //创建单个线程的线程池,如果当前线程在执行任务时突然中断,则会创建一个新的线程替换它继续执行.
        ExecutorService singleThreadPool=Executors.newSingleThreadExecutor();
        //效果类似于Timer定时器
        ScheduledExecutorService scheduledThreadPool=Executors.newScheduledThreadPool(3);
         //run(fixedThreadPool); //(1)
        run(cacheThreadPool); //(2)
        // run(singleThreadPool); //(3)
        // run(scheduledThreadPool); //(4)
    }
}