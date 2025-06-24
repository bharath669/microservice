package com.example.company.company;

import java.util.List;

public interface CompanyService {
    List<Company>getAllCompanies();

    boolean updatedCompany(Company company,Long id);
    void createCompany(Company company);
    boolean deleteCompanyByID(Long id);
    Company getCompanyById(Long id);
}
