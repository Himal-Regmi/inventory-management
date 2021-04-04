package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.dao.ProductRepository;
import com.ismt.inventorymanagement.dao.SaleDetailRepository;
import com.ismt.inventorymanagement.dao.SaleRepository;
import com.ismt.inventorymanagement.dto.SaleDetailDto;
import com.ismt.inventorymanagement.entity.Product;
import com.ismt.inventorymanagement.entity.SaleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("sale-detail")
public class SaleDetailController {
    @Autowired
    private SaleDetailRepository saleDetailRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/list")
    public String ShowSaleDetail(Model model){
        List<SaleDetail> saleDetailList= saleDetailRepository.findAll();
        model.addAttribute("saleDetailList",saleDetailList);
        return "sale-detail/list";
    }
    @GetMapping("/save-form")
    public String ShowSaveForm(Model model){
        SaleDetailDto saleDetailDto= new SaleDetailDto();
        model.addAttribute("saleDetailDto",saleDetailDto);
        model.addAttribute("saleList", saleRepository.findAll());
        model.addAttribute("productList", productRepository.findAll());
        return "sale-detail/save-form";
    }

    @GetMapping("/update-form")
    public String update(@RequestParam("id") int id, Model model){
        SaleDetail saleDetail= saleDetailRepository.findById(id).get();
        SaleDetailDto saleDetailDto = saleDetail.changeToSaleDetailDto();


        model.addAttribute("saleDetailDto",saleDetailDto);
        model.addAttribute("saleList", saleRepository.findAll());
        model.addAttribute("productList", productRepository.findAll());
        return "sale-detail/save-form";
    }
    @PostMapping("/save")
    public String saveSaleDetail(@ModelAttribute("saleDetailDto") @Valid SaleDetailDto saleDetailDto,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "sale-detail/save-form";
        }else if(saleDetailDto.getSaleId()==0) {
            bindingResult.reject("Please select a Sale");
            return "sale-detail/save-form";
        }else if(saleDetailDto.getProductId()==0){
                bindingResult.reject("Please select a product");
                return "purchase-detail/save-form";
        }else{
            SaleDetail oldSaleDetail;
            Product product;
            if(saleDetailDto.getId()!=0){
                oldSaleDetail=saleDetailRepository.findById(saleDetailDto.getId()).get();
                product=productRepository.findById(oldSaleDetail.getProduct().getId()).get();

                product.setQuantity(product.getQuantity()+ oldSaleDetail.getQuantity());

                productRepository.save(product);
            }
            if(productRepository.findById(saleDetailDto.getProductId()).get().getQuantity()<saleDetailDto.getQuantity()){
                bindingResult.reject("Not enough item in inventory");
                return "purchase-detail/save-form";
            }
            SaleDetail saleDetail= saleDetailDto.changeToSaleDetail(
                saleDetailDto.getId(),
                saleRepository.findById(saleDetailDto.getSaleId()).get(),
                productRepository.findById(saleDetailDto.getProductId()).get(),
                saleDetailDto.getQuantity(),
                saleDetailDto.getRate()
            );
            saleDetailRepository.save(saleDetail);
            saleDetail.getProduct().setQuantity(saleDetail.getProduct().getQuantity()-
                                                    saleDetail.getQuantity());
            productRepository.save(saleDetail.getProduct());
            return "redirect:/sale-detail/save-form?success";
        }
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        saleDetailRepository.deleteById(id);
        return "redirect:/sale-detail/list?deletionSuccess";
    }
}
