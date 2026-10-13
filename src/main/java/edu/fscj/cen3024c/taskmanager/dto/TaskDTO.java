package edu.fscj.cen3024c.taskmanager.dto;

public class TaskDTO {
    private Integer id;
    private String title;
    private String description;
    private String status;
    private String dueDate;
    private Integer priorityId;
    private String priorityLevel;

    public TaskDTO() {
    }

    public TaskDTO(Integer id, String title, String description,
                   String status, String dueDate,
                   Integer priorityId, String priorityLevel) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priorityId = priorityId;
        this.priorityLevel = priorityLevel;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public Integer getPriorityId() { return priorityId; }
    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public String getPriorityLevel() { return priorityLevel; }
    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

}
