package com.erickmob.schedulingjob.service;

import com.erickmob.schedulingjob.exceptions.TimeWindowException;
import com.erickmob.schedulingjob.model.Job;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SchedulingServiceTest {

    @Autowired
    private SchedulingService schedulingService;

    private List<Job> jobsList;

    private  LocalDateTime executionTimeWindowStart = LocalDateTime.of(
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


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void whenValidDate(){
        //given
        LocalDateTime date = LocalDateTime.of(
                2019,
                Month.NOVEMBER,
                11,
                12,
                0,
                0);

        //when
        LocalDateTime result = schedulingService.getValidDate(date, executionTimeWindowStart);

        //then
        assertEquals(date, result);
    }

    @Test
    void whenInvalidDate(){

        //given
        LocalDateTime date = null;

        //when
        LocalDateTime result = schedulingService.getValidDate(date, executionTimeWindowStart);

        //then
        assertEquals(executionTimeWindowStart, result);
    }


    @Test
    void whenSameTimeWindowException() throws Exception {
        //given
        jobsList = getJobsArrayMocked();
        LocalDateTime sameTimeWindow = LocalDateTime.of(
                2019,
                Month.NOVEMBER,
                10,
                9,
                0,
                0);

        //when
        try{
            schedulingService.validateTimeWindow(sameTimeWindow, sameTimeWindow);
        }catch (TimeWindowException ex){
            //then
            assertEquals( "Time Window too short", ex.getMessage());
        }
    }

    @Test
    void whenInitialTimeWindowAfterFinalException() throws Exception {
        //given
        jobsList = getJobsArrayMocked();
        LocalDateTime initialTimeWindow = LocalDateTime.of(
                2019,
                Month.NOVEMBER,
                10,
                9,
                0,
                0);


        LocalDateTime finalTimeWindow = LocalDateTime.of(
                2019,
                Month.NOVEMBER,
                9,
                9,
                0,
                0);

        //when
        try{
            schedulingService.validateTimeWindow(initialTimeWindow, finalTimeWindow);
        }catch (TimeWindowException ex){
            //then
            assertEquals( "Invalid Window Time", ex.getMessage());
        }
    }


    @Test
    void whenJobListSameDateThenSort() throws Exception {
        //given
        jobsList = getJobsArrayMocked();
        LocalDateTime jobDateTime = LocalDateTime.of(2019,
                Month.NOVEMBER,
                10,
                12,
                0,
                0);
        jobsList.add(new Job(4,"Importação de arquivos de fundos 2", jobDateTime, Duration.ofHours(1)));
        jobsList.add(new Job(5,"Importação de arquivos de fundos 3", jobDateTime, Duration.ofHours(1)));

        //when
        List<Job> result = schedulingService.sortListByDateAndFilter(jobsList, executionTimeWindowStart, executionTimeWindowEnd);
        assertEquals(1,result.get(0).getId());
        assertEquals(3,result.get(1).getId());
        assertEquals(4,result.get(2).getId());
        assertEquals(5,result.get(3).getId());
        assertEquals(2,result.get(4).getId());
    }

    @Test
    void whenJobListWithConclusionOutSideTimeWindowThenFilter() throws Exception {
        //given
        jobsList = getJobsArrayMocked();
        LocalDateTime jobDateTime = LocalDateTime.of(2019,
                Month.NOVEMBER,
                10,
                12,
                0,
                0);
        jobsList.add(new Job(4,"Importação de arquivos de fundos 2", jobDateTime, Duration.ofHours(27)));
        jobsList.add(new Job(5,"Importação de arquivos de fundos 3", jobDateTime, Duration.ofHours(26)));

        //when
        List<Job> result = schedulingService.sortListByDateAndFilter(jobsList, executionTimeWindowStart, executionTimeWindowEnd);

        //then
        assertEquals(1,result.get(0).getId());
        assertEquals(3,result.get(1).getId());
        assertEquals(2,result.get(2).getId());

    }

    private List<Job> getJobsArrayMocked() {
        jobsList = new ArrayList<Job>(Arrays.asList(
                getJob1Mocked(),
                getJob2Mocked(),
                getJob3Mocked()
        ));
        return jobsList;
    }

    private Job getJob1Mocked() {
        LocalDateTime jobDateTime = LocalDateTime.of(2019,
                Month.NOVEMBER,
                10,
                12,
                0,
                0);
        return new Job(1,"Importação de arquivos de fundos", jobDateTime, Duration.ofHours(2));
    }

    private Job getJob2Mocked() {
        LocalDateTime jobDateTime = LocalDateTime.of(2019,
                Month.NOVEMBER,
                11,
                12,
                0,
                0);
        return new Job(2,"Importação de dados da Base Legada", jobDateTime, Duration.ofHours(4));
    }


    private Job getJob3Mocked() {
        LocalDateTime jobDateTime = LocalDateTime.of(2019,
                Month.NOVEMBER,
                10,
                12,
                0,
                0);
        return new Job(3,"Importação de dados de integração", jobDateTime, Duration.ofHours(6));
    }

}