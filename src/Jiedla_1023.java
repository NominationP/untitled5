/**
 * Created by zhangzexiang on 2016/8/9.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.Size2DSyntax;

public class Jiedla_1023 {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        String s1 = reader.next();
        String s2 = reader.next();

        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();

        ArrayList<String> temp2 = new ArrayList<String>();

        ArrayList<String> spare1 = new ArrayList<String>();
        ArrayList<String> spare2 = new ArrayList<String>();

        for (int i = 0; i < s1.length(); i++) {
            list1.add(String.valueOf(s1.charAt(i)));
        }

        for (int i = 0; i < s2.length(); i++) {
            list2.add(String.valueOf(s2.charAt(i)));
        }

        if (s1.equals(s2)) {
            System.out.println("YES");
            return;
        } else {
            if (s1.length() != s2.length()) {
                System.out.println("NO");
                return;
            }

            int half = s1.length() / 2;
            for (int i = 0; i < half; i++) {
                temp.add(list1.get(i));
            }
            for (int i = half; i < s1.length(); i++) {
                temp2.add(list2.get(i));
            }
            Collections.sort(temp);
            Collections.sort(temp2);
            if (temp.equals(temp2)) {
                for (int i = half; i < s1.length(); i++) {
                    spare1.add(list1.get(i));
                }
                for (int i = 0; i < half; i++) {
                    spare2.add(list2.get(i));
                }
                Collections.sort(spare1);
                Collections.sort(spare2);
                if (spare1.equals(spare2)) {

                    System.out.println("YES");
                    return;
                } else {

                    System.out.println("NO");
                    return;
                }
            } else {
                System.out.println("NO");
                return;
            }


        }
    }
    }
