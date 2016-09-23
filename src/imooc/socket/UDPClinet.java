package imooc.socket;

import java.io.IOException;
import java.net.*;

/**
 * Created by yibo on 2016/9/20.
 *
 * 客户端, 实现基于UDP的用户登录
 */
public class UDPClinet {
    public static void main(String[] args) throws IOException {
        /**
         * 向服务区端发送数据
         */
        //1 定义服务器的地址，端口号，数据
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8800;
        byte[] data = "username: admin; password:123".getBytes();
        //2 create datagram, 包含要发送的数据信息
        DatagramPacket packet = new DatagramPacket(data,data.length,address,port);
        //3 create DatagramSocket
        DatagramSocket socket= new DatagramSocket();
        //4 sent datagram to server
        socket.send(packet);

        /**
         * 接受服务器端响应的数据
         */
        //1 create datagram, 用于接受服务器端响应的数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet2  = new DatagramPacket(data2,data2.length);
        //2 接收服务器响应的数据
        socket.receive(packet2);
        //3 读取数据
        String reply = new String(data2,0,packet2.getLength());
        System.out.println("im client , server say : "+reply);
        //4 close resource
        socket.close();

    }
}
