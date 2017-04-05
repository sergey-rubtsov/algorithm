package my.algorithm.schedule;

public class TestClass {

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

    public static void process(int count, int[] input) {
        int[] output = new int[input.length];
        StackArray deque = new StackArray(count);
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
        for (int tower : output) {
            System.out.print(tower + " ");
        }
    }

    private static void increaseRange(StackArray stack, int[] output) {
        for (int i = 0; i < stack.getTop() + 1; i++) {
            int order = stack.getStack()[i][1];
            output[order] = output[order] + 1;
        }
    }

    public static void main(String[] args) {
/*        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            int count = scanner.nextInt();
            int[] range = new int[count];
            for (int j = 0; j < count; j++) {
                range[j] = scanner.nextInt();
            }*/
            int[] range = {100, 80, 60, 70, 60, 75, 85};
            new TestClass().process(7, range);
            System.out.println("");
/*        }*/
    }
}
