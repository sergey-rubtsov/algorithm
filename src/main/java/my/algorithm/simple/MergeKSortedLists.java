package my.algorithm.simple;

public class MergeKSortedLists {

    /*
    *   Input: lists = [[1,4,5],[1,3,4],[2,6]]
    *   Output: [1,1,2,3,4,4,5,6]
    *
    * */
    public static void main(String[] args) {
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        ListNode n2 = new ListNode(5);
        ListNode n1 = new ListNode(4, n2);
        ListNode l1 = new ListNode(1, n1);

        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(3, n4);
        ListNode l2 = new ListNode(1, n3);

        ListNode n5 = new ListNode(6);
        ListNode l3 = new ListNode(2, n5);
        ListNode[] lists = {l1, l2, l3};
        ListNode node = mergeKSortedLists.mergeKLists(lists);
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }

    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode first = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode second = lists[i];
            first = mergeTwoLists(first, second);
        }
        return first;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
