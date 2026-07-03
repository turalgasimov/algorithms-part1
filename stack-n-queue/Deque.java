import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(T item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null!");

        Node<T> newFirst = new Node<>();

        newFirst.item = item;
        newFirst.next = first;
        newFirst.prev = null;

        if (isEmpty())
            last = newFirst;

        this.size++;

        if (newFirst.next != null)
            first.prev = newFirst;

        first = newFirst;
    }

    // add the item to the back
    public void addLast(T item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null!");

        Node<T> newLast = new Node<>();

        newLast.item = item;
        newLast.next = null;
        newLast.prev = last;

        if (isEmpty())
            first = newLast;

        this.size++;

        if (newLast.prev != null)
            last.next = newLast;

        last = newLast;
    }

    // remove and return the item from the front
    public T removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");

        Node<T> newFirst = first.next;
        T item = first.item;

        first.item = null;
        first.next = null;

        this.size--;
        if (isEmpty())
            last = first = null;
        else {
            first = newFirst;
            newFirst.prev = null;
        }

        return item;
    }

    // remove and return the item from the back
    public T removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");

        Node<T> newLast = last.prev;
        T item = last.item;

        last.item = null;
        last.prev = null;

        this.size--;
        if (isEmpty())
            first = last = null;
        else {
            last = newLast;
            newLast.next = null;
        }

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> curr = first;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException("End of deque!");
                
                T item = curr.item;
                curr = curr.next;
                return item;

            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Deque Tests ---");
        Deque<String> deque = new Deque<>();

        // Test 1: Empty state verification
        System.out.println("Is empty (expected true): " + deque.isEmpty());
        System.out.println("Size (expected 0): " + deque.size());

        // Test 2: Adding elements to front and back
        System.out.println("\n--- Testing Add Operations ---");
        deque.addFirst("B"); // Deque: [B]
        deque.addFirst("A"); // Deque: [A, B]
        deque.addLast("C"); // Deque: [A, B, C]
        deque.addLast("D"); // Deque: [A, B, C, D]

        System.out.println("Is empty (expected false): " + deque.isEmpty());
        System.out.println("Size (expected 4): " + deque.size());

        // Test 3: Testing Iterator (For-each loop)
        System.out.println("\n--- Testing Iterator (Should print A -> B -> C -> D) ---");
        for (String item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();

        // Test 4: Testing Removals
        System.out.println("\n--- Testing Remove Operations ---");
        System.out.println("Removed First (expected A): " + deque.removeFirst()); // Deque: [B, C, D]
        System.out.println("Removed Last (expected D): " + deque.removeLast()); // Deque: [B, C]
        System.out.println("Current Size (expected 2): " + deque.size());

        // Print remaining elements to ensure integrity
        System.out.print("Remaining items (expected B C): ");
        for (String item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();

        // Empty the deque fully
        System.out.println("Removed First (expected B): " + deque.removeFirst()); // Deque: [C]
        System.out.println("Removed Last (expected C): " + deque.removeLast()); // Deque: []
        System.out.println("Is empty after clearing (expected true): " + deque.isEmpty());

        // Test 5: Corner Case - Exception Handling
        System.out.println("\n--- Testing Corner Case Exceptions ---");

        // 5a. IllegalArgumentException on null add
        try {
            deque.addFirst(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Successfully caught IllegalArgumentException on null add: " + e.getMessage());
        }

        // 5b. NoSuchElementException on empty remove
        try {
            deque.removeFirst();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Successfully caught NoSuchElementException on empty remove: " + e.getMessage());
        }

        System.out.println("\n--- All Tests Executed Successfully ---");
    }

}
