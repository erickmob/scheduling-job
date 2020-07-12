package com.erickmob.schedulingjob;

import com.erickmob.schedulingjob.controller.JobController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SchedulingJobApplicationTests {

	@Autowired
	JobController jobController;

	@Test
	void contextLoads() {
		assertThat(jobController).isNotNull();
	}

}
