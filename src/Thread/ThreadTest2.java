package Thread;

/**
 * Created by zhangzexiang on 2016/9/11.
 */
import DecoratorMode.Ingredient;
import com.sun.javafx.image.IntPixelGetter;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.InternalError;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.*;

public class ThreadTest2 {

    static BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
    //static Queue<Integer> queue = new ArrayDeque<>();
    static int i = 1;
    static int count = 20;
    static int m;

    static void doSomething() throws InterruptedException {
        Thread.sleep(1000);
    }


    // generagte a data per 500
//    static synchronized void g(int i) throws InterruptedException {
//        queue.add(i);
//        System.out.println("generate date -ing ...............");
//        try {
//            Thread.sleep(0);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {

        for (int datac = 1; datac <= 20; datac++) {

            queue.add(datac);

        }

        //只允许1个线程同时访问
        final Semaphore semp = new Semaphore(1);



        System.out.println("wait generate data");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }d
        //模拟10个客户端访问
        int size = queue.size();

        while (size-- >0) {

        //   System.out.println("bbbbbbbbbbbbb");

            for (int index = 1; index <= 10; index++) {


                final int finalIndex = index;
                new Thread(new Runnable () {
                    public void run() {

                        try {
                            //获取许可

                            semp.acquire();

                            m = queue.take();

                            doSomething();
                            System.out.println("线程" +
                                    finalIndex + "处理：" + m);


                            //释放许可
                            semp.release();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
//                exec.execute(run);
            }
        }
        //关闭线程池
//            exec.shutdown();
    }

}
