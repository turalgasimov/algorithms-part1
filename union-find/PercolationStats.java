import edu.princeton.cs.algs4.*;


public class PercolationStats {

    int n;
    int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        try {
            if (n <= 0 || trials <= 0) {
                throw new IllegalArgumentException("n and trials should be greater than 0");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // sample mean of percolation threshold
    public double mean()

    // sample standard deviation of percolation threshold
    public double stddev()

    // low endpoint of 95% confidence interval
    public double confidenceLo()

    // high endpoint of 95% confidence interval
    public double confidenceHi()

    // test client (see below)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int T = StdIn.readInt();

        double sampleMean = mean();
        double sampleStdDev = stddev();
        double sampleConfLo = confidenceLo();
        double sampleConfHi = confidenceHi();
    }

}