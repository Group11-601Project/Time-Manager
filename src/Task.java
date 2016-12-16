import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.valueOf;

public class Task {
    private int mins;
    private int hours;
    private String title;
    private String content;
    private String name = "C:\\Users\\User\\Git\\Projects\\Hack Sprint 2\\res\\" + title + ".txt";

    public Task(boolean write) {
        this("New task", "00:00", "Put your thoughts here", write);
    }

    public Task(String title, String time, boolean write) {
        this(title, time, " ", write);
    }

    public Task(String title, String time, String content, boolean write) {
        Pattern p = Pattern.compile("(?<hours>[0-1][0-9]|2[0-3]):(?<minutes>[0-5][0-9])");
        Matcher m = p.matcher(time);
        this.hours = valueOf(m.group("hours"));
        this.mins = valueOf(m.group("minutes"));
        this.title = title;
        this.content = content;
        if (write) {
            createNewFile();
        }
    }

    private void createNewFile() {
        try (FileWriter writer = new FileWriter(name, false)) {
            writer.write(hours + ":" + mins);
            writer.append('\n');
            writer.write(title);
            writer.append('\n');
            writer.write(content);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Task readFile(String name) {
        int k = 0;
        String s;
        String tt = "";
        String tm = "";
        StringBuilder ct = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(name));
            try {
                while ((s = in.readLine()) != null) {
                    if (k == 0) {
                        tm = s;
                    } else {
                        if (k == 1) {
                            tt = s;
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
        return (new Task(tt, tm, ct.toString(), false));
    }

    public int getMins() {
        return mins;
    }

    public int getHours() {
        return hours;
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

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return getTime() + " | " + title;
    }
}