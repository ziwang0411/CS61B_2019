package hw2;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static int size;
    public WeightedQuickUnionUF UF;
    private boolean[][] grid;
    private int numOpenSites = 0;
    private int[] dr = {1, 0, -1, 0};
    private int[] dc = {0, 1, 0, -1};

    public Percolation(int N) {             // create N-by-N grid, with all sites initially blocked
        grid = new boolean[N][N];
        UF = new WeightedQuickUnionUF(N * N + N + 1);
        size = N;
        for (int i = 0; i < N; i++) {
            UF.union(xyTo1D(0, i), N * N + N);
        }
        for (int i = 0; i < N; i++) {
            UF.union(xyTo1D(N - 1, i), xyTo1D(N, i));
        }
    }


    public int xyTo1D(int r, int c) {
        return r * size + c;
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0)
            throw new IndexOutOfBoundsException("the row and column indices are integers between 0 and N − 1");
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites += 1;
            if (row == 0) {
                UF.union(xyTo1D(row, col), size * (size + 1));
            }

            if (row == size - 1) {
                UF.union(xyTo1D(row, col), xyTo1D(size, col));
            }
            for (int k = 0; k < 4; ++k) {
                int nr = row + dr[k];
                int nc = col + dc[k];
                if (0 <= nr && nr < size && 0 <= nc && nc < size && isOpen(nr, nc))
                    UF.union(xyTo1D(row, col), xyTo1D(nr, nc));
            }
        }

    }

    // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }// is the site (row, col) open?

    public boolean isFull(int row, int col) {       // is the site (row, col) full?
        validate(row,col);
        if (row ==0) {return isOpen(row, col);}
        else return UF.connected(xyTo1D(row,col), size*(size+1));
    }

    public int numberOfOpenSites() {         // number of open sites
        return numOpenSites;
    }
    public boolean percolates() {       // does the system percolate?
        for (int i = 0; i< size; i++) {
            if (UF.connected(size*(size+1), xyTo1D(size,i))) {
                return true;
            }
        }
        return false;
    }

    public void validate(int row, int col) {
        if (row < 0 || col < 0)
            throw new IndexOutOfBoundsException("the row and column indices are integers between 0 and N − 1");
    }


    public static void main(String[] args) {
//
//        Percolation A = new Percolation(3);
//        assertFalse(A.isOpen(0,1));
//        assertFalse(A.isFull(0,1));
//        A.open(0,0);
//        assertEquals(3, A.xyTo1D(1,0));
//        A.open(1,0);
//        assertTrue(A.isOpen(1,0));
//        assertTrue(A.isOpen(0,0));
//        assertTrue(A.isFull(1,0));
//        assertFalse(A.percolates());
//        A.open(2,0);
//        assertTrue(A.percolates());
//        A.open(2,2);
//        assertFalse(A.isFull(2,2));
//        assertEquals(4,A.numberOfOpenSites());

    }   // use for unit testing (not required, but keep this here for the autograder)
}