package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.dao.CustomerRepository;
import com.ismt.inventorymanagement.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/list")
    public String ShowCustomers(Model model){
        List<Customer> customerList= customerRepository.findAll();
        model.addAttribute("customerList",customerList);
        return "customer/list";
    }
    @GetMapping("/save-form")
    public String ShowSaveForm(Model model){
        Customer customer=new Customer();
        model.addAttribute("customer",customer);
        return "customer/save-form";
    }
    @GetMapping("/update-form")
    public String updateCustomer(@RequestParam("id") int id, Model model){
        Optional<Customer> customer = customerRepository.findById(id);
        model.addAttribute("customer",customer);
        return "customer/save-form";
    }
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") @Valid Customer customer,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "customer/save-form";
        }else{
            customerRepository.save(customer);
            return "redirect:/customer/save-form?success";
        }
    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id){
        customerRepository.deleteById(id);
        return "redirect:/customer/list?deletionSuccess";
    }
}
