/**
 * Created by zhangzexiang on 2016/8/22.
 */



        import java.awt.geom.GeneralPath;
        import java.util.Arrays;
        import java.util.Scanner;

/**
 * Created by MR.WEN on 2016/8/19.
 */
class Point implements Comparable<Point> {
    double x, y;

    public int compareTo(Point p) {
        if (this.x == p.x) {
            return  (int)(this.y -p.y);
        } else {
            return (int)(this.x - p.x);
        }
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

}

public class Circle_10018 {
    static Point[] hull1;
    static Point[] hull2;

    public static double cross(Point O, Point A, Point B) {//应该是判断夹角的函数
        return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
    }

    public static Point[] convex_hull(Point[] P) {//构建凸包的函数，注意此函数返回的是P数组 用的是单链表

        if (P.length > 1) {
            int n = P.length, k = 0;
            Point[] H = new Point[2 * n];
            Arrays.sort(P);
            // Build lower hull
            for (int i = 0; i < n; ++i) {
                while (k >= 2 && cross(H[k - 2], H[k - 1], P[i]) <= 0)
                    k--;
                H[k++] = P[i];
            }

            // Build upper hull
            for (int i = n - 2, t = k + 1; i >= 0; i--) {
                while (k >= t && cross(H[k - 2], H[k - 1], P[i]) <= 0)
                    k--;
                H[k++] = P[i];
            }
            if (k > 1) {
                H = Arrays.copyOfRange(H, 0, k - 1); // remove non-hull vertices
                // after k; remove k - 1
                // which is a duplicate
            }
            return H;
        } else if (P.length <= 1) {
            return P;
        } else {
            return null;
        }

    }

    static double r;//定义半径
    static double r1;
    static Point d = new Point();//定义点
    static Point d1 = new Point();
    static Point[] a = new Point[10000];//定义点集
    static Point[] b = new Point[10000];

