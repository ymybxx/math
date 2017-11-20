package com.yyx.leetcode;

public class Test {


    @org.junit.Test
    public void testIsPal() {
        System.out.println(isPal("sbsba", 0, 4));
        System.out.println(isPal("sbsba", 1, 3));

    }



    public boolean isPal(String s, int begin, int end) {

        for (int i = begin, j = end; i < j; i++ ,j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }

}
