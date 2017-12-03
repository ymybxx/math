package com.yyx.leetcode;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.junit.Test;

import java.util.*;

public class Question {


    static private Map<Integer,String> phoneNum;
    public static void main(String[] args) {
        int[] height = new int[]{1, 1};
        System.out.println();
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode p = head;
        while (l1 != null || l2 != null) {
            if (l1.val < l2.val) {
                addNode(p, l1.val);
                l1 = l1.next;
                p = p.next;
            } else {
                addNode(p, l2.val);
                l2 = l2.next;
                p = p.next;
            }
        }
        while (l1 != null) {
            addNode(p, l1.val);
            l1 = l1.next;
            p = p.next;
        }
        while (l2 != null) {
            addNode(p, l2.val);
            l2 = l2.next;
            p = p.next;
        }
        return head.next;

    }

    public void addNode(ListNode listNode, int value) {
            ListNode newNode = new ListNode(value);
            listNode.next = listNode;
    }

    public boolean isValid(String s) {
        char[] words = s.toCharArray();
        List<Character> list = new ArrayList<Character>();
        for (char word : words) {
            int size = list.size();
            if(word == '(' || word =='[' || word =='{') list.add(word);
            else if (word == ')') {
                if (list.get(size - 1) == ')') {
                    list.remove(size - 1);
                } else {
                    return false;
                }
            }
            else if (word == '}') {
                if (list.get(size - 1) == '}') {
                    list.remove(size -1 );
                } else {
                    return false;
                }
            }
            else if (word == ']') {
                if (list.get(size - 1) == ']') {
                    list.remove(size - 1);
                } else {
                    return false;
                }
            }
        }
        if (list.size() == 0) {
            return true;
        } else {
            return false;
        }

    }

