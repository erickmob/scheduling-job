package com.erickmob.schedulingjob.service;

import com.erickmob.schedulingjob.model.Job;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
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
}