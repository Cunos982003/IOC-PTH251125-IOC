package re.edu.repositories;

import org.springframework.stereotype.Repository;
import re.edu.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TaskRepository implements ITaskRepository {

    private List<Task> tasks = new ArrayList<>(List.of(
            new Task(1, "Fix bug", "Fix login bug", "HIGH", 1),
            new Task(2, "Write API", "Create user API", "MEDIUM", 2),
            new Task(3, "Test app", "Manual testing", "LOW", 3),
            new Task(4, "Deploy", "Deploy to server", "HIGH", 1),
            new Task(5, "Optimize DB", "Improve query", "MEDIUM", 2),
            new Task(6, "Refactor code", "Clean code", "LOW", 3),
            new Task(7, "Design UI", "Homepage UI", "HIGH", 1),
            new Task(8, "Write docs", "API docs", "LOW", 2),
            new Task(9, "Fix CSS", "UI bug", "MEDIUM", 3),
            new Task(10, "Security check", "Fix auth", "HIGH", 1)
    ));

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public Task save(Task task) {
        task.setId((tasks.size() + 1));
        tasks.add(task);
        return task;
    }

    @Override
    public Task findById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Task update(int id, Task task) {
        Task existing = findById(id);

        if (existing == null) return null;

        existing.setTitle(task.getTitle());
        existing.setAssignedTo(task.getAssignedTo());

        return existing;
    }

    @Override
    public Task deleteById(int id) {
        Task existing = findById(id);

        if (existing == null) return null;

        tasks.remove(existing);
        return existing;
    }
}
