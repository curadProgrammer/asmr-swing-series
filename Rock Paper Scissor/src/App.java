import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // instantiate a RockPaperScissorGUI obj
                RockPaperScissorGUI rockPaperScissorGUI = new RockPaperScissorGUI();

                // display the GUI
                rockPaperScissorGUI.setVisible(true);
            }
        });
    }
}
