package Thread;

import java.util.concurrent.Semaphore;

/**
 * Created by zhangzexiang on 2016/9/9.
 */
public class TestTicket {

       static final Semaphore semp = new Semaphore(1);

    public static void main(String[] args)
    {
        Resource rs = new Resource(20);

        for (int i=0;i<10 ;i++ )
        {
        new Thread(new Seller(i,rs)).start();
//        new Thread(new Seller(2,rs)).start();
//        new Thread(new Seller(3,rs)).start();
//        new Thread(new Seller(4,rs)).start();
        }
    }

    static class  Resource
    {
        int ticketNum = 50;
        boolean flag = false ; // 定义票是否卖完

        public Resource(){}
        public Resource(int num)
        {
            this.ticketNum = num;
        }

        public synchronized void sellTicket(Seller s) throws InterruptedException {


            if (ticketNum > 0)
            {
                System.out.println("第" + s.num + "售票点卖出了第" + ticketNum + "张票……");
                ticketNum--;
            }
            else
            {
                flag = true;
            }
        }
    }
    /**
     *  售票点类
     *
     */
    static class Seller implements Runnable
    {
        int num;
        Resource rs;

        public Seller(int num,Resource rs)
        {
            this.num = num;
            this.rs = rs;
        }

        public  final void run()
        {

            try {
                semp.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!rs.flag)
            {
                /**
                 *  调用资源类的同步方法
                 */
                try {
                    rs.sellTicket(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                semp.release();
            }

        }
    }

}
