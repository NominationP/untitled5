package Thread;

import com.sun.javafx.image.IntPixelGetter;
import com.sun.webpane.platform.ThemeClient;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhangzexiang on 2016/9/10.
 */
public class ThreadTest1_Queue {

    public static void main(String[] agrs) throws InterruptedException {

        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(16);

        for(int i=1; i<=16; i++){   //save 16 date
            queue.put(i);
        }

//        for (int i = 1; i <= 16; i++) {
//            final int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    try {
//                       System.out.println("read data");
//                        queue.put(finalI);
//                     //   System.out.println("read D");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
        int n=4;

        while(n>0) {    //almost 16

           // System.out.println("n = ");

            final int finalN = n;
            new Thread(new Runnable() {     //A new Thread
                @Override
                public void run() {
                    //4 thread
                    for (int j = 1; j <= 4; j++) {
                        try {
                            // System.out.println("take data");
                            int temp = queue.take();    //almost in same time to take

                            System.out.println("thread "+ finalN +" take data: "+temp);   //output
                            try {
                                Thread.sleep(1000);     //wait 1S
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
            n--;
    }

    }
}