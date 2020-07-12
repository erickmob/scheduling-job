package com.erickmob.schedulingjob.service;

import com.erickmob.schedulingjob.exceptions.TimeWindowException;
import com.erickmob.schedulingjob.model.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SchedulingService {

    private static Duration maxDurationHour = Duration.ofHours(8);

    private LocalDateTime executionTimeWindowStart = LocalDateTime.of(
            2019,
            Month.NOVEMBER,
            10,
            9,
            0,
            0);

    private  LocalDateTime executionTimeWindowEnd = LocalDateTime.of(
            2019,
            Month.NOVEMBER,
            11,
            12,
            0,
            0);

    public List<ArrayList> sortJobsForScheduling(List<Job> jobsList, LocalDateTime inicioJanelaDeExecucao, LocalDateTime fimJanelaDeExecucao) throws TimeWindowException {

        inicioJanelaDeExecucao = getValidDate(inicioJanelaDeExecucao, executionTimeWindowStart);
        fimJanelaDeExecucao = getValidDate(fimJanelaDeExecucao, executionTimeWindowEnd);

        validateTimeWindow(inicioJanelaDeExecucao, fimJanelaDeExecucao);

        jobsList = sortListByDateAndFilter(jobsList, inicioJanelaDeExecucao, fimJanelaDeExecucao);

        return createJobExecuteSequence(jobsList , inicioJanelaDeExecucao, fimJanelaDeExecucao);
    }

    LocalDateTime getValidDate(LocalDateTime receivedDate, LocalDateTime defaultDate) {
        return receivedDate == null ? defaultDate : receivedDate;
    }

    void validateTimeWindow(LocalDateTime inicioJanelaDeExecucao, LocalDateTime fimJanelaDeExecucao) throws TimeWindowException {

        if(inicioJanelaDeExecucao.isAfter(fimJanelaDeExecucao)){
            throw new TimeWindowException("Invalid Window Time");
        }

        if(inicioJanelaDeExecucao.isEqual(fimJanelaDeExecucao)){
            throw new TimeWindowException("Time Window too short");
        }
    }


    List<Job>  sortListByDateAndFilter(List<Job> jobsList, LocalDateTime inicioJanelaDeExecucao, LocalDateTime fimJanelaDeExecucao) {
        return  jobsList.stream()
                .filter(job -> job.getTempoEstimado().toHours() < maxDurationHour.toHours())
                .filter(job -> conlusionInsideTimeWindow(job, inicioJanelaDeExecucao, fimJanelaDeExecucao))
                .filter(job -> maxEstimatedDateInsideTimeWindow(job, inicioJanelaDeExecucao))
                .sorted(
                        Comparator.comparing(Job::getDataMaximaDeConclusao)
                )
                .collect(Collectors.toList());

    }

    boolean conlusionInsideTimeWindow(Job job, LocalDateTime inicioJanelaDeExecucao, LocalDateTime fimJanelaDeExecucao) {
        LocalDateTime estimateConcludion = inicioJanelaDeExecucao.plusHours(job.getTempoEstimado().toHours());
        Duration duration = Duration.between(estimateConcludion, fimJanelaDeExecucao);
        return  duration.toHours() > 0;
    }

    boolean maxEstimatedDateInsideTimeWindow(Job job, LocalDateTime inicioJanelaDeExecucao) {
        return  job.getDataMaximaDeConclusao().isAfter(inicioJanelaDeExecucao);
    }

    private List<ArrayList> createJobExecuteSequence(List<Job> jobsList, LocalDateTime inicioJanelaDeExecucao, LocalDateTime fimJanelaDeExecucao) {

        ArrayList<ArrayList> resultList = new ArrayList<>();
        ArrayList<Long> eightHoursArray = new ArrayList<>();

        long countTotalHoursForArray = 0;

        LocalDateTime currentDateTime = inicioJanelaDeExecucao;
        LocalDateTime estimateJobConclusionDate;

        for(Job job : jobsList){

            estimateJobConclusionDate = currentDateTime.plusHours(job.getTempoEstimado().toHours());

            if(estimateJobConclusionDate.isAfter(fimJanelaDeExecucao)){
                break;
            }
            if(estimateJobConclusionDate.isAfter(job.getDataMaximaDeConclusao())){
                break;
            }

            if(jobFitsInsideEightHoursArray(countTotalHoursForArray, job)){
                eightHoursArray.add(job.getId());
                countTotalHoursForArray += job.getTempoEstimado().toHours();
            }else{
                addEightHourArrayToResultList(resultList, eightHoursArray);
                eightHoursArray = new ArrayList<>();
                eightHoursArray.add(job.getId());
                countTotalHoursForArray = job.getTempoEstimado().toHours();
            }
            currentDateTime = currentDateTime.plusHours(job.getTempoEstimado().toHours());

        }

        addEightHourArrayToResultList(resultList, eightHoursArray);

        return resultList;
    }

    private void addEightHourArrayToResultList(ArrayList<ArrayList> expectedList, ArrayList<Long> longArray) {
        if (longArray.size() > 0) {
            expectedList.add(longArray);
        }
    }

    private boolean jobFitsInsideEightHoursArray(long totalHoursForArray, Job job) {
        return (totalHoursForArray + job.getTempoEstimado().toHours()) <= maxDurationHour.toHours();
    }
}
