import java.io.IOException;

import javax.swing.tree.TreeNode;

/*
 * You are given two non-empty linked lists representing two non-negative integers. The digits 
 * are stored in reverse order and each of their nodes contain a single digit. Add the two numbers 
 * and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
}
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);
    
        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }
    
    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }
    
        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);
            
            ListNode ret = new Solution().addTwoNumbers(l1, l2);
            ListNode ret = new SolutionTwo().addTwoNumbers(l1, l2);
            
            String out = listNodeToString(ret);
            
            System.out.print(out);
        }
    }
    class SolutionTwo {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return add(l1,l2,0);
        }

        public ListNode add(ListNode l1, ListNode l2, int carry){
            if(l1==null&&l2==null){
                if(carry == 0){
                    return null;
                } else {
                    ListNode num = new ListNode(1);
                    num.next=null;
                    return num;
                }
            }
            if(l1 == null){
                if(carry == 0){
                    return l2;
                } else {
                    if(l2.val+carry>=10){
                        l2.val = (l2.val+carry)%10;
                        l2.next = add(l1,l2.next,1);
                    } else {
                        l2.val+=carry;
                    }
                    return l2;
                }
            }
            if(l2 == null){
                if(carry == 0){
                    return l1;
                } else {
                    if(l1.val+carry>=10){
                        l1.val = (l1.val+carry)%10;
                        l1.next = add(l1.next,l2,1);
                    } else {
                        l1.val+=carry;
                    }
                    return l1;
                }
            }
            if(l1.val + l2.val+carry>=10){
                ListNode num = new ListNode((l1.val + l2.val+carry)%10);
                num.next = add(l1.next,l2.next,1);
                return num;
            } else {
                ListNode num = new ListNode(l1.val + l2.val+carry);
                num.next = add(l1.next,l2.next,0);
                return num;
            }
        }
    }
}
/*
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  
 * You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0
*/
class SolutionTwo {
    public int numJewelsInStones(String J, String S) {
        if(J.isEmpty()) return 0;
		int cnt = 0;
		for(int i = 0 ; i < S.length() ; i++) {
			if(J.indexOf(S.charAt(i)) != -1) cnt++;
		}
		return cnt;
    }
}
/**
 * Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
class SolutionThree {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    // p and q are both null
    if (p == null && q == null) return true;
    // one of p and q is null
    if (q == null || p == null) return false;
    if (p.val != q.val) return false;
    return isSameTree(p.right, q.right) &&
            isSameTree(p.left, q.left);
  }
}

