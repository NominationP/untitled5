package downlodeThread;

/**
 * Created by zhangzexiang on 2016/9/16.
 */

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.InputStream;
        import java.io.RandomAccessFile;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class MutilDownloader {
    // 开启的线程的个数
    public static final int THREAD_COUNT = 3;
    public static int runningThread = 3;// 记录正在运行的下载文件的线程数

    public static void main(String[] args) throws Exception {
        String path = "https://www.voidtools.com/Everything-1.3.4.686.x86-Setup.exe";
        // 1、连接服务器，获取一个文件，获取文件的长度，在本地创建一个大小跟服务器文件大小一样的临时文件
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        int code = conn.getResponseCode();
        if (code == 200) {
            // 服务器返回的数据的长度，实际就是文件的长度
            int length = conn.getContentLength();
            System.out.println("----文件总长度----" + length);
            // 在客户端本地创建出来一个大小跟服务器端文件一样大小的临时文件
            RandomAccessFile raf = new RandomAccessFile("temp.apk", "rwd");
            // 指定创建的这个文件的长度
            raf.setLength(length);
            // 关闭raf
            raf.close();
            // 假设是3个线程去下载资源
            // 平均每一个线程下载的文件的大小
            int blockSize = length / THREAD_COUNT;
            for (int threadId = 1; threadId <= THREAD_COUNT; threadId++) {
                // 第一个线程开始下载的位置
                int startIndex = (threadId - 1) * blockSize;
                int endIndex = threadId * blockSize - 1;
                if (threadId == THREAD_COUNT) {
                    endIndex = length;
                }
                System.out.println("----threadId---" + "--startIndex--"
                        + startIndex + "--endIndex--" + endIndex);
                new DownloadThread(path, threadId, startIndex, endIndex)
                        .start();
            }
        }

    }

    /**
     * 下载文件的子线程，每一个线程下载对应位置的文件
     *
     * @author loonggg
     *
     */
    public static class DownloadThread extends Thread {
        private int threadId;
        private int startIndex;
        private int endIndex;
        private String path;

        /**
         * @param path
         *            下载文件在服务器上的路径
         * @param threadId
         *            线程id
         * @param startIndex
         *            线程下载的开始位置
         * @param endIndex
         *            线程下载的结束位置
         */
        public DownloadThread(String path, int threadId, int startIndex,
                              int endIndex) {
            this.path = path;
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            try {

                // 检查是否存在记录下载长度的文件，如果存在读取这个文件的数据
                File tempFile = new File(threadId + ".txt");
                if (tempFile.exists() && tempFile.length() > 0) {
                    FileInputStream fis = new FileInputStream(tempFile);
                    byte[] temp = new byte[1024 * 10];
                    int leng = fis.read(temp);
                    // 已经下载的长度
                    String downloadLen = new String(temp, 0, leng);
                    int downloadInt = Integer.parseInt(downloadLen);
                    startIndex = downloadInt;
                    fis.close();
                }

                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setRequestMethod("GET");
                // 重要：请求服务器下载部分的文件 指定文件的位置
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-"
                        + endIndex);
                conn.setConnectTimeout(5000);
                // 从服务器请求全部资源的状态码200 ok 如果从服务器请求部分资源的状态码206 ok
                int code = conn.getResponseCode();
                System.out.println("---code---" + code);
                InputStream is = conn.getInputStream();// 已经设置了请求的位置，返回的是当前位置对应的文件的输入流
                RandomAccessFile raf = new RandomAccessFile("temp.apk", "rwd");
                // 随机写文件的时候从哪个位置开始写
                raf.seek(startIndex);// 定位文件
                int len = 0;
                byte[] buffer = new byte[1024];
                int total = 0;// 记录已经下载的数据的长度
                while ((len = is.read(buffer)) != -1) {
                    RandomAccessFile recordFile = new RandomAccessFile(threadId
                            + ".txt", "rwd");// 记录每个线程的下载进度，为断点续传做标记
                    raf.write(buffer, 0, len);
                    total += len;
                    recordFile.write(String.valueOf(startIndex + total)
                            .getBytes());
                    recordFile.close();
                }
                is.close();
                raf.close();
                System.out.println("线程：" + threadId + "下载完毕了！");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                runningThread--;
                if (runningThread == 0) {// 所有的线程已经执行完毕
                    for (int i = 1; i <= THREAD_COUNT; i++) {
                        File file = new File(i + ".txt");
                        file.delete();
                    }
                }
            }

        }
    }

}
