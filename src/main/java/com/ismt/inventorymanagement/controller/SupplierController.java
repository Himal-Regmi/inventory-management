package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.dao.SupplierRepository;
import com.ismt.inventorymanagement.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/list")
    public String ShowSuppliers(Model model){
        List<Supplier> supplierList= supplierRepository.findAll();
        model.addAttribute("supplierList",supplierList);
        return "supplier/list";
    }
    @GetMapping("/save-form")
    public String ShowSaveForm(Model model){
        Supplier supplier=new Supplier();
        model.addAttribute("supplier",supplier);
        return "supplier/save-form";
    }
    @GetMapping("/update-form")
    public String updateSupplier(@RequestParam("id") int id, Model model){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        model.addAttribute("supplier",supplier);
        return "supplier/save-form";
    }
    @PostMapping("/save")
    public String saveSupplier(@ModelAttribute("supplier") @Valid Supplier supplier,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "supplier/save-form";
        }else{
            supplierRepository.save(supplier);
            return "redirect:/supplier/save-form?success";
        }
    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id){
        supplierRepository.deleteById(id);
        return "redirect:/supplier/list?deletionSuccess";
    }
}
