 import java.io.File;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void setTaskPlace(File file) {
        setTaskPlace(Task.readFile(file.getName()));
    }

    public void setTaskPlace(Task t) {
        Task task;
        int num;
        int i = 0;
        int tempMins;
        int tempHours;
        boolean taskAdded = false;
        for (i = 0; ((i < tasks.size()) && (!taskAdded)); i++) {
            if (tasks.size() == 0) {
                add(0, t);
                taskAdded = true;
                continue;
            }
            task = tasks.get(i);
            tempHours = task.getHours();
            tempMins = task.getMins();
            if (tempHours == t.getHours()) {
                if (tempMins == t.getMins()) {
                    num = tasks.indexOf(task);
                    remove(num);
                    add(num, t);
                    taskAdded = true;
                }
            }
        }
    }

    public void add(int i, Task task) {
        tasks.add(i, task);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
