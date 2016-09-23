package imooc.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangzexiang on 2016/9/17.
 *
 * 编程实现基于TCP的Socket通信，实现用户登录
 *
 * 服务器端
 */
public class Server {


    public static void main(String[] args){
        try {
            //1 创建一个服务器段的Socket,即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8888);

            Socket socket = null;
            //record client number
            int count = 0;
            System.out.println("***server is beginning,wait client ***");

            while (true) {
                //2 调用accept()方法开始监听，等待客户端的连接
                socket = serverSocket.accept();
                //create a new thread
                ServerThread serverThread = new ServerThread(socket);
                //start thread
                serverThread.start();

                count++; //统计客户端的数量
                System.out.println("client number: "+count);

                InetAddress address = socket.getInetAddress();
                System.out.println("current client ip : "+address.getHostAddress());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
