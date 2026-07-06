import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        // Corner case: If k is 0, we don't need to process or store anything
        if (k == 0) {
            return;
        }

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int count = 0;

        // Stream elements one by one from standard input
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            count++;

            // If we haven't reached k items yet, always keep the item
            if (count <= k) {
                rq.enqueue(item);
            } else {
                // For the i-th item, pick a random integer between 0 and i-1
                int r = StdRandom.uniformInt(count);

                // It has a k/count probability of replacing an existing item
                if (r < k) {
                    rq.dequeue(); // Evict a random item to maintain size k
                    rq.enqueue(item); // Add the new item into the reservoir
                }
            }
        }

        // Print out the final perfectly uniform sample of size k
        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue());
        }
    }
}