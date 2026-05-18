package bioinformatics.lot3;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bioinformatics.lot1.Kmers;

public class Assembler {
    
    /**
     * @implSpec insert the kmers inside a bit array for bloom filter
     * @param kmers
     * @param m
     * @return the bit array
     */
    public static BitSet bloomFilter(List<Kmers> kmers, int m){
        BitSet bits = new BitSet(m);
        int n = kmers.size();
        int kFunc = (int) (Math.log(2) * (m/(double)n));
        System.out.println("Number of hash functions :"+kFunc);
        for(Kmers kmer : kmers){
            int i=0;
            while(i < kFunc){
                int hash = hash(kmer.getSequence(), i++, m);
                bits.set(hash);
            }
        }
        return bits;
    }

    /**
     * @implSpec it just uses java natif hashcode h1 + i * h2
     * @param kmer
     * @param ith the number of ith hash function from 0 to ln(2) * m/n
     * @param n the size of bloom filter
     * @return 
     */
    public static int hash(String sequence, int ith, int m){
        int hash = -1;
        int h1 = Math.abs(sequence.hashCode()), h2 = Math.abs((sequence + "_secret").hashCode());
        hash = h1 + ith * h2;
        return Math.abs(hash % m);
    }

    public static boolean lookup(BitSet bloomFilter, String sequence, int kFunc, int m){
        int i=0;
        while(i < kFunc){
            int hash = hash(sequence, i++, m);
            if(!bloomFilter.get(hash)) return false;
        }
        return true;
    }

    /**
     * @implNote from a seed it generates possible neighbors and check the bloom filter for validation
     * @param bloomFilter
     * @param seed
     * @param kFunc the number of hash functions
     * @param dna is by default the seed
     * @return the contig or a "" if for bifurcation
     */
    public static String onThefly(BitSet bloomFilter, String seed, int kFunc, int m, Set<String> visited){
        if(visited.contains(seed)) return "";
        visited.add(seed);
        String nucleotide = "ATCG";
        String maybeNeighbor = "";
        String added="";
        int count = 0;
        for(int i=0; i<nucleotide.length(); i++){
            String checkNeighbor = seed.substring(1, seed.length()) + nucleotide.charAt(i);
            if(lookup(bloomFilter, checkNeighbor, kFunc, m)){
                count++;
                added = nucleotide.charAt(i)+"";
                maybeNeighbor = checkNeighbor;
            }
        }
        if(count == 1){
            return added + onThefly(bloomFilter, maybeNeighbor, kFunc, m, visited);
        }
        return "";
    }

    public static List<String> generateContig(List<Kmers> kmers, int m){
        List<String> contigs = new ArrayList<>();
        BitSet bloomFilter = bloomFilter(kmers, m);
        int kFunc = (int)(Math.log(2) * (m/(double)kmers.size()));
        for(Kmers kmer : kmers){
            Set<String> visited = new HashSet<>();
            String dna = kmer.getSequence();
            dna += onThefly(bloomFilter, kmer.getSequence(), kFunc, m, visited);
            contigs.add(dna);
        }
        return contigs;
    }

}
