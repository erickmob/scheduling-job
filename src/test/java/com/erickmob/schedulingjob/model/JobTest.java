package com.erickmob.schedulingjob.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;


class JobTest {

    @Test
    void getId() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when
        long result = job.getId();
        //then
        Assert.assertEquals(1,result);
    }

    @Test
    void getDescricao() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when
        String descricao = job.getDescricao();
        //then
        Assert.assertEquals("teste", descricao);
    }

    @Test
    void getDataMaximaDeConclusao() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when
        LocalDateTime result = job.getDataMaximaDeConclusao();

        //then
        Assert.assertEquals(dateTime, result);
    }

    @Test
    void getTempoEstimado() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when
        Duration duration = Duration.ofHours(2);

        //then
        Assert.assertEquals(2l, duration.toHours());
    }

    @Test
    void setId() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when
        job.setId(2);


        //then
        Assert.assertEquals(2l, job.getId());
    }

    @Test
    void setDescricao() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when
        job.setDescricao("new");
        //then
        Assert.assertEquals("new", job.getDescricao());
    }

    @Test
    void setDataMaximaDeConclusao() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when
        LocalDateTime newdateTime = LocalDateTime.of(2019,Month.DECEMBER,10,9,0,0);
        job.setDataMaximaDeConclusao(newdateTime);


        //then
        Assert.assertEquals(newdateTime, job.getDataMaximaDeConclusao());
    }

    @Test
    void setTempoEstimado() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when
        job.setTempoEstimado(Duration.ofHours(3));

        //then
        Assert.assertEquals(3l, job.getTempoEstimado().toHours());
    }

    @Test
    void testEquals() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));
        //when

        Job job2 = new Job(1, "teste", dateTime, Duration.ofHours(2));
        //then
        assert (job.equals(job2));
    }

    @Test
    void testHashCode() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when
        int hash = job.hashCode();

        //then
        Assert.assertEquals(hash, 1568697534);
    }

    @Test
    void testToString() {
        //given
        LocalDateTime dateTime = LocalDateTime.of(2019,Month.NOVEMBER,10,9,0,0);
        Job job = new Job(1, "teste", dateTime, Duration.ofHours(2));

        //when

        //then
        Assert.assertEquals("Job(id=1, descricao=teste, dataMaximaDeConclusao=2019-11-10T09:00, tempoEstimado=PT2H)",job.toString());
    }
}