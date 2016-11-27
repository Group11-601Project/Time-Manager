public class Task {
	String title;
	String time;
	String content;

	public Task() {
		this.title = "Новая задача";
		this.time = "ЧЧ.ММ";
		this.content = "Содержимое заметки";
	}

	public Task(String tt, String tm, String ct) {
		this.title = tt;
		this.time = tm;
		this.content = ct;
	}

	public void setTitle(String tt) {
		this.title = tt;
	}

	public void setTime(String tm) {
		this.time = tm;
	}

	public void setContent(String ct) {
		this.content = ct;
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

	public String toString() {
		return "" + time + "	" + title;
	}
}