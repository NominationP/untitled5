package downlodeThread;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.InputStream;
        import java.io.RandomAccessFile;
        import java.net.HttpURLConnection;
        import java.net.URL;

/**
 * @author zengtao
 */
public class DemoLoader {
    private static DemoLoader loader = new DemoLoader();
    private static int threadCount = 3;
    private static int runningThread = 3;

    private DemoLoader() {

    }

    public static DemoLoader getInstance() {
        return loader;
    }

    /**
     * 去服务器端下载文件
     *
     * @param path
     *            服务器地址
     */
    public void downFile(String path) {
        // 去服务器端获取文件的长度,在本地创建一个跟服务器一样大小的文件
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            int code = connection.getResponseCode();
            if (code == 200) {
                // 1.获取服务器端文件的长度
                int fileLength = connection.getContentLength();
                // 2.本地创建一个跟服务器一样大小的文件
                RandomAccessFile raf = new RandomAccessFile("setup.exe", "rwd");
                raf.setLength(fileLength);
                raf.close();
                // 3.假设三个线程下载
                int blockSize = fileLength / threadCount;
                for (int threadId = 0; threadId < threadCount; threadId++) {
                    int startIndex = (threadId - 1) * blockSize;
                    int endIndex = threadId * blockSize - 1;
                    if (threadId == threadCount) {
                        endIndex = fileLength;
                    }

                    // log 假设下载
                    System.out.println("假设线程：" + threadId + ",下载：" + startIndex
                            + "--->" + endIndex);
                    // 4.开始下载
                    new DownLoadThread(threadId, startIndex, endIndex, path)
                            .start();
                }
                System.out.println("文件总长度为：" + fileLength);
            } else {
                System.out.println("请求失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件的线程
     *
     * @author Administrator zengtao
     *
     */
    public class DownLoadThread extends Thread {
        private int threadId;
        private int startIndex;
        private int endIndex;
        private String path;

        /**
         *
         * @param threadId
         *            线程id
         * @param startIndex
         *            线程下载开始位置
         * @param endIndex
         *            线程下载结束位置
         * @param path
         *            线程下载结束文件放置地址
         */
        public DownLoadThread(int threadId, int startIndex, int endIndex,
                              String path) {
            super();
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.path = path;
        }

        @Override
        public void run() {
            super.run();
            try {
                // 1.检验是否有存的记录
                // ---------------------------------------------------
                File file = new File(threadId + ".txt");
                if (file.exists() && file.length() > 0) {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] temp = new byte[1024];
                    int leng = fis.read(temp);
                    String loadLength = new String(temp, 0, leng);
                    int load = Integer.parseInt(loadLength);
                    startIndex = load;
                    fis.close();
                }
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                // 2.请求服务器下载部分的文件，制定开始的位置，和结束位置
                connection.setRequestProperty("Range", "bytes=" + startIndex
                        + "-" + endIndex);
                // log 真实下载
                System.out.println("真实线程：" + threadId + ",下载：" + startIndex
                        + "--->" + endIndex);
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000);
                // 3.从服务器获取的全部数据，返回：200，从服务器获取部分数据，返回：206
                int code = connection.getResponseCode();
                System.out.println("code = " + code);
                InputStream is = connection.getInputStream();
                RandomAccessFile raf = new RandomAccessFile("setup.exe", "rwd");
                raf.seek(startIndex); // 随机写文件的时候，从什么时候开始
                int len = 0;
                int total = 0; // 记录下载多少
                byte[] buff = new byte[1024];
                while ((len = is.read(buff)) != -1) {
                    RandomAccessFile info = new RandomAccessFile(threadId
                            + ".txt", "rwd");
                    raf.write(buff, 0, len);
                    total += len;
                    info.write(("" + startIndex + total).getBytes()); // 4.存数据：（真正下载到开始的位置）下载的+开始的----------------------------------------
                    info.close();
                }
                is.close();
                raf.close();
                System.out.println("线程：" + threadId + ",下载完成");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 5.notice一定要文件都下载完毕之后再将记录文件删除
                runningThread--;
                if (runningThread == 0) {
                    for (int i = 1; i <= threadCount; i++) {
                        File file = new File(i + ".txt");
                        file.delete();
                    }
                    System.out.println("文件下载完毕，删除记录文件");
                }
            }
        }
    }
}