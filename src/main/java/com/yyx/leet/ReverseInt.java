package com.yyx.leet;

import java.util.ArrayList;
import java.util.List;

public class ReverseInt {


    public static void main(String[] args) {

        System.out.println(reverse(1463847412));
    }

    /***
     * 倒序整数
     * @param x
     * @return
     */
    public static  int reverse(int x) {
        int sum = 0;
        while (x != 0) {
            int yu = x % 10;
            x = x / 10;
            if (sum <= Integer.MAX_VALUE / 10 && sum >= Integer.MIN_VALUE / 10) {
                sum = sum * 10 + yu;
            }
            else{
                return 0;
            }
        }
        return sum;
    }
}
