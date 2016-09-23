package Thread;

import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by zhangzexiang on 2016/9/11.
 */

//不能改动此Test类
public class Test3Standard {

    //不能改动此Test类
    public static class Test1 extends Thread{

        private TestDo testDo;
        private String key;
        private String value;

        public Test1(String key,String key2,String value){
            this.testDo = TestDo.getInstance();
		/*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
		以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
            this.key = key+key2;
            this.value = value;
        }


        public static void main(String[] args) throws InterruptedException{
            Test1 a = new Test1("1","","1");
            Test1 b = new Test1("1","","2");
            Test1 c = new Test1("4","","3");
            Test1 d = new Test1("4","","4");
            System.out.println("begin:"+(System.currentTimeMillis()/1000));
            a.start();
            b.start();
            c.start();
            d.start();

        }

        public void run(){
            testDo.doSome(key, value);
        }
    }

    static class TestDo {

        //使用线程安全的ConcurrentSkipListSet
        private ConcurrentSkipListSet<Object>set = new ConcurrentSkipListSet<Object>();

        private TestDo() {}
        private static TestDo _instance = new TestDo();
        public static TestDo getInstance() {
            return _instance;
        }

        public void doSome(Object key, String value) {
            //将key添加进set，如果set中包含内容重复的元素则不添加
            set.add(key);
            Iterator<Object> it = set.iterator();
            Object myKey = null;
            while(it.hasNext()){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String str = (String)it.next();
                //找到set中与key内容相同的元素，将其赋给myKey作为锁
                if(str.equals(key)){
                    myKey = str;
                }
            }

            synchronized(myKey)
            // 以大括号内的是需要局部同步的代码，不能改动!
            {
                try {
                    Thread.sleep(1000);
                    System.out.println(key+":"+value + ":"
                            + (System.currentTimeMillis() / 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
