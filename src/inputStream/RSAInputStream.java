package inputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhangzexiang on 2016/9/8.
 */
public class RSAInputStream extends InputStream{


    private String publicKey;
    private String privateKey;



    public void setUp() throws Exception {
        Map<String, Object> keyMap = RSACoder.initKey();

        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);

    }


    public RSAInputStream(InputStream in) {
    }

    public byte[] read(String ee) throws Exception {

        setUp();



        String inputStr = ee;

        byte[] data = inputStr.getBytes();
       byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);


        System.err.println("加密前: " + inputStr + "\n\r"+"加密后："+encodedData+"\n\r" );
        //assertEquals(inputStr, outputStr);
        return encodedData;

    }


    public void  write(byte[] encodedData ) throws Exception {

        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData, privateKey);

        String outputStr = new String(decodedData);
        System.err.println("解密后: " + outputStr);
        //  assertEquals(inputStr, outputStr);
        //return encodedData;

    }



    @Override
    public int read() throws IOException {


        return 0;
    }
}
