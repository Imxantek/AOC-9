public class Swap {
    private int swapIndex;
    private int swapLength;
    public Swap(int swapIndex, int swapLength) {
        this.swapIndex = swapIndex;
        this.swapLength = swapLength;
    }
    public int getSwapIndex() {
        return swapIndex;
    }
    public int getSwapLength() {
        return swapLength;
    }
    public void print() {
        System.out.println(swapIndex + " " + swapLength);
    }
}
