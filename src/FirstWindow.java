import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FirstWindow extends JFrame {
    private TaskList taskList = new TaskList();
    private JPanel panel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private JButton addButton = new JButton("Add task");
    private JButton delButton = new JButton(" Delete ");
    private SecondWindow sw = new SecondWindow();

    public FirstWindow() {
        super("Task Manager");
        ImageIcon img = new ImageIcon("C:\\Users\\User\\Git\\Projects\\Hack Sprint 2\\Base");
        super.setIconImage(img.getImage());
        Task t0 = new Task("Ex1", "11:11", "Ne rabotayet 11111", false);
        Task t1 = new Task("Ex2", "22:22", "Stranno 22222", false);
        Task t2 = new Task("Ex3", "23:44", "Hmm 33333", false);
        taskList.setTaskPlace(t0);
        taskList.setTaskPlace(t1);
        taskList.setTaskPlace(t2);
    /*
        File folder = new File("C:\\Users\\User\\Git\\Projects\\Hack Sprint 2\\res");
        try {
            for (File nested : folder.listFiles()) {
                taskList.setTaskPlace(nested);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    */
        createGUI();
    }

    private void createGUI() {
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        panel.setLayout(new BorderLayout());
        setContentPane(panel);
        centerPanel.setLayout(new FlowLayout());

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        for (int i = 0; i < taskList.size(); i++) {
            model.addElement(taskList.get(i).toString());
            checkBoxes.add(new JCheckBox("", false));
        }

        list.setLayoutOrientation(JList.VERTICAL);
        list.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent evt) {
                /* JList list = (JList) evt.getSource();
                if ((evt.getClickCount() == 1) && (evt.getButton() == MouseEvent.BUTTON1)) {
                    sw = new SecondWindow(getTaskList().get(evt), panel);
                    sw.setVisible(true);
                    sw.setLocationRelativeTo(null);
                } */
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
        list.validate();
        centerPanel.add(list);

        JList<JCheckBox> checkList = new JList<>();
        checkList.setLayoutOrientation(JList.VERTICAL);
        checkList.validate();
        centerPanel.add(checkList);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        addButton.setPreferredSize(new Dimension(100, 80));
        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SecondWindow sw = new SecondWindow(new Task(true), panel);
                sw.setVisible(true);
                sw.setLocationRelativeTo(null);
            }

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}
        });

        delButton.setPreferredSize(new Dimension(100, 80));
        delButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!checkList.isEnabled()) {
                    checkList.setEnabled(true);
                } else {
                    for (int i = 0; i < taskList.size(); i++) {
                        if (checkBoxes.get(i).isSelected()) {
                            remove(i);
                        }
                    }
                    checkList.setEnabled(false);
                }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}
        });

        leftPanel.add(addButton);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(delButton);

        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(leftPanel, BorderLayout.WEST);
        setPreferredSize(new Dimension(800, 600));
        pack();
    }

    public JPanel getPanel() {
        return panel;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void handle() {
        //handling SecondWindow activities
    }
}