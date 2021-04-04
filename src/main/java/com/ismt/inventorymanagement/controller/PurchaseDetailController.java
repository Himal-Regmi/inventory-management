package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.dao.ProductRepository;
import com.ismt.inventorymanagement.dao.PurchaseDetailRepository;
import com.ismt.inventorymanagement.dao.PurchaseRepository;
import com.ismt.inventorymanagement.dto.PurchaseDetailDto;
import com.ismt.inventorymanagement.entity.Product;
import com.ismt.inventorymanagement.entity.PurchaseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("purchase-detail")
public class PurchaseDetailController {
    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/list")
    public String ShowPurchaseDetail(Model model){
        List<PurchaseDetail> purchaseDetailList= purchaseDetailRepository.findAll();
        model.addAttribute("purchaseDetailList",purchaseDetailList);
        return "purchase-detail/list";
    }
    @GetMapping("/save-form")
    public String ShowSaveForm(Model model){
        PurchaseDetailDto purchaseDetailDto= new PurchaseDetailDto();
        model.addAttribute("purchaseDetailDto",purchaseDetailDto);
        model.addAttribute("purchaseList", purchaseRepository.findAll());
        model.addAttribute("productList", productRepository.findAll());
        return "purchase-detail/save-form";
    }
    @GetMapping("/update-form")
    public String update(@RequestParam("id") int id, Model model){
        PurchaseDetail purchaseDetail= purchaseDetailRepository.findById(id).get();
        PurchaseDetailDto purchaseDetailDto= purchaseDetail.changeToPurchaseDetailDto();


        model.addAttribute("purchaseDetailDto",purchaseDetailDto);
        model.addAttribute("purchaseList", purchaseRepository.findAll());
        model.addAttribute("productList", productRepository.findAll());
        return "purchase-detail/save-form";
    }
    @PostMapping("/save")
    public String savePurchaseDetail(@ModelAttribute("purchaseDetailDto") @Valid PurchaseDetailDto purchaseDetailDto,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "purchase-detail/save-form";
        }else if(purchaseDetailDto.getPurchaseId()==0) {
            bindingResult.reject("Please select a Purchase");
            return "purchase-detail/save-form";
        }else if(purchaseDetailDto.getProductId()==0){
                bindingResult.reject("Please select a product");
                return "purchase-detail/save-form";
        }else{
            PurchaseDetail oldPurchaseDetail;
            Product product;
            if(purchaseDetailDto.getId()!=0){
                oldPurchaseDetail=purchaseDetailRepository.findById(purchaseDetailDto.getId()).get();
                product=productRepository.findById(oldPurchaseDetail.getProduct().getId()).get();
                product.setQuantity(product.getQuantity()- oldPurchaseDetail.getQuantity());
                productRepository.save(oldPurchaseDetail.getProduct());
            }
            PurchaseDetail purchaseDetail= purchaseDetailDto.changeToPurchaseDetail(
                purchaseDetailDto.getId(),
                purchaseRepository.findById(purchaseDetailDto.getPurchaseId()).get(),
                productRepository.findById(purchaseDetailDto.getProductId()).get(),
                purchaseDetailDto.getQuantity(),
                purchaseDetailDto.getRate()
            );
            purchaseDetailRepository.save(purchaseDetail);
            purchaseDetail.getProduct().setQuantity(purchaseDetail.getProduct().getQuantity()+
                                                    purchaseDetail.getQuantity());
            productRepository.save(purchaseDetail.getProduct());
            return "redirect:/purchase-detail/save-form?success";
        }
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        purchaseDetailRepository.deleteById(id);
        return "redirect:/purchase-detail/list?deletionSuccess";
    }
}
