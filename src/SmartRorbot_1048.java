import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zhangzexiang on 2016/8/9.
 */
public class SmartRorbot_1048 {

    static int maxn=100;
    static boolean vst[][]  = new boolean[maxn][maxn]; // 访问标记
    static String point[][] = new String[maxn][maxn ]; // 坐标范围 坐标中所有的点
    static ArrayList<Point> dir = new ArrayList<>(); //方向向量，(x,y)周围的四个方向

    static Point robort = new Point();
    static String robortDir = "";
    static int w=0;
    static int h=0;
    static int res = 1;
    static int i=0;
    static int isCircle = 1;

    static boolean once = true;
    static boolean end = true;
    static boolean isfirst = false;
    public static int change(String s){
        switch (s){
            case "U":
                return 0;
            case "R":
                return 1;
            case "D":
                return 2;
            case "L":
                return 3;

            default:
                return 0;

        }
    }



   public static boolean CheckEdge(Point newPoint) // 边界条件和约束条件的判断
   {
       if(newPoint.x<0 || newPoint.y<0){
           i++;
           if(i==4){
               i=0;
           }
           return false;
       }
       String ss = point[newPoint.x][newPoint.y];
       if (!vst[newPoint.x][newPoint.y] && newPoint.x < h && newPoint.y < w
               && !point[newPoint.x][newPoint.y].equals("*") ) // 满足条件
           return true;
       else // 与约束条件冲突
       {
           i++;
           if(i==4){
               i=0;
           }
           return false;
       }
   }

    public static void dfs(Point robort)
    {
        if(once){
            i = change(robortDir);
            once =false;
        }
        vst[robort.x][robort.y]=true; // 标记该节点被访问过
//        if(point[x][y]=="") // 出现目标态G
//        {
//      //  ...... // 做相应处理
//            return;
//        }
        while(true)
        {
            if(!end){
                return;
            }

//            if(i==3 && isfirst ){
//                i=0;
//            }
//            isfirst=true;

            Point newPoint = new Point(robort.x+dir.get(i).x , robort.y+dir.get(i).y);
            if(CheckEdge(newPoint)) {// 按照规则生成下一个节点
                isCircle=1;
//                if (vst[newPoint.x][newPoint.y]) {
//                    end = false;
//                    return;
//                   // System.out.println(res);
//                }
                res++;
                dfs(newPoint);
            }else {
                isCircle++;
                if(isCircle>=4){
                    end = false;
                }

            }
        }
//        return; // 没有下层搜索节点，回溯
    }

    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);

        w = reader.nextInt();
        h = reader.nextInt();

        reader.nextLine();

        ArrayList<String> allPoint = new ArrayList<>();

        ArrayList<String> slist = new ArrayList<>();

        for(int i=0; i<w; i++){
            slist.add(reader.nextLine());
    }

    int ww = w;

        for(int i=0; i<w; i++){
            String s = slist.get(--ww);
            for(int j =0; j<h; j++){

                if(String.valueOf(s.charAt(j)).equals("U") || String.valueOf(s.charAt(j)).equals("R") ||
                String.valueOf(s.charAt(j)).equals("D") ||
                String.valueOf(s.charAt(j)).equals("L")){

                    robortDir = String.valueOf(s.charAt(j));
                     robort = new Point(j,i);
                }

                point[j][i] = String.valueOf(s.charAt(j));
            }
        }

        //设定方向
        dir.add(new Point(0,1));
        dir.add(new Point(1,0));
        dir.add(new Point(0,-1));
        dir.add(new Point(-1,0));

        dfs(robort);

        System.out.println(res);


    }

}
