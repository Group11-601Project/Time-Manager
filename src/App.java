public class App {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FirstWindow fw = new FirstWindow();
                fw.setLocationRelativeTo(null);
                fw.setVisible(true);
                SecondWindow sw = new SecondWindow(new Task(true), fw.getPanel());
                sw.setVisible(true);
             /*   sw.setVisible(false);
                if (fw.isAddClicked()) {
                    sw = new SecondWindow(new Task(true), fw);
                    sw.setVisible(true);
                } else {
                    if (fw.isTaskClicked()) {
                        sw = new SecondWindow(fw.getTaskList().get(fw.getTaskIndex()), fw);
                        sw.setVisible(true);
                    }
                } */
                sw.setLocationRelativeTo(null);
              /*  if (fw.isAddClicked()) {
                    if (sw.saveTask()) {
                        Task t = sw.getFields(true);
                        fw.getTaskList().setTaskPlace(t);
                    }
                } */
            }
        });
    }
}