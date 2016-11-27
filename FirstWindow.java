import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class FirstWindow extends JFrame {
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private JPanel panel = new JPanel();

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
		}
		JList list = new JList(data);
		list.setLayoutOrientation(JList.VERTICAL);
		rightPanel.add(list, BorderLayout.EAST);	
		JPanel leftPanel = new JPanel();
 		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
 		
 		JButton addButton = new JButton("Add task");
  		addButton.setPreferredSize(new Dimension(100, 80));
  		addButton.addMouseListener(new MouseAdapter());
    	
    	JButton delButton = new JButton(" Delete ");
	 	delButton.setPreferredSize(new Dimension(100, 80));
      	addButton.addMouseListener(new MouseAdapter());

       	leftPanel.add(addButton);
       	leftPanel.add(Box.createVerticalGlue());
       	leftPanel.add(delButton);

		getContentPane().add(rightPanel, BorderLayout.EAST);
		getContentPane().add(leftPanel, BorderLayout.WEST);
		setPreferredSize(new Dimension(750, 500));  
		pack();
	}

    public void add(Task t) {
    	this.tasks.add(t);
    }

    public void add(int i, Task t) {
    	this.tasks.add(i, t);
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

	public void changeWindow() {
        //Не прописывать
    }

	public class MouseAdapter implements MouseListener {
 
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            //Bulat: Реализовать клик [проверка двух клавиш[Del и Add]]
        }

        //Не нужно прописывать: 

        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            //Наведение на клавишу
        }
 
        public void mouseExited(MouseEvent e) {
			JButton button = (JButton) e.getSource();
			//Выход из зоны клавиши
		}
 
        public void mousePressed(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            //Зажатие клавиши
		}
 
        public void mouseReleased(MouseEvent e) {
            JButton button = (JButton) e.getSource();	
        	//Клавиша отпущена
        }
    
    }
}