package downlodeThread;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MulThreadDownloader {

    public static void main(String[] args) throws Exception {
        String path = "http://bos.wenku.bdimg.com/v1/wenku29//a1c81513861a85f00d7db4b46d69a41d?responseContentDisposition=attachment%3B%20filename%3D%22%B3%F5%D6%D0%D7%F7%CE%C4%CB%D8%B2%C4%BF%E2%B4%F3%C8%AB.doc%22&responseContentType=application%2Foctet-stream&responseCacheControl=no-cache&authorization=bce-auth-v1%2Ffa1126e91489401fa7cc85045ce7179e%2F2016-09-14T06%3A28%3A55Z%2F3600%2Fhost%2Fb042b801127a181e99299560c300347619ed506a86f72fd167106e0733cbacba&token=0c4d498278a5434517a3f8fd54f160f86fcea96f972df98654975bc8234b804a&expire=2016-09-14T07:28:55Z";
        int threadsize = 3;
        new MulThreadDownloader().download(path, threadsize);

    }

    private void download(String path, int threadsize) throws Exception {
        URL downpath = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) downpath.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            int length = conn.getContentLength();//获取网络文件的长度
            File file = new File(getFileName(path));
            RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");//生成本地文件
            accessFile.setLength(length);
            accessFile.close();
            //计算每条线程负责下载的数据量
            int block = length % threadsize == 0 ? length / threadsize : length / threadsize +1;
            for(int threadid = 0 ; threadid < threadsize ; threadid++){
                new DownloadThread(threadid, downpath, block, file).start();
            }
        }
    }
    //负责下载操作
    private final class DownloadThread extends Thread{
        private int threadid;
        private URL downpath;
        private int block;
        private File file;

        public DownloadThread(int threadid, URL downpath, int block, File file) {
            this.threadid = threadid;
            this.downpath = downpath;
            this.block = block;
            this.file = file;
        }
        public void run() {
            int startposition = threadid * block;//从网络文件的什么位置开始下载数据
            int endposition = (threadid+1) * block - 1;//下载到网络文件的什么位置结束
            //指示该线程要从网络文件的startposition位置开始下载，下载到endposition位置结束
            //Range:bytes=startposition-endposition
            try{
                RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
                accessFile.seek(startposition);//移动指针到文件的某个位置
                HttpURLConnection conn = (HttpURLConnection) downpath.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Range", "bytes="+ startposition+ "-"+ endposition);
                InputStream inStream = conn.getInputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while( (len = inStream.read(buffer)) != -1 ){
                    accessFile.write(buffer, 0, len);
                }
                accessFile.close();
                inStream.close();
                System.out.println("第"+ (threadid+1)+ "线程下载完成");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取文件名称
     * @param path 下载路径
     * @return
     */
    private static String getFileName(String path) {
        return path.substring(path.lastIndexOf("/")+ 1);
    }

}