
import java.util.*;

/**
 * Created by zhangzexiang on 2016/8/17.
 */
public class BearPrison_10005 {






//dfs---------------------------------------------
    static int maxn=510;
    static boolean vst[][] = new boolean[maxn][maxn]; // 访问标记
    static int dir[][]= new int[][]{{0,1},{0,-1},{1,0},{-1,0}}; // 方向向量，(x,y)周围的四个方向

    static int when_added[] = new int[maxn*maxn];
    static int fa[][]= new int[maxn][maxn];
    static int dep[] = new int[maxn*maxn];
    static int number=0;
    static Set<Integer> edge = new HashSet<>();


    //main---------------------------------------------
    static int prison[][] = new int[maxn][maxn];
    static int n,k;

    static ArrayList<Integer> ress = new ArrayList<>();


    public static boolean CheckEdge(int x,int y) // 边界条件和约束条件的判断
    {

        if(x<0 || x>=n || y<0 || y>=n){
            return false;
        }

        if(!vst[x][y] && prison[x][y] == 46 ) // 满足条件
        {

            return true;
        }

        else // 与约束条件冲突
            return false;
    }

    public static  void dfs(int x,int y)
    {
        vst[x][y]=true; // 标记该节点被访问过


        fa[x][y] = number;
        dep[fa[x][y]]++;


//    if(map[x][y]) // 出现目标态G
//    {
//        ...... // 做相应处理
//        return;
//    }
        for(int i=0; i<4; i++)
        {
            if(CheckEdge(x+dir[i][0],y+dir[i][1])) // 按照规则生成下一个节点
                dfs(x+dir[i][0],y+dir[i][1]);
        }
        return; // 没有下层搜索节点，回溯
    }

    public static void findEdge(int x, int y,int result, int num)
    {
        if(x>0 && x<n && y>0 && y<n && prison[x][y] == 46)
        {
            int id = fa[x][y];

            if(when_added[id] != num)
            {
                when_added[id] = num;
                result += dep[id];
            }
        }
    }


//    public static void findEdge(int x,int y){
//
//        if(x<0 || x>=n || y<0 || y>=n){
//            return;
//        }
//
//        if(prison[x][y]==46 && !edge.contains(fa[x][y])){
//
//            edge.findEdge(fa[x][y]);
//            res += dep[fa[x][y]];
//
//        }
//    }


    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);

        n = reader.nextInt();
        k = reader.nextInt();

        String s;

        reader.nextLine();

        for(int i=0; i<n; i++){
            s = reader.nextLine();
            for(int j=0; j<s.length(); j++){
                prison[i][j] = s.charAt(j);
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){

                if(prison[i][j] == 46 && vst[i][j] == false){

                    number++;

                    dfs(i,j);
                }
            }
        }

        //以上是DFS遍历一次
        //开始
        

        int cur_time = 1;
        int res  = 0;

        for(int y_low = 0; y_low + k - 1 < n; y_low++)
        {
            for(int x = 0; x < k; x++)
                for(int y = y_low; y < y_low + k; y++)
                {
                    --dep[fa[x][y]];
                }

            for(int x_low = 0; x_low + k - 1 < n; x_low++)
            {
                int result = k * k;

                for(int x = x_low; x < x_low + k; x++)
                {
                    findEdge(x, y_low - 1 ,result,cur_time);
                    findEdge(x, y_low + k, result, cur_time);
                }

                for(int y = y_low; y < y_low + k; y++)
                {
                    findEdge(x_low - 1, y, result, cur_time);
                    findEdge(x_low + k, y,result, cur_time);
                }

                ++cur_time;
                ress.add(res);

                if(x_low + k != n)
                {
                    for(int y = y_low; y < y_low + k; y++)
                    {
                        ++dep[fa[x_low][y]];
                        --dep[fa[x_low + k][y]];
                    }
                }
            }

            for(int x = n - k; x < n; x++)
                for(int y = y_low; y < y_low + k; y++)
                {
                    ++dep[fa[x][y]];
                }
        }


        Collections.sort(ress);
        System.out.println(ress.get(ress.size()-1));


    }




}
