package my.algorithm.schedule;

/**
 * Created by sergei.rubtcov on 2/6/2017.
 */
public class Second {

    public static void main(String[] args) {
        Second test = new Second();
        System.out.println(test.solution(-1, 5, 5, 3));
    }

    public int solution(int A, int B, int C, int D) {
        int[] shuffle = {A, B, C, D};
        int max = compute(shuffle);
        return shuffler(shuffle, 0, 4, max);
    }

    public int shuffler(int[] array, int first, int last, int max) {
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

    public int compute(int[] shuffle) {
        System.out.println(shuffle[0] + " " + shuffle[1] + " " + shuffle[2] + " " + shuffle[3]);
        int result = 0;
        for (int i = 0; i < shuffle.length - 1; i++) {
            result = result + Math.abs(shuffle[i] - shuffle[i + 1]);
        }
        return result;
    }
}
