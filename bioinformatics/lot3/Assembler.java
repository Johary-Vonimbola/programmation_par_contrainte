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
        int kFunc = Math.max(3, (int) (Math.log(2) * (m/(double)n)));
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
    public static String onThefly(BitSet bloomFilter, Set<String> realKmers, String seed, int kFunc, int m, Set<String> visited){
        if(visited.contains(seed)) return "";
        visited.add(seed);
        
        List<String> neighbors = new ArrayList<>();
        List<String> addedChars = new ArrayList<>();
        
        for(char c : "ATCG".toCharArray()){
            String checkNeighbor = seed.substring(1) + c;
            if(lookup(bloomFilter, checkNeighbor, kFunc, m) && realKmers.contains(checkNeighbor)){
                neighbors.add(checkNeighbor);
                addedChars.add(c + "");
            }
        }
        
        if(neighbors.isEmpty()) return "";
        
        String best = "";
        for(int i = 0; i < neighbors.size(); i++){
            Set<String> branchVisited = new HashSet<>(visited);
            String candidate = addedChars.get(i) + onThefly(bloomFilter, realKmers, neighbors.get(i), kFunc, m, branchVisited);
            if(candidate.length() > best.length()) best = candidate;
        }
        return best;
    }

    public static List<String> removeSubContigs(List<String> contigs) {
        List<String> result = new ArrayList<>();

        for (String c1 : contigs) {
            boolean isContained = false;

            for (String c2 : contigs) {
                if (!c1.equals(c2) && c2.contains(c1)) {
                    isContained = true;
                    break;
                }
            }

            if (!isContained) {
                result.add(c1);
            }
        }

        return result;
    }

    public static List<String> generateContig(List<Kmers> kmers, int m){
        List<String> contigs = new ArrayList<>();
        BitSet bloomFilter = bloomFilter(kmers, m);
        int kFunc = Math.max(3, (int)(Math.log(2) * (m/(double)kmers.size())));
        
        Set<String> realKmers = new HashSet<>();
        for(Kmers kmer : kmers) realKmers.add(kmer.getSequence());
        
        for(Kmers kmer : kmers){
            if(kmer.getFrequency() >= 1){
                Set<String> visited = new HashSet<>();
                String dna = kmer.getSequence();
                dna += onThefly(bloomFilter, realKmers, kmer.getSequence(), kFunc, m, visited);
                contigs.add(dna);
            }
        }
        contigs = removeSubContigs(contigs);
        return contigs;
    }
}
