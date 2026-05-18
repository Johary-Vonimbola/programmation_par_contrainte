package bioinformatics;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bioinformatics.lot1.CUtil;
import bioinformatics.lot1.Kmers;
import bioinformatics.lot1.UI;
import bioinformatics.lot2.AlignmentUI;

public class Main {
    
    public static void main(String[] args) {
        
        int qualityThreshold = 10;
        int k = 31;
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

    }

}
