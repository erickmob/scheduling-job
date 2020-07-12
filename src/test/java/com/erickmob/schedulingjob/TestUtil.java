package com.erickmob.schedulingjob;

import com.erickmob.schedulingjob.Dto.JobDTO;
import com.erickmob.schedulingjob.Dto.JobsDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class TestUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }

    public static JobsDTO getValidContent() {

        JobsDTO jobsDTO = new JobsDTO();
        jobsDTO.setInicioJanelaDeExecucao(LocalDateTime.of(
                2019,
                Month.NOVEMBER,
                10,
                9,
                0,
                0));

        jobsDTO.setFimJanelaDeExecucao(LocalDateTime.of(
                2019,
                Month.NOVEMBER,
                11,
                12,
                0,
                0));

        jobsDTO.setJobList(new ArrayList<>());

        jobsDTO.getJobList().add(new JobDTO(
                1,
                "Importação de arquivos de fundos",
                LocalDateTime.of(
                        2019,
                        Month.NOVEMBER,
                        20,
                        12,
                        0,
                        0),
                2
        ));

        jobsDTO.getJobList().add(new JobDTO(
                2,
                "Importação de dados de integração",
                LocalDateTime.of(
                        2019,
                        Month.NOVEMBER,
                        11,
                        12,
                        0,
                        0),
                4
        ));

        jobsDTO.getJobList().add(new JobDTO(
                3,
                "",
                LocalDateTime.of(
                        2019,
                        Month.NOVEMBER,
                        11,
                        8,
                        0,
                        0),
                6
        ));

        return jobsDTO;
    }
}
