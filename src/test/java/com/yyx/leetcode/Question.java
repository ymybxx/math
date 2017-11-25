package com.yyx.leetcode;

import org.junit.Test;

public class Question {


    public static void main(String[] args) {
        int[] height = new int[]{1,1};
        System.out.println();
     }


     @Test
     public void testMaxArea() {
         int[] height = new int[]{1,1,2,3,1,2,1,2,4,1,2,2,1,1};
         System.out.println(maxArea2(height));
     }


    public int maxArea2(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(max, Math.min(height[left],height[right])*(right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max ;
    }
    public int maxArea(int[] height) {
        int max = 0;
        int tmp = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for(int j = 0; j < height.length; j++) {
                int width = j - i;
                int high = Math.min(height[i], height[j]);
                tmp = width * high;
                max = (max > tmp) ? max : tmp;
            }
        }
        return max;
    }
    @Test
    public void test() {
        System.out.println("aaa");
    }
}
