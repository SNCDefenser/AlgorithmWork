

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * Created by wangyi1 on 2016/10/22.
 */
public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;


    public Percolation(int n)  {
        if (n <= 0) throw new IllegalArgumentException();
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);

    }               // create n-by-n grid, with all sites blocked

    public void open(int row, int col)  {
        if (row > grid.length ||  row < 1 || col > grid.length || col < 1) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (row == 1) {
            uf.union(xyToD(row, col), 0);
            uf2.union(xyToD(row, col), 0);
        }
        if (row == grid.length) {
            uf.union(xyToD(row, col), xyToD(grid.length, grid.length)+1);
        }
        if (row >= 2 && isOpen(row-1, col)) {
            uf.union(xyToD(row, col), xyToD(row-1, col));
            uf2.union(xyToD(row, col), xyToD(row-1, col));
        }
        if (row + 1 <= grid.length && isOpen(row+1, col)) {
            uf.union(xyToD(row, col),  xyToD(row+1, col));
            uf2.union(xyToD(row, col),  xyToD(row+1, col));
        }
        if (col >= 2 && isOpen(row, col-1)) {
            uf.union(xyToD(row, col), xyToD(row, col-1));
            uf2.union(xyToD(row, col), xyToD(row, col-1));
        }
        if (col + 1 <= grid.length && isOpen(row, col+1)) {
            uf.union(xyToD(row, col), xyToD(row, col+1));
            uf2.union(xyToD(row, col), xyToD(row, col+1));
        }
        grid[row-1][col-1] = true;

    }        // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col)    {
        if (row > grid.length ||  row < 1 || col > grid.length || col < 1) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }

        return grid[row-1][col-1];
    }  // is site (row, col) open?

    public boolean isFull(int row, int col) {
        if (row > grid.length || row < 1)throw new IndexOutOfBoundsException("row index i out of bounds");
        if (col > grid.length || col < 1)throw new IndexOutOfBoundsException("row index i out of bounds");
        return uf2.connected(xyToD(row, col), 0);
    } // is site (row, col) full?

    public boolean percolates() {
        return uf.connected(0, xyToD(grid.length, grid.length) + 1);
    }

    private int xyToD(int row, int col) {
        return (grid.length) * (row-1) + col;
    }
}
