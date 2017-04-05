package my.algorithm.schedule;

public class Solution {

    public static int max = 0;

    public static int counter = 0;

    public static void main(String[] args) {
/*        int[] array = {2, 3, -1, 1, 3};
        System.out.print(solution(array));*/
        //int[] array = {5, -1, 5, 3};
        //System.out.print(compute(array));
/*        int[] array = {-5, 1, 5, 3};
        shuffle(array, 0, 4);
        System.out.println(max);
        System.out.println(counter);*/
        int[] array = {5, 2, 4, 6, 3, 7};
        System.out.println(solution(array));

    }

    public static int solution(int[] a) {
        int left = a[0];
        int right = a[a.length - 1];
        for (int i = 0, j = a.length - 1; i < a.length; i++, j--) {
            if (i >= j - 1) {
                return left + right;
            }
            if (left > a[i]) {
                left = a[i];
            }
            if (right > a[j]) {
                right = a[j];
            }
        }
        return -1;
    }


    public static void shuffle(int[] array, int first, int last) {
        if (first == last) {
            counter++;
            if (compute(array) > max) {
                max = compute(array);
            }
            return;
        }
        for (int i = first; i < last; i++) {
            swap(array, i, first);
            shuffle(array, first + 1, last);
            swap(array, first, i);
        }
    }

    public static void swap(int[] array, int one, int another) {
        int aux = array[one];
        array[one] = array[another];
        array[another] = aux;
    }

    private static void direct() {
        int time = 0;
        int[] shuffle = {5, 3, -1, 5};
        for (int i = 0; i < 1; i++) {
            for (int j = i; j < 2; j++) {
                for (int k = j; k < 3; k++) {
                    int var = shuffle[k];
                    shuffle[k+1] = shuffle[k];
                    shuffle[k] = var;
                    time++;
                }
            }
        }
        System.out.print(time);
    }

    public static int compute(int[] shuffle) {
        int result = 0;
        for (int i = 0; i < shuffle.length - 1; i++) {
            result = result + Math.abs(shuffle[i] - shuffle[i + 1]);
        }
        return result;
    }

 /*   public static int solution(int[] a) {
        int next = a[0];
        for (int i = 0; i < a.length; i++) {
            if (next > a.length - 1) {
                return i + 1;
            } else {
                next = next + a[next];
            }
        }
        return -1;
    }*/

}
