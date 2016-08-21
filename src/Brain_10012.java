import java.util.Scanner;

/**
 * Created by zhangzexiang on 2016/8/11.
 */
public class Brain_10012 {
    public  static void main(String[] args){
        Brain_10012 task = new Brain_10012();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] title = new int[n+1];//     ÿ   ڵ Ĳ
        title[1] = 0;// ѳ ʼ   ĵ һ   ڵ ģ    Ĳ     ó 0
        int[][] fa = new int[n+1][80]; //ǰ
        int u = 1;
        int v = 2;
        int result = 1;

        fa[1][1] = -1;

        for(int i=0;i<n-1;i++) {
            int w = i + 2;
            int input = scanner.nextInt();
//            int input = new Random().nextInt(i+1)+1;
            title[i+2] = title[input] + 1 ;
            fa[i+2][0] = input ;
            int a = 0;
            while (!(fa[i+2][a] ==1)){
                fa[i+2][a+1] = fa[fa[i+2][a]][0];
                int jiji =  fa[i+2][a+1];
                a = a + 1;
            }
//            if (input==i+1){
//                result = result +1;
//            }else {
//        //        task.lca(i+1,i+2,title,fa)
//                result =  title[i+1] + title[i+2]  - 2 *title[task.lca(i+1,i+2,title,fa)]  ;
//                }
            if (i+2!=2){
                //               result =  title[zhege] + title[i+2]  - 2 *title[task.lca(i+1,i+2,title,fa)]
                int vu = result;
                int wu = title[w] + title[u]  - 2 *title[task.lca(w,u,title,fa)];
                int vw = title[w] + title[v]  - 2 *title[task.lca(w,v,title,fa)];
                if (wu>vu){
                    v = w ;
//                    vu = wu;
                    result = wu;
                }
                if (vw>vu){
                    u = w;
                    result = vw;
                }

            }
            System.out.print(result+" ");

        }

    }
//        System.out.print(task.lca(3,6,title,fa));
//        for (int t = 0; t<20;t++){
//            for (int o = 2;o<=n;o++){
//                System.out.print(fa[o][t]);
//            }
//                System.out.println();
//        }

    // public void dedaofujiedian()

    int lca(int u, int v,int[] title,int[][] fa) {
        if (title[u] > title[v]){
            int temp = 0;
            temp  = u;
            u = v;
            v = temp;
        }
        int hu = title[u], hv = title[v];
        int tu = u, tv = v;

        int det;
        int i;
        for ( det = hv - hu , i = 0;  det >= 1 && i>=0 ;i++,det--)
        {
            if (det == 1) tv = fa[tv][i];
        }
        if (tu == tv) return tu;

        for (int j = 79; j >= 0; j--) {
            if (fa[tu][j] == fa[tv][j]) continue;
            tu = fa[tu][j];
            tv = fa[tv][j];
        }
        return fa[tu][0];
    }
}
