package re.edu.session3.repositories;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T> {
    List<T> findAll();
    Optional<T> findById(long id);
    T create(T t);
    T update(int id, T t);
    T deleteById(int id);
}
