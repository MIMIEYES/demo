package com.concurrent.threadpool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Pierreluo on 2017/12/6.
 */
public class BulkExecuteUtil {
    private static ThreadLocal<Connection> connections = new ThreadLocal<>();
    private static final String PATH = "/home/bulk/bulkConf.properties";
    private static final File FILE = new File(PATH);
    private static volatile long lastModified = FILE.lastModified();
    private static final ReadWriteLock RWLock = new ReentrantReadWriteLock();


    public static Connection getConnection() throws Exception {
        Connection conn = connections.get();
        if(conn == null) {
            //TODO new Instance conn
            connections.set(conn);
        }
        return conn;
    }

    public static void beginTransaction(Connection conn) throws Exception {
        if(conn != null) {
            if(conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
        }
    }

    public static void endTransaction(Connection conn) throws Exception {
        if(conn != null) {
            if(!conn.getAutoCommit()) {
                conn.setAutoCommit(true);
            }
        }
    }

    public static void closeConnection() throws Exception {
        Connection conn = connections.get();
        if(conn != null) {
            conn.close();
            connections.remove();
        }
    }

    public static boolean isCancel() throws Exception{
        boolean isCancel = false;
        if(FILE.lastModified() != readLastModified()) {
            System.out.println("Load the conf's modified currentTimeMillis = " + System.currentTimeMillis());
            Properties properties = new Properties();
            InputStream in = new FileInputStream(FILE);
            properties.load(in);
            in.close();
            isCancel = "Y".equals(properties.getProperty("cancel"));
            if(!isCancel) {
                writeLastModified(FILE.lastModified());
            }
            properties = null;
        }
        return isCancel;
    }

    private static void writeLastModified(long l) {
        RWLock.writeLock().lock();
        try {
            lastModified = l;
        } finally {
            RWLock.writeLock().unlock();
        }
    }

    private static long readLastModified() {
        RWLock.readLock().lock();
        try {
            return lastModified;
        } finally {
            RWLock.readLock().unlock();
        }
    }
}
