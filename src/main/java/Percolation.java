import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Lamuel on 2/3/2016.
 */
public class Percolation {
    private WeightedQuickUnionUF cells;
    private int side;
    private boolean[] states;
    private int nCells;

    private int topIndex;    // Index of extra site modelling top of grid
    private int bottomIndex; // Index of extra site modelling bottom of grid

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        side = N;
        nCells = side * side;
        topIndex = nCells;
        bottomIndex = nCells+1;
        cells = new WeightedQuickUnionUF(nCells + 2);
        states = new boolean[nCells + 2];
        for (int i=0; i < nCells; i++) {
            states[i] = false;
        }
        states[topIndex]    = true;
        states[bottomIndex] = true;
    }

    private void checkRange(int i, int j){
        if (i<0||j<0||i>side||j>side)
            throw new IndexOutOfBoundsException(String.format("row index [%d] must be between 0 and %d", i, (side)));
    }

    private int getCellIndex(int i, int j){
        return (side*(i))+j;
    }

    private void union(int i, int j){
        if (!cells.connected(i, j)){
            cells.union(i, j);
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        checkRange(i,j);

        int cell=getCellIndex(i,j);

        states[cell]=true;

        //if not top row
        if(i!=0 && isOpen(i,j)){
            union(getCellIndex(i-1,j),cell);
        }else if(i==1){
            //connect to virtual top cell
            union(cell,topIndex);
        }
        //if not bottom row
        if(i!=side && isOpen(i+1,j)){
            union(getCellIndex(i+1,j),cell);
        }else if (i==side){
            //connect to virtual bottom cell
            union(cell,bottomIndex);
        }
        //if not left border
        if(j!=1 && isOpen(i,j-1)){
            union(getCellIndex(i,j-1),cell);
        }
        //if not right border
        if(j!=side && isOpen(i,j+1)){
            union(getCellIndex(i,j+1),cell);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j)  {
        checkRange(i, j);
        return cells.connected(nCells, getCellIndex(i, j));
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkRange(i, j);
        return states[getCellIndex(i, j)];
    }

    // does the system percolate?
    public boolean percolates() {
        return cells.connected(topIndex, bottomIndex);
    }

    // test client (optional)
    public static void main(String[] args) {
//        Percolation per =  new Percolation(10);
//        System.out.printf("\nDoes the system percolate : %s", per.percolates());

        int[][] percolatingGrid = {
                {0,1,1,0,0,0,1,1,1},
                {0,0,1,0,0,0,0,0,1},
                {0,0,1,1,1,0,0,0,0},
                {0,0,0,0,1,1,1,1,1},
                {0,0,0,0,1,0,0,0,1},
                {0,1,1,1,1,0,0,0,1},
                {0,1,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,1}
        };

        int[][] nonPercolatingGrid = {
                {0,1,1,0,1,1,1,1,0},
                {0,0,1,0,0,0,0,1,0},
                {0,1,0,1,1,0,1,0,0},
                {1,1,0,0,1,1,1,0,0},
                {1,0,1,1,1,1,1,0,1},
                {1,1,1,1,1,0,0,0,1},
                {0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,0,1},
                {1,0,1,0,1,0,1,0,1}
        };

        Percolation yesP = new Percolation(9);
        Percolation noP = new Percolation(9);

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if (percolatingGrid[i][j] == 1) {
                    yesP.open(i, j);
                }
                if (nonPercolatingGrid[i][j] == 1) {
                    noP.open(i,j);
                }

            }
        }
        System.out.println(String.format("\nYesP %s, NoP %s", yesP.percolates(), noP.percolates()));

    }
}
