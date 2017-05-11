package my.algorithm.simple;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by sergei.rubtcov on 5/11/2017.
 */
public class VerticalTowersDP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int[][] input = new int[T][];
        for (int i = 0; i < input.length; i++) {
            int N = scanner.nextInt();
            input[i] = new int[N];
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = scanner.nextInt();
            }
            System.out.print(String.format("%.02f\n", calculate(input[i])));
        }
    }

    public static float calculate(int[] range) {
        Arrays.sort(range);
        int next;
        int current;
        int value = range.length;
        float n = range.length + 1;
        float result = n / (value + 1);
        for (int i = 1; i < range.length; i++) {
            current = range[i - 1];
            next = range[i];
            if (next != current) {
                value = range.length - i;
            }
            result = result + n / (value + 1);
        }
        return result;
    }

}
