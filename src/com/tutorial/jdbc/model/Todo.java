package com.tutorial.jdbc.model;

public class Todo {

	private int id;
	private String title;
	private boolean completed;

	public Todo() {}

	public Todo(int id, String title, boolean completed) {
		super();
		this.id = id;
		this.title = title;
		this.completed = completed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", completed=" + completed + "]";
	}
	
}
