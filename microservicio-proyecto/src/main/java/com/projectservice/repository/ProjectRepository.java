package com.projectservice.repository;

import com.projectservice.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository
extends MongoRepository<Project, String> {
}
