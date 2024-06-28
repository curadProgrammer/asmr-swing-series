import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintGui extends JFrame {
    public PaintGui(){
        super("Paint GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(540, 760));
        pack();
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents(){
        // JPanel Configs
        JPanel canvasPanel = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        canvasPanel.setLayout(springLayout);

        // 1. Canvas
        Canvas canvas = new Canvas(1500, 950);
        canvasPanel.add(canvas);
        springLayout.putConstraint(SpringLayout.NORTH, canvas, 50, SpringLayout.NORTH, canvasPanel);

        // 2. Color Chooser
        JButton chooseColorButton = new JButton("Choose Color");
        chooseColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null, "Select a color", Color.BLACK);
                chooseColorButton.setBackground(c);
                canvas.setColor(c);
            }
        });
        canvasPanel.add(chooseColorButton);
        springLayout.putConstraint(SpringLayout.NORTH, chooseColorButton, 10, SpringLayout.NORTH, canvasPanel);
        springLayout.putConstraint(SpringLayout.WEST, chooseColorButton, 25, SpringLayout.WEST, canvasPanel);

        // 3. Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.resetCanvas();
            }
        });
        canvasPanel.add(resetButton);
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 10, SpringLayout.NORTH, canvasPanel);
        springLayout.putConstraint(SpringLayout.WEST, resetButton, 150, SpringLayout.WEST, canvasPanel);


        this.getContentPane().add(canvasPanel);
    }
}
