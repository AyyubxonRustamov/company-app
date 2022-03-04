package com.example.springbootmvc.service;

import com.example.springbootmvc.dto.ApiResponse;
import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse add(Company company) {
        Company save = companyRepository.save(company);
        return new ApiResponse("Saved", true, save);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        companyRepository.deleteById(id);
        return "redirect:/company";
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable(value = "id") Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        Company company = byId.get();
        model.addAttribute("company", company);
        return "company/company-edit";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, @ModelAttribute Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        Company company1 = optionalCompany.get();
        company1.setId(company.getId());
        company1.setName(company.getName());
        companyRepository.save(company1);
        return "redirect:/company";
    }
}
