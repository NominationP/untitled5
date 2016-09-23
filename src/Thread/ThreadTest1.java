package Thread;

/**
 * Created by zhangzexiang on 2016/9/9.
 *
 * 创建4个线程来调用一个方法打印（通常一次只能打印一个数据，这次用4个来打印4个数据）
 * 为了不让打印的数据重复，给打印数据的函数（printData）前添加了（synchronized）关键字
 *
 */
public class ThreadTest1 {

    public static void main(String[] args)
    {
        Resource rs = new Resource(20); //initial 20 data

      for (int i=1;i<=4 ;i++ )  //creat 4 thread
       {
        new Thread(new Gener(i,rs)).start();
//        new Thread(new Seller(2,rs)).start();
//        new Thread(new Seller(3,rs)).start();
//        new Thread(new Seller(4,rs)).start();
       }
    }

    static class Resource
    {
        int dataCount = 50;
        boolean flag = false ; //define data is or not 


        public Resource(int num)
        {
            this.dataCount = num;
        }

        public synchronized void printData(Gener s)
        {
            if (dataCount > 0)
            {
                System.out.println("第" + s.num + "个线程执行第" + dataCount + "个数据");
                dataCount--;
            }
            else
            {
                flag = true;
            }
        }
    }
    /**
     *  gener data
     *
     */
    static class Gener implements Runnable
    {
        int num;
        Resource rs;

        public Gener(int num,Resource rs)
        {
            this.num = num;
            this.rs = rs;
        }

        public final void run()
        {
            while (!rs.flag)
            {

                rs.printData(this);

                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
