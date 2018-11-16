package com.tutorial.jdbc.queries;

public abstract class Queries {
	protected final static String SAVE_TODO = "INSERT INTO tbl_todo(title, completed) VALUES(?, ?)";
	protected final static String UPDATE_TODO = "UPDATE tbl_todo SET tbl_todo.title = ?, tbl_todo.completed = ? WHERE tbl_todo.id = ?";
	protected final static String READ_TODO = "SELECT * FROM tbl_todo";
	protected final static String DELETE_TODO = "DELETE FROM tbl_todo WHERE tbl_todo.id = ?";
}
