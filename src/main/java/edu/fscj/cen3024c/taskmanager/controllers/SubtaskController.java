package edu.fscj.cen3024c.taskmanager.controllers;

import edu.fscj.cen3024c.taskmanager.dto.SubtaskDTO;
import edu.fscj.cen3024c.taskmanager.services.SubtaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/subtask")
public class SubtaskController {

    @Autowired
    private SubtaskService subtaskService;

    @GetMapping
    public List<SubtaskDTO> getAllSubtasks() {
        return subtaskService.findAllDTOs();
    }
    @GetMapping("/{id}")
    public SubtaskDTO getSubtaskById(@PathVariable Integer id) {
        return subtaskService.findDTOById(id);
    }

    @PostMapping
    public SubtaskDTO createSubtask(@RequestBody SubtaskDTO dto) {
        return subtaskService.create(dto);
    }
    @PostMapping("/{id}")
    public SubtaskDTO updateSubtask(
            @PathVariable Integer id,
            @RequestBody SubtaskDTO dto) {
        return subtaskService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable Integer id) {
        subtaskService.deletebyId(id);
        return ResponseEntity.noContent().build();
    }
}
