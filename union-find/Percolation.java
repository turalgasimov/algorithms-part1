import edu.princeton.cs.algs4.*;


public class Percolation {

    public int n;
    public int[][] grid;
    public int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        int[][] grid = new int[n][n];
        openSites = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0; // 0 represents a blocked site
            }
        }

        this.n = n;
        this.grid = grid;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        try {
            if (row < 1 || row > n || col < 1 || col > n)
                throw new IllegalArgumentException("row and col should be between 1 and n");

            if (!isOpen(row, col)) {
                grid[row-1][col-1] = 1; // 1 represents an open site
                openSites++;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        try {
            if (row < 1 || row > n || col < 1 || col > n)
                throw new IllegalArgumentException("row and col should be between 1 and " + n);

            if (grid[row-1][col-1] == 0)
                return false;
            else 
                return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        try {
            if (row < 1 || row > n || col < 1 || col > n)
                throw new IllegalArgumentException("row and col should be between 1 and " + n);

            while (col > 0) {
                if (isOpen(row, col-1))
                    col--;
                else if (isOpen(row, col+1))
                    col++;
                else if (isOpen(row-1, col))
                    row--;
                else if (isOpen(row+1, col))
                    row++;
                else
                    return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 1; i <= n; i++) {
            if (isFull(n, i))
                return true;
        }
        return false;
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
