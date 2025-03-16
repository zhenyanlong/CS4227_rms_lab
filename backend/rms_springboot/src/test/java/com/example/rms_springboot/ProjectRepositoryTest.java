package com.example.rms_springboot;

import com.example.rms_springboot.DataModel.Project;
import com.example.rms_springboot.Repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testSaveProject() {
        Project project = new Project();
        project.setTitle("JUnit Test Project");
        //Project savedProject = projectRepository.save(project);

    }
}