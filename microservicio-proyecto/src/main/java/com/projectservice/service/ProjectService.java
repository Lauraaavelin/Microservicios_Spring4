package com.projectservice.service;

import com.projectservice.model.Project;
import com.projectservice.model.Report;
import com.projectservice.repository.ProjectRepository;
import com.projectservice.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ReportRepository reportRepository;

    public Project createProject(Project project){

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){

        return projectRepository.findAll();
    }

    public Project getProjectById(String id){

        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));
    }

    public Project updateProject(
            String id,
            Project updatedProject){

        Project existing =
                getProjectById(id);

        existing.setName(updatedProject.getName());
        existing.setDescription(
                updatedProject.getDescription());
        existing.setStatus(updatedProject.getStatus());

        return projectRepository.save(existing);
    }

    public void deleteProject(String id){

        projectRepository.deleteById(id);
    }

    public Double getProjectSaving(String projectId){

    List<Report> reports =
            reportRepository
                    .findByProyectoAsociado(projectId);

    return reports.stream()
            .filter(r -> r.getCreatedAt() != null)
            .max((r1, r2) ->
                    r1.getCreatedAt()
                            .compareTo(r2.getCreatedAt()))
            .map(Report::getAhorro)
            .orElse(0.0);
}
}
