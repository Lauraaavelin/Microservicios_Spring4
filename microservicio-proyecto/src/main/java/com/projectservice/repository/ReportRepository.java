package com.projectservice.repository;

import com.projectservice.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository
extends MongoRepository<Report, String> {

    List<Report>
    findByProyectoAsociado(String proyecto_asociado);
}

