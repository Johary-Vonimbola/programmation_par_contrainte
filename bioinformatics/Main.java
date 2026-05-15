package bioinformatics;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bioinformatics.lot1.CUtil;
import bioinformatics.lot1.Kmers;
import bioinformatics.lot1.UI;

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

        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("K-mers histograme");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(700, 500);
            f.add(new UI(kmers));
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });

    }

}
