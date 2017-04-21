package my.algorithm.schedule;

import java.util.*;

public class TreePattern {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = n + 1; i - 1 > 0; i--) {
            for (int j = 0; j < n; j++) {
                if (i - 2 > j) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
}
