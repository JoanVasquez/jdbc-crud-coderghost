package com.tutorial.jdbc;

import java.util.List;

import com.tutorial.jdbc.dao.TodoDao;
import com.tutorial.jdbc.model.Todo;

public class Main {

	public static void main(String[] args) {
		TodoDao todoDao = new TodoDao();
		Todo todo = new Todo();
		todo.setTitle("Read a book of JS");
		todo.setCompleted(false);

		todo = todoDao.saveEntity(todo);

		System.out.println("Saved todo: " + "Id: " + todo.getId() + " Title: " + todo.getTitle() + " Is completed? "
				+ todo.isCompleted());

		boolean isUpdated = todoDao.updateEntity(todo);

		System.out.println("Was todo updated? " + isUpdated);

		List<Todo> todoList = todoDao.getEntities();
		System.out.println(todoList);

		boolean isDeleted = todoDao.deleteEntity(todo.getId());
		System.out.println("Was todo deleted? " + isDeleted);
	}

}
