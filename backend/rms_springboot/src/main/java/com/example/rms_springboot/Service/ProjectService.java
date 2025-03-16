package com.example.rms_springboot.Service;

// src/main/java/com/rms/service/ProjectService.java

import com.example.rms_springboot.DataModel.Project;
import com.example.rms_springboot.DataModel.User;
import com.example.rms_springboot.Repository.ProjectRepository;
import com.example.rms_springboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final String FILE_STORAGE_PATH = "uploads/"; // File storage path

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;

        // Ensure that the upload directory exists
        new File(FILE_STORAGE_PATH).mkdirs();
    }

    // get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Create a new project (including file upload)
    public Project createProject(String title, String description, MultipartFile file, User user) {
        // File storage logic (keep previous implementation)
        String fileName = storeFile(file);

        Project project = new Project(title,description,fileName,user);

        return projectRepository.save(project);
    }

    // File storage method
    private String storeFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("The uploaded file cannot be empty");
        }

        try {
            // Generate unique file names (prevent overwriting)
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(FILE_STORAGE_PATH + fileName);

            // Save the file locally
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("File storage failure: " + e.getMessage());
        }
    }
}