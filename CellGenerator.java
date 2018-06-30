import java.io.*;
public class CellGenerator
{
    public CellGenerator(String fileName, int amountOfCells, int rows, int cols){
        try{
            FileWriter fr = new FileWriter(new File(fileName), false);
            for(int i = 0; i < amountOfCells; i++){
                fr.write((int)(Math.random() * rows) + "," + (int)(Math.random() * cols));
                fr.write(System.lineSeparator());
            }
            fr.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
