import javafx.util.Pair;

import java.util.*;

/**
 * Created by zhangzexiang on 2016/8/10.
 */
public class LibensTaxi_10010_3 {


    static int maxn = 100 + 10;
    static int dir[][] =new int[][]{{0, 1},{1,0}, {0, -1}, {-1, 0}};
    static int INF = 0x3f3f3f3f;
    static int maxe = 20000+100;
    static double eps = 1e-8;

    static int n, W, L;
    static double a, vm, vp;
    static Pair<Integer,Integer> pos[] = new Pair[maxn];
    static int vis[][] =  new int[maxn][maxn];//0 st 1 ed >=2 police
    static int vis2[][] = new int[maxn][maxn];
    static ArrayList<Integer> neighbor = new ArrayList<>();
    static int d[] = new int[maxn];
    static int Head[] = new int[maxn];
    static int cntE;
    static Edge edge[]= new Edge[maxe];
    static double dist[] = new double[maxn];
    static double cur_v[] = new double[maxn];

    static boolean b1 =true;
    static boolean b2 =true;
    static boolean b3 =true;


//    typedef pair<int, int> pair<int,int>;
    public static class Edge{
        int v, next;
        double d;
        Edge(int v1,double d1, int next1){

            v = v1;
            d = d1;
            next = next1;

        }
       // Edge(int v = 0, double d = 0, int next = 0) : v(v), d(d), next(next) {}
    }
    public static class HeapNode{
        int v;
        double min_time;

        //HeapNode(){}
        HeapNode(int v1,double min_time1){

            v = v1;
            min_time=min_time1;
        }

        //boolean compart  (HeapNode rhs){
//            return min_time > rhs.min_time;
//        }

        //HeapNode(int v, double min_time) : v(v), min_time(min_time) {}
        //HeapNode() {}
//        bool operator < (const HeapNode &rhs) const{
//            return min_time > rhs.min_time;
        }




    public static boolean judge(int x, int y){
        if(x >= 0 && x <= W && y >= 0 && y <= L)
            return true;
        return false;
    }
    public static class Work{
        int x, y;
        int step;

        Work(){}
        Work(int x1, int y1,int step1) {

            x = x1;
            y = y1;
            step = step1;

        }

        //Work(int x, int y, int step) : x(x), y(y), step(step) {}
        //Work() {}
    }

   public static void bfs(int x, int y, int cu){
        Work st = new Work(x, y, 0);
        Queue<Work> Q = new ArrayDeque<>();
        Q.add(st);
        d[cu] = 0;
        while(!Q.isEmpty()){
            Work cur = Q.peek(); Q.poll();
            int xx = cur.x;
            int yy = cur.y;
            if(vis2[xx][yy]!=0) continue;
            vis2[xx][yy] = 1;
            int step = cur.step;
            if(vis[xx][yy]==0){
                b1 = false;
            }else {
                b1 = true;
            }
            if(b1 && vis[xx][yy] != cu){
                d[vis[xx][yy]] = step;
                neighbor.add(vis[xx][yy]);
                continue;
            }
            for(int i = 0; i < 4; i++){
                int dx = xx + dir[i][0];
                int dy = yy + dir[i][1];

//                if(vis2[dx][dy] ==0){
//                    b3=false;
//                }else {
//                    b3=true;
//                }


                if(!judge(dx, dy) || vis2[dx][dy]!=0) continue;
                Q.add(new Work(dx, dy, step + 1));
            }
        }
    }
    public static void init(){
      //  memset(Head, -1, sizeof(Head));
        for(int i=0; i<Head.length; i++){
            Head[i] = -1;
        }
        cntE = 0;
    }
    public static void add(int u, int v, int d){
        edge[cntE] = new Edge(v, (double)(d), Head[u]);
        Head[u] = cntE++;
    }
   public static double get_time(double v0, double vt, double s, double vf){
        double t1 = (vt - v0) / a;
        if(v0 * t1 + a * t1 * t1 / 2 >= s){
            double ans = (Math.sqrt(v0 * v0 + 2 * a * s) - v0) / a;
            vf = v0 + a * ans;
            return ans;
        }
        else{
            double len = (2 * vm * vm - v0 * v0 - vt * vt) / 2 / a, vx;
            vf = vt;
            if(len > s){
                vx = Math.sqrt((2 * a * s + v0 * v0 + vt * vt) / 2);
                return (2 * vx - v0 - vt) / a;
            }
            else{
                return (2  * vm - v0 - vt) / a + (s - (2 * vm * vm - v0 * v0 - vt * vt) / 2 / a) / vm;
            }
        }
    }

    public static void dijkstra(){
        double ddd;
        for(int i = 0; i < n + 3; i++) dist[i] =  INF;
        dist[1] = 0;
        PriorityQueue<HeapNode> Q = new PriorityQueue<>();
        Q.add(new HeapNode(1, 0));
        while(!Q.isEmpty()){
            HeapNode cur = Q.peek(); Q.poll();
            int u = cur.v;
            double t = cur.min_time;
            for(int i = Head[u]; i>=0; i = edge[i].next){
                int v = edge[i].v;
                double v0, vt, vf=0;
                v0 = cur_v[u];
                if(v > 2) vt = vp;//is police
                else vt = vm;
                double tt = get_time(v0, vt, edge[i].d, vf);
                if(dist[v] > dist[u] + tt){
                    dist[v] = dist[u] + tt;
                    cur_v[v] = vf;

                    Q.add( new HeapNode(v, dist[v]));
                }
            }
        }
    }
    public static void main(String[] args)
    {

        Scanner reader= new Scanner(System.in);

        W = reader.nextInt();
        L = reader.nextInt();
        n = reader.nextInt();
        a = reader.nextDouble();
        vm = reader.nextInt();
        vp = reader.nextInt();

        if(vm < vp) vp = vm;

        for(int i=1; i<=2; i++){
            int x = reader.nextInt();
            int y = reader.nextInt();
            pos[i]=new Pair<>(x,y);
        }


       // scanf("%d%d%d%d", &pos[1].first, &pos[1].second, &pos[2].first, &pos[2].second);
        vis[pos[1].getKey()][pos[1].getValue()] = 1;
        vis[pos[2].getKey()][pos[2].getKey()] = 2;
        for(int i = 3; i < n + 3; i++){
            //int x, y;
            int x = reader.nextInt();
            int y = reader.nextInt();
            //scanf("%d%d", &x, &y);
            pos[i] = new Pair<>(x,y);
            //pos[i].first = x;
            //pos[i].second = y;
            vis[x][y] = i;
        }
        init();
        for(int i = 1; i < n + 3; i++){
            int x = pos[i].getKey();
            int y = pos[i].getValue();
            //memset(vis2, 0, sizeof(vis2));
            for(int e=0 ; e<maxn; e++){
                for(int ee=0 ;ee<maxn; ee++)
                vis2[e][ee] = 0;
            }

            //memset(d, INF, sizeof(d));
            for(int ii=0 ;ii<maxn; ii++){
                d[ii] = INF;
            }
            neighbor.clear();
            bfs(x, y, i);
            for(int j = 0; j < (int)neighbor.size(); j++){
                add(i, neighbor.get(j), d[neighbor.get(j)]);
            }
        }
        dijkstra();
       // printf("%.10lf\n", dist[2]);

        System.out.println(dist[2]);
    }
}
