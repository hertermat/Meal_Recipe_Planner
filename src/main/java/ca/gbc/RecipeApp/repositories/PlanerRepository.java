package ca.gbc.RecipeApp.repositories;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Defining the meal planner repository
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.Planer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanerRepository extends JpaRepository<Planer,Integer> {
    public Long countById(Integer id);
}
