package ua.lviv.ura.shared;

import java.util.List;

public interface AbstractCRUD <T> {
	T create(T t);
	List<T> readAll();
	T read(int id);
	T update(T t);
	void delete(int id);
}