      public class ListNode
      {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }



    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        int length = 0;
        while (p != null) {
            length++;
            p = p.next;
        }
        p = head;
        int num = length - n;
        int tmp =0;
        if (num == 0) {
            head = head.next;
        }
        else {
            while (tmp < num - 1) {
                p = p.next;
                tmp++;
            }
            p.next = p.next.next;
        }
        return head;
    }

    @Test
    public void testLetter() {
        letterCombinations("32");
    }
    public List<String> letterCombinations(String digits) {
        List<String> resultList = new ArrayList<String>();
        //初始化号码对应的英文字母
        String[] phoneDic = new String[10];
        phoneDic[2] = "abc";phoneDic[3] = "def";phoneDic[4] = "ghi";phoneDic[5] = "jkl";
        phoneDic[6] = "mno";phoneDic[7] = "pqrs";phoneDic[8] = "tuv";phoneDic[9] = "wxyz";
        //获取参数的个数
        char[] phoneNumberChars = digits.toCharArray();
        int size = phoneNumberChars.length;
        //长度为0直接返回
        if (size == 0) return resultList;
        //第一个输入号码对应的字符数组,依次填入集合
        char[] phoneFirst = phoneDic[phoneNumberChars[0] - '0'].toCharArray();
        for (char ch : phoneFirst
             ) {
            resultList.add(String.valueOf(ch));
        }
        //结果中每个元素的长度
        int reLength = 1;


        //遍历之后的数字
        for (int i = 1; i < size; i++) {
            char number = phoneNumberChars[i];
            //获取字符对应的号码
            int numberInt = number - '0';
            //获取数字对应的字母字典
            char[] phoneDicChr = phoneDic[numberInt].toCharArray();
            reLength ++;
            while (resultList.get(0).length() < reLength) {
                for (char word : phoneDicChr) {
                    String tmp = resultList.get(0) + String.valueOf(word);
                    resultList.add(tmp);
                }
                resultList.remove(0);
            }
        }

        System.out.println(resultList);
        return resultList;
    }
    @Test
    public void testMaxArea() {
        int[] height = new int[]{1, 1, 2, 3, 1, 2, 1, 2, 4, 1, 2, 2, 1, 1};
        System.out.println(maxArea2(height));
    }


    @Test
    public void testThreeSum() {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        int[] nums = new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        //-5 -5 -4 -4 -4 -2 -2 -2 0 0 1 1 3  4 4
        lists = threeSum3(nums);
        System.out.println(lists);

    }



    @Test
    public void testThreeSumClose() {
        int arr[] = new int[]{-1,2,1,-4};
        int re = threeSumClosest(arr, 1);
        System.out.println(re);
    }
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int close = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int twoSum = target - nums[i];//剩下两数的和
            int left = i + 1;
            int right = length - 1;
            while (close != 0 && left < right) {
                int r = nums[left] + nums[right] - twoSum;
                if (Math.abs(r) < Math.abs(close)) {
                    close = r;
                }
                if (r > 0) {
                    while (left < right && nums[right - 1] == nums[right]) right--;
                    right--;
                } else {
                    while(left < right && nums[left + 1] == nums[left]) left++;
                    left++;
                }

            }
        }
        return target+close;

    }
    /**
     * 参考后精简
     * @param nums
     * @return
     */

    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        //
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for (int i = 0; i < length - 1; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int left = i+1, right = length - 1;
            int current = -nums[i];
            while (left < right) {
                    //-1 -2 -2 -4 0 1 2 2 3 4
                if (nums[left] + nums[right] == current) {
                    lists.add(Arrays.asList(-current, nums[left], nums[right]));
                    while ( left <right && nums[left] == nums[left+1] ) left++;
                    while(left < right && nums[right] == nums[right-1] ) right--;
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < current) {
                    while ( left <right && nums[left] == nums[left+1] ) left++;
                    left++;
                } else {
                    while(left < right && nums[right] == nums[right-1] ) right--;
                    right--;
                }
            }
        }
        return lists;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();

        int length = nums.length;
        //快排后的数组
        List<Integer> sortArr = new ArrayList<Integer>();
        for (int num : nums
                ) {
            sortArr.add(num);
        }
        Collections.sort(sortArr);
        //选取一个数为基准
        for (int numIndex = 0; numIndex < length; numIndex++) {
            int current = -sortArr.get(numIndex);
            int left = 0, right = length - 1;//设置前后两个指针准备遍历
            while (left < right) {
                if (left == numIndex) {
                    left++;
                    continue;
                }
                if (right == numIndex) {
                    right--;
                    continue;
                }
                if ((sortArr.get(left) + sortArr.get(right)) < current) {
                    left++;
                } else if ((sortArr.get(left) + sortArr.get(right)) > current) {
                    right--;
                } else {
                    List<Integer> numList = new ArrayList<Integer>();
                    numList.add(sortArr.get(left));
                    numList.add(sortArr.get(right));
                    numList.add(sortArr.get(numIndex));
                    Collections.sort(numList);

                    //判断是否有重复
                    if (!lists.contains(numList)) {
                        lists.add(numList);
                    }
                    right--;
                    left++;
                }
            }
        }
        return lists;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();

        int length = nums.length;
        //快排后的数组
        Arrays.sort(nums);
        int sortArr[] = nums;
        //选取一个数为基准
        for (int numIndex = 0; numIndex < length; numIndex++) {
            if (numIndex > 0 && sortArr[numIndex] == sortArr[numIndex - 1]) {
                continue;
            }
            int current = -sortArr[numIndex];
            int left = 0, right = length - 1;//设置前后两个指针准备遍历
            while (left < right) {
                if (left == numIndex) {
                    left++;
                    continue;
                }
                if (right == numIndex) {
                    right--;
                    continue;
                }
                if ((sortArr[left] + sortArr[right]) < current || (left > 0 && sortArr[left] == sortArr[left - 1] && left - 1 != numIndex)) {
                    left++;
                } else if ((sortArr[left] + sortArr[right]) > current || (right < length - 1 && sortArr[right] == sortArr[right + 1] && right + 1 != numIndex)) {
                    right--;
                } else {
                    List<Integer> numList = new ArrayList<Integer>();
                    numList.add(sortArr[left]);
                    numList.add(sortArr[right]);
                    numList.add(sortArr[numIndex]);
                    Collections.sort(numList);
                    //lists.add(numList);
                    //判断是否有重复
                    if (!lists.contains(numList)) {
                        lists.add(numList);
                    }
                    right--;
                    left++;
                }
            }
        }
        return lists;
    }


    @Test
    public void testQuickSort() {
        int[] arr = new int[]{2, 3, 1, 2, 3};
        int[] arrs = quickSort(arr, 0, arr.length - 1);
        for (int n : arrs
                ) {
            System.out.print(n + " ");
        }
    }


    /**
     * 快速排序
     */

    public int[] quickSort(int[] list, int left, int right) {
        if (left >= right) {
            return list;
        }
        int base = list[left];
        int tmp_left = left;
        int tmp_right = right;
        while (tmp_left < tmp_right) {
            if (list[tmp_left] >= list[tmp_right]) {
                int tmp = list[tmp_left];
                list[tmp_left] = list[tmp_right];
                list[tmp_right] = tmp;


            }
            if (list[tmp_left] == base) {
                tmp_right--;

            } else {
                tmp_left++;
            }

        }
        quickSort(list, left, tmp_left - 1);
        quickSort(list, tmp_left + 1, right);
        return list;
    }

    public int maxArea2(int[] height) {
        int dFlag = 0;//1表示前一次左进,2表示右退
        int tmp_left = 0;//上次经过计算的水桶的左值
        int tmp_right = 0;//上次经过计算的水桶的右值s
        int max = 0;
        int left = 0;
        int tmpHeight = 0;
        int right = height.length - 1;
        while (left < right) {
            if (dFlag == 1) {
                if (height[left] <= tmp_left) {
                    left++;
                    continue;
                }
            }
            if (dFlag == 2) {
                if (height[right] <= tmp_right) {
                    right--;
                    continue;
                }
            }
            tmp_left = height[left];
            tmp_right = height[right];
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
                dFlag = 1;
            } else {
                right--;
                dFlag = 2;
            }
        }
        return max;
    }

    public int maxArea(int[] height) {
        int max = 0;
        int tmp = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = 0; j < height.length; j++) {
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
