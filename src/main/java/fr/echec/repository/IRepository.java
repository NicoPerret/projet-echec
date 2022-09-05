package fr.echec.repository;

import java.util.List;

public interface IRepository <T, Id> {
	public T findById(Id id);

	public List<T> findAll();

	public void save(T entity);

	public void deleteById(Id id);

}
