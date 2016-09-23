package inputStream;

import java.io.*;


/**
 * Created by zhangzexiang on 2016/9/6.
 */
public class BufferRreader {

    public static void main(String args[]) throws IOException{

        char c;
        //use System.in to creat BuffereReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter characters, 'q' to quit");

//        //read char
//        do{
//            c = (char) br.read();
//            System.out.println(c);
//        }while (c != 'q');

        String str;
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");
        do{
            str = br.readLine();
            System.out.println(str);
        }while (!str.equals("end"));
    }

}
