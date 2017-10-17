package test.system.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Pierreluo on 2017/8/9.
 */
public class SystemConfigTest {


    @Before
    public void showAllSystemConfig() {
        Properties config = System.getProperties();
        Enumeration en = config.propertyNames();
        String key, value;
        while(en.hasMoreElements()) {
            key = en.nextElement().toString();
            value = (String) config.get(key);
            System.out.println(key + " = " + value);
        }
    }

    @Test
    public void testUserHomeConfig() {
        Assert.assertEquals("C:\\Users\\Pierreluo", System.getProperty("user.home"));
    }
}
