package com.example.jobms.job;


import java.util.List;

public interface JobService {

    boolean updateJob(Long id, Job updatedJob);

    boolean deleteJobById(long id);

    List<Job> findAll();

    void createJob(Job job);


    Job getJobById(Long id);

}