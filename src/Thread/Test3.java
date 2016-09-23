package Thread;

import java.sql.SQLSyntaxErrorException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

import static sun.misc.PostVMInitHook.run;

/**
 * Created by zhangzexiang on 2016/9/11.
 */
public class Test3 {

    //creat ThreadPool
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
    final static Semaphore sp = new Semaphore(1);
    final static Semaphore sp1 = new Semaphore(1);
    static ConcurrentSkipListSet<Integer> set =  new ConcurrentSkipListSet<>();


    public static class newclass {
        int key;
        int value;
        newclass(int k, int v){
            this.key = k;
            this.value = v;
        }
    }

    private static void doSomething(final int key ,  final int value){


            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {

                    set.add(key);

//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    Object keydup = null;
                    if(set.contains(key)){
                        keydup = key;
                    }

                    synchronized (keydup){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(key+": "+ System.currentTimeMillis());

                    }

                }
            });
        }


    public static void main(String[] args){

        newclass a = new newclass(1,1);
        newclass b = new newclass(2,2);
        newclass c = new newclass(1,3);
        newclass d = new newclass(2,4);


        doSomething(a.key, a.value);
        doSomething(b.key, b.value);
        doSomething(c.key, c.value);
        doSomething(d.key, d.value);

    }
}
