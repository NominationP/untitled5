import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by zhangzexiang on 2016/8/5.
 */
public class VillegeHouse_1025_3 {

    static int maxn=100;

    static boolean[] vst=new boolean[maxn]; // 访问标记

    static int[] map = new int[maxn]; // 坐标范围

    static int res = 1;

    static Set<Integer> set = new HashSet<Integer>();

    static  int k,n,se;



    //  int dir[4][2]= {0,1,0,-1,1,0,-1,0}; // 方向向量，(x,y)周围的四个方向

    static boolean CheckEdge(int x) // 边界条件和约束条件的判断
    {
        if(!vst[x] && !set.contains(x) && x<=4 ) // 满足条件
            return true;
        else // 与约束条件冲突
            return false;
    }



    static void dfs(int x)
    {
        map[x]=x;
        vst[x]=true; // 标记该节点被访问过d
        set.add(x);
        if(map[x]==1 && map.length>1) // 出现目标态G
        {
            //vst = new boolean[maxn];
            //map = new int[maxn];
            //set.clear();

            vst[x] =false;
            set.remove(map[x]);
            map[x] = 0;

            se++;  // 做相应处理

            return;
        }

        for(int i=1; i<=k; i++)
        {

            if(CheckEdge(map[i])) // 按照规则生成下一个节点
                dfs(i);
        }

        vst[x] =false;
        set.remove(map[x]);
        map[x] = 0;
        return; // 没有下层搜索节点，回溯
    }


    public static  void main(String[] args){

        Scanner reader = new Scanner(System.in);

        n = reader.nextInt();
        k = reader.nextInt();

        se = 0;

        res = k;

        for (int i=0; i<n-k; i++){

            res *= n-k;
        }
        for(int i=1; i<=k; i++)
            dfs(i);


        System.out.println(res*(se+1));


    }
}
