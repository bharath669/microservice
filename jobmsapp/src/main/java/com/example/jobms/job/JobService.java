package com.example.jobms.job;


import com.example.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {

    boolean updateJob(Long id, Job updatedJob);

    boolean deleteJobById(Long id);

    boolean deleteJobAll();

    List<JobWithCompanyDTO> findAll();

    void createJob(Job job);


    JobWithCompanyDTO getJobById(Long id);

}