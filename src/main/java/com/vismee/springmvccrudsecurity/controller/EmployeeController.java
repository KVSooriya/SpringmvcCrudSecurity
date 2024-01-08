package com.vismee.springmvccrudsecurity.controller;

import com.vismee.springmvccrudsecurity.entity.Employee;
import com.vismee.springmvccrudsecurity.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController
{
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model)
    {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees",employees);
        return "employees/employee-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model)
    {
        model.addAttribute("employee",new Employee());
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee)
    {
        employeeService.save(theEmployee);
        // to avoid duplicate submission of data : using redirect
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateEmployee(@RequestParam("employeeId") int id,Model model)
    {
        Employee dbEmployee = employeeService.findById(id);
        model.addAttribute("employee",dbEmployee);
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id)
    {
        employeeService.deleteById(id);

        return "redirect:/employees/list";
    }
}
