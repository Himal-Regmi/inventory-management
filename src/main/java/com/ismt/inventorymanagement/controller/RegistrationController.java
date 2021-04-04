package com.ismt.inventorymanagement.controller;

import com.ismt.inventorymanagement.entity.User;
import com.ismt.inventorymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String registerUser(Model model) {
        model.addAttribute("user",new User());

        return "register-form";
    }

    @PostMapping("/confirm-registration")
    public String confirmUserRegistration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        User existing = userService.findUserByEmail(user.getEmail());
        if (existing != null){
            bindingResult.rejectValue("email",null , "Email Already Used..");
        }

        if (bindingResult.hasErrors()){
            return "register-form";
        }

        userService.save(user);
        return "redirect:/register?success";
    }
}
