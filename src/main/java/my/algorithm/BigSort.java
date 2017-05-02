package my.algorithm;

/**
 * Created by Serg on 30.04.2017.
 */
public class BigSort {

    public static void main(String[] args) {
        //System.out.println(compare("3", "10"));
        //System.out.println(compare("10", "3"));
        String[] unsorted = {"31415926535897932384626433832795", "1", "3", "10", "3", "5"};
        sort(unsorted);
        for (int i = 0; i < unsorted.length; i++) {
            System.out.println(unsorted[i]);
        }
    }

    private static void longSort(String[] unsorted) {
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

    //merge sort
    private static String[] array;
    private static String[] tempMergArr;
    private static int length;

    public static void sort(String[] inputArr) {
        array = inputArr;
        length = inputArr.length;
        tempMergArr = new String[length];
        doMergeSort(0, length - 1);
    }

    private static void doMergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    private static void mergeParts(int lowerIndex, int middle, int higherIndex) {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            //tempMergArr[i] <= tempMergArr[j]
            if (compare (tempMergArr[i], tempMergArr[j]) <= 0) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }

    }
}
