package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.dao.PurchaseRepository;
import com.ismt.inventorymanagement.dao.SupplierRepository;
import com.ismt.inventorymanagement.dto.PurchaseDto;
import com.ismt.inventorymanagement.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/list")
    public String ShowPurchase(Model model){
        List<Purchase> purchaseList= purchaseRepository.findAll();
        model.addAttribute("purchaseList",purchaseList);
        return "purchase/list";
    }
    @GetMapping("/save-form")
    public String ShowSaveForm(Model model){
        PurchaseDto purchaseDto=new PurchaseDto();
        model.addAttribute("purchaseDto",purchaseDto);
        model.addAttribute("supplierList",supplierRepository.findAll());
        return "purchase/save-form";
    }
    @GetMapping("/update-form")
    public String updatePurchase(@RequestParam("id") int id, Model model){
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        PurchaseDto purchaseDto = purchase.get().createPurchaseDto();
        model.addAttribute("purchaseDto",purchaseDto);
        model.addAttribute("supplierList",supplierRepository.findAll());
        return "purchase/save-form";
    }
    @PostMapping("/save")
    public String savePurchase(@ModelAttribute("purchaseDto") @Valid PurchaseDto purchaseDto,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "purchase/save-form";
        }else if(purchaseDto.getSupplierId()==0){
            bindingResult.reject("Please select a supplier");
            return "purchase/save-form";
        }else{
            Purchase purchase= purchaseDto.changeToPurchase(purchaseDto.getId(),
                                        purchaseDto.getDate(),
                                        supplierRepository.findById(purchaseDto.getSupplierId()).get(),
                                        purchaseDto.getTotal(),
                                        purchaseDto.getDiscount());
            purchaseRepository.save(purchase);
            return "redirect:/purchase/save-form?success";
        }
    }
    @GetMapping("/delete")
    public String deletePurchase(@RequestParam("id") int id){
        purchaseRepository.deleteById(id);
        return "redirect:/purchase/list?deletionSuccess";
    }
}
