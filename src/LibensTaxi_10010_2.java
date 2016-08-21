//import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MultiHashtable;
//import com.sun.xml.internal.ws.api.pipe.helper.PipeAdapter;
//import javafx.util.Pair;
//
//import java.awt.*;
//import java.awt.geom.Arc2D;
//import java.nio.DoubleBuffer;
//import java.util.*;
//import java.util.List;
//
///**
// * Created by zhangzexiang on 2016/8/9.
// */
//public class LibensTaxi_10010_2 {
//
//    static int maxn=100;
//    static boolean vst[][] = new boolean[maxn][maxn]; // 访问标记
//    static ArrayList<Point> direct = new ArrayList<>();   // 方向向量，(x,y)周围的四个方向
// //   static ArrayList<Point> countPoint = new ArrayList<>();  //记录经过的点  一条路径
//    static ArrayList<Point> policePoint  = new ArrayList<>(); //记录警察出现的点
//    static ArrayList<Double> outVt  = new ArrayList<>();    //记录出去一个点的速度
//    static Set<Double> countTime = new HashSet<>();   // 记录所有时间
//
//    static Map<Point , double[][]>  pointAllThing = new HashMap<>(); //记录到一个点的路程,速度，时间,
//
//   // static ArrayList<Pair<Point,ArrayList<Double>>> pointAllThing = new ArrayList<>(); //记录到一个点的路程，时间,速度
//
//    static int countPoint = 0; //记录是第几个点
//
//    static double journey = 0.0;
//    static double speed = 0.0;
//    static double time = 0.0;
//
//    static double journeyOld = 0.0;
//    static double speedOld = 0.0;
//    static double timeOld = 0.0;
//
//    static Object[][]  aaa = new Object[100][100];
//
//    static Queue<Point> q = new ArrayDeque<>();
//
//    static double[][] vtPoint = new double[100][100];
//
//
//    static int w,l,n,vmax,vp;
//    static double a;
//    static Point begin = new Point(0,0);
//    static Point end = new Point(0,0);
//    static Point now  = new Point(0,0);
//    static Point pass  = new Point(0,0);
//
//
////    struct State // BFS 队列中的状态数据结构
////    {
////        int x,y; // 坐标位置
////        int Step_Counter; // 搜索步数统计器
////    };
////    State a[maxn];
//
//    public static double getTime (double v0, double vt, double ac){ //初速度和末速度，加速度，求时间
//        return (vt-v0)/ac;
//    }
//
//    public static double getDis (double v0, double vt, double ac){ //求开始速度和结束速度的路程
//        return (vt*vt - v0*v0)/(2*a);
//    }
//
//    public static double getVt (double v0, double a, double x){ //根据初速度，加速度，和路程求初速度
//        return Math.sqrt(2*a*x + v0*v0);
//    }
//
//
//    public static boolean judge(Point pass){ //判断pointAllThing中原本有的pass点，和新点比较
//
//        //ArrayList<double[][]> = pointAllThing.get(pass);
//
//        double[][] onePointThing  =  pointAllThing.get(pass);
//
//      for(int i=0; i<onePointThing.length/3; i++){
//
//             if(onePointThing[i][1]==)
//
//         }
//
//      }
//
//
//
//
//
//
//
//
//
//    public static void addTimePoint (Point pass, final double journey, final double speed, final double time){   //添加点的所有信息，如果他的路长相等，那么选择时间小的
//
//       if(!pointAllThing.containsKey(pass)){
//            pointAllThing.put(pass,new double[][]{{journey,speed,time}});
//       }else if (judge(pass)){
//
//       }
//
//    }
//
//    public static void addVtPoint (Point pass, double vt){ //记录对应点的速度，如果之前没有速度，加，如果有速度比新速度大，
//
//        if(vtPoint[pass.x][pass.y] == 0.0 ){
//            vtPoint[pass.x][pass.y] = vt;
//            return ;
//        }
//
//
//        if( vtPoint[pass.x][pass.y] > vt) {
//            vtPoint[pass.x][pass.y] = vt;
//            return;
//        }
//
//    }
//
//
//
//
//public static boolean CheckEdge(Point pass, Point oldPass) // 边界条件和约束条件的判断
//{
//
////    double[] countRoad = pointAllThing.getValue();
////    Map<Integer,Integer> map =  new HashMap<>();
////    pointAllThing.put(new Pair<Point, Double>(pass,))
////    if()
//
//    Set<Integer> st = new HashSet<>();
//
//    st.add(1);
//
//
//    journeyOld = pointAllThing.get(pass)[0][0];
//    speedOld = pointAllThing.get(pass)[0][1];
//    timeOld = pointAllThing.get(pass)[0][2];
//
//   // countPoint.add(pass);
//
//    if(policePoint.contains(pass)){
//
//    }else{
//
//        double x1 = getDis(speed,vmax,a); //从初速度到末速度加速度为a，所用的路程
//
//        if(x1 == 1){ //如果从初速度到最大速度正好路过
//
//             time = getTime(speed,vmax,a);
//             speed = vmax;
//
//         //   countVt[pass.x][pass.y] = vmax;
//            addTimePoint(pass,journeyOld+1,speed,time);
//        }
//
//        if (x1 < 1){ //如果从初速度到末速度，所走的路程没能穿过点（x1<1）
//
//            double t1 = getTime(v0,vmax,a);
//            double x2 = 1-x1;
//            double t2 = x2/vmax;
//            double tsum = t1+t2;
//
//            addTimePoint(pass,tsum);
//
//        } else{
//
//            double vt = getVt(v0,a,1);
//            double t1 = getTime(v0,vt,a);
//
//            addTimePoint(pass,t1);
//        }
//
//    }
//
//
//
//    if(pass.x<0 || pass.y<0){
//        return false;
//    }
//    if(!vst[pass.x][pass.y] && pass.x<=w && pass.x>=0 && pass.y<=l && pass.y>=0) // 满足条件
//        return true;
//    else // 与约束条件冲突
//        return false;
//}
//
//    public static void bfs(Point begin)
//    {
//       // Queue <Point> q; // BFS 队列
//        int step_count=0; // 计数器清零
//        countPoint.add(begin);
//
//        pointAllThing.put(pass,new HashSet<double[][]>(){{add(new double[][]{{0,0,0 }});
//
//        //outVt.add(0.0);
//        countVt[begin.x][begin.y] = 0;
//
//        q.add(begin); // 入队
//        vst[begin.x][begin.y]=true; // 访问标记
//        while(!q.isEmpty())
//        {
//            now = q.peek(); // 取队首元素进行扩展
//            if(now == end) // 出现目标态，此时为Step_Counter 的最小值，可以退出即可
//            {
//
//
//
//
//            ...... // 做相关处理
//                return;
//            }
//            for (int j = 0; j < 3; j++) {
//                Point tempDirector = direct.get(j);
//                Point newPass = new Point(pass.x + tempDirector.x, pass.y + tempDirector.y);// 按照规则生成下一个节点
//                countPoint++;
//                if (CheckEdge(newPass,pass)) {
//                    CaculateTime();
//                    q.add(pass);
//                    vst[pass.x][pass.y]=true;//访问标记
//                }
//
//            q.remove(); // 队首元素出队
//        }}
//        return;
//    }
//}
