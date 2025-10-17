import java.io.*;
import java.util.ArrayList;

public class Main {
    public static String readLine() {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("AOC9.txt"));
            line = br.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        return line;
    }
    public static int locateSwap(String input) {
        for(int i = input.length() - 1; i >= 0; i--) {
            if(input.charAt(i) != '.') {
                return i;
            }
        }
        return -1;
    }
    public static int locateSwap2(ArrayList<Integer> list) {
        for(int i = list.size() - 1; i >= 0; i--) {
            if(list.get(i) != -1) {
                return i;
            }
        }
        return -1;
    }
    public static boolean checkSwap(String input) {
        boolean numbers = false;
        for(int i = input.length() - 1; i >= 0; i--) {
            if(input.charAt(i) != '.') {
                numbers = true;
            }
            if (numbers && input.charAt(i) == '.') {
                return false;
            }
        }
        return true;
    }
    public static boolean checkSwap2(ArrayList<Integer> list) {
        boolean numbers = false;
        for(int i = list.size() - 1; i >= 0; i--) {
            if(list.get(i) != -1) {
                numbers = true;
            }
            if (numbers && list.get(i) == -1) {
                return false;
            }
        }
        return true;
    }
    public static void sol1(String input) {
        StringBuilder output = new StringBuilder();
        boolean file=false, space=false;
        for(int i=0; i<input.length(); i++){
            if(i%2==0){
                file=true;
                space=false;
            }else{
                file=false;
                space=true;
            }
            if(file){
                for(int j=0; j<input.charAt(i)-'0'; j++){
                    String temp=Integer.toString(i/2);
                    output.append(temp);
                }
            }if(space){
                for(int j=0; j<input.charAt(i)-'0'; j++){
                    output.append('.');
                }
            }
        }
        System.out.println(output);

        for(int i=0; i<output.length(); i++){
            if(output.charAt(i)=='.'){
                int swapIndex1=i;
                int swapIndex2=locateSwap(output.toString());
                char ch1=output.charAt(swapIndex1);
                char ch2=output.charAt(swapIndex2);

                output.setCharAt(swapIndex1, ch2);
                output.setCharAt(swapIndex2, ch1);

                if(checkSwap(output.toString())){
                    break;
                }
            }
        }
        long result=0;
        for(int i=0; i<output.length(); i++){
            if(output.charAt(i)=='.'){
                break;
            }
            result+=(output.charAt(i)-'0')*i;
        }
        System.out.println(output);
        System.out.println(result);
    }
    public static void main(String[] args)  {
        String input=readLine();
        ArrayList <Integer> list=new ArrayList<>();
        boolean file=false, space=false;
        for(int i=0; i<input.length(); i++){
            if(i%2==0){
                file=true;
                space=false;
            }else{
                file=false;
                space=true;
            }
            if(file){
                for(int j=0; j<input.charAt(i)-'0'; j++){
                    list.add(i/2);
                }
            }
            if(space){
                for(int j=0; j<input.charAt(i)-'0'; j++){
                    list.add(-1);
                }
            }
        }

        for(int i=0; i<list.size(); i++){
            if(list.get(i)==-1){
                int swapIndex1=i;
                int swapIndex2=locateSwap2(list);
                int val1=list.get(swapIndex1);
                int val2=list.get(swapIndex2);
                list.set(swapIndex1, val2);
                list.set(swapIndex2, val1);
                if(checkSwap2(list)){
                    break;
                }
            }
        }
        long result=0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i)==-1){
                break;
            }
            result+=list.get(i)*i;
        }
        System.out.println(result);
    }
}