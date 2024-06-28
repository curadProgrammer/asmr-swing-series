import javax.swing.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGui().setVisible(true);
            }
        });

        // Wait for input before exiting
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
