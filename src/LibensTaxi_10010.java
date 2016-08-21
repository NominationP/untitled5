import javafx.util.Pair;
import org.omg.CORBA.INTERNAL;

import java.awt.*;
import java.util.*;
import java.util.regex.Matcher;

/**
 * Created by zhangzexiang on 2016/8/8.
 */
public class LibensTaxi_10010 {

    static int w,l,n,vmax,vp;
    static double a;
    static Point begin = new Point(0,0);
    static Point end = new Point(0,0);

    static ArrayList<Point> policeman = new ArrayList<>();
    static ArrayList<Point> countPoint = new ArrayList<>();  //记录经过的点  一条路径
    static ArrayList<ArrayList<Point>> countPath = new ArrayList<>();  //记录所有对应路径
    static ArrayList<Point> direct = new ArrayList<>();   // 方向向量，(x,y)周围的四个方向

    static Map<Integer,ArrayList<ArrayList<Point>>> mapAll= new HashMap<>();

    static int maxn=100;
    static boolean vst[][] = new boolean[maxn][maxn]; // 访问标记
    static int map[][] = new int[w][l]; // 坐标范围
    static int count; //记录步数

    static  boolean hasPolice = false;
    static  ArrayList<Integer> countPolice = new ArrayList<>();
    static  ArrayList<Point> countPolicePoint = new ArrayList<>();

    public static double getTime (double v0, double vt, double ac){ //初速度和末速度，加速度，求时间
        return (vt-v0)/ac;
    }

    public static double getDis (double v0, double vt, double ac){ //求开始速度和结束速度的路程
        return (vt*vt - v0*v0)/(2*a);
    }

    public static double getVt (double v0, double a, double x){ //根据初速度，加速度，和路程求初速度
        return Math.sqrt(2*a*x + v0*v0);
    }


    public static Object getMinKey(Map<Integer,ArrayList<ArrayList<Point>>> map) {
        if (map == null) return null;
        Set<Integer> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return obj[0];
    }


    public static boolean CheckEdge(int x,int y) // 边界条件和约束条件的判断
    {
        if(x<0 || y<0){
            return false;
        }
        if(!vst[x][y] && x<=w && x>=0 && y<=l && y>=0) // 满足条件
        return true;
    else // 与约束条件冲突
        return false;
    }

    public static void dfs(int x,int y) {
        Point pass = new Point(x, y);
        vst[x][y] = true; // 标记该节点被访问过
        countPoint.add(new Point(x,y)); //增加路径经过的点
        count++;
        if (pass.equals(end)) // 出现目标态G
        {
            vst[x][y]=false;

            countPath.add(new ArrayList<Point>(countPoint));


            if(mapAll.containsKey(count)){
                ArrayList<ArrayList<Point>> newPath = new ArrayList<>();
              newPath =  mapAll.get(count);
                newPath.add(new ArrayList<Point>(countPoint));
            }else {
                mapAll.put(count,new ArrayList<ArrayList<Point>>(countPath));
            }

            countPoint.remove(countPoint.size()-1);
            countPath.clear();
            count --;

           // mapAll.put(1,countPath);
            return;
        }


        for (int j = 0; j < 4; j++) {
            Point tempDirector = direct.get(j);
            Point newPass = new Point(pass.x + tempDirector.x, pass.y + tempDirector.y);// 按照规则生成下一个节点
            if (CheckEdge(newPass.x, newPass.y)) {
                dfs(newPass.x, newPass.y);
            }
        }
        vst[x][y] =false;
        countPoint.remove(countPoint.size()-1);
        count--;
        return; // 没有下层搜索节点，回溯
    }





    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);


        //输入信息
         w = reader.nextInt();
         l = reader.nextInt();
         n = reader.nextInt();
         a = reader.nextDouble();
         vmax = reader.nextInt();
         vp = reader.nextInt();

         begin = new Point(reader.nextInt(), reader.nextInt());
         end = new Point(reader.nextInt(), reader.nextInt());


        for (int i = 0; i < n; i++){
            Point p = new Point(reader.nextInt(), reader.nextInt());
            policeman.add(p);
        }

        //添加方向向量
        Point left = new Point(-1,0);
        Point right = new Point(1,0);
        Point up = new Point(0,1);
        Point down = new Point(0,-1);
        direct.add(left);
        direct.add(right);
        direct.add(up);
        direct.add(down);

        dfs(begin.x,begin.y);

        for (Map.Entry<Integer,ArrayList<ArrayList<Point>>> entry : mapAll.entrySet()) {

          //  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

        }

        int minmap = Integer.parseInt( getMinKey(mapAll).toString());

        ArrayList<ArrayList<Point>>   minPath = mapAll.get(minmap);

        int countP = -1;
        for(ArrayList<Point> everyPath : minPath){
            countP++;
            for(Point p : everyPath){
                if(policeman.contains(p)) {
                    countPolice.add(countP);
                    countPolicePoint.add(p);
                    hasPolice = true;
                }
            }
        }

        int distance = minmap-1;

        if(!hasPolice){
            System.out.println(Math.sqrt(2*distance/a));
        }

        if(hasPolice) {
            if (countP == 0) { //最短距离只有一条路径，且这条路径上有警察
                ArrayList<Point> ppp = mapAll.get(minmap).get(countP);
                double no = ppp.indexOf(countPolicePoint.get(0));
                double fr = no - 0;
                double be = ppp.size() - 1 - fr;

                double dd = ((vp * vp) / (4 * a));
                double ddd = fr/2;

                double vt = getVt(0,a,fr);
                if(vt <= vp){
                    double t11 = vp/a;

                    double x111 = (vmax*vmax - vp*vp)/2*a;

                    if(x111>= be){

                        double temV = Math.sqrt(2*a*be + vp*vp);
                        double t22 = (temV-vp)/a;
                    }else{

                        double x11 = (vmax*vmax - vp*vp)/2*a;
                        double x22 = be-x11;
                        double t33 = (vmax-vp)/a;
                       // double t44 =
                    }
                }



                double x1 = ((vp * vp) / (4 * a)) + (fr / 2);


                double maxV = Math.sqrt(2 * a * x1);
                double t1 = maxV / a;
                double t2 = (maxV - vp) / a;

                double x3 = (vmax * vmax - vp * vp) / (2 * a);
                if (x3 > be) {
                    double vEnd = Math.sqrt((2 * a * x3) + (maxV * maxV));
                    double t3 = (vEnd - vp) / a;

                    System.out.println(t1 + t2 + t3);
                }

            }

        }





    }
}
