package com.erickmob.schedulingjob.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobsDTO {

    private LocalDateTime inicioJanelaDeExecucao;
    private LocalDateTime fimJanelaDeExecucao;
    List<JobDTO> jobList;

}
