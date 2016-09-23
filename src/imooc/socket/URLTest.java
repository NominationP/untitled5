package imooc.socket;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhangzexiang on 2016/9/15.
 */
public class URLTest {

    public static void main(String[] args) throws MalformedURLException {

        //create a URL example
        URL imooc = new URL("http://www.imooc.com");
        URL url = new URL(imooc, "/index.html?username=tom#test"); //？后面表示参数，#后面表示瞄点

        System.out.println("协议："+url.getProtocol());
        System.out.println("主机："+url.getHost());
        //如果未指定端口号，则使用默认的端口号，此时getPork()方法返回值为-1
        System.out.println("端口："+url.getPort());
        System.out.println("文件路径："+url.getPath());
        System.out.println("文件名："+url.getFile());
        System.out.println("相对路径："+url.getRef());
        System.out.println("查询字符串："+url.getQuery());




    }
}
