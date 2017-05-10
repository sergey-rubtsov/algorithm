package my.algorithm.simple;

import java.util.Scanner;

/**
 * Created by sergei.rubtcov on 5/10/2017.
 */
public class EqualDP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int N = scanner.nextInt();
        int[] colleagues = new int[N];
        for (int i = 0; i < N; i++) {
            colleagues[i] = scanner.nextInt();
        }
        for (int i = 0; i < N; i++) {
            System.out.println(colleagues[i]);
        }
    }

}
