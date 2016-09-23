//package downlodeThread.Standrad;
//
///**
// * Created by zhangzexiang on 2016/9/16.
// */
//public class DownloadStartup {
//
//    private static final String encoding = "utf-8";
//    public static void main(String[] args) {
//        DownloadTask downloadManager = new DownloadTask();
//        String urlStr = "http://apache.freelamp.com/velocity/tools/1.4/velocity-tools-1.4.zip";
//        downloadManager.setSleepSeconds(5);
//        downloadManager.download(urlStr, encoding);
//    }
//
//    public String download(String urlStr, String charset) {
//        this.charset = charset;
//        long contentLength = 0;
//①		CountDownLatch latch = new CountDownLatch(threadNum);
//        long[] startPos = new long[threadNum];
//        long endPos = 0;
//
//        try {
//            // 从url中获得下载的文件格式与名字
//            this.fileName = urlStr.substring(urlStr.lastIndexOf("/") + 1, urlStr.lastIndexOf("?")>0 ? urlStr.lastIndexOf("?") : urlStr.length());
//            if("".equalsIgnoreCase(this.fileName)){
//                this.fileName = UUID.randomUUID().toString();
//            }
//
//            this.url = new URL(urlStr);
//            URLConnection con = url.openConnection();
//            setHeader(con);
//            // 得到content的长度
//            contentLength = con.getContentLength();
//            // 把context分为threadNum段的话，每段的长度。
//            this.threadLength = contentLength / threadNum;
//
//            // 第一步，分析已下载的临时文件，设置断点，如果是新的下载任务，则建立目标文件。在第4点中说明。
//            startPos = setThreadBreakpoint(fileDir, fileName, contentLength, startPos);
//
//            //第二步，分多个线程下载文件
//            ExecutorService exec = Executors.newCachedThreadPool();
//            for (int i = 0; i < threadNum; i++) {
//                // 创建子线程来负责下载数据，每段数据的起始位置为(threadLength * i + 已下载长度)
//                startPos[i] += threadLength * i;
//
//				/*设置子线程的终止位置，非最后一个线程即为(threadLength * (i + 1) - 1)
//				最后一个线程的终止位置即为下载内容的长度*/
//                if (i == threadNum - 1) {
//                    endPos = contentLength;
//                } else {
//                    endPos = threadLength * (i + 1) - 1;
//                }
//                // 开启子线程，并执行。
//                ChildThread thread = new ChildThread(this, latch, i, startPos[i], endPos);
//                childThreads[i] = thread;
//                exec.execute(thread);
//            }
//
//            try {
//                // 等待CountdownLatch信号为0，表示所有子线程都结束。
//②				latch.await();
//                exec.shutdown();
//
//                // 第三步，把分段下载下来的临时文件中的内容写入目标文件中。在第3点中说明。
//                tempFileToTargetFile(childThreads);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//}
