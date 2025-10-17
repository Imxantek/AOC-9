import java.io.*;
import java.lang.reflect.Array;
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
    public static Swap locateSwap3(ArrayList<Integer> list, int windowSize) {
        int prevNr=list.get(list.size()-1);
        int currSize=0;
        for(int i = list.size() - 1; i >= 0; i--) {
            while(list.get(i)==-1) {
                i--;
            }
            prevNr=list.get(i);

            while(list.get(i)==prevNr) {
                currSize++;
                i--;
            }
            if(currSize>windowSize) {
                currSize=0;
                while(list.get(i)==-1){
                    i--;
                }
                prevNr=list.get(i);
            }else{
                return new Swap(i+1,currSize);
            }

        }
        return null;
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
    public static void sol2(String input) {
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
    public static void printArray(ArrayList<Integer> list) {
        for(Integer i: list){
            if(i==-1){
                System.out.print(".");
            }else{
                System.out.print(i);
            }
        }
        System.out.println();
    }
    public static void swap(int leftIndex, int rightIndex, int swapSize, ArrayList<Integer> list) {
        int valLeft=list.get(leftIndex);
        int valRight=list.get(rightIndex);
//        System.out.println("swap attempted");
        for(int i=0; i<swapSize; i++){

            list.set(leftIndex+i, valRight);
            list.set(rightIndex+i, valLeft);
        }
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
//        printArray(list);
//        System.out.println();
        mainloop:
        for(int i=list.size()-1; i>=0; i--){
            while(list.get(i)==-1){
                i--;
            }
            int currNr=list.get(i);
            int rightSize=0;

            while(list.get(i)==currNr){
                rightSize++;
                i--;
                if(i==0){
                    break mainloop;
                }
            }
            i++;
            int rightIndex=i;
//            System.out.println("rightSize="+rightSize+", rightIndex="+rightIndex+" rightval="+list.get(rightIndex));
            int leftSize=0;
            swaploop:
            for(int j=0; j<list.size()-1; j++){
                if(list.get(j)!=-1){
                    leftSize=0;
                }
                if(list.get(j)==-1){
                    int leftIndex=j;
                    while(list.get(j)==-1){
                        leftSize++;
                        if(leftSize>=rightSize && leftIndex<rightIndex){
//                            System.out.println("leftIndex="+leftIndex+" rightIndex="+rightIndex+" rightSize="+rightSize);
                            swap(leftIndex, rightIndex, rightSize, list);
//                            printArray(list);
                            break swaploop;
                        }
                        if(j+1<list.size()-1){
                            j++;
                        }else{
                            break;
                        }
                    }
                    leftSize=0;
                }
            }
//            printArray(list);
//            System.out.println();
        }
        long result=0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i)!=-1){
                result+=list.get(i)*i;
            }
        }
        System.out.println();
        System.out.println(result);

    }
}