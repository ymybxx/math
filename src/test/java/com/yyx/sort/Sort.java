package com.yyx.sort;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * 插入排序
 */
public class  Sort {





    public static void main(String[] args) {

    }


    public void testMergeSort(List<Integer> list) {
        double start = System.nanoTime();
        mergeSort(list,0, list.size() /2 ,list.size() - 1);
        System.out.println(list);
        double end = System.nanoTime();
        System.out.println("time :" + (end - start));

    }


    public void mergeSort(List<Integer> list, int left, int mid, int right) {
        if (left < right-1) {
            mergeSort(list, left, (left + mid) / 2, mid);
            mergeSort(list,mid+1,(mid + right) /2,right);
        }
        sSrot(list,left,mid,right);
    }
    @Test
    public void testSSort() {
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(9);
        l1.add(2);
        sSrot(l1,0,0,1);
        System.out.println(l1);

    }


    //mid是left的最后一个元素
    public void sSrot(List<Integer> list, int left, int mid, int right) {
        List<Integer> tmpList = new ArrayList<Integer>();
        int l = left;
        int r = mid + 1;
        while (l <= mid && r <= right) {
            if (list.get(l) <= list.get(r)) {
                tmpList.add(list.get(l++));
            } else {
                tmpList.add(list.get(r++));
            }
        }

        while (l <= mid) {
            tmpList.add(list.get(l++));
        }
        while (r <= right) {
            tmpList.add(list.get(r++));
        }

        int i = left;
        for (int now : tmpList) {
            list.set(i++, now);
        }
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
        List list = getRandomList(50000, 50000);
        List list1 = new ArrayList(list);
        List list2 = new ArrayList(list);
        List list3 = new ArrayList(list);
        System.out.println("insert");
        testInsertSort(list);
        System.out.println("shell");
        testShellSort(list1);
        System.out.println("quick");
        testQucikSort(list2);
        //40232
        //02324
        System.out.println("merge");
        testMergeSort(list3);
        System.out.println(list3);
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
