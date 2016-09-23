package inputStream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by zhangzexiang on 2016/9/7.
 *
 * 输出文件中的内容
 */
	public class TestFile {
	    public static void main(String args[]) throws IOException {
        try{
                          FileInputStream rf=new   FileInputStream("D:\\all study\\2016.7上海交大\\服务器第一阶段\\dd.txt");
            	               int n=512;   byte  buffer[]=new  byte[n];
            	               while((rf.read(buffer,0,n)!=-1)&&(n>0)){
                	                   System.out.println(new String(buffer) );
                	                }
            	                System.out.println();
            	                rf.close();
        } catch(IOException  IOe){
            	              System.out.println(IOe.toString());
            	        }

        	    }

        	}

