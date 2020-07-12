package com.erickmob.schedulingjob.service;

import com.erickmob.schedulingjob.Dto.JobsDTO;
import com.erickmob.schedulingjob.TestUtil;
import com.erickmob.schedulingjob.model.Job;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JobServiceTest {

    @Autowired
    private JobService jobService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void convertWhenValidTest() {
        //given
        JobsDTO jobsDTOList = TestUtil.getValidContent();

        //when
        List<Job> jobList = jobService.createFromJobListDto(jobsDTOList.getJobList());

        //then
        assertEquals(3, jobList.size());
        assertEquals(1, jobList.get(0).getId());
        assertEquals(2, jobList.get(1).getId());
        assertEquals(3, jobList.get(2).getId());

    }
}
