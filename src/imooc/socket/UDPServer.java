package imooc.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by yibo on 2016/9/20.
 *
 * server, 实现基于UDP的用户登录
 */
public class UDPServer {

    public static void main(String[] args) throws IOException {

        /**
         * 接受客户端发送的数据
         */
        //1 create server DatagramSocket , assign pork
        DatagramSocket socket = new DatagramSocket(8800);
        //2 create datagram, to receive client data
        byte[] data =new byte[1024]; //创建字节数组，指定接受的数据包的大小
        DatagramPacket packet = new DatagramPacket(data,data.length);
        //3 receive client data
        System.out.println("**** server already done ,wait client ***");
        socket.receive(packet); //此方法在接收到数据报之前会一直阻塞
        //4 读取数据
        String info = new String(data,0,packet.getLength());
        System.out.println("I am server, client say: "+ info);

        /**
         * 向客户端响应数据
         */
        //1 定义客户端的地址 端口号 数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "welcome !".getBytes();
        //2 create datagram , 包含响应的数据信息
        DatagramPacket packet2 = new DatagramPacket(data2,data2.length,address,port);
        //3 响应客户端
        socket.send(packet2);
        //4 close resouce
        socket.close();

    }
}
