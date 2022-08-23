package fr.echec.repository;

import java.util.List;

public interface IRepository <T> {
	public T findById(int id);

	public List<T> findAll();

	public void save(T entity);

	public void deleteById(int id);

}
