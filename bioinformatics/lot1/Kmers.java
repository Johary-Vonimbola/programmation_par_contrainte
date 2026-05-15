package bioinformatics.lot1;

public class Kmers{
    private String sequence;
    private int frequency;

    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    public Kmers(String sequence, int frequency){
        this.setSequence(sequence);
        this.setFrequency(frequency);
    }
}