package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.dao.CustomerRepository;
import com.ismt.inventorymanagement.dao.SaleRepository;
import com.ismt.inventorymanagement.dto.SaleDto;
import com.ismt.inventorymanagement.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("sale")
public class SaleController {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/list")
    public String ShowSale(Model model){
        List<Sale> saleList= saleRepository.findAll();
        model.addAttribute("saleList",saleList);
        return "sale/list";
    }
    @GetMapping("/save-form")
    public String ShowSaveForm(Model model){
        SaleDto saleDto=new SaleDto();
        model.addAttribute("saleDto",saleDto);
        model.addAttribute("customerList", customerRepository.findAll());
        return "sale/save-form";
    }
    @GetMapping("/update-form")
    public String updateSale(@RequestParam("id") int id, Model model){
        Optional<Sale> sale = saleRepository.findById(id);
        SaleDto saleDto = sale.get().createSaleDto();
        model.addAttribute("saleDto",saleDto);
        model.addAttribute("customerList", customerRepository.findAll());
        return "sale/save-form";
    }
    @PostMapping("/save")
    public String saveSale(@ModelAttribute("saleDto") @Valid SaleDto saleDto,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "sale/save-form";
        }else if(saleDto.getCustomerId()==0){
            bindingResult.reject("Please select a customer");
            return "sale/save-form";

        }else{
            Sale sale= saleDto.changeToSale(saleDto.getId(),
                                        saleDto.getDate(),
                                        customerRepository.findById(saleDto.getCustomerId()).get(),
                                        saleDto.getTotal(),
                                        saleDto.getDiscount());
            saleRepository.save(sale);
            return "redirect:/sale/save-form?success";
        }
    }
    @GetMapping("/delete")
    public String deleteSale(@RequestParam("id") int id){
        saleRepository.deleteById(id);
        return "redirect:/sale/list?deletionSuccess";
    }
}
