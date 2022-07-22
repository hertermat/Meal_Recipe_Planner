package ca.gbc.RecipeApp.services;

//        *********************************************************************************
//        * Project:                Recipe App
//        * Assignment:             Assignment 2
//        * Author(s):              Forough Kiani, Matias Herter, Sehajpreet Kaur Khurana
//        * Student Number:         101282711, 101272358, 101282557
//        * Date:                   December 5th, 2021
//        * Description:            Defining the Custom User Details Services that implements
//                                  User Details Service
//        *********************************************************************************

import ca.gbc.RecipeApp.domain.CustomUserDetails;
import ca.gbc.RecipeApp.domain.Ingredient;
import ca.gbc.RecipeApp.domain.Recipe;
import ca.gbc.RecipeApp.domain.User;
import ca.gbc.RecipeApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class CustomUserDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
    public void updateResetPasswordToken(String userName) throws UserNotFoundException {
        User customer = userRepository.findByUsername(userName);
        if (customer != null) {
            customer.setResetPasswordToken(userName);
            userRepository.save(customer);
        } else {
            throw new UserNotFoundException("Could not find any customer with the User Name " + userName);
        }
    }

    public User getByResetPasswordToken(String userName) {
        return userRepository.findByResetPasswordToken(userName);
    }

    public void updatePassword(User customer, String newPassword) {
        customer.setPassword(newPassword);
        customer.setResetPasswordToken(null);
        userRepository.save(customer);
    }
    public void save(User user){
        userRepository.save(user);
    }
    public User get(Long id){
        return userRepository.findById(id).get();
    }



}