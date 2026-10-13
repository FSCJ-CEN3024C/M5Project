package edu.fscj.cen3024c.taskmanager.services;

import edu.fscj.cen3024c.taskmanager.dto.TaskDTO;
import edu.fscj.cen3024c.taskmanager.exceptions.TaskNotFoundException;
import edu.fscj.cen3024c.taskmanager.entities.Task;
import edu.fscj.cen3024c.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    @Transactional(readOnly = true)

    public Task findById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }

    public TaskDTO toDTO(Task task) {  Integer priorityId = null;
        String priorityLevel = null;

        if (task.getPriority() != null) {
            Long id = task.getPriority().getId();

            if (id != null && id <= Integer.MAX_VALUE) {
                priorityId = id.intValue();
            }

            if (task.getPriority().getLevel() != null) {
                priorityLevel = task.getPriority().getLevel().name();
            }
        }

        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                priorityId,
                priorityLevel
        );
    }

    public List<TaskDTO> findAllDTOs() {
        return taskRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public TaskDTO findDTOById(Integer id) {
        return toDTO(findById(id));}
}
