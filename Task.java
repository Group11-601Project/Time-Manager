import java.io.*;
import java.nio.file.*;

public class Task {
	String content;
	String title;
	String time;
	String name = title + ".txt";
	File file = new File(name);
		
	public Task() {
		this.content = "Содержимое заметки";
		this.title = "Новая задача";
		this.time = "ЧЧ.ММ";
		createNewTask();
	}
	
	public Task(String tt, String tm) {
		this.title = tt;
		this.time = tm;
		this.content = "-";
		createNewTask();
	}

	public Task(String tt, String tm, String ct) {
		this.title = tt;
		this.time = tm;
		this.content = ct;
		createNewTask();
	}

	public void createNewTask() {
		try (FileWriter writer = new FileWriter(name, false)) {
			writer.write(time); 
			writer.append('\n');
			writer.write(title);
			writer.append('\n');
			writer.write(content);
			writer.flush();
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}

	public String getTitle() {
		return this.title;
	}

	public String getTime() {
		return this.time;
	}

	public String getContent() {
		return this.content;
	}

	public File getFile() {
		return this.file;
	}	
}