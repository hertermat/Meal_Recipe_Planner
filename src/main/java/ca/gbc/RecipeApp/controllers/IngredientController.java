package ca.gbc.RecipeApp.controllers;

//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Implementing the functionalities and endpoints for ingredients

import ca.gbc.RecipeApp.domain.Ingredient;
import ca.gbc.RecipeApp.domain.Recipe;
import ca.gbc.RecipeApp.services.IngredientService;
import ca.gbc.RecipeApp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@Controller
public class IngredientController {
    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }
    @RequestMapping({"/ingredient/list","/recipe/{recipeId}/ingredients"})
    public String listIngredients(@PathVariable Long recipeId, Model model){
        model.addAttribute("recipe", recipeService.get(recipeId));
        return "ingredients/list_ingredients";
    }
    @RequestMapping("/ingredient/{id}/show")
    public String showById(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("ingredient", ingredientService.get(id));
        return "ingredients/show_ingredient";
    }
    @RequestMapping("/ingredient/{id}/edit")
    public ModelAndView showEditIngredientPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("ingredients/edit_ingredient");
        Ingredient ingredient = ingredientService.get(id);
        mav.addObject("ingredient", ingredient);

        return mav;
    }
    @RequestMapping("/ingredient/new")
    public String showNewIngredient(Model model) {
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);
        model.addAttribute("ingredient", ingredient);

        return "ingredients/new_ingredient";
    }
    @RequestMapping(value = "/ingredient/save", method = RequestMethod.POST)
    public String saveIngredient(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ingredients/new_ingredient";
        } else {
            ingredientService.save(ingredient);
            return "redirect:/list";
        }
    }

    @RequestMapping("ingredient/{id}/update")
    public String updateRecipeIngredient (@PathVariable Long recipeId,
                                          @PathVariable Long id,
                                          Model model){
        Recipe recipe = recipeService.get(recipeId);
        recipeId = recipe.getId();
        model.addAttribute("ingredient", ingredientService
                .update(recipeId,id));
        return "ingredients/ingredient_form";
    }
    @RequestMapping("/recipe/{id}/ingredient")
    public String saveOrUpdate (@ModelAttribute Ingredient ingredient){
       ingredientService.save(ingredient);

        return "redirect:/list";
    }
    @RequestMapping("/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable(name = "id") Long id) {
        ingredientService.delete(id);
        return "redirect:/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/add")
    public String addRecipeIngredient (@PathVariable Long recipeId,
                                       Model model){
        Recipe recipe = recipeService.get(recipeId);

        Ingredient ingredient = new Ingredient();
        recipeId = ingredient.getRecipe().getId();
        model.addAttribute("ingredient", ingredient);

        return "/ingredients/ingredient_form";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteRecipeIngredient (@PathVariable Long recipeId,
                                          @PathVariable Long id){
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));
        return "redirect:/list";
    }
}

