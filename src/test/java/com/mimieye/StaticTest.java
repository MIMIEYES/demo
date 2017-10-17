package com.mimieye;

/**
 * Created by Pierreluo on 2017/4/20.
 */
public class StaticTest {
    private void test() {
       int[] a = new int[2048*1024];
       int[] b = new int[2048*1024];
       int[] c = new int[2048*1024];
       int[] d = new int[2048*1024];
       int[] a1 = new int[2048*1024];
       int[] b1 = new int[2048*1024];
       int[] c1 = new int[2048*1024];
       int[] d1 = new int[2048*1024];
//        a=null;
//        b=null;
//        c=null;
//        d=null;
//        a1=null;
//        b1=null;
//        c1=null;
//        d1=null;

    }

    private void printMemory(){
        int i = (int)Runtime.getRuntime().totalMemory()/1024;//Java 虚拟机中的内存总量,以字节为单位
        int j = (int)Runtime.getRuntime().freeMemory()/1024;//Java 虚拟机中的空闲内存量
        System.out.println("使用内存量 is "+((i-j)/1024d));
    }

    public static void main(String[] args) throws Exception{
        StaticTest st = new StaticTest();
        st.printMemory();
        //System.gc();
//        Thread.sleep(1000*3);
        st.test();
        st.printMemory();
        st = null;
        //System.gc();
        int p = 0;
        long s = System.currentTimeMillis();
        while (true){
//            p++;
//            if(p%50000000 == 0){
//                System.gc();
//            }
//                p =0;
//                System.out.println();
//                System.out.println();
//                System.out.println();
//                System.out.println();
//                System.out.println();
                int k = (int)Runtime.getRuntime().totalMemory()/1024;//Java 虚拟机中的内存总量,以字节为单位
                int m = (int)Runtime.getRuntime().freeMemory()/1024;//Java 虚拟机中的空闲内存量
                double d = ((k-m)/1024d);
                if(d<10){
                    System.out.println("使用内存量 is "+d);
                    long e = System.currentTimeMillis();
                    System.out.println((e-s)/1000d);
                    break;
                }
//                Thread.sleep(1000*3);
//            }
        }
    }





}
