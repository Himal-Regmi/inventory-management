package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.dao.CategoryRepository;
import com.ismt.inventorymanagement.dao.ProductRepository;
import com.ismt.inventorymanagement.dao.UnitRepository;
import com.ismt.inventorymanagement.dto.ProductDto;
import com.ismt.inventorymanagement.entity.Category;
import com.ismt.inventorymanagement.entity.Product;
import com.ismt.inventorymanagement.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UnitRepository unitRepository;

    @GetMapping("/list")
    public String ShowProducts(Model model){
        List<Product> productList= productRepository.findAll();
        model.addAttribute("productList",productList);
        return "product/list";
    }
    @GetMapping("/save-form")
    public String ShowSaveForm(Model model){
        ProductDto productDto= new ProductDto();
        model.addAttribute("productDto",productDto);
        model.addAttribute("categoryList", categoryRepository.findAll());
        model.addAttribute("unitList", unitRepository.findAll());
        return "product/save-form";
    }
    @GetMapping("/update-form")
    public String updateProduct(@RequestParam("id") int id, Model model){
        Optional<Product> product = productRepository.findById(id);
        ProductDto productDto = product.get().createProductDto();
        model.addAttribute("productDto",productDto);
        model.addAttribute("categoryList", categoryRepository.findAll());
        model.addAttribute("unitList", unitRepository.findAll());
        return "product/save-form";
    }
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("productDto") @Valid ProductDto productDto,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "product/save-form";
        }else{
            Category category;
            Unit unit;
            if(productDto.getCategoryId()!=0){
                category=categoryRepository.findById(productDto.getCategoryId()).get();
            }else{
                category=null;
            }
            if(productDto.getUnitId()!=0){
                unit=unitRepository.findById(productDto.getUnitId()).get();
            }else{
                unit=null;
            }
            Product product= productDto.changeToProduct(productDto.getId(),
                                        productDto.getName(),
                                        category,
                                        productDto.getRate(),
                                        unit,
                                        productDto.getQuantity());
            productRepository.save(product);
            return "redirect:/product/save-form?success";
        }
    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id){
        productRepository.deleteById(id);
        return "redirect:/product/list?deletionSuccess";
    }
}
