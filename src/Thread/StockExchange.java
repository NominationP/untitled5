package Thread;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class StockExchange {
    /**
     * 模拟一个股票交易系统，100卖方，100个买方不停执行买卖操作
     */
    public static void main(String[] args) {
        System.out.println("按下回车键停止买卖!！！！！！！！！！");
        BlockingDeque<Integer> orderQueue=new LinkedBlockingDeque<Integer>();   //构建阻塞订单队列
        Seller seller=new Seller(orderQueue);
        Thread[] sellerThread=new Thread[10];
        for (int i = 0; i < sellerThread.length; i++) {   //加入100个卖方线程
            sellerThread[i]=new Thread(seller);
            sellerThread[i].start();
        }

        Buyer buyer=new Buyer(orderQueue);
        Thread[] buyerThread=new Thread[10];
        for (int i = 0; i < buyerThread.length; i++) { //加入100个买方线程
            sellerThread[i]=new Thread(buyer);
            sellerThread[i].start();
        }

        try {
            while(System.in.read()!='\n')  //当按下回车键时
                System.out.println("所有买卖方全部禁止交易!");
            for(Thread t: sellerThread){   //让卖方线程停止
                t.interrupt();
            }
            for(Thread t: buyerThread){    //让买方线程停止
                t.interrupt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Seller implements Runnable{

    private BlockingDeque<Integer> orderQueue;
    private boolean shutdownRequest=false;  //设定一个关闭标志
    public Seller(BlockingDeque<Integer> orderQueue) {
        this.orderQueue=orderQueue;
    }
    @Override
    public void run() {
        while(shutdownRequest==false){ //正常情况下无限循环抛售
            Integer quantity=(int)(Math.random()*100);
            try {
                orderQueue.put(quantity);
                System.out.println("卖方 "+Thread.currentThread().getName()+"抛售了股票 "+quantity+"股");
            } catch (InterruptedException e) {
                shutdownRequest=true; //抛出异常后停止
                e.printStackTrace();
            }
        }
    }

}

class Buyer implements Runnable{

    private BlockingDeque<Integer> orderQueue;
    private boolean shutdownRequest=false;  //同上
    public Buyer(BlockingDeque<Integer> orderQueue) {
        this.orderQueue=orderQueue;
    }
    @Override
    public void run() {
        while(shutdownRequest==false){
            try {
                Integer quantity=orderQueue.take();
                System.out.println("买方"+Thread.currentThread().getName()+"购买了股票 "+quantity+"股");
            } catch (InterruptedException e) {
                shutdownRequest=true;
                e.printStackTrace();
            }
        }
    }

}  