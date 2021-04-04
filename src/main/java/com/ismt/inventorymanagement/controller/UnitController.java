package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.dao.UnitRepository;
import com.ismt.inventorymanagement.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("unit")
public class UnitController {
    @Autowired
    private UnitRepository unitRepository;

    @GetMapping("/list")
    public String ShowUnits(Model model){
        List<Unit> unitList= unitRepository.findAll();
        model.addAttribute("unitList",unitList);
        return "unit/list";
    }
    @GetMapping("/save-form")
    public String ShowSaveForm(Model model){
        Unit unit= new Unit();
        model.addAttribute("unit",unit);
        return "unit/save-form";
    }
    @GetMapping("/update-form")
    public String updateUnit(@RequestParam("id") int id, Model model){
        Unit unit = unitRepository.findById(id).get();
        model.addAttribute("unit",unit);
        return "unit/save-form";
    }
    @PostMapping("/save")
    public String saveUnit(@ModelAttribute("unit") @Valid Unit unit,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "unit/save-form";
        }else{
            unitRepository.save(unit);
            return "redirect:/unit/save-form?success";
        }
    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id){
        unitRepository.deleteById(id);
        return "redirect:/unit/list?deletionSuccess";
    }
}
