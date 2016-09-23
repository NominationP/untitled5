package imooc.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhangzexiang on 2016/9/15.
 *
 * URL读取网页内容
 */
public class URLTest2 {

    public static void main(String[] args){

        try {
            //create URL ex
            URL url  = new URL("http://www.baidu.com");
            //通过 URL 的 openStream 方法获取URL对象所表示的资源的字节输入流
            InputStream is = url.openStream();
            //将字节输入流转换为字符输入流
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            //为字符输入流添加缓冲（加快速度？）
            BufferedReader br = new BufferedReader(isr);


            String data = br.readLine();//读取数据
            while(data!=null){//循环读取数据
                System.out.println(data);//输出数据
                data = br.readLine();
            }

            br.close();//close BufferedReader
            isr.close(); // close InputStreamReader 关闭字符输入流
            is.close(); //close InputStream 关闭字节输入流





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
