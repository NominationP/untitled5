import java.math.BigInteger;
import java.util.Scanner;





public class VillegeHouse_1025_5 {


    static BigInteger MOD = new BigInteger("1000000007");
    static double count1= 0.0;
    static double count2 = 0.0;

   public static BigInteger pow_mod(int n, int k) {
//       BigInteger ret = new BigInteger("1");
       BigInteger ret = new BigInteger("1");
       BigInteger p = new BigInteger(String.valueOf(n));
        while (k > 0) {
            if (k % 2 != 0) {
                ret = ret.multiply(p);
                ret = ret.mod(MOD);
            }
            p = p.multiply(p);
            p = p.mod(MOD);
            k = k / 2;
        }
        return ret;
    }

    public  static void main(String [] args) {
        Scanner reader = new Scanner(System.in);
        int n, k;
        n = reader.nextInt();
        k = reader.nextInt();

        BigInteger r1 = pow_mod(k,k-1);
        BigInteger r2 = pow_mod(n-k,n-k);

        System.out.println(r1.multiply(r2).mod(MOD));

    }
}
