package my.algorithm.simple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
*
*
* Correctness test cases Passed 6 out of 8
* Performance test cases Passed 4 out of 7
*
* */

public class GapsAndBudgets {

    public static void main(String[] args) {
        System.out.println(solution("...xxx..x....xxx.", 7));
    }

    public static int solution(String S, int B) {
        if (B == 0) return 0;
        if (S.equals("x") && B > 0) {
            return 1;
        }
        List<Integer> list = new ArrayList<>();
        boolean fragment = false;
        for (char c: S.toCharArray()) {
            if (c == 'x') {
                if (!fragment) {
                    list.add(0);
                }
                list.set(list.size() - 1, list.get(list.size() - 1) + 1);
                fragment = true;
            }
            else fragment = false;
        }
        list.sort(Comparator.reverseOrder());
        int max = 0;
        for (int num : list) {
            B = B - num - 1;
            if (B >= 0) {
                max = max + num;
            } else {
                return max + (num + B);
            }
        }
        return max;
    }

}
