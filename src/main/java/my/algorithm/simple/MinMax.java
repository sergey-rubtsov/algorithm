package my.algorithm.simple;

import java.util.*;

/**
 * Created by Serg on 23.04.2017.
 */
public class MinMax {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long d = in.nextLong();
        long e = in.nextLong();
        long sum = a + b + c + d + e;
        long[] nums = {a, b, c, d, e};
        long max = sum - a;
        long min = sum - e;
        for (int i = 0; i < nums.length; i++) {
            long result = sum - nums[i];
            if (result < min) {
                min = result;
            } else if (result > max) {
                max = result;
            }
        }
        System.out.print(min + " " + max);
    }
}

