import java.util.Scanner;

/**
 * Created by zhangzexiang on 2016/8/17.
 */
public class ZebraTiger_1049 {


    public static void main(String[] args) {
        // write your code here
        Scanner reader = new Scanner(System.in);
        int m = reader.nextInt();
        int n = reader.nextInt();
        int count=6;

        if (n <= 3 && (m-n+1)>n) {

            System.out.println(-1);

        } else if (n > 2 * m) {

            System.out.println(1);

        }else if (n > m && n < 2 * m) {

            System.out.println(3);

        } else if (n == m) {

            System.out.println(5);

        } else if ((m - n + 1) <= n) {

            int num;
            if ((m - n + 2) % (n - 1) != 0) {

                num = (m - n + 2) / (n - 1);

                count = count + 2 * (num + 1);

            } else {

                num = (m - n + 2) / (n - 1);

                count = count + 2 * num;

            }

            count = count - 1;

            System.out.print(count);

        } else if ((m - n + 1) > n && n > 3) {

            int num;

            if ((m - n + 1) % (n / 2 - 1) != 0) {

                num = (m - n + 1) / (n / 2 - 1);

                count = count + 2 * num;

            } else {

                num = (m - n + 1) / (n / 2 - 1);

                count = count + 2 * (num - 1);

            }


            count = count - 1;

            System.out.print(count);
        }
    }




}
