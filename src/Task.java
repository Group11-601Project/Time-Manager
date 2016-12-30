import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

public class Task {
    private int mins;
    private int hours;
    private String title;
    private String content;

    public Task() {
        this("New task", "00:00", " ");
    }

    public Task(String title, String time, String content) {
        this.hours = Integer.parseInt(time.substring(0, 2));
        this.mins = Integer.parseInt(time.substring(3, 5));
        this.title = title;
        this.content = content;
    }

    public void createNewFile() {
        File f = new File("res\\" + this.title + ".txt");
        boolean create = true;
        try {
            if (f.exists()) {
                Task t = readFile(f.getName());
                if (!Task.this.equals(t)) {
                    if (!this.getContent().equals(t.getContent())) {
                        removeFile(t.title + ".txt");
                        f = new File("res\\" + this.title + ".txt");
                        create = true;
                    } else
                        create = false;
                } else
                    create = false;
            }
            if (create) {
                if (f.createNewFile()) {
                    FileWriter writer = new FileWriter(f, false);
                    writer.write(title);
                    writer.append('\n');
                    writer.write(getTime());
                    writer.append('\n');
                    writer.write(content);
                    writer.flush();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Task readFile(String name) {
        File f = new File("res\\" + name);
        StringBuilder ct = new StringBuilder();
        String tt = "";
        String tm = "";
        String s;
        int k = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            try {
                while ((s = in.readLine()) != null) {
                    if (k == 0) {
                        tt = s;
                    } else {
                        if (k == 1) {
                            tm = s;
                        } else {
                            ct.append(s);
                            ct.append("\n");
                        }
                    }
                    k++;
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (new Task(tt, tm, ct.toString()));
    }

    public static void removeFile(String name) {
        File f = new File("res\\" + name);
        try {
            Files.delete(f.toPath());
        } catch (NoSuchFileException x) {
            System.err.format("No such file in ", f.getPath());
        } catch (IOException x) {
            System.err.println(x.getMessage());
        }
    }

    public String getTime() {
        String time;
        if (this.hours / 10 == 0) {
            if (this.mins / 10 == 0) {
                time = "0" + this.hours + ":" + "0" + this.mins;
            } else {
                time = "0" + this.hours + ":" + this.mins;
            }
        } else {
            if (this.mins / 10 == 0) {
                time = this.hours + ":" + "0" + this.mins;
            } else {
                time = this.hours + ":" + this.mins;
            }
        }
        return time;
    }

    public int getMins() {
        return mins;
    }

    public int getHours() {
        return hours;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return getTime() + " | " + title;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (mins != task.mins) return false;
        if (hours != task.hours) return false;
        if (!title.equals(task.title)) return false;
        return true;
    }

    public int hashCode() {
        int result = mins;
        result = 31 * result + hours;
        result = 31 * result + title.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }
}