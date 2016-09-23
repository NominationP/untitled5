package inputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhangzexiang on 2016/9/8.
 */
public class RSAOutputStream extends OutputStream {

    String publicKey;
    String privateKey;

    public RSAOutputStream() {
    }


    public void  write1(byte[] decodedData) throws Exception {


        Map<String, Object> keyMap = null;
        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);


        String outputStr = new String(decodedData);
        System.err.println("解密后: " + outputStr);
      //  assertEquals(inputStr, outputStr);
        //return encodedData;

    }


    @Override
    public void write(int b) throws IOException {

    }
}
