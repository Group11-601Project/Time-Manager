import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

public class FirstWindow extends JFrame {
    private JPanel panel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JButton delButton = new JButton("Delete");
    private JButton addButton = new JButton("Add Task");
    private static TaskList taskList = new TaskList();
    private DefaultListModel<JCheckBox> checkModel = new DefaultListModel<>();
    private JCheckBoxList checkList;
    private DefaultListModel<Task> taskModel = new DefaultListModel<>();
    private JList<Task> list = new JList<>(taskModel);

    public FirstWindow() {
        super("Task Manager");
        taskList = readFiles();
        createGUI();
    }

    private void createGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panel.setBorder(BorderFactory.createEmptyBorder(5, 3, 5, 3));
        panel.setLayout(new BorderLayout());

        setContentPane(panel);

        centerPanel.setLayout(new FlowLayout());

        setLists();

        list.setFixedCellHeight(40);
        list.setFixedCellWidth(650);
        list.setLayoutOrientation(JList.VERTICAL);
        list.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                newWindow(taskList.get(list.locationToIndex(evt.getPoint())), FirstWindow.this);
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

        checkList.setFixedCellHeight(40);
        checkList.setLayoutOrientation(JList.VERTICAL);
        checkList.setVisible(false);

        centerPanel.add(checkList);
        centerPanel.add(list);

        leftPanel.setLayout(new BorderLayout());

        addButton.setPreferredSize(new Dimension(100, 80));
        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newWindow(new Task(), FirstWindow.this);
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

        delButton.setPreferredSize(new Dimension(100, 80));
        delButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!checkList.isVisible()) {
                    checkList.setVisible(true);
                } else {
                    for (int i = 0; i < checkModel.getSize(); i++) {
                        if (checkModel.elementAt(i).isSelected()) {
                            taskList.remove(i);
                            Task.removeFile(taskList.get(i).getTitle() + ".txt");
                            setLists();
                        }
                    }
                    checkList.setVisible(false);
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

        leftPanel.add(addButton, BorderLayout.NORTH);
        leftPanel.add(delButton, BorderLayout.SOUTH);

        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(leftPanel, BorderLayout.WEST);
        setPreferredSize(new Dimension(800, 600));
        pack();
    }

    public void setLists() {
        taskModel.clear();
        for (Task t : taskList) {
            taskModel.addElement(t);
        }
        list.validate();
        checkModel.clear();
        checkList = new JCheckBoxList(checkModel);
        ListModel currentList = list.getModel();
        for (int i = 0; i < currentList.getSize(); i++) {
            if (currentList.getElementAt(i) != null)
                checkModel.addElement(new JCheckBox());
        }
        checkList.validate();
    }

    public static TaskList getTaskList() {
        return taskList;
    }

    public static TaskList readFiles() {
        File folder = new File("res");
        TaskList result = new TaskList();
        try {
            for (String nested : folder.list()) {
                result.setTaskPlace(Task.readFile(nested));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private void newWindow(Task t, FirstWindow fw) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SecondWindow sw = new SecondWindow(t, fw);
                sw.setVisible(true);
                sw.setLocationRelativeTo(null);
            }
        });
    }

    public static class TaskList extends ArrayList<Task> {
        public TaskList() {
        }

        public void setTaskPlace(Task t) {
            Task task;
            int i;
            int tempHours;
            int tempMins;
            boolean taskAdded = false;
            if (this.size() == 0) {
                add(t);
            } else
                for (i = 0; ((i < this.size()) && (!taskAdded)); i++) {
                    task = this.get(i);
                    tempHours = task.getHours();
                    tempMins = task.getMins();
                    if (t.getHours() < tempHours) {
                        add(i - 1, t);
                        taskAdded = true;
                    } else if (tempHours == t.getHours()) {
                        if (tempMins == t.getMins()) {
                            if (t.equals(task)) {
                                remove(i);
                            }
                            add(i, t);
                            taskAdded = true;
                        } else {
                            if (t.getMins() < tempMins) {
                                if (i - 1 < 0) {
                                    add(0, t);
                                } else
                                    add(i - 1, t);
                            } else if (t.getMins() > tempMins) {
                                add(i + 1, t);
                            }
                            taskAdded = true;
                        }
                    } else if (t.getHours() > tempHours) {
                        add(i + 1, t);
                        taskAdded = true;
                    }
                }
        }
    }

    public class JCheckBoxList extends JList<JCheckBox> {
        protected Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

        public JCheckBoxList() {
            setCellRenderer(new CellRenderer());
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int index = locationToIndex(e.getPoint());
                    if (index != -1) {
                        JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
                        checkbox.setSelected(!checkbox.isSelected());
                        repaint();
                        if (checkbox.isSelected()) {
//                            task4Deleting[index] = true;
                        } else {
//                            task4Deleting[index] = false;
                        }
                    }
                }
            });
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        public JCheckBoxList(ListModel<JCheckBox> model) {
            this();
            setModel(model);
        }

        protected class CellRenderer implements ListCellRenderer<JCheckBox> {
            public Component getListCellRendererComponent(
                    JList<? extends JCheckBox> list, JCheckBox value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JCheckBox checkbox = value;

                checkbox.setBackground(isSelected ? getSelectionBackground()
                        : getBackground());
                checkbox.setForeground(isSelected ? getSelectionForeground()
                        : getForeground());
                checkbox.setEnabled(isEnabled());
                checkbox.setFont(getFont());
                checkbox.setFocusPainted(false);
                checkbox.setBorderPainted(true);
                checkbox.setBorder(isSelected ? UIManager
                        .getBorder("List.focusCellHighlightBorder") : noFocusBorder);
                return checkbox;
            }
        }
    }
}