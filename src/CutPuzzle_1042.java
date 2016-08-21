import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zhangzexiang on 2016/8/10.
 */
public class CutPuzzle_1042 {


    static ArrayList<StringBuffer> cut = new ArrayList<>();


    public static void main(String[] args){

        Scanner reader = new Scanner(System.in);

        int a = reader.nextInt();
        int b = reader.nextInt();
        String s = "";
        StringBuffer ss = new StringBuffer("");
        reader.nextLine();
        String [][] all = new String [30][30];

        //建立坐标轴
        for(int i=0; i<a; i++){
            s = reader.nextLine();
            for(int j=0; j<s.length(); j++){
                all[i][j] = String.valueOf(s.charAt(j));
            }
        }

        //开始切

        for(int i=0; i<b; i++){
            for(int j=0; j<a; j++){
                ss.append(all[i][j]);
            }
                cut.add(ss);
        }
    }
}
