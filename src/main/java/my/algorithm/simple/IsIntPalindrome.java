package my.algorithm.simple;

public class IsIntPalindrome {
    public static void main(String[] args) {
        IsIntPalindrome test = new IsIntPalindrome();
        assert test.isPalindrome(0);
        assert test.isPalindrome(1);
        assert test.isPalindrome(121);
        assert test.isPalindrome(1001);
        assert test.isPalindrome(1221);
        assert test.isPalindrome(123321);
        assert test.isPalindrome(1234321);
        assert test.isPalindrome(123454321);
        assert !test.isPalindrome(-121);
        assert !test.isPalindrome(123);
        assert !test.isPalindrome(10);
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) return false;
        int tmp = x;
        int order = 1;
        while(tmp >= 10) {
            tmp = tmp / 10;
            order++;
        }
        int[] numbers = new int[order];
        tmp = x;
        for (int i = 0; i < numbers.length; i++) {
            int tmp2 = tmp / 10;
            numbers[i] = (tmp - tmp2 * 10);
            tmp = tmp2;
        }
        for (int i = 0, j = numbers.length - 1; i < numbers.length / 2; i++, j--) {
             if (numbers[i] != numbers[j]) {
                 return false;
             }
        }
        return true;
    }
}
