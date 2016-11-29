import java.util.ArrayList;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class App {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
				FirstWindow fw = new FirstWindow();

				fw.setLocationRelativeTo(null);
        		fw.setVisible(true);

				SecondWindow sw = new SecondWindow();

				sw.setLocationRelativeTo(null);
        		sw.setVisible(true);

        	}
        });
	}
}