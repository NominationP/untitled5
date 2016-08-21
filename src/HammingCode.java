import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zhangzexiang on 2016/8/16.
 */
public class HammingCode {




    static ArrayList<Integer> v = new ArrayList<>();

    static String ss;

    static int count = 0;

    static  String string1 =  new String("");

    static  int x;

    static  int N, B, D;


    public static  boolean judge(int s)
    {




        for(int i=0; i<v.size(); i++)
        {


            x = v.get(i) ^ s;

            count = 0;

            string1 = new String();



            string1 = Integer.toBinaryString(x);


            for(int j = 0; j < string1.length(); j++)
            {
                if(String.valueOf(string1.charAt(j)).equals("1"))
                {

                    count++;

                }
            }
            if(count < D)
            {
                return false;
            }

        }

        return true;

    }


   public static  void   main(String[] args)
    {


        Scanner reader = new Scanner(System.in);

        v.add(0);


        N= reader.nextInt();
        B = reader.nextInt();
        D = reader.nextByte();




        String string = new String();





        for(int i = 1; v.size()<N; i++)
        {


            if(judge(i))
            {
                v.add(i);
            }



        }

        for(int i=0; i<v.size(); i++)
        {
           System.out.print(v.get(i)+" ");
        }





    }

}
