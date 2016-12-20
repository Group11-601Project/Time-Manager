public class App {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FirstWindow fw = new FirstWindow();
                fw.setLocationRelativeTo(null);
                fw.setVisible(true);
            }
        });
    }
}