package imooc.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by zhangzexiang on 2016/9/15.
 */
public class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {

        InetAddress address = InetAddress.getLocalHost();

        System.out.println("computer name: "+address.getHostName());
        System.out.println("computer ip: "+address.getHostAddress());
    }

}
