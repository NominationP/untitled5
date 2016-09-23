package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程并发库介绍，及新技术案例分析
 */
public class ThreadPoolTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //1.new数量为3的固定的线程池;工具类一般都带着s;
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //2.缓存线程池，好处：需要几个线程就创建几个；不创建多余也不少创建
        //ExecutorService threadPool = Executors.newCachedThreadPool();
        //单例线程，就好比单独创建一个线程；好处：该线程死了立刻又创建出一个，可以解决线程死后重新启动的问题
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 10; i++) {


//			匿名内部类中不能必须使用最终变量;
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for task of " + task);
                    }
                }
            });
        }
        System.out.println("all of 10 takes have committed!!");
        //线程池中没有任务了就结束线程池，如果不使用就会就算线程池中所有任务都结束了但是线程还在等着运行，不结束
        //threadPool.shutdown();
        /*使用shutdown()方法的巡行结果：
         * 分析下面结果发现循环了10次即这个任务都调用的线程池中的这三个线程，每个任务又重复执行10次
		all of 10 takes have committed!!
		pool-1-thread-1 is looping of 1 for task of 1
		pool-1-thread-3 is looping of 1 for task of 3
		pool-1-thread-2 is looping of 1 for task of 2
		pool-1-thread-3 is looping of 2 for task of 3
		pool-1-thread-1 is looping of 2 for task of 1
		pool-1-thread-2 is looping of 2 for task of 2
		pool-1-thread-3 is looping of 3 for task of 3
		pool-1-thread-1 is looping of 3 for task of 1
		pool-1-thread-2 is looping of 3 for task of 2
		pool-1-thread-3 is looping of 4 for task of 3
		pool-1-thread-1 is looping of 4 for task of 1
		pool-1-thread-2 is looping of 4 for task of 2
		pool-1-thread-1 is looping of 5 for task of 1
		pool-1-thread-3 is looping of 5 for task of 3
		pool-1-thread-2 is looping of 5 for task of 2
		pool-1-thread-1 is looping of 6 for task of 1
		pool-1-thread-3 is looping of 6 for task of 3
		pool-1-thread-2 is looping of 6 for task of 2
		pool-1-thread-3 is looping of 7 for task of 3
		pool-1-thread-1 is looping of 7 for task of 1
		pool-1-thread-2 is looping of 7 for task of 2
		pool-1-thread-1 is looping of 8 for task of 1
		pool-1-thread-3 is looping of 8 for task of 3
		pool-1-thread-2 is looping of 8 for task of 2
		pool-1-thread-1 is looping of 9 for task of 1
		pool-1-thread-3 is looping of 9 for task of 3
		pool-1-thread-2 is looping of 9 for task of 2
		pool-1-thread-1 is looping of 10 for task of 1
		pool-1-thread-3 is looping of 10 for task of 3
		pool-1-thread-2 is looping of 10 for task of 2
		pool-1-thread-3 is looping of 1 for task of 5
		pool-1-thread-1 is looping of 1 for task of 4
		pool-1-thread-2 is looping of 1 for task of 6
		pool-1-thread-3 is looping of 2 for task of 5
		pool-1-thread-1 is looping of 2 for task of 4
		pool-1-thread-2 is looping of 2 for task of 6
		pool-1-thread-1 is looping of 3 for task of 4
		pool-1-thread-3 is looping of 3 for task of 5
		pool-1-thread-2 is looping of 3 for task of 6
		pool-1-thread-1 is looping of 4 for task of 4
		pool-1-thread-3 is looping of 4 for task of 5
		pool-1-thread-2 is looping of 4 for task of 6
		pool-1-thread-1 is looping of 5 for task of 4
		pool-1-thread-3 is looping of 5 for task of 5
		pool-1-thread-2 is looping of 5 for task of 6
		pool-1-thread-1 is looping of 6 for task of 4
		pool-1-thread-3 is looping of 6 for task of 5
		pool-1-thread-2 is looping of 6 for task of 6
		pool-1-thread-1 is looping of 7 for task of 4
		pool-1-thread-3 is looping of 7 for task of 5
		pool-1-thread-2 is looping of 7 for task of 6
		pool-1-thread-3 is looping of 8 for task of 5
		pool-1-thread-1 is looping of 8 for task of 4
		pool-1-thread-2 is looping of 8 for task of 6
		pool-1-thread-1 is looping of 9 for task of 4
		pool-1-thread-3 is looping of 9 for task of 5
		pool-1-thread-2 is looping of 9 for task of 6
		pool-1-thread-3 is looping of 10 for task of 5
		pool-1-thread-1 is looping of 10 for task of 4
		pool-1-thread-2 is looping of 10 for task of 6
		pool-1-thread-1 is looping of 1 for task of 8
		pool-1-thread-3 is looping of 1 for task of 7
		pool-1-thread-2 is looping of 1 for task of 9
		pool-1-thread-3 is looping of 2 for task of 7
		pool-1-thread-1 is looping of 2 for task of 8
		pool-1-thread-2 is looping of 2 for task of 9
		pool-1-thread-1 is looping of 3 for task of 8
		pool-1-thread-3 is looping of 3 for task of 7
		pool-1-thread-2 is looping of 3 for task of 9
		pool-1-thread-1 is looping of 4 for task of 8
		pool-1-thread-3 is looping of 4 for task of 7
		pool-1-thread-2 is looping of 4 for task of 9
		pool-1-thread-1 is looping of 5 for task of 8
		pool-1-thread-3 is looping of 5 for task of 7
		pool-1-thread-2 is looping of 5 for task of 9
		pool-1-thread-1 is looping of 6 for task of 8
		pool-1-thread-3 is looping of 6 for task of 7
		pool-1-thread-2 is looping of 6 for task of 9
		pool-1-thread-1 is looping of 7 for task of 8
		pool-1-thread-3 is looping of 7 for task of 7
		pool-1-thread-2 is looping of 7 for task of 9
		pool-1-thread-3 is looping of 8 for task of 7
		pool-1-thread-1 is looping of 8 for task of 8
		pool-1-thread-2 is looping of 8 for task of 9
		pool-1-thread-3 is looping of 9 for task of 7
		pool-1-thread-1 is looping of 9 for task of 8
		pool-1-thread-2 is looping of 9 for task of 9
		pool-1-thread-1 is looping of 10 for task of 8
		pool-1-thread-3 is looping of 10 for task of 7
		pool-1-thread-2 is looping of 10 for task of 9
		pool-1-thread-1 is looping of 1 for task of 10
		pool-1-thread-1 is looping of 2 for task of 10
		pool-1-thread-1 is looping of 3 for task of 10
		pool-1-thread-1 is looping of 4 for task of 10
		pool-1-thread-1 is looping of 5 for task of 10
		pool-1-thread-1 is looping of 6 for task of 10
		pool-1-thread-1 is looping of 7 for task of 10
		pool-1-thread-1 is looping of 8 for task of 10
		pool-1-thread-1 is looping of 9 for task of 10
		pool-1-thread-1 is looping of 10 for task of 10*/


        //结束当前线程
