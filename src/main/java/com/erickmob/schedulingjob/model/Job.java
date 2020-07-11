package com.erickmob.schedulingjob.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Job {
    private long id;
    private String descricao;
    private LocalDateTime dataMaximaDeDuracao;
    private Duration tempoEstimado;
}
