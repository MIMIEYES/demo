package com.mimieye.dubbo.impl;

import com.mimieye.dubbo.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by Pierreluo on 2017/8/10.
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    //@Override
    public String sayWhat() {
        return "Just F devtool?";
    }
}
