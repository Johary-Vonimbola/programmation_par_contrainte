package bioinformatics.lot3;

import java.util.BitSet;
import java.util.List;

import bioinformatics.lot1.Kmers;

public class Assembler {
    
    /**
     * @implSpec insert the kmers inside a bit array for bloom filter
     * @param kmers
     * @param m
     * @return the bit array
     */
    public static BitSet bloomFilter(List<Kmers> kmers, int m){
        return null;
    }

    /**
     * @implSpec it just uses java natif hashcode h1 + i * h2
     * @param kmer
     * @param ith the number of ith hash function from 0 to ln(2) * n/m
     * @return 
     */
    public static int hash(Kmers kmer, int ith){
        return -1; 
    }

    /**
     * @implNote from a seed it generates possible neighbors and check the bloom filter for validation
     * @param seed
     * @param bloomFilter
     * @return the contig or a "" if for bifurcation
     */
    public static String contigOnTheFly(Kmers seed, BitSet bloomFilter){
        return "";
    }

}
