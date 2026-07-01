import edu.princeton.cs.algs4.*;


public class Percolation {

    private final int n;
    private final boolean[] openSites;
    private final int virtualTop;
    private final int virtualBottom;
    private int openSitesCount;

    // union-find objects
    private final WeightedQuickUnionUF ufPercolation;
    private final WeightedQuickUnionUF ufFullness;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        this.openSites = new boolean[n * n + 1];
        this.openSitesCount = 0;

        this.virtualTop = 0;
        this.virtualBottom = n * n + 1;

        this.ufPercolation = new WeightedQuickUnionUF(n * n + 2);
        this.ufFullness = new WeightedQuickUnionUF(n * n + 1); // No virtual bottom
    }

    // Maps 2D (row, col) coordinates to a 1D array index
    private int xyTo1D(int row, int col) {
        return (row - 1) * n + col;
    }

    // Validates that (row, col) are within the prescribed bounds
    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Row and col must be between 1 and " + n);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        
        if (isOpen(row, col)) {
            return;
        }

        int index = xyTo1D(row, col);
        openSites[index] = true;
        openSitesCount++;

        // Connect to virtual top if in the first row
        if (row == 1) {
            ufPercolation.union(index, virtualTop);
            ufFullness.union(index, virtualTop);
        }
        
        // Connect to virtual bottom if in the last row
        if (row == n) {
            ufPercolation.union(index, virtualBottom);
        }

        // Connect to open neighbors (Up, Down, Left, Right)
        if (row > 1 && isOpen(row - 1, col)) {
            int up = xyTo1D(row - 1, col);
            ufPercolation.union(index, up);
            ufFullness.union(index, up);
        }
        if (row < n && isOpen(row + 1, col)) {
            int down = xyTo1D(row + 1, col);
            ufPercolation.union(index, down);
            ufFullness.union(index, down);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            int left = xyTo1D(row, col - 1);
            ufPercolation.union(index, left);
            ufFullness.union(index, left);
        }
        if (col < n && isOpen(row, col + 1)) {
            int right = xyTo1D(row, col + 1);
            ufPercolation.union(index, right);
            ufFullness.union(index, right);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[xyTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        // We check fullness using the UF object that lacks a virtual bottom
        return ufFullness.find(virtualTop) == ufFullness.find(xyTo1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufPercolation.find(virtualTop) == ufPercolation.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);

        while (!perc.percolates()) {
            int row = StdRandom.uniformInt(1, n + 1);
            int col = StdRandom.uniformInt(1, n + 1);
            perc.open(row, col);
        }

        if (perc.percolates())
            StdOut.println("The system percolates with threshold: " + (double) perc.numberOfOpenSites() / (n * n));
    }    

}
