package edu.fscj.cen3024c.taskmanager.entities;

import edu.fscj.cen3024c.taskmanager.enums.PriorityLevel;
import jakarta.persistence.*;

@Entity
@Table(name = "priorities")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PriorityLevel level;


public Priority() {

}
    public Priority(PriorityLevel level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriorityLevel getLevel() {
        return level;
    }

    public void setLevel(PriorityLevel level) {
        this.level = level;
    }

}
