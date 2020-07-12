package com.erickmob.schedulingjob.controller;

import com.erickmob.schedulingjob.Dto.JobsDTO;
import com.erickmob.schedulingjob.exceptions.TimeWindowException;
import com.erickmob.schedulingjob.model.Job;
import com.erickmob.schedulingjob.service.JobService;
import com.erickmob.schedulingjob.service.SchedulingService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@Api(tags = "SequenceJob")
public class JobController {

    @Autowired
    private SchedulingService schedulingService;

    @Autowired
    private JobService jobService;

    @PostMapping(path = "/sequenceJobs")
    public List<ArrayList> sequenceJobs(@RequestBody JobsDTO jobsDTO) {
        log.info("Received request with body:"+jobsDTO.toString());
        List<ArrayList> result;
        List<Job> jobsList = new ArrayList<>();
        try{
            if(!jobsDTO.getJobList().isEmpty()){
                jobsList = jobService.createFromJobListDto(jobsDTO.getJobList());
            }
            result = schedulingService.sortJobsForScheduling(jobsList, jobsDTO.getInicioJanelaDeExecucao(), jobsDTO.getFimJanelaDeExecucao());
            return result;
        }catch (TimeWindowException e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
    }
}
