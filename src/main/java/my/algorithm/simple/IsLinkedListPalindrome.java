package my.algorithm.simple;

public class IsLinkedListPalindrome {

    public static void main(String[] args) {
        IsLinkedListPalindrome isLinkedListPalindrome = new IsLinkedListPalindrome();
        ListNode n5 = new ListNode(0);
        ListNode n4 = new ListNode(1, n5);
        ListNode n3 = new ListNode(2, n4);
        ListNode n2 = new ListNode(3, n3);
        ListNode n1 = new ListNode(4, n2);
        ListNode head = new ListNode(5, n1);
        System.out.println(isLinkedListPalindrome.isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        int length = 0;
        ListNode head0 = head;
        while (null != head0.next) {
            head0 = head0.next;
            length++;
        }
        int index = length / 2;
        if (length % 2 != 0) index++;
        ListNode head1;
        ListNode prev = null;
        ListNode current = head;
        ListNode next;
        while (current != null && index > 0) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            index--;
        }
        head0 = prev;
        if (length % 2 != 0) {
            head1 = current;
        } else {
            head1 = current.next;
        }
        while (head0 != null && head1 != null) {
            if (head0.val != head1.val) {
                return false;
            }
            head0 = head0.next;
            head1 = head1.next;
        }
        return true;
    }

}
