package com.yyx.leetcode;

import org.junit.Test;

import java.util.*;

public class Question {


    static private Map<Integer, String> phoneNum;



    public static void main(String[] args) {

        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(1);
        System.out.println(minStack.getMin());
    }

    static  class MinStack {


        Map<Integer,Integer> minMap = new TreeMap<Integer, Integer>();
        Stack<Integer> stack = new Stack<Integer>();

        /*
         * initialize your data structure here.
         */
        public MinStack() {

        }








        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return isSy(root.left, root.right);
        }
        public boolean isSy(TreeNode left, TreeNode right) {

            if (left == null && right == null) {
                return true;
            }

            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            return (isSy(left.left, right.right) && isSy(left.right, right.left));


        }
        public void push(int x) {
            stack.push(x);
            if (minMap.containsKey(x) == false) {
                minMap.put(x, 1);
            } else {
                minMap.put(x, minMap.get(x) + 1);
            }

        }

        public void pop() {
            int tmp = stack.pop();
            if (minMap.get(tmp) == 1) {
                minMap.remove(tmp);
            } else {
                minMap.put(tmp, minMap.get(tmp) - 1);
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minMap.entrySet().iterator().next().getKey();


        }
    }

    @Test
    public void testFirstMissing() {
        int[] nums = new int[]{3,4,-1,1};
        System.out.println(firstMissingPositive(nums));
    }

