package downlodeThread;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhangzexiang on 2016/9/14.
 */
public class Download {
    /**
     * 开启线程的数量
     */
    static int threadCount=3;
    /**
     * 访问下载的路径
     */
    static String path="http://bos.wenku.bdimg.com/v1/wenku45//f604b515590ded755d6d01fce92ed4b0?responseContentDisposition=attachment%3B%20filename%3D%22%B3%F5%D6%D0%D7%F7%CE%C4%CB%D8%B2%C4.doc%22&responseContentType=application%2Foctet-stream&responseCacheControl=no-cache&authorization=bce-auth-v1%2Ffa1126e91489401fa7cc85045ce7179e%2F2016-09-14T06%3A46%3A35Z%2F3600%2Fhost%2F72af119a9cc5c5f6aa7a1d10097c92b31589837d9a171ba0913fcfaaaa3e246e&token=f08481902db376f96f27310cc7188fb666d0a51badb1f4f83f625f2db1707e64&expire=2016-09-14T07:46:35Z";

    static int threadFinished=0;
    public static void main(String[] args) {
        try {
            //创建URL对象  参数：设置路径
            URL url=new URL(path);
            //打开连接，通过强制类型转换HttpURLConnection
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            //设置请求的方式
            connection.setRequestMethod("GET");
            //设置读取的时间
            connection.setReadTimeout(5000);
            //设置连接的时间
            connection.setConnectTimeout(5000);
            if (connection.getResponseCode()==200) {
                //拿到要下载文件的大小
                int length = connection.getContentLength();
                //指定临时文件的路径和文件名
                File file=new File(getFileName(path));
                //创建随机存储文件对象
                RandomAccessFile raf=new RandomAccessFile(file, "rwd");
                //设置临时文件的大小和服务器的一模一样

                raf.setLength(length);
                //计算每个线程下载的字节数
                int size=length/threadCount;
                for (int i = 0; i <threadCount; i++) {
                    //计算3个线程的开始位置和结束位置
                    int startIndex=i*size;
                    int endIndex=(i+1)*size-1;
                    //如果是最后一个线程，那么结束位置特殊处理
                    if (i==threadCount-1) {
                        endIndex=length-1;
                    }
                    System.out.println("线程"+i+"-----------"+startIndex+"-------"+endIndex);
                    DowndLoadThread thread = new DowndLoadThread(i, startIndex, endIndex);
                    thread.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 得到文件名字的
     * @param path 传入的访问的路径
     * @return 返回处理后的结果
     */
    public  static String getFileName(String path){
        //找到最后/的索引
        int index = path.lastIndexOf("/");
        //进行截取
        return path.substring(index+1);

    }
    static class DowndLoadThread extends Thread{
        int threadId;
        int startIndex;
        int endIndex;

        /**
         * 创建有参构造函数
         * @param threadId 第几个线程的
         * @param startIndex 开始的索引
         * @param endIndex   结束的索引
         */
        public DowndLoadThread( int threadId,int startIndex, int endIndex) {
            super();
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            super.run();
            URL url;
            try {
                //设置线程从那个位置开始写入数据到临时文件
                File fileProgress=new File("d://"+threadId+".txt");
                //判断下载的临时文件是否存在
                if (fileProgress.exists()) {
                    FileInputStream fis=new FileInputStream(fileProgress);
                    BufferedReader br=new BufferedReader(new InputStreamReader(fis));
                    //拿到临时文件所存储的位置
                    int newStartIndex = Integer.parseInt(br.readLine());
                    startIndex=newStartIndex;
                    System.out.println("线程"+threadId+"最终位置---------------"+startIndex);
                }
                url = new URL(path);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                //设置请求数据的范围
                connection.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);

                if (connection.getResponseCode()==206) {
                    InputStream is = connection.getInputStream();
                    int len=0;
                    byte[] b=new byte[1024];
                    int total=0;
                    File file=new File("d://"+Download.getFileName(path));
                    //创建随机文件的存储对象
                    RandomAccessFile raf=new RandomAccessFile(file, "rwd");
                    raf.seek(startIndex);
                    //记录当前下载进度
                    int currentPosition=startIndex;

                    while((len=is.read(b))!=-1){
                        //把下载下来的临时文件写入raf临时文件
                        raf.write(b, 0, len);
                        total+=len;
                        @SuppressWarnings("resource")
                        RandomAccessFile rafProgress=new RandomAccessFile(fileProgress, "rwd");
                        currentPosition=startIndex+total;
                        //把下载进度写入rafProgress临时文件，下一次下载时作为新的startIndex
                        rafProgress.write((currentPosition+"").getBytes());
                        System.out.println("线程"+threadId+"---------------"+total);
                    }
                    raf.close();
                    System.out.println("线程"+threadId+"下载完毕");
                    Download.threadFinished++;
                    //如果这个条件成立，说明线程下载完毕
                    synchronized (Download.path) {
                        if (Download.threadFinished==Download.threadCount) {
                            for (int i = 0; i < Download.threadCount; i++) {
                                File temp=new File("d://"+i+".txt");
                                temp.delete();
                            }
                            Download.threadFinished=0;
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
