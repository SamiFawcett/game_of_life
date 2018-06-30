import java.util.List;
import java.io.*;
import java.awt.event.KeyEvent;
public class Grid
{
    int rows;
    int cols;
    private boolean[][] GRID;
    int cellsOnGrid = 0;
    public Grid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        StdDraw.setCanvasSize(1500, 1500);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, rows);
        StdDraw.setYscale(0, cols);
        GRID = new boolean[this.rows + 1][this.cols + 1];
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                GRID[i][j] = false;
                StdDraw.line(i,j, i+1, j);
                StdDraw.line(i,j, i, j+1);
            }
        }
        StdDraw.show();
    }

    public Grid(int rows, int cols, List<Coordinate> liveCells){
        this.rows = rows;
        this.cols = cols;
        StdDraw.setCanvasSize(1500, 1500);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, rows);
        StdDraw.setYscale(0, cols);
        GRID = new boolean[this.rows + 1][this.cols + 1];
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                StdDraw.line(i,j, i+1, j);
                StdDraw.line(i,j, i, j+1);
            }
        }
        for(int i = 0; i < liveCells.size(); i++){
            this.alive(liveCells.get(i));
        }
        cellsOnGrid = liveCells.size();
        StdDraw.show();
    }

    public void alive(Coordinate c){
        GRID[c.x][c.y] = true;
        cellsOnGrid++;
    }

    public void dead(Coordinate c){
        GRID[c.x][c.y] = false;
        cellsOnGrid--;
    }

    public boolean cell(int x, int y){
        return GRID[x][y];
    }

    public boolean[][] getGridValues(){
        return GRID; 
    }

    public void selectLive(String fileName){
        boolean enterPressed = StdDraw.isKeyPressed(KeyEvent.VK_ENTER);
        while(!enterPressed){
            int count = 1;
            int x = (int)StdDraw.mouseX();
            int y = (int)StdDraw.mouseY();
            this.alive(new Coordinate(x, y));
            try{
                FileWriter fw = new FileWriter(new File(fileName), true);
                if(count > 0){
                    fw.write(x+","+y);
                    fw.write(System.lineSeparator()); 
                    count--;    
                }
                fw.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        this.draw();
        return;
    }

    public void draw(){
        StdDraw.setPenRadius(1);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                //System.out.println("x: " + i + ", y: " + j + ", GRID_VALUE: " + GRID[i][j]);
                if(GRID[i][j]){
                    StdDraw.setPenColor(StdDraw.BOOK_RED);
                    StdDraw.filledSquare(i + .5, j + .5, .4);
                } else {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledSquare(i +.5, j +.5, .4);
                }
            }
        }
        StdDraw.show();
    }

}
