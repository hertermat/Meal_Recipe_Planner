package ca.gbc.RecipeApp.controllers;

//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Implementing the functionalities and endpoints for profile

import ca.gbc.RecipeApp.domain.Ingredient;
import ca.gbc.RecipeApp.domain.User;
import ca.gbc.RecipeApp.services.CustomUserDetailsServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
class ProfileController {

    private CustomUserDetailsServices customUserDetailsServices;

    @RequestMapping("/profile")
    public String profile(Model model){

        model.addAttribute("user", customUserDetailsServices);
        return "profile/profile";
    }

    @RequestMapping(value = "/profile/save", method = RequestMethod.POST)
    public String saveProfile(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "index";
        } else {
            customUserDetailsServices.save(user);

            return "redirect:/profile/profile";
        }

    }
    @RequestMapping("/profile/edit/{id}")
    public ModelAndView showEditProfilePage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("shoppingCart/edit_item");
        User user = customUserDetailsServices.get(id);
        mav.addObject("user", user);

        return mav;
    }

}
