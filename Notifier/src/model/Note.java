package model;

public class Note {
	private String email;
	private String notebookname;
	private String note;
	private String descr;
	private String remdate;
	
	public Note() {}
	public Note(String email, String notebookname, String note, String descr) {
		super();
		this.email = email;
		this.notebookname = notebookname;
		this.note = note;
		this.descr = descr;
	}
	
	
	public Note(String email, String notebookname, String note, String descr, String remdate) {
		super();
		this.email = email;
		this.notebookname = notebookname;
		this.note = note;
		this.descr = descr;
		this.remdate = remdate;
	}


	public String getEmail() {
		return email;
	}
	public String getRemdate() {
		return remdate;
	}


	public void setRemdate(String remdate) {
		this.remdate = remdate;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNotebookname() {
		return notebookname;
	}
	public void setNotebookname(String notebookname) {
		this.notebookname = notebookname;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDesc() {
		return descr;
	}
	public void setDesc(String descr) {
		this.descr = descr;
	}
}
