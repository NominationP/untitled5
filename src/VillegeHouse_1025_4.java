import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by zhangzexiang on 2016/8/5.
 */
public class VillegeHouse_1025_4 {


    public static  void main(String[] args){

        Scanner reader = new Scanner(System.in);

        int end = 1000000007;

        int n = reader.nextInt();
        int k = reader.nextInt();

        int res=1;

        res = (int)Math.pow(n-k,n-k);

        System.out.println((int)((Math.pow(2,n-1)*res)%end));


    }
}
