package classload;

/**
 * Created by Pierreluo on 2017/12/14.
 */
public class ClassLoadTest {
    static int i = 1;
    static {
        test();
        System.out.println("a1:" + i);
    }
    static {
        i++;
        System.out.println("a2:" + i);
    }
    public static void test() {
        i++;
        System.out.println("test():" + i );
    }
    public ClassLoadTest() {
        i++;

        System.out.println("construct:" + i);
    }

}
