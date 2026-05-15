package bioinformatics.lot2;

import java.util.ArrayList;
import java.util.List;

public class Overlapping {
    List<OverlapPosition> overlapPositions;
    String left;
    String right;
    int score;

    public List<OverlapPosition> getOverlapPositions() {
        return overlapPositions;
    }
    public void setOverlapPositions(List<OverlapPosition> overlapPositions) {
        this.overlapPositions = overlapPositions;
    }
    public String getLeft() {
        return left;
    }
    public void setLeft(String left) {
        this.left = left;
    }
    public String getRight() {
        return right;
    }
    public void setRight(String right) {
        this.right = right;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public Overlapping(String left, String right) {
        this.setOverlapPositions(new ArrayList<>());
        this.setLeft(left);
        this.setRight(right);
        this.setScore(0);
    }

    public void addPosition(int left, int right){
        this.getOverlapPositions().add(new OverlapPosition(left, right));
        this.setScore(this.getScore() + 1);
    }

    public int getRightIndexOf(int i){
        for(OverlapPosition op : this.getOverlapPositions()){
            if(op.getLeft() == i) return op.getRight();
        }
        return -1;
    }

    public int getLeftIndexOf(int i){
        for(OverlapPosition op : this.getOverlapPositions()){
            if(op.getRight() == i) return op.getLeft();
        }
        return -1;
    }
    
    
}
