import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation{
	
	private int n;
	private int[][] grid;
	private int openSites = 0;
	
	private WeightedQuickUnionUF uf;
	
	public Percolation(int n){
		
		if (n <= 0){
			throw new IllegalArgumentException("n must be > 0");
		}
		
		this.n = n;
		this.grid = new int[n][n];
		this.uf = new WeightedQuickUnionUF(n);
		
		//O blocked 1 Open
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j ++){
				this.grid[i][j] = 0;
			} 
		}
	}
	
	public void open(int row, int col){
		
		if (row < 1 || row > this.n || col < 1 || col > this.n){
			throw new IllegalArgumentException("row, col must be > 1 and < n");
		}
		
		this.grid[row][col] = 1;
		
		if(row - 1 >= 0){
			this.uf.union(row, col);
		}
		if(row + 1 <= this.n){
			this.uf.union(row, col);
		}
		if(col - 1 >= 0){
			this.uf.union(row, col);
		}
		if(col + 1 <= this.n){
			this.uf.union(row, col);
		}

		this.openSites += 1;

	}
	
	public boolean isOpen(int row, int col){
		
		if (row < 1 || row > this.n || col < 1 || col > this.n){
			throw new IllegalArgumentException("row, col must be > 1 and < n");
		}
		
		boolean ret_val = false;
		
		if (this.grid[row][col] == 1){
			ret_val = true;
		}
		
		return ret_val;
		
	}

	public boolean isFull(int row, int col){
		
		if (row < 1 || row > this.n || col < 1 || col > this.n){
			throw new IllegalArgumentException("row, col must be > 1 and < n");
		}
		
		return true;
		
	}
	
	public int numberOfOpenSites(){
		
		return this.openSites;
		
	}
	
	public boolean percolates(){
		return true;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Hello world!");
	}

}
