import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int size;

    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        size = 0;
        arr = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // method to resize the array
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++)
            temp[i] = arr[i];

        arr = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null!");

        if (size == arr.length)
            resize(2 * arr.length);

        arr[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty!");

        if (size == arr.length / 4)
            resize(arr.length / 2);

        int randIndex = StdRandom.uniformInt(0, size);
        Item item = arr[randIndex];
        arr[randIndex] = arr[size - 1];
        arr[size - 1] = null;

        size--;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty!");

        int randIndex = StdRandom.uniformInt(0, size);
        return arr[randIndex];
    }

    // return an independent iterator over items in random order
    @SuppressWarnings("unchecked")
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Item[] copyArr = (Item[]) new Object[size];

            {
                for (int i = 0; i < size; i++)
                    copyArr[i] = arr[i];
                StdRandom.shuffle(copyArr);
            }

            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException("No more elements!");
                return copyArr[current++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove() is not supported!");
            }

        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("--- Starting RandomizedQueue Tests ---");
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        // Test 1: Empty state tracking
        System.out.println("IsEmpty (expected true): " + rq.isEmpty());
        System.out.println("Size (expected 0): " + rq.size());

        // Test 2: Sequential Enqueue (forces internal dynamic resizing)
        System.out.println("\n--- Testing Enqueue ---");
        for (int i = 1; i <= 10; i++) {
            rq.enqueue(i);
        }
        System.out.println("Size after 10 enqueues (expected 10): " + rq.size());
        System.out.println("IsEmpty (expected false): " + rq.isEmpty());

        // Test 3: Nondestructive Sampling
        System.out.println("\n--- Testing Sample (Should output random entries, size remains 10) ---");
        System.out.println("Sample 1: " + rq.sample());
        System.out.println("Sample 2: " + rq.sample());
        System.out.println("Sample 3: " + rq.sample());
        System.out.println("Current Size (expected 10): " + rq.size());

        // Test 4: Dual Iterator Mutual Independence Verification
        System.out.println("\n--- Testing Iterators (Should print different random sequences) ---");
        System.out.print("Iterator 1 sequence: ");
        for (int item : rq) {
            System.out.print(item + " ");
        }
        System.out.println();

        System.out.print("Iterator 2 sequence: ");
        for (int item : rq) {
            System.out.print(item + " ");
        }
        System.out.println();

        // Test 5: Destructive random drain
        System.out.println("\n--- Testing Dequeue (Should remove uniformly at random) ---");
        int initialSize = rq.size();
        for (int i = 0; i < initialSize; i++) {
            System.out.print(rq.dequeue() + " ");
        }
        System.out.println();
        System.out.println("Size after draining (expected 0): " + rq.size());
        System.out.println("IsEmpty (expected true): " + rq.isEmpty());

        // Test 6: API Spec Defensive Exception Checking
        System.out.println("\n--- Testing Corner Case Exceptions ---");
        try {
            rq.enqueue(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected IllegalArgumentException on null enqueue.");
        }

        try {
            rq.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected NoSuchElementException on empty dequeue.");
        }

        try {
            rq.sample();
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected NoSuchElementException on empty sample.");
        }

        System.out.println("\n--- All Tests Executed Successfully ---");
    }

}