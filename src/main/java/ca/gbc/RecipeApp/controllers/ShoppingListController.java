package ca.gbc.RecipeApp.controllers;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Implementing the functionalities and endpoints for Shopping List
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Ingredient;
import ca.gbc.RecipeApp.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShoppingListController {
    @Autowired
    private IngredientService service;


    @RequestMapping("/shoppingCart/items")
    public String viewItemList(Model model){

        List<Ingredient> listIngredients = service.listAll();
        model.addAttribute("listIngredients", listIngredients);
        return "shoppingCart/shopping_cart";
    }

    @RequestMapping("/shoppingCart/new")
    public String showNewItem(Model model) {
        Ingredient ingredient = new Ingredient();
        model.addAttribute("ingredient", ingredient);

        return "shoppingCart/new_item";
    }
    @RequestMapping(value = "/shoppingCart/save", method = RequestMethod.POST)
    public String saveItem(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "shoppingCart/new_item";
        } else {
            service.save(ingredient);

            return "redirect:/shoppingCart/items";
        }

    }
    @RequestMapping("/shoppingCart/edit/{id}")
    public ModelAndView showEditItemPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("shoppingCart/edit_item");
        Ingredient ingredient = service.get(id);
        mav.addObject("ingredient", ingredient);

        return mav;
    }

    @RequestMapping("/shoppingCart/delete/{id}")
    public String deleteItem(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/shoppingCart/items";
    }
}
