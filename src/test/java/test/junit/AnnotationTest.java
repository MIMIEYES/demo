package test.junit;

import org.junit.Test;

/**
 * Created by Pierreluo on 2017/9/12.
 */
public class AnnotationTest {
    private String asd = "asd";
    @Test
    public void aaa() {
        asd = asd + "aaa";
        System.out.println(asd);
    }

    @Test
    public void bbb() {
        asd = asd + "bbb";
        System.out.println(asd);
    }
}
