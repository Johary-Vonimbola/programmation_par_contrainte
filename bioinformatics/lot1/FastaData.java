package bioinformatics.lot1;

import java.util.List;

public class FastaData {
    private String identifier;
    private String sequence;


    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }


    public FastaData(String identifier, String sequence){
        this.setIdentifier(identifier);
        this.setSequence(sequence);
    }

    /**
     * @implNote generate all kmers { sub-strings of k length }
     * @param k
     * @return
     */
    public List<Kmers> generateKmers(int k){
        return null;
    }
}
