import javax.swing.*;
import java.awt.*;

public class SimpleWindow extends JFrame {
    SimpleWindow() {


        super("Пробное окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box box = Box.createVerticalBox();
        box.add(new JButton("Кнопка"));
        box.add(Box.createVerticalStrut(10));
        box.add(new JButton("+"));
        JButton rightButton = new JButton("-");
        rightButton.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        rightButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        box.add(rightButton);
        box.add(Box.createVerticalStrut(10));
        box.add(new JButton("Кнопка с длинной надписью"));
        setContentPane(box);
        setSize(600, 400);
    }
}