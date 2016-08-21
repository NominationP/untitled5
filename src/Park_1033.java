/**
 * Created by zhangzexiang on 2016/8/18.
 */
import java.util.Scanner;

public class Park_1033 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int L = input.nextInt();
        int B = input.nextInt();
        int F = input.nextInt();
        int numCar = input.nextInt();


        int[] Parking= new int[L+F+B];

        for(int i = 1; i <= numCar; ++i) {
            int Opreate =input.nextInt() ;
            int carLength=input.nextInt();
            if(Opreate== 1)
            {
                int num = 0 , j;
                for(j = 0; j < L+B+F && num<B+F+carLength; ++j)
                {
                    if(Parking[j]==0){
                        num++;
                    }
                    else{
                        num = 0;
                    }
                }
                if(num < B+F+carLength){
                    System.out.println("-1");
                } else {
                    j -= F+1;
                    for(int k = 0; k < carLength; ++k)
                        Parking[j-k] = i;
                    System.out.println(j-carLength-B+1);
                }
            }else{
                for(int j = 0; j < L+B+F; ++j) {
                    if(Parking[j]==carLength){
                        Parking[j] = 0;
                    }
                }
            }
        }
    }
}

