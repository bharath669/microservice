package com.example.jobms.job.mapper;

import com.example.jobms.job.Job;
import com.example.jobms.job.dto.JobCompanyDTO;
import com.example.jobms.job.external.Company;
import com.example.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobCompanyDTO mapToJobWithCompanyDto(Company company, Job job, List<Review> reviews){
        JobCompanyDTO jobCompanyDTO=new JobCompanyDTO();
        jobCompanyDTO.setId(job.getId());
        jobCompanyDTO.setTitle(job.getTitle());
        jobCompanyDTO.setDescription(job.getDescription());
        jobCompanyDTO.setMinSalary(job.getMinSalary());
        jobCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobCompanyDTO.setLocation(job.getLocation());
        jobCompanyDTO.setCompany(company);
        jobCompanyDTO.setReviews(reviews);
        return jobCompanyDTO; 
    }
}
