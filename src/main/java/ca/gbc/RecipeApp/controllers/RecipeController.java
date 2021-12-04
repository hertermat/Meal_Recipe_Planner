package ca.gbc.RecipeApp.controllers;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 1
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   November 7th, 2021
//        * Description:            Implementing the functionalities and endpoints for recipes
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Recipe;
import ca.gbc.RecipeApp.services.RecipeService;
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
public class RecipeController {
    @Autowired
    private RecipeService service;


    @RequestMapping("/list")
    public String viewHomePage(Model model){

        List<Recipe> listRecipes = service.listAll();
        model.addAttribute("listRecipes", listRecipes);
        return "recipes/list_recipes";
    }

    @RequestMapping("/new")
    public String showNewRecipe(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);

        return "recipes/new_recipe";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "recipes/new_recipe";
        } else {
            service.save(recipe);

            return "redirect:/list";
        }

    }
    @RequestMapping("/show/{id}")
    public String showById(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("recipe", service.get(id));
        return "recipes/show_recipe";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditRecipePage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("recipes/edit_recipe");
        Recipe recipe = service.get(id);
        mav.addObject("recipe", recipe);

        return mav;
    }

    @RequestMapping("/search")
    public String searchRecipe(String keyword, Model model) {
        List<Recipe> listRecipes = service.search(keyword);
        model.addAttribute("listRecipes", listRecipes);
        return "recipes/recipe";
}
    @RequestMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/list";
    }

}

