package com.erickmob.schedulingjob.controller;

import com.erickmob.schedulingjob.Dto.JobsDTO;
import com.erickmob.schedulingjob.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JobControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void sendDataWhenValid() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(
                        post("/sequenceJobs")
                                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                                .content(TestUtil.convertObjectToJsonBytes(TestUtil.getValidContent())))
                .andExpect(status().isOk());

        String expected = "[[3],[2,1]]";
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        assert(contentAsString.equals(expected));
    }

    @Test
    void sendDataWhenSameDateAndExpectErrorStatus() throws Exception {

        JobsDTO jobsDTO = TestUtil.getValidContent();
        jobsDTO.setFimJanelaDeExecucao(LocalDateTime.of(
                2019,
                Month.NOVEMBER,
                10,
                9,
                0,
                0));
        ResultActions resultActions = mockMvc
                .perform(
                        post("/sequenceJobs")
                                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                                .content(TestUtil.convertObjectToJsonBytes(jobsDTO)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void sendEmptyDataExpectErrorStatus() throws Exception {

        JobsDTO jobsDTO = new JobsDTO();
        ResultActions resultActions = mockMvc
                .perform(
                        post("/sequenceJobs")
                                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                                .content(TestUtil.convertObjectToJsonBytes(jobsDTO)))
                .andExpect(status().isNotAcceptable());

    }
}
