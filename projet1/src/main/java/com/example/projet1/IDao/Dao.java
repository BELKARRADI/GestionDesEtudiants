package com.example.projet1.IDao;

import java.util.List;

public interface Dao <T> {
	
	T create (T o);
	boolean  delete(T o);
	T update(T o);
	List<T> findAll();
	T findById (int id);

}
