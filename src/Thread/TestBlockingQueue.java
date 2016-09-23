package Thread;
import com.sun.glass.ui.ClipboardAssistance;

import java.awt.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



        public class TestBlockingQueue {

            public static void main(String[] args){

                final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);

                System.out.println("begin:"+(System.currentTimeMillis()/1000));
		/*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
		修改程序代码，开四个线程让这16个对象在4秒钟打完。
		*/
                for(int i=0;i<16;i++){  //这行代码不能改动
                    final String log = ""+(i+1);//这行代码不能改动
                    {
                        //Test.parseLog(log);
                        try {
                            //将日志文件存储到queue中
                            queue.put(log);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                for(int i = 0; i < 4; i++){
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                for(int j = 0; j < 4; j++){
                                    //从queue中取出日志并打印
                                    String log = queue.take();
                                    parseLog(log);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }

            //parseLog方法内部的代码不能改动
            public static void parseLog(String log){
                System.out.println(log+":"+(System.currentTimeMillis()/1000));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }