package bioinformatics.lot2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AlignmentUI extends JPanel{
    JTextField left;
    JLabel leftText;
    JTextField right;
    JLabel rightText;
    JButton button;

    String leftValue;
    String rightValue;
    Overlapping overlapping;

    public Overlapping getOverlapping() {
        return overlapping;
    }
    public void setOverlapping(Overlapping overlapping) {
        this.overlapping = overlapping;
    }
    public String getLeftValue() {
        return leftValue;
    }
    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }
    public String getRightValue() {
        return rightValue;
    }
    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }
    public JTextField getLeft() {
        return left;
    }
    public void setLeft(JTextField left) {
        this.left = left;
    }
    public JLabel getLeftText() {
        return leftText;
    }
    public void setLeftText(JLabel leftText) {
        this.leftText = leftText;
    }
    public JTextField getRight() {
        return right;
    }
    public void setRight(JTextField right) {
        this.right = right;
    }
    public JLabel getRightText() {
        return rightText;
    }
    public void setRightText(JLabel rightText) {
        this.rightText = rightText;
    }
    public JButton getButton() {
        return button;
    }
    public void setButton(JButton button) {
        this.button = button;
    }

    public AlignmentUI(){
        setBackground(new Color(10, 10, 20));
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 12));
        this.setLeftText(new JLabel("Sequence 1 :"));
        this.setLeft(new JTextField(20));
        this.setRightText(new JLabel("Sequence 2 :"));
        this.setRight(new JTextField(20));
        this.setButton(new JButton("Analyser"));
        this.setLeftValue("");
        this.setRightValue("");

        this.getLeftText().setForeground(new Color(0, 180, 255));

        this.getLeft().setBackground(new Color(20, 30, 50));
        this.getLeft().setForeground(new Color(0, 220, 255)); 
        this.getLeft().setCaretColor(Color.CYAN);
        this.getLeft().setBorder(BorderFactory.createCompoundBorder(
            //external border with stroke
            BorderFactory.createLineBorder(new Color(0, 180, 255), 2), 
            //padding TOP LEFT BOTTOM RIGHT
            BorderFactory.createEmptyBorder(6, 10, 6, 10))
        );
        this.getLeft().setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        this.getRightText().setForeground(new Color(0, 180, 255));
        this.getRight().setBackground(new Color(20, 30, 50));
        this.getRight().setForeground(new Color(0, 220, 255)); 
        this.getRight().setBackground(new Color(20, 30, 50));
        this.getRight().setForeground(new Color(0, 220, 255));  
        this.getRight().setCaretColor(Color.CYAN);
        this.getRight().setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 180, 255), 2), 
            BorderFactory.createEmptyBorder(6, 10, 6, 10))
        );
        this.getRight().setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        this.getButton().setBackground(new Color(0, 120, 200));
        this.getButton().setForeground(Color.WHITE);
        this.getButton().setFocusPainted(false); // take off the hideous rectangle when focus
        this.getButton().setBorderPainted(false); // take off native border
        this.getButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.getButton().setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
        this.getButton().setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 180, 255), 2), 
            BorderFactory.createEmptyBorder(6, 10, 6, 10))
        );

        formPanel.setBackground(new Color(10, 10, 20));
        formPanel.add(getLeftText());
        formPanel.add(getLeft());
        formPanel.add(getRightText());
        formPanel.add(getRight());
        formPanel.add(getButton());

        Color textColor = new Color(0, 220, 255);
        int fontSize = 32;
        Font font = new Font(Font.MONOSPACED, Font.BOLD, fontSize);
        JPanel resultPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(textColor);
                g2.setFont(font);
                FontMetrics fm = g2.getFontMetrics();

                if(getOverlapping() != null){
                    String left = getOverlapping().getLeft();
                    String right = getOverlapping().getRight();
                    int charW = fm.stringWidth("A"); //the calculation of fm and fontSize are differents
                    g2.drawString(left, (getWidth() - fm.stringWidth(getLeftValue())) / 2, getHeight() / 3);
                    g2.drawString(right, (getWidth() - fm.stringWidth(getRightValue())) / 2, getHeight() * 2/3);
                    int leftX=(getWidth() - fm.stringWidth(getLeftValue())) / 2;
                    int rightX=(getWidth() - fm.stringWidth(getRightValue())) / 2;
                    if(left.length() >= right.length()){
                        for(int i=0; i<left.length(); i++){
                            int rightIndex = getOverlapping().getRightIndexOf(i);
                            if(rightIndex >= 0){
                                g2.drawLine(leftX + charW * i + charW/2, (getHeight() / 3) + 15, rightX + charW * rightIndex + charW/2, (getHeight() * 2/3) - 40);
                            }
                        }
                    }else{
                        for(int i=0; i<right.length(); i++){
                            int leftIndex = getOverlapping().getLeftIndexOf(i);
                            if(leftIndex >= 0){
                                g2.drawLine(leftX + charW * leftIndex + charW/2, (getHeight() / 3) + 15, rightX + charW * i + charW/2, (getHeight() * 2/3) - 40);
                            }
                        }
                    }
                }
            }
        };
        resultPanel.setBackground(new Color(8, 12, 24));
        
        this.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setLeftValue(getLeft().getText());
                setRightValue(getRight().getText());

                Overlapping overlapping = AlignmentChecker.lsbs(getLeftValue(), getRightValue());
                setOverlapping(overlapping);
            }
        });

        this.add(formPanel, BorderLayout.NORTH);
        this.add(resultPanel, BorderLayout.CENTER);

        new Timer(16, e -> {
            resultPanel.repaint();
        }).start();
    }
    
}
