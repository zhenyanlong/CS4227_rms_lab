package com.example.rms_springboot.DataModel;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private Long id;

    public Project(String title, String description, String filePath, User createdBy) {
        this.title = title;
        this.description = description;
        this.filePath = filePath;
        this.createdBy = createdBy;
    }

    public Project() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    private String description;
    private String filePath; // File storage path

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;
}