package myUber;

import java.util.Date;

public class Message {
	private int ID;
	private String content;
	private Date date;
	
	private static int nextID = 1;

	public Message(String content, Date date) {
		super();
		this.content = content;
		this.date = date;
		this.ID = nextID;
		nextID++;
	}

	public int getID() {
		return ID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
