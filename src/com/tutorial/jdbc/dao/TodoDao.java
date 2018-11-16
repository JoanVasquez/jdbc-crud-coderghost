package com.tutorial.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tutorial.jdbc.connection.DBConnection;
import com.tutorial.jdbc.crud.Crud;
import com.tutorial.jdbc.model.Todo;
import com.tutorial.jdbc.queries.Queries;

public class TodoDao extends Queries implements Crud<Todo> {

	private DBConnection dbConnection;
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;

	public TodoDao() {
		dbConnection = DBConnection.openConnection();
		dbConnection.closeConnection();
	}

	@Override
	public Todo saveEntity(Todo entity) {
		dbConnection = DBConnection.openConnection();
		try {
			int todoId = 0;
			prepareStatement = dbConnection.getConnection().prepareStatement(SAVE_TODO,
					Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, entity.getTitle());
			prepareStatement.setBoolean(2, entity.isCompleted());

			prepareStatement.executeUpdate();

			resultSet = prepareStatement.getGeneratedKeys();
			if (resultSet.next()) {
				todoId = resultSet.getInt(1);
				entity.setId(todoId);
			}

			resultSet.close();
			prepareStatement.close();
			dbConnection.closeConnection();

		} catch (Exception e) {
			System.out.println("Error while saving the todo: " + e.getMessage());
		}
		return entity;
	}

	@Override
	public boolean updateEntity(Todo entity) {
		dbConnection = DBConnection.openConnection();
		boolean isUpdated = false;
		try {
			prepareStatement = dbConnection.getConnection().prepareStatement(UPDATE_TODO);
			prepareStatement.setString(1, entity.getTitle());
			prepareStatement.setBoolean(2, entity.isCompleted());
			prepareStatement.setInt(3, entity.getId());

			prepareStatement.executeUpdate();

			isUpdated = true;
			prepareStatement.close();

		} catch (Exception e) {
			System.out.println("Error while updating the todo: " + e.getMessage());
		} finally {
			dbConnection.closeConnection();
		}
		return isUpdated;
	}

	@Override
	public boolean deleteEntity(int id) {
		dbConnection = DBConnection.openConnection();
		boolean isDeleted = false;

		try {
			prepareStatement = dbConnection.getConnection().prepareStatement(DELETE_TODO);
			prepareStatement.setInt(1, id);

			prepareStatement.executeUpdate();
			prepareStatement.close();
			isDeleted = true;
		} catch (Exception e) {
			System.out.println("Error while deleting the todo: " + e.getMessage());
		} finally {
			dbConnection.closeConnection();
		}

		return isDeleted;
	}

	@Override
	public List<Todo> getEntities() {
		dbConnection = DBConnection.openConnection();
		List<Todo> listTodo = new ArrayList<>();
		try {
			prepareStatement = dbConnection.getConnection().prepareStatement(READ_TODO);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Todo todo = new Todo();
				todo.setId(resultSet.getInt(1));
				todo.setTitle(resultSet.getString(2));
				todo.setCompleted(resultSet.getBoolean(3));
				listTodo.add(todo);

			}
			resultSet.close();
			prepareStatement.close();
		} catch (Exception e) {
			System.out.println("Error while reading the todo: " + e.getMessage());
		} finally {
			dbConnection.closeConnection();
		}
		return listTodo;
	}

}
