import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class BearPrison_10005_2 {

    static int maxn = 505;

   static int n=0;
    static int k=0;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] prison = new char[maxn][maxn];
    static int[][] fa = new int[maxn][maxn];
    static int[] dep = new int[maxn*maxn];
    static int[] edge = new int[maxn*maxn];
    static int a = 0;
    static int res=0;
    static  ArrayList<Integer> vr = new ArrayList<>();

    public static void edgePoint(int x, int y, int num) {
        if (checkcc(x,y)&&prison[x][y]=='.'){
            int id =  fa[x][y];
            if (edge[id]!=num){
                edge[id]=num;
                a+=dep[id];
            }
        }
    }

    public static void dfs(int x, int y, int num) {
        fa[x][y] = num;
        ++dep[num];
        for (int i=0;i<4;i++){
            int x2 = x+dx[i];
            int y2 = y + dy[i];
            if (checkcc(x2,y2)&&prison[x2][y2]=='.'&&fa[x2][y2]==0){
                dfs(x2,y2,num);
            }
        }
    }

    public static boolean checkcc(int x, int y) {
        return !(x < 0 || y >= n) && !(y < 0 || y >= n);
    }

    public  static void circle(int yyy){
        for (int x=0;x<k;x++){
            for (int y=yyy;y<yyy+k;y++){
                --dep[fa[x][y]];
            }
        }
    }




    public static void back(int yyy){
        for (int x=n-k;x<n;x++){
            for (int y=yyy;y<yyy+k;y++){
                ++dep[fa[x][y]];
            }
        }
    }


    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        n = reader.nextInt();
        k = reader.nextInt();

        for (int i = 0; i < n; i++) {
            String s = reader.next();
            char[] strings = s.toCharArray();
            System.arraycopy(strings, 0, prison[i], 0, strings.length);
        }

        int cnt = 0;
        for (int i=0;i<n;i++){;
            for (int j=0;j<n;j++){
                if (prison[i][j]=='.'&&fa[i][j]==0){
                    dfs(i,j,++cnt);
                }
            }
        }

        int number = 1;


        for (int yyy=0;yyy<n-k+1;yyy++){


            circle(yyy); //in circle point


           // edge(yyy,number); //out circle point
            for (int x_up=0;x_up<n-k+1;x_up++){
                a = k*k;

                for (int x= x_up;x<x_up+k;x++){
                    edgePoint(x,yyy-1,number);
                    edgePoint(x,yyy+k,number);
                }

                for (int y=yyy;y<yyy+k;y++){
                    edgePoint(x_up-1,y,number);
                    edgePoint(x_up+k,y,number);
                }

                ++number;
                vr.add(a);

//                System.out.print(a);
//                System.out.print(ans);
//                ans = Math.max(ans,a);

                if (x_up+k!=n){
                    for (int y=yyy;y<yyy+k;y++){
                        ++dep[fa[x_up][y]];
                        --dep[fa[x_up+k][y]];
                    }
                }

            }

            back(yyy); //back


        }
        Collections.sort(vr);
        System.out.println(vr.get(vr.size()-1));

    }


}
