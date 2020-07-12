package com.erickmob.schedulingjob.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private long id;
    private String descricao;
    private LocalDateTime dataMaximaDeConclusao;
    private long tempoEstimado;
}
