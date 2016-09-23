//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package inputStream;

import inputStream.RSAInputStream;
import org.junit.Test;

public class main {
    private String publicKey;
    private String privateKey;

    public main() {
    }

    @Test
    public void testPub2Pri() throws Exception {
        RSAInputStream rsa = new RSAInputStream(System.in);
        byte[] de = rsa.read("hellow world");
        rsa.write(de);
    }
}

