package Thread;

import java.util.concurrent.Semaphore;

/**
 * Created by zhangzexiang on 2016/9/11.
 */


    public class SemaphoreTest3 {

        public static void main(String[] args) {

            //信号类控制线程运行
            final Semaphore semaphore = new Semaphore(1);

            System.out.println("begin:"+(System.currentTimeMillis()/1000));
            for(int i=0;i<10;i++){  //这行不能改动
                String input = i+"";  //这行不能改动
                //String output = TestDo.doSome(input);
                //System.out.println(Thread.currentThread().getName()+ ":" + output);

                final String myInput = input;
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            //多个线程并发操作要先获取信号才可继续运行
                            semaphore.acquire();
                            String output = TestDo.doSome(myInput);
                            System.out.println(Thread.currentThread().getName()+ ":" + output);
                            //运行完后释放信号
                            semaphore.release();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }

    //不能改动此TestDo类
    class TestDo {
        public static String doSome(String input){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String output = input + ":"+ (System.currentTimeMillis() / 1000);
            return output;
        }
    }