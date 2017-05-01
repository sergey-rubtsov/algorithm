package my.algorithm.simple;

/**
 * Created by Serg on 29.04.2017.
 * Compute number of 1 in binary number
 */
public class BinaryNumbers {

    public static void main(String[] args) {
        int n = 128959;
        int ones = 0;
        int max = 0;
        while (n > 0) {
            if (n % 2 == 1) {
                System.out.print(1);
                ones++;
                if (max < ones) {
                    max = ones;
                }
            } else {
                ones = 0;
                System.out.print(0);
            }
            n = n / 2;
        }
        System.out.println("\n" + max);
    }
}
