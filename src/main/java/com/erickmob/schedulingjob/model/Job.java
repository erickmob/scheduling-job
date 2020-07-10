package com.erickmob.schedulingjob.model;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class Job {
    private long ID;
    private String descricao;
    private LocalDate dataMaximaDeDuracao;
    private Duration tempoEstimado;
}
