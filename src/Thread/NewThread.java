package Thread;

/**
 * Created by zhangzexiang on 2016/9/9.
 *
 * 创建一个线程方法之一：通过实现Runnable接口来创建线程
 *
 * 重写 public void run()
 * 理解run()可以调用其他方法，使用其他类，并声明变量，就像主线程一样
 *
 * 在类中先实例化一个线程对象
 * 常用： Thread(Runnable threadOb, String threadName);
 *  threadOb :是一个实现Runnable接口的类的实例
 *  threadName :指定线程的名字
 *
 *
 *  新线程创建之后，你调用它的start()方法它才会运行。
 *
 *
 */

public class NewThread{
    public static void main(String args[]){
        new NewThread1();    //create new thread
        try{
            for(int i=5; i>0; i--){
                System.out.println("Main Thread "+i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread exiting");
    }
}


//创建一个新的线程
 class NewThread1 implements Runnable {

    Thread t;
    NewThread1(){
        //创建第二个新线程
        t = new Thread(this,"Demo Thread");
        System.out.println("child thread: " + t);
        t.start();  //begin thread

    }

    //the second thread entrance(入口)
    @Override
    public void run() {
        try{
            for(int i=5; i>0; i--){
                System.out.println("chaild thread:" + i);
                //pause thread
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Exiting child thread");
    }
}



