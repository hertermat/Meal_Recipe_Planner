package ca.gbc.RecipeApp.repositories;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Defining the ingredient repository
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
//    void deleteById(Long recipeId, Long id);
}
