import java.util.Scanner;

/*************************************************************************
 > File Name: CF-177-D.cpp
 > Author: ALex
 > Mail: zchao1995@gmail.com
 > Created Time: 2015年04月08日 星期三 19时09分18秒
 ************************************************************************/



public class VillegeHouse_1025 {


    static int mod = 1000000007;

    static double res;
    static int n, k;
    static int arr[] = new int[10];
    static boolean vis[] = new boolean[10];

   public static void dfs(int cur) {
        if (cur > k) {
            boolean flag = true;
            for (int i = 2; i <= k; ++i) {
                //memset(vis, 0, sizeof(vis));
                vis = new boolean[10];

                vis[i] = true;
                int u = arr[i];
                while (u != 1) {
                    if (vis[u]) {
                        break;
                    }
                    vis[u] = true;
                    u = arr[u];
                }
                if (u != 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ++res;
                res %= mod;
            }
            return;
        }
        for (int i = 1; i <= k; ++i) {
            arr[cur] = i;
            dfs(cur + 1);
        }
    }

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

         n = reader.nextInt();
         k = reader.nextInt();

            double ans = 1;
            for (int i = 1; i <= n - k; ++i) {
                ans *= (n - k);
                ans %= mod;
            }
            ans *= k;
            ans %= mod;
            res = 0;
            dfs(2);
            ans *= res;
            ans %= mod;
            System.out.println(ans);
        }
    }
