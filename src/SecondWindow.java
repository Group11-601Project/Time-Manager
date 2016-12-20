import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SecondWindow extends JFrame {
    private static final int BORDER = 25;
    private JPanel panel = new JPanel();
    private JTextField titleField = new JTextField(255);
    private JTextField timeField = new JTextField(5);
    private JTextArea contentField = new JTextArea(50, 255);
    private JButton okButton = new JButton("OK");
    private Task task;

    public SecondWindow(Task t, FirstWindow fw) {
        super(t.getTitle());
        this.task = t;
        createGUI(fw);
        addWindowListener(new WindowListener() {
            public void windowActivated(WindowEvent event) {
            }

            public void windowClosed(WindowEvent event) {
            }

            @Override
            public void windowClosing(WindowEvent event) {
                taskClosing(fw);
            }

            public void windowDeactivated(WindowEvent event) {
            }

            public void windowDeiconified(WindowEvent event) {
            }

            public void windowIconified(WindowEvent event) {
            }

            public void windowOpened(WindowEvent event) {
            }
        });
    }

    public void createGUI(FirstWindow fw) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(panel);

        titleField.setBounds(BORDER, BORDER, 700 - 3 * BORDER, BORDER);
        titleField.setToolTipText("Enter your title");
        titleField.setEditable(true);
        titleField.setText(task.getTitle());
        panel.add(titleField);

        timeField.setBounds(700 - timeField.getWidth(), BORDER, 2 * BORDER, BORDER);
        timeField.setToolTipText("HH.MM");
        timeField.setEditable(true);
        timeField.setText(task.getTime());
        panel.add(timeField);

        contentField.setBounds(BORDER, 2 * BORDER + titleField.getHeight(),
                titleField.getWidth(), 525 - titleField.getHeight() - BORDER);
        contentField.setToolTipText("Enter your task");
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        contentField.setEditable(true);
        contentField.setText(task.getContent());
        panel.add(new JScrollPane(contentField));
        panel.add(contentField);

        okButton.setBounds(700 - okButton.getWidth() - BORDER, 525 - okButton.getHeight() - BORDER, 100, 50);
        okButton.setPreferredSize(new Dimension(100, 80));
        okButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                if (button == okButton) {
                    taskClosing(fw);
                }
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        panel.add(okButton);

        setPreferredSize(new Dimension(800, 600));
        pack();
    }

    private void taskClosing(FirstWindow fw) {
        Object[] options = {"Да", "Нет"};
        int n = JOptionPane.showOptionDialog(this, "Сохранить заметку?",
                "Подтверждение", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);
        if (n == 0) {
            task = getFields();
            task.createNewFile();
            for (Task t : FirstWindow.getTaskList()) {
                if (t.equals(task)) {
                    FirstWindow.getTaskList().remove(t);
                }
            }
            FirstWindow.getTaskList().setTaskPlace(task);
            fw.setLists();
        }
        this.setVisible(false);
    }

    private Task getFields() {
        return new Task(titleField.getText(), timeField.getText(), contentField.getText());
    }
}