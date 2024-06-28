import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ToDoList extends JFrame {

    private JPanel taskPanel;
    private JTextField taskField;
    private JButton addTaskButton;

    public ToDoList() {
        super("To Do List");
        setSize(540, 760);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("To Do List", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(taskPanel), BorderLayout.CENTER);

        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        taskField = new JTextField();
        taskField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        add(taskField, BorderLayout.SOUTH);
        add(addTaskButton, BorderLayout.SOUTH);
    }

    private void addTask() {
        JPanel task = new JPanel();
        task.setLayout(new BoxLayout(task, BoxLayout.X_AXIS));

        JCheckBox checkbox = new JCheckBox();
        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    taskField.setFont(taskField.getFont().deriveFont(Font.ITALIC));
                } else {
                    taskField.setFont(taskField.getFont().deriveFont(Font.PLAIN));
                }
            }
        });
        task.add(checkbox);

        JTextField taskField = new JTextField(this.taskField.getText());
        taskField.setMaximumSize(new Dimension(Integer.MAX_VALUE, taskField.getPreferredSize().height));
        task.add(taskField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.remove(task);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        });
        task.add(deleteButton);

        taskPanel.add(task);
        taskPanel.revalidate();
        taskPanel.repaint();
        this.taskField.setText("");
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.setVisible(true);
    }
}