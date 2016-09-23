///**
// * Created by zhangzexiang on 2016/8/27.
// */
//
//听海 2016/8/27 22:01:34
//        import java.util.Scanner;
//
//public class Main10020 {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        int m = scan.nextInt();
//        char[][] chars = new char[n][m];
//        int[][] cost = new int[n][m];
//        int[][]  colCost=new int[n][m];
//        int[][] state = new int[n][m];
//        int[] dp = new int[1<<21];
//        for (int i=0;i<n;i++){
//            System.arraycopy(scan.next().toCharArray(),0,chars[i],0,m);
//        }
//        int ok = (1<<n)-1;           //n位都为1的二进制数，第几位为1代表相应第几个字符串满足状态
//        for (int i=0;i<n;i++){
//            for (int j=0;j<m;j++){
//                cost[i][j] = scan.nextInt();
//            }
//        }
//        for (int i=0;i<n;i++){
//            for (int k=0;k<m;k++){
//                int maxCost = 0;
//                for (int j=0;j<n;j++){
//                    if (chars[i][k]==chars[j][k]){
//                        colCost[i][k] += cost[j][k];
//                        maxCost = Math.max(cost[j][k],maxCost);
//                        state[i][k] |= 1<<j;            //将与第i行第k个字母相同的行对应的二进制位标为1
//                    }
//                }
//                colCost[i][k] -=maxCost;                             //修改第i行第k个字母所在列除需最大金币数字母外的所有字母所需金币数
//            }
//        }
//        for (int i=0;i<dp.length;i++){
//            dp[i] = 0x3f;
//        }
//        dp[0] = 0;
//        for ( int i = 0 ; i < ok ; i++ ) {
//            for (int j = 0; ; j++) {
//                if (((i >> j) & 1) == 0) {       //i右移j位与1为0，例0001右移1位与1为0，即j为i从右往左数第一位为0的位数-1,
//                    for (int k = 0; k < m; k++) {
//
//                        //将i的右数第一位为0的位置1后所有为1位数对应的字符串满足状态所需花费最小金币数
//                        //分别修改第j行第k个字母所需金币数中最小金币数即为第i种情况第j行一定满足状态的所需最小金币数
//                        dp[i | (1 << j)] = Math.min(dp[i | (1 << j)], dp[i] + cost[j][k]);
//
//                        //分修改第j行第k个字母所在列使第j行一定满足状态的所需金币数中最小的值
//                        //第i种情况第j行一定满足状态
//                        dp[i | state[j][k]] = Math.min(dp[i | state[j][k]], dp[i] + colCost[j][k]);
//                    }
//                    break;
//                }
//            }
//        }
//        System.out.println(dp[ok])  ;
//    }
//}
