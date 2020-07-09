package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fractions;
    private int numTrials;
    public PercolationStats(int N, int T, PercolationFactory pf)  {// perform T independent experiments on an N-by-N grid
        if (N<=0 || T<=0) throw new IllegalArgumentException("N<=0 or T<=0");
        fractions = new double[T];
        numTrials = T;
        for (int i =0; i < T; i++) {
            int numOpened = 0;
            Percolation p =pf.make(N);
            while (!p.percolates()) {
                int r = StdRandom.uniform(N);
                int c = StdRandom.uniform(N);
                if (!p.isOpen(r,c)) {
                    p.open(r,c);
                    numOpened +=1;
                }
            }
            fractions[i] = (double) numOpened / (N*N);
        }
    }

    public double mean() {
        return StdStats.mean(fractions);
    }                                          // sample mean of percolation threshold
    public double stddev()     {return StdStats.stddev(fractions);}                                    // sample standard deviation of percolation threshold
    public double confidenceLow()   {
        double mu = mean();
        double sigma = stddev();
        return mu-1.96*sigma / Math.sqrt(numTrials);
    }                               // low endpoint of 95% confidence interval
    public double confidenceHigh()     {
        double mu = mean();
        double sigma = stddev();
        return mu+1.96*sigma / Math.sqrt(numTrials);
    }                            // high endpoint of 95% confidence interval
    public static void main(String[] args) {

        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps1 = new PercolationStats(20, 200, pf);
        System.out.println("For 20*20 grid, 200 times, the mean is " + ps1.mean() +", the stddev is " + ps1.stddev() + ", the confidence low is "+ ps1.confidenceLow() +", the confidence high is "+ ps1.confidenceHigh() );
        PercolationStats ps2 = new PercolationStats(20, 400, pf);
        System.out.println("For 20*20 grid, 400 times, the mean is " + ps2.mean() +", the stddev is " + ps2.stddev() + ", the confidence low is "+ ps2.confidenceLow() +", the confidence high is "+ ps2.confidenceHigh() );

    }
}
