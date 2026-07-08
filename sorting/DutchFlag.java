import java.util.Arrays;

public class DutchFlag {

    public enum Color {
        RED, WHITE, BLUE
    }

    private final Color[] buckets;

    public DutchFlag(Color[] buckets) {
        if (buckets == null) {
            throw new IllegalArgumentException("Buckets array cannot be null");
        }
        this.buckets = buckets;
    }

    private Color color(int i) {
        return buckets[i];
    }

    private void swap(int i, int j) {
        Color temp = buckets[i];
        buckets[i] = buckets[j];
        buckets[j] = temp;
    }

    public void sort() {
        int n = buckets.length;
        int low = 0;
        int mid = 0;
        int high = n - 1;

        while (mid <= high) {
            Color currentColor = color(mid);

            if (currentColor == Color.RED) {
                swap(mid, low);
                low++;
                mid++;
            } else if (currentColor == Color.WHITE) {
                mid++;
            } else if (currentColor == Color.BLUE) {
                swap(mid, high);
                high--;
            }
        }
    }

    public static void main(String[] args) {
        Color[] pebbles = {
                Color.BLUE, Color.WHITE, Color.RED, Color.BLUE,
                Color.WHITE, Color.RED, Color.WHITE, Color.RED
        };

        System.out.println("Before sorting:");
        System.out.println(Arrays.toString(pebbles));
        
        DutchFlag flag = new DutchFlag(pebbles);
        flag.sort();

        System.out.println("\nAfter sorting:");
        System.out.println(Arrays.toString(pebbles));
    }
}