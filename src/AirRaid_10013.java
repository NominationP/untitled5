import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by zhangzexiang on 2016/8/13.
 */
public class AirRaid_10013 {

   static int n;           //宿舍数量
    static int m;           //多少次操作
    static    int maxn = 500010;


    static    int cur;
    static    int cur1;

    static    int dormitory[] =  new int[maxn];

    static    int countDoor;  //对应高校的宿舍数量
    static    int countDoorDup;

    static  int i=0;

// int universion[maxn];

    //vector<int> military;
//    static Map<Integer, ArrayList<Integer>> universion = new HashMap<>();
//    static    Map<Integer, ArrayList<Integer>>  military = new HashMap<>();

    static ArrayList<Integer>[] universion= new ArrayList[maxn];
    static ArrayList<Integer>[] military= new ArrayList[maxn];


    public  static void mix(int cur1, int cur2)
    {
        ArrayList<Integer> v = military[cur2];

        military[cur1].addAll(military[cur2]);

        //military[cur2].clear();

    }

    public  static   void unity(int cur1, int cur2)
    {
        ArrayList<Integer> v = universion[cur2];

        universion[cur1].addAll(universion[cur2]);

//        military.remove(cur2);

    }

    public  static   void add(int cur)
    {

        countDoor = universion[cur].size();  // how many domitary in this university

        for(int i=0; i<countDoor; i++){

            dormitory[universion[cur].get(i)] += countDoor;
        }

    }

    public  static   void zzz(int cur)  //清空的是cur序号宿舍的学生 还是cur高校所有宿舍的学生 check one!
    {


        ArrayList<Integer> raid = military[cur];


 for(int i=0; i<raid.size(); i++){

        dormitory[raid.get(i)] = 0;


        }

    }

    public  static  void query(int cur)
    {


        System.out.println(dormitory[cur]);



    }


    public  static void  main(String[] args)
    {

        Scanner reader= new Scanner(System.in);

        n = reader.nextInt();
        m = reader.nextInt();


        for(  i = 1; i <= n; i++)
        {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(i);
            ArrayList<Integer> b = new ArrayList<>();
            b.add(i);

            universion[i]=a;
            military[i]=b;

        }
    reader.nextLine();


        String c = new String("");

        while(m-->0)
        {

            c = reader.nextLine();

            switch(c.charAt(0))
            {

                case 'A':
                    cur = Integer.parseInt(String.valueOf(c.charAt(2)));
                    add(cur);
                    break;

                case 'Z':
                    cur = Integer.parseInt(String.valueOf(c.charAt(2)));
                    zzz(cur);
                    break;

                case 'Q':
                    cur = Integer.parseInt(String.valueOf(c.charAt(2)));
                    query(cur);
                    break;

                case 'U':
                    cur = Integer.parseInt(String.valueOf(c.charAt(2)));
                    cur1 = Integer.parseInt(String.valueOf(c.charAt(4)));
                    unity(cur, cur1);
                    break;

                case 'M':
                    cur = Integer.parseInt(String.valueOf(c.charAt(2)));
                    cur1 = Integer.parseInt(String.valueOf(c.charAt(4)));
                    mix(cur, cur1);
                    break;

            }

        }

    }

}
