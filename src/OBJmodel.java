import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Created by Josh on 1/26/17.
 */
public class OBJmodel {

    private ArrayList<double[]> vertices;
    private ArrayList<int[]> faces;


    public OBJmodel(String filename){
        vertices = new ArrayList<double[]>();
        faces = new ArrayList<int[]>();
        File f = new File(filename);
        parseOBJFile(f);
    }

    public ArrayList<double[]> getVertices(){
        return vertices;
    }

    public ArrayList<int[]> getFaces(){
        return faces;
    }

    private void parseOBJFile(File f){
        Scanner s = null;
        try {
            s = new Scanner(f);
        }catch(FileNotFoundException fnf){
            fnf.printStackTrace();
        }
        String currentLine;
        String vals[];
        while(s.hasNextLine()) {
            currentLine = s.nextLine();
            if (!currentLine.isEmpty()) {
                if (currentLine.charAt(0) == 'v') {
                    vals = currentLine.substring(2).split(" ", 3);
                    double[] xy = {Double.parseDouble(vals[0]), Double.parseDouble(vals[1])};
                    System.out.println("V: " + xy[0] + ", " + xy[1]);
                    vertices.add(xy);
                } else if (currentLine.charAt(0) == 'f') {
                    vals = currentLine.substring(2).split(" |/");
                    //System.out.println(Arrays.toString(vals) + "\n");
                    int[] face = {Integer.parseInt(vals[0]), Integer.parseInt(vals[3]), Integer.parseInt(vals[6])};
                    System.out.println("F: " + face[0] + ", " + face[1] + ", " + face[2]);
                    faces.add(face);
                }
            }
        }
    }

}
