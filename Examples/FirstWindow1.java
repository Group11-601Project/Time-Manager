import javax.swing.*;
import java.awt.*;

public class FirstWindow extends JFrame {
	
	public FirstWindow() {
		super("Task Manager v0.1");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.add(new JButton("Big"), BorderLayout.NORTH);
		panel.add(new JButton("Big"), BorderLayout.SOUTH);
		setSize(400, 400);
	}

}