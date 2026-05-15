package bioinformatics.lot1;

public class FastqData {
    private String identifier;
    private String sequence;
    private String description;
    private String quality;
    
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }

    public FastqData(String identifier, String sequence, String description, String quality) {
        this.setIdentifier(identifier);
        this.setSequence(sequence);
        this.setDescription(description);
        this.setQuality(quality);
    }

    /**
     * 
     * @param qualitySequence the ASCII characters order by lower to highest quality from left to right
     * @return
     */
    public int calculateQuality(String qualitySequence){
        int quality = 0;
        for(int i=0; i<this.getSequence().length(); i++){
            char c = this.getSequence().charAt(i);
            int index = qualitySequence.indexOf(c+"");
            quality += index;
        }
        return quality / this.getSequence().length();
    }

    /**
     * @implSpec calculate the quality and filter with threshold and then create a fasta instance with appropriate data
     * @param qualityThreshold
     * @param qualitySequence
     * @return
     */
    public FastaData convertToFasta(int qualityThreshold, String qualitySequence){
        int score = this.calculateQuality(qualitySequence);
        if(score >= qualityThreshold) return new FastaData(this.getIdentifier(), this.getSequence());
        return null;
    }
    
}
