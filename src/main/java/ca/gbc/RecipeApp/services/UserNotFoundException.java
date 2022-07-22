package ca.gbc.RecipeApp.services;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Defining functions for error handling
//        *********************************************************************************

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
