package my.algorithm;

/**
 * Created by Serg on 30.04.2017.
 */
public class BigSort {

    public static void main(String[] args) {
        //System.out.println(compare("3", "10"));
        //System.out.println(compare("10", "3"));
        String[] unsorted = {"31415926535897932384626433832795", "1", "3", "10", "3", "5"};
        for (int i = 0; i < unsorted.length; i++) {
            int min = i;
            for (int j = i; j < unsorted.length; j++) {
                if (compare(unsorted[min], unsorted[j]) > 0) {
                    min = j;
                }
            }
            String temp = unsorted[i];
            unsorted[i] = unsorted[min];
            unsorted[min] = temp;
        }
        for (int i = 0; i < unsorted.length; i++) {
            System.out.println(unsorted[i]);
        }
    }

    public static int compare(String one, String another) {
        int result = one.length() - another.length();
        if (result != 0) {
            return result;
        } else {
            char[] oneArray = one.toCharArray();
            char[] anotherArray = another.toCharArray();
            for (int i = 0; i < oneArray.length; i++) {
                char oneChar = oneArray[i];
                char anotherChar = anotherArray[i];
                if (oneChar == anotherChar) {
                    continue;
                } else {
                    return oneChar - anotherChar;
                }
            }
        }
        return 0;
    }

}
