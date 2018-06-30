import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class Reader
{
    private List<Coordinate> liveCells;
    public Reader(String fileName){
        this.read(fileName);
        System.out.println(">> Reading File...SUCCESS");
    }

    private boolean read(String fileName){
        liveCells = new ArrayList<Coordinate>();
        boolean succeeded = false;
        try{
            BufferedReader bfr = new BufferedReader(new FileReader(new File(fileName)));
            if(bfr.ready()){
                String line;
                while((line = bfr.readLine()) != null){
                    String[] coordinate = line.split(",");
                    int x = Integer.parseInt(coordinate[0]);
                    int y = Integer.parseInt(coordinate[1]);
                    liveCells.add(new Coordinate(x, y));
                }
            }
            succeeded = true;
            bfr.close();
            return succeeded;
        }catch(IOException e){
            return false;
        }
    }

    public List<Coordinate> getLiveCells(){
        return this.liveCells;
    }
}
