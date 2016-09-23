package imooc.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhangzexiang on 2016/9/17.
 *
 *
 * 编程实现基于TCP的Socket通信，实现用户登录
 *
 * 客户端
 */
public class Client {

    public static void main(String[] args) {

        try {
            //1 创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("localhost",8888);
            //2 换取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(os); //将输出流包装为打印流
            pw.write("username:admin;password:123");
            pw.flush();
            socket.shutdownOutput();//colse outputStream

            //3 获取输入流，并读取服务器端的相应消息
            InputStream is =socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info=br.readLine())!=null){//循环读取服务端的信息
                System.out.println("我是客户端，服务器说："+info);
            }


            //4 colse resource
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
