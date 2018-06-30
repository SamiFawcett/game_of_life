import java.util.List;
import java.util.Scanner;
import java.awt.event.KeyEvent;
public class Simulate
{
    private Grid old_board;
    private Grid new_board; 
    public Simulate(int rows, int cols, String fileName, boolean generate_cells){
        this.run(rows, cols, fileName, generate_cells);
    }

    public void updateNewBoard(){
        //fewer than 2 (under population), more than 3 (over population), dead cell with EXACTLY 3 live neighbors is alive due to reproduction
        /*
         * [c0][c1][c2]
         * [c7][CV][c3]
         * [c6][c5][c4]
         */
        boolean[][] old_boardGridValues = old_board.getGridValues();
        int iter = 0;
        for(int i = 0; i < old_board.rows; i++){
            for(int j = 0; j < old_board.cols; j++){
                if(i == 0 || i == old_board.cols - 1 || j == 0 || j == old_board.rows - 1){

                } else {
                    boolean[] neighboringCells = new boolean[8];
                    boolean CV = old_boardGridValues[i][j];
                    neighboringCells[0] = old_boardGridValues[i - 1][j + 1];
                    neighboringCells[1] = old_boardGridValues[i][j + 1];
                    neighboringCells[2] = old_boardGridValues[i + 1][j + 1];
                    neighboringCells[3] = old_boardGridValues[i + 1][j];
                    neighboringCells[4] = old_boardGridValues[i + 1][j - 1];
                    neighboringCells[5] = old_boardGridValues[i][j - 1];
                    neighboringCells[6] = old_boardGridValues[i - 1][j - 1];
                    neighboringCells[7] = old_boardGridValues[i - 1][j];
                    int count = 0;
                    for(boolean cellValue : neighboringCells){
                        if(cellValue){
                            count++;
                        }
                    }
                    System.out.println("iteration: " + iter + " " + CV + " x: " + i + " y: " + j + " neighbor_count: " + count);
                    if(CV){
                        System.out.println("x: " + i + " y: " + j + " neighbor_count: " + count);
                        if(count < 2 || count > 3){
                            System.out.println("DEAD: " + "x: " + i + " y: " + j);
                            new_board.dead(new Coordinate(i, j));
                        }
                        if(count == 2 || count == 3){
                            new_board.alive(new Coordinate(i, j));
                        }
                    } else {
                        if(count == 3){
                            System.out.println("ALIVE: " + "x: " + i + " y: " + j);
                            new_board.alive(new Coordinate(i, j));
                        }
                    }
                }
            }
            iter++;
        }
    }

    public Grid getOld(){
        return old_board;
    }

    public void tick(){
        this.updateNewBoard();
        new_board.draw();
        old_board = new_board;
    }

    private void run(int rows, int cols, String fileName, boolean generateCells){
        Grid g;
        Reader r;
        if(fileName != null && generateCells){
            r = new Reader(fileName);
            Scanner sc = new Scanner(System.in); 
            System.out.println(">> Number of cells: ");
            int num_generated_cells = sc.nextInt();
            CellGenerator cg = new CellGenerator(fileName, num_generated_cells, rows, cols);
            g = new Grid(rows, cols, r.getLiveCells());
        } else if(fileName != null && !generateCells) {
            r = new Reader(fileName);
            g = new Grid(rows, cols, r.getLiveCells());
        } else {
            g = new Grid(rows, cols);
        }

        old_board = g;
        new_board = old_board;
        old_board.draw();

    }

    public static void main(String fileName) throws InterruptedException{
        Simulate s = new Simulate(50,50, fileName, true);
        while(s.old_board.cellsOnGrid > 0){
            if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){
                s.tick();
                Thread.sleep(60);
            }
        }
    }

}
