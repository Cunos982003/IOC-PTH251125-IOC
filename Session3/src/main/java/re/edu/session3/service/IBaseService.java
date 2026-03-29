package re.edu.session3.service;

import java.util.List;

public interface IBaseService<T> {

    List<T> getAll();

    T getById(long id);

    T create(T t);

    T update(int id, T t);

    T deleteById(int id);
}