//		threadPool.shutdownNow();
        /*
         * 运行结果：
		 * 主要看下面的结果，暂且忽略错误提示；上面的是十个线程，这个却是三个，
		 * 这是因为结束当前线程，当前就三个线程。所以直接结束了不管后面没有线程的
		 * 其他七个任务。
		 * all of 10 takes have committed!!
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at com.itcast.family.ThreadPoolTest$1.run(ThreadPoolTest.java:25)
	at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:886)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:908)
	at java.lang.Thread.run(Thread.java:619)
pool-1-thread-1 is looping of 1 for task of 1java.lang.InterruptedException: sleep interrupted

	at java.lang.Thread.sleep(Native Method)
	at com.itcast.family.ThreadPoolTest$1.run(ThreadPoolTest.java:25)
	at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:886)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:908)
	at java.lang.Thread.run(Thread.java:619)
pool-1-thread-2 is looping of 1 for task of 2
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at com.itcast.family.ThreadPoolTest$1.run(ThreadPoolTest.java:25)
	at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:886)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:908)
	at java.lang.Thread.run(Thread.java:619)
    pool-1-thread-3 is looping of 1 for task of 3
    pool-1-thread-1 is looping of 2 for task of 1
    pool-1-thread-2 is looping of 2 for task of 2
    pool-1-thread-3 is looping of 2 for task of 3
    pool-1-thread-2 is looping of 3 for task of 2
    pool-1-thread-1 is looping of 3 for task of 1
    pool-1-thread-3 is looping of 3 for task of 3
    pool-1-thread-1 is looping of 4 for task of 1
    pool-1-thread-2 is looping of 4 for task of 2
    pool-1-thread-3 is looping of 4 for task of 3
    pool-1-thread-2 is looping of 5 for task of 2
    pool-1-thread-1 is looping of 5 for task of 1
    pool-1-thread-3 is looping of 5 for task of 3
    pool-1-thread-1 is looping of 6 for task of 1
    pool-1-thread-2 is looping of 6 for task of 2
    pool-1-thread-3 is looping of 6 for task of 3
    pool-1-thread-2 is looping of 7 for task of 2
    pool-1-thread-1 is looping of 7 for task of 1
    pool-1-thread-3 is looping of 7 for task of 3
    pool-1-thread-2 is looping of 8 for task of 2
    pool-1-thread-1 is looping of 8 for task of 1
    pool-1-thread-3 is looping of 8 for task of 3
    pool-1-thread-1 is looping of 9 for task of 1
    pool-1-thread-2 is looping of 9 for task of 2
    pool-1-thread-3 is looping of 9 for task of 3
    pool-1-thread-2 is looping of 10 for task of 2
    pool-1-thread-1 is looping of 10 for task of 1
    pool-1-thread-3 is looping of 10 for task of 3

		 */



		/*动态变化即缓存那个的运行结果：
         * 需要几个线程就new出几个线程；这里是个是个任务就new出了十个线程
		 * 而不是像第一种那样是个任务三个线程
		 * all of 10 takes have committed!!
pool-1-thread-2 is looping of 1 for task of 2
pool-1-thread-4 is looping of 1 for task of 4
pool-1-thread-6 is looping of 1 for task of 6
pool-1-thread-8 is looping of 1 for task of 8
pool-1-thread-10 is looping of 1 for task of 10
pool-1-thread-1 is looping of 1 for task of 1
pool-1-thread-3 is looping of 1 for task of 3
pool-1-thread-5 is looping of 1 for task of 5
pool-1-thread-7 is looping of 1 for task of 7
pool-1-thread-9 is looping of 1 for task of 9
pool-1-thread-6 is looping of 2 for task of 6
pool-1-thread-4 is looping of 2 for task of 4
pool-1-thread-2 is looping of 2 for task of 2
pool-1-thread-1 is looping of 2 for task of 1
pool-1-thread-8 is looping of 2 for task of 8
pool-1-thread-10 is looping of 2 for task of 10
pool-1-thread-3 is looping of 2 for task of 3
pool-1-thread-5 is looping of 2 for task of 5
pool-1-thread-7 is looping of 2 for task of 7
pool-1-thread-9 is looping of 2 for task of 9
pool-1-thread-6 is looping of 3 for task of 6
pool-1-thread-4 is looping of 3 for task of 4
pool-1-thread-1 is looping of 3 for task of 1
pool-1-thread-2 is looping of 3 for task of 2
pool-1-thread-8 is looping of 3 for task of 8
pool-1-thread-5 is looping of 3 for task of 5
pool-1-thread-3 is looping of 3 for task of 3
pool-1-thread-10 is looping of 3 for task of 10
pool-1-thread-9 is looping of 3 for task of 9
pool-1-thread-7 is looping of 3 for task of 7
pool-1-thread-6 is looping of 4 for task of 6
pool-1-thread-1 is looping of 4 for task of 1
pool-1-thread-4 is looping of 4 for task of 4
pool-1-thread-8 is looping of 4 for task of 8
pool-1-thread-2 is looping of 4 for task of 2
pool-1-thread-3 is looping of 4 for task of 3
pool-1-thread-5 is looping of 4 for task of 5
pool-1-thread-10 is looping of 4 for task of 10
pool-1-thread-7 is looping of 4 for task of 7
pool-1-thread-9 is looping of 4 for task of 9
pool-1-thread-1 is looping of 5 for task of 1
pool-1-thread-6 is looping of 5 for task of 6
pool-1-thread-4 is looping of 5 for task of 4
pool-1-thread-2 is looping of 5 for task of 2
pool-1-thread-5 is looping of 5 for task of 5
pool-1-thread-3 is looping of 5 for task of 3
pool-1-thread-8 is looping of 5 for task of 8
pool-1-thread-10 is looping of 5 for task of 10
pool-1-thread-7 is looping of 5 for task of 7
pool-1-thread-9 is looping of 5 for task of 9
pool-1-thread-1 is looping of 6 for task of 1
pool-1-thread-6 is looping of 6 for task of 6
pool-1-thread-4 is looping of 6 for task of 4
pool-1-thread-5 is looping of 6 for task of 5
pool-1-thread-3 is looping of 6 for task of 3
pool-1-thread-2 is looping of 6 for task of 2
pool-1-thread-10 is looping of 6 for task of 10
pool-1-thread-8 is looping of 6 for task of 8
pool-1-thread-7 is looping of 6 for task of 7
pool-1-thread-9 is looping of 6 for task of 9
pool-1-thread-1 is looping of 7 for task of 1
pool-1-thread-4 is looping of 7 for task of 4
pool-1-thread-6 is looping of 7 for task of 6
pool-1-thread-2 is looping of 7 for task of 2
pool-1-thread-3 is looping of 7 for task of 3
pool-1-thread-5 is looping of 7 for task of 5
pool-1-thread-8 is looping of 7 for task of 8
pool-1-thread-10 is looping of 7 for task of 10
pool-1-thread-7 is looping of 7 for task of 7
pool-1-thread-9 is looping of 7 for task of 9
pool-1-thread-1 is looping of 8 for task of 1
pool-1-thread-6 is looping of 8 for task of 6
pool-1-thread-4 is looping of 8 for task of 4
pool-1-thread-2 is looping of 8 for task of 2
pool-1-thread-5 is looping of 8 for task of 5
pool-1-thread-3 is looping of 8 for task of 3
pool-1-thread-8 is looping of 8 for task of 8
pool-1-thread-10 is looping of 8 for task of 10
pool-1-thread-7 is looping of 8 for task of 7
pool-1-thread-9 is looping of 8 for task of 9
pool-1-thread-1 is looping of 9 for task of 1
pool-1-thread-6 is looping of 9 for task of 6
pool-1-thread-4 is looping of 9 for task of 4
pool-1-thread-5 is looping of 9 for task of 5
pool-1-thread-3 is looping of 9 for task of 3
pool-1-thread-2 is looping of 9 for task of 2
pool-1-thread-8 is looping of 9 for task of 8
pool-1-thread-10 is looping of 9 for task of 10
pool-1-thread-7 is looping of 9 for task of 7
pool-1-thread-9 is looping of 9 for task of 9
pool-1-thread-1 is looping of 10 for task of 1
pool-1-thread-3 is looping of 10 for task of 3
pool-1-thread-5 is looping of 10 for task of 5
pool-1-thread-2 is looping of 10 for task of 2
pool-1-thread-4 is looping of 10 for task of 4
pool-1-thread-6 is looping of 10 for task of 6
pool-1-thread-8 is looping of 10 for task of 8
pool-1-thread-7 is looping of 10 for task of 7
pool-1-thread-9 is looping of 10 for task of 9
pool-1-thread-10 is looping of 10 for task of 10

		 */


        //java新技术中的定时器的使用;相当于一次性炸弹
        Executors.newScheduledThreadPool(3).schedule(new Runnable() {

                                                         @Override
                                                         public void run() {
                                                             System.out.println("bombing!!!");
                                                         }
                                                     }, 4,
                TimeUnit.SECONDS);

        //连环炸弹
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {

                                                                    @Override
                                                                    public void run() {
                                                                        System.out.println("bombing!!!");
                                                                    }
                                                                },
                4,  //隔多少秒炸一下
                2,  //隔多少秒再炸
                TimeUnit.SECONDS);
    }
}


