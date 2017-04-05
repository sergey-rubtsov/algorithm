package my.algorithm.schedule;

public class StackArray {

    private int top;
    private int stack[];

    public StackArray(int length) {
        this.top = -1;
        this.stack = new int[length + 1];
    }

    public boolean empty() {
        if (top == -1)
            return true;
        else
            return false;
    }

    public void push(int data) {
        top = top + 1;
        stack[top] = data;
    }

    public int pop() {
        int result = stack[top];
        top = top - 1;
        return result;
    }

    public int peek() {
        return stack[top];
    }

}
