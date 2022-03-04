package com.example.springbootmvc.controller;

import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.entity.Employee;
import com.example.springbootmvc.repository.CompanyRepository;
import com.example.springbootmvc.repository.DepartmentRepository;
import com.example.springbootmvc.repository.EmployeeRepository;
import com.example.springbootmvc.service.CompanyService;
import com.example.springbootmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;



    //zaproslarni tutib ishlatish
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getEmployeePage(Model model) {
        model.addAttribute("employeeList", employeeRepository.findAll());
        //listini yuborish
        return "employee/employee";
    }

    @GetMapping("/add")
//    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String getSaveEmployee(Model model) {
        model.addAttribute("departmentList", departmentRepository.findAll());
        return "employee/employee-add";
    }

    @PostMapping("/add")
    public String saveCompany(Model model, @ModelAttribute Employee employee) {
        employeeService.add(employee);
        return "redirect:/employee";
    }


}