    @Test
    public void testTrap() {
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(nums));
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }
        return (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val));
    }
        public boolean hasPathSum2(TreeNode root, int sum) {
            if (root == null) return false;
            int now = 0;
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            Set<TreeNode> nodeSet = new TreeSet<TreeNode>();

            queue.add(root);
            TreeNode node = null;
            while ((node = queue.peek()) != null) {
                //当前node
                now += node.val;//获取新值
                if (now == sum) {
                    return true;
                }
                //如果set中不存在则加入set中标记
                if (nodeSet.contains(node)) {
                    nodeSet.add(node);
                }
                //如果有左节点未进入则把左节点加入队列
                if (node.left != null && nodeSet.contains(node.left) == false) {
                    queue.add(node.left);
                } else if (node.right != null && nodeSet.contains(node.right) == false) {
                    queue.add(node.left);
                } else {
                    now -= node.val;
                    queue.poll();
                }

            }
            return false;
        }

    public int trap(int[] height) {
        int l = 0;
        int r = 0;
        int min = 0;
        int left = 0;
        int water = 0;
        int right = height.length - 1;
        while (left < right) {
            //获取当前两端的值
            int ln = height[left];
            int rn = height[right];
            //如果以后后小于之前的高度
            if (ln < l) {
                if( ln < min) {
                    water += min - ln;
                    left++;
                    continue;
                }
            }else {
                l =ln;
            }
            if (rn < r) {
                if(rn < min) {
                    water += min - rn;
                    right--;
                    continue;
                }
            } else {
                r = rn;
            }
            //左右两端比较小的
            min = Math.min(l, r);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right -- ;
            }
        }
        return water;
    }




    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        inOrder(list, root);
        return list;
    }

    public void inOrder(List<Integer> list, TreeNode cur) {
        if (cur != null) {
            inOrder(list,cur.left);
            list.add(cur.val);
            inOrder(list,cur.right);

        }
    }

    @Test
    public void testChangeInteger() {
        Integer integer = new Integer(2);
        testInteger(integer);
        System.out.println(integer);

    }
    public void testInteger(Integer integer) {
        integer = 1;
    }


    @Test
    public void testNumTree() {
        System.out.println(numTrees(3));

    }



    public int numTrees(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
       int[] re  = new int[n + 1];
       re[0] = 1;
       for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                re[i] += re[j] * re[i - 1 - j];
            }
        }
        return re[n];
    }



     class TreeNode {
         int val;
          TreeNode left;
          TreeNode right;
        TreeNode(int x) { val = x; }
     }


    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            int now = nums[i];
            if (now <= 0 || now > nums.length || now == i + 1) { i++; continue;}//忽略情况
            int ex = nums[now - 1];//正确位置上的数字
            //两数字相等
            if (ex == now) {i++; continue;}
            //两数不相等则交换两数字
            if (ex != now) {
                nums[i] = ex;
                nums[now - 1] = now;
            }

        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
    @Test
    public void testNext() {
        int[] nums = new int[]{3,4,-1,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int length = nums.length;
        for (int i = length - 2; i >= 0; i--) {
            int in_index = -1;
            int swap_index = -1;
            //找到减少 i
            if (nums[i] < nums[i + 1]) {
                in_index = i;//递增点坐标
                for (int j = i + 1; j < length; j++) {
                    //找到减少点i 小的数
                    if (nums[j] > nums[in_index]) {
                        swap_index = j;
                    }
                }
            }

            if (in_index != -1 && swap_index != -1) {
                //将两个坐标的数交换
                int tmp = nums[in_index];
                nums[in_index] = nums[swap_index];
                nums[swap_index] = tmp;
                //将后续的数组排序
                Arrays.sort(nums, in_index + 1, length);
                return;
            }
        }
        Arrays.sort(nums);
    }

    public int searchInsert(int[] nums, int target) {

        int i = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                break;
            }
        }

        return i;
    }

    @Test
    public void testSub() {
        String s = "12345";
        System.out.println(divide(-2147483648, -2147483648));
    }

    @Test
    public void testCanJump() {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(canJump(nums));
    }

    public boolean canJump(int[] nums) {
        int i = 0;
        int length = nums.length;
        while (i < length) {
            if (nums[i] == 0) return false;
            int next_index = i + nums[i];
            if (next_index >= length - 1) {
                return true;
            }
            int maxStep = next_index + nums[next_index];//最远步数
            int maxIndex = next_index;
            for (int j = i + 1; j < next_index; j++) {
                if (j + nums[j] > maxStep) {
                    maxStep = j + nums[j];
                    maxIndex = j;
                }
            }
            i = maxIndex;
        }
        return true;
    }
    @Test
    public void testCombinatition() {
        int[] test = new int[]{10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum2(test,8));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        combineSumTwo(candidates, 0, target, lists, null);
        return lists;
    }



    public void combineSumTwo(int[] candidates, int begin,int target,List<List<Integer>> lists,List<Integer> list) {
        for (int i = begin; i < candidates.length; i++) {
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int now = candidates[i];
            if (now == target) {
                if (list == null) {
                    list = new ArrayList<Integer>();
                }
                List<Integer> integerList = new ArrayList<Integer>(list);
                integerList.add(now);
                lists.add(integerList);
                continue;

            }
            if (now * 2 <=  target) { //两倍的值小于目标值
                if (list == null) {
                    list = new ArrayList<Integer>();
                }
                List<Integer> integerList = new ArrayList<Integer>(list);
                integerList.add(now);
                combineSumTwo(candidates,i  + 1,target - now,lists,integerList);
            }
        }

    }
    public void combineSum(int[] candidates, int begin,int target,List<List<Integer>> lists,List<Integer> list) {
        for (int i = begin; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int now = candidates[i];
            if (now == target) {
                if (list == null) {
                    list = new ArrayList<Integer>();
                }
                List<Integer> integerList = new ArrayList<Integer>(list);
                integerList.add(now);
                lists.add(integerList);
                continue;

            }
            if (now * 2 <=  target) { //两倍的值小于目标值
                if (list == null) {
                    list = new ArrayList<Integer>();
                }
                List<Integer> integerList = new ArrayList<Integer>(list);
                integerList.add(now);
                combineSum(candidates,i,target - now,lists,integerList);
            }
        }

    }

    public void count(int n) {


        if (n == 1) {
            s = "1";
            return;
        }

        count(n - 1);
        StringBuffer sb = new StringBuffer();
        char ch = s.charAt(0);
        int times = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ch) {
                times++;
            } else {
                sb.append(times).append(ch);
                ch = s.charAt(i);
                times = 1;
            }
        }
        sb.append(times).append(ch);
        s = sb.toString();


    }

    static String s = "";
    public String countAndSay(int n) {
        count(n);
        return s;
    }

    @Test
    public void testCountAndSay() {
        String s = countAndSay(15);
        System.out.println(s);
    }

    @Test
    public void testSearch() {
        int[] nums = new int[]{1, 3};
        int r = search(nums, 2);
        System.out.println(r);
    }

    public int search(int[] nums, int target) {

        int length = nums.length;
        if (length == 0) return -1;
        if (length == 1) {
            if (nums[0] == target) return 0;
            else return -1;
        }
        int rot = -1;
        int left;
        int right;
        int mid;
        //找出拐点
        for (int i = 1; i < length; i++) {
            if (nums[i] < nums[i - 1]) {
                rot = i;
            }
        }
        //没有拐点
        if (rot == -1) {
            left = 0;
            right = length - 1;
            mid = (left + right) / 2;
        } else {
            //目标比拐点前一个数大或者比拐点数小
            if (target > nums[rot - 1] || target < nums[rot]) {
                return -1;
            }
            //目标大于首元素
            if (target >= nums[0]) {
                left = 0;
                right = rot - 1;
                mid = (left + right) / 2;
            } else {
                left = rot;
                right = length - 1;
                mid = (left + right) / 2;
            }
        }

        while (left <= right) {
            if (nums[mid] == target) {
                return mid;
            }
            if (target < nums[mid]) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else {
                left = mid + 1;
                mid = (left + right) / 2;
            }
        }
        return -1;
    }

    public int divide(int dividend, int divisor) {

        long dividend_long = dividend;
        long divisor_long = divisor;
        int flag = 1;
        if (divisor_long == 0) {
            return Integer.MAX_VALUE;
        }
        if (divisor_long < 0) {
            divisor_long = -divisor_long;
            flag = flag * -1;
        }
        //分子转化成字符串
        String fenzi = String.valueOf(dividend_long);
        //处理负号
        if (fenzi.charAt(0) == '-') {
            fenzi = fenzi.substring(1);
            flag = flag * -1;
        }
        //设置当前的左右游标
        int left = 0;
        int right = 1;
        int length = fenzi.length();
        StringBuffer sb = new StringBuffer();
        int yu = 0;
        while (right <= length) {
            long now = Integer.parseInt(fenzi.substring(left, right));//将当前要计算的字符串转化为数字
            now = now + yu * 10;
            long sum = 0;
            long result_now = 0;
            while ((sum + divisor_long) <= now) {
                sum += divisor_long;
                result_now++;
            }
            yu = (int) (now - sum);
            if (result_now == 0) {
                if (yu != 0) {
                    if (sb.length() != 0) {
                        sb.append(0);
                    }
                    left = right;
                    right++;

                } else {
                    sb.append(0);
                    left = right;
                    right++;
                }

            } else {
                sb.append(result_now);
                left = right;
                right++;
            }
        }
        if (sb.length() == 0) {
            return 0;
        }
        Long result = flag * Long.parseLong(sb.toString());
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return result.intValue();
        }
    }


    public ListNode swapPairs(ListNode head) {
        if (head == null && head.next == null) {
            return head;
        }
        //第一组进行交换
        ListNode p = head.next;
        head.next = head.next.next;
        p.next = head;
        head = p;


        //后续循环操作
        p = p.next;//第三个元素
        while (p.next != null && p.next.next != null) {
            ListNode now_node = p.next;
            ListNode next_node = p.next.next;
            now_node.next = next_node.next;
            next_node.next = now_node;
            p.next = next_node;
            p = now_node;
        }

        return head;

    }


    @Test
    public void testGen() {
        List<String> list = generateParenthesis(3);
        System.out.println(list);
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        String s = "";
        genPar(list, s, n, n);
        return list;
    }

    public void genPar(List<String> re, String s, int left, int right) {
        if (left == 0 && right == 0) {
            re.add(s);
        }

        if (left > 0) {
            genPar(re, s + "(", left - 1, right);
        }
        if (right > left) {
            genPar(re, s + ")", left, right - 1);
        }
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
            if (word == '(' || word == '[' || word == '{') list.add(word);
            else if (word == ')') {
                if (list.get(size - 1) == ')') {
                    list.remove(size - 1);
                } else {
                    return false;
                }
            } else if (word == '}') {
                if (list.get(size - 1) == '}') {
                    list.remove(size - 1);
                } else {
                    return false;
                }
            } else if (word == ']') {
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

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
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
        int tmp = 0;
        if (num == 0) {
            head = head.next;
        } else {
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
        phoneDic[2] = "abc";
        phoneDic[3] = "def";
        phoneDic[4] = "ghi";
        phoneDic[5] = "jkl";
        phoneDic[6] = "mno";
        phoneDic[7] = "pqrs";
        phoneDic[8] = "tuv";
        phoneDic[9] = "wxyz";
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
            reLength++;
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
        int[] nums = new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        //-5 -5 -4 -4 -4 -2 -2 -2 0 0 1 1 3  4 4
        lists = threeSum3(nums);
        System.out.println(lists);

    }


    @Test
    public void testThreeSumClose() {
        int arr[] = new int[]{-1, 2, 1, -4};
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
                    while (left < right && nums[left + 1] == nums[left]) left++;
                    left++;
                }

            }
        }
        return target + close;

    }

    @Test
    public void testFourSum() {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> lists = fourSum(nums, 0);
        System.out.println(lists);

    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = length - 1;
            threeSumdForFour(lists, nums, left, right, nums[i], target);
        }
        return lists;
    }


    public void threeSumdForFour(List<List<Integer>> lists, int[] nums, int begin, int end, int first, int target) {
        int length = end + 1;
        //

        for (int i = begin; i < length - 1; i++) {
            if (i > begin && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = length - 1;
            int current = target - first - nums[i];
            while (left < right) {
                //-1 -2 -2 -4 0 1 2 2 3 4
                if (nums[left] + nums[right] == current) {
                    lists.add(Arrays.asList(first, nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < current) {
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                } else {
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    right--;
                }
            }
        }
    }

    /**
     * 参考后精简
     *
     * @param nums
     * @return
     */

    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        //
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for (int i = 0; i < length - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = length - 1;
            int current = -nums[i];
            while (left < right) {
                //-1 -2 -2 -4 0 1 2 2 3 4
                if (nums[left] + nums[right] == current) {
                    lists.add(Arrays.asList(-current, nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < current) {
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                } else {
                    while (left < right && nums[right] == nums[right - 1]) right--;
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
