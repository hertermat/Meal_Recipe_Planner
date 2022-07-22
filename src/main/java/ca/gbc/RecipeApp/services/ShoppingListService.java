package ca.gbc.RecipeApp.services;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Defining functions for the shopping list
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Ingredient;
import ca.gbc.RecipeApp.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingListService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> listAll(){
        return ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }
    public void save(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }
    public Ingredient get(Long id){
        return ingredientRepository.findById(id).get();
    }
    public void delete(Long id){
        ingredientRepository.deleteById(id);
    }
}
