package com.concurrent.threadpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pierreluo on 2017/12/6.
 */
public class BulkExecuteMain {
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        System.out.println("******** Bulk Execute Start ********");
        try {
            Class.forName("");
        } catch (Exception e) {
            System.err.println("Failed to Initialize Settings.");
            return;
        }
        boolean running = true;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Future<?>> futureList = Collections.synchronizedList(new ArrayList<>());
        try {
            String toDateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "23:59:59";
            while (running) {
                try {
                    conn = BulkExecuteUtil.getConnection();
                    Long id = null;
                    // 生产者
                    pstm = conn.prepareStatement("select id from tablexxx where xxx limit 1");
                    rs = pstm.executeQuery();
                    id = rs.getLong("id");
                    rs.close();
                    pstm.close();

                    // 消费者
                    if(id != null) {
                        BulkExecuteUtil.beginTransaction(conn);
                        pstm = conn.prepareStatement("update tablexxx set status = 'Q' where id = ?");
                        pstm.setLong(1, id);
                        pstm.executeUpdate();
                        pstm.close();
                        conn.commit();
                        BulkExecuteUtil.endTransaction(conn);
                        Future<?> future = fixedThreadPool.submit(new BulkExecuteJob(String.valueOf(id)));
                        futureList.add(future);
                    } else {
                        BulkExecuteUtil.closeConnection();
                        System.out.println(" ********* Bulk Execute Data Scanning Waiting For 10 Minutes");
                        TimeUnit.MINUTES.sleep(10);
                    }
                } catch (Exception e) {
                    if(conn != null) conn.rollback();
                    System.err.println("error " + e.getMessage());
                } finally {
                    try {
                        if(rs != null) rs.close();
                        if(pstm != null) pstm.close();
                    } catch (Exception e) { }
                }
                if(BulkExecuteUtil.isCancel()) {
                    running = true;
                }
            }
            for(Future<?> future : futureList) {
                future.get();
            }
            // need double check?
            for(Future<?> future : futureList) {
                future.get();
            }
            fixedThreadPool.shutdown();
            conn = BulkExecuteUtil.getConnection();
            BulkExecuteUtil.beginTransaction(conn);
            // clear all taskQueue --> status = 'P'(Pause)
            pstm = conn.prepareStatement("update tablexxx set status = 'P' where status = 'Q'");
            pstm.executeUpdate();
            pstm.close();
            conn.commit();
            BulkExecuteUtil.endTransaction(conn);
        } catch (Exception e) {
            if(conn != null) try {
                conn.rollback();
            } catch (SQLException e1) { }
            System.err.println("error" + e.getMessage());
        } finally {

            try {
                if(rs != null) rs.close();
                if(pstm != null) pstm.close();
                if(conn != null) BulkExecuteUtil.closeConnection();
            } catch (Exception e) { }
        }
        System.out.println(" ************ Bulk Execute End ************* ");
        System.exit(0);
    }
}
