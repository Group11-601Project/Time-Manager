import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SecondWindow extends JFrame {

    public SecondWindow() {
        super("Новая задача");
        createGUI(new JPanel());
    }

    public SecondWindow(int i, FirstWindow fw) {
        super(fw.getTask(i).getTitle());
        createGUI(fw.getPanel());
    }

    public void createGUI(JPanel p) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Eldar: Создание окна, панелей и полей с их размерами, положением.
        titleField.addKeyListener(new KeyAdapter());
        timeField.addKeyListener(new KeyAdapter());
        contentField.addKeyListener(new KeyAdapter());
        setPreferredSize(new Dimension(750, 500));  
        pack();
    }

    //Alya:
    public void changeTitle() {
        //Изменение заголовка задачи.
    }

    public void changeTime() {
        //Изменение времени задачи.
    }

    public void changeContent() {
        //Изменение содержимого задачи.
    }

    public void changeWindow() {
        //Не прописывать. Заглушка для перехода в другое окно [Клавиша OK]
    }

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

    public class MouseAdapter implements MouseListener {
 
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            //Bulat: Реализовать клик на поле, на кнопку OK.
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