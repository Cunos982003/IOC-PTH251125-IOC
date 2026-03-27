package re.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.edu.model.Task;
import re.edu.model.User;
import re.edu.repositories.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
    public Task createTask(Task task) {
        User user = userService.findUserById(task.getAssignedTo());
        if (user == null) {
            return null;
        }

        return taskRepository.save(task);
    }

    public Task updateTask(int id, Task task) {
        return taskRepository.update(id, task);
    }

    public Task deleteTask(int id) {
        return taskRepository.deleteById(id);
    }


}
