package ca.gbc.RecipeApp.bootstrap;
//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 1
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   November 7th, 2021
//        * Description:            to have some data for recipe and users in the database to start with
//        *********************************************************************************
import ca.gbc.RecipeApp.domain.Ingredient;
import ca.gbc.RecipeApp.domain.Recipe;
import ca.gbc.RecipeApp.domain.User;
import ca.gbc.RecipeApp.repositories.RecipeRepository;
import ca.gbc.RecipeApp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class BootStrap implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public BootStrap(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Jane", "sue_jones", "$2a$12$igKioMGhbbVcdqahkwWMLOMQHemvQ0qbwy9z1sj9W985rDMMapRQm");
        Recipe recipe1 = new Recipe("pizza", "Italian pizza","2020-11-11");

        recipe1.addIngredient(new Ingredient("mushrooms", new BigDecimal(2)));
        recipe1.addIngredient(new Ingredient("onion", new BigDecimal(1)));
        user1.getRecipes().add(recipe1);
        recipe1.getUsers().add(user1);
//
        userRepository.save(user1);
        recipeRepository.save(recipe1);
    }
}
