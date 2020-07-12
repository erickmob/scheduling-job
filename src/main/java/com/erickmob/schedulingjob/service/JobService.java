package com.erickmob.schedulingjob.service;

import com.erickmob.schedulingjob.Dto.JobDTO;
import com.erickmob.schedulingjob.model.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JobService {

    public List<Job> createFromJobListDto(List<JobDTO> jobDTOList){
        return jobDTOList.stream()
                .map( jobDTO ->
                        new Job(
                                jobDTO.getId(),
                                jobDTO.getDescricao(),
                                jobDTO.getDataMaximaDeDuracao(),
                                Duration.ofHours(jobDTO.getTempoEstimado())
                        )
                )
                .collect(Collectors.toList());
    }

}
