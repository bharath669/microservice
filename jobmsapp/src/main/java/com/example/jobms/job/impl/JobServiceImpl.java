package com.example.jobms.job.impl;

import com.example.jobms.job.Job;
import com.example.jobms.job.JobRepository;
import com.example.jobms.job.JobService;
import com.example.jobms.job.dto.JobWithCompanyDTO;
import com.example.jobms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    //    private List<Job> jobs=new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        return jobs.stream().map(this::convertToDo).collect(Collectors.toList());
    }

        public JobWithCompanyDTO convertToDo(Job job){
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            RestTemplate restTemplate = new RestTemplate();
            Company company = restTemplate.getForObject("http://localhost:8084/companies/"
                    + job.getCompanyId(), Company.class);
            jobWithCompanyDTO.setCompany(company);
            return jobWithCompanyDTO;
    }

    @Override
    public void createJob(Job job) {
        // job.setId(nextId++);
        jobRepository.save(job);
    }
    @Override
    public Job getJobById(Long id){
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteJobAll() {
        try {
            jobRepository.deleteAll();
            return true;
        } catch (Exception e){
            return false;
        }
    }




    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional=jobRepository.findById(id);

        if(jobOptional.isPresent()){

            Job job=jobOptional.get();
            job.setId(updatedJob.getId());
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setCompanyId(updatedJob.getCompanyId());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
