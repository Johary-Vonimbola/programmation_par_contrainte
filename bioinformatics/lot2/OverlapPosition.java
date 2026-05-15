package bioinformatics.lot2;

public class OverlapPosition {
    int left;
    int right;

    public int getLeft() {
        return left;
    }
    public void setLeft(int left) {
        this.left = left;
    }
    public int getRight() {
        return right;
    }
    public void setRight(int right) {
        this.right = right;
    }

    public OverlapPosition(int left, int right){
        this.setLeft(left);
        this.setRight(right);
    }
    
}
