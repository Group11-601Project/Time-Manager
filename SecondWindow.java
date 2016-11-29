import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SecondWindow extends JFrame {
    private JPanel panel= new JPanel();
    private JTextField titleField = new JTextField(300);
    private JTextField timeField = new JTextField(100);
    private JTextArea contentField = new JTextArea(300,255);
    private JButton okButton = new JButton("OK");
    private boolean okClicked = false;

    
    public SecondWindow() {
        super("New task");
        createGUI(panel);
    }

    public SecondWindow(int i, FirstWindow fw) {
        super(fw.getTask(i).getTitle());
        createGUI(fw.getPanel());
    }

    public void createGUI(JPanel p) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,10,5));
        setContentPane(panel);
        
        okButton.setPreferredSize(new Dimension(100, 80));
        panel.add(okButton);
        okButton.setBounds(590, 375, 100, 50);
        
        titleField.setBounds(10, 15, 475, 25);
        titleField.setToolTipText("Title");
        panel.add(titleField);

        timeField.setBounds(10 + 475 + 100 , 15, 120, 25);
        timeField.setToolTipText("HH.MM");
        panel.add(timeField);

        contentField.setBounds(10, 15 + 25 + 10, 450 + 100, 375);
        contentField.setToolTipText("Your task");
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        panel.add(contentField);

        //titleField.addKeyListener(new KeyAdapter());
        //timeField.addKeyListener(new KeyAdapter());
        //contentField.addKeyListener(new KeyAdapter());
        setPreferredSize(new Dimension(750, 500));
        pack();
    }

    public String watTitle() {
        return titleField.getText();
    }

    public String watTime() {
        return timeField.getText();
    }

    public String watContent() {
        return contentField.getText();
    }
/*
    public class KeyAdapter implements KeyListener {

        public void keyTyped(KeyEvent e) {
            //Alya: Реализовать действия при нажатии на клавишу в поле ввода, т.е. обработка ввода
        }
        
        public void keyPressed(KeyEvent e) {
            //Alya: Реализовать переход в новое окно при нажатии клавиши Enter [то же, что и Кнопка "OK"]
        }
        
        public void keyReleased(KeyEvent e) {

        }

    }
*/
    public class MouseAdapter implements MouseListener {
 
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            if (button == okButton) {
                okClicked = true;
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