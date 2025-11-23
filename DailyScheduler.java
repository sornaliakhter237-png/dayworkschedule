import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DailyScheduler extends JFrame {

    private JTextField timeField, taskField;
    private JButton addButton, deleteButton, updateButton;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public DailyScheduler() {
        // Frame settings
        setTitle("Daily Time Scheduler");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input fields
        timeField = new JTextField(10);
        taskField = new JTextField(20);

        // Buttons
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");
        updateButton = new JButton("Update Task");

        // Task list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        // Add components to frame
        add(new JLabel("Time (HH:MM):"));
        add(timeField);
        add(new JLabel("Task:"));
        add(taskField);
        add(addButton);
        add(deleteButton);
        add(updateButton);
        add(scrollPane);

        // Button actions
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        updateButton.addActionListener(e -> updateTask());

        setVisible(true);
    }

    private void addTask() {
        String time = timeField.getText().trim();
        String task = taskField.getText().trim();
        if (time.isEmpty() || task.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both time and task.");
            return;
        }
        taskListModel.addElement(time + " - " + task);
        timeField.setText("");
        taskField.setText("");
    }

    private void deleteTask() {
        int selected = taskList.getSelectedIndex();
        if (selected != -1) {
            taskListModel.remove(selected);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.");
        }
    }

    private void updateTask() {
        int selected = taskList.getSelectedIndex();
        if (selected != -1) {
            String time = timeField.getText().trim();
            String task = taskField.getText().trim();
            if (time.isEmpty() || task.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter both time and task to update.");
                return;
            }
            taskListModel.set(selected, time + " - " + task);
            timeField.setText("");
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to update.");
        }
    }

    public static void main(String[] args) {
        new DailyScheduler();
    }
}
