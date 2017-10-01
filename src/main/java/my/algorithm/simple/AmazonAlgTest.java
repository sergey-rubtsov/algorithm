package my.algorithm.simple;

/**
 * Created by Serg on 05.02.2017.
 */
public class AmazonAlgTest {

    public static void main(String[] args) {
        long test = 23545;
        long test2 = 94187978322L;

        long next = findNextPalindrome(test);
        long result = 23632;

        System.out.println(next == test);
        System.out.println(next);
        System.out.println(findNextPalindrome(test2));
        System.out.println(test2 > findNextPalindrome(test2));
    }

    //find the next smallest palindrome
    public static long findNextPalindrome(Long input) {
        if (!isPalindrom(input)) {
            char[] array = input.toString().toCharArray();
            for (int i = 0, j = array.length - 1; i < array.length; i++, j--) {
                array[j] = array[i];
                if (i >= j) {
                    break;
                }
            }
            long middle = Long.parseLong(new String(array));
            if (middle < input) {

            }
            return middle;
        } else {
            return findNextPalindrome(input++);
        }
    }

    public static boolean isPalindrom(Long input) {
        return isPalindrom(input.toString().toCharArray());
    }

    public static boolean isPalindrom(char[] array) {
        for (int i = 0, j = array.length - 1; i < array.length; i++, j--) {
            if (array[i] != array[j]) {
                return false;
            }
        }
        return true;
    }
}
