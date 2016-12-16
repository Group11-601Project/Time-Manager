import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SecondWindow extends JFrame {
    private JPanel panel = new JPanel();
    private JTextField titleField = new JTextField(255);
    private JTextField timeField = new JTextField(5);
    private JTextArea contentField = new JTextArea(50, 255);
    private JButton okButton = new JButton("OK");
    private boolean saveTask = false;

    public SecondWindow() {
    }

    public SecondWindow(Task t, JPanel p) {
        super(t.getTitle());
        createGUI(t, p);
        addWindowListener(new WindowListener() {
            public void windowActivated(WindowEvent event) {}

            public void windowClosed(WindowEvent event) {}

            public void windowClosing(WindowEvent event) {
                taskClosing(event);
            }

            public void windowDeactivated(WindowEvent event) {}

            public void windowDeiconified(WindowEvent event) {}

            public void windowIconified(WindowEvent event) {}

            public void windowOpened(WindowEvent event) {}
        });
    }

    public void createGUI(Task task, JPanel p) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        setContentPane(panel);

        okButton.setPreferredSize(new Dimension(100, 80));
        okButton.setBounds(590, 375, 100, 50);
        okButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                if (button == okButton) {
                    taskClosing();
                }
            }

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}
        });
        panel.add(okButton);

        titleField.setBounds(10, 15, 475, 25);
        titleField.setToolTipText("Enter your title");
        titleField.setEditable(true);
        titleField.setText(task.getTitle());
        panel.add(titleField);

        timeField.setBounds(10 + 475 + 100, 15, 120, 25);
        timeField.setToolTipText("HH.MM");
        timeField.setEditable(true);
        timeField.setText(task.getTime());
        panel.add(timeField);

        contentField.setBounds(10, 15 + 25 + 10, 450 + 100, 375);
        contentField.setToolTipText("Enter your task");
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        contentField.setEditable(true);
        contentField.setText(task.getContent());
        panel.add(new JScrollPane(contentField));
        panel.add(contentField);

        setPreferredSize(new Dimension(750, 500));
        pack();
    }

    public void taskClosing(WindowEvent event) {
        Object[] options = {"Да", "Нет"};
        int n = JOptionPane.showOptionDialog(event.getWindow(), "Сохранить заметку?",
                "Подтверждение", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);
        switch (n) {
            case 0:
                event.getWindow().setVisible(false);
                saveTask = true;
                break;
            case 1:
                event.getWindow().setVisible(false);
                saveTask = false;
        }
    }

    public void taskClosing() {
        Object[] options = {"Да", "Нет"};
        int n = JOptionPane.showOptionDialog(this , "Сохранить заметку?",
                "Подтверждение", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);
        switch (n) {
            case 0:
                this.setVisible(false);
                saveTask = true;
                break;
            case 1:
                this.setVisible(false);
                saveTask = false;
        }
    }

    public boolean saveTask() {
        return saveTask;
    }

    public Task getFields(boolean write) {
        return new Task(titleField.getText(), timeField.getText(), contentField.getText(), write);
    }

}