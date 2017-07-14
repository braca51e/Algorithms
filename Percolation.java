import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation{
	
	private int n;
	private boolean[] openSites;
	private int openCount = 0;
	private int virtualTop;
	private int virtualBot;
	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF ufFull;
	
	private int to1DIndex(int row, int col){
		return (row-1)*this.n+(col); 
	}
	
	private boolean indexCheck(int row, int col){
		boolean ret_val = true;
		if (row < 1 || row > this.n || col < 1 || col > this.n){
			ret_val = false;
			throw new IllegalArgumentException("row, col must be > 1 and < n");
		}
		return ret_val;
	}
	
	public Percolation(int n){
		
		if (n <= 0){
			throw new IllegalArgumentException("n must be > 0");
		}
		this.n = n;
		this.openSites = new boolean[n*n+2];
		this.uf = new WeightedQuickUnionUF(n*n+2);
		this.ufFull = new WeightedQuickUnionUF(n*n+2);
		this.virtualTop = 0;
		this.virtualBot = n*n+1;
		//open virtual top and bottom
		this.openSites[virtualTop] = true;
		this.openSites[virtualBot] = true;
		//O blocked 1 Open
		for(int i = 1; i <= n; i++){
			//top row
			int row = 1;
			int index = to1DIndex(row, i);
			this.uf.union(virtualTop, index);
			this.ufFull.union(virtualTop, index);
			//bot row
			row = this.n;
			index = to1DIndex(row, i);
			this.uf.union(virtualBot, index);
		}
	}
		
	public void open(int row, int col){
		
		if(indexCheck(row, col) && !isOpen(row, col)){
		
			int index = to1DIndex(row, col);
			
		    this.openSites[index] = true;
		
		    if(row>1 && isOpen(row - 1, col)){
		    	int index2 = to1DIndex(row - 1, col);
			    this.uf.union(index, index2);
			    this.ufFull.union(index, index2);
		    }
		    if(row<this.n && isOpen(row + 1, col)){
		    	int index2 = to1DIndex(row + 1, col);
			    this.uf.union(index, index2);
			    this.ufFull.union(index, index2);
		    }
		    if(col>1 && isOpen(row, col - 1)){
		    	int index2 = to1DIndex(row, col - 1);
			    this.uf.union(index, index2);
			    this.ufFull.union(index, index2);
		    }
		    if(col<this.n && isOpen(row, col + 1)){
		    	int index2 = to1DIndex(row, col + 1);
			    this.uf.union(index, index2);
			    this.ufFull.union(index, index2);
		    }
		
		    this.openCount += 1;
		}
	}
	
	public boolean isOpen(int row, int col){
		
		boolean ret_val = false;
		
		if(indexCheck(row, col)){
            int index = to1DIndex(row, col);
            ret_val = this.openSites[index];
		}
		
		return ret_val;
	}

	public boolean isFull(int row, int col){
		
		boolean ret_val = false;
		
		if(indexCheck(row, col)){
			int index = to1DIndex(row, col);
			ret_val = this.ufFull.connected(this.virtualTop, index) && isOpen(row, col);
		}
		
		return ret_val;	
	}
	
	public int numberOfOpenSites(){
		
		return this.openCount;
		
	}
	
	public boolean percolates(){
		boolean ret_val = false;
		
		if(this.n>1){
			ret_val = this.uf.connected(this.virtualTop, this.virtualBot);
		}
		else{
			ret_val = this.openSites[to1DIndex(1,1)];
		}
		
		return ret_val;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 20;
		Percolation per = new Percolation(N);
		
		while(!per.percolates()){
			int row = StdRandom.uniform(1, N+1);
			int col = StdRandom.uniform(1, N+1);
			System.out.println("Opening: " + row + ", " + col);
			per.open(row, col);
		}
		System.out.println("It percolates!");
		System.out.println("Open Sites: " + per.numberOfOpenSites());
	}
}
