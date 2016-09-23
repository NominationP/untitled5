package Thread;

/**
 * Created by zhangzexiang on 2016/9/9.
 *
 * 通过继承Thread来创建线程
 *
 *
 * 创建一个线程的第二种方法是创建一个新的类，该类继承Thread类，然后创建一个该类的实例。
 * 继承类必须重写run()方法，该方法是新线程的入口点。它也必须调用start()方法才能执行。
 */



public class ExtendThread {
    public static void main(String args[]) {
        new NewThread2(); // 创建一个新线程
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }
}

class NewThread2 extends Thread {
    NewThread2() {
        // 创建第二个新线程
        super("Demo Thread");
        System.out.println("Child thread: " + this);
        start(); // 开始线程
    }

    // 第二个线程入口
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                // 让线程休眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}
