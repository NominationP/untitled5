package downlodeThread;

/**
 * Created by zhangzexiang on 2016/9/14.
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 多线程下载
 * @author xqq
 */
public class MutilThreadDownload extends Thread {


    private String downloadPath;
    private String savePath;
    private int nThread;
    private final ExecutorService exec;

    public MutilThreadDownload(String downloadPath, String savePath, int nThread) {
        this.downloadPath = downloadPath;
        this.savePath = savePath;
        this.nThread = nThread;
        this.exec = Executors.newFixedThreadPool(nThread);
    }

    @Override
    public void run() {
        try {
            // 连接获取文件信息
            URL url = new URL(downloadPath);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            int code = conn.getResponseCode();
            if (code == 200) {

                int length = conn.getContentLength();// 获取文件长度
                System.out.println("文件的总长度为： " + length);

                //每个线程所需下载文件的大小
                int blockSize = length / nThread;

                for (int i = 1; i <= nThread; i++) {

                    int startIndex = (i - 1) * blockSize;
                    int endIndex = i * blockSize - 1;

                    if (i == nThread) {
                        endIndex = length;
                    }
                    //开始下载文件
                    exec.execute(new Download(startIndex, endIndex, downloadPath, i, savePath));
                }
            } else {
                System.out.println("文件错误......");
            }

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        //当所有线程均下载完成之后，关闭线程池
        exec.shutdown();
    }

    public static class Download implements Runnable{

        private int startIndex;
        private int endIndex;
        private String path;
        private static int runningThread = Test.nThread;
        private int threadID;
        private String savePath;

        public Download(int startIndex, int endIndex, String path, int threadID, String savePath) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.path = path;
            this.threadID = threadID;
            this.savePath = savePath;
        }

        public void run() {

            try {
                File tempFile = new File(savePath + threadID + ".txt");

                if(tempFile.exists() && tempFile.length() > 0){
                    FileInputStream fis = new FileInputStream(tempFile);
                    byte [] temp = new byte[1024];
                    int length = fis.read(temp);
                    String downloadLen = new String(temp, 0, length);
                    startIndex = Integer.parseInt(downloadLen);
                    fis.close();
                }
                System.out.println(Thread.currentThread() + " 下载范围： " + startIndex + " - >" + endIndex);
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");

                int code = conn.getResponseCode();
                //获取部分数据，成功返回206
                if(code == 206){
                    RandomAccessFile file = new RandomAccessFile(savePath + "setup.exe", "rwd");
                    file.seek(startIndex);//定位到文件的位置

                    InputStream in = conn.getInputStream();

                    byte [] buffer = new byte[1024 * 2];
                    int len = 0;
                    int total = 0;
                    while((len = in.read(buffer)) != -1){
                        RandomAccessFile totalFile = new RandomAccessFile(savePath + threadID + ".txt", "rwd");
                        file.write(buffer, 0, len);
                        total += len;
                        totalFile.write((total + startIndex + "").getBytes());
                        totalFile.close();
                    }
                    file.close();
                }else{
                    System.out.println(Thread.currentThread() + " 下载失败");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{

                System.out.println(savePath + threadID + ".txt");
                System.out.println(Thread.currentThread() + " 下载完成.....");
                runningThread --;
                if(runningThread == 0){
                    for(int i = 1; i <= Test.nThread; i++){
                        File file = new File(savePath + i + ".txt");
                        file.delete();
                    }
                }
            }
        }
    }


    public static class Test {
        private final static String downloadPath = "http://bos.wenku.bdimg.com/v1/wenku45//f604b515590ded755d6d01fce92ed4b0?responseContentDisposition=attachment%3B%20filename%3D%22%B3%F5%D6%D0%D7%F7%CE%C4%CB%D8%B2%C4.doc%22&responseContentType=application%2Foctet-stream&responseCacheControl=no-cache&authorization=bce-auth-v1%2Ffa1126e91489401fa7cc85045ce7179e%2F2016-09-14T06%3A46%3A35Z%2F3600%2Fhost%2F72af119a9cc5c5f6aa7a1d10097c92b31589837d9a171ba0913fcfaaaa3e246e&token=f08481902db376f96f27310cc7188fb666d0a51badb1f4f83f625f2db1707e64&expire=2016-09-14T07:46:35Z";
        private static final String savePath = "d://backup";
        public static final int nThread = 5;

        public static void main(String[] args) {
            MutilThreadDownload thread = new MutilThreadDownload(downloadPath,savePath,  nThread);
            thread.start();
        }
    }
}
