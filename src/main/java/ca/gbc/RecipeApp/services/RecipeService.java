package ca.gbc.RecipeApp.services;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 1
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   November 7th, 2021
//        * Description:            Defining functions for the recipe
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Recipe;
import ca.gbc.RecipeApp.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> listAll(){
        return recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }
    public void save(Recipe recipe){
        recipeRepository.save(recipe);
    }
    public Recipe get(Long id){
        return recipeRepository.findById(id).get();
    }
    public void delete(Long id){
        recipeRepository.deleteById(id);
    }
    public List<Recipe> search(String title){return recipeRepository.findRecipeByTitle(title);}
}
