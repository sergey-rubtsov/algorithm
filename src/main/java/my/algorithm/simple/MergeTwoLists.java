package my.algorithm.simple;

public class MergeTwoLists {

/*
    Input: l1 = [1,2,4], l2 = [1,3,4]
    Output: [1,1,2,3,4,4]
    Example 2:

    Input: l1 = [], l2 = []
    Output: []
    Example 3:

    Input: l1 = [], l2 = [0]
    Output: [0]
*/
    public static void main(String[] args) {
        MergeTwoLists mergeTwoLists = new MergeTwoLists();

        ListNode n2 = new ListNode(4);
        ListNode n1 = new ListNode(2, n2);
        ListNode l1 = new ListNode(1, n1);

        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(3, n4);
        ListNode l2 = new ListNode(1, n3);

        ListNode node = mergeTwoLists.mergeTwoLists(l1, l2);
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode merged = new ListNode();
        ListNode head = merged;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                merged.val = list1.val;
                list1 = list1.next;
            } else {
                merged.val = list2.val;
                list2 = list2.next;
            }
            merged.next = new ListNode();
            merged = merged.next;
        }
        ListNode left;
        if (list1 == null) {
            left = list2;
        } else {
            left = list1;
        }
        while (left != null) {
            merged.val = left.val;
            left = left.next;
            if (left != null) {
                merged.next = new ListNode();
                merged = merged.next;
            }
        }
        return head;
    }

}
