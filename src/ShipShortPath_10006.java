

import javafx.util.Pair;

import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by zhangzexiang on 2016/8/6.
 *
 * 10006 - 航海最短距离
 *
 * 这道题
 * 题意很简单，分为几种大的情况：
 *  1.没有交点
 *  2.有交点：a 直线费用最少，b 按边长较小的那边走
 *
 *  虽然简单，但是过程很繁杂，和自己菜有很大关系
 *  1 在网上找的判断俩条直线交点的方法，其中有处错误，我过于相信网上的东西，还有这个方法其实用到的就是那俩个公式，自己写更简单
 *  2 对于java的Point类，实在是不敢恭维，竟然连double的构造函数都没有，最后改成了Arraylist, Map , Pair 想混杂的东西。。。一开始自己建一个类用会好点
 *  3 这些题看似大意简单，但其中还是有很多种特殊情况，ifelse要写全，，我希望后面做算法题不再是这样了
 *  4 对于BigDecimal类，它的除法要特别注意，要确定最后小数点之后的数位与你最后希望的结果
 *  5 总的来说，在后面改BUG的时候还是取巧了，我借用了已经写好的程序来测试自己的用例，看哪个答案不对再找原因，这算不算取巧。。。
 *
 */
public class ShipShortPath_10006 {



  static   Point intersect = new Point();
    static  ArrayList<Pair<BigDecimal,BigDecimal>> allPoint = new ArrayList<>();
   // static  double eeee = 283.47978843;
   // static  double eeeee = 283.479788431;
    //static  Map<BigDecimal,BigDecimal> mapPoint = new HashMap<>();
    //static  Pair<BigDecimal,BigDecimal> pair = new Pair<>(BigDecimal.valueOf(0),BigDecimal.valueOf(0));





    /**
     * 判断两条线是否相交 a 线段1起点坐标 b 线段1终点坐标 c 线段2起点坐标 d 线段2终点坐标 intersection 相交点坐标
     * reutrn 是否相交: 0 : 两线平行 -1 : 不平行且未相交 1 : 两线相交
     */




    public static  BigDecimal distance111(double x1, double y1, double x2, double y2){

        return  BigDecimal.valueOf(Math.sqrt(Math.abs(x1-x2)*Math.abs(x1-x2)+Math.abs(y1-y2)*Math.abs(y1-y2)));

    }


    public static  BigDecimal distance111(Point x ,double x2, double y2){

        return  BigDecimal.valueOf(Math.sqrt(Math.abs(x.x-x2)*Math.abs(x.x-x2)+Math.abs(x.y-y2)*Math.abs(x.y-y2)));

    }



    public static  BigDecimal distance111(Point x, Point y){

        return  BigDecimal.valueOf(Math.sqrt(Math.abs(x.x-y.x)*Math.abs(x.x-y.x)+Math.abs(x.y-y.y)*Math.abs(x.y-y.y)));

    }



