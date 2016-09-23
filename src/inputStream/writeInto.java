package inputStream;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhangzexiang on 2016/9/7.
 *
 * 给文件中写入内容
 *
 */
public class writeInto {
    public static void main(String args[]) throws IOException {
                try {
                    System.out.println("please Input from      Keyboard");
                        int count, n = 512;
            	            byte buffer[] = new byte[n];
            	            count = System.in.read(buffer);
            	            FileOutputStream wf = new FileOutputStream("D:\\all study\\2016.7上海交大\\服务器第一阶段\\dd.txt");
            	            wf.write(buffer, 0, count);
                  wf.close(); // 当流写操作结束时，调用close方法关闭流。
      	            System.out.println("Save to the write.txt");
      	        } catch (IOException IOe) {
      	            System.out.println("File Write Error!");
  }
   }


}
