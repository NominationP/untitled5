package inputStream;

        import static org.junit.Assert.*;

        import org.junit.Before;
        import org.junit.Test;

        import java.util.Map;
        import java.util.Scanner;


/** *//**
 *
 *   使用Junit4 来进行单元测试。
 * @version 1.0
 * @since 1.0
 */
public class RSACoderTest {
    private String publicKey;
    private String privateKey;

//    @Before
//    public void setUp() throws Exception {
//        Map<String, Object> keyMap = RSACoder.initKey();
//
//        publicKey = RSACoder.getPublicKey(keyMap);
//        privateKey = RSACoder.getPrivateKey(keyMap);
//        System.err.println("公钥: \n\r" + publicKey);
//        System.err.println("私钥： \n\r" + privateKey);
//    }

    @Test
    public void testPub2Pri() throws Exception {



        RSAInputStream rsa = new RSAInputStream(System.in);

        byte[] de =rsa.read("hellow world");

        rsa.write(de);






//        System.err.println("公钥加密——私钥解密");
//        String inputStr = "ee";
//        byte[] data = inputStr.getBytes();
//
//        byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);
//
//        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
//                privateKey);
//
//        String outputStr = new String(decodedData);
//        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
//        assertEquals(inputStr, outputStr);

//    }
//
//    @Test
//    public void testPri2Pub() throws Exception {
//        System.err.println("私钥加密——公钥解密");
//        String inputStr = "sign";
//        byte[] data = inputStr.getBytes();
//
//        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
//
//        byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);
//
//        String outputStr = new String(decodedData);
//        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
//        assertEquals(inputStr, outputStr);    //使用Junit断言，加密前的原文与解密后的明文是否一致。
//
//        System.err.println("私钥签名——公钥验证签名");
//        // 产生签名   这里的encodedData可以与下面的encodedData同时换成new int[]{2,45}
//        String sign = RSACoder.sign(encodedData, privateKey); //数字签名只要公钥人拿到签名的sign对比
//        //，自己公钥通过同样的byte[]运算得到签名是否一致。是到致代表这个公钥就是对的，就是为现在发私钥人服务的。
//        System.err.println("签名:\r" + sign);
//
//        // 验证签名
//        boolean status = RSACoder.verify(encodedData, publicKey, sign);
//        System.err.println("状态:\r" + status);
//        assertTrue(status);
//
//
   }
}
