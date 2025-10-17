import java.io.*;
import java.util.ArrayList;

public class Main {
    static ArrayList<String> t=new ArrayList<>();
    public static void load(ArrayList<String> arr) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("AOC9.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                arr.add(line);
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args)  {
        load(t);
        for(int i=0;i<t.size();i++)
            System.out.println(t.get(i));
    }
}