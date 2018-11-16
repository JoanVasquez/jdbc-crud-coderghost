package com.tutorial.jdbc.crud;

import java.util.List;

public interface Crud<Entity> {
	public Entity saveEntity(Entity entity);

	public boolean updateEntity(Entity entity);

	public boolean deleteEntity(int id);

	List<Entity> getEntities();
}