package Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class BlockingQueueCommunication {

    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    business.sub(i);
                }
            }
        }).start();

        for (int i = 1; i <= 20; i++) {
            business.main(i);
        }

    }

    /*
     * 编写一个有子方法（用来调用子线程）和主方法（调用主线程）的业务类 加static是因为上面new的对象是final的，为了把这个类弄成外部类，
     * 但是外部又有同名类，所以这样搞； 产生的类名为：BlockingQueueCommunication.Bussiness
     */
    static class Business {

        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);

        /*
         * 这样直接在大括号内写的代码叫匿名构造方法；匿名构造方法优先与其他任何构造方法执行。
         * 带上static关键字的叫做静态代码块，不带的也叫普通代码块
         * 静态代码块在类加载的时候执行，只会执行一次；普通代码块创建几个对象就会执行几次。
         */

        {
            try {
                System.out.println("我执行了，一上来就把queue2中放了一个数据");
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //此处要注意的问题：一定不要用同步锁sychronized,否则效果达不到还会出粗
        //因为阻塞已经类似到了同步的功能，再用同步锁就是死锁了
        public void sub(int i) {
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread sequence of  " + j
                        + " ,loop of  " + i);
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void main(int i) {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("main thread sequence of  " + j
                        + " ,loop of  " + i);
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}