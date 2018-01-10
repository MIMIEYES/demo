package classload;

/**
 * Created by Pierreluo on 2017/12/14.
 */
public class ClassLoadBTest extends ClassLoadTest{
    static int i = 1;
    static {
        System.out.println("b1:" + i);
    }
    static {
        i++;
        System.out.println("b2:" + i);
    }
    public ClassLoadBTest() {
        super();
        i++;

        System.out.println("b:construct:" + i);
    }

    public static void btest() {
        i++;
        System.out.println("btest():" + i );
    }
}
