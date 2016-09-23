import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zhangzexiang on 2016/8/27.
 */


public class EndElection {


        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int N = Integer.parseInt(scanner.nextLine());
            scanner.nextLine();
            for (int i = 0; i < N; i++) //--
            {
                int n = Integer.parseInt(scanner.nextLine());
                String[] candidate = new String[n];
                for (int j = 0; j < n; j++)
                {
                    candidate[j] = scanner.nextLine();
                }
                ArrayList<int[]> voter = new ArrayList<int[]>();

                while (true)//---
                {
                    if (!scanner.hasNextInt())
                    {
                        break;
                    }
                    String ticket2 = scanner.nextLine();
                    String[] ticket1 = new String[n];
                    if (ticket2 .equals(""))
                    {
                        break;
                    }
                    ticket1 = ticket2.split(" ");
                    int[] ticket = new int[ticket1.length];
                    for (int j = 0; j < ticket1.length; j++)
                    {
                        ticket[j] = Integer.valueOf(ticket1[j]);
                    }
                    voter.add(ticket);  //all ticket
                }
                ArrayList<Integer>[] getCandidate = new ArrayList[n];
                for(int j = 0;j<n;j++)
                {
                    getCandidate[j] = new ArrayList<Integer>(); // candidate voter situation
                }
                for(int j = 0;j<voter.size();j++)
                {
                    int a = voter.get(j)[0];
                    for (int p = 0; p < n; p++)
                    {
                        if (a - 1 == p)
                        {
                            getCandidate[p].add(j);     //take ticket
                            voter.get(j)[0] = 0;        //take ticket , old palce = 0
                        }
                    }
                }
                int y = voter.size()/2;
                loop:while (true)
                {
                    int min = Integer.MAX_VALUE;
                    int max = Integer.MIN_VALUE;
                    int max1 = 0;
                    int min1 = 0;
                    for (int j = 0; j < n; j++)
                    {
                        if (getCandidate[j].size() > max)   //take max
                        {
                            max = getCandidate[j].size();
                            max1 = j;
                        }
                    }
                    for (int j = 0; j < n; j++)   // take min
                    {
                        if (getCandidate[j].size() < min && getCandidate[j].size()>0)
                        {
                            min = getCandidate[j].size();
                            min1 = j;
                        }
                    }
                    if (max > y)  //1
                    {
                        System.out.println(candidate[max1]);
                        break ;
                    } else if (max == min) //2
                    {
                        for (int j = 0; j < candidate.length; j++)
                        {
                            if (getCandidate.length != 0) {
                                System.out.println(candidate[j]);
                                break ;
                            }
                        }
                    } else { //3
                        for (int j = 0; j < getCandidate[min1].size(); j++)
                        {
                            for (int m = 1; m < n; m++)
                            {
                                int c = voter.get(getCandidate[min1].get(j))[m];
                                if (c > 0)
                                {

                                    getCandidate[c - 1].add(getCandidate[min1].get(j)); //add ticket of new
                                    voter.get(getCandidate[min1].get(j))[m] = 0;    //set old ticket = 0
                                    break;
                                }
                            }
                        }
                        getCandidate[min1].clear();
                    }
                }
                if (i + 1 < N)
                    System.out.println("");
            }
        }
    }




