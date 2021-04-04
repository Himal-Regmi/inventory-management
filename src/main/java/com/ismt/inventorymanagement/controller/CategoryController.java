package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.dao.CategoryRepository;
import com.ismt.inventorymanagement.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public String ShowCustomers(Model model){
        List<Category> categoryList= categoryRepository.findAll();
        model.addAttribute("categoryList",categoryList);
        return "category/list";
    }
    @GetMapping("/save-form")
    public String ShowSaveForm(Model model){
        Category category= new Category();
        model.addAttribute("category",category);
        return "category/save-form";
    }
    @GetMapping("/update-form")
    public String updateCustomer(@RequestParam("id") int id, Model model){
        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category",category);
        return "category/save-form";
    }
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("category") @Valid Category category,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "category/save-form";
        }else{
            categoryRepository.save(category);
            return "redirect:/category/save-form?success";
        }
    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id){
        categoryRepository.deleteById(id);
        return "redirect:/category/list?deletionSuccess";
    }
}
