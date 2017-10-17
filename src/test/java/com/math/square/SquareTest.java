package com.math.square;

/**
 * Created by Pierreluo on 2017/6/23.
 */
public class SquareTest {
    public static void main(String[] args) {
        System.out.println(extractSquare(256*256));
    }

    // 穷举法
    // 预先准备一个2的n次方的数组，从中筛选出平方根因子范围
    public static double extractSquare(int a) {
        double min = 2;
        double seed0 = 0.1;
        double seed1 = 1;
        double b = a/min;
        int count = 0;
        if(b < min) {
            //
            while(b*b > a) {
                b = b - seed0;
            }
            return b;
        } else {
            while(b*b >a){
                b = b -seed1;
                System.out.println((count++)+"="+b);
            }
            // 3
            while(b*b < a) {
                b = b + seed0;
                System.out.println((count++)+"="+b);
            }
            return b;
        }
    }

    // 二分法查找
    //TODO
    public static double binarySearch(int a) {
        return 0;
    }
}
