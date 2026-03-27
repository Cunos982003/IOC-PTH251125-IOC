package re.edu.repositories;

import re.edu.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();
    User save(User User);
    User findById(int id);
    User update(int id, User User);
    User deleteById(int id);
}
