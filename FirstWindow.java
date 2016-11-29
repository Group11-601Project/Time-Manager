import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class FirstWindow extends JFrame {
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private JPanel panel = new JPanel();
	private JPanel checkPanel = new JPanel();
	private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	private JButton addButton = new JButton("Add task");
	private JButton delButton = new JButton(" Delete ");
	private boolean click = false;

	public FirstWindow() {
		super("Task Manager v0.1");
		createGUI();
	}

	public void createGUI() {
		int size = tasks.size();
		String [] data = new String[size];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,10,5));
		setContentPane(panel);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		for (int i = 0; i < size; i++) {
			data[i] = tasks.get(i).toString();
			checkBoxes.add(new JCheckBox("", false));
		}

		JList list = new JList(data);
		list.setLayoutOrientation(JList.VERTICAL);
		rightPanel.add(list, BorderLayout.EAST);
		JList checkList = new JList(checkBoxes.toArray());	
		JPanel leftPanel = new JPanel();
 		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
 		checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));
		checkPanel.setEnabled(false);

 		list.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent evt) {
       			JList list = (JList)evt.getSource();
        		if (evt.getClickCount() == 2) {
            		click = true;
            		int index = list.locationToIndex(evt.getPoint());
            	}
        	}
        });

  		addButton.setPreferredSize(new Dimension(100, 80));
  		addButton.addMouseListener(new MouseAdapter());
    	
    	delButton.setPreferredSize(new Dimension(100, 80));
      	addButton.addMouseListener(new MouseAdapter());

       	leftPanel.add(addButton);
       	leftPanel.add(Box.createVerticalGlue());
       	leftPanel.add(delButton);

       	checkList.setLayoutOrientation(JList.VERTICAL);
       	checkPanel.add(checkList);

		getContentPane().add(rightPanel, BorderLayout.EAST);
		getContentPane().add(leftPanel, BorderLayout.WEST);
		getContentPane().add(checkPanel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(750, 500));  
		pack();
	}

    public void setTaskPlace(Task t) {

		Task task;
		boolean taskAdded = false;
		int taskHours = (int) t.time.charAt(0) * 10 + t.time.charAt(1);
		int taskMins = (int) t.time.charAt(3) * 10 + t.time.charAt(4);
		int tempHours;
		int tempMins;
		int i = 0;
		int num;
		for (i = 0; ((i < tasks.size()) && (!taskAdded)); i++) {
			if (tasks.size() == 0) {
				add(0,t);
				taskAdded = true;
			}
			task = tasks.get(i);
			tempHours = task.getTime().charAt(0) * 10 + task.getTime().charAt(1);
			tempMins = task.getTime().charAt(3) * 10 + task.getTime().charAt(4);
			if (tempHours == taskHours) {
			 	if (tempMins == taskMins) {
				 	num = tasks.indexOf(task);
				 	remove(num);
				 	add(num, t);
					taskAdded = true;
				}
				else {
					if (tempMins > taskMins) {
						continue;
					}
				}
			}
			else {
				if (tempHours > taskHours) {
					continue;
				}
			}
		}
	}

    public void add(int i, Task t) {
		this.tasks.add(i,t);
    }

    public void remove(int i) {
    	this.tasks.remove(i);
    }

	public Task getTask(int i) {
		return this.tasks.get(i);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}

	public ArrayList<Task> getList() {
		return this.tasks;
	}
	
	public class MouseAdapter implements MouseListener {
        
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            if (button == addButton) {
  	        	getList().add(new Task());
            }
            if (button == delButton) {
            	if (!checkPanel.isEnabled()) {
            		checkPanel.setEnabled(true);
            	}
            	else {
					for (int i = 0; i < getList().size(); i++) {
						if (checkBoxes.get(i).isSelected() == true) {
							remove(i);
						}
					}
				}
				checkPanel.setEnabled(false);
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
    
    }
}