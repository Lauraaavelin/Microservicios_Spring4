package com.projectservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reports")
public class Report {

    @Id
    private String reporte_id;

    @Field("proyecto_asociado")
    private String proyectoAsociado;

    private String tipo;

    private Double total_facturado;
    private Double gasto_cpu;
    private Double gasto_bases_de_datos;
    private Double ahorro;

    private Date createdAt;
}
