import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class CookieClicker extends JFrame {
    private JTextField counterField;

    public CookieClicker(){
        super("Cookie Clicker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 800));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents(){
        SpringLayout springLayout = new SpringLayout();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(springLayout);

        //1. Banner
        JLabel bannerImg = loadImage("resources/banner.png", true, 450, 100);

        jPanel.add(bannerImg);
        springLayout.putConstraint(SpringLayout.WEST, bannerImg, 65, SpringLayout.WEST, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, bannerImg, 25, SpringLayout.NORTH, jPanel);

        //2. Cookie Button
        JButton cookieButton = createImageButton("resources/cookie.png");
        cookieButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int counter = Integer.parseInt(counterField.getText());
                counterField.setText(Integer.toString(++counter));
            }
        });

        jPanel.add(cookieButton);
        springLayout.putConstraint(SpringLayout.WEST, cookieButton, 95, SpringLayout.WEST, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, cookieButton, 175, SpringLayout.NORTH, jPanel);

        //3. Counter Label
        JLabel counterLabel = new JLabel("Clicks: ");
        counterLabel.setFont(new Font("Dialog", Font.BOLD, 26));

        jPanel.add(counterLabel);
        springLayout.putConstraint(SpringLayout.WEST, counterLabel, 95, SpringLayout.WEST, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, counterLabel, 550, SpringLayout.NORTH, jPanel);

        //4. Counter Field
        counterField = new JTextField(10);
        counterField.setFont(new Font("Dialog", Font.BOLD, 26));
        counterField.setHorizontalAlignment(SwingConstants.RIGHT);
        counterField.setText("0");
        counterField.setEditable(false);

        jPanel.add(counterField);
        springLayout.putConstraint(SpringLayout.WEST, counterField, 210, SpringLayout.WEST, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, counterField, 550, SpringLayout.NORTH, jPanel);

        //5. Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Dialog", Font.BOLD, 18));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counterField.setText("0");
            }
        });

        jPanel.add(resetButton);
        springLayout.putConstraint(SpringLayout.WEST, resetButton, 250, SpringLayout.WEST, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 700, SpringLayout.NORTH, jPanel);

        this.getContentPane().add(jPanel);
    }

    private JButton createImageButton(String fileName){
        JButton button;
        try{
            InputStream inputStream = this.getClass().getResourceAsStream(fileName);
            Image image = ImageIO.read(inputStream);
            button = new JButton(new ImageIcon(image));
            return button;
        }catch(Exception e){
            System.out.println("Error: " + e);
            return null;
        }
    }

    private JLabel loadImage(String fileName, boolean isResized, int targetWidth, int targetHeight){
        BufferedImage image;
        JLabel imageContainer;
        try{
            InputStream inputStream = this.getClass().getResourceAsStream(fileName);
            image = ImageIO.read(inputStream);
            if(isResized){
                image = resizeImage(image, targetWidth, targetHeight);
            }
            imageContainer = new JLabel(new ImageIcon(image));
            return imageContainer;
        }catch(Exception e){
            System.out.println("Error: " + e);
            return null;
        }
    }

    private BufferedImage resizeImage(BufferedImage image, int targetWidth, int targetHeight){
        BufferedImage newImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return newImage;
    }
}
