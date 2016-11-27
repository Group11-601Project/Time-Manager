import java.util.ArrayList;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class App {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
				FirstWindow fw = new FirstWindow();
				//Добавление новой задачи. Реализовано не до конца
				Task t = new Task("To-do", "11:50", "Buy tickets");
				fw.add(t);
				fw.setLocationRelativeTo(null);
        		fw.setVisible(true);
        		//Тут будет работа самой программы.
        	}
        });
	}
}