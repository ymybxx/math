package com.yyx.leetcode;

import org.junit.Test;

class Solution {


    public static void main(String[] args) {
        System.out.println(longestPalindrome("abbbbbbbba"));
    }
    public  static  String longestPalindrome(String s) {
        int max = 0 ;
        boolean[][] booleans = new boolean[s.length()][s.length()];
        int longBegin = 0;

        for(int i = 0; i < s.length(); i++) {
            booleans[i][i]  = true;
            max = 1;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                booleans[i][i+1]=true;

                max = 2;
                longBegin = i;
            }else {
                booleans[i][i+1]=false;
            }
        }
        for(int len = 3; len <= s.length(); len ++) {
            for(int i = 0 ; i < s.length() - len + 1; i++) {
                if (s.charAt(i) == s.charAt(i + len - 1) && booleans[i + 1][i + len - 2]) {
                    booleans[i][i + len - 1] = true;
                    longBegin = i;
                    max = len;
                }
            }
        }
        return s.substring(longBegin, longBegin + max);
    }


    @Test
    public void testIsPal() {
        System.out.println(isPal("sbsba", 0, 4));
        System.out.println(isPal("sbsba", 1, 3));

    }



    public boolean isPal(String s, int begin, int end) {

        if (begin < 0) {
            begin = 0;
        }
        for (int i = begin, j = end; i <= j; i++) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}
