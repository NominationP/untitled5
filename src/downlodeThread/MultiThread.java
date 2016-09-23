package downlodeThread;

/**
 * Created by zhangzexiang on 2016/9/14.
 */
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MultiThread {

    static int threadNum = 5;

    public static void main(String[] args) {
        //文件下载路径
        String filePath = "https://www.voidtools.com/Everything-1.3.4.686.x86-Setup.exe";
       //String filePath = "http://bos.wenku.bdimg.com/v1/wenku45//f604b515590ded755d6d01fce92ed4b0?responseContentDisposition=attachment%3B%20filename%3D%22%B3%F5%D6%D0%D7%F7%CE%C4%CB%D8%B2%C4.doc%22&responseContentType=application%2Foctet-stream&responseCacheControl=no-cache&authorization=bce-auth-v1%2Ffa1126e91489401fa7cc85045ce7179e%2F2016-09-14T06%3A46%3A35Z%2F3600%2Fhost%2F72af119a9cc5c5f6aa7a1d10097c92b31589837d9a171ba0913fcfaaaa3e246e&token=f08481902db376f96f27310cc7188fb666d0a51badb1f4f83f625f2db1707e64&expire=2016-09-14T07:46:35Z";
     //   String filePath = "http://dldir1.qq.com/qqfile/qq/QQ2013/QQ2013Beta2.exe";
        //文件保存路径
        String destination = "D://tst";
        //打算开启的线程数

        new MultiThread().download(filePath, destination, threadNum);
    }

    /**
     * 下载文件
     */
    private void download(String filePath, String destination, int threadNum) {
        try {
            //通过下载路径获取连接
            URL url = new URL(filePath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置连接的相关属性
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            //判断连接是否正确。
            if (conn.getResponseCode() == 200) {
                // 获取文件大小。
                int fileSize = conn.getContentLength();
                //得到文件名
                String fileName = getFileName(filePath);
//                String fileName = url.getFile();
                //根据文件大小及文件名，创建一个同样大小，同样文件名的文件
                File file = new File(destination + File.separator + fileName);
                System.out.println(destination + File.separator + fileName);
//                RandomAccessFile raf = new RandomAccessFile(file, "rw");
//                raf.setLength(fileSize);
//                raf.close();
                // 将文件分成threadNum = 5份。
                // block 5份中的一份的容量（大于等于fileSize/5）
                int block = fileSize % threadNum == 0 ? fileSize / threadNum
                        : fileSize / threadNum + 1;
                System.out.println("sum size: "+fileSize);
                for (int threadId = 0; threadId < threadNum; threadId++) {
                    //传入线程编号，并开始下载。
                    System.out.println("线程"+threadId+" 每份： "+block+" begin!");
                    new DownloadThread(threadId,block, file, url, fileName,fileSize).start();
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //由路径获取文件名。
    private String getFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf('/') + 1);
    }

}

//文件下载线程
class DownloadThread extends Thread {
    int start, end,threadId;
    File file = null;
    URL url = null;
    static int threadNum = 5;
    static int threadNumdup = 5;
    static String filename = null;
    static int fileSize;

    public DownloadThread(int threadId,int block, File file, URL url, String filename,int fileSize) {
        this.threadId = threadId;
        start = block * threadId;
        end = block * (threadId + 1) - 1;
        this.file = file;
        this.url = url;
        this.filename = filename;
        this.fileSize = fileSize;
    }

    public void run() {
        try {

            //检查是否存在记录下载长度的文件，如果存在读取这个文件的数据
        //    File tempFile = new File("d://tst//"+threadId+ ".txt");

//            File tempFile = new File("d://tst//"+threadId+filename);
            RandomAccessFile recordFile = new RandomAccessFile("d://tst//"+threadId+".txt", "rwd");


            //获取连接并设置相关属性。
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);


            conn.setRequestProperty("Range", "bytes=" + start + "-" + end);
//            System.out.println("Range"+ "bytes=" + start + "-" + end);

            // 从服务器请求全部资源的状态码200 ok 如果从服务器请求部分资源的状态码206 ok
            if (conn.getResponseCode() == 206) {

//                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                //移动指针至该线程负责写入数据的位置。
//                raf.seek(start);
                //读取数据并写入
                // HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。
                //无论是post还是get，http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去。
                // 已经设置了请求的位置，返回的是当前位置对应的文件的输入流
                InputStream inStream = conn.getInputStream();
                byte[] b = new byte[1024];
                int len = 0;
                int total = 0; //记录已经下载的数据的长度

//                read() 以整数形式返回实际读取的字节数
                while ((len = inStream.read(b)) != -1) {
                    //记录每个线程的下载进度，为断点续传做标记
                    RandomAccessFile recordFile1 = new RandomAccessFile("d://tst//"+threadId+".txt", "rwd");
                    recordFile1.seek(recordFile1.length());
//                    System.out.println(threadId+".txt put "+len+" size data");
//                    raf.write(b, 0, len);
                    recordFile1.write(b,0,len);

                    recordFile1.close();

//                    total +=len;
//                    recordFile.write(String.valueOf(start + total).getBytes());
//                    recordFile.close();

                }
                inStream.close();

//                raf.close();
                System.out.println("线程"+threadId+"下载完毕");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            threadNumdup--;
            if(threadNumdup ==0 ){ //all thread done

                System.out.println("begin mix");

                try {
                    RandomAccessFile raf = new RandomAccessFile(file, "rw");
                 //   raf.setLength(fileSize);
               //     raf.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

//                File fileOut = new File("D://tst//"+filename);

                for(int i=0; i<threadNum; i++){
//                    File file = new File("D://tst//"+i+".txt");

                    try {
//                        FileReader fr = new FileReader(file);
                        RandomAccessFile fr = new RandomAccessFile("D://tst//"+i+".txt","rw");
//                        RandomAccessFile fr = new RandomAccessFile("D://tst//"+i+".txt","rw");
//                        FileWriter fw = new FileWriter(fileOut);

                        byte[] b = new byte[1024];
                        int len = 0;
                        int total = 0; //记录已经下载的数据的长度

//                read() 以整数形式返回实际读取的字节数
                        while ((len = fr.read(b)) != -1) {
                            RandomAccessFile raf = new RandomAccessFile(file, "rw");
                            raf.seek(raf.length());
                            raf.write(b,0,len);
//                            raf.close();
                        }

                        fr.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


//                System.out.println("delete - ing");
//                for(int i=0; i<threadNum; i++){
//                    File file = new File("D://tst//"+i+".txt");
//                    System.out.println("!!!!delete - ing");
//                    file.delete();
//                }

            }

        }
    }
}