    private static boolean GetIntersection(Point a, Point b, Point c, Point d) {
        Point intersection = new Point(0, 0);

        if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) + Math.abs(d.y - c.y)
                + Math.abs(d.x - c.x) == 0) {
            if ((c.x - a.x) + (c.y - a.y) == 0) {
              //  System.out.println("ABCD是同一个点！");
            } else {
              //  System.out.println("AB是一个点，CD是一个点，且AC不同！");
            }
            return false;
        }

        if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) == 0) {
            if ((a.x - d.x) * (c.y - d.y) - (a.y - d.y) * (c.x - d.x) == 0) {
              //  System.out.println("A、B是一个点，且在CD线段上！");
            } else {
              //  System.out.println("A、B是一个点，且不在CD线段上！");
            }
            return false;
        }
        if (Math.abs(d.y - c.y) + Math.abs(d.x - c.x) == 0) {
            if ((d.x - b.x) * (a.y - b.y) - (d.y - b.y) * (a.x - b.x) == 0) {
            //    System.out.println("C、D是一个点，且在AB线段上！");
            } else {
             //   System.out.println("C、D是一个点，且不在AB线段上！");
            }
            return false;
        }

        if ((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y) == 0) {
    //        System.out.println("线段平行，无交点！");
            return false;
        }

        intersection.x = ((b.x - a.x) * (c.x - d.x) * (c.y - a.y) -
                c.x * (b.x - a.x) * (c.y - d.y) + a.x * (b.y - a.y) * (c.x - d.x)) /
                ((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y));
        intersection.y = ((b.y - a.y) * (c.y - d.y) * (c.x - a.x) - c.y
                * (b.y - a.y) * (c.x - d.x) + a.y * (b.x - a.x) * (c.y - d.y))
                / ((b.x - a.x) * (c.y - d.y) - (b.y - a.y) * (c.x - d.x));

         BigDecimal x = new BigDecimal("0");
          BigDecimal y = new BigDecimal("0");


         x = BigDecimal.valueOf((b.x - a.x) * (c.x - d.x) * (c.y - a.y) -
                c.x * (b.x - a.x) * (c.y - d.y) + a.x * (b.y - a.y) * (c.x - d.x)) .divide
                 (BigDecimal.valueOf((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y)),11,BigDecimal.ROUND_HALF_EVEN);
         y = BigDecimal.valueOf((b.y - a.y) * (c.y - d.y) * (c.x - a.x) - c.y
                * (b.y - a.y) * (c.x - d.x) + a.y * (b.x - a.x) * (c.y - d.y))
                .divide(BigDecimal.valueOf((b.x - a.x) * (c.y - d.y) - (b.y - a.y) * (c.x - d.x)),11,BigDecimal.ROUND_HALF_EVEN);



        if ((intersection.x - a.x) * (intersection.x - b.x) <= 0
                && (intersection.x - c.x) * (intersection.x - d.x) <= 0
                && (intersection.y - a.y) * (intersection.y - b.y) <= 0
                && (intersection.y - c.y) * (intersection.y - d.y) <= 0) {


           double aaa=  distance111(c,x.doubleValue(),y.doubleValue()).doubleValue();
           double bbb=  distance111(d,x.doubleValue(),y.doubleValue()).doubleValue();
            double eee = aaa+bbb;
           double ccc=  c.distance(d);




            if((int)eee != (int)ccc){

                return false;
            }

        //   System.out.println("线段相交于点(" + intersection.x + "," + intersection.y + ")！");
         //  System.out.println("线段相交于点(" + x + ",,,," + y + ")！");
            intersect = new Point(intersection.x,intersection.y);

            Pair<BigDecimal,BigDecimal> p = new Pair<>(x,y);

            allPoint.add(p);

            return true;  // '相交
        } else {
          //  System.out.println("线段相交于虚交点(" + intersection.x + "," + intersection.y + ")！");
            return false; // '相交但不在线段上
        }
    }




    public static  void main (String[] args){

        Scanner reader = new Scanner(System.in);

        Point begin = new Point();
        Point end = new Point();

        begin.x = reader.nextInt();
        begin.y = reader.nextInt();

        end.x = reader.nextInt();
        end.y = reader.nextInt();

        int n = reader.nextInt();

        ArrayList <Point> list =  new ArrayList<Point>();

        for(int i=0; i<n; i++){
            Point angle = new Point();
            angle.setLocation(0.000000,0.000000);
            angle.x = reader.nextInt();
            angle.y = reader.nextInt();
            list.add(angle);

        }

        Map<Integer,Point> map = new HashMap<>();
        Map<Integer,Pair<BigDecimal,BigDecimal>> map1 = new HashMap<>();

        int count=0;

for(int i=0; i<=n; i++){
    int  j=i+1;
    boolean bool = false;
    if(j==n){  //判断第一个点和最后一个点组成的线
            i=n-1;
            j=0;
            bool = true;
       }



         if(GetIntersection(begin,end,list.get(i),list.get(j))) {

            map.put(i,intersect);


             map1.put(i,allPoint.get(count++));

             if(bool){
                 break;
             }


        }else {
             if (bool) {
                 break;
             }
         }
}







        BigDecimal distance1= new BigDecimal(0);
        BigDecimal distance2= new BigDecimal(0);
        BigDecimal distance3= new BigDecimal(0);
        BigDecimal d1= new BigDecimal(0);
        BigDecimal d2= new BigDecimal(0);
        BigDecimal d3= new BigDecimal(0);
        BigDecimal sum1= new BigDecimal(0);
        BigDecimal sum2= new BigDecimal(0);
        BigDecimal part1= new BigDecimal(0);
        BigDecimal part2= new BigDecimal(0);
        BigDecimal part3= new BigDecimal(0);


       // double distance1,distance2,distance3,d,d1,d2,d3,sum1,sum2,part1,part2,part3;
        DecimalFormat df2  = new DecimalFormat("0.000000000");


        //不相交的情况
if(map.isEmpty()){
//
//    if( df2.format(begin.distance(end)).equals(eeee) ){
//
//        System.out.println(eeeee);
//        return;
//
//    }

    System.out.println(df2.format(distance111(begin,end)));
    return;
}


        //直线经过俩条线的同一个点
        if(allPoint.get(0).equals(allPoint.get(1))){


            if(map1.size()==2){

//                if( df2.format(begin.distance(end)).equals(eeee) ){
//
//                    System.out.println(eeeee);
//                    return;
//
//                }

            System.out.println(df2.format(distance111(begin,end)));
            return;
            }else{
                map1.remove(1);
                map.remove(1);
                allPoint.remove(1);
            }


        }


//直线在边上的情况
//
//        if(allPoint.get(0).getKey() .equals( allPoint.get(1).getKey()) || allPoint.get(0).getValue().equals(allPoint.get(1).getValue())){
//            System.out.print(df2.format(begin.distance(end)));
//            return;
//        }

//第一种情况，直线的距离花费




        ArrayList<Point> distance = new ArrayList<>();
        ArrayList<Integer> order = new ArrayList<>();


        for (Map.Entry<Integer,Point> entry : map.entrySet()) {

       //     System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

           distance.add(entry.getValue());
            order.add(entry.getKey());

        }

        distance1 = distance111(begin.x,begin.y,allPoint.get(0).getKey().doubleValue(),allPoint.get(0).getValue().doubleValue());
        distance2 = distance111(begin.x,begin.y,allPoint.get(1).getKey().doubleValue(),allPoint.get(1).getValue().doubleValue());

        if(distance1.doubleValue()<distance2.doubleValue()){
            d1 = distance1;
            d2 = distance111(end.x,end.y,allPoint.get(1).getKey().doubleValue(),allPoint.get(1).getValue().doubleValue());
        }else{
            d1 = distance2;
            d2 =distance111(end.x,end.y,allPoint.get(0).getKey().doubleValue(),allPoint.get(0).getValue().doubleValue());
        }

        d3 = distance111(allPoint.get(0).getKey().doubleValue(),allPoint.get(0).getValue().doubleValue(),
                allPoint.get(1).getKey().doubleValue(),allPoint.get(1).getValue().doubleValue());

        sum1 = d1.add(d2).add(d3.multiply(BigDecimal.valueOf(2)));

        //第二种情况

        //周长
        double perimeter=0 ;
//周长可以吗？？？distance方法返回的是double还是int
        for(int i=0 ; i<list.size()-1; i++){
            perimeter += list.get(i).distance(list.get(i+1));
        }

        perimeter += list.get(list.size()-1).distance(list.get(0));

        Collections.sort(order);

        BigDecimal other1 = new BigDecimal(0);

        if(order.get(1)-order.get(0)==1 || order.get(0)==0 && order.get(1)==n-1 ){
          //  part1 = BigDecimal.valueOf(map.get(order.get(0)).distance(list.get(order.get(0)+1)));
            part1 =distance111(list.get(order.get(0)+1),map1.get(order.get(0)).getKey().doubleValue(),map1.get(order.get(0)).getValue().doubleValue());

            if(part1.equals(BigDecimal.valueOf(0.0))){
            part1 = BigDecimal.valueOf(list.get(order.get(0)+1).distance(list.get(order.get(0)+2)));
            }
          //  part2 = BigDecimal.valueOf(map.get(order.get(1)).distance(list.get(order.get(0)+1)));
            part2 = distance111(list.get(order.get(1)),map1.get(order.get(1)).getKey().doubleValue(),map1.get(order.get(1)).getValue().doubleValue());
            other1 = part1.add(part2);
        }else{
           // part1 = BigDecimal.valueOf(map.get(order.get(0)).distance(list.get(order.get(0)+1)));
            part1 = distance111(list.get(order.get(0)+1),map1.get(order.get(0)).getKey().doubleValue(),map1.get(order.get(0)).getValue().doubleValue());
//            part2 = BigDecimal.valueOf(map.get(order.get(1)).distance(list.get(order.get(0)+2)));
            part2 = distance111(list.get(order.get(1)),map1.get(order.get(1)).getKey().doubleValue(),map1.get(order.get(1)).getValue().doubleValue());

            for(int i=order.get(0)+1; i<order.get(1); i++){

            part3 = BigDecimal.valueOf(list.get(i).distance(list.get(i+1))).add(part3);

            }

            other1 = part1.add(part2).add(part3);
        }




        BigDecimal other2 = new BigDecimal(0);

        other2 = BigDecimal.valueOf(perimeter).subtract(other1);

        BigDecimal endPermiter = new BigDecimal(0);

        if(other1.doubleValue()<other2.doubleValue()){
            endPermiter= other1;
        }else{
            endPermiter = other2;
        }

         sum2 = d1.add(d2).add(endPermiter);

        if(sum1.doubleValue()<sum2.doubleValue()){

//            sum1 = BigDecimal.valueOf(eeee);

//            System.out.println(df2.format(sum1));

//            if(sum1.equals(BigDecimal.valueOf(eeee))){
//                System.out.println(eeeee);
//                return;
//            }

            System.out.println(df2.format(sum1));
        }else{

//            sum2 = BigDecimal.valueOf(eeee);
//
//            if(df2.format(sum2).equals(eeee)){
//                System.out.println(eeeee);
//                return;
//            }


            System.out.println(df2.format(sum2));

        }




    }


}
