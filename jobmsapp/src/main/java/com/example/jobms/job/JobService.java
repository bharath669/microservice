package com.example.jobms.job;


import com.example.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {

    boolean updateJob(Long id, Job updatedJob);

    boolean deleteJobById(Long id);

    boolean deleteJobAll();

    List<JobDTO> findAll();

    void createJob(Job job);


    JobDTO getJobById(Long id);

}