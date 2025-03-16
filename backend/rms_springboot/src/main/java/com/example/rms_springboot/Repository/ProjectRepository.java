package com.example.rms_springboot.Repository;

import com.example.rms_springboot.DataModel.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
