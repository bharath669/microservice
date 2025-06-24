package com.example.jobms.job;

import com.example.jobms.job.dto.JobCompanyDTO;

import java.util.List;

public interface JobService {

    boolean updateJob(Long id, Job updatedJob);

    boolean deleteJobById(long id);

    List<JobCompanyDTO> findAll();

    void createJob(Job job);


    JobCompanyDTO getJobById(Long id);

}