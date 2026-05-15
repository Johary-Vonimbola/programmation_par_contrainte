package bioinformatics.lot1;

import java.util.List;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.Timer;

public class UI extends JPanel{
    private List<Kmers> kmers;
    Color lineColor = new Color(0, 255, 255, 80);
    Color startColorGradient = new Color(0, 220, 255);
    Color endColorGradient = new Color(0, 80, 160);
    BasicStroke bs = new BasicStroke(2);
    int mt=30, mb=30, ml=30, mr=30;
    int barWidth = 50, barSpace=10;
    int histoWidth;
    int histoHeight;


    public int getHistoHeight() {
        return histoHeight;
    }
    public void setHistoHeight(int histoHeight) {
        this.histoHeight = histoHeight;
    }
    public int getHistoWidth() {
        return histoWidth;
    }
    public void setHistoWidth(int histoWidth) {
        this.histoWidth = histoWidth;
    }

    public List<Kmers> getKmers() {
        return kmers;
    }
    public void setKmers(List<Kmers> kmers) {
        this.kmers = kmers;
    }

    public UI(List<Kmers> kmers){
        this.setBackground(new Color(10, 10, 20));
        this.setKmers(kmers);
        this.setHistoWidth(getWidth() - mr - ml);
        this.setHistoHeight(getHeight() - mt - mb);

        new Timer(16, e -> {
            repaint();
        });
    }

    public int getMaxValue(){
        int max = Integer.MIN_VALUE;
        for(Kmers k : kmers)
            if(max < k.getFrequency()) max = k.getFrequency();
        return max;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        //y
        g2.setColor(lineColor);
        g2.setStroke(bs);
        g2.drawLine(this.ml, getHeight() - this.mb, this.ml, this.mt);

        //x
        g2.drawLine(this.ml, getHeight() - this.mb, getWidth() - this.mr, getHeight() - this.mb);

        int maxY = this.getHeight() - this.mb - this.mt;
        int x = barSpace + this.ml;
        for(int i=0; i<this.getKmers().size(); i++){
            Kmers kmers = this.getKmers().get(i);
            int height = maxY * kmers.getFrequency() / getMaxValue();
            int y = getHeight() - this.mb - height;
            g2.setColor(Color.CYAN);
            FontMetrics fm = g2.getFontMetrics();
            g2.setStroke(bs);
            g2.drawString(kmers.getSequence(), x + (barWidth - fm.stringWidth(kmers.getSequence())) / 2, y - 10);
            g2.drawString(kmers.getFrequency()+"", this.ml - 10 - fm.stringWidth(kmers.getFrequency()+""), y);

            g2.setColor(lineColor);
            g2.drawLine(this.ml, y, getWidth() - this.mr, y );

            g2.setPaint(
                new GradientPaint(x, y, startColorGradient, x, y+height, endColorGradient)
            );
            g2.fillRoundRect(x, y, barWidth - barSpace,  height, 5, 5);
            x += barWidth + barSpace;
        }
    }


}
