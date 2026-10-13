package edu.fscj.cen3024c.taskmanager.services;

import edu.fscj.cen3024c.taskmanager.dto.SubtaskDTO;
import edu.fscj.cen3024c.taskmanager.entities.Subtask;
import edu.fscj.cen3024c.taskmanager.entities.Task;
import edu.fscj.cen3024c.taskmanager.enums.SubtaskStatus;
import edu.fscj.cen3024c.taskmanager.repositories.SubtaskRepository;
import edu.fscj.cen3024c.taskmanager.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubtaskService {

    @Autowired
    private SubtaskRepository subtaskRepository;

    @Autowired
    private TaskRepository taskRepository;

    private SubtaskDTO toDTO(Subtask subtask) {
        Integer taskId = subtask.getTask() != null
                ? subtask.getTask().getId()
                : null;

        return new SubtaskDTO(
                subtask.getId(),
                subtask.getTitle(),
                subtask.getStatus() != null
                ? subtask.getStatus().name()
                        : null,
                taskId
        );
    }
    @Transactional(readOnly = true)
    public List<SubtaskDTO> findAllDTOs() {
        return subtaskRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    public SubtaskDTO findDTOById(Integer id) {
        Subtask subtask = subtaskRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException(
                                "Subtask not found: " + id));
        return toDTO(subtask);
    }

    public SubtaskDTO create(SubtaskDTO dto) {

        Subtask subtask = new Subtask();
        subtask.setTitle(dto.getTitle());
        subtask.setStatus(
                SubtaskStatus.valueOf(dto.getStatus()));
        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Task not found: " + dto.getTaskId()));

        subtask.setTask(task);
        return toDTO(subtaskRepository.save(subtask));
    }
    public SubtaskDTO update(Integer id, SubtaskDTO dto) {
        Subtask subtask = subtaskRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException(
                                "Subtask not found: " + id
                        ));
        subtask.setTitle(dto.getTitle());
        subtask.setStatus(
                SubtaskStatus.valueOf(dto.getStatus())
        );
        if (dto.getTaskId() != null) {
            Task task = taskRepository.findById(dto.getTaskId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Task not found: " + dto.getTaskId()
                            ));
            subtask.setTask(task);
        }
        return toDTO(subtaskRepository.save(subtask));
    }
    public void deletebyId(Integer id) {
        if(!subtaskRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Subtask not found: " + id
            );
        }
        subtaskRepository.deleteById(id);
    }

}
