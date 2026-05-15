package bioinformatics.lot1;

import java.util.List;

//CUtil: converter util
public class CUtil {
    public static String qualitySequence = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

    /**
     * @implNote this func convert a data from a file to a list of fastqData
     * @param filePath
     * @return a list of FastqData
     */
    public static List<FastqData> readFile(String filePath){
        return null;
    }

    /**
     * @implNote convert fastaDatas to fastqDatas
     * @param fastqDatas
     * @return
     */
    public static List<FastaData> fastqToFasta(List<FastqData> fastqDatas){
        return null;
    }

    /**
     * @implSpec create a map of string and integer for the sequence and frequency and create a new list of kmers with frequencies
     * @param kmers
     * @return
     */
    public static List<Kmers> getFrequencies(List<Kmers> kmers){
        return null;
    }


}
