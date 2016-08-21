import java.util.Scanner;

/**
 * Created by zhangzexiang on 2016/8/9.
 */
public class BFS_example_FamerCow {


    static int MAX = 200001;
    static int queue[] = new int[MAX];
    static int[] vis = new int[MAX];

    ///visit函数记录是否被访问过，并且
///表示需要第几步走到这个位置
    static boolean check(int x) {
        ///检查该点是否合法，合法的条件是在数轴上，
        ///且这个点没有被访问过
        if (x >= 0 && x < MAX && vis[x] == -1) return true;
        else return false;
    }

    static int BFS(int start, int end) {
        //memset(vis,-1,sizeof(vis));
        vis = new int[MAX];
        int front = 0, back = 0, now;///定义队首和队尾
        queue[back++] = start;///在队尾插入一个元素
        vis[start] = 0;///该点被访问，并且是走到这一步需要0步

        while (front <= back) {
            now = queue[front++];///每次取队首元素
            if (now == end) return vis[end];///如果队首元素是要找的元素，返回结果
            if (check(now - 1)) {
                queue[back++] = now - 1;///合法的点被插入到队尾
                vis[now - 1] = vis[now] + 1;///是由上一步走过来的，所以vis要+1
            }
            if (check(now + 1)) {
                queue[back++] = now + 1;
                vis[now + 1] = vis[now] + 1;
            }
            if (check(now * 2)) {
                queue[back++] = now * 2;
                vis[now * 2] = vis[now] + 1;
            }
        }
        return 0;
    }


    public static void main(String [] args)
    {
        int start,end;

        Scanner reader = new Scanner(System.in);

        start = reader.nextInt();
        end = reader.nextInt();

        System.out.println(BFS(start,end));
    }
}
