import java.util.Scanner;
public class CharIndex_1050 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        int m = reader.nextInt();
        String s = reader.next();
        char chars_s[] = new char[n + 2];
        int num = 0;
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < n; i++)
            chars_s[i + 1] = s.charAt(i);
        for (int i = 1; i < n; i++) {
            if (chars_s[i] == '.' && chars_s[i + 1] == '.')
                num++;
        }
        while (m != 0) {
            int x = reader.nextInt();
            char c = reader.next().charAt(0);
            if (chars_s[x - 1] == '.' && chars_s[x] == '.')
                num--;
            if (chars_s[x] == '.' && chars_s[x + 1] == '.')
                num--;
            chars_s[x] = c;
            if (chars_s[x - 1] == '.' && chars_s[x] == '.')
                num++;
            if (chars_s[x] == '.' && chars_s[x + 1] == '.')
                num++;
            ans.append(num).append("\n");
            m--;
        }
        System.out.print(ans);
    }
}