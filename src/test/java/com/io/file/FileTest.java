package com.io.file;

import java.io.File;

/**
 * Created by Pierreluo on 2017/9/25.
 */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("");
        long lastModified = file.lastModified();
    }
}
