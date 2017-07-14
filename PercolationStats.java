import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int T;
	private double[] openCount;
	
	public PercolationStats(int n, int trials){
		if(n <= 0 || trials <= 0){
			throw new IllegalArgumentException("n, trial must be > 0");
		}
		
		this.T = trials;
		this.openCount = new double[trials];
		
		for(int i = 0; i < trials; i++){
			Percolation per = new Percolation(n);
			while(!per.percolates()){
				int row = StdRandom.uniform(1, n+1);
				int col = StdRandom.uniform(1, n+1);
				per.open(row, col);
			}
			this.openCount[i] = (double)per.numberOfOpenSites()/(n*n);
		}
	}
	
	public double mean(){
		return StdStats.mean(this.openCount);
	}
	
	public double stddev(){
		return StdStats.stddev(this.openCount);
	}
	
	public double confidenceLo(){
		double mean = this.mean();
		double stddev = this.stddev();
		return mean - 1.96*stddev/Math.sqrt(this.T);
	}
	
	public double confidenceHi(){
		double mean = this.mean();
		double stddev = this.stddev();
		return mean + 1.96*stddev/Math.sqrt(this.T);
	}
	
	public static void main(String[] args){
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		
		PercolationStats test = new PercolationStats(n, trials);
		
		System.out.println("mean = " + test.mean());
		System.out.println("stddev = " + test.stddev());
		System.out.println("95% confidence interval = " + "[" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
	}
}
