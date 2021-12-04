package ca.gbc.RecipeApp.controllers;

import ca.gbc.RecipeApp.domain.Ingredient;
import ca.gbc.RecipeApp.domain.Recipe;
import ca.gbc.RecipeApp.services.IngredientService;
import ca.gbc.RecipeApp.services.RecipeService;
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
        model.addAttribute("ingredient", ingredientService
                .update(recipeId,id));
        return "ingredients/ingredient_form";
    }
    @RequestMapping("/recipe/ingredient")
    public String saveOrUpdate (@ModelAttribute Ingredient ingredient){
       ingredientService.save(ingredient);

        return "redirect:/list";
    }
//    @RequestMapping("/ingredient/list")
//    public String listIngredients(Model model){
//
//        List<Ingredient> listIngredients = service.listAll();
//        model.addAttribute("listIngredients", listIngredients);
//        return "ingredients/list_ingredients";
//    }
//
//    @RequestMapping("/ingredient/new")
//    public String showNewIngredient(Model model) {
//        Ingredient ingredient = new Ingredient();
//        model.addAttribute("ingredient", ingredient);
//        return "ingredients/new_ingredient";
//    }
//    @RequestMapping(value = "/ingredient/save", method = RequestMethod.POST)
//    public String saveIngredient(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "ingredients/new_ingredient";
//        } else {
//            service.save(ingredient);
//            return "redirect:/ingredient/list";
//        }
//    }
//    @RequestMapping("/ingredient/{id}/show")
//    public String showById(@PathVariable(name = "id") Long id, Model model) {
//        model.addAttribute("ingredient", service.get(id));
//        return "ingredients/show_ingredient";
//    }
//    @RequestMapping("ingredient/{id}/edit")
//    public ModelAndView showEditIngredientPage(@PathVariable(name = "id") Long id) {
//        ModelAndView mav = new ModelAndView("ingredients/edit_ingredient");
//        Ingredient ingredient = service.get(id);
//        mav.addObject("ingredient", ingredient);
//
//        return mav;
//    }
////    @RequestMapping("/search")
////    public String searchRecipe(String keyword, Model model) {
////        List<Recipe> listRecipes = service.search(keyword);
////        model.addAttribute("listRecipes", listRecipes);
////        return "recipes/recipe";
////    }
//    @RequestMapping("/ingredient/{id}/delete")
//    public String deleteIngredient(@PathVariable(name = "id") Long id) {
//        service.delete(id);
//        return "redirect:/ingredient/list";
//    }
}

