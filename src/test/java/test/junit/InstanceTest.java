package test.junit;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Created by Pierreluo on 2017/9/12.
 */
public class InstanceTest extends TestCase {
    private String a = "asd";

    protected void setUp() {

    }

    public void test0() {
        System.out.println(a + "0");
    }

    public void test1() {
        System.out.println(a + "1");
    }

}
