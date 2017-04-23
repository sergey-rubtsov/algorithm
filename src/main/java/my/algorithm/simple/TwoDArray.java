package my.algorithm.simple;

import java.util.Scanner;

/**
 * Created by Serg on 06.02.2017.
 * How to rotate 2D array
 */
public class TwoDArray {

/*    3 5
            13 4 8 14 1
            9 6 3 7 21
            5 12 17 9 3*/

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        int a[][] = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            arr = line.split(" ");
            for (int j = 0; j < M; j++) {
                a[i][j] = Integer.parseInt(arr[j]);
            }
        }
        a = rotate(a);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    protected static int[][] rotate(int[][] input) {
        int[][] rotated = new int[input[0].length][input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                rotated[j][i] = input[i][j];
            }
        }
        return rotated;
    }

    protected static int[][] mirror(int[][] input) {
        int[][] result = new int[input[0].length][input.length];
        for (int i = 0, k = result.length - 1; i < result.length; i++, k--) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = input[k][j];
            }
        }
        return result;
    }

}
