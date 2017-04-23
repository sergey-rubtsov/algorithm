package my.algorithm.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Serg on 13.06.2016.
 */
public class Task {

    public static void main(String[] args) {
        //String test = "a0Ba";
        //System.out.print(new Task().solution(test));
        int a[] = {1, 3, 2, 1};
        int b[] = {2, 4, 5, 3, 2};
        System.out.print(new Task().solution(a, b));
    }

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        LinkedList<Integer> listA = new LinkedList<>();
        LinkedList<Integer> listB = new LinkedList<>();
        for (int i = 0; i < A.length; i++){
            listA.add(new Integer(A[i]));
        }
        for (int i = 0; i < B.length; i++){
            listB.add(new Integer(B[i]));
        }
        Integer minA = listA.peek();
        Integer minB = listB.peek();
        while (!listA.isEmpty() && !listB.isEmpty()) {
            if (minA.equals(minB)) {
                return minA;
            } else if (minA < minB) {
                listA.poll();
                minA = listA.peek();
            } else if (minA > minB) {
                listB.poll();
                minB = listB.peek();
            }
        }
        return -1;
    }

    public int solution(String S) {
        List<String> subs = new ArrayList<>();
        char[] symbols = S.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < symbols.length; i++) {
            char ch = symbols[i];
            if ((ch >= '0' && ch <= '9') || i == (symbols.length - 1)) {
                if (i == (symbols.length - 1)) {
                    builder.append(ch);
                }
                subs.add(builder.toString());
                builder = new StringBuilder();
            } else {
                builder.append(ch);
            }
        }
        int number = 0;
        for (String sub : subs) {
            if (!(sub.equals(sub.toLowerCase())) && sub.length() > 1) {
                if (sub.length() > number) number = sub.length();
            }
        }
        if (number == 0) return -1;
        return number;
    }


}
