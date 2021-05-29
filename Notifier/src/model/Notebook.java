package model;

public class Notebook {
	private String email;
	private String notebookname;
	private int notes;
	public String getEmail() {
		return email;
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
	public int getNotes() {
		return notes;
	}
	public void setNotes(int notes) {
		this.notes = notes;
	}
	
	public Notebook(String email, String notebookname, int notes) {
		this.email = email;
		this.notebookname = notebookname;
		this.notes = notes;
	}
	public Notebook() {}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "notebookname:"+notebookname+"notes:"+notes;
	}
}
