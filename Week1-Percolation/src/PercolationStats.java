import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by wangyi1 on 2016/10/26.
 */
public class PercolationStats {
    private double[] experiments;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        experiments = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int count = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                while (p.isOpen(row, col)) {
                    row = StdRandom.uniform(1, n+1);
                    col = StdRandom.uniform(1, n+1);
                }
                p.open(row, col);
                count++;
            }
            experiments[i] = (double) count / (n * n);

        }

    }

    public double mean() {
        return StdStats.mean(experiments);
    }
    public double stddev() {
        return StdStats.stddev(experiments);
    }
    public double confidenceLo() {
        return mean()- 1.96*stddev()/Math.sqrt(experiments.length);
    }
    public double confidenceHi() {
        return mean()+ 1.96*stddev()/Math.sqrt(experiments.length);
    }
    public static void main(String[] args) {
        PercolationStats ps1 = new PercolationStats(200, 100);
        System.out.println("mean = " + ps1.mean());
        System.out.println("stddev = "+ps1.stddev());
        System.out.println("95% confidence interval = " +ps1.confidenceLo() + ", "+ps1.confidenceHi());
        PercolationStats ps2 = new PercolationStats(200, 100);
        System.out.println("mean = " + ps2.mean());
        System.out.println("stddev = "+ps2.stddev());
        System.out.println("95% confidence interval = " +ps2.confidenceLo() + ", "+ps2.confidenceHi());
        PercolationStats ps3 = new PercolationStats(2, 10000);
        System.out.println("mean = " + ps3.mean());
        System.out.println("stddev = "+ps3.stddev());
        System.out.println("95% confidence interval = " +ps3.confidenceLo() + ", "+ps3.confidenceHi());
        PercolationStats ps4 = new PercolationStats(2, 10000);
        System.out.println("mean = " + ps4.mean());
        System.out.println("stddev = "+ps4.stddev());
        System.out.println("95% confidence interval = " +ps4.confidenceLo() + ", "+ps4.confidenceHi());

    }
}
