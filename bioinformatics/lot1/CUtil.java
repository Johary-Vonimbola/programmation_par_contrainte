package bioinformatics.lot1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//CUtil: converter util
public class CUtil {
    public static String qualitySequence = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

    /**
     * @implNote this func convert a data from a file to a list of fastqData
     * @param filePath
     * @return a list of FastqData
     */
    public static List<FastqData> readFile(String filePath){
        List<FastqData> fastqDatas = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))){
            String idLine = null, sequenceLine = null, descriptionLine = null, qualityLine = null;
            while((idLine = reader.readLine()) != null){
                sequenceLine = reader.readLine();
                descriptionLine = reader.readLine();
                qualityLine = reader.readLine();
                FastqData fastqData = new FastqData(idLine, sequenceLine, descriptionLine, qualityLine);
                fastqDatas.add(fastqData);
            }
        }catch(IOException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return fastqDatas;
    }

    /**
     * @implNote convert fastaDatas to fastqDatas
     * @param fastqDatas
     * @return
     */
    public static List<FastaData> fastqToFasta(List<FastqData> fastqDatas, int threshold){
        List<FastaData> fastaDatas = new ArrayList<>();
        for(FastqData fastqData : fastqDatas){
            fastaDatas.add(fastqData.convertToFasta(threshold, qualitySequence));
        }
        return fastaDatas;
    }

    /**
     * @implNote loop all fastaDatas and call generateKmers(k)
     * @param fastaDatas
     * @param k
     * @return
     */
    public static List<Kmers> getKmers(List<FastaData> fastaDatas, int k){
        List<Kmers> kmers = new ArrayList<>();
        for(FastaData fastaData : fastaDatas){
            kmers.addAll(fastaData.generateKmers(k));
        }
        return kmers;
    }

    /**
     * @implSpec create a map of string and integer for the sequence and frequency and create a new list of kmers with frequencies
     * @param kmers
     * @return
     */
    public static List<Kmers> getFrequencies(List<Kmers> kmers){
        List<Kmers> results = new ArrayList<>();
        Map<String, Integer> frequencies = new HashMap<>();
        for(Kmers kmer : kmers){
            int val = frequencies.containsKey(kmer.getSequence()) ? frequencies.get(kmer.getSequence()) + kmer.getFrequency() : kmer.getFrequency();
            frequencies.put(kmer.getSequence(), val);
        }
        frequencies.forEach((key, value) -> {
            results.add(new Kmers(key, value));
        });
        return results;
    }


    public static List<Kmers> generateHistogram(String filePath, int quanlityThreshold, int k){
        List<FastqData> fastqDatas = readFile(filePath);
        List<FastaData> fastaDatas = fastqToFasta(fastqDatas, quanlityThreshold);
        List<Kmers> kmers = getKmers(fastaDatas, k);
        kmers = getFrequencies(kmers);
        kmers.sort((a, b) -> {
            int diff = a.getSequence().compareTo(b.getSequence());
            if(diff != 0) return diff;
            return Integer.compare(a.getFrequency(), b.getFrequency());
        });
        return kmers;
    }

}
