package bioinformatics;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bioinformatics.lot1.CUtil;
import bioinformatics.lot1.Kmers;
import bioinformatics.lot1.UI;
import bioinformatics.lot2.AlignmentUI;
import bioinformatics.lot3.Assembler;

public class Main {
    
    public static void main(String[] args) {
        
        int qualityThreshold = 0;
        int k = 11;
        String filePath = "./data.fastq";

        List<Kmers> kmers = CUtil.generateHistogram(filePath, qualityThreshold, k);

        for(Kmers data : kmers){
            System.out.println(data);
        }
        System.out.println("Total "+kmers.size());

        SwingUtilities.invokeLater(() -> {
            JFrame f1 = new JFrame("Analyse de chevauchement");
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f1.setSize(800, 500);
            f1.add(new AlignmentUI());
            f1.setLocationRelativeTo(null);
            f1.setVisible(true);


            JFrame f2 = new JFrame("K-mers histograme");
            f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f2.setSize(800, 500);
            f2.add(new UI(kmers));
            f2.setLocationRelativeTo(null);
            f2.setVisible(true);
        });

        int n = kmers.size();
        // double p = 0.01; // 1% of false positive
        // int m = (int) Math.ceil(-(n * Math.log(p)) / (Math.log(2) * Math.log(2)));
        double p = 0.0001;
        int m = (int) Math.ceil(-(n * Math.log(p)) / (Math.log(2) * Math.log(2)));

        System.out.println("n = " + n + ", m optimal = " + m);
        List<String> contigs = Assembler.generateContig(kmers, m);
        for(String c : contigs){
            System.out.println(c);
        }

    }

}
