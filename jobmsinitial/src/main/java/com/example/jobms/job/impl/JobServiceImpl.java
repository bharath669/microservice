package com.example.jobms.job.impl;

import com.example.jobms.job.Clients.CompanyClient;
import com.example.jobms.job.Clients.ReviewClient;
import com.example.jobms.job.Job;
import com.example.jobms.job.JobRepository;
import com.example.jobms.job.JobService;
import com.example.jobms.job.dto.JobCompanyDTO;
import com.example.jobms.job.external.Company;
import com.example.jobms.job.external.Review;
import com.example.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    //public Long nextId=1L;

    @Autowired
    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository,CompanyClient
            companyClient,ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }

    @Override
    public List<JobCompanyDTO> findAll() {
        List<Job> jobs=jobRepository.findAll();
        List<JobCompanyDTO>jobCompanyDTOs=new ArrayList<>();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    private JobCompanyDTO convertToDto(Job job){
        Company company=companyClient.getCompany(job.getCompanyId());
        List<Review>reviews=reviewClient.getReviews(job.getCompanyId());
        JobCompanyDTO jobCompanyDTO= JobMapper.mapToJobWithCompanyDto(company,job,reviews );
        //jobCompanyDTO.setCompany(company);

        return jobCompanyDTO;
    }
    @Override
    public void createJob(Job job) {
       // job.setId(nextId++);
        jobRepository.save(job);
    }
    @Override
    public JobCompanyDTO getJobById(Long id){
        Job job=jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
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
                jobRepository.save(job);
                return true;
            }
        return false;
    }
}

