//package downlodeThread.Standrad;
//
///**
// * Created by zhangzexiang on 2016/9/16.
// */
//public class ChildThread extends Thread {
//    public static final int STATUS_HASNOT_FINISHED = 0;
//    public static final int STATUS_HAS_FINISHED = 1;
//    public static final int STATUS_HTTPSTATUS_ERROR = 2;
//    private DownloadTask task;
//    private int id;
//    private long startPosition;
//    private long endPosition;
//    private final CountDownLatch latch;
//    // private RandomAccessFile tempFile = null;
//    private File tempFile = null;
//    //线程状态码
//    private int status = ChildThread.STATUS_HASNOT_FINISHED;
//
//    public ChildThread(DownloadTask task, CountDownLatch latch, int id, long startPos, long endPos) {
//        super();
//        this.task = task;
//        this.id = id;
//        this.startPosition = startPos;
//        this.endPosition = endPos;
//        this.latch = latch;
//
//        try {
//            tempFile = new File(this.task.fileDir + this.task.fileName + "_" + id);
//            if(!tempFile.exists()){
//                tempFile.createNewFile();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void run() {
//        System.out.println("Thread " + id + " run ...");
//        HttpURLConnection con = null;
//        InputStream inputStream = null;
//        BufferedOutputStream outputStream = null;
//        long count = 0;
//        long threadDownloadLength = endPosition - startPosition;
//
//        try {
//            outputStream = new BufferedOutputStream(new FileOutputStream(tempFile.getPath(), true));
//        } catch (FileNotFoundException e2) {
//            e2.printStackTrace();
//        }
//
//	③		for(int k = 0; k < 10; k++){
//            if(k > 0)
//                System.out.println("Now thread " + id + "is reconnect, start position is " + startPosition);
//            try {
//                //打开URLConnection
//                con = (HttpURLConnection) task.url.openConnection();
//                setHeader(con);
//                con.setAllowUserInteraction(true);
//                //设置连接超时时间为10000ms
//	④				con.setConnectTimeout(10000);
//                //设置读取数据超时时间为10000ms
//                con.setReadTimeout(10000);
//
//                if(startPosition < endPosition){
//                    //设置下载数据的起止区间
//                    con.setRequestProperty("Range", "bytes=" + startPosition + "-"
//                            + endPosition);
//                    System.out.println("Thread " + id + " startPosition is " + startPosition);
//                    System.out.println("Thread " + id + " endPosition is " + endPosition);
//
//                    //判断http status是否为HTTP/1.1 206 Partial Content或者200 OK
//                    //如果不是以上两种状态，把status改为STATUS_HTTPSTATUS_ERROR
//	⑤					if (con.getResponseCode() != HttpURLConnection.HTTP_OK
//                            && con.getResponseCode() != HttpURLConnection.HTTP_PARTIAL) {
//                        System.out.println("Thread " + id + ": code = "
//                                + con.getResponseCode() + ", status = "
//                                + con.getResponseMessage());
//                        status = ChildThread.STATUS_HTTPSTATUS_ERROR;
//                        this.task.statusError = true;
//                        outputStream.close();
//                        con.disconnect();
//                        System.out.println("Thread " + id + " finished.");
//                        latch.countDown();
//                        break;
//                    }
//
//                    inputStream = con.getInputStream();
//
//                    int len = 0;
//                    byte[] b = new byte[1024];
//                    while ((len = inputStream.read(b)) != -1) {
//                        outputStream.write(b, 0, len);
//                        count += len;
//	⑥						startPosition += len;
//                        //每读满4096个byte（一个内存页），往磁盘上flush一下
//                        if(count % 4096 == 0){
//	⑦							outputStream.flush();
//                        }
//                    }
//
//                    System.out.println("count is " + count);
//                    if (count >= threadDownloadLength) {
//                        status = ChildThread.STATUS_HAS_FINISHED;
//                    }
//	⑧					outputStream.flush();
//                    outputStream.close();
//                    inputStream.close();
//                    con.disconnect();
//                } else {
//                    status = ChildThread.STATUS_HAS_FINISHED;
//                }
//
//                System.out.println("Thread " + id + " finished.");
//                latch.countDown();
//                break;
//            } catch (IOException e) {
//                try {
//	⑨					outputStream.flush();
//	⑩					TimeUnit.SECONDS.sleep(getSleepSeconds());
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                } catch (IOException e2) {
//                    e2.printStackTrace();
//                }
//                continue;
//            }
//        }
//    }
//
//    private void setHeader(URLConnection con) {
//        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
//        con.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
//        con.setRequestProperty("Accept-Encoding", "aa");
//        con.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
//        con.setRequestProperty("Keep-Alive", "300");
//        con.setRequestProperty("Connection", "keep-alive");
//        con.setRequestProperty("If-Modified-Since", "Fri, 02 Jan 2009 17:00:05 GMT");
//        con.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");
//        con.setRequestProperty("Cache-Control", "max-age=0");
//        con.setRequestProperty("Referer", "http://www.dianping.com");
//    }
//}