package inputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by zhangzexiang on 2016/9/8.
 */
public class UnZipExample {
    public static void main(String[] args) {
        String zipFile = "log.zip";
        String outputFolder = "output";
        byte[] buffer = new byte[1024];
        try {
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }
            //获取zip文件
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            //获取zip文件里面的文件列表
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);
                System.out.println("文件解压 : " + newFile.getAbsoluteFile());
                //获取文件名中的路径创建文件夹
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            System.out.println("End");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
