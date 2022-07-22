package ca.gbc.RecipeApp.controllers;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Implementing the functionalities and endpoints for Reset Password
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.User;
import ca.gbc.RecipeApp.services.CustomUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import javax.servlet.http.HttpServletRequest;

public class ResetPasswordController {
    @Autowired
    private CustomUserDetailsServices customerService;

    @GetMapping("/reset_password")
    public String showForgotPasswordForm() {
        return "reset_password";
    }

    @PostMapping("/reset_password")
    public String processForgotPassword() {

        return null;
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(String userName,Model model) {
        if( userName == null){
            model.addAttribute("message","Invalid User Name");
            return "message";
        }
        return "reset_password";


    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("userName");
        String password = request.getParameter("password");

        User user = customerService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid User Name");
            return "message";
        } else {
            customerService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "message";
    }


}
