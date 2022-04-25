package my.algorithm.simple;

import org.junit.*;

public class AddTwoNumbersLinkedList {

    /*
    * You are given two non-empty linked lists representing two non-negative integers.
    * The digits are stored in reverse order, and each of their nodes contains a single
    * digit. Add the two numbers and return the sum as a linked list. You may assume the
    * two numbers do not contain any leading zero, except the number 0 itself.
    *
    * Input: l1 = [2,4,3], l2 = [5,6,4]
    * Output: [7,0,8]
    * Explanation: 342 + 465 = 807.
    *
    * [9,9,9,9,9,9,9]
    * [9,9,9,9]
    * [8,9,9,9,0,0,0,1]
    * */

    @Test
    public void test() {
        AddTwoNumbersLinkedList addTwoNumbersLinkedList = new AddTwoNumbersLinkedList();
        ListNode n2 = new ListNode(2);
        ListNode n4 = new ListNode(4, n2);
        ListNode n3 = new ListNode(3, n4);

        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6, l5);
        ListNode l4 = new ListNode(4, l6);
        ListNode summ = addTwoNumbersLinkedList.addTwoNumbers(n3, l4);
        Assert.assertEquals(summ.val, 7);
        Assert.assertEquals(summ.next.val, 0);
        Assert.assertEquals(summ.next.next.val, 8);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        ListNode result = node;
        while (l1 != null || l2 != null) {
            ListNode next = new ListNode();
            if (l1 != null && l2 != null) {
                node.val = node.val + l1.val + l2.val;
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                node.val = node.val + l1.val;
                l1 = l1.next;
            } else {
                node.val = node.val + l2.val;
                l2 = l2.next;
            }
            if (node.val > 9) {
                node.val = node.val - 10;
                next.val = 1;
            }
            if (l1 != null || l2 != null || next.val == 1) {
                node.next = next;
                node = node.next;
            }
        }
        return result;
    }

}
