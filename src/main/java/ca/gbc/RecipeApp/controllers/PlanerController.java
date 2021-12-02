package ca.gbc.RecipeApp.controllers;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 1
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   November 7th, 2021
//        * Description:            Implementing the functionalities and endpoints for meal planner
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Planer;
import ca.gbc.RecipeApp.domain.Recipe;
import ca.gbc.RecipeApp.services.PlanerService;
import ca.gbc.RecipeApp.services.RecipeService;
import ca.gbc.RecipeApp.services.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlanerController {
    @Autowired
    private PlanerService service;
    @Autowired
    private RecipeService service2;
    @GetMapping("/planer")
    public String showPlanerList(Model model){
        List<Planer> planerList = service.listAll();
        model.addAttribute("planerList",planerList);

        return "planer/planer_list";

    }

    @GetMapping("/planer/new")
    public String showNewForm(Model model){
        model.addAttribute("pageTitle","Add New Meal to Your Planer");

        List<Recipe> recipes = new ArrayList<>();
        model.addAttribute("recipes", recipes);
        List<Recipe> listRecipes = service2.listAll();
        model.addAttribute("listRecipes", listRecipes);

        model.addAttribute("planer",new Planer());

        return "planer/planer_form";
    }
    @PostMapping("/planer/save")
    public String saveMeal(Planer planer , RedirectAttributes redirectAttributes){
        service.save(planer);
        redirectAttributes.addFlashAttribute("message", "The meal has been added to your planer");
        //redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/planer";
    }

    @GetMapping("/planer/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){

        try {

            Planer planer=service.get(id);
            List<Recipe> recipes = new ArrayList<>();
            model.addAttribute("recipes", recipes);
            List<Recipe> listRecipes = service2.listAll();
            model.addAttribute("listRecipes", listRecipes);
            model.addAttribute("pageTitle","Edit Meal(ID:"+id+")");
            model.addAttribute("planer",planer);
            return "planer/planer_form";

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            //redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/planer";

        }

    }

    @GetMapping("/planer/delete/{id}")
    public String deleteMeal(@PathVariable("id") Integer id,RedirectAttributes ra){

        try {
            service.delete(id);
            ra.addFlashAttribute("message","Meal has been deleted");


        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            //redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        }
        return "redirect:/planer";
    }





}