    static double Dis(Point p1, Point p2) {//两点的距离
        return  Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    static double xmult(Point p1, Point p2, Point p0) {
        return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
    }

    static void MiniDisWith2pointss(Point p, Point q, int n) {//找寻两点的最短距离从而找出圆的半径
        d.x = (p.x + q.x) / 2;
        d.y = (p.y + q.y) / 2;
        r = Dis(p, q) / 2;
        int i;
        double c1, c2;
        double t1,t2,t3;
        for (i = 0; i < n; i++) {
            if (Dis(a[i], d) <= r)
                continue;
            if (xmult(p, q, a[i]) != 0) {
                c1 = (p.x * p.x + p.y * p.y - q.x * q.x - q.y * q.y) / 2;
                c2 = (p.x * p.x + p.y * p.y - a[i].x * a[i].x - a[i].y * a[i].y) / 2;
                d.x = (c1 * (p.y - a[i].y) - c2 * (p.y - q.y)) / ((p.x - q.x) * (p.y - a[i].y) - (p.x - a[i].x) * (p.y - q.y));
                d.y = (c1 * (p.x - a[i].x) - c2 * (p.x - q.x)) / ((p.y - q.y) * (p.x - a[i].x) - (p.y - a[i].y) * (p.x - q.x));
                r = Dis(d, a[i]);
            } else {
                t1 = Dis(p, q);
                t2 = Dis(p, a[i]);
                t3 = Dis(q, a[i]);
                if (t1 >= t2 && t1 >= t3) {
                    d.x = (p.x + q.x) / 2;
                    d.y = (p.y + q.y) / 2;
                    r = Dis(p, q) / 2;
                } else if (t2 >= t1 && t2 >= t3) {
                    d.x = (a[i].x + q.x) / 2;
                    d.y = (a[i].y + q.y) / 2;
                    r = Dis(a[i], q) / 2;
                } else {
                    d.x = (a[i].x + p.x) / 2;
                    d.y = (a[i].y + p.y) / 2;
                    r = Dis(a[i], p) / 2;
                }
            }
        }
    }

    static void MiniDisWith2pointss1(Point p, Point q, int n) {//同上
        d1.x = (p.x + q.x) / 2;
        d1.y = (p.y + q.y) / 2;
        r1 = Dis(p, q) / 2;
        int i;
        double c3, c4 ;
        double t4, t5, t6;
        for (i = 0; i < n; i++) {
            if (Dis(b[i], d1) <= r1)
                continue;
            if (xmult(p, q, b[i]) != 0) {
                c3 = (p.x * p.x + p.y * p.y - q.x * q.x - q.y * q.y) / 2;
                c4 = (p.x * p.x + p.y * p.y - b[i].x * b[i].x - b[i].y * b[i].y) / 2;
                d1.x = (c3 * (p.y - b[i].y) - c4 * (p.y - q.y)) / ((p.x - q.x) * (p.y - b[i].y) - (p.x - b[i].x) * (p.y - q.y));
                d1.y = (c3 * (p.x - b[i].x) - c4 * (p.x - q.x)) / ((p.y - q.y) * (p.x - b[i].x) - (p.y - b[i].y) * (p.x - q.x));
                r1 = Dis(d1, b[i]);
            } else {
                t4 = Dis(p, q);
                t5 = Dis(p, b[i]);
                t6 = Dis(q, b[i]);
                if (t4 >= t5 && t4 >= t6) {
                    d1.x = (p.x + q.x) / 2;
                    d1.y = (p.y + q.y) / 2;
                    r1 = Dis(p, q) / 2;
                } else if (t5 >= t4 && t5 >= t6) {
                    d1.x = (b[i].x + q.x) / 2;
                    d1.y = (b[i].y + q.y) / 2;
                    r1 = Dis(b[i], q) / 2;
                } else {
                    d1.x = (b[i].x + p.x) / 2;
                    d1.y = (b[i].y + p.y) / 2;
                    r1 = Dis(b[i], p) / 2;
                }
            }
        }
    }

    static void MiniDisWithpointss(Point pi, int n) {
        d.x = (pi.x + a[0].x) / 2;
        d.y = (pi.y + a[0].y) / 2;
        r = Dis(a[0], pi) / 2;
        int j;
        for (j = 2; j < n; j++) {
            if (Dis(a[j], d) <= r)
                continue;
            MiniDisWith2pointss(pi, a[j], j - 1);
        }
    }

    static void MiniDisWithpointss1(Point pi, int n) {
        d1.x = (pi.x + b[0].x) / 2;
        d1.y = (pi.y + b[0].y) / 2;
        r1 = Dis(b[0], pi) / 2;
        int j;
        for (j = 2; j < n; j++) {
            if (Dis(b[j], d1) <= r1)
                continue;
            MiniDisWith2pointss1(pi, b[j], j - 1);
        }

    }

    public static boolean checkWithJdkGeneralPath(Point point, Point[] polygon) {//判断点是否在凸包构成的多边形内
        GeneralPath s = new GeneralPath();
        Point first = polygon[0];
        s.moveTo(first.x, first.y);
        for (Point d : polygon) {
            s.lineTo(d.x, d.y);
        }
        s.lineTo(first.x, first.y);
        s.closePath();
        return s.contains(point.x, point.y);//返回true或者false
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        Point[] p1 = new Point[n];
        Point[] p2 = new Point[m];
        for (int i = 0; i < n; i++) {
            a[i] = new Point();
            p1[i] = new Point();
            a[i].x = s.nextInt();
            a[i].y = s.nextInt();
            p1[i] = a[i];

        }
        for (int i = 0; i < m; i++) {
            b[i] = new Point();
            p2[i] = new Point();
            b[i].x = s.nextInt();
            b[i].y = s.nextInt();
            p2[i] = b[i];
        }
        if (n == 1 && m == 1) {//两族人分别只有一个点时
            System.out.println("YES");
        } else if (n == 1 && m > 1) {//其中一族人有一个以上的时候
            System.out.println("YES");
        } else if (n > 1 && m == 1) {
            System.out.println("YES");
        } else if (n == 2 && m == 2) {//两族人都只有两个点时
            d.x = (a[0].x + a[1].x) / 2;
            d.y = (a[0].y + a[1].y) / 2;
            r = Dis(a[0], a[1]) / 2;//第一族人的最小外接圆
            d1.x = (b[0].x + b[1].x) / 2;
            d1.y = (b[0].y + b[1].y) / 2;
            r1 = Dis(b[0], b[1]) / 2;//第二族人的最小外接圆
            if (Dis(b[0], d) > r && Dis(b[1], d) > r) {//若某族人的点全部在另外一族人的最小外接圆之外时 就是YES否则是NO
                System.out.println("YES");
            } else if (Dis(a[0], d1) > r1 && Dis(a[1], d1) > r1) {
                System.out.println("YES");
            } else System.out.println("NO");
        } else if (n == 2 && m > 2) {//当一族人只有一个点另外一族人有多个点时
            d.x = (a[0].x + a[1].x) / 2;//两个点的圆心的x坐标
            d.y = (a[0].y + a[1].y) / 2;//两个点的圆心的y坐标
            r = Dis(a[0], a[1]) / 2;//两个点的圆的半径
            d1.x = (b[0].x + b[1].x) / 2;//多个点的初始圆心x坐标
            d1.y = (b[0].y + b[1].y) / 2;//多个点的初始圆心x坐标
            r1 = Dis(b[0], b[1]) / 2;//初始半径
            for (int i = 2; i < m; i++) {
                if (Dis(d1, b[i]) <= r1)
                    continue;
                MiniDisWithpointss1(b[i], i);//多点最终的半径和圆心
            }
            int k = 0, l = 0;
            for (int i = 0; i < m; i++) {
                if (Dis(b[i], d) > r) {
                    k++;//判断某族人的点是否全部在另外的一族人的最小外接圆的外部
                } else {
                    break;
                }
            }
            for (int i = 0; i < n; i++) {
                if (Dis(a[i], d1) > r1) {
                    l++;
                } else {
                    break;
                }
            }
            if (k == m || l == n) {//若满足一族人的点全部在另外一族人的最小外接圆的外部就可以了输出YES否则输出NO
                System.out.println("YES");
            } else
                System.out.println("NO");
        } else if (n > 2 && m == 2) {
            d.x = (a[0].x + a[1].x) / 2;//两个点的圆心的x坐标
            d.y = (a[0].y + a[1].y) / 2;//两个点的圆心的y坐标
            r = Dis(a[0], a[1]) / 2;//两个点的圆的半径
            d1.x = (b[0].x + b[1].x) / 2;//多个点的初始圆心x坐标
            d1.y = (b[0].y + b[1].y) / 2;//多个点的初始圆心x坐标
            r1 = Dis(b[0], b[1]) / 2;//初始半径
            for (int i = 2; i < n; i++) {
                if (Dis(d, a[i]) <= r)
                    continue;
                MiniDisWithpointss(a[i], i);//多点最终的半径和圆心
            }
            int k = 0, l = 0;
            for (int i = 0; i < m; i++) {//计算点与圆心的距离
                if (Dis(b[i], d) > r) {
                    k++;
                } else {
                    break;
                }
            }
            for (int i = 0; i < n; i++) {
                if (Dis(a[i], d1) > r1) {
                    l++;
                } else {
                    break;
                }
            }
            if (k == m || l == n) {
                System.out.println("YES");
            } else

                System.out.println("NO");
        } else {//当两族人都有多个点的时候
            hull1 = convex_hull(p1).clone();
            hull2 = convex_hull(p2).clone();//首先我们找到两族人点的凸包
            int w = 0, z = 0;
            for (int i = 0; i < hull1.length; i++) {//判断两个凸包的位置关系
                if (!checkWithJdkGeneralPath(hull1[i], hull2)) {
                    w++;
                }
            }
            for (int i = 0; i < hull2.length; i++) {
                if (!checkWithJdkGeneralPath(hull2[i], hull1)) {
                    z++;
                }
            }
            if (w == hull1.length && z == hull2.length) {//若两个凸包相离的话就输出YES
                System.out.println("YES");
            } else {//否则比较两个族的最小外接圆其他一族的点的位置关系
                d.x = (a[0].x + a[1].x) / 2;
                d.y = (a[0].y + a[1].y) / 2;
                r = Dis(a[0], a[1]) / 2;
                d1.x = (b[0].x + b[1].x) / 2;
                d1.y = (b[0].y + b[1].y) / 2;
                r1 = Dis(b[0], b[1]) / 2;
                for (int i = 2; i < n; i++) {
                    if (Dis(d, a[i]) <= r)
                        continue;
                    MiniDisWithpointss(a[i], i);
                }
                for (int i = 2; i < m; i++) {
                    if (Dis(d1, b[i]) <= r1)
                        continue;
                    MiniDisWithpointss1(b[i], i);
                }
                int k = 0, l = 0;
                for (int i = 0; i < m; i++) {
                    if (Dis(b[i], d) > r) {
                        k++;
                    } else {
                        break;
                    }
                }
                for (int i = 0; i < n; i++) {
                    if (Dis(a[i], d1) > r1) {
                        l++;
                    } else {
                        break;
                    }
                }
                if (k == m || l == n) {//如果一族人的点全部在另外一族人的最小外接圆的外面就输出YES否则输出NO
                    System.out.println("YES");
                } else
                    System.out.println("NO");
            }
        }
    }

}
