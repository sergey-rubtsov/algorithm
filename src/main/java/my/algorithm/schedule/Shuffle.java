package my.algorithm.schedule;

/**
 * The best shuffle compute as maximal:
 * F(S) = abs(S[0]-S[1]) + abs(S[1]-S[2]) + abs(S[2]-S[3]).
 * for input -1, 3, 5, 5 result is 14
 */
public class Shuffle {

    public static void main(String[] args) {
        int[] shuffle = {-1, 3, 5, 5};
        int max = compute(shuffle);
        System.out.print(shuffler(shuffle, 0, shuffle.length, max));
    }

    public static int shuffler(int[] array, int first, int last, int max) {
        if (first == last) {
            int cur = compute(array);
            if (cur > max) {
                max = cur;
            }
            return max;
        }
        for (int i = first; i < last; i++) {
            swap(array, i, first);
            max = shuffler(array, first + 1, last, max);
            swap(array, first, i);
        }
        return max;
    }

    public static void swap(int[] array, int one, int another) {
        int aux = array[one];
        array[one] = array[another];
        array[another] = aux;
    }

    public static int compute(int[] shuffle) {
        int result = 0;
        for (int i = 0; i < shuffle.length - 1;
             i++) {
            result = result + Math.abs(shuffle[i] -
                    shuffle[i + 1]);
        }
        return result;
    }
}
