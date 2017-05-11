package my.algorithm.simple;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by sergei.rubtcov on 5/11/2017.
 */
public class VerticalTowersDP {

    public static class StackArray {
        private int top;
        private int stack[][];

        public StackArray(int length) {
            this.top = -1;
            this.stack = new int[length][2];
        }

        public void push(int data, int order) {
            top = top + 1;
            stack[top][0] = data;
            stack[top][1] = order;
        }

        public int pop() {
            int result = stack[top][0];
            top = top - 1;
            return result;
        }

        public boolean isEmpty() {
            if (top == -1)
                return true;
            else
                return false;
        }

        public int peek() {
            return stack[top][0];
        }

        public int[][] getStack() {
            return stack;
        }

        public int getTop() {
            return top;
        }
    }

    public static int process(int[] input) {
        int[] output = new int[input.length];
        StackArray deque = new StackArray(input.length - 1);
        deque.push(input[input.length - 1], input.length - 1);
        output[input.length - 1] = 1;
        for (int i = input.length - 2; i >= 0; i--) {
            int current = input[i];
            while (!deque.isEmpty() && current > deque.peek()) {
                deque.pop();
            }
            deque.push(current, i);
            increaseRange(deque, output);
        }
        int result = 0;
        for (int range : output) {
            result = result + range;
        }
        return result;
    }

    private static void increaseRange(StackArray stack, int[] output) {
        for (int i = 0; i < stack.getTop() + 1; i++) {
            int order = stack.getStack()[i][1];
            output[order] = output[order] + 1;
        }
    }

    //{3, 2, 5, 3, 3, 4, 1, 2}
    /*
    6
    3
    1 2 3
    3
    3 3 3
    3
    2 2 3
    4
    10 2 4 4
    5
    10 10 10 5 10
    6
    1 2 3 4 5 6
    */

    /*        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int[][] input = new int[T][];
        for (int i = 0; i < input.length; i++) {
            int N = scanner.nextInt();
            input[i] = new int[N];
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = scanner.nextInt();
            }
            System.out.println(average(input[i]));
        }*/

    public static void main(String[] args) {
        int[] sample = {3, 2, 5, 3, 3, 4, 1, 2};
/*        compute(sample);
        for (int i = 0; i < sample.length; i++) {
            System.out.print(sample[i]);
        }*/
        System.out.println(average(sample));
    }

    private static int[] compute(int[] range) {
        Arrays.sort(range);
        int[] aux = new int[range.length];
        int next;
        int current;
        int value = range.length - 1;
        aux[0] = range.length;
        for (int i = 1; i < range.length; i++) {
            current = range[i - 1];
            next = range[i];
            if (next != current) {
                value = range.length - i;
            }
            aux[i] = value;
        }
        return aux;
    }



    //we can calculate average by formula (n+1)/(k+1), where k is the number of elements, with a greater or equal height:
    public static float average(int[] range) {
        int[] array = compute(range);
        float n = array.length + 1;
        float result = 0;
        for (int i = 0; i < array.length; i++) {
            int k = array[i] + 1;
            result = result + n / (k + 1);
        }
        return result;
    }



}
