package re.edu.repositories;

import re.edu.model.Task;

import java.util.List;

public interface ITaskRepository {
    List<Task> findAll();
    Task save(Task task);
    Task findById(int id);
    Task update(int id, Task task);      // update
    Task deleteById(int id);           // delete
}
