package Thread;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionModifyExceptionTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //CopyOnWriteArrayList为线程并发库集合中的类，可以避免HashMap中的并发异常
        /*
         * 直接使用传统的ArrayList会出现各式各样的线程并发异常异常，有兴趣的可以试试
         */
        Collection<User> users = new CopyOnWriteArrayList<User>();
        //Collection<User> users = new ArrayList<User>();
        users.add(new User("Desmond", 21));
        users.add(new User("Hamza", 20));
        users.add(new User("Dave", 22));
        Iterator itrUsers = users.iterator();
        while (itrUsers.hasNext()) {
            User user = (User) itrUsers.next();
            if ("Dave".equals(user.getName())) {
                users.remove(user);
            } else {
                System.out.println(user);
            }
        }
    }

}