package com.yyx.sort;


import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * 插入排序
 */
public class Sort {





    public static void main(String[] args) {

    }

    @Test

    public void testList() {
        List list = new ArrayList<Integer>();
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(4, 8);
        System.out.println(list);
    }


    /**
     * 获取随机数组
     * @param size
     * @param bound
     * @return
     */

    public List getRandomList(int size,int bound){
        Random random = new Random();
        List list = new ArrayList();

        for (int i = 0; i < size; i++ ) {
            list.add(random.nextInt(bound));
        }

        return list;

    }



    public void testQucikSort(List<Integer> list) {

        double start = System.nanoTime();
        quickSort(list,0,list.size() - 1);
        System.out.println(list);
        double end = System.nanoTime();
        System.out.println("time :" + (end - start));
    }

    /**
     * 快速排序
     */

    public void quickSort(List<Integer> list, int left, int right) {
        if (left >= right) {
            return;
        }
        int base = list.get(left);
        int tmp_left = left;
        int tmp_right = right;
        while (tmp_left < tmp_right) {
            if (list.get(tmp_left) >= list.get(tmp_right)) {
                int tmp = list.get(tmp_left);
                list.set(tmp_left, list.get(tmp_right));
                list.set(tmp_right, tmp);


            }
            if (list.get(tmp_left) == base) {
                tmp_right--;

            } else {
                tmp_left++;
            }

        }
        quickSort(list,left,tmp_left - 1);
        quickSort(list, tmp_left+1,right);
    }
    /**
     * 希尔排序
     */
    public void shellSort(List<Integer> list) {

        //初始化间隔
        int length = list.size();
        int step = length/2;


        while (step >= 1) {
            for (int i = 0; i < step; i++) {
                for (int j = i + step; j < list.size(); j += step) {

                    for (int k = i; k < j; k += step) {
                        if (list.get(j) < list.get(k)) {
                            list.add(k, list.get(j));
                            list.remove(j + 1);
                            continue;
                        }
                    }
                }
            }

            step /= 2;
        }
    }


    /**
     * 测试算法时间
     */

    @Test
    public  void testAlgorithm() {
        List list = getRandomList(500, 1000);
        System.out.println("insert");
        testInsertSort(list);
        System.out.println("shell");
        testShellSort(list);
        System.out.println("quick");
        testQucikSort(list);
    }


    /**
     * 希尔排序测试
     */


    public void testShellSort(List<Integer> list) {
        double start = System.nanoTime();
        shellSort(list);
        System.out.println(list);
        double end = System.nanoTime();
        System.out.println("time :" + (end - start));
    }

    /**
     * 插入排序测试
     */
    public void testInsertSort(List<Integer>list){
        double start = System.nanoTime();
        insertSort(list);
        System.out.println(list);
        double end = System.nanoTime();
        System.out.println("time :" + (end - start));
    }



    /**
     * 插入排序
     * @param list
     */


    public void insertSort(List<Integer> list) {


        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(i) < list.get(j)) {
                    list.add(j, list.get(i));
                    list.remove(i + 1);
                    continue;
                }
            }
        }
    }
}
