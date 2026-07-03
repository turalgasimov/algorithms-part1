import java.util.EmptyStackException;
import java.util.Stack;

public class StackMaxing {

    private Stack<Double> mainStack;
    private Stack<Double> maxStack;

    public StackMaxing() {
        mainStack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(double value) {
        mainStack.push(value);
        if (maxStack.isEmpty())
            maxStack.push(value);
        else {
            double currentMax = maxStack.peek();
            maxStack.push(value > currentMax ? value : currentMax);
        }
    }

    public double pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        maxStack.pop();
        return mainStack.pop();
    }

    public double getMax() {
        return maxStack.peek();
    }

    public double peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return mainStack.peek();
    }

    public boolean isEmpty() {
        return mainStack.isEmpty();
    }

    public static void main(String[] args) {
        StackMaxing stack = new StackMaxing();
        stack.push(3.0);
        stack.push(5.0);
        stack.push(9.0);
        System.out.println(stack.getMax());
        stack.pop();
        stack.push(2.0);
        stack.push(1.0);
        System.out.println(stack.getMax());
        stack.pop();
        System.out.println(stack.getMax());
        stack.pop();
        System.out.println(stack.getMax());
        stack.pop();
        System.out.println(stack.getMax());
    }

}