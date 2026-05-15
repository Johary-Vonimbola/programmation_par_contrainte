package bioinformatics;

import java.util.List;

import bioinformatics.lot1.CUtil;
import bioinformatics.lot1.Kmers;

public class Main {
    
    public static void main(String[] args) {
        
        int qualityThreshold = 10;
        int k = 3;
        String filePath = "./data.fastq";

        List<Kmers> kmers = CUtil.generateHistogram(filePath, qualityThreshold, k);

        for(Kmers data : kmers){
            System.out.println(data);
        }
        System.out.println("Total "+kmers.size());


    }

}
