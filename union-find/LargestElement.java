import edu.princeton.cs.algs4.*;

public class LargestElement {

    static int[] list = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    static int[] id = { 7, 8, 8, 5, 5, 5, 8, 7, 8 };

    static int id1 = 8;
    static int id2 = 5;
    static int id3 = 7;

    static int[] comp1 = { 1, 2, 6, 8 };
    static int[] comp2 = { 3, 4, 5 };
    static int[] comp3 = { 0, 7 };

    /**
     * Returns the largest element in the connected component
     * 
     * @param i the element that is a part of the connected component
     * @return the largest element in the connected component
     */
    private static int find(int i) {
        int max = i;
        for (int j : list) {
            if (id[j] == id[i] && j > max) {
                max = j;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int i = StdIn.readInt();
        System.out.println(find(i));
    }

}
