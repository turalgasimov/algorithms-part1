import java.util.Stack;
import java.util.NoSuchElementException;


public class QueueWithStacks<T> {

    private Stack<T> stackIn;
    private Stack<T> stackOut;

    public QueueWithStacks() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void enqueue(T item) {
        stackIn.push(item);
    }

    private void shiftStacks() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        shiftStacks();
        return stackOut.pop();
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        shiftStacks();
        return stackOut.peek();
    }

    public boolean isEmpty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    public int size() {
        return stackIn.size() + stackOut.size();
    }

    public static void main(String[] args) {
        QueueWithStacks<Integer> q = new QueueWithStacks<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println(q.dequeue()); // Prints 1 (FIFO order)

        q.enqueue(4);

        System.out.println(q.dequeue()); // Prints 2
        System.out.println(q.dequeue()); // Prints 3
        System.out.println(q.dequeue()); // Prints 4
    }

